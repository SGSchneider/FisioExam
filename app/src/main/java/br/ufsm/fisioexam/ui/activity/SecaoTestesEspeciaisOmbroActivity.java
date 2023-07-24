package br.ufsm.fisioexam.ui.activity;

import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_EXAME;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_SECAO;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.ufsm.fisioexam.R;
import br.ufsm.fisioexam.database.FisioExamDatabase;
import br.ufsm.fisioexam.database.dao.OmbroDAO;
import br.ufsm.fisioexam.database.dao.SecoesDAO;
import br.ufsm.fisioexam.database.thread.QueryManager;
import br.ufsm.fisioexam.model.Ombro;
import br.ufsm.fisioexam.model.Secoes;

public class SecaoTestesEspeciaisOmbroActivity extends AppCompatActivity {
    private Ombro ombro;
    private OmbroDAO ombroDao;
    private QueryManager<Ombro> ombroQueryManager;
    private Secoes secoes;
    private SecoesDAO secoesDao;
    private QueryManager<Secoes> secoesQueryManager;

    private CheckBox campoJobeDir;
    private CheckBox campoJobeEsq;
    private CheckBox campoPatteDir;
    private CheckBox campoPatteEsq;
    private CheckBox campoGerberDir;
    private CheckBox campoGerberEsq;
    private CheckBox campoNeerDir;
    private CheckBox campoNeerEsq;
    private CheckBox campoHawkinsDir;
    private CheckBox campoHawkinsEsq;
    private CheckBox campoSpeedDir;
    private CheckBox campoSpeedEsq;
    private CheckBox campoYergasonDir;
    private CheckBox campoYergasonEsq;
    private CheckBox campoApreensaoDir;
    private CheckBox campoApreensaoEsq;
    private CheckBox campoSinalSulcoDir;
    private CheckBox campoSinalSulcoEsq;
    private ImageButton buttonHelpJobe;
    private ImageButton buttonHelpPatte;
    private ImageButton buttonHelpGerber;
    private ImageButton buttonHelpNeer;
    private ImageButton buttonHelpHawkins;
    private ImageButton buttonHelpSpeed;
    private ImageButton buttonHelpYergason;
    private ImageButton buttonHelpApreensao;
    private ImageButton buttonHelpSinalSulco;

    private Button buttonSalvar;
    private Button buttonProximo;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secao_testes_especiais_ombro);
        inicializaDAOS();
        carregaExame();
        inicializaBotoes();
        inicializaFormulario();
    }


    private void inicializaBotoes() {
        buttonProximo = findViewById(R.id.activity_secao_testes_especiais_ombro_button_proximo);
        buttonSalvar = findViewById(R.id.activity_secao_testes_especiais_ombro_button_salvar_e_sair);

        buttonHelpApreensao = findViewById(R.id.activity_secao_testes_especiais_ombro_apreensao_anterior_button_help);
        buttonHelpSinalSulco = findViewById(R.id.activity_secao_testes_especiais_ombro_sinal_sulco_button_help);

        buttonHelpJobe = findViewById(R.id.activity_secao_testes_especiais_ombro_jobe_button_help);
        buttonHelpPatte = findViewById(R.id.activity_secao_testes_especiais_ombro_patte_button_help);
        buttonHelpGerber = findViewById(R.id.activity_secao_testes_especiais_ombro_gerber_button_help);
        buttonHelpNeer = findViewById(R.id.activity_secao_testes_especiais_ombro_neer_button_help);
        buttonHelpHawkins = findViewById(R.id.activity_secao_testes_especiais_ombro_hawkins_button_help);
        buttonHelpSpeed = findViewById(R.id.activity_secao_testes_especiais_ombro_speed_button_help);
        buttonHelpYergason = findViewById(R.id.activity_secao_testes_especiais_ombro_yergason_button_help);

        setListenerBotoes();
    }

    private void setListenerBotoes() {
        buttonProximo.setOnClickListener(v -> proximoForm());
        buttonSalvar.setOnClickListener(v -> salvarESair());

        buttonHelpApreensao.setOnClickListener(v -> vaiParaSecaoAjuda(AjudaTestesEspeciaisOmbroApreensaoActivity.class));
        buttonHelpSinalSulco.setOnClickListener(v -> vaiParaSecaoAjuda(AjudaTestesEspeciaisOmbroSinalSulcoActivity.class));

        buttonHelpJobe.setOnClickListener(v -> vaiParaSecaoAjuda(AjudaTestesEspeciaisOmbroJobeActivity.class));
        buttonHelpPatte.setOnClickListener(v -> vaiParaSecaoAjuda(AjudaTestesEspeciaisOmbroPatteActivity.class));
        buttonHelpGerber.setOnClickListener(v -> vaiParaSecaoAjuda(AjudaTestesEspeciaisOmbroGerberActivity.class));
        buttonHelpNeer.setOnClickListener(v -> vaiParaSecaoAjuda(AjudaTestesEspeciaisOmbroNeerActivity.class));
        buttonHelpHawkins.setOnClickListener(v -> vaiParaSecaoAjuda(AjudaTestesEspeciaisOmbroHawkinsActivity.class));
        buttonHelpSpeed.setOnClickListener(v -> vaiParaSecaoAjuda(AjudaTestesEspeciaisOmbroSpeedActivity.class));
        buttonHelpYergason.setOnClickListener(v -> vaiParaSecaoAjuda(AjudaTestesEspeciaisOmbroYergasonActivity.class));

    }

    private void vaiParaSecaoAjuda(Class<?> classe) {
        Intent vaiParaSecaoAjudaActivity = new Intent(this, classe);
        startActivity(vaiParaSecaoAjudaActivity);
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
        vaiParaFormularioSecaoActivity.putExtra(CHAVE_EXAME, ombro.getExame());
        vaiParaFormularioSecaoActivity.putExtra(CHAVE_SECAO, 6);
        startActivity(vaiParaFormularioSecaoActivity);
        finish();
    }

    private void salva() {
        secoes.setTestesEspeciais(true);
        secoesQueryManager.update(secoes, secoesDao);


        ombro.setJobeDir(campoJobeDir.isChecked());
        ombro.setJobeEsq(campoJobeEsq.isChecked());
        ombro.setPatteDir(campoPatteDir.isChecked());
        ombro.setPatteEsq(campoPatteEsq.isChecked());
        ombro.setGerberLiftOffDir(campoGerberDir.isChecked());
        ombro.setGerberLiftOffEsq(campoGerberEsq.isChecked());
        ombro.setNeerDir(campoNeerDir.isChecked());
        ombro.setNeerEsq(campoNeerEsq.isChecked());
        ombro.setHawkinsDir(campoHawkinsDir.isChecked());
        ombro.setHawkinsEsq(campoHawkinsEsq.isChecked());
        ombro.setPalmUpSpeedDir(campoSpeedDir.isChecked());
        ombro.setPalmUpSpeedEsq(campoSpeedEsq.isChecked());
        ombro.setYergasonDir(campoYergasonDir.isChecked());
        ombro.setYergasonEsq(campoYergasonEsq.isChecked());
        ombro.setApreensaoAnteriorDir(campoApreensaoDir.isChecked());
        ombro.setApreensaoAnteriorEsq(campoApreensaoEsq.isChecked());
        ombro.setSinalSulcoDir(campoSinalSulcoDir.isChecked());
        ombro.setSinalSulcoEsq(campoSinalSulcoEsq.isChecked());


        ombroQueryManager.update(ombro, ombroDao);
    }


    private void inicializaFormulario() {

        campoJobeDir = findViewById(R.id.activity_secao_testes_especiais_ombro_direita_jobe);
        campoJobeEsq = findViewById(R.id.activity_secao_testes_especiais_ombro_esquerda_jobe);
        campoPatteDir = findViewById(R.id.activity_secao_testes_especiais_ombro_direita_patte);
        campoPatteEsq = findViewById(R.id.activity_secao_testes_especiais_ombro_esquerda_patte);
        campoGerberDir = findViewById(R.id.activity_secao_testes_especiais_ombro_direita_gerber);
        campoGerberEsq = findViewById(R.id.activity_secao_testes_especiais_ombro_esquerda_gerber);
        campoNeerDir = findViewById(R.id.activity_secao_testes_especiais_ombro_direita_neer);
        campoNeerEsq = findViewById(R.id.activity_secao_testes_especiais_ombro_esquerda_neer);
        campoHawkinsDir = findViewById(R.id.activity_secao_testes_especiais_ombro_direita_hk);
        campoHawkinsEsq = findViewById(R.id.activity_secao_testes_especiais_ombro_esquerda_hk);
        campoSpeedDir = findViewById(R.id.activity_secao_testes_especiais_ombro_direita_speed);
        campoSpeedEsq = findViewById(R.id.activity_secao_testes_especiais_ombro_esquerda_speed);
        campoYergasonDir = findViewById(R.id.activity_secao_testes_especiais_ombro_direita_yergason);
        campoYergasonEsq = findViewById(R.id.activity_secao_testes_especiais_ombro_esquerda_yergason);
        campoApreensaoDir = findViewById(R.id.activity_secao_testes_especiais_ombro_direita_apreensao_anterior);
        campoApreensaoEsq = findViewById(R.id.activity_secao_testes_especiais_ombro_esquerda_apreensao_anterior);
        campoSinalSulcoDir = findViewById(R.id.activity_secao_testes_especiais_ombro_direita_sinal_de_sulco);
        campoSinalSulcoEsq = findViewById(R.id.activity_secao_testes_especiais_ombro_esquerda_sinal_de_sulco);

        preencheFormulario();
    }

    private void preencheFormulario() {
        if (secoes.isTestesEspeciais()) {

            campoJobeDir.setChecked(ombro.isJobeDir());
            campoJobeEsq.setChecked(ombro.isJobeEsq());
            campoPatteDir.setChecked(ombro.isPatteDir());
            campoPatteEsq.setChecked(ombro.isPatteEsq());
            campoGerberDir.setChecked(ombro.isGerberLiftOffDir());
            campoGerberEsq.setChecked(ombro.isGerberLiftOffEsq());
            campoNeerDir.setChecked(ombro.isNeerDir());
            campoNeerEsq.setChecked(ombro.isNeerEsq());
            campoHawkinsDir.setChecked(ombro.isHawkinsDir());
            campoHawkinsEsq.setChecked(ombro.isHawkinsEsq());
            campoSpeedDir.setChecked(ombro.isPalmUpSpeedDir());
            campoSpeedEsq.setChecked(ombro.isPalmUpSpeedEsq());
            campoYergasonDir.setChecked(ombro.isYergasonDir());
            campoYergasonEsq.setChecked(ombro.isYergasonEsq());
            campoApreensaoDir.setChecked(ombro.isApreensaoAnteriorDir());
            campoApreensaoEsq.setChecked(ombro.isApreensaoAnteriorEsq());
            campoSinalSulcoDir.setChecked(ombro.isSinalSulcoDir());
            campoSinalSulcoEsq.setChecked(ombro.isSinalSulcoEsq());
        }
    }


    private void carregaExame() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_EXAME)) {
            ombro = ombroQueryManager.getOne((String) dados.getSerializableExtra(CHAVE_EXAME), ombroDao);
            secoes = secoesQueryManager.getOne(ombro.getExame(), secoesDao);
        }
    }

    private void inicializaDAOS() {
        FisioExamDatabase database = FisioExamDatabase.getInstance(this);
        ombroDao = database.getRoomOmbroDAO();
        secoesDao = database.getRoomSecoesDAO();
        ombroQueryManager = new QueryManager<>();
        secoesQueryManager = new QueryManager<>();
    }

}
