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
import br.ufsm.fisioexam.database.dao.PunhoDAO;
import br.ufsm.fisioexam.database.dao.SecoesDAO;
import br.ufsm.fisioexam.model.Punho;
import br.ufsm.fisioexam.model.Secoes;

public class SecaoTestesEspeciaisPunhoActivity extends AppCompatActivity {
    private Punho punho;
    private PunhoDAO punhoDao;
    private Secoes secoes;
    private SecoesDAO secoesDao;

    private Button buttonSalvar;
    private Button buttonProximo;

    private Calendar dataDash;

    private RadioGroup radiophalen;
    private RadioGroup radioPhalenInvertido;
    private RadioGroup radioTinel;
    private RadioGroup radioTriade;
    private RadioGroup radioFinkelstein;


    private EditText campoDataDash;
    private EditText campoPontoDash;
    private EditText campoResultDash;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secao_testes_especiais_punho);
        inicializaCalendars();
        inicializaDAOS();
        carregaExame();
        inicializaBotoes();
        inicializaFormulario();
        setListenerCalendariosDatas();
    }

    private void inicializaCalendars() {
        dataDash = Calendar.getInstance();
    }

    private void inicializaBotoes() {
        buttonProximo = findViewById(R.id.activity_secao_testes_especiais_punho_button_proximo);
        buttonSalvar = findViewById(R.id.activity_secao_testes_especiais_punho_button_salvar_e_sair);
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
        vaiParaFormularioSecaoActivity.putExtra(CHAVE_EXAME, punho.getExame());
        vaiParaFormularioSecaoActivity.putExtra(CHAVE_SECAO, 6);
        startActivity(vaiParaFormularioSecaoActivity);
        finish();
    }

    private void salva() {
        secoes.setTestesEspeciais(true);
        secoesDao.edita(secoes);

        setaPhalen();
        setaPhalenInvertido();
        setaTinel();
        setaTriade();
        setaFinkelstein();

        punho.setDashData(dataDash.getTimeInMillis());
        punho.setDashPontuacao(campoPontoDash.getText().toString());
        punho.setDashResultados(campoResultDash.getText().toString());


        punhoDao.edita(punho);
    }

    private void setListenerCalendariosDatas() {

        DatePickerDialog.OnDateSetListener dateDash = instanciaSeletorData(dataDash);
        atualizaDataDash();

        campoDataDash.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                new DatePickerDialog(SecaoTestesEspeciaisPunhoActivity.this, dateDash,
                        dataDash.get(Calendar.YEAR),
                        dataDash.get(Calendar.MONTH),
                        dataDash.get(Calendar.DAY_OF_MONTH)).show();
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


    private void setaPhalen() {
        String idPhalen;
        switch (radiophalen.getCheckedRadioButtonId()) {
            case (R.id.activity_secao_testes_especiais_punho_radio_direita_mais_phalen):
                idPhalen = CHAVE_DIR_MAIS;
                break;
            case (R.id.activity_secao_testes_especiais_punho_radio_direita_menos_phalen):
                idPhalen = CHAVE_DIR_MENOS;
                break;
            case (R.id.activity_secao_testes_especiais_punho_radio_esquerda_mais_phalen):
                idPhalen = CHAVE_ESQ_MAIS;
                break;
            case (R.id.activity_secao_testes_especiais_punho_radio_esquerda_menos_phalen):
                idPhalen = CHAVE_ESQ_MENOS;
                break;
            default:
                idPhalen = "";
        }
        punho.setTestesEspeciaisPhalen(idPhalen);
    }

    private void setaPhalenInvertido() {
        String idPhalenInvertido;
        switch (radioPhalenInvertido.getCheckedRadioButtonId()) {
            case (R.id.activity_secao_testes_especiais_punho_radio_direita_mais_phalen_invertido):
                idPhalenInvertido = CHAVE_DIR_MAIS;
                break;
            case (R.id.activity_secao_testes_especiais_punho_radio_direita_menos_phalen_invertido):
                idPhalenInvertido = CHAVE_DIR_MENOS;
                break;
            case (R.id.activity_secao_testes_especiais_punho_radio_esquerda_mais_phalen_invertido):
                idPhalenInvertido = CHAVE_ESQ_MAIS;
                break;
            case (R.id.activity_secao_testes_especiais_punho_radio_esquerda_menos_phalen_invertido):
                idPhalenInvertido = CHAVE_ESQ_MENOS;
                break;
            default:
                idPhalenInvertido = "";
        }
        punho.setTestesEspeciaisPhalenInvertido(idPhalenInvertido);
    }

    private void setaTinel() {
        String idTinel;
        switch (radioTinel.getCheckedRadioButtonId()) {
            case (R.id.activity_secao_testes_especiais_punho_radio_direita_mais_tinel):
                idTinel = CHAVE_DIR_MAIS;
                break;
            case (R.id.activity_secao_testes_especiais_punho_radio_direita_menos_tinel):
                idTinel = CHAVE_DIR_MENOS;
                break;
            case (R.id.activity_secao_testes_especiais_punho_radio_esquerda_mais_tinel):
                idTinel = CHAVE_ESQ_MAIS;
                break;
            case (R.id.activity_secao_testes_especiais_punho_radio_esquerda_menos_tinel):
                idTinel = CHAVE_ESQ_MENOS;
                break;
            default:
                idTinel = "";
        }
        punho.setTestesEspeciaisTinel(idTinel);
    }

    private void setaTriade() {
        String idTriade;
        switch (radioTriade.getCheckedRadioButtonId()) {
            case (R.id.activity_secao_testes_especiais_punho_radio_direita_mais_tunel_ulnar):
                idTriade = CHAVE_DIR_MAIS;
                break;
            case (R.id.activity_secao_testes_especiais_punho_radio_direita_menos_tunel_ulnar):
                idTriade = CHAVE_DIR_MENOS;
                break;
            case (R.id.activity_secao_testes_especiais_punho_radio_esquerda_mais_tunel_ulnar):
                idTriade = CHAVE_ESQ_MAIS;
                break;
            case (R.id.activity_secao_testes_especiais_punho_radio_esquerda_menos_tunel_ulnar):
                idTriade = CHAVE_ESQ_MENOS;
                break;
            default:
                idTriade = "";
        }
        punho.setTestesEspeciaisTriade(idTriade);
    }

    private void setaFinkelstein() {
        String idFinkelstein;
        switch (radioFinkelstein.getCheckedRadioButtonId()) {
            case (R.id.activity_secao_testes_especiais_punho_radio_direita_mais_finkelstein):
                idFinkelstein = CHAVE_DIR_MAIS;
                break;
            case (R.id.activity_secao_testes_especiais_punho_radio_direita_menos_finkelstein):
                idFinkelstein = CHAVE_DIR_MENOS;
                break;
            case (R.id.activity_secao_testes_especiais_punho_radio_esquerda_mais_finkelstein):
                idFinkelstein = CHAVE_ESQ_MAIS;
                break;
            case (R.id.activity_secao_testes_especiais_punho_radio_esquerda_menos_finkelstein):
                idFinkelstein = CHAVE_ESQ_MENOS;
                break;
            default:
                idFinkelstein = "";
        }
        punho.setTestesEspeciaisFinkelstein(idFinkelstein);

    }

    private void inicializaFormulario() {
        radiophalen = findViewById(R.id.activity_secao_testes_especiais_punho_radio_phalen);
        radioPhalenInvertido = findViewById(R.id.activity_secao_testes_especiais_punho_radio_phalen_invertido);
        radioTinel = findViewById(R.id.activity_secao_testes_especiais_punho_radio_tinel);
        radioTriade = findViewById(R.id.activity_secao_testes_especiais_punho_radio_tunel_ulnar);
        radioFinkelstein = findViewById(R.id.activity_secao_testes_especiais_punho_radio_finkelstein);

        campoDataDash = findViewById(R.id.activity_secao_testes_especiais_punho_dash_data);
        campoPontoDash = findViewById(R.id.activity_secao_testes_especiais_punho_dash_pontuacao);
        campoResultDash = findViewById(R.id.activity_secao_testes_especiais_punho_dash_resultados);

        preencheFormulario();
    }

    private void preencheFormulario() {
        if (secoes.isTestesEspeciais()) {
            dataDash.setTimeInMillis(punho.getDashData());

            getDadosRadio(punho.getTestesEspeciaisPhalen(),
                    R.id.activity_secao_testes_especiais_punho_radio_direita_mais_phalen,
                    R.id.activity_secao_testes_especiais_punho_radio_direita_menos_phalen,
                    R.id.activity_secao_testes_especiais_punho_radio_esquerda_mais_phalen,
                    R.id.activity_secao_testes_especiais_punho_radio_esquerda_menos_phalen);

            getDadosRadio(punho.getTestesEspeciaisPhalenInvertido(),
                    R.id.activity_secao_testes_especiais_punho_radio_direita_mais_phalen_invertido,
                    R.id.activity_secao_testes_especiais_punho_radio_direita_menos_phalen_invertido,
                    R.id.activity_secao_testes_especiais_punho_radio_esquerda_mais_phalen_invertido,
                    R.id.activity_secao_testes_especiais_punho_radio_esquerda_menos_phalen_invertido);

            getDadosRadio(punho.getTestesEspeciaisTinel(),
                    R.id.activity_secao_testes_especiais_punho_radio_direita_mais_tinel,
                    R.id.activity_secao_testes_especiais_punho_radio_direita_menos_tinel,
                    R.id.activity_secao_testes_especiais_punho_radio_esquerda_mais_tinel,
                    R.id.activity_secao_testes_especiais_punho_radio_esquerda_menos_tinel);

            getDadosRadio(punho.getTestesEspeciaisTriade(),
                    R.id.activity_secao_testes_especiais_punho_radio_direita_mais_tunel_ulnar,
                    R.id.activity_secao_testes_especiais_punho_radio_direita_menos_tunel_ulnar,
                    R.id.activity_secao_testes_especiais_punho_radio_esquerda_mais_tunel_ulnar,
                    R.id.activity_secao_testes_especiais_punho_radio_esquerda_menos_tunel_ulnar);

            getDadosRadio(punho.getTestesEspeciaisFinkelstein(),
                    R.id.activity_secao_testes_especiais_punho_radio_direita_mais_finkelstein,
                    R.id.activity_secao_testes_especiais_punho_radio_direita_menos_finkelstein,
                    R.id.activity_secao_testes_especiais_punho_radio_esquerda_mais_finkelstein,
                    R.id.activity_secao_testes_especiais_punho_radio_esquerda_menos_finkelstein);

            atualizaDataDash();
            campoPontoDash.setText(punho.getDashPontuacao());
            campoResultDash.setText(punho.getDashResultados());

        }
    }

    private void getDadosRadio(@Nullable String radio, int idDM, int idDm, int idEM, int idEm) {
        if (radio != null) {
            switch (radio) {
                case CHAVE_DIR_MAIS:
                    radiophalen.check(idDM);
                    break;
                case CHAVE_DIR_MENOS:
                    radiophalen.check(idDm);
                    break;
                case CHAVE_ESQ_MAIS:
                    radiophalen.check(idEM);
                    break;
                case CHAVE_ESQ_MENOS:
                    radiophalen.check(idEm);
                    break;
            }
        }
    }

    private void carregaExame() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_EXAME)) {
            punho = punhoDao.getPunho((String) dados.getSerializableExtra(CHAVE_EXAME));
            secoes = secoesDao.getSecao(punho.getExame());
        }
    }

    private void inicializaDAOS() {
        FisioExamDatabase database = FisioExamDatabase.getInstance(this);
        punhoDao = database.getRoomPunhoDAO();
        secoesDao = database.getRoomSecoesDAO();
    }
}
