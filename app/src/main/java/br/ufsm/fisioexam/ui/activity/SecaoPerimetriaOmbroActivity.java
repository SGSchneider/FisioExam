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

public class SecaoPerimetriaOmbroActivity extends AppCompatActivity {
    //Usar Parâmetros MAGEE (2010) por ser mais recente.

    private EditText campoSup5Esq;
    private EditText campoSup5Dir;
    private EditText campoSup10Esq;
    private EditText campoSup10Dir;
    private EditText campoSup15Esq;
    private EditText campoSup15Dir;
    private EditText campoInf5Esq;
    private EditText campoInf5Dir;
    private EditText campoInf10Esq;
    private EditText campoInf10Dir;
    private EditText campoInf15Esq;
    private EditText campoInf15Dir;

    private ImageButton botaoHelpPerimetria;

    private Button proximo;
    private Button salvarESair;
    private Ombro ombro;
    private OmbroDAO ombroDao;
    private Secoes secoes;
    private SecoesDAO secoesDao;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secao_perimetria_ombro);
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
        proximo = findViewById(R.id.activity_secao_perimetria_ombro_button_proximo);
        salvarESair = findViewById(R.id.activity_secao_perimetria_ombro_button_salvar_e_sair);

        botaoHelpPerimetria = findViewById(R.id.activity_secao_perimetria_ombro_button_help);

        configuraListenersDeClique();
    }

    private void configuraListenersDeClique() {
        proximo.setOnClickListener(v -> proximoForm());

        salvarESair.setOnClickListener(v -> finalizaForm());

        botaoHelpPerimetria.setOnClickListener(v -> vaiParaAjuda());
    }

    private void vaiParaAjuda() {
        Intent intent = new Intent(this, AjudaPerimetriaOmbroActivity.class);
        startActivity(intent);
    }


    private void finalizaForm() {
        salva();
        finish();
    }

    private void salva() {
        secoes.setPerimetria(true);
        secoesDao.edita(secoes);
        ombro.setPerimetriaInfDir5(campoInf5Dir.getText().toString());
        ombro.setPerimetriaInfDir10(campoInf10Dir.getText().toString());
        ombro.setPerimetriaInfDir15(campoInf15Dir.getText().toString());
        ombro.setPerimetriaInfEsq5(campoInf5Esq.getText().toString());
        ombro.setPerimetriaInfEsq10(campoInf10Esq.getText().toString());
        ombro.setPerimetriaInfEsq15(campoInf15Esq.getText().toString());
        ombro.setPerimetriaSupDir5(campoSup5Dir.getText().toString());
        ombro.setPerimetriaSupDir10(campoSup10Dir.getText().toString());
        ombro.setPerimetriaSupDir15(campoSup15Dir.getText().toString());
        ombro.setPerimetriaSupEsq5(campoSup5Esq.getText().toString());
        ombro.setPerimetriaSupEsq10(campoSup10Esq.getText().toString());
        ombro.setPerimetriaSupEsq15(campoSup15Esq.getText().toString());

        ombroDao.edita(ombro);
    }

    private void proximoForm() {
        salva();
        Intent vaiParaFormularioSecaoActivity = new Intent(this, IntermediarioSecoesEspecificasActivity.class);
        vaiParaFormularioSecaoActivity.putExtra(CHAVE_EXAME, ombro.getExame());
        vaiParaFormularioSecaoActivity.putExtra(CHAVE_SECAO, 3);
        startActivity(vaiParaFormularioSecaoActivity);
        finish();
    }


    private void carregaExame() {
        Intent dados = getIntent();

        if (dados.hasExtra(CHAVE_EXAME)) {
            ombro = ombroDao.getOmbro((String) dados.getSerializableExtra(CHAVE_EXAME));
            secoes = secoesDao.getSecao(ombro.getExame());
        }
    }


    private void inicializaFormulario() {
        campoSup5Esq = findViewById(R.id.activity_secao_perimetria_ombro_sup_5_esq);
        campoSup5Dir = findViewById(R.id.activity_secao_perimetria_ombro_sup_5_dir);
        campoSup10Esq = findViewById(R.id.activity_secao_perimetria_ombro_sup_10_esq);
        campoSup10Dir = findViewById(R.id.activity_secao_perimetria_ombro_sup_10_dir);
        campoSup15Esq = findViewById(R.id.activity_secao_perimetria_ombro_sup_15_esq);
        campoSup15Dir = findViewById(R.id.activity_secao_perimetria_ombro_sup_15_dir);
        campoInf5Esq = findViewById(R.id.activity_secao_perimetria_ombro_inf_5_esq);
        campoInf5Dir = findViewById(R.id.activity_secao_perimetria_ombro_inf_5_dir);
        campoInf10Esq = findViewById(R.id.activity_secao_perimetria_ombro_inf_10_esq);
        campoInf10Dir = findViewById(R.id.activity_secao_perimetria_ombro_inf_10_dir);
        campoInf15Esq = findViewById(R.id.activity_secao_perimetria_ombro_inf_15_esq);
        campoInf15Dir = findViewById(R.id.activity_secao_perimetria_ombro_inf_15_dir);

        if (secoes.isPerimetria()) {
            preencheCampos();
        }
    }

    private void preencheCampos() {

        campoSup5Esq.setText(ombro.getPerimetriaSupEsq5());
        campoSup5Dir.setText(ombro.getPerimetriaSupDir5());
        campoSup10Esq.setText(ombro.getPerimetriaSupEsq10());
        campoSup10Dir.setText(ombro.getPerimetriaSupDir10());
        campoSup15Esq.setText(ombro.getPerimetriaSupEsq15());
        campoSup15Dir.setText(ombro.getPerimetriaSupDir15());
        campoInf5Esq.setText(ombro.getPerimetriaInfEsq5());
        campoInf5Dir.setText(ombro.getPerimetriaInfDir5());
        campoInf10Esq.setText(ombro.getPerimetriaInfEsq10());
        campoInf10Dir.setText(ombro.getPerimetriaInfDir10());
        campoInf15Esq.setText(ombro.getPerimetriaInfEsq15());
        campoInf15Dir.setText(ombro.getPerimetriaInfDir15());

    }
}

