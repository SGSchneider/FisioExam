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
import br.ufsm.fisioexam.database.dao.OmbroDAO;
import br.ufsm.fisioexam.database.dao.SecoesDAO;
import br.ufsm.fisioexam.model.Ombro;
import br.ufsm.fisioexam.model.Secoes;

public class SecaoTestesEspeciaisOmbroPt2Activity extends AppCompatActivity {
    private Ombro ombro;
    private OmbroDAO ombroDao;
    private Secoes secoes;
    private SecoesDAO secoesDao;

    private Button buttonSalvar;
    private Button buttonProximo;

    private Calendar dataDash;
    private Calendar dataAses;
    private RadioGroup radioApreensao;
    private RadioGroup radioSinalSulco;

    private EditText campoDataDash;
    private EditText campoPontoDash;
    private EditText campoResultDash;
    private EditText campoDataAses;
    private EditText campoPontoAses;
    private EditText campoResultAses;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secao_testes_especiais_ombro_pt1);
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
        vaiParaFormularioSecaoActivity.putExtra(CHAVE_EXAME, ombro.getExame());
        vaiParaFormularioSecaoActivity.putExtra(CHAVE_SECAO, 6);
        startActivity(vaiParaFormularioSecaoActivity);
        finish();
    }

    private void salva() {
        secoes.setTestesEspeciais(true);
        secoesDao.edita(secoes);

        setaApreensao();
        setaSinalSulco();

        ombro.setDashData(dataDash.getTimeInMillis());
        ombro.setDashPontuacao(campoPontoDash.getText().toString());
        ombro.setDashResultados(campoResultDash.getText().toString());

        ombro.setAsesData(dataAses.getTimeInMillis());
        ombro.setAsesPontuacao(campoPontoAses.getText().toString());
        ombro.setAsesResultados(campoResultAses.getText().toString());


        ombroDao.edita(ombro);
    }

    private void setListenerCalendariosDatas() {

        DatePickerDialog.OnDateSetListener dateDash = instanciaSeletorData(dataDash);
        DatePickerDialog.OnDateSetListener dateAses = instanciaSeletorData(dataAses);


        atualizaDataAses();
        atualizaDataDash();

        campoDataDash.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                new DatePickerDialog(SecaoTestesEspeciaisOmbroPt2Activity.this, dateDash,
                        dataDash.get(Calendar.YEAR),
                        dataDash.get(Calendar.MONTH),
                        dataDash.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        campoDataAses.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                new DatePickerDialog(SecaoTestesEspeciaisOmbroPt2Activity.this, dateAses,
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



    private void setaApreensao() {
        String idApreensao;
        if (radioApreensao.getCheckedRadioButtonId() == R.id.activity_secao_testes_especiais_ombro_radio_direita_mais_apreensao_anterior) {
            idApreensao = CHAVE_DIR_MAIS;
        } else if (radioApreensao.getCheckedRadioButtonId() == R.id.activity_secao_testes_especiais_ombro_radio_direita_menos_apreensao_anterior) {
            idApreensao = CHAVE_DIR_MENOS;
        } else if (radioApreensao.getCheckedRadioButtonId() == R.id.activity_secao_testes_especiais_ombro_radio_esquerda_mais_apreensao_anterior) {
            idApreensao = CHAVE_ESQ_MAIS;
        } else if (radioApreensao.getCheckedRadioButtonId() == R.id.activity_secao_testes_especiais_ombro_radio_esquerda_menos_apreensao_anterior) {
            idApreensao = CHAVE_ESQ_MENOS;
        } else {
            idApreensao = "";
        }
        ombro.setApreensaoAnterior(idApreensao);
    }

    private void setaSinalSulco() {
        String idSinalSulco;
        if (radioSinalSulco.getCheckedRadioButtonId() == R.id.activity_secao_testes_especiais_ombro_radio_direita_mais_sinal_de_sulco) {
            idSinalSulco = CHAVE_DIR_MAIS;
        } else if (radioSinalSulco.getCheckedRadioButtonId() == R.id.activity_secao_testes_especiais_ombro_radio_direita_menos_sinal_de_sulco) {
            idSinalSulco = CHAVE_DIR_MENOS;
        } else if (radioSinalSulco.getCheckedRadioButtonId() == R.id.activity_secao_testes_especiais_ombro_radio_esquerda_mais_sinal_de_sulco) {
            idSinalSulco = CHAVE_ESQ_MAIS;
        } else if (radioSinalSulco.getCheckedRadioButtonId() == R.id.activity_secao_testes_especiais_ombro_radio_esquerda_menos_sinal_de_sulco) {
            idSinalSulco = CHAVE_ESQ_MENOS;
        } else {
            idSinalSulco = "";
        }
        ombro.setSinalSulco(idSinalSulco);
    }

    private void inicializaFormulario() {
        radioApreensao = findViewById(R.id.activity_secao_testes_especiais_ombro_radio_apreensao_anterior);
        radioSinalSulco = findViewById(R.id.activity_secao_testes_especiais_ombro_radio_sinal_de_sulco);

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
            dataDash.setTimeInMillis(ombro.getDashData());
            dataAses.setTimeInMillis(ombro.getAsesData());

            getDadosRadio(ombro.getApreensaoAnterior(),
                    R.id.activity_secao_testes_especiais_ombro_radio_direita_mais_apreensao_anterior,
                    R.id.activity_secao_testes_especiais_ombro_radio_direita_menos_apreensao_anterior,
                    R.id.activity_secao_testes_especiais_ombro_radio_esquerda_mais_apreensao_anterior,
                    R.id.activity_secao_testes_especiais_ombro_radio_esquerda_menos_apreensao_anterior,
                    radioApreensao);

            getDadosRadio(ombro.getSinalSulco(),
                    R.id.activity_secao_testes_especiais_ombro_radio_direita_mais_sinal_de_sulco,
                    R.id.activity_secao_testes_especiais_ombro_radio_direita_menos_sinal_de_sulco,
                    R.id.activity_secao_testes_especiais_ombro_radio_esquerda_mais_sinal_de_sulco,
                    R.id.activity_secao_testes_especiais_ombro_radio_esquerda_menos_sinal_de_sulco,
                    radioSinalSulco);

            atualizaDataDash();
            campoPontoDash.setText(ombro.getDashPontuacao());
            campoResultDash.setText(ombro.getDashResultados());
            atualizaDataAses();
            campoPontoAses.setText(ombro.getAsesPontuacao());
            campoResultAses.setText(ombro.getAsesResultados());

        }
    }

    private void getDadosRadio(@Nullable String radioChecked, int idDM, int idDm, int idEM, int idEm, RadioGroup radio) {
        if (radioChecked != null) {
            switch (radioChecked) {
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
            ombro = ombroDao.getOmbro((String) dados.getSerializableExtra(CHAVE_EXAME));
            secoes = secoesDao.getSecao(ombro.getExame());
        }
    }

    private void inicializaDAOS() {
        FisioExamDatabase database = FisioExamDatabase.getInstance(this);
        ombroDao = database.getRoomOmbroDAO();
        secoesDao = database.getRoomSecoesDAO();
    }
}
