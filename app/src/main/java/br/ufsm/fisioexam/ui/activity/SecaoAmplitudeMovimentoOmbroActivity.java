package br.ufsm.fisioexam.ui.activity;

import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_EXAME;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_SECAO;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import br.ufsm.fisioexam.R;
import br.ufsm.fisioexam.database.FisioExamDatabase;
import br.ufsm.fisioexam.database.dao.OmbroDAO;
import br.ufsm.fisioexam.database.dao.SecoesDAO;
import br.ufsm.fisioexam.database.thread.QueryManager;
import br.ufsm.fisioexam.model.Ombro;
import br.ufsm.fisioexam.model.Secoes;

public class SecaoAmplitudeMovimentoOmbroActivity extends AppCompatActivity {
    //Usar Parâmetros MAGEE (2010) por ser mais recente.

    private EditText campoFlexaoDir;
    private EditText campoFlexaoEsq;
    private EditText campoExtensaoDir;
    private EditText campoExtensaoEsq;
    private EditText campoAbducaoDir;
    private EditText campoAbducaoEsq;
    private EditText campoAducaoHorizontalDir;
    private EditText campoAducaoHorizontalEsq;

    private EditText campoRotacaoMedialDir;
    private EditText campoRotacaoMedialEsq;
    private EditText campoRotacaoLateralDir;
    private EditText campoRotacaoLateralEsq;
    private SwitchCompat campoAlteracaoRitmoEscapuloUmeral;
    private CheckBox campoGrauAlteracaoI;
    private CheckBox campoGrauAlteracaoII;
    private CheckBox campoGrauAlteracaoIII;

    private ImageButton ajudaFlexao;
    private ImageButton ajudaExtensao;
    private ImageButton ajudaAbducao;
    private ImageButton ajudaAducaoHorizontal;
    private ImageButton ajudaRotacaoMedial;
    private ImageButton ajudaRotacaoLateral;


    private Button proximo;
    private Button salvarESair;

    private Ombro ombro;
    private OmbroDAO ombroDao;
    private QueryManager<Ombro> ombroQueryManager;
    private Secoes secoes;
    private SecoesDAO secoesDao;
    private QueryManager<Secoes> secoesQueryManager;


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


    private void inicializaDaos() {
        FisioExamDatabase database = FisioExamDatabase.getInstance(this);
        ombroDao = database.getRoomOmbroDAO();
        secoesDao = database.getRoomSecoesDAO();
        ombroQueryManager = new QueryManager<>();
        secoesQueryManager = new QueryManager<>();
    }

    private void inicializaBotoes() {

        proximo = findViewById(R.id.activity_secao_amplitude_movimento_ombro_button_proximo);
        salvarESair = findViewById(R.id.activity_secao_amplitude_movimento_ombro_button_salvar_e_sair);

        ajudaFlexao = findViewById(R.id.activity_secao_amplitude_movimento_ombro_button_help_flexao);
        ajudaExtensao = findViewById(R.id.activity_secao_amplitude_movimento_ombro_button_help_extensao);
        ajudaAbducao = findViewById(R.id.activity_secao_amplitude_movimento_ombro_button_help_abducao);
        ajudaAducaoHorizontal = findViewById(R.id.activity_secao_amplitude_movimento_ombro_button_help_aducao_horizontal);
        ajudaRotacaoLateral = findViewById(R.id.activity_secao_amplitude_movimento_ombro_button_help_rotacao_lateral);
        ajudaRotacaoMedial = findViewById(R.id.activity_secao_amplitude_movimento_ombro_button_help_rotacao_medial);

        configuraListenersDeClique();
    }

    private void configuraListenersDeClique() {
        proximo.setOnClickListener(v -> proximoForm());

        salvarESair.setOnClickListener(v -> finalizaForm());

        ajudaFlexao.setOnClickListener(v -> vaiParaAjuda(AjudaAmplitudeMovimentoOmbroFlexaoActivity.class));
        ajudaAbducao.setOnClickListener(v -> vaiParaAjuda(AjudaAmplitudeMovimentoOmbroAbducaoActivity.class));
        ajudaAducaoHorizontal.setOnClickListener(v -> vaiParaAjuda(AjudaAmplitudeMovimentoOmbroAducaoHorizontalActivity.class));
        ajudaExtensao.setOnClickListener(v -> vaiParaAjuda(AjudaAmplitudeMovimentoOmbroExtensaoActivity.class));
        ajudaRotacaoLateral.setOnClickListener(v -> vaiParaAjuda(AjudaAmplitudeMovimentoOmbroRotacaoLateralActivity.class));
        ajudaRotacaoMedial.setOnClickListener(v -> vaiParaAjuda(AjudaAmplitudeMovimentoOmbroRotacaoMedialActivity.class));
    }

    private void vaiParaAjuda(Class<?> classe) {
        Intent vaiParaAjudaActivity = new Intent(this, classe);
        startActivity(vaiParaAjudaActivity);
    }


    private void salva() {
        secoes.setAmplitudeMovimento(true);
        secoesQueryManager.update(secoes, secoesDao);

        ombro.setFlexaoDir(campoFlexaoDir.getText().toString());
        ombro.setFlexaoDir(campoFlexaoDir.getText().toString());
        ombro.setFlexaoEsq(campoFlexaoEsq.getText().toString());
        ombro.setFlexaoEsq(campoFlexaoEsq.getText().toString());
        ombro.setExtensaoDir(campoExtensaoDir.getText().toString());
        ombro.setExtensaoDir(campoExtensaoDir.getText().toString());
        ombro.setExtensaoEsq(campoExtensaoEsq.getText().toString());
        ombro.setExtensaoEsq(campoExtensaoEsq.getText().toString());
        ombro.setAbducaoDir(campoAbducaoDir.getText().toString());
        ombro.setAbducaoDir(campoAbducaoDir.getText().toString());
        ombro.setAbducaoEsq(campoAbducaoEsq.getText().toString());
        ombro.setAbducaoEsq(campoAbducaoEsq.getText().toString());
        ombro.setAducaoHorDir(campoAducaoHorizontalDir.getText().toString());
        ombro.setAducaoHorDir(campoAducaoHorizontalDir.getText().toString());
        ombro.setAducaoHorEsq(campoAducaoHorizontalEsq.getText().toString());
        ombro.setAducaoHorEsq(campoAducaoHorizontalEsq.getText().toString());
        ombro.setRotacaoMedDir(campoRotacaoMedialDir.getText().toString());
        ombro.setRotacaoMedDir(campoRotacaoMedialDir.getText().toString());
        ombro.setRotacaoMedEsq(campoRotacaoMedialEsq.getText().toString());
        ombro.setRotacaoMedEsq(campoRotacaoMedialEsq.getText().toString());
        ombro.setRotacaoLatDir(campoRotacaoLateralDir.getText().toString());
        ombro.setRotacaoLatDir(campoRotacaoLateralDir.getText().toString());
        ombro.setRotacaoLatEsq(campoRotacaoLateralEsq.getText().toString());
        ombro.setRotacaoLatEsq(campoRotacaoLateralEsq.getText().toString());
        ombro.setAlteracaoRitmoEscapuloUmeral(campoAlteracaoRitmoEscapuloUmeral.isChecked());
        ombro.setGrauI(campoGrauAlteracaoI.isChecked());
        ombro.setGrauII(campoGrauAlteracaoII.isChecked());
        ombro.setGrauIII(campoGrauAlteracaoIII.isChecked());

        ombroQueryManager.update(ombro, ombroDao);
    }


    private void carregaExame() {
        Intent dados = getIntent();

        if (dados.hasExtra(CHAVE_EXAME)) {
            ombro = ombroQueryManager.getOne((String) dados.getSerializableExtra(CHAVE_EXAME), ombroDao);
            secoes = secoesQueryManager.getOne(ombro.getExame(), secoesDao);
        }
    }


    private void inicializaFormulario() {
        campoFlexaoDir = findViewById(R.id.activity_secao_amplitude_movimento_ombro_flexao_direito);
        campoFlexaoEsq = findViewById(R.id.activity_secao_amplitude_movimento_ombro_flexao_esquerdo);
        campoExtensaoDir = findViewById(R.id.activity_secao_amplitude_movimento_ombro_extensao_direito);
        campoExtensaoEsq = findViewById(R.id.activity_secao_amplitude_movimento_ombro_extensao_esquerdo);
        campoAbducaoDir = findViewById(R.id.activity_secao_amplitude_movimento_ombro_abducao_direito);
        campoAbducaoEsq = findViewById(R.id.activity_secao_amplitude_movimento_ombro_abducao_esquerdo);
        campoAducaoHorizontalDir = findViewById(R.id.activity_secao_amplitude_movimento_ombro_aducao_horizontal_direito);
        campoAducaoHorizontalEsq = findViewById(R.id.activity_secao_amplitude_movimento_ombro_aducao_horizontal_esquerdo);

        campoRotacaoMedialDir = findViewById(R.id.activity_secao_amplitude_movimento_ombro_rotacao_medial_direito);
        campoRotacaoMedialEsq = findViewById(R.id.activity_secao_amplitude_movimento_ombro_rotacao_medial_esquerdo);
        campoRotacaoLateralDir = findViewById(R.id.activity_secao_amplitude_movimento_ombro_rotacao_lateral_direito);
        campoRotacaoLateralEsq = findViewById(R.id.activity_secao_amplitude_movimento_ombro_rotacao_lateral_esquerdo);
        campoAlteracaoRitmoEscapuloUmeral = findViewById(R.id.activity_secao_amplitude_movimento_ombro_alteracao_ritmo_escapulo_umeral);
        campoGrauAlteracaoI = findViewById(R.id.activity_secao_amplitude_movimento_ombro_grau_i_ritmo_escapulo_umeral);
        campoGrauAlteracaoII = findViewById(R.id.activity_secao_amplitude_movimento_ombro_grau_ii_ritmo_escapulo_umeral);
        campoGrauAlteracaoIII = findViewById(R.id.activity_secao_amplitude_movimento_ombro_grau_iii_ritmo_escapulo_umeral);

        if (secoes.isAmplitudeMovimento()) {
            preencheCampos();
        }
    }


    private void preencheCampos() {

        campoFlexaoDir.setText(ombro.getFlexaoDir());
        campoFlexaoDir.setText(ombro.getFlexaoDir());
        campoFlexaoEsq.setText(ombro.getFlexaoEsq());
        campoFlexaoEsq.setText(ombro.getFlexaoEsq());
        campoExtensaoDir.setText(ombro.getExtensaoDir());
        campoExtensaoDir.setText(ombro.getExtensaoDir());
        campoExtensaoEsq.setText(ombro.getExtensaoEsq());
        campoExtensaoEsq.setText(ombro.getExtensaoEsq());
        campoAbducaoDir.setText(ombro.getAbducaoDir());
        campoAbducaoDir.setText(ombro.getAbducaoDir());
        campoAbducaoEsq.setText(ombro.getAbducaoEsq());
        campoAbducaoEsq.setText(ombro.getAbducaoEsq());
        campoAducaoHorizontalDir.setText(ombro.getAducaoHorDir());
        campoAducaoHorizontalDir.setText(ombro.getAducaoHorDir());
        campoAducaoHorizontalEsq.setText(ombro.getAducaoHorEsq());
        campoAducaoHorizontalEsq.setText(ombro.getAducaoHorEsq());

        campoRotacaoMedialDir.setText(ombro.getRotacaoMedDir());
        campoRotacaoMedialDir.setText(ombro.getRotacaoMedDir());
        campoRotacaoMedialEsq.setText(ombro.getRotacaoMedEsq());
        campoRotacaoMedialEsq.setText(ombro.getRotacaoMedEsq());
        campoRotacaoLateralDir.setText(ombro.getRotacaoLatDir());
        campoRotacaoLateralDir.setText(ombro.getRotacaoLatDir());
        campoRotacaoLateralEsq.setText(ombro.getRotacaoLatEsq());
        campoRotacaoLateralEsq.setText(ombro.getRotacaoLatEsq());
        campoAlteracaoRitmoEscapuloUmeral.setChecked(ombro.getAlteracaoRitmoEscapuloUmeral());
        campoGrauAlteracaoI.setChecked(ombro.getGrauI());
        campoGrauAlteracaoII.setChecked(ombro.getGrauII());
        campoGrauAlteracaoIII.setChecked(ombro.getGrauIII());

    }


    private void inicializaOtimizadores() {
        configuraListenerDeMarcacao();
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


    private void proximoForm() {
        salva();
        Intent vaiParaFormularioSecaoActivity = new Intent(this, IntermediarioSecoesEspecificasActivity.class);
        vaiParaFormularioSecaoActivity.putExtra(CHAVE_EXAME, ombro.getExame());
        vaiParaFormularioSecaoActivity.putExtra(CHAVE_SECAO, 2);
        startActivity(vaiParaFormularioSecaoActivity);
        finish();
    }


}
