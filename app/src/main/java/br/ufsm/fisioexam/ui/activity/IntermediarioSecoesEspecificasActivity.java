package br.ufsm.fisioexam.ui.activity;

import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_EXAME;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_SECAO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.TIPO_COTOVELO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.TIPO_OMBRO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.TIPO_PUNHO;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import br.ufsm.fisioexam.R;
import br.ufsm.fisioexam.database.FisioExamDatabase;
import br.ufsm.fisioexam.database.dao.ExameDAO;
import br.ufsm.fisioexam.database.dao.OmbroDAO;
import br.ufsm.fisioexam.model.Exame;
import br.ufsm.fisioexam.model.Ombro;

public class IntermediarioSecoesEspecificasActivity extends AppCompatActivity {
    private Exame exame;
    private String tipo;
    private int secao;
    private ExameDAO exameDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermediario_secoes_especificas);
        inicializaDAOs();
        inicializaCampos();
        rodaGif();
    }

    private void inicializaDAOs() {
        FisioExamDatabase database = FisioExamDatabase.getInstance(this);
        exameDao = database.getRoomExameDAO();
    }

    private void inicializaCampos() {
        Intent dados = getIntent();

        if (dados.hasExtra(CHAVE_EXAME) && dados.hasExtra(CHAVE_SECAO)) {
            exame = exameDao.getExame((int) dados.getSerializableExtra(CHAVE_EXAME));
            secao = (int) dados.getSerializableExtra(CHAVE_SECAO);
            selecionaFormulario();
        }else{
            finish();
        }
    }

    private void selecionaFormulario() {
        switch(secao){
            case 1:
                amplitudeMovimento();
                finish();
                break;
            case 2:
                perimetria();
                finish();
                break;
            case 3:
                forcaMuscular();
                finish();
                break;
            case 4:
                sensibilidade();
                finish();
                break;
            case 5:
                testesEspeciais();
                finish();
                break;
            default:
                finish();
        }
    }

    private void testesEspeciais() {
        tipo = exame.getTipo();
    }

    private void sensibilidade() {
        tipo = exame.getTipo();
        switch(tipo) {
            case TIPO_OMBRO:
                Ombro ombro;
                FisioExamDatabase database = FisioExamDatabase.getInstance(this);
                OmbroDAO ombroDao = database.getRoomOmbroDAO();
                if (ombroDao.todos(exame.getId()).isEmpty()) {
                    ombro = new Ombro(exame.getId());
                    ombroDao.salva(ombro);
                    ombro.setId(ombroDao.getIdOmbroPeloExame(exame.getId()));
                } else {
                    ombro = ombroDao.getOmbro(ombroDao.getIdOmbroPeloExame(exame.getId()));
                }
                Intent vaiParaFormularioSecaoOmbroActivity = new Intent(this, SecaoSensibilidadeOmbroActivity.class);
                vaiParaFormularioSecaoOmbroActivity.putExtra(CHAVE_EXAME, ombro.getId());
                startActivity(vaiParaFormularioSecaoOmbroActivity);
                break;
            case TIPO_COTOVELO:
//                Intent vaiParaFormularioSecaoCotoveloActivity = new Intent(this, SecaoAmplitudeMovimentoCotoveloActivity.class);
//                vaiParaFormularioSecaoCotoveloActivity.putExtra(CHAVE_EXAME, exame.getId());
//                startActivity(vaiParaFormularioSecaoCotoveloActivity);
                break;
            case TIPO_PUNHO:
//                Intent vaiParaFormularioSecaoPunhoActivity = new Intent(this, SecaoAmplitudeMovimentoPunhoActivity.class);
//                vaiParaFormularioSecaoCotoveloActivity.putExtra(CHAVE_EXAME, exame.getId());
//                startActivity(vaiParaFormularioSecaoCotoveloActivity);
                break;
        }
    }

    private void forcaMuscular() {
        tipo = exame.getTipo();
        switch(tipo) {
            case TIPO_OMBRO:
                Ombro ombro;
                FisioExamDatabase database = FisioExamDatabase.getInstance(this);
                OmbroDAO ombroDao = database.getRoomOmbroDAO();
                if (ombroDao.todos(exame.getId()).isEmpty()) {
                    ombro = new Ombro(exame.getId());
                    ombroDao.salva(ombro);
                    ombro.setId(ombroDao.getIdOmbroPeloExame(exame.getId()));
                } else {
                    ombro = ombroDao.getOmbro(ombroDao.getIdOmbroPeloExame(exame.getId()));
                }
                Intent vaiParaFormularioSecaoOmbroActivity = new Intent(this, SecaoForcaMuscularOmbroActivity.class);
                vaiParaFormularioSecaoOmbroActivity.putExtra(CHAVE_EXAME, ombro.getId());
                startActivity(vaiParaFormularioSecaoOmbroActivity);
                break;
            case TIPO_COTOVELO:
//                Intent vaiParaFormularioSecaoCotoveloActivity = new Intent(this, SecaoAmplitudeMovimentoCotoveloActivity.class);
//                vaiParaFormularioSecaoCotoveloActivity.putExtra(CHAVE_EXAME, exame.getId());
//                startActivity(vaiParaFormularioSecaoCotoveloActivity);
                break;
            case TIPO_PUNHO:
//                Intent vaiParaFormularioSecaoPunhoActivity = new Intent(this, SecaoAmplitudeMovimentoPunhoActivity.class);
//                vaiParaFormularioSecaoCotoveloActivity.putExtra(CHAVE_EXAME, exame.getId());
//                startActivity(vaiParaFormularioSecaoCotoveloActivity);
                break;
        }
    }

    private void perimetria() {
        tipo = exame.getTipo();
        switch(tipo) {
            case TIPO_OMBRO:
                Ombro ombro;
                FisioExamDatabase database = FisioExamDatabase.getInstance(this);
                OmbroDAO ombroDao = database.getRoomOmbroDAO();
                if (ombroDao.todos(exame.getId()).isEmpty()) {
                    ombro = new Ombro(exame.getId());
                    ombroDao.salva(ombro);
                    ombro.setId(ombroDao.getIdOmbroPeloExame(exame.getId()));
                } else {
                    ombro = ombroDao.getOmbro(ombroDao.getIdOmbroPeloExame(exame.getId()));
                }
                Intent vaiParaFormularioSecaoOmbroActivity = new Intent(this, SecaoPerimetriaOmbroActivity.class);
                vaiParaFormularioSecaoOmbroActivity.putExtra(CHAVE_EXAME, ombro.getId());
                startActivity(vaiParaFormularioSecaoOmbroActivity);
                break;
            case TIPO_COTOVELO:
//                Intent vaiParaFormularioSecaoCotoveloActivity = new Intent(this, SecaoAmplitudeMovimentoCotoveloActivity.class);
//                vaiParaFormularioSecaoCotoveloActivity.putExtra(CHAVE_EXAME, exame.getId());
//                startActivity(vaiParaFormularioSecaoCotoveloActivity);
                break;
            case TIPO_PUNHO:
//                Intent vaiParaFormularioSecaoPunhoActivity = new Intent(this, SecaoAmplitudeMovimentoPunhoActivity.class);
//                vaiParaFormularioSecaoCotoveloActivity.putExtra(CHAVE_EXAME, exame.getId());
//                startActivity(vaiParaFormularioSecaoCotoveloActivity);
                break;
        }
    }

    private void amplitudeMovimento() {
        tipo = exame.getTipo();
        switch(tipo){
            case TIPO_OMBRO:
                Ombro ombro;
                FisioExamDatabase database = FisioExamDatabase.getInstance(this);
                OmbroDAO ombroDao = database.getRoomOmbroDAO();
                if (ombroDao.todos(exame.getId()).isEmpty()){
                    ombro = new Ombro(exame.getId());
                    ombroDao.salva(ombro);
                    ombro.setId(ombroDao.getIdOmbroPeloExame(exame.getId()));
                }else{
                    ombro = ombroDao.getOmbro(ombroDao.getIdOmbroPeloExame(exame.getId()));
                }
                Intent vaiParaFormularioSecaoOmbroActivity = new Intent(this, SecaoAmplitudeMovimentoOmbroActivity.class);
                vaiParaFormularioSecaoOmbroActivity.putExtra(CHAVE_EXAME, ombro.getId());
                startActivity(vaiParaFormularioSecaoOmbroActivity);
                break;
            case TIPO_COTOVELO:
//                Intent vaiParaFormularioSecaoCotoveloActivity = new Intent(this, SecaoAmplitudeMovimentoCotoveloActivity.class);
//                vaiParaFormularioSecaoCotoveloActivity.putExtra(CHAVE_EXAME, exame.getId());
//                startActivity(vaiParaFormularioSecaoCotoveloActivity);
                break;
            case TIPO_PUNHO:
//                Intent vaiParaFormularioSecaoPunhoActivity = new Intent(this, SecaoAmplitudeMovimentoPunhoActivity.class);
//                vaiParaFormularioSecaoCotoveloActivity.putExtra(CHAVE_EXAME, exame.getId());
//                startActivity(vaiParaFormularioSecaoCotoveloActivity);
                break;
        }
    }


    private void rodaGif() {
        ImageView imageView = findViewById(R.id.activity_secao_intermediario_secoes_especificas_gif);
        RequestOptions requestOptions = new RequestOptions().centerInside().override(200, 200);
        Glide.with(this)
                .asGif()
                .load(R.drawable.loading_gif)
                .apply(requestOptions)
                .into(imageView);
    }

}


