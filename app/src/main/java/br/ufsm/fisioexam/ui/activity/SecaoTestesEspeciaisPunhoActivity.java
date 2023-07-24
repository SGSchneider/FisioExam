package br.ufsm.fisioexam.ui.activity;

import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_EXAME;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_SECAO;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.ufsm.fisioexam.R;
import br.ufsm.fisioexam.database.FisioExamDatabase;
import br.ufsm.fisioexam.database.dao.PunhoDAO;
import br.ufsm.fisioexam.database.dao.SecoesDAO;
import br.ufsm.fisioexam.database.thread.QueryManager;
import br.ufsm.fisioexam.model.Punho;
import br.ufsm.fisioexam.model.Secoes;

public class SecaoTestesEspeciaisPunhoActivity extends AppCompatActivity {
    private Punho punho;
    private PunhoDAO punhoDao;
    private QueryManager<Punho> punhoQueryManager;
    private Secoes secoes;
    private SecoesDAO secoesDao;
    private QueryManager<Secoes> secoesQueryManager;

    private Button buttonSalvar;
    private Button buttonProximo;


    private CheckBox campoPhalenDir;
    private CheckBox campoPhalenEsq;
    private CheckBox campoPhalenInvertidoDir;
    private CheckBox campoPhalenInvertidoEsq;
    private CheckBox campoTinelDir;
    private CheckBox campoTinelEsq;
    private CheckBox campoTriadeDir;
    private CheckBox campoTriadeEsq;
    private CheckBox campoFinkelsteinDir;
    private CheckBox campoFinkelsteinEsq;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secao_testes_especiais_punho);
        inicializaDAOS();
        carregaExame();
        inicializaBotoes();
        inicializaFormulario();
    }


    private void inicializaBotoes() {
        buttonProximo = findViewById(R.id.activity_secao_testes_especiais_punho_button_proximo);
        buttonSalvar = findViewById(R.id.activity_secao_testes_especiais_punho_button_salvar_e_sair);
        setListenerBotoes();
    }

    private void setListenerBotoes() {
        buttonProximo.setOnClickListener(v -> proximoForm());
        buttonSalvar.setOnClickListener(v -> salvarESair());
    }

    private void salvarESair() {
        salva();
        finish();
    }

    private void proximoForm() {
        salva();
        abreProximo();
        finish();
    }

    private void abreProximo() {
        salva();
        Intent vaiParaFormularioSecaoActivity = new Intent(this, IntermediarioSecoesEspecificasActivity.class);
        vaiParaFormularioSecaoActivity.putExtra(CHAVE_EXAME, punho.getExame());
        vaiParaFormularioSecaoActivity.putExtra(CHAVE_SECAO, 6);
        startActivity(vaiParaFormularioSecaoActivity);
        finish();
    }

    private void salva() {
        secoes.setTestesEspeciais(true);
        secoesQueryManager.update(secoes, secoesDao);
        punho.setTestesEspeciaisPhalenDir(campoPhalenDir.isChecked());
        punho.setTestesEspeciaisPhalenEsq(campoPhalenEsq.isChecked());
        punho.setTestesEspeciaisPhalenInvertidoDir(campoPhalenInvertidoDir.isChecked());
        punho.setTestesEspeciaisPhalenInvertidoEsq(campoPhalenInvertidoEsq.isChecked());
        punho.setTestesEspeciaisTinelDir(campoTinelDir.isChecked());
        punho.setTestesEspeciaisTinelEsq(campoTinelEsq.isChecked());
        punho.setTestesEspeciaisTriadeDir(campoTriadeDir.isChecked());
        punho.setTestesEspeciaisTriadeEsq(campoTriadeEsq.isChecked());
        punho.setTestesEspeciaisFinkelsteinDir(campoFinkelsteinDir.isChecked());
        punho.setTestesEspeciaisFinkelsteinEsq(campoFinkelsteinEsq.isChecked());
        punhoQueryManager.update(punho, punhoDao);
    }


    private void inicializaFormulario() {

        campoPhalenDir = findViewById(R.id.activity_secao_testes_especiais_punho_direita_phalen);
        campoPhalenEsq = findViewById(R.id.activity_secao_testes_especiais_punho_esquerda_phalen);
        campoPhalenInvertidoDir = findViewById(R.id.activity_secao_testes_especiais_punho_direita_phalen_invertido);
        campoPhalenInvertidoEsq = findViewById(R.id.activity_secao_testes_especiais_punho_esquerda_phalen_invertido);
        campoTinelDir = findViewById(R.id.activity_secao_testes_especiais_punho_direita_tinel);
        campoTinelEsq = findViewById(R.id.activity_secao_testes_especiais_punho_esquerda_tinel);
        campoTriadeDir = findViewById(R.id.activity_secao_testes_especiais_punho_direita_tunel_ulnar);
        campoTriadeEsq = findViewById(R.id.activity_secao_testes_especiais_punho_esquerda_tunel_ulnar);
        campoFinkelsteinDir = findViewById(R.id.activity_secao_testes_especiais_punho_direita_finkelstein);
        campoFinkelsteinEsq = findViewById(R.id.activity_secao_testes_especiais_punho_esquerda_finkelstein);

        preencheFormulario();
    }

    private void preencheFormulario() {
        if (secoes.isTestesEspeciais()) {
            campoPhalenDir.setChecked(punho.getTestesEspeciaisPhalenDir());
            campoPhalenEsq.setChecked(punho.getTestesEspeciaisPhalenEsq());
            campoPhalenInvertidoDir.setChecked(punho.getTestesEspeciaisPhalenInvertidoDir());
            campoPhalenInvertidoEsq.setChecked(punho.getTestesEspeciaisPhalenInvertidoEsq());
            campoTinelDir.setChecked(punho.getTestesEspeciaisTinelDir());
            campoTinelEsq.setChecked(punho.getTestesEspeciaisTinelEsq());
            campoTriadeDir.setChecked(punho.getTestesEspeciaisTriadeDir());
            campoTriadeEsq.setChecked(punho.getTestesEspeciaisTriadeEsq());
            campoFinkelsteinDir.setChecked(punho.getTestesEspeciaisFinkelsteinDir());
            campoFinkelsteinEsq.setChecked(punho.getTestesEspeciaisFinkelsteinEsq());
        }
    }



    private void carregaExame() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_EXAME)) {
            punho = punhoQueryManager.getOne((String) dados.getSerializableExtra(CHAVE_EXAME), punhoDao);
            secoes = secoesQueryManager.getOne(punho.getExame(), secoesDao);
        }
    }

    private void inicializaDAOS() {
        FisioExamDatabase database = FisioExamDatabase.getInstance(this);
        punhoDao = database.getRoomPunhoDAO();
        secoesDao = database.getRoomSecoesDAO();
        punhoQueryManager = new QueryManager<>();
        secoesQueryManager = new QueryManager<>();
    }
}
