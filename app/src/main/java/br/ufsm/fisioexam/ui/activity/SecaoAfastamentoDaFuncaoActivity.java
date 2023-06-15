package br.ufsm.fisioexam.ui.activity;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_EXAME;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import br.ufsm.fisioexam.R;
import br.ufsm.fisioexam.database.FisioExamDatabase;
import br.ufsm.fisioexam.database.dao.ExameDAO;
import br.ufsm.fisioexam.database.dao.SecoesDAO;
import br.ufsm.fisioexam.database.thread.QueryManager;
import br.ufsm.fisioexam.model.Exame;
import br.ufsm.fisioexam.model.Secoes;

public class SecaoAfastamentoDaFuncaoActivity extends AppCompatActivity {
    private Button proximo;
    private Button salvarESair;
    private Exame exame;
    private ExameDAO exameDao;
    private QueryManager<Exame> exameQueryManager;
    private Secoes secoes;
    private SecoesDAO secoesDao;
    private QueryManager<Secoes> secaoQueryManager;
    private SwitchCompat campoAfastamentoDaFuncao;
    private EditText campoQualAfastamentoDaFuncao;
    private EditText campoAfastamentoDaFuncaoPorQuantoTempo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secao_afastamento_da_funcao);
        inicializaDaos();
        carregaExame();
        inicializaFormulario();
        inicializaBotoes();
        setListenerSwitch();
        EscondeFormulario();
    }

    private void inicializaDaos() {
        FisioExamDatabase database = FisioExamDatabase.getInstance(this);
        exameDao = database.getRoomExameDAO();
        secoesDao = database.getRoomSecoesDAO();
        exameQueryManager = new QueryManager<>();
        secaoQueryManager = new QueryManager<>();
    }

    private void inicializaBotoes() {
        proximo = findViewById(R.id.activity_secao_afastamento_da_funcao_button_proximo);
        salvarESair = findViewById(R.id.activity_secao_afastamento_da_funcao_button_salvar_e_sair);
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
        secoes.setAfastamentoDaFuncao(true);
        secaoQueryManager.update(secoes, secoesDao);

        //Salva as alterações na variável
        exame.setAfastamentoFuncao(campoAfastamentoDaFuncao.isChecked());
        exame.setQualAfastamentoFuncao(campoQualAfastamentoDaFuncao.getText().toString());
        exame.setTempoAfastamento(campoAfastamentoDaFuncaoPorQuantoTempo.getText().toString());

        //Salva no Banco de Dados
        exameQueryManager.update(exame, exameDao);
    }

    private void proximoForm() {
        salva();
        Intent vaiParaFormularioSecaoActivity = new Intent(this, SecaoHistoriaPregressaActivity.class);
        vaiParaFormularioSecaoActivity.putExtra(CHAVE_EXAME, exame.getId());
        startActivity(vaiParaFormularioSecaoActivity);
        finish();
    }


    private void carregaExame() {
        Intent dados = getIntent();

        if (dados.hasExtra(CHAVE_EXAME)) {
            exame = exameQueryManager.getOne((String) dados.getSerializableExtra(CHAVE_EXAME), exameDao);
            secoes = secaoQueryManager.getOne(exame.getId(), secoesDao);
        }
    }


    private void inicializaFormulario() {
        campoAfastamentoDaFuncao = findViewById(R.id.activity_secao_afastamento_da_funcao_switch);
        campoQualAfastamentoDaFuncao = findViewById(R.id.activity_secao_afastamento_da_funcao_qual_problema_afastou_da_funcao);
        campoAfastamentoDaFuncaoPorQuantoTempo = findViewById(R.id.activity_secao_afastamento_da_funcao_tempo_afastamento_da_funcao);

        if (secoes.isAfastamentoDaFuncao()) {
            preencheCampos();
        }
    }


    private void setListenerSwitch() {
        campoAfastamentoDaFuncao.setOnCheckedChangeListener((buttonView, isChecked) -> EscondeFormulario());
    }


    private void EscondeFormulario() {
        int visibilidade;
        if (campoAfastamentoDaFuncao.isChecked()) {
            visibilidade = VISIBLE;
        } else {
            visibilidade = GONE;
        }
        campoQualAfastamentoDaFuncao.setVisibility(visibilidade);
        campoAfastamentoDaFuncaoPorQuantoTempo.setVisibility(visibilidade);
    }

    private void preencheCampos() {
        campoAfastamentoDaFuncao.setChecked(exame.isAfastamentoFuncao());
        campoQualAfastamentoDaFuncao.setText(exame.getQualAfastamentoFuncao());
        campoAfastamentoDaFuncaoPorQuantoTempo.setText(exame.getTempoAfastamento());
    }
}
