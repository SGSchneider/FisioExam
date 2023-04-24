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

public class SecaoSinaisVitaisActivity extends AppCompatActivity {

    private Button proximo;
    private Button salvarESair;
    private Exame exame;
    private ExameDAO exameDao;
    private Secoes secoes;
    private SecoesDAO secoesDao;
    private EditText campoPA;
    private EditText campoFC;
    private EditText campoFR;
    private EditText campoTempCorporal;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secao_sinais_vitais);
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
        proximo = findViewById(R.id.activity_secao_sinais_vitais_button_proximo);
        salvarESair = findViewById(R.id.activity_secao_sinais_vitais_button_salvar_e_sair);
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
        secoes.setSinaisVitais(true);
        secoesDao.edita(secoes);
        exame.setPA(campoPA.getText().toString());
        exame.setFC(campoFC.getText().toString());
        exame.setFR(campoFR.getText().toString());
        exame.setTempCorporal(campoTempCorporal.getText().toString());
        exameDao.edita(exame);
    }

    private void proximoForm() {
        salva();
        Intent vaiParaFormularioSecaoActivity = new Intent(this, SecaoInspecaoActivity.class);
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
        campoPA = findViewById(R.id.activity_secao_sinais_vitais_pa);
        campoFC = findViewById(R.id.activity_secao_sinais_vitais_fc);
        campoFR = findViewById(R.id.activity_secao_sinais_vitais_fr);
        campoTempCorporal = findViewById(R.id.activity_secao_sinais_vitais_temp);
        if (secoes.isSinaisVitais()) {
            preencheCampos();
        }
    }

    private void preencheCampos() {
        campoPA.setText(exame.getPA());
        campoFC.setText(exame.getFC());
        campoFR.setText(exame.getFR());
        campoTempCorporal.setText(exame.getTempCorporal());
    }
}
