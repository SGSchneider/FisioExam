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
import br.ufsm.fisioexam.database.dao.PunhoDAO;
import br.ufsm.fisioexam.database.dao.SecoesDAO;
import br.ufsm.fisioexam.model.Punho;
import br.ufsm.fisioexam.model.Secoes;

public class SecaoAmplitudeMovimentoPunhoActivity extends AppCompatActivity {

    Punho punho;
    PunhoDAO punhoDAO;
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

    EditText campoDesvioRadialDA;
    EditText campoDesvioRadialDP;
    EditText campoDesvioRadialEA;
    EditText campoDesvioRadialEP;

    EditText campoDesvioUlnarDA;
    EditText campoDesvioUlnarDP;
    EditText campoDesvioUlnarEA;
    EditText campoDesvioUlnarEP;


    Button buttonSalvar;
    Button buttonProximo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secao_amplitude_movimento_punho);
        inicializaDaos();
        carregaExame();
        inicializacampos();
        inicializaBotoes();

    }

    private void inicializaBotoes() {
        buttonProximo = findViewById(R.id.activity_secao_amplitude_movimento_punho_button_proximo);
        buttonSalvar = findViewById(R.id.activity_secao_amplitude_movimento_punho_button_salvar_e_sair);

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

        punho.setFlexaoDirAtivo(campoFlexaoDA.getText().toString());
        punho.setFlexaoDirPassivo(campoFlexaoDP.getText().toString());
        punho.setFlexaoEsqAtivo(campoFlexaoEA.getText().toString());
        punho.setFlexaoEsqPassivo(campoFlexaoEP.getText().toString());
        punho.setExtensaoDirAtivo(campoExtensaoDA.getText().toString());
        punho.setExtensaoDirPassivo(campoExtensaoDP.getText().toString());
        punho.setExtensaoEsqAtivo(campoExtensaoEA.getText().toString());
        punho.setExtensaoEsqPassivo(campoExtensaoEP.getText().toString());
        punho.setDesvioRadialDirAtivo(campoDesvioRadialDA.getText().toString());
        punho.setDesvioRadialDirPassivo(campoDesvioRadialDP.getText().toString());
        punho.setDesvioRadialEsqAtivo(campoDesvioRadialEA.getText().toString());
        punho.setDesvioRadialEsqPassivo(campoDesvioRadialEP.getText().toString());
        punho.setDesvioUlnarDirAtivo(campoDesvioUlnarDA.getText().toString());
        punho.setDesvioUlnarDirPassivo(campoDesvioUlnarDP.getText().toString());
        punho.setDesvioUlnarEsqAtivo(campoDesvioUlnarEA.getText().toString());
        punho.setDesvioUlnarEsqPassivo(campoDesvioUlnarEP.getText().toString());

        punhoDAO.edita(punho);
    }

    private void activityChange() {
        Intent vaiParaIntermediarioProximaSecao = new Intent(this, IntermediarioSecoesEspecificasActivity.class);
        vaiParaIntermediarioProximaSecao.putExtra(CHAVE_EXAME, punho.getExame());
        vaiParaIntermediarioProximaSecao.putExtra(CHAVE_SECAO, 2);
        startActivity(vaiParaIntermediarioProximaSecao);
    }

    private void salvarESair() {
        salva();
        finish();
    }

    private void inicializacampos() {
        campoFlexaoDA = findViewById(R.id.activity_secao_amplitude_movimento_punho_flexao_ativo_direito);
        campoFlexaoDP = findViewById(R.id.activity_secao_amplitude_movimento_punho_flexao_passivo_direito);
        campoFlexaoEA = findViewById(R.id.activity_secao_amplitude_movimento_punho_flexao_ativo_esquerdo);
        campoFlexaoEP = findViewById(R.id.activity_secao_amplitude_movimento_punho_flexao_passivo_esquerdo);
        campoExtensaoDA = findViewById(R.id.activity_secao_amplitude_movimento_punho_extensao_ativo_direito);
        campoExtensaoDP = findViewById(R.id.activity_secao_amplitude_movimento_punho_extensao_passivo_direito);
        campoExtensaoEA = findViewById(R.id.activity_secao_amplitude_movimento_punho_extensao_ativo_esquerdo);
        campoExtensaoEP = findViewById(R.id.activity_secao_amplitude_movimento_punho_extensao_passivo_esquerdo);
        campoDesvioRadialDA = findViewById(R.id.activity_secao_amplitude_movimento_punho_desvio_radial_ativo_direito);
        campoDesvioRadialDP = findViewById(R.id.activity_secao_amplitude_movimento_punho_desvio_radial_passivo_direito);
        campoDesvioRadialEA = findViewById(R.id.activity_secao_amplitude_movimento_punho_desvio_radial_ativo_esquerdo);
        campoDesvioRadialEP = findViewById(R.id.activity_secao_amplitude_movimento_punho_desvio_radial_passivo_esquerdo);
        campoDesvioUlnarDA = findViewById(R.id.activity_secao_amplitude_movimento_punho_desvio_ulnar_ativo_direito);
        campoDesvioUlnarDP = findViewById(R.id.activity_secao_amplitude_movimento_punho_desvio_ulnar_passivo_direito);
        campoDesvioUlnarEA = findViewById(R.id.activity_secao_amplitude_movimento_punho_desvio_ulnar_ativo_esquerdo);
        campoDesvioUlnarEP = findViewById(R.id.activity_secao_amplitude_movimento_punho_desvio_ulnar_passivo_esquerdo);

        preenchecampos();
    }

    private void preenchecampos() {
        campoFlexaoDA.setText(punho.getFlexaoDirAtivo());
        campoFlexaoDP.setText(punho.getFlexaoDirPassivo());
        campoFlexaoEA.setText(punho.getFlexaoEsqAtivo());
        campoFlexaoEP.setText(punho.getFlexaoEsqPassivo());
        campoExtensaoDA.setText(punho.getExtensaoDirAtivo());
        campoExtensaoDP.setText(punho.getExtensaoDirPassivo());
        campoExtensaoEA.setText(punho.getExtensaoEsqAtivo());
        campoExtensaoEP.setText(punho.getExtensaoEsqPassivo());
        campoDesvioRadialDA.setText(punho.getDesvioRadialDirAtivo());
        campoDesvioRadialDP.setText(punho.getDesvioRadialDirPassivo());
        campoDesvioRadialEA.setText(punho.getDesvioRadialEsqAtivo());
        campoDesvioRadialEP.setText(punho.getDesvioRadialEsqPassivo());
        campoDesvioUlnarDA.setText(punho.getDesvioUlnarDirAtivo());
        campoDesvioUlnarDP.setText(punho.getDesvioUlnarDirPassivo());
        campoDesvioUlnarEA.setText(punho.getDesvioUlnarEsqAtivo());
        campoDesvioUlnarEP.setText(punho.getDesvioUlnarEsqPassivo());

    }

    private void carregaExame() {
        Intent dados = getIntent();
        if(dados.hasExtra(CHAVE_EXAME)){
            punho = punhoDAO.getPunho(dados.getSerializableExtra(CHAVE_EXAME).toString());
            secoes = secoesDAO.getSecao(punho.getExame());
        }
    }

    private void inicializaDaos() {
        FisioExamDatabase database = FisioExamDatabase.getInstance(this);
        punhoDAO = database.getRoomPunhoDAO();
        secoesDAO = database.getRoomSecoesDAO();
    }


}
