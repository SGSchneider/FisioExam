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

public class SecaoAmplitudeMovimentoCotoveloActivity extends AppCompatActivity {

    Cotovelo cotovelo;
    CotoveloDAO cotoveloDAO;
    private QueryManager<Cotovelo> cotoveloQueryManager;
    private Secoes secoes;
    private SecoesDAO secoesDAO;
    private QueryManager<Secoes> secaoQueryManager;
    
    EditText campoFlexaoD;
    EditText campoFlexaoE;

    EditText campoExtensaoD;
    EditText campoExtensaoE;

    EditText campoSupinacaoD;
    EditText campoSupinacaoE;

    EditText campoPronacaoD;
    EditText campoPronacaoE;

    EditText campoAnguloCarregamentoDP;
    EditText campoAnguloCarregamentoEP;

    Button buttonSalvar;
    Button buttonProximo;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secao_amplitude_movimento_cotovelo);
        inicializaDaos();
        carregaExame();
        inicializacampos();
        inicializaBotoes();

    }

    private void inicializaBotoes() {
        buttonProximo = findViewById(R.id.activity_secao_amplitude_movimento_cotovelo_button_proximo);
        buttonSalvar = findViewById(R.id.activity_secao_amplitude_movimento_cotovelo_button_salvar_e_sair);

        buttonProximo.setOnClickListener(v -> proximoForm());
        buttonSalvar.setOnClickListener(v-> salvarESair());
    }

    private void proximoForm() {
        salva();
        activityChange();
        finish();
    }

    private void salva() {
        secoes.setAmplitudeMovimento(true);
        secaoQueryManager.update(secoes, secoesDAO);

        cotovelo.setFlexaoDir(campoFlexaoD.getText().toString());
        cotovelo.setFlexaoEsq(campoFlexaoE.getText().toString());
        cotovelo.setExtensaoDir(campoExtensaoD.getText().toString());
        cotovelo.setExtensaoEsq(campoExtensaoE.getText().toString());
        cotovelo.setSupinacaoDir(campoSupinacaoD.getText().toString());
        cotovelo.setSupinacaoEsq(campoSupinacaoE.getText().toString());
        cotovelo.setPronacaoDir(campoPronacaoD.getText().toString());
        cotovelo.setPronacaoEsq(campoPronacaoE.getText().toString());
        cotovelo.setAnguloCarregamentoDir(campoAnguloCarregamentoDP.getText().toString());
        cotovelo.setAnguloCarregamentoEsq(campoAnguloCarregamentoEP.getText().toString());


        cotoveloQueryManager.update(cotovelo, cotoveloDAO);
    }

    private void activityChange() {
        Intent vaiParaIntermediarioProximaSecao = new Intent(this, IntermediarioSecoesEspecificasActivity.class);
        vaiParaIntermediarioProximaSecao.putExtra(CHAVE_EXAME, cotovelo.getExame());
        vaiParaIntermediarioProximaSecao.putExtra(CHAVE_SECAO, 2);
        startActivity(vaiParaIntermediarioProximaSecao);
    }

    private void salvarESair() {
        salva();
        finish();
    }

    private void inicializacampos() {
        campoFlexaoD = findViewById(R.id.activity_secao_amplitude_movimento_cotovelo_flexao_direito);
        campoFlexaoE = findViewById(R.id.activity_secao_amplitude_movimento_cotovelo_flexao_esquerdo);
        campoExtensaoD = findViewById(R.id.activity_secao_amplitude_movimento_cotovelo_extensao_direito);
        campoExtensaoE = findViewById(R.id.activity_secao_amplitude_movimento_cotovelo_extensao_esquerdo);
        campoSupinacaoD = findViewById(R.id.activity_secao_amplitude_movimento_cotovelo_supinacao_direito);
        campoSupinacaoE = findViewById(R.id.activity_secao_amplitude_movimento_cotovelo_supinacao_esquerdo);
        campoPronacaoD = findViewById(R.id.activity_secao_amplitude_movimento_cotovelo_pronacao_direito);
        campoPronacaoE = findViewById(R.id.activity_secao_amplitude_movimento_cotovelo_pronacao_esquerdo);
        campoAnguloCarregamentoDP = findViewById(R.id.activity_secao_amplitude_movimento_cotovelo_angulo_carregamento_direito);
        campoAnguloCarregamentoEP = findViewById(R.id.activity_secao_amplitude_movimento_cotovelo_angulo_carregamento_esquerdo);

        preenchecampos();
    }

    private void preenchecampos() {
        campoFlexaoD.setText(cotovelo.getFlexaoDir());
        campoFlexaoE.setText(cotovelo.getFlexaoEsq());
        campoExtensaoD.setText(cotovelo.getExtensaoDir());
        campoExtensaoE.setText(cotovelo.getExtensaoEsq());
        campoSupinacaoD.setText(cotovelo.getSupinacaoDir());
        campoSupinacaoE.setText(cotovelo.getSupinacaoEsq());
        campoPronacaoD.setText(cotovelo.getPronacaoDir());
        campoPronacaoE.setText(cotovelo.getPronacaoEsq());
        campoAnguloCarregamentoDP.setText(cotovelo.getAnguloCarregamentoDir());
        campoAnguloCarregamentoEP.setText(cotovelo.getAnguloCarregamentoEsq());

    }

    private void carregaExame() {
        Intent dados = getIntent();
        if(dados.hasExtra(CHAVE_EXAME)){
            cotovelo = cotoveloQueryManager.getOne(dados.getSerializableExtra(CHAVE_EXAME).toString(), cotoveloDAO);
            secoes = secaoQueryManager.getOne(cotovelo.getExame(), secoesDAO);
        }
    }

    private void inicializaDaos() {
        FisioExamDatabase database = FisioExamDatabase.getInstance(this);
        cotoveloDAO = database.getRoomCotoveloDAO();
        secoesDAO = database.getRoomSecoesDAO();
        cotoveloQueryManager = new QueryManager<>();
        secaoQueryManager = new QueryManager<>();

    }
}
