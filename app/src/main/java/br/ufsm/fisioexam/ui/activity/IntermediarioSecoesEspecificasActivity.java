package br.ufsm.fisioexam.ui.activity;

import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_EXAME;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_SECAO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_TIPO_COTOVELO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_TIPO_OMBRO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_TIPO_PUNHO;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import br.ufsm.fisioexam.R;
import br.ufsm.fisioexam.database.FisioExamDatabase;
import br.ufsm.fisioexam.database.dao.CotoveloDAO;
import br.ufsm.fisioexam.database.dao.ExameDAO;
import br.ufsm.fisioexam.database.dao.OmbroDAO;
import br.ufsm.fisioexam.database.dao.PunhoDAO;
import br.ufsm.fisioexam.database.thread.QueryManager;
import br.ufsm.fisioexam.model.Cotovelo;
import br.ufsm.fisioexam.model.Exame;
import br.ufsm.fisioexam.model.Ombro;
import br.ufsm.fisioexam.model.Punho;

public class IntermediarioSecoesEspecificasActivity extends AppCompatActivity {
    private Exame exame;
    private String tipo;
    private int secao;
    private ExameDAO exameDao;
    private QueryManager<Exame> queryManagerExame;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermediario_secoes_especificas);
        inicializaDAOs();
        inicializaCampos();
        rodaGif();
    }

    private void inicializaDAOs() {
        FisioExamDatabase database = FisioExamDatabase.getInstance(this);
        exameDao = database.getRoomExameDAO();
        queryManagerExame = new QueryManager<>();
    }

    private void inicializaCampos() {
        Intent dados = getIntent();

        if (dados.hasExtra(CHAVE_EXAME) && dados.hasExtra(CHAVE_SECAO)) {
            exame = queryManagerExame.getOne((String) dados.getSerializableExtra(CHAVE_EXAME), exameDao);
            secao = (int) dados.getSerializableExtra(CHAVE_SECAO);
            selecionaFormulario();
        } else {
            finish();
        }
    }

    private void selecionaFormulario() {
        switch (secao) {
            case 1 -> {
                amplitudeMovimento();
                finish();
            }
            case 2 -> {
                perimetria();
                finish();
            }
            case 3 -> {
                forcaMuscular();
                finish();
            }
            case 4 -> {
                sensibilidade();
                finish();
            }
            case 5 -> {
                testesEspeciais();
                finish();
            }
            case 6 -> {
                observacoes();
                finish();
            }
            default -> finish();
        }
    }

    private void observacoes() {
        Intent vaiParaFormularioSecaoActivity = new Intent(this, SecaoObservacoesActivity.class);
        adicionaChaveTipo(vaiParaFormularioSecaoActivity, exame.getId());
        startActivity(vaiParaFormularioSecaoActivity);
    }

    private void testesEspeciais() {
        tipo = exame.getTipo();
        switch (tipo) {
            case CHAVE_TIPO_OMBRO -> {
                Ombro ombro = getOmbro();
                vaiParaSecaoEspecifica(ombro.getId(), SecaoTestesEspeciaisOmbroActivity.class);
            }
            case CHAVE_TIPO_COTOVELO -> {
                Cotovelo cotovelo = getCotovelo();
                vaiParaSecaoEspecifica(cotovelo.getId(), SecaoTestesEspeciaisCotoveloActivity.class);
            }
            case CHAVE_TIPO_PUNHO -> {
                Punho punho = getPunho();
                vaiParaSecaoEspecifica(punho.getId(), SecaoTestesEspeciaisPunhoActivity.class);
            }
        }
    }



    private void adicionaChaveTipo(Intent vaiParaFormularioSecaoEspecificaActivity, String idTipo) {
        vaiParaFormularioSecaoEspecificaActivity.putExtra(CHAVE_EXAME, idTipo);
    }

    private void sensibilidade() {
        tipo = exame.getTipo();
        switch (tipo) {
            case CHAVE_TIPO_OMBRO -> {
                Ombro ombro = getOmbro();
                vaiParaSecaoEspecifica(ombro.getId(), SecaoSensibilidadeOmbroActivity.class);
            }
            case CHAVE_TIPO_COTOVELO -> {
                Cotovelo cotovelo = getCotovelo();
                vaiParaSecaoEspecifica(cotovelo.getId(), SecaoSensibilidadeCotoveloActivity.class);
            }
            case CHAVE_TIPO_PUNHO -> {
                Punho punho = getPunho();
                vaiParaSecaoEspecifica(punho.getId(), SecaoSensibilidadePunhoActivity.class);
            }
        }
    }

    private void forcaMuscular() {
        tipo = exame.getTipo();
        switch (tipo) {
            case CHAVE_TIPO_OMBRO -> {
                Ombro ombro = getOmbro();
                vaiParaSecaoEspecifica(ombro.getId(), SecaoForcaMuscularOmbroPt1Activity.class);
            }
            case CHAVE_TIPO_COTOVELO -> {
                Cotovelo cotovelo = getCotovelo();
                vaiParaSecaoEspecifica(cotovelo.getId(), SecaoForcaMuscularCotoveloActivity.class);
            }
            case CHAVE_TIPO_PUNHO -> {
                Punho punho = getPunho();
                vaiParaSecaoEspecifica(punho.getId(), SecaoForcaMuscularPunhoActivity.class);
            }
        }
    }

    private void perimetria() {
        tipo = exame.getTipo();
        switch (tipo) {
            case CHAVE_TIPO_OMBRO -> {
                Ombro ombro = getOmbro();
                vaiParaSecaoEspecifica(ombro.getId(), SecaoPerimetriaOmbroActivity.class);
            }
            case CHAVE_TIPO_COTOVELO -> {
                Cotovelo cotovelo = getCotovelo();
                vaiParaSecaoEspecifica(cotovelo.getId(), SecaoPerimetriaCotoveloActivity.class);
            }
            case CHAVE_TIPO_PUNHO -> {
                Punho punho = getPunho();
                vaiParaSecaoEspecifica(punho.getId(), SecaoPerimetriaPunhoActivity.class);
            }
        }
    }

    private void amplitudeMovimento() {
        tipo = exame.getTipo();
        switch (tipo) {
            case CHAVE_TIPO_OMBRO -> {
                Ombro ombro = getOmbro();
                vaiParaSecaoEspecifica(ombro.getId(), SecaoAmplitudeMovimentoOmbroActivity.class);
            }
            case CHAVE_TIPO_COTOVELO -> {
                Cotovelo cotovelo = getCotovelo();
                vaiParaSecaoEspecifica(cotovelo.getId(), SecaoAmplitudeMovimentoCotoveloActivity.class);
            }
            case CHAVE_TIPO_PUNHO -> {
                Punho punho = getPunho();
                vaiParaSecaoEspecifica(punho.getId(), SecaoAmplitudeMovimentoPunhoActivity.class);
            }
        }
    }


    private void rodaGif() {
        ImageView imageView = findViewById(R.id.activity_secao_intermediario_secoes_especificas_gif);
        RequestOptions requestOptions = new RequestOptions().centerInside().override(200, 200);
        Glide.with(this).asGif().load(R.drawable.loading_gif).apply(requestOptions).into(imageView);
    }

    private Ombro getOmbro() {
        Ombro ombro;
        FisioExamDatabase database = FisioExamDatabase.getInstance(this);
        OmbroDAO ombroDAO = database.getRoomOmbroDAO();
        QueryManager<Ombro> queryManager = new QueryManager<>();
        String id;
        id = queryManager.getIdByForeign(exame.getId(), ombroDAO);
        ombro = queryManager.getOne(id, ombroDAO);
        return ombro;
    }

    private Cotovelo getCotovelo() {
        Cotovelo cotovelo;
        FisioExamDatabase database = FisioExamDatabase.getInstance(this);
        CotoveloDAO cotoveloDAO = database.getRoomCotoveloDAO();
        QueryManager<Cotovelo> queryManager = new QueryManager<>();
        String id;
        id = queryManager.getIdByForeign(exame.getId(), cotoveloDAO);
        cotovelo = queryManager.getOne(id, cotoveloDAO);
        return cotovelo;
    }

    private Punho getPunho() {
        Punho punho;
        FisioExamDatabase database = FisioExamDatabase.getInstance(this);
        PunhoDAO punhoDAO = database.getRoomPunhoDAO();
        QueryManager<Punho> queryManager = new QueryManager<>();
        String id;
        id = queryManager.getIdByForeign(exame.getId(), punhoDAO);
        punho = queryManager.getOne(id, punhoDAO);
        return punho;
    }

    private void vaiParaSecaoEspecifica(String chave, Class<?> secao) {
        Intent vaiParaFormularioSecaoEspecificaActivity = new Intent(this, secao);
        adicionaChaveTipo(vaiParaFormularioSecaoEspecificaActivity, chave);
        startActivity(vaiParaFormularioSecaoEspecificaActivity);
    }
}


