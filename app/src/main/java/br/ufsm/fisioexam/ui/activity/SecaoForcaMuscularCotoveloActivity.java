package br.ufsm.fisioexam.ui.activity;

import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_EXAME;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_SECAO;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.ufsm.fisioexam.R;
import br.ufsm.fisioexam.database.FisioExamDatabase;
import br.ufsm.fisioexam.database.dao.CotoveloDAO;
import br.ufsm.fisioexam.database.dao.SecoesDAO;
import br.ufsm.fisioexam.model.Cotovelo;
import br.ufsm.fisioexam.model.Secoes;

public class SecaoForcaMuscularCotoveloActivity extends AppCompatActivity {

    private EditText campoBicepsBraquialDir;
    private EditText campoBicepsBraquialEsq;
    private EditText campoBraquialDir;
    private EditText campoBraquialEsq;
    private EditText campoBraquirradialDir;
    private EditText campoBraquirradialEsq;
    private EditText campoTricepsBraquialDir;
    private EditText campoTricepsBraquialEsq;
    private EditText campoSupinadorDir;
    private EditText campoSupinadorEsq;
    private EditText campoPronadorDir;
    private EditText campoPronadorEsq;



    private Button proximo;
    private Button salvarESair;
    private Cotovelo cotovelo;
    private CotoveloDAO cotoveloDao;
    private Secoes secoes;
    private SecoesDAO secoesDao;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secao_forca_muscular_cotovelo);
        inicializaDaos();
        carregaExame();
        inicializaFormulario();
        inicializaBotoes();
    }


    private void inicializaDaos() {
        FisioExamDatabase database = FisioExamDatabase.getInstance(this);
        cotoveloDao = database.getRoomCotoveloDAO();
        secoesDao = database.getRoomSecoesDAO();
    }

    private void inicializaBotoes() {
        proximo = findViewById(R.id.activity_secao_forca_muscular_cotovelo_button_proximo);
        salvarESair = findViewById(R.id.activity_secao_forca_muscular_cotovelo_button_salvar_e_sair);
        configuraListenersDeClique();
    }

    private void configuraListenersDeClique() {
        proximo.setOnClickListener(v -> proximoForm());

        salvarESair.setOnClickListener(v -> finalizaForm());
    }


    private void finalizaForm() {
        salva();
        finish();
    }

    private void salva() {
        secoes.setForcaMuscular1(true);
        secoesDao.edita(secoes);

        cotovelo.setBicepsBraquialDir(campoBicepsBraquialDir.getText().toString());
        cotovelo.setBicepsBraquialEsq(campoBicepsBraquialEsq.getText().toString());
        cotovelo.setBraquialDir(campoBraquialDir.getText().toString());
        cotovelo.setBraquialEsq(campoBraquialEsq.getText().toString());
        cotovelo.setBraquirradialDir(campoBraquialDir.getText().toString());
        cotovelo.setBraquirradialEsq(campoBraquirradialEsq.getText().toString());
        cotovelo.setTricepsBraquialEAnconeoDir(campoTricepsBraquialDir.getText().toString());
        cotovelo.setTricepsBraquialEAnconeoEsq(campoTricepsBraquialEsq.getText().toString());
        cotovelo.setSupinadorDir(campoSupinadorDir.getText().toString());
        cotovelo.setSupinadorEsq(campoSupinadorEsq.getText().toString());
        cotovelo.setPronadorQuadradoERedondoDir(campoPronadorDir.getText().toString());
        cotovelo.setPronadorQuadradoERedondoEsq(campoPronadorEsq.getText().toString());

        cotoveloDao.edita(cotovelo);
    }

    private void proximoForm() {
        salva();
        Intent vaiParaFormularioSecaoActivity = new Intent(this, IntermediarioSecoesEspecificasActivity.class);
        vaiParaFormularioSecaoActivity.putExtra(CHAVE_EXAME, cotovelo.getExame());
        vaiParaFormularioSecaoActivity.putExtra(CHAVE_SECAO, 4);
        startActivity(vaiParaFormularioSecaoActivity);
        finish();
    }


    private void carregaExame() {
        Intent dados = getIntent();

        if (dados.hasExtra(CHAVE_EXAME)) {
            cotovelo = cotoveloDao.getCotovelo((String) dados.getSerializableExtra(CHAVE_EXAME));
            secoes = secoesDao.getSecao(cotovelo.getExame());
        }
    }


    private void inicializaFormulario() {
        campoBicepsBraquialDir = findViewById(R.id.activity_secao_forca_muscular_cotovelo_biceps_braquial_cotovelo_dir);
        campoBicepsBraquialEsq = findViewById(R.id.activity_secao_forca_muscular_cotovelo_biceps_braquial_cotovelo_esq);
        campoBraquialDir = findViewById(R.id.activity_secao_forca_muscular_cotovelo_braquial_cotovelo_dir);
        campoBraquialEsq = findViewById(R.id.activity_secao_forca_muscular_cotovelo_braquial_cotovelo_esq);
        campoBraquirradialDir = findViewById(R.id.activity_secao_forca_muscular_cotovelo_braquirradial_cotovelo_dir);
        campoBraquirradialEsq = findViewById(R.id.activity_secao_forca_muscular_cotovelo_braquirradial_cotovelo_esq);
        campoTricepsBraquialDir = findViewById(R.id.activity_secao_forca_muscular_cotovelo_triceps_braquial_cotovelo_dir);
        campoTricepsBraquialEsq = findViewById(R.id.activity_secao_forca_muscular_cotovelo_triceps_braquial_cotovelo_esq);
        campoSupinadorDir = findViewById(R.id.activity_secao_forca_muscular_cotovelo_supinador_cotovelo_dir);
        campoSupinadorEsq = findViewById(R.id.activity_secao_forca_muscular_cotovelo_supinador_cotovelo_esq);
        campoPronadorDir = findViewById(R.id.activity_secao_forca_muscular_cotovelo_pronador_cotovelo_dir);
        campoPronadorEsq = findViewById(R.id.activity_secao_forca_muscular_cotovelo_pronador_cotovelo_esq);



        if (secoes.isForcaMuscular1()) {
            preencheCampos();
        }
    }

    private void preencheCampos() {
        campoBicepsBraquialDir.setText(cotovelo.getBicepsBraquialDir());
        campoBicepsBraquialEsq.setText(cotovelo.getBicepsBraquialEsq());
        campoBraquialDir.setText(cotovelo.getBraquialDir());
        campoBraquialEsq.setText(cotovelo.getBraquialEsq());
        campoBraquirradialDir.setText(cotovelo.getBraquirradialDir());
        campoBraquirradialEsq.setText(cotovelo.getBraquirradialEsq());
        campoTricepsBraquialDir.setText(cotovelo.getTricepsBraquialEAnconeoDir());
        campoTricepsBraquialEsq.setText(cotovelo.getTricepsBraquialEAnconeoEsq());
        campoSupinadorDir.setText(cotovelo.getSupinadorDir());
        campoSupinadorEsq.setText(cotovelo.getSupinadorEsq());
        campoPronadorDir.setText(cotovelo.getPronadorQuadradoERedondoDir());
        campoPronadorEsq.setText(cotovelo.getPronadorQuadradoERedondoEsq());

    }
}

