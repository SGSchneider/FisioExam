package br.ufsm.fisioexam.ui.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.ufsm.fisioexam.R;
import br.ufsm.fisioexam.database.FirebaseHelper;
import br.ufsm.fisioexam.database.FisioExamDatabase;
import br.ufsm.fisioexam.database.dao.LoginInfoDAO;
import br.ufsm.fisioexam.database.sync.SyncBD;
import br.ufsm.fisioexam.database.thread.QueryManager;
import br.ufsm.fisioexam.model.LoginInfo;

public class ConfiguracoesActivity extends AppCompatActivity {
    public static final String TITULO_APPBAR = "Configurações";
    private static final int INTERNET_REQUEST_CODE = 1;

    private FirebaseAuth mAuth;
    private FisioExamDatabase database;
    private FirebaseHelper firebase;
    private SyncBD sincronizador;
    private EditText campoEmail;
    private EditText campoSenha;
    private String email;
    private String password;
    private Button buttonSync;
    private Button buttonBack;
    private Button buttonLogin;

    private List<LoginInfo> loginList;
    private LoginInfo loginInfo;
    private LoginInfoDAO lInfoDAO;
    private QueryManager<LoginInfo> qManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);


        setTitle(TITULO_APPBAR);
        checkPermissoes();
        inicializaBancoLogin();
        inicializaFirebase();
        inicializaBotoes();
        inicializaCamposLogin();
        carregaInfoLogin();
        addListenerBotoes();
    }

    private void inicializaBancoLogin() {
        FisioExamDatabase database = FisioExamDatabase.getInstance(this);
        lInfoDAO = database.getRoomLoginInfoDAO();
        qManager = new QueryManager<>();
    }

    private void checkPermissoes() {
        Log.i("PERMISSAO", "Verificando Permissão: Internet");
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            // Permissão não concedida. Solicite permissão em tempo de execução.
            Log.w("PERMISSAO", "Solicitando Permissão: Internet");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, INTERNET_REQUEST_CODE);
        } else {
            Log.i("PERMISSAO", "Possui Permissão: Internet");
        }
    }

    private void inicializaFirebase() {
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        database = FisioExamDatabase.getInstance(this);
        firebase = FirebaseHelper.getInstance();
    }

    private void addListenerBotoes() {
        buttonSync.setOnClickListener(view -> syncDB());
        buttonBack.setOnClickListener(view -> finish());
        buttonLogin.setOnClickListener(view -> logar());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == INTERNET_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permissão concedida. Execute a ação desejada.

                Log.i("PERMISSAO", "Permissão Concedida: Internet");
                // ...
            } else {
                checkPermissoes();
            }
        }
    }

    private void logar() {
        email = campoEmail.getText().toString();
        password = campoSenha.getText().toString();
        if (email != null && password != null && !email.isEmpty() && !password.isEmpty()) {
            AuthCredential credential = EmailAuthProvider.getCredential(email, password);
            mAuth.signInWithCredential(credential)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Login bem sucedido
                                Log.i("LOGIN", "Login Bem Sucedido");
                                showToast("Login Bem sucedido!", Toast.LENGTH_SHORT);
                                FirebaseUser user = mAuth.getCurrentUser();
                                firebase.setFbUser(user);
                                sincronizador = new SyncBD(database, firebase.getDatabaseReference(), firebase.getFbUser());
                                // Faça algo com o usuário logado

                                saveLoginInfo();
                            } else {
                                // Login falhou
                                Log.e("LOGIN", "Login Falhou");
                                showToast("Login Falhou!", Toast.LENGTH_SHORT);
                                // Exiba uma mensagem de erro ao usuário
                            }
                        }
                    });
        } else {
            Log.i("LOGIN", "Campos de Login Vazios");
            showToast("Credenciais Inválidas!", Toast.LENGTH_LONG);
        }
    }

    private void showToast(String texto, int length) {
        Toast toast = Toast.makeText(this, texto, length);
        toast.show();
    }


    private void saveLoginInfo() {
        loginInfo.setUser(email);
        loginInfo.setPassword(password);
        qManager.deleteAll(lInfoDAO);
        qManager.insert(loginInfo, lInfoDAO);
    }

    private void syncDB() {
        if (sincronizador != null) {
            if (isAdmin(firebase.getFbUser().getUid())) {
                syncAdmin();
            } else {
                sincronizador.syncNow();
            }
        } else {
            showToast("É necessário fazer o login primeiro!", Toast.LENGTH_SHORT);
        }
    }

    private boolean isAdmin(String uid) {
        try {
            String json = null;
            InputStream inputStream = this.getAssets().open("AdminUIDS.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");

            try {
                JSONObject object = new JSONObject(json);

                Iterator<String> keys = object.keys();
                while (keys.hasNext()) {
                    String key = keys.next();
                    String valor = object.getString(key);
                    if (valor.equals(uid)) {
                        return true;
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private void syncAdmin() {
        new AlertDialog.Builder(this)
                .setTitle("Sincronizando como Administrador")
                .setMessage("Tem certeza que deseja sincronizar como administrador?\n" +
                        "Ao Sincronizar como administrador os dados não sincronizados previamente serão apagados.\n" +
                        "Só prossiga caso tenha certeza de que nenhum dado importante será perdido!")
                .setPositiveButton("Sim", (dialog, which) -> {
                    sincronizador.syncNowAdmin();
                })
                .setNegativeButton("Não", null)
                .show();
    }


    private void inicializaBotoes() {
        buttonBack = findViewById(R.id.activity_configuracoes_button_back);
        buttonSync = findViewById(R.id.activity_configuracoes_button_sync);
        buttonLogin = findViewById(R.id.activity_configuracoes_button_login);
    }

    private void carregaInfoLogin() {
        if (qManager.countSize(lInfoDAO) > 0) {
            loginList = qManager.getAll(lInfoDAO);
            loginInfo = loginList.get(0);
            email = loginInfo.getUser();
            password = loginInfo.getPassword();
        } else {
            loginList = new ArrayList<>();
            loginInfo = new LoginInfo("", "");
        }
        campoEmail.setText(email);
        campoSenha.setText(password);
    }

    private void inicializaCamposLogin() {
        campoEmail = findViewById(R.id.activity_configuracoes_campo_email);
        campoSenha = findViewById(R.id.activity_configuracoes_campo_senha);
    }
}
