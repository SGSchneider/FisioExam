package br.ufsm.fisioexam.ui.activity;

import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_EXAME;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_SECAO;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import br.ufsm.fisioexam.R;
import br.ufsm.fisioexam.database.FisioExamDatabase;
import br.ufsm.fisioexam.database.dao.CotoveloDAO;
import br.ufsm.fisioexam.database.dao.SecoesDAO;
import br.ufsm.fisioexam.database.thread.QueryManager;
import br.ufsm.fisioexam.model.Cotovelo;
import br.ufsm.fisioexam.model.Secoes;

public class SecaoTestesEspeciaisCotoveloActivity extends AppCompatActivity {
    private Cotovelo cotovelo;
    private CotoveloDAO cotoveloDao;
    private QueryManager<Cotovelo> cotoveloQueryManager;
    private Secoes secoes;
    private SecoesDAO secoesDao;
    private QueryManager<Secoes> secoesQueryManager;

    private Button buttonSalvar;
    private Button buttonProximo;

    private Calendar dataDash;
    private Calendar dataAses;

    private CheckBox campoCozenDir;
    private CheckBox campoCozenEsq;
    private CheckBox campoCotoveloGolfistaDir;
    private CheckBox campoCotoveloGolfistaEsq;
    private CheckBox campoSinalTinelDir;
    private CheckBox campoSinalTinelEsq;
    private CheckBox campoLclDir;
    private CheckBox campoLclEsq;
    private CheckBox campoLcmDir;
    private CheckBox campoLcmEsq;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secao_testes_especiais_cotovelo);
        inicializaCalendars();
        inicializaDAOS();
        carregaExame();
        inicializaBotoes();
        inicializaFormulario();
    }

    private void inicializaCalendars() {
        dataDash = Calendar.getInstance();
        dataAses = Calendar.getInstance();
    }

    private void inicializaBotoes() {
        buttonProximo = findViewById(R.id.activity_secao_testes_especiais_cotovelo_button_proximo);
        buttonSalvar = findViewById(R.id.activity_secao_testes_especiais_cotovelo_button_salvar_e_sair);
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
        vaiParaFormularioSecaoActivity.putExtra(CHAVE_EXAME, cotovelo.getExame());
        vaiParaFormularioSecaoActivity.putExtra(CHAVE_SECAO, 6);
        startActivity(vaiParaFormularioSecaoActivity);
        finish();
    }

    private void salva() {
        secoes.setTestesEspeciais(true);
        secoesQueryManager.update(secoes, secoesDao);

        cotovelo.setTesteCozenDir(campoCozenDir.isChecked());
        cotovelo.setTesteCozenEsq(campoCozenEsq.isChecked());
        cotovelo.setTesteCotoveloGolfistaDir(campoCotoveloGolfistaDir.isChecked());
        cotovelo.setTesteCotoveloGolfistaEsq(campoCotoveloGolfistaEsq.isChecked());
        cotovelo.setTesteSinalTinelDir(campoSinalTinelDir.isChecked());
        cotovelo.setTesteSinalTinelEsq(campoSinalTinelEsq.isChecked());
        cotovelo.setTesteEsforcoVaroDir(campoLclDir.isChecked());
        cotovelo.setTesteEsforcoVaroEsq(campoLclEsq.isChecked());
        cotovelo.setTesteEsforcoValgoDir(campoLcmDir.isChecked());
        cotovelo.setTesteEsforcoValgoEsq(campoLcmEsq.isChecked());

        cotoveloQueryManager.update(cotovelo, cotoveloDao);
    }



    private void inicializaFormulario() {
        campoCozenDir = findViewById(R.id.activity_secao_testes_especiais_cotovelo_direita_cozen);
        campoCozenEsq = findViewById(R.id.activity_secao_testes_especiais_cotovelo_esquerda_cozen);
        campoCotoveloGolfistaDir = findViewById(R.id.activity_secao_testes_especiais_cotovelo_direita_teste_golfista);
        campoCotoveloGolfistaEsq = findViewById(R.id.activity_secao_testes_especiais_cotovelo_esquerda_teste_golfista);
        campoSinalTinelDir = findViewById(R.id.activity_secao_testes_especiais_cotovelo_direita_tinel);
        campoSinalTinelEsq = findViewById(R.id.activity_secao_testes_especiais_cotovelo_esquerda_tinel);
        campoLclDir = findViewById(R.id.activity_secao_testes_especiais_cotovelo_direita_lcl);
        campoLclEsq = findViewById(R.id.activity_secao_testes_especiais_cotovelo_esquerda_lcl);
        campoLcmDir = findViewById(R.id.activity_secao_testes_especiais_cotovelo_direita_lcm);
        campoLcmEsq = findViewById(R.id.activity_secao_testes_especiais_cotovelo_esquerda_lcm);


        preencheFormulario();
    }

    private void preencheFormulario() {
        if(secoes.isTestesEspeciais()){
            dataDash.setTimeInMillis(cotovelo.getDashData());
            dataAses.setTimeInMillis(cotovelo.getAsesData());

            campoCozenDir.setChecked(cotovelo.getTesteCozenDir());
            campoCozenEsq.setChecked(cotovelo.getTesteCozenEsq());
            campoCotoveloGolfistaDir.setChecked(cotovelo.getTesteCotoveloGolfistaDir());
            campoCotoveloGolfistaEsq.setChecked(cotovelo.getTesteCotoveloGolfistaEsq());
            campoSinalTinelDir.setChecked(cotovelo.getTesteSinalTinelDir());
            campoSinalTinelEsq.setChecked(cotovelo.getTesteSinalTinelEsq());
            campoLclDir.setChecked(cotovelo.getTesteEsforcoVaroDir());
            campoLclEsq.setChecked(cotovelo.getTesteEsforcoVaroEsq());
            campoLcmDir.setChecked(cotovelo.getTesteEsforcoValgoDir());
            campoLcmEsq.setChecked(cotovelo.getTesteEsforcoValgoEsq());

        }
    }

    

    private void carregaExame() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_EXAME)) {
            cotovelo = cotoveloQueryManager.getOne((String) dados.getSerializableExtra(CHAVE_EXAME), cotoveloDao);
            secoes = secoesQueryManager.getOne(cotovelo.getExame(), secoesDao);
        }
    }

    private void inicializaDAOS() {
        FisioExamDatabase database = FisioExamDatabase.getInstance(this);
        cotoveloDao = database.getRoomCotoveloDAO();
        secoesDao = database.getRoomSecoesDAO();
        cotoveloQueryManager = new QueryManager<>();
        secoesQueryManager = new QueryManager<>();
    }
}
