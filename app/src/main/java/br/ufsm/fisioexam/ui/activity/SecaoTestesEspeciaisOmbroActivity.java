package br.ufsm.fisioexam.ui.activity;

import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_EXAME;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_SECAO;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import br.ufsm.fisioexam.R;
import br.ufsm.fisioexam.database.FisioExamDatabase;
import br.ufsm.fisioexam.database.dao.OmbroDAO;
import br.ufsm.fisioexam.database.dao.SecoesDAO;
import br.ufsm.fisioexam.model.Ombro;
import br.ufsm.fisioexam.model.Secoes;

public class SecaoTestesEspeciaisOmbroActivity extends AppCompatActivity {
    private Ombro ombro;
    private OmbroDAO ombroDao;
    private Secoes secoes;
    private SecoesDAO secoesDao;

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
    private ImageButton buttonHelpDash;
    private ImageButton buttonHelpAses;

    private Button buttonSalvar;
    private Button buttonProximo;

    private Calendar dataDash;
    private Calendar dataAses;

    private EditText campoDataDash;
    private EditText campoPontoDash;
    private EditText campoResultDash;
    private EditText campoDataAses;
    private EditText campoPontoAses;
    private EditText campoResultAses;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secao_testes_especiais_ombro);
        inicializaCalendars();
        inicializaDAOS();
        carregaExame();
        inicializaBotoes();
        inicializaFormulario();
        setListenerCalendariosDatas();
    }

    private void inicializaCalendars() {
        dataDash = Calendar.getInstance();
        dataAses = Calendar.getInstance();
    }

    private void inicializaBotoes() {
        buttonProximo = findViewById(R.id.activity_secao_testes_especiais_ombro_button_proximo);
        buttonSalvar = findViewById(R.id.activity_secao_testes_especiais_ombro_button_salvar_e_sair);

        buttonHelpApreensao = findViewById(R.id.activity_secao_testes_especiais_ombro_apreensao_anterior_button_help);
        buttonHelpSinalSulco = findViewById(R.id.activity_secao_testes_especiais_ombro_sinal_sulco_button_help);
        buttonHelpDash = findViewById(R.id.activity_secao_testes_especiais_ombro_dash_button_help);
        buttonHelpAses = findViewById(R.id.activity_secao_testes_especiais_ombro_ases_button_help);

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
        buttonHelpDash.setOnClickListener(v -> vaiParaSecaoAjuda(AjudaTestesEspeciaisDashOmbroActivity.class));
        buttonHelpAses.setOnClickListener(v -> vaiParaSecaoAjuda(AjudaTestesEspeciaisAsesOmbroActivity.class));

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
        secoesDao.edita(secoes);



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


        ombroDao.edita(ombro);
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





        campoDataDash = findViewById(R.id.activity_secao_testes_especiais_ombro_dash_data);
        campoPontoDash = findViewById(R.id.activity_secao_testes_especiais_ombro_dash_pontuacao);
        campoResultDash = findViewById(R.id.activity_secao_testes_especiais_ombro_dash_resultados);
        campoDataAses = findViewById(R.id.activity_secao_testes_especiais_ombro_ases_data);
        campoPontoAses = findViewById(R.id.activity_secao_testes_especiais_ombro_ases_pontuacao);
        campoResultAses = findViewById(R.id.activity_secao_testes_especiais_ombro_ases_resultados);

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

            dataDash.setTimeInMillis(ombro.getDashData());
            dataAses.setTimeInMillis(ombro.getAsesData());
            atualizaDataDash();
            campoPontoDash.setText(ombro.getDashPontuacao());
            campoResultDash.setText(ombro.getDashResultados());
            atualizaDataAses();
            campoPontoAses.setText(ombro.getAsesPontuacao());
            campoResultAses.setText(ombro.getAsesResultados());
        }
    }


    private void carregaExame() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_EXAME)) {
            ombro = ombroDao.getOmbro((String) dados.getSerializableExtra(CHAVE_EXAME));
            secoes = secoesDao.getSecao(ombro.getExame());
        }
    }

    private void inicializaDAOS() {
        FisioExamDatabase database = FisioExamDatabase.getInstance(this);
        ombroDao = database.getRoomOmbroDAO();
        secoesDao = database.getRoomSecoesDAO();
    }



    private void setListenerCalendariosDatas() {

        DatePickerDialog.OnDateSetListener dateDash = instanciaSeletorData(dataDash);
        DatePickerDialog.OnDateSetListener dateAses = instanciaSeletorData(dataAses);


        atualizaDataAses();
        atualizaDataDash();

        campoDataDash.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                new DatePickerDialog(SecaoTestesEspeciaisOmbroActivity.this, dateDash,
                        dataDash.get(Calendar.YEAR),
                        dataDash.get(Calendar.MONTH),
                        dataDash.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        campoDataAses.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                new DatePickerDialog(SecaoTestesEspeciaisOmbroActivity.this, dateAses,
                        dataAses.get(Calendar.YEAR),
                        dataAses.get(Calendar.MONTH),
                        dataAses.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private DatePickerDialog.OnDateSetListener instanciaSeletorData(Calendar data) {
        return (view, year, month, dayOfMonth) -> {
            data.set(Calendar.YEAR, year);
            data.set(Calendar.MONTH, month);
            data.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        };
    }

    private void atualizaDataDash() {
        String formatoData = "dd/MM/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatoData, new Locale("pt", "BR"));
        campoDataDash.setText(dateFormat.format(dataDash.getTime()));
    }

    private void atualizaDataAses() {
        String formatoData = "dd/MM/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatoData, new Locale("pt", "BR"));
        campoDataAses.setText(dateFormat.format(dataAses.getTime()));
    }


}
