package br.ufsm.fisioexam.ui.activity;

import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_EXAME;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_SECAO;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

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

    private CheckBox campoCozenDir;
    private CheckBox campoCozenEsq;
    private CheckBox campoCotoveloGolfistaDir;
    private CheckBox campoCotoveloGolfistaEsq;
    private CheckBox campoSinalTinelDir;
    private CheckBox campoSinalTinelEsq;
    private CheckBox campoLclDir;
    private CheckBox campoLclEsq;
    private CheckBox campoLcmDir;
    private CheckBox campoLcmEsq;


    private EditText cotoveloDataDash;
    private EditText cotoveloPontoDash;
    private EditText cotoveloResultDash;
    private EditText cotoveloDataAses;
    private EditText cotoveloPontoAses;
    private EditText cotoveloResultAses;

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

        cotovelo.setTesteCozenDir(campoCozenDir.isChecked());
        cotovelo.setTesteCozenEsq(campoCozenEsq.isChecked());
        cotovelo.setTesteCotoveloGolfistaDir(campoCotoveloGolfistaDir.isChecked());
        cotovelo.setTesteCotoveloGolfistaEsq(campoCotoveloGolfistaEsq.isChecked());
        cotovelo.setTesteSinalTinelDir(campoSinalTinelDir.isChecked());
        cotovelo.setTesteSinalTinelEsq(campoSinalTinelEsq.isChecked());
        cotovelo.setTesteEsforcoVaroDir(campoLclDir.isChecked());
        cotovelo.setTesteEsforcoVaroEsq(campoLclEsq.isChecked());
        cotovelo.setTesteEsforcoValgoDir(campoLcmDir.isChecked());
        cotovelo.setTesteEsforcoValgoEsq(campoLcmEsq.isChecked());

        cotovelo.setDashData(dataDash.getTimeInMillis());
        cotovelo.setDashPontuacao(cotoveloPontoDash.getText().toString());
        cotovelo.setDashResultados(cotoveloResultDash.getText().toString());

        cotovelo.setAsesData(dataAses.getTimeInMillis());
        cotovelo.setAsesPontuacao(cotoveloPontoAses.getText().toString());
        cotovelo.setAsesResultados(cotoveloResultAses.getText().toString());


        cotoveloDao.edita(cotovelo);
    }

    private void setListenerCalendariosDatas() {

        DatePickerDialog.OnDateSetListener dateDash = instanciaSeletorData(dataDash);
        DatePickerDialog.OnDateSetListener dateAses = instanciaSeletorData(dataAses);


        atualizaDataAses();
        atualizaDataDash();

        cotoveloDataDash.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                new DatePickerDialog(SecaoTestesEspeciaisCotoveloActivity.this, dateDash,
                        dataDash.get(Calendar.YEAR),
                        dataDash.get(Calendar.MONTH),
                        dataDash.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        cotoveloDataAses.setOnFocusChangeListener((v, hasFocus) -> {
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

        cotoveloDataDash.setText(dateFormat.format(dataDash.getTime()));
    }

    private void atualizaDataAses() {
        String formatoData = "dd/MM/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatoData, new Locale("pt", "BR"));

        cotoveloDataAses.setText(dateFormat.format(dataAses.getTime()));
    }

    private void inicializaFormulario() {
        campoCozenDir = findViewById(R.id.activity_secao_testes_especiais_cotovelo_direita_cozen);
        campoCozenEsq = findViewById(R.id.activity_secao_testes_especiais_cotovelo_esquerda_cozen);
        campoCotoveloGolfistaDir = findViewById(R.id.activity_secao_testes_especiais_cotovelo_direita_teste_golfista);
        campoCotoveloGolfistaEsq = findViewById(R.id.activity_secao_testes_especiais_cotovelo_esquerda_teste_golfista);
        campoSinalTinelDir = findViewById(R.id.activity_secao_testes_especiais_cotovelo_direita_tinel);
        campoSinalTinelEsq = findViewById(R.id.activity_secao_testes_especiais_cotovelo_esquerda_tinel);
        campoLclDir = findViewById(R.id.activity_secao_testes_especiais_cotovelo_direita_lcl);
        campoLclEsq = findViewById(R.id.activity_secao_testes_especiais_cotovelo_esquerda_lcl);
        campoLcmDir = findViewById(R.id.activity_secao_testes_especiais_cotovelo_direita_lcm);
        campoLcmEsq = findViewById(R.id.activity_secao_testes_especiais_cotovelo_esquerda_lcm);

        cotoveloDataDash = findViewById(R.id.activity_secao_testes_especiais_cotovelo_dash_data);
        cotoveloPontoDash = findViewById(R.id.activity_secao_testes_especiais_cotovelo_dash_pontuacao);
        cotoveloResultDash = findViewById(R.id.activity_secao_testes_especiais_cotovelo_dash_resultados);
        cotoveloDataAses = findViewById(R.id.activity_secao_testes_especiais_cotovelo_ases_data);
        cotoveloPontoAses = findViewById(R.id.activity_secao_testes_especiais_cotovelo_ases_pontuacao);
        cotoveloResultAses = findViewById(R.id.activity_secao_testes_especiais_cotovelo_ases_resultados);

        preencheFormulario();
    }

    private void preencheFormulario() {
        if(secoes.isTestesEspeciais()){
            dataDash.setTimeInMillis(cotovelo.getDashData());
            dataAses.setTimeInMillis(cotovelo.getAsesData());

            campoCozenDir.setChecked(cotovelo.getTesteCozenDir());
            campoCozenEsq.setChecked(cotovelo.getTesteCozenEsq());
            campoCotoveloGolfistaDir.setChecked(cotovelo.getTesteCotoveloGolfistaDir());
            campoCotoveloGolfistaEsq.setChecked(cotovelo.getTesteCotoveloGolfistaEsq());
            campoSinalTinelDir.setChecked(cotovelo.getTesteSinalTinelDir());
            campoSinalTinelEsq.setChecked(cotovelo.getTesteSinalTinelEsq());
            campoLclDir.setChecked(cotovelo.getTesteEsforcoVaroDir());
            campoLclEsq.setChecked(cotovelo.getTesteEsforcoVaroEsq());
            campoLcmDir.setChecked(cotovelo.getTesteEsforcoValgoDir());
            campoLcmEsq.setChecked(cotovelo.getTesteEsforcoValgoEsq());

            atualizaDataDash();
            cotoveloPontoDash.setText(cotovelo.getDashPontuacao());
            cotoveloResultDash.setText(cotovelo.getDashResultados());

            atualizaDataAses();
            cotoveloPontoAses.setText(cotovelo.getAsesPontuacao());
            cotoveloResultAses.setText(cotovelo.getAsesResultados());

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
