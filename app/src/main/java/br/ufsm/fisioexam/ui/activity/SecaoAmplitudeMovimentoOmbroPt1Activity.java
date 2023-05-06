package br.ufsm.fisioexam.ui.activity;

import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_EXAME;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.ufsm.fisioexam.R;
import br.ufsm.fisioexam.database.FisioExamDatabase;
import br.ufsm.fisioexam.database.dao.OmbroDAO;
import br.ufsm.fisioexam.database.dao.SecoesDAO;
import br.ufsm.fisioexam.model.Ombro;
import br.ufsm.fisioexam.model.Secoes;

public class SecaoAmplitudeMovimentoOmbroPt1Activity extends AppCompatActivity {
    //Usar Parâmetros MAGEE (2010) por ser mais recente.

    private EditText campoFlexaoPassivoDir;
    private EditText campoFlexaoAtivoDir;
    private EditText campoFlexaoPassivoEsq;
    private EditText campoFlexaoAtivoEsq;
    private EditText campoExtensaoAtivoDir;
    private EditText campoExtensaoPassivoDir;
    private EditText campoExtensaoAtivoEsq;
    private EditText campoExtensaoPassivoEsq;
    private EditText campoAbducaoAtivoDir;
    private EditText campoAbducaoPassivoDir;
    private EditText campoAbducaoAtivoEsq;
    private EditText campoAbducaoPassivoEsq;
    private EditText campoAducaoHorizontalAtivoDir;
    private EditText campoAducaoHorizontalPassivoDir;
    private EditText campoAducaoHorizontalAtivoEsq;
    private EditText campoAducaoHorizontalPassivoEsq;

    private Button parteDois;
    private ImageButton ajudaFlexao;
    private ImageButton ajudaExtensao;
    private ImageButton ajudaAbducao;
    private ImageButton ajudaAducaoHorizontal;

    private Ombro ombro;
    private OmbroDAO ombroDao;
    private Secoes secoes;
    private SecoesDAO secoesDao;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secao_amplitude_movimento_ombro_pt1);
        inicializaDaos();
        carregaExame();
        inicializaFormulario();
        inicializaBotoes();
    }


    private void inicializaDaos() {
        FisioExamDatabase database = FisioExamDatabase.getInstance(this);
        ombroDao = database.getRoomOmbroDAO();
        secoesDao = database.getRoomSecoesDAO();
    }

    private void inicializaBotoes() {
        parteDois = findViewById(R.id.activity_secao_amplitude_movimento_ombro_button_parte_2);

        ajudaFlexao = findViewById(R.id.activity_secao_amplitude_movimento_ombro_button_help_flexao);
        ajudaExtensao = findViewById(R.id.activity_secao_amplitude_movimento_ombro_button_help_extensao);
        ajudaAbducao = findViewById(R.id.activity_secao_amplitude_movimento_ombro_button_help_abducao);
        ajudaAducaoHorizontal = findViewById(R.id.activity_secao_amplitude_movimento_ombro_button_help_aducao_horizontal);

        configuraListenersDeClique();
    }

    private void configuraListenersDeClique() {
        parteDois.setOnClickListener(v -> vaiParaParteDois());


        ajudaFlexao.setOnClickListener(v -> vaiParaAjuda(AjudaAmplitudeMovimentoOmbroFlexaoActivity.class));
        ajudaAbducao.setOnClickListener(v -> vaiParaAjuda(AjudaAmplitudeMovimentoOmbroAbducaoActivity.class));
        ajudaAducaoHorizontal.setOnClickListener(v -> vaiParaAjuda(AjudaAmplitudeMovimentoOmbroAducaoHorizontalActivity.class));
        ajudaExtensao.setOnClickListener(v -> vaiParaAjuda(AjudaAmplitudeMovimentoOmbroExtensaoActivity.class));
    }

    private void vaiParaAjuda(Class<?> classe) {
        Intent vaiParaAjudaActivity = new Intent(this,classe);
        startActivity(vaiParaAjudaActivity);
    }


    private void salva() {
        secoes.setAmplitudeMovimento(true);
        secoesDao.edita(secoes);

        ombro.setFlexaoDirAtivo(campoFlexaoAtivoDir.getText().toString());
        ombro.setFlexaoDirPassivo(campoFlexaoPassivoDir.getText().toString());
        ombro.setFlexaoEsqAtivo(campoFlexaoAtivoEsq.getText().toString());
        ombro.setFlexaoEsqPassivo(campoFlexaoPassivoEsq.getText().toString());
        ombro.setExtensaoDirAtivo(campoExtensaoAtivoDir.getText().toString());
        ombro.setExtensaoDirPassivo(campoExtensaoPassivoDir.getText().toString());
        ombro.setExtensaoEsqAtivo(campoExtensaoAtivoEsq.getText().toString());
        ombro.setExtensaoEsqPassivo(campoExtensaoPassivoEsq.getText().toString());
        ombro.setAbducaoDirAtivo(campoAbducaoAtivoDir.getText().toString());
        ombro.setAbducaoDirPassivo(campoAbducaoPassivoDir.getText().toString());
        ombro.setAbducaoEsqAtivo(campoAbducaoAtivoEsq.getText().toString());
        ombro.setAbducaoEsqPassivo(campoAbducaoPassivoEsq.getText().toString());
        ombro.setAducaoHorDirAtivo(campoAducaoHorizontalAtivoDir.getText().toString());
        ombro.setAducaoHorDirPassivo(campoAducaoHorizontalPassivoDir.getText().toString());
        ombro.setAducaoHorEsqAtivo(campoAducaoHorizontalAtivoEsq.getText().toString());
        ombro.setAducaoHorEsqPassivo(campoAducaoHorizontalPassivoEsq.getText().toString());

        ombroDao.edita(ombro);
    }

    private void vaiParaParteDois() {
        salva();
        Intent vaiParaParteDoisActivity = new Intent(this, SecaoAmplitudeMovimentoOmbroPt2Activity.class);
        vaiParaParteDoisActivity.putExtra(CHAVE_EXAME, ombro.getId());
        startActivity(vaiParaParteDoisActivity);
        finish();
    }


    private void carregaExame() {
        Intent dados = getIntent();

        if (dados.hasExtra(CHAVE_EXAME)) {
            ombro = ombroDao.getOmbro((String) dados.getSerializableExtra(CHAVE_EXAME));
            secoes = secoesDao.getSecao(ombro.getExame());
        }
    }


    private void inicializaFormulario() {
        campoFlexaoAtivoDir = findViewById(R.id.activity_secao_amplitude_movimento_ombro_flexao_ativo_direito);
        campoFlexaoPassivoDir = findViewById(R.id.activity_secao_amplitude_movimento_ombro_flexao_passivo_direito);
        campoFlexaoPassivoEsq = findViewById(R.id.activity_secao_amplitude_movimento_ombro_flexao_ativo_esquerdo);
        campoFlexaoAtivoEsq = findViewById(R.id.activity_secao_amplitude_movimento_ombro_flexao_passivo_esquerdo);
        campoExtensaoAtivoDir = findViewById(R.id.activity_secao_amplitude_movimento_ombro_extensao_ativo_direito);
        campoExtensaoPassivoDir = findViewById(R.id.activity_secao_amplitude_movimento_ombro_extensao_passivo_direito);
        campoExtensaoAtivoEsq = findViewById(R.id.activity_secao_amplitude_movimento_ombro_extensao_ativo_esquerdo);
        campoExtensaoPassivoEsq = findViewById(R.id.activity_secao_amplitude_movimento_ombro_extensao_passivo_esquerdo);
        campoAbducaoAtivoDir = findViewById(R.id.activity_secao_amplitude_movimento_ombro_abducao_ativo_direito);
        campoAbducaoPassivoDir = findViewById(R.id.activity_secao_amplitude_movimento_ombro_abducao_passivo_direito);
        campoAbducaoAtivoEsq = findViewById(R.id.activity_secao_amplitude_movimento_ombro_abducao_ativo_esquerdo);
        campoAbducaoPassivoEsq = findViewById(R.id.activity_secao_amplitude_movimento_ombro_abducao_passivo_esquerdo);
        campoAducaoHorizontalAtivoDir = findViewById(R.id.activity_secao_amplitude_movimento_ombro_aducao_horizontal_ativo_direito);
        campoAducaoHorizontalPassivoDir = findViewById(R.id.activity_secao_amplitude_movimento_ombro_aducao_horizontal_passivo_direito);
        campoAducaoHorizontalAtivoEsq = findViewById(R.id.activity_secao_amplitude_movimento_ombro_aducao_horizontal_ativo_esquerdo);
        campoAducaoHorizontalPassivoEsq = findViewById(R.id.activity_secao_amplitude_movimento_ombro_aducao_horizontal_passivo_esquerdo);

        if (secoes.isAmplitudeMovimento()) {
            preencheCampos();
        }
    }

    private void preencheCampos() {

        campoFlexaoAtivoDir.setText(ombro.getFlexaoDirAtivo());
        campoFlexaoPassivoDir.setText(ombro.getFlexaoDirPassivo());
        campoFlexaoPassivoEsq.setText(ombro.getFlexaoEsqPassivo());
        campoFlexaoAtivoEsq.setText(ombro.getFlexaoEsqAtivo());
        campoExtensaoAtivoDir.setText(ombro.getExtensaoDirAtivo());
        campoExtensaoPassivoDir.setText(ombro.getExtensaoDirPassivo());
        campoExtensaoAtivoEsq.setText(ombro.getExtensaoEsqAtivo());
        campoExtensaoPassivoEsq.setText(ombro.getExtensaoEsqPassivo());
        campoAbducaoAtivoDir.setText(ombro.getAbducaoDirAtivo());
        campoAbducaoPassivoDir.setText(ombro.getAbducaoDirPassivo());
        campoAbducaoAtivoEsq.setText(ombro.getAbducaoEsqAtivo());
        campoAbducaoPassivoEsq.setText(ombro.getAbducaoEsqPassivo());
        campoAducaoHorizontalAtivoDir.setText(ombro.getAducaoHorDirAtivo());
        campoAducaoHorizontalPassivoDir.setText(ombro.getAducaoHorDirPassivo());
        campoAducaoHorizontalAtivoEsq.setText(ombro.getAducaoHorEsqAtivo());
        campoAducaoHorizontalPassivoEsq.setText(ombro.getAducaoHorEsqPassivo());

    }
}
