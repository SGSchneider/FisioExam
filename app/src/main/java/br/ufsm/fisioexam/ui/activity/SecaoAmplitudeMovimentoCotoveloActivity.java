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
import br.ufsm.fisioexam.model.Cotovelo;
import br.ufsm.fisioexam.model.Secoes;

public class SecaoAmplitudeMovimentoCotoveloActivity extends AppCompatActivity {

    Cotovelo cotovelo;
    CotoveloDAO cotoveloDAO;
    Secoes secoes;
    SecoesDAO secoesDAO;
    
    EditText campoFlexaoDA;
    EditText campoFlexaoDP;
    EditText campoFlexaoEA;
    EditText campoFlexaoEP;

    EditText campoExtensaoDA;
    EditText campoExtensaoDP;
    EditText campoExtensaoEA;
    EditText campoExtensaoEP;

    EditText campoSupinacaoDA;
    EditText campoSupinacaoDP;
    EditText campoSupinacaoEA;
    EditText campoSupinacaoEP;

    EditText campoPronacaoDA;
    EditText campoPronacaoDP;
    EditText campoPronacaoEA;
    EditText campoPronacaoEP;

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
        secoesDAO.edita(secoes);

        cotovelo.setFlexaoDirAtivo(campoFlexaoDA.getText().toString());
        cotovelo.setFlexaoDirPassivo(campoFlexaoDP.getText().toString());
        cotovelo.setFlexaoEsqAtivo(campoFlexaoEA.getText().toString());
        cotovelo.setFlexaoEsqPassivo(campoFlexaoEP.getText().toString());
        cotovelo.setExtensaoDirAtivo(campoExtensaoDA.getText().toString());
        cotovelo.setExtensaoDirPassivo(campoExtensaoDP.getText().toString());
        cotovelo.setExtensaoEsqAtivo(campoExtensaoEA.getText().toString());
        cotovelo.setExtensaoEsqPassivo(campoExtensaoEP.getText().toString());
        cotovelo.setSupinacaoDirAtivo(campoSupinacaoDA.getText().toString());
        cotovelo.setSupinacaoDirPassivo(campoSupinacaoDP.getText().toString());
        cotovelo.setSupinacaoEsqAtivo(campoSupinacaoEA.getText().toString());
        cotovelo.setSupinacaoEsqPassivo(campoSupinacaoEP.getText().toString());
        cotovelo.setPronacaoDirAtivo(campoPronacaoDA.getText().toString());
        cotovelo.setPronacaoDirPassivo(campoPronacaoDP.getText().toString());
        cotovelo.setPronacaoEsqAtivo(campoPronacaoEA.getText().toString());
        cotovelo.setPronacaoEsqPassivo(campoPronacaoEP.getText().toString());
        cotovelo.setAnguloCarregamentoDirPassivo(campoAnguloCarregamentoDP.getText().toString());
        cotovelo.setAnguloCarregamentoEsqPassivo(campoAnguloCarregamentoEP.getText().toString());


        cotoveloDAO.edita(cotovelo);
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
        campoFlexaoDA = findViewById(R.id.activity_secao_amplitude_movimento_cotovelo_flexao_ativo_direito);
        campoFlexaoDP = findViewById(R.id.activity_secao_amplitude_movimento_cotovelo_flexao_passivo_direito);
        campoFlexaoEA = findViewById(R.id.activity_secao_amplitude_movimento_cotovelo_flexao_ativo_esquerdo);
        campoFlexaoEP = findViewById(R.id.activity_secao_amplitude_movimento_cotovelo_flexao_passivo_esquerdo);
        campoExtensaoDA = findViewById(R.id.activity_secao_amplitude_movimento_cotovelo_extensao_ativo_direito);
        campoExtensaoDP = findViewById(R.id.activity_secao_amplitude_movimento_cotovelo_extensao_passivo_direito);
        campoExtensaoEA = findViewById(R.id.activity_secao_amplitude_movimento_cotovelo_extensao_ativo_esquerdo);
        campoExtensaoEP = findViewById(R.id.activity_secao_amplitude_movimento_cotovelo_extensao_passivo_esquerdo);
        campoSupinacaoDA = findViewById(R.id.activity_secao_amplitude_movimento_cotovelo_supinacao_ativo_direito);
        campoSupinacaoDP = findViewById(R.id.activity_secao_amplitude_movimento_cotovelo_supinacao_passivo_direito);
        campoSupinacaoEA = findViewById(R.id.activity_secao_amplitude_movimento_cotovelo_supinacao_ativo_esquerdo);
        campoSupinacaoEP = findViewById(R.id.activity_secao_amplitude_movimento_cotovelo_supinacao_passivo_esquerdo);
        campoPronacaoDA = findViewById(R.id.activity_secao_amplitude_movimento_cotovelo_pronacao_ativo_direito);
        campoPronacaoDP = findViewById(R.id.activity_secao_amplitude_movimento_cotovelo_pronacao_passivo_direito);
        campoPronacaoEA = findViewById(R.id.activity_secao_amplitude_movimento_cotovelo_pronacao_ativo_esquerdo);
        campoPronacaoEP = findViewById(R.id.activity_secao_amplitude_movimento_cotovelo_pronacao_passivo_esquerdo);
        campoAnguloCarregamentoDP = findViewById(R.id.activity_secao_amplitude_movimento_cotovelo_angulo_carregamento_passivo_direito);
        campoAnguloCarregamentoEP = findViewById(R.id.activity_secao_amplitude_movimento_cotovelo_angulo_carregamento_passivo_esquerdo);

        preenchecampos();
    }

    private void preenchecampos() {
        campoFlexaoDA.setText(cotovelo.getFlexaoDirAtivo());
        campoFlexaoDP.setText(cotovelo.getFlexaoDirPassivo());
        campoFlexaoEA.setText(cotovelo.getFlexaoEsqAtivo());
        campoFlexaoEP.setText(cotovelo.getFlexaoEsqPassivo());
        campoExtensaoDA.setText(cotovelo.getExtensaoDirAtivo());
        campoExtensaoDP.setText(cotovelo.getExtensaoDirPassivo());
        campoExtensaoEA.setText(cotovelo.getExtensaoEsqAtivo());
        campoExtensaoEP.setText(cotovelo.getExtensaoEsqPassivo());
        campoSupinacaoDA.setText(cotovelo.getSupinacaoDirAtivo());
        campoSupinacaoDP.setText(cotovelo.getSupinacaoDirPassivo());
        campoSupinacaoEA.setText(cotovelo.getSupinacaoEsqAtivo());
        campoSupinacaoEP.setText(cotovelo.getSupinacaoEsqPassivo());
        campoPronacaoDA.setText(cotovelo.getPronacaoDirAtivo());
        campoPronacaoDP.setText(cotovelo.getPronacaoDirPassivo());
        campoPronacaoEA.setText(cotovelo.getPronacaoEsqAtivo());
        campoPronacaoEP.setText(cotovelo.getPronacaoEsqPassivo());
        campoAnguloCarregamentoDP.setText(cotovelo.getAnguloCarregamentoDirPassivo());
        campoAnguloCarregamentoEP.setText(cotovelo.getAnguloCarregamentoEsqPassivo());

    }

    private void carregaExame() {
        Intent dados = getIntent();
        if(dados.hasExtra(CHAVE_EXAME)){
            cotovelo = cotoveloDAO.getCotovelo(dados.getSerializableExtra(CHAVE_EXAME).toString());
            secoes = secoesDAO.getSecao(cotovelo.getExame());
        }
    }

    private void inicializaDaos() {
        FisioExamDatabase database = FisioExamDatabase.getInstance(this);
        cotoveloDAO = database.getRoomCotoveloDAO();
        secoesDAO = database.getRoomSecoesDAO();
    }


}
