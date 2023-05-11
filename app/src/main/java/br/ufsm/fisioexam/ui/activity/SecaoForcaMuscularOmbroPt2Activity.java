package br.ufsm.fisioexam.ui.activity;

import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_EXAME;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_SECAO;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.ufsm.fisioexam.R;
import br.ufsm.fisioexam.database.FisioExamDatabase;
import br.ufsm.fisioexam.database.dao.OmbroDAO;
import br.ufsm.fisioexam.database.dao.SecoesDAO;
import br.ufsm.fisioexam.model.Ombro;
import br.ufsm.fisioexam.model.Secoes;

public class SecaoForcaMuscularOmbroPt2Activity extends AppCompatActivity {


    private EditText campoRedondoMaiorDir;
    private EditText campoRedondoMaiorEsq;
    private EditText campoSupraespinhalDir;
    private EditText campoSupraespinhalEsq;
    private EditText campoDeltoideMedioDir;
    private EditText campoDeltoideMedioEsq;
    private EditText campoDeltoidePosteriorDir;
    private EditText campoDeltoidePosteriorEsq;
    private EditText campoPeitoralMaiorDir;
    private EditText campoPeitoralMaiorEsq;
    private EditText campoSubescapularDir;
    private EditText campoSubescapularEsq;
    private EditText campoInfraespinhalDir;
    private EditText campoInfraespinhalEsq;

    private ImageButton buttonHelpRedondoMaior;
    private ImageButton buttonHelpSupraespinhal;
    private ImageButton buttonHelpDeltoideMedio;
    private ImageButton buttonHelpDeltoidePosterior;
    private ImageButton buttonHelpPeitoralMaior;
    private ImageButton buttonHelpSubescapular;
    private ImageButton buttonHelpInfraespinhal;



    private Button proximo;
    private Button salvarESair;
    private Ombro ombro;
    private OmbroDAO ombroDao;
    private Secoes secoes;
    private SecoesDAO secoesDao;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secao_forca_muscular_ombro_pt2);
        inicializaDaos();
        carregaExame();
        inicializaFormulario();
        inicializaBotoes();
    }


    private void inicializaDaos() {
        FisioExamDatabase database = FisioExamDatabase.getInstance(this);
        ombroDao = database.getRoomOmbroDAO();
        secoesDao = database.getRoomSecoesDAO();
    }

    private void inicializaBotoes() {
        proximo = findViewById(R.id.activity_secao_forca_muscular_ombro_button_proximo);
        salvarESair = findViewById(R.id.activity_secao_forca_muscular_ombro_button_salvar_e_sair);

        buttonHelpRedondoMaior = findViewById(R.id.activity_secao_forca_muscular_ombro_button_help_redondo_maior);
        buttonHelpSupraespinhal = findViewById(R.id.activity_secao_forca_muscular_ombro_button_help_supraespinhal);
        buttonHelpDeltoideMedio = findViewById(R.id.activity_secao_forca_muscular_ombro_button_help_deltoide_medio);
        buttonHelpDeltoidePosterior = findViewById(R.id.activity_secao_forca_muscular_ombro_button_help_deltoide_posterior);
        buttonHelpPeitoralMaior = findViewById(R.id.activity_secao_forca_muscular_ombro_button_help_peitoral_maior);
        buttonHelpSubescapular = findViewById(R.id.activity_secao_forca_muscular_ombro_button_help_subescapular);
        buttonHelpInfraespinhal = findViewById(R.id.activity_secao_forca_muscular_ombro_button_help_infraespinhal);

        configuraListenersDeClique();
    }

    private void configuraListenersDeClique() {
        proximo.setOnClickListener(v -> proximoForm());

        salvarESair.setOnClickListener(v -> finalizaForm());



        buttonHelpRedondoMaior.setOnClickListener(v -> vaiParaAjuda(AjudaForcaMuscularOmbroRedondoMaiorActivity.class));
        buttonHelpSupraespinhal.setOnClickListener(v -> vaiParaAjuda(AjudaForcaMuscularOmbroSupraespinhalActivity.class));
        buttonHelpDeltoideMedio.setOnClickListener(v -> vaiParaAjuda(AjudaForcaMuscularOmbroDeltoideMedioActivity.class));
        buttonHelpDeltoidePosterior .setOnClickListener(v -> vaiParaAjuda(AjudaForcaMuscularOmbroDeltoidePosteriorActivity.class));
        buttonHelpPeitoralMaior.setOnClickListener(v -> vaiParaAjuda(AjudaForcaMuscularOmbroPeitoralMaiorActivity.class));
        buttonHelpSubescapular.setOnClickListener(v -> vaiParaAjuda(AjudaForcaMuscularOmbroSubescapularActivity.class));
        buttonHelpInfraespinhal.setOnClickListener(v -> vaiParaAjuda(AjudaForcaMuscularOmbroInfraespinhalActivity.class));

    }

    private void vaiParaAjuda(Class<?> classe) {
        Intent intent = new Intent(this, classe);
        startActivity(intent);
    }


    private void finalizaForm() {
        salva();
        finish();
    }

    private void salva() {
        secoes.setForcaMuscular2(true);
        secoesDao.update(secoes);


        ombro.setRedondoMaiorDir(campoRedondoMaiorDir.getText().toString());
        ombro.setRedondoMaiorEsq(campoRedondoMaiorEsq.getText().toString());
        ombro.setSupraespinhalDir(campoSupraespinhalDir.getText().toString());
        ombro.setSupraespinhalEsq(campoSupraespinhalEsq.getText().toString());
        ombro.setDeltoideMedioDir(campoDeltoideMedioDir.getText().toString());
        ombro.setDeltoideMedioEsq(campoDeltoideMedioEsq.getText().toString());
        ombro.setDeltoidePosteriorDir(campoDeltoidePosteriorDir.getText().toString());
        ombro.setDeltoidePosteriorEsq(campoDeltoidePosteriorEsq.getText().toString());
        ombro.setPeitoralMaiorDir(campoPeitoralMaiorDir.getText().toString());
        ombro.setPeitoralMaiorEsq(campoPeitoralMaiorEsq.getText().toString());
        ombro.setSubescapularDir(campoSubescapularDir.getText().toString());
        ombro.setSubescapularEsq(campoSubescapularEsq.getText().toString());
        ombro.setInfraespinhalRedondoMenorDir(campoInfraespinhalDir.getText().toString());
        ombro.setInfraespinhalRedondoMenorEsq(campoInfraespinhalEsq.getText().toString());



        ombroDao.update(ombro);
    }

    private void proximoForm() {
        salva();
        Intent vaiParaFormularioSecaoActivity = new Intent(this, IntermediarioSecoesEspecificasActivity.class);
        vaiParaFormularioSecaoActivity.putExtra(CHAVE_EXAME, ombro.getExame());
        vaiParaFormularioSecaoActivity.putExtra(CHAVE_SECAO, 4);
        startActivity(vaiParaFormularioSecaoActivity);
        finish();
    }


    private void carregaExame() {
        Intent dados = getIntent();

        if (dados.hasExtra(CHAVE_EXAME)) {
            ombro = ombroDao.getOne((String) dados.getSerializableExtra(CHAVE_EXAME));
            secoes = secoesDao.getOne(ombro.getExame());
        }
    }


    private void inicializaFormulario() {
        campoRedondoMaiorDir = findViewById(R.id.activity_secao_forca_muscular_ombro_redondo_maior_ombro_dir);
        campoRedondoMaiorEsq = findViewById(R.id.activity_secao_forca_muscular_ombro_redondo_maior_ombro_esq);
        campoSupraespinhalDir = findViewById(R.id.activity_secao_forca_muscular_ombro_supraespinhal_ombro_dir);
        campoSupraespinhalEsq = findViewById(R.id.activity_secao_forca_muscular_ombro_supraespinhal_ombro_esq);
        campoDeltoideMedioDir = findViewById(R.id.activity_secao_forca_muscular_ombro_deltoide_medio_ombro_dir);
        campoDeltoideMedioEsq = findViewById(R.id.activity_secao_forca_muscular_ombro_deltoide_medio_ombro_esq);
        campoDeltoidePosteriorDir = findViewById(R.id.activity_secao_forca_muscular_ombro_deltoide_posterior_ombro_dir);
        campoDeltoidePosteriorEsq = findViewById(R.id.activity_secao_forca_muscular_ombro_deltoide_posterior_ombro_esq);
        campoPeitoralMaiorDir = findViewById(R.id.activity_secao_forca_muscular_ombro_peitoral_maior_ombro_dir);
        campoPeitoralMaiorEsq = findViewById(R.id.activity_secao_forca_muscular_ombro_peitoral_maior_ombro_esq);
        campoSubescapularDir = findViewById(R.id.activity_secao_forca_muscular_ombro_subescapular_ombro_dir);
        campoSubescapularEsq = findViewById(R.id.activity_secao_forca_muscular_ombro_subescapular_ombro_esq);
        campoInfraespinhalDir = findViewById(R.id.activity_secao_forca_muscular_ombro_redondo_menor_ombro_dir);
        campoInfraespinhalEsq = findViewById(R.id.activity_secao_forca_muscular_ombro_redondo_menor_ombro_esq);



        if (secoes.isForcaMuscular2()) {
            preencheCampos();
        }
    }

    private void preencheCampos() {
        campoRedondoMaiorDir.setText(ombro.getRedondoMaiorDir());
        campoRedondoMaiorEsq.setText(ombro.getRedondoMaiorEsq());
        campoSupraespinhalDir.setText(ombro.getSupraespinhalDir());
        campoSupraespinhalEsq.setText(ombro.getSupraespinhalEsq());
        campoDeltoideMedioDir.setText(ombro.getDeltoideMedioDir());
        campoDeltoideMedioEsq.setText(ombro.getDeltoideMedioEsq());
        campoDeltoidePosteriorDir.setText(ombro.getDeltoidePosteriorDir());
        campoDeltoidePosteriorEsq.setText(ombro.getDeltoidePosteriorEsq());
        campoPeitoralMaiorDir.setText(ombro.getPeitoralMaiorDir());
        campoPeitoralMaiorEsq.setText(ombro.getPeitoralMaiorEsq());
        campoSubescapularDir.setText(ombro.getSubescapularDir());
        campoSubescapularEsq.setText(ombro.getSubescapularEsq());
        campoInfraespinhalDir.setText(ombro.getInfraespinhalRedondoMenorDir());
        campoInfraespinhalEsq.setText(ombro.getInfraespinhalRedondoMenorEsq());
    }
}

