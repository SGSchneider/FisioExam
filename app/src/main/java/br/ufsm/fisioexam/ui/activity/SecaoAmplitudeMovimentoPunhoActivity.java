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
import br.ufsm.fisioexam.database.thread.QueryManager;
import br.ufsm.fisioexam.model.Punho;
import br.ufsm.fisioexam.model.Secoes;

public class SecaoAmplitudeMovimentoPunhoActivity extends AppCompatActivity {

    private Punho punho;
    private PunhoDAO punhoDAO;
    private QueryManager<Punho> punhoQueryManager;
    private Secoes secoes;
    private SecoesDAO secoesDAO;
    private QueryManager<Secoes> secoesQueryManager;
    private EditText campoFlexaoD;
    private EditText campoFlexaoE;
    private EditText campoExtensaoD;
    private EditText campoExtensaoE;
    private EditText campoDesvioRadialD;
    private EditText campoDesvioRadialE;
    private EditText campoDesvioUlnarD;
    private EditText campoDesvioUlnarE;

    private Button buttonSalvar;
    private Button buttonProximo;

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

        InicializaListenerDeClique();
    }

    private void InicializaListenerDeClique() {
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
        secoesQueryManager.update(secoes, secoesDAO);

        punho.setFlexaoDir(campoFlexaoD.getText().toString());
        punho.setFlexaoEsq(campoFlexaoE.getText().toString());
        punho.setExtensaoDir(campoExtensaoD.getText().toString());
        punho.setExtensaoEsq(campoExtensaoE.getText().toString());
        punho.setDesvioRadialDir(campoDesvioRadialD.getText().toString());
        punho.setDesvioRadialEsq(campoDesvioRadialE.getText().toString());
        punho.setDesvioUlnarDir(campoDesvioUlnarD.getText().toString());
        punho.setDesvioUlnarEsq(campoDesvioUlnarE.getText().toString());

        punhoQueryManager.update(punho, punhoDAO);
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
        campoFlexaoD = findViewById(R.id.activity_secao_amplitude_movimento_punho_flexao_direito);
        campoFlexaoE = findViewById(R.id.activity_secao_amplitude_movimento_punho_flexao_esquerdo);
        campoExtensaoD = findViewById(R.id.activity_secao_amplitude_movimento_punho_extensao_direito);
        campoExtensaoE = findViewById(R.id.activity_secao_amplitude_movimento_punho_extensao_esquerdo);
        campoDesvioRadialD = findViewById(R.id.activity_secao_amplitude_movimento_punho_desvio_radial_direito);
        campoDesvioRadialE = findViewById(R.id.activity_secao_amplitude_movimento_punho_desvio_radial_esquerdo);
        campoDesvioUlnarD = findViewById(R.id.activity_secao_amplitude_movimento_punho_desvio_ulnar_direito);
        campoDesvioUlnarE = findViewById(R.id.activity_secao_amplitude_movimento_punho_desvio_ulnar_esquerdo);

        preenchecampos();
    }

    private void preenchecampos() {
        campoFlexaoD.setText(punho.getFlexaoDir());
        campoFlexaoE.setText(punho.getFlexaoEsq());
        campoExtensaoD.setText(punho.getExtensaoDir());
        campoExtensaoE.setText(punho.getExtensaoEsq());
        campoDesvioRadialD.setText(punho.getDesvioRadialDir());
        campoDesvioRadialE.setText(punho.getDesvioRadialEsq());
        campoDesvioUlnarD.setText(punho.getDesvioUlnarDir());
        campoDesvioUlnarE.setText(punho.getDesvioUlnarEsq());

    }

    private void carregaExame() {
        Intent dados = getIntent();
        if(dados.hasExtra(CHAVE_EXAME)){
            punho = punhoQueryManager.getOne(dados.getSerializableExtra(CHAVE_EXAME).toString(), punhoDAO);
            secoes = secoesQueryManager.getOne(punho.getExame(), secoesDAO);
        }
    }

    private void inicializaDaos() {
        FisioExamDatabase database = FisioExamDatabase.getInstance(this);
        punhoDAO = database.getRoomPunhoDAO();
        secoesDAO = database.getRoomSecoesDAO();
        punhoQueryManager = new QueryManager<>();
        secoesQueryManager = new QueryManager<>();
    }


}
