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
import br.ufsm.fisioexam.model.Exame;
import br.ufsm.fisioexam.model.Secoes;

public class SecaoTratamentoAnteriorActivity extends AppCompatActivity {

    private Button proximo;
    private Button salvarESair;
    private Exame exame;
    private ExameDAO exameDao;
    private Secoes secoes;
    private SecoesDAO secoesDao;
    private SwitchCompat campoTratamentoAnterior;
    private EditText campoQualTratamento;
    private EditText campoTratamentoHaQuantoTempo;
    private EditText campoTratamentoPorQuantoTempo;
    private SwitchCompat campoHouveMelhora;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secao_tratamento_anterior);
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
    }

    private void inicializaBotoes() {
        proximo = findViewById(R.id.activity_secao_tratamento_anterior_button_proximo);
        salvarESair = findViewById(R.id.activity_secao_tratamento_anterior_button_salvar_e_sair);
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
        secoes.setTratamentoAnterior(true);
        secoesDao.edita(secoes);

        //Salva as alterações na variável
        exame.setTratamentoPassado(campoTratamentoAnterior.isChecked());
        exame.setQualTratamentoPassado(campoQualTratamento.getText().toString());
        exame.setTempoTratamentoPassado(campoTratamentoHaQuantoTempo.getText().toString());
        exame.setDuracaoTratamentoPassado(campoTratamentoPorQuantoTempo.getText().toString());
        exame.setTratamentoPassadoMelhora(campoHouveMelhora.isChecked());

        //Salva no Banco de Dados
        exameDao.edita(exame);
    }

    private void proximoForm() {
        salva();
        Intent vaiParaFormularioSecaoActivity = new Intent(this, SecaoAfastamentoDaFuncaoActivity.class);
        vaiParaFormularioSecaoActivity.putExtra(CHAVE_EXAME, exame.getId());
        startActivity(vaiParaFormularioSecaoActivity);
        finish();
    }


    private void carregaExame() {
        Intent dados = getIntent();

        if (dados.hasExtra(CHAVE_EXAME)) {
            exame = exameDao.getExame((String) dados.getSerializableExtra(CHAVE_EXAME));
            secoes = secoesDao.getSecao(exame.getId());
        }
    }


    private void inicializaFormulario() {
        campoTratamentoAnterior = findViewById(R.id.activity_secao_tratamento_anterior_switch_fez);
        campoQualTratamento = findViewById(R.id.activity_secao_tratamento_anterior_qual_tratamento);
        campoTratamentoHaQuantoTempo = findViewById(R.id.activity_secao_tratamento_anterior_ha_quanto_tempo_fez_tratamento);
        campoTratamentoPorQuantoTempo = findViewById(R.id.activity_secao_tratamento_anterior_por_quanto_tempo_fez_tratamento);
        campoHouveMelhora = findViewById(R.id.activity_secao_tratamento_anterior_switch_houve_melhora);

        if (secoes.isDor()) {
            preencheCampos();
        }
    }


    private void setListenerSwitch() {
        campoTratamentoAnterior.setOnCheckedChangeListener((buttonView, isChecked) -> EscondeFormulario());
    }


    private void EscondeFormulario() {
        int visibilidade;
        if (campoTratamentoAnterior.isChecked()) {
            visibilidade = VISIBLE;

        } else {
            visibilidade = GONE;
        }
        campoQualTratamento.setVisibility(visibilidade);
        campoTratamentoPorQuantoTempo.setVisibility(visibilidade);
        campoTratamentoHaQuantoTempo.setVisibility(visibilidade);
        campoHouveMelhora.setVisibility(visibilidade);
    }

    private void preencheCampos() {
        if(secoes.isTratamentoAnterior()){
            campoTratamentoAnterior.setChecked(exame.isTratamentoPassado());
            campoQualTratamento.setText(exame.getQualTratamentoPassado());
            campoTratamentoHaQuantoTempo.setText(exame.getTempoTratamentoPassado());
            campoTratamentoPorQuantoTempo.setText(exame.getDuracaoTratamentoPassado());
            campoHouveMelhora.setChecked(exame.isTratamentoPassadoMelhora());
        }
    }
}
