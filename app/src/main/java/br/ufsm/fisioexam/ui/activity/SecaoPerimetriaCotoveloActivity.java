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
import br.ufsm.fisioexam.database.thread.QueryManager;
import br.ufsm.fisioexam.model.Cotovelo;
import br.ufsm.fisioexam.model.Secoes;

public class SecaoPerimetriaCotoveloActivity extends AppCompatActivity {
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

    private Button proximo;
    private Button salvarESair;
    private Cotovelo cotovelo;
    private CotoveloDAO cotoveloDao;
    private QueryManager<Cotovelo> cotoveloQueryManager;
    private Secoes secoes;
    private SecoesDAO secoesDao;
    private QueryManager<Secoes> secoesQueryManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secao_perimetria_cotovelo);
        inicializaDaos();
        carregaExame();
        inicializaFormulario();
        inicializaBotoes();
    }


    private void inicializaDaos() {
        FisioExamDatabase database = FisioExamDatabase.getInstance(this);
        cotoveloDao = database.getRoomCotoveloDAO();
        secoesDao = database.getRoomSecoesDAO();
        cotoveloQueryManager = new QueryManager<>();
        secoesQueryManager = new QueryManager<>();
    }

    private void inicializaBotoes() {
        proximo = findViewById(R.id.activity_secao_perimetria_cotovelo_button_proximo);
        salvarESair = findViewById(R.id.activity_secao_perimetria_cotovelo_button_salvar_e_sair);
        configuraListenersDeClique();
    }

    private void vaiParaAjuda(Class<?> classe) {
        Intent vaiParaAjudaActivity = new Intent(this, classe);
        startActivity(vaiParaAjudaActivity);
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
        secoes.setPerimetria(true);
        secoesQueryManager.update(secoes, secoesDao);
        cotovelo.setPerimetriaInfDir5(campoInf5Dir.getText().toString());
        cotovelo.setPerimetriaInfDir10(campoInf10Dir.getText().toString());
        cotovelo.setPerimetriaInfDir15(campoInf15Dir.getText().toString());
        cotovelo.setPerimetriaInfEsq5(campoInf5Esq.getText().toString());
        cotovelo.setPerimetriaInfEsq10(campoInf10Esq.getText().toString());
        cotovelo.setPerimetriaInfEsq15(campoInf15Esq.getText().toString());
        cotovelo.setPerimetriaSupDir5(campoSup5Dir.getText().toString());
        cotovelo.setPerimetriaSupDir10(campoSup10Dir.getText().toString());
        cotovelo.setPerimetriaSupDir15(campoSup15Dir.getText().toString());
        cotovelo.setPerimetriaSupEsq5(campoSup5Esq.getText().toString());
        cotovelo.setPerimetriaSupEsq10(campoSup10Esq.getText().toString());
        cotovelo.setPerimetriaSupEsq15(campoSup15Esq.getText().toString());

        cotoveloQueryManager.update(cotovelo, cotoveloDao);
    }

    private void proximoForm() {
        salva();
        Intent vaiParaFormularioSecaoActivity = new Intent(this, IntermediarioSecoesEspecificasActivity.class);
        vaiParaFormularioSecaoActivity.putExtra(CHAVE_EXAME, cotovelo.getExame());
        vaiParaFormularioSecaoActivity.putExtra(CHAVE_SECAO, 3);
        startActivity(vaiParaFormularioSecaoActivity);
        finish();
    }


    private void carregaExame() {
        Intent dados = getIntent();

        if (dados.hasExtra(CHAVE_EXAME)) {
            cotovelo = cotoveloQueryManager.getOne((String) dados.getSerializableExtra(CHAVE_EXAME), cotoveloDao);
            secoes = secoesQueryManager.getOne(cotovelo.getExame(), secoesDao);
        }
    }


    private void inicializaFormulario() {
        campoSup5Esq = findViewById(R.id.activity_secao_perimetria_cotovelo_sup_5_esq);
        campoSup5Dir = findViewById(R.id.activity_secao_perimetria_cotovelo_sup_5_dir);
        campoSup10Esq = findViewById(R.id.activity_secao_perimetria_cotovelo_sup_10_esq);
        campoSup10Dir = findViewById(R.id.activity_secao_perimetria_cotovelo_sup_10_dir);
        campoSup15Esq = findViewById(R.id.activity_secao_perimetria_cotovelo_sup_15_esq);
        campoSup15Dir = findViewById(R.id.activity_secao_perimetria_cotovelo_sup_15_dir);
        campoInf5Esq = findViewById(R.id.activity_secao_perimetria_cotovelo_inf_5_esq);
        campoInf5Dir = findViewById(R.id.activity_secao_perimetria_cotovelo_inf_5_dir);
        campoInf10Esq = findViewById(R.id.activity_secao_perimetria_cotovelo_inf_10_esq);
        campoInf10Dir = findViewById(R.id.activity_secao_perimetria_cotovelo_inf_10_dir);
        campoInf15Esq = findViewById(R.id.activity_secao_perimetria_cotovelo_inf_15_esq);
        campoInf15Dir = findViewById(R.id.activity_secao_perimetria_cotovelo_inf_15_dir);

        if (secoes.isPerimetria()) {
            preencheCampos();
        }
    }

    private void preencheCampos() {

        campoSup5Esq.setText(cotovelo.getPerimetriaSupEsq5());
        campoSup5Dir.setText(cotovelo.getPerimetriaSupDir5());
        campoSup10Esq.setText(cotovelo.getPerimetriaSupEsq10());
        campoSup10Dir.setText(cotovelo.getPerimetriaSupDir10());
        campoSup15Esq.setText(cotovelo.getPerimetriaSupEsq15());
        campoSup15Dir.setText(cotovelo.getPerimetriaSupDir15());
        campoInf5Esq.setText(cotovelo.getPerimetriaInfEsq5());
        campoInf5Dir.setText(cotovelo.getPerimetriaInfDir5());
        campoInf10Esq.setText(cotovelo.getPerimetriaInfEsq10());
        campoInf10Dir.setText(cotovelo.getPerimetriaInfDir10());
        campoInf15Esq.setText(cotovelo.getPerimetriaInfEsq15());
        campoInf15Dir.setText(cotovelo.getPerimetriaInfDir15());

    }
}

