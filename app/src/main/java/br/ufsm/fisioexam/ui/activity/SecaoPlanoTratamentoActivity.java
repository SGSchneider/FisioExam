package br.ufsm.fisioexam.ui.activity;

import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_EXAME;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.ufsm.fisioexam.R;
import br.ufsm.fisioexam.database.FisioExamDatabase;
import br.ufsm.fisioexam.database.dao.ExameDAO;
import br.ufsm.fisioexam.database.dao.SecoesDAO;
import br.ufsm.fisioexam.model.Exame;
import br.ufsm.fisioexam.model.Secoes;

public class SecaoPlanoTratamentoActivity extends AppCompatActivity {


    private EditText campoPlanoTratamento;
    private Button proximo;
    private Button salvarESair;
    private Exame exame;
    private ExameDAO exameDao;
    private Secoes secoes;
    private SecoesDAO secoesDao;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secao_plano_tratamento);
        inicializaDaos();
        carregaExame();
        inicializaFormulario();
        inicializaBotoes();
    }

    private void inicializaDaos() {
        FisioExamDatabase database = FisioExamDatabase.getInstance(this);
        exameDao = database.getRoomExameDAO();
        secoesDao = database.getRoomSecoesDAO();
    }

    private void inicializaBotoes() {
        proximo = findViewById(R.id.activity_secao_plano_tratamento_button_proximo);
        salvarESair = findViewById(R.id.activity_secao_plano_tratamento_button_salvar_e_sair);
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
        secoes.setPlanoTratamento(true);
        secoesDao.edita(secoes);
        exame.setPlanoTratamento(campoPlanoTratamento.getText().toString());
        exameDao.edita(exame);
    }

    private void proximoForm() {
        salva();

        finish();
    }


    private void carregaExame() {
        Intent dados = getIntent();

        if (dados.hasExtra(CHAVE_EXAME)) {
            exame = exameDao.getExame((int) dados.getSerializableExtra(CHAVE_EXAME));
            secoes = secoesDao.getSecao(exame.getId());
        }
    }


    private void inicializaFormulario() {
        campoPlanoTratamento = findViewById(R.id.activity_secao_plano_tratamento_edit_text);
        if (secoes.isDiagnosticoFisio()) {
            campoPlanoTratamento.setText(exame.getPlanoTratamento());
        }
    }
}
