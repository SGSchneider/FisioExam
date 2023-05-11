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

public class SecaoHistoriaOcupacionalActivity extends AppCompatActivity {
    private Button proximo;
    private Button salvarESair;
    private Exame exame;
    private ExameDAO exameDao;
    private Secoes secoes;
    private SecoesDAO secoesDao;
    private EditText campoLocalAtual;
    private EditText campoTempoAtual;
    private EditText campoLocalAnterior;
    private EditText campoTempoAnterior;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secao_historia_ocupacional);
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
        proximo = findViewById(R.id.activity_secao_historia_ocupacional_button_proximo);
        salvarESair = findViewById(R.id.activity_secao_historia_ocupacional_button_salvar_e_sair);
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
        secoes.setHistoriaOcupacional(true);
        secoesDao.update(secoes);

        //Salva as alterações na variável
        exame.setOcupacaoAtual(campoLocalAtual.getText().toString());
        exame.setTempoTrabalhoAtual(campoTempoAtual.getText().toString());
        exame.setOcupacaoAnterior(campoLocalAnterior.getText().toString());
        exame.setTempoTrabalhoAnterior(campoTempoAnterior.getText().toString());

        //Salva no Banco de Dados
        exameDao.update(exame);
    }

    private void proximoForm() {
        salva();
        Intent vaiParaFormularioSecaoActivity = new Intent(this, SecaoHabitosVidaActivity.class);
        vaiParaFormularioSecaoActivity.putExtra(CHAVE_EXAME, exame.getId());
        startActivity(vaiParaFormularioSecaoActivity);
        finish();
    }


    private void carregaExame() {
        Intent dados = getIntent();

        if (dados.hasExtra(CHAVE_EXAME)) {
            exame = exameDao.getOne((String) dados.getSerializableExtra(CHAVE_EXAME));
            secoes = secoesDao.getOne(exame.getId());
        }
    }


    private void inicializaFormulario() {
        campoLocalAtual = findViewById(R.id.activity_secao_historia_ocupacional_local_trabalho_atual);
        campoTempoAtual = findViewById(R.id.activity_secao_historia_ocupacional_tempo_trabalho_atual);
        campoLocalAnterior = findViewById(R.id.activity_secao_historia_ocupacional_local_trabalho_anterior);
        campoTempoAnterior = findViewById(R.id.activity_secao_historia_ocupacional_tempo_trabalho_anterior);

        if (secoes.isHistoriaOcupacional()) {
            preencheCampos();
        }
    }


    private void preencheCampos() {
        campoLocalAtual.setText(exame.getOcupacaoAtual());
        campoTempoAtual.setText(exame.getTempoTrabalhoAtual());
        campoLocalAnterior.setText(exame.getOcupacaoAnterior());
        campoTempoAnterior.setText(exame.getTempoTrabalhoAnterior());
    }

}
