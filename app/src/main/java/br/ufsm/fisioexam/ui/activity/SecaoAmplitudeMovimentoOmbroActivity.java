package br.ufsm.fisioexam.ui.activity;

import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_EXAME;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_SECAO;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import br.ufsm.fisioexam.R;
import br.ufsm.fisioexam.database.FisioExamDatabase;
import br.ufsm.fisioexam.database.dao.OmbroDAO;
import br.ufsm.fisioexam.database.dao.SecoesDAO;
import br.ufsm.fisioexam.model.Ombro;
import br.ufsm.fisioexam.model.Secoes;

public class SecaoAmplitudeMovimentoOmbroActivity extends AppCompatActivity {
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
    private EditText campoRotacaoMedialAtivoDir;
    private EditText campoRotacaoMedialPassivoDir;
    private EditText campoRotacaoMedialAtivoEsq;
    private EditText campoRotacaoMedialPassivoEsq;
    private EditText campoRotacaoLateralAtivoDir;
    private EditText campoRotacaoLateralPassivoDir;
    private EditText campoRotacaoLateralAtivoEsq;
    private EditText campoRotacaoLateralPassivoEsq;
    private SwitchCompat campoAlteracaoRitmoEscapuloUmeral;
    private CheckBox campoGrauAlteracaoI;
    private CheckBox campoGrauAlteracaoII;
    private CheckBox campoGrauAlteracaoIII;
    private Button proximo;
    private Button salvarESair;
    private Ombro ombro;
    private OmbroDAO ombroDao;
    private Secoes secoes;
    private SecoesDAO secoesDao;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secao_amplitude_movimento_ombro);
        inicializaDaos();
        carregaExame();
        inicializaFormulario();
        inicializaBotoes();
        inicializaOtimizadores();
    }

    private void inicializaOtimizadores() {
        configuraListenerDeMarcacao();
    }

    private void inicializaDaos() {
        FisioExamDatabase database = FisioExamDatabase.getInstance(this);
        ombroDao = database.getRoomOmbroDAO();
        secoesDao = database.getRoomSecoesDAO();
    }

    private void inicializaBotoes() {
        proximo = findViewById(R.id.activity_secao_amplitude_movimento_ombro_button_proximo);
        salvarESair = findViewById(R.id.activity_secao_amplitude_movimento_ombro_button_salvar_e_sair);
        configuraListenersDeClique();
    }

    private void configuraListenersDeClique() {
        proximo.setOnClickListener(v -> proximoForm());

        salvarESair.setOnClickListener(v -> finalizaForm());
    }

    private void configuraListenerDeMarcacao() {
        campoGrauAlteracaoI.setOnCheckedChangeListener((compoundButton, b) -> selecaoDeGrauI());
        campoGrauAlteracaoII.setOnCheckedChangeListener((compoundButton, b) -> selecaoDeGrauII());
        campoGrauAlteracaoIII.setOnCheckedChangeListener((compoundButton, b) -> selecaoDeGrauIII());
    }

    private void selecaoDeGrauI() {
        if (campoGrauAlteracaoI.isChecked()) {
            campoGrauAlteracaoII.setChecked(false);
            campoGrauAlteracaoIII.setChecked(false);
        }
    }

    private void selecaoDeGrauII() {
        if (campoGrauAlteracaoII.isChecked()) {
            campoGrauAlteracaoI.setChecked(false);
            campoGrauAlteracaoIII.setChecked(false);
        }
    }

    private void selecaoDeGrauIII() {
        if (campoGrauAlteracaoIII.isChecked()) {
            campoGrauAlteracaoII.setChecked(false);
            campoGrauAlteracaoI.setChecked(false);
        }
    }

    private void finalizaForm() {
        salva();
        finish();
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
        ombro.setRotacaoMedDirAtivo(campoRotacaoMedialAtivoDir.getText().toString());
        ombro.setRotacaoMedDirPassivo(campoRotacaoMedialPassivoDir.getText().toString());
        ombro.setRotacaoMedEsqAtivo(campoRotacaoMedialAtivoEsq.getText().toString());
        ombro.setRotacaoMedEsqPassivo(campoRotacaoMedialPassivoEsq.getText().toString());
        ombro.setRotacaoLatDirAtivo(campoRotacaoLateralAtivoDir.getText().toString());
        ombro.setRotacaoLatDirPassivo(campoRotacaoLateralPassivoDir.getText().toString());
        ombro.setRotacaoLatEsqAtivo(campoRotacaoLateralAtivoEsq.getText().toString());
        ombro.setRotacaoLatEsqPassivo(campoRotacaoLateralPassivoEsq.getText().toString());
        ombro.setAlteracaoRitmoEscapuloUmeral(campoAlteracaoRitmoEscapuloUmeral.isChecked());
        ombro.setGrauI(campoGrauAlteracaoI.isChecked());
        ombro.setGrauII(campoGrauAlteracaoII.isChecked());
        ombro.setGrauIII(campoGrauAlteracaoIII.isChecked());

        ombroDao.edita(ombro);
    }

    private void proximoForm() {
        salva();
        Intent vaiParaFormularioSecaoActivity = new Intent(this, IntermediarioSecoesEspecificasActivity.class);
        vaiParaFormularioSecaoActivity.putExtra(CHAVE_EXAME, ombro.getExame());
        vaiParaFormularioSecaoActivity.putExtra(CHAVE_SECAO, 2);
        startActivity(vaiParaFormularioSecaoActivity);
        finish();
    }


    private void carregaExame() {
        Intent dados = getIntent();

        if (dados.hasExtra(CHAVE_EXAME)) {
            ombro = ombroDao.getOmbro((String) dados.getSerializableExtra(CHAVE_EXAME));
            secoes = secoesDao.getSecao(ombro.getId());
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
        campoRotacaoMedialAtivoDir = findViewById(R.id.activity_secao_amplitude_movimento_ombro_rotacao_medial_ativo_direito);
        campoRotacaoMedialPassivoDir = findViewById(R.id.activity_secao_amplitude_movimento_ombro_rotacao_medial_passivo_direito);
        campoRotacaoMedialAtivoEsq = findViewById(R.id.activity_secao_amplitude_movimento_ombro_rotacao_medial_ativo_esquerdo);
        campoRotacaoMedialPassivoEsq = findViewById(R.id.activity_secao_amplitude_movimento_ombro_rotacao_medial_passivo_esquerdo);
        campoRotacaoLateralAtivoDir = findViewById(R.id.activity_secao_amplitude_movimento_ombro_rotacao_lateral_ativo_direito);
        campoRotacaoLateralPassivoDir = findViewById(R.id.activity_secao_amplitude_movimento_ombro_rotacao_lateral_passivo_direito);
        campoRotacaoLateralAtivoEsq = findViewById(R.id.activity_secao_amplitude_movimento_ombro_rotacao_lateral_ativo_esquerdo);
        campoRotacaoLateralPassivoEsq = findViewById(R.id.activity_secao_amplitude_movimento_ombro_rotacao_lateral_passivo_esquerdo);
        campoAlteracaoRitmoEscapuloUmeral = findViewById(R.id.activity_secao_amplitude_movimento_ombro_alteracao_ritmo_escapulo_umeral);
        campoGrauAlteracaoI = findViewById(R.id.activity_secao_amplitude_movimento_ombro_grau_i_ritmo_escapulo_umeral);
        campoGrauAlteracaoII = findViewById(R.id.activity_secao_amplitude_movimento_ombro_grau_ii_ritmo_escapulo_umeral);
        campoGrauAlteracaoIII = findViewById(R.id.activity_secao_amplitude_movimento_ombro_grau_iii_ritmo_escapulo_umeral);

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
        campoRotacaoMedialAtivoDir.setText(ombro.getRotacaoMedDirAtivo());
        campoRotacaoMedialPassivoDir.setText(ombro.getRotacaoMedDirPassivo());
        campoRotacaoMedialAtivoEsq.setText(ombro.getRotacaoMedEsqAtivo());
        campoRotacaoMedialPassivoEsq.setText(ombro.getRotacaoMedEsqPassivo());
        campoRotacaoLateralAtivoDir.setText(ombro.getRotacaoLatDirAtivo());
        campoRotacaoLateralPassivoDir.setText(ombro.getRotacaoLatDirPassivo());
        campoRotacaoLateralAtivoEsq.setText(ombro.getRotacaoLatEsqAtivo());
        campoRotacaoLateralPassivoEsq.setText(ombro.getRotacaoLatEsqPassivo());
        campoAlteracaoRitmoEscapuloUmeral.setChecked(ombro.getAlteracaoRitmoEscapuloUmeral());
        campoGrauAlteracaoI.setChecked(ombro.getGrauI());
        campoGrauAlteracaoII.setChecked(ombro.getGrauII());
        campoGrauAlteracaoIII.setChecked(ombro.getGrauIII());

    }
}
