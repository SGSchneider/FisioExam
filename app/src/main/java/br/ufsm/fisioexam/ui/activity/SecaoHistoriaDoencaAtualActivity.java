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
import br.ufsm.fisioexam.database.thread.QueryManager;
import br.ufsm.fisioexam.model.Exame;
import br.ufsm.fisioexam.model.Secoes;

public class SecaoHistoriaDoencaAtualActivity extends AppCompatActivity {

    private EditText campoHistoriaDoencaAtual;
    private Button proximo;
    private Button salvarESair;
    private Exame exame;
    private ExameDAO exameDao;
    private QueryManager<Exame> exameQueryManager;
    private Secoes secoes;
    private SecoesDAO secoesDao;
    private QueryManager<Secoes> secoesQueryManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secao_historia_doenca_atual);
        inicializaDaos();
        carregaExame();
        inicializaFormulario();
        inicializaBotoes();
    }

    private void inicializaDaos() {
        FisioExamDatabase database = FisioExamDatabase.getInstance(this);
        exameDao = database.getRoomExameDAO();
        secoesDao = database.getRoomSecoesDAO();
        exameQueryManager = new QueryManager<>();
        secoesQueryManager = new QueryManager<>();
    }

    private void inicializaBotoes() {
        proximo = findViewById(R.id.activity_secao_historia_doenca_atual_button_proximo);
        salvarESair = findViewById(R.id.activity_secao_historia_doenca_atual_button_salvar_e_sair);
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
        secoes.setHistoriaDoencaAtual(true);
        secoesQueryManager.update(secoes, secoesDao);
        exame.setHistoriaDoencaAtual(campoHistoriaDoencaAtual.getText().toString());
        exameQueryManager.update(exame, exameDao);
    }

    private void proximoForm() {
        salva();
        Intent vaiParaFormularioSecaoActivity = new Intent(this, SecaoDorActivity.class);
        vaiParaFormularioSecaoActivity.putExtra(CHAVE_EXAME, exame.getId());
        startActivity(vaiParaFormularioSecaoActivity);
        finish();
    }


    private void carregaExame() {
        Intent dados = getIntent();

        if (dados.hasExtra(CHAVE_EXAME)) {
            exame = exameQueryManager.getOne((String) dados.getSerializableExtra(CHAVE_EXAME),exameDao);
            secoes = secoesQueryManager.getOne(exame.getId(), secoesDao);
        }
    }


    private void inicializaFormulario() {
        campoHistoriaDoencaAtual = findViewById(R.id.activity_secao_historia_doenca_atual_edit_text);
        if (secoes.isHistoriaDoencaAtual()) {
            campoHistoriaDoencaAtual.setText(exame.getHistoriaDoencaAtual());
        }
    }
}
