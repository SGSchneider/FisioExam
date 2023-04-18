package br.ufsm.fisioexam.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

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

import br.ufsm.fisioexam.R;
import br.ufsm.fisioexam.database.FirebaseHelper;
import br.ufsm.fisioexam.database.FisioExamDatabase;
import br.ufsm.fisioexam.database.sync.SyncBD;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);
        setTitle(TITULO_APPBAR);
        checkPermissoes();
        inicializaFirebase();
        inicializaBotoes();
        addListenerBotoes();
    }

    private void checkPermissoes() {
        Log.i("PERMISSAO", "Verificando Permissão: Internet");
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            // Permissão não concedida. Solicite permissão em tempo de execução.
            Log.w("PERMISSAO", "Solicitando Permissão: Internet");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, INTERNET_REQUEST_CODE);
        }else{
            Log.i("PERMISSAO", "Possui Permissão: Internet");
        }
    }

    private void inicializaFirebase() {
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        database = FisioExamDatabase.getInstance(this);
        firebase = FirebaseHelper.getInstance();
        sincronizador = new SyncBD(database, firebase.getDatabaseReference());

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
        if (email != null && password != null){
            AuthCredential credential = EmailAuthProvider.getCredential(email,password);
            mAuth.signInWithCredential(credential)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Login bem sucedido
                                Log.i("LOGIN", "Login Bem Sucedido");
                                FirebaseUser user = mAuth.getCurrentUser();
                                // Faça algo com o usuário logado
                            } else {
                                // Login falhou
                                Log.e("LOGIN", "Login Falhou");
                                // Exiba uma mensagem de erro ao usuário
                            }
                        }
                    });
        }


    }

    private void syncDB() {
        sincronizador.syncPacientes();
        sincronizador.syncExames();
        sincronizador.syncOmbros();
        sincronizador.syncNow(firebase.getDatabaseReference());
    }

    private void inicializaBotoes() {
        buttonBack = findViewById(R.id.activity_configuracoes_button_back);
        buttonSync = findViewById(R.id.activity_configuracoes_button_sync);
        buttonLogin = findViewById(R.id.activity_configuracoes_button_login);
        campoEmail = findViewById(R.id.activity_configuracoes_campo_email);
        campoSenha = findViewById(R.id.activity_configuracoes_campo_senha);
    }


}
