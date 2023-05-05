package br.ufsm.fisioexam.ui.activity;

import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_DIR_MAIS;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_DIR_MENOS;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_ESQ_MAIS;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_ESQ_MENOS;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_EXAME;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_SECAO;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import br.ufsm.fisioexam.R;
import br.ufsm.fisioexam.database.FisioExamDatabase;
import br.ufsm.fisioexam.database.dao.CotoveloDAO;
import br.ufsm.fisioexam.database.dao.SecoesDAO;
import br.ufsm.fisioexam.model.Cotovelo;
import br.ufsm.fisioexam.model.Secoes;

public class SecaoTestesEspeciaisCotoveloActivity extends AppCompatActivity {
    private Cotovelo cotovelo;
    private CotoveloDAO cotoveloDao;
    private Secoes secoes;
    private SecoesDAO secoesDao;

    private Button buttonSalvar;
    private Button buttonProximo;

    private Calendar dataDash;
    private Calendar dataAses;

    private RadioGroup radioCozen;
    private RadioGroup radioCotoveloGolfista;
    private RadioGroup radioSinalTinel;
    private RadioGroup radioLcl;
    private RadioGroup radioLcm;


    private EditText campoDataDash;
    private EditText campoPontoDash;
    private EditText campoResultDash;
    private EditText campoDataAses;
    private EditText campoPontoAses;
    private EditText campoResultAses;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secao_testes_especiais_cotovelo);
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
        secoesDao.edita(secoes);

        setaCozen();
        setaCotoveloGolfista();
        setaSinalTinel();
        setaLcl();
        setaLcm();

        cotovelo.setDashData(dataDash.getTimeInMillis());
        cotovelo.setDashPontuacao(campoPontoDash.getText().toString());
        cotovelo.setDashResultados(campoResultDash.getText().toString());

        cotovelo.setAsesData(dataAses.getTimeInMillis());
        cotovelo.setAsesPontuacao(campoPontoAses.getText().toString());
        cotovelo.setAsesResultados(campoResultAses.getText().toString());


        cotoveloDao.edita(cotovelo);
    }

    private void setListenerCalendariosDatas() {

        DatePickerDialog.OnDateSetListener dateDash = instanciaSeletorData(dataDash);
        DatePickerDialog.OnDateSetListener dateAses = instanciaSeletorData(dataAses);


        atualizaDataAses();
        atualizaDataDash();

        campoDataDash.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                new DatePickerDialog(SecaoTestesEspeciaisCotoveloActivity.this, dateDash,
                        dataDash.get(Calendar.YEAR),
                        dataDash.get(Calendar.MONTH),
                        dataDash.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        campoDataAses.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                new DatePickerDialog(SecaoTestesEspeciaisCotoveloActivity.this, dateAses,
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

    private void setaCozen() {
        String idCozen;
        
            if(radioCozen.getCheckedRadioButtonId() ==R.id.activity_secao_testes_especiais_cotovelo_radio_direita_mais_cozen){
                idCozen = CHAVE_DIR_MAIS;
                } else
            if(radioCozen.getCheckedRadioButtonId() ==R.id.activity_secao_testes_especiais_cotovelo_radio_direita_menos_cozen){
                idCozen = CHAVE_DIR_MENOS;
                } else
            if(radioCozen.getCheckedRadioButtonId() ==R.id.activity_secao_testes_especiais_cotovelo_radio_esquerda_mais_cozen){
                idCozen = CHAVE_ESQ_MAIS;
                } else
            if(radioCozen.getCheckedRadioButtonId() ==R.id.activity_secao_testes_especiais_cotovelo_radio_esquerda_menos_cozen){
                idCozen = CHAVE_ESQ_MENOS;
                } else
            {
                idCozen = "";
        }
        cotovelo.setTesteCozen(idCozen);
    }

    private void setaCotoveloGolfista() {
        String idCotoveloGolfista;
            if(radioCotoveloGolfista.getCheckedRadioButtonId() ==R.id.activity_secao_testes_especiais_cotovelo_radio_direita_mais_teste_golfista){
                idCotoveloGolfista = CHAVE_DIR_MAIS;
                } else
            if(radioCotoveloGolfista.getCheckedRadioButtonId() ==R.id.activity_secao_testes_especiais_cotovelo_radio_direita_menos_teste_golfista){
                idCotoveloGolfista = CHAVE_DIR_MENOS;
                } else
            if(radioCotoveloGolfista.getCheckedRadioButtonId() ==R.id.activity_secao_testes_especiais_cotovelo_radio_esquerda_mais_teste_golfista){
                idCotoveloGolfista = CHAVE_ESQ_MAIS;
                } else
            if(radioCotoveloGolfista.getCheckedRadioButtonId() ==R.id.activity_secao_testes_especiais_cotovelo_radio_esquerda_menos_teste_golfista){
                idCotoveloGolfista = CHAVE_ESQ_MENOS;
                } else
            {
                idCotoveloGolfista = "";
        }
        cotovelo.setTesteCotoveloGolfista(idCotoveloGolfista);
    }

    private void setaSinalTinel() {
        String idSinalTinel;
            if(radioSinalTinel.getCheckedRadioButtonId() ==R.id.activity_secao_testes_especiais_cotovelo_radio_direita_mais_tinel){
                idSinalTinel = CHAVE_DIR_MAIS;
                } else
            if(radioSinalTinel.getCheckedRadioButtonId() ==R.id.activity_secao_testes_especiais_cotovelo_radio_direita_menos_tinel){
                idSinalTinel = CHAVE_DIR_MENOS;
                } else
            if(radioSinalTinel.getCheckedRadioButtonId() ==R.id.activity_secao_testes_especiais_cotovelo_radio_esquerda_mais_tinel){
                idSinalTinel = CHAVE_ESQ_MAIS;
                } else
            if(radioSinalTinel.getCheckedRadioButtonId() ==R.id.activity_secao_testes_especiais_cotovelo_radio_esquerda_menos_tinel){
                idSinalTinel = CHAVE_ESQ_MENOS;
                } else
            {
                idSinalTinel = "";
        }
        cotovelo.setSinalTinel(idSinalTinel);
    }

    private void setaLcl() {
        String idLcl;
            if(radioLcl.getCheckedRadioButtonId() ==R.id.activity_secao_testes_especiais_cotovelo_radio_direita_mais_lcl){
                idLcl = CHAVE_DIR_MAIS;
                } else
            if(radioLcl.getCheckedRadioButtonId() ==R.id.activity_secao_testes_especiais_cotovelo_radio_direita_menos_lcl){
                idLcl = CHAVE_DIR_MENOS;
                } else
            if(radioLcl.getCheckedRadioButtonId() ==R.id.activity_secao_testes_especiais_cotovelo_radio_esquerda_mais_lcl){
                idLcl = CHAVE_ESQ_MAIS;
                } else
            if(radioLcl.getCheckedRadioButtonId() ==R.id.activity_secao_testes_especiais_cotovelo_radio_esquerda_menos_lcl){
                idLcl = CHAVE_ESQ_MENOS;
                } else
            {
                idLcl = "";
        }
        cotovelo.setTesteEsforcoVaro(idLcl);
    }

    private void setaLcm() {
        String idLcm;
            if(radioLcm.getCheckedRadioButtonId() ==R.id.activity_secao_testes_especiais_cotovelo_radio_direita_mais_lcm){
                idLcm = CHAVE_DIR_MAIS;
                } else
            if(radioLcm.getCheckedRadioButtonId() ==R.id.activity_secao_testes_especiais_cotovelo_radio_direita_menos_lcm){
                idLcm = CHAVE_DIR_MENOS;
                } else
            if(radioLcm.getCheckedRadioButtonId() ==R.id.activity_secao_testes_especiais_cotovelo_radio_esquerda_mais_lcm){
                idLcm = CHAVE_ESQ_MAIS;
                } else
            if(radioLcm.getCheckedRadioButtonId() ==R.id.activity_secao_testes_especiais_cotovelo_radio_esquerda_menos_lcm){
                idLcm = CHAVE_ESQ_MENOS;
                } else
            {
                idLcm = "";
        }
        cotovelo.setTesteEsforcoValgo(idLcm);

    }

    private void inicializaFormulario() {
        radioCozen = findViewById(R.id.activity_secao_testes_especiais_cotovelo_radio_cozen);
        radioCotoveloGolfista = findViewById(R.id.activity_secao_testes_especiais_cotovelo_radio_teste_golfista);
        radioSinalTinel = findViewById(R.id.activity_secao_testes_especiais_cotovelo_radio_tinel);
        radioLcl = findViewById(R.id.activity_secao_testes_especiais_cotovelo_radio_lcl);
        radioLcm = findViewById(R.id.activity_secao_testes_especiais_cotovelo_radio_lcm);

        campoDataDash = findViewById(R.id.activity_secao_testes_especiais_cotovelo_dash_data);
        campoPontoDash = findViewById(R.id.activity_secao_testes_especiais_cotovelo_dash_pontuacao);
        campoResultDash = findViewById(R.id.activity_secao_testes_especiais_cotovelo_dash_resultados);
        campoDataAses = findViewById(R.id.activity_secao_testes_especiais_cotovelo_ases_data);
        campoPontoAses = findViewById(R.id.activity_secao_testes_especiais_cotovelo_ases_pontuacao);
        campoResultAses = findViewById(R.id.activity_secao_testes_especiais_cotovelo_ases_resultados);

        preencheFormulario();
    }

    private void preencheFormulario() {
        if(secoes.isTestesEspeciais()){
            dataDash.setTimeInMillis(cotovelo.getDashData());
            dataAses.setTimeInMillis(cotovelo.getAsesData());

            getDadosRadio(cotovelo.getTesteCozen(),
                    R.id.activity_secao_testes_especiais_cotovelo_radio_direita_mais_cozen,
                    R.id.activity_secao_testes_especiais_cotovelo_radio_direita_menos_cozen,
                    R.id.activity_secao_testes_especiais_cotovelo_radio_esquerda_mais_cozen,
                    R.id.activity_secao_testes_especiais_cotovelo_radio_esquerda_menos_cozen,
                    radioCozen);

            getDadosRadio(cotovelo.getTesteCotoveloGolfista(),
                    R.id.activity_secao_testes_especiais_cotovelo_radio_direita_mais_teste_golfista,
                    R.id.activity_secao_testes_especiais_cotovelo_radio_direita_menos_teste_golfista,
                    R.id.activity_secao_testes_especiais_cotovelo_radio_esquerda_mais_teste_golfista,
                    R.id.activity_secao_testes_especiais_cotovelo_radio_esquerda_menos_teste_golfista,
                    radioCotoveloGolfista);

            getDadosRadio(cotovelo.getSinalTinel(),
                    R.id.activity_secao_testes_especiais_cotovelo_radio_direita_mais_tinel,
                    R.id.activity_secao_testes_especiais_cotovelo_radio_direita_menos_tinel,
                    R.id.activity_secao_testes_especiais_cotovelo_radio_esquerda_mais_tinel,
                    R.id.activity_secao_testes_especiais_cotovelo_radio_esquerda_menos_tinel,
                    radioSinalTinel);

            getDadosRadio(cotovelo.getTesteEsforcoVaro(),
                    R.id.activity_secao_testes_especiais_cotovelo_radio_direita_mais_lcl,
                    R.id.activity_secao_testes_especiais_cotovelo_radio_direita_menos_lcl,
                    R.id.activity_secao_testes_especiais_cotovelo_radio_esquerda_mais_lcl,
                    R.id.activity_secao_testes_especiais_cotovelo_radio_esquerda_menos_lcl,
                    radioLcl);

            getDadosRadio(cotovelo.getTesteEsforcoValgo(),
                    R.id.activity_secao_testes_especiais_cotovelo_radio_direita_mais_lcm,
                    R.id.activity_secao_testes_especiais_cotovelo_radio_direita_menos_lcm,
                    R.id.activity_secao_testes_especiais_cotovelo_radio_esquerda_mais_lcm,
                    R.id.activity_secao_testes_especiais_cotovelo_radio_esquerda_menos_lcm,
                    radioLcm);

            atualizaDataDash();
            campoPontoDash.setText(cotovelo.getDashPontuacao());
            campoResultDash.setText(cotovelo.getDashResultados());
            atualizaDataAses();
            campoPontoAses.setText(cotovelo.getAsesPontuacao());
            campoResultAses.setText(cotovelo.getAsesResultados());

        }
    }

    private void getDadosRadio(@Nullable String radioChecked, int idDM, int idDm, int idEM, int idEm, RadioGroup radio) {
        if(radioChecked != null){
            switch (radioChecked){
                case CHAVE_DIR_MAIS:
                    radio.check(idDM);
                    break;
                case CHAVE_DIR_MENOS:
                    radio.check(idDm);
                    break;
                case CHAVE_ESQ_MAIS:
                    radio.check(idEM);
                    break;
                case CHAVE_ESQ_MENOS:
                    radio.check(idEm);
                    break;
            }
        }
    }

    private void carregaExame() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_EXAME)) {
            cotovelo = cotoveloDao.getCotovelo((String) dados.getSerializableExtra(CHAVE_EXAME));
            secoes = secoesDao.getSecao(cotovelo.getExame());
        }
    }

    private void inicializaDAOS() {
        FisioExamDatabase database = FisioExamDatabase.getInstance(this);
        cotoveloDao = database.getRoomCotoveloDAO();
        secoesDao = database.getRoomSecoesDAO();
    }
}
