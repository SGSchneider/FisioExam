package br.ufsm.fisioexam.ui.activity;

import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_EXAME;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_SECAO;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.ufsm.fisioexam.R;
import br.ufsm.fisioexam.database.FisioExamDatabase;
import br.ufsm.fisioexam.database.dao.OmbroDAO;
import br.ufsm.fisioexam.database.dao.SecoesDAO;
import br.ufsm.fisioexam.model.Ombro;
import br.ufsm.fisioexam.model.Secoes;

public class SecaoForcaMuscularOmbroActivity extends AppCompatActivity {
    //Usar Parâmetros MAGEE (2010) por ser mais recente.


    private EditText campoTrapezioSuperiorDir;
    private EditText campoTrapezioSuperiorEsq;
    private EditText campoTrapezioMedioDir;
    private EditText campoTrapezioMedioEsq;
    private EditText campoTrapezioInferiorDir;
    private EditText campoTrapezioInferiorEsq;
    private EditText campoRomboidesDir;
    private EditText campoRomboidesEsq;
    private EditText campoSerratilDir;
    private EditText campoSerratilEsq;
    private EditText campoDeltoideAnteriorDir;
    private EditText campoDeltoideAnteriorEsq;
    private EditText campoCoracobraquialDir;
    private EditText campoCoracobraquialEsq;
    private EditText campoGrandeDorsalDir;
    private EditText campoGrandeDorsalEsq;
    private EditText campoRedondoMaiorDir;
    private EditText campoRedondoMaiorEsq;
    private EditText campoSupraespinhalDir;
    private EditText campoSupraespinhalEsq;
    private EditText campoDeltoideMedioDir;
    private EditText campoDeltoideMedioEsq;
    private EditText campoDeltoidePosteriorDir;
    private EditText campoDeltoidePosteriorEsq;
    private EditText campoPeitoralMaiorDir;
    private EditText campoPeitoralMaiorEsq;
    private EditText campoSubescapularDir;
    private EditText campoSubescapularEsq;
    private EditText campoInfraespinhalDir;
    private EditText campoInfraespinhalEsq;


    private Button proximo;
    private Button salvarESair;
    private Ombro ombro;
    private OmbroDAO ombroDao;
    private Secoes secoes;
    private SecoesDAO secoesDao;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secao_forca_muscular_ombro);
        inicializaDaos();
        carregaExame();
        inicializaFormulario();
        inicializaBotoes();
    }


    private void inicializaDaos() {
        FisioExamDatabase database = FisioExamDatabase.getInstance(this);
        ombroDao = database.getRoomOmbroDAO();
        secoesDao = database.getRoomSecoesDAO();
    }

    private void inicializaBotoes() {
        proximo = findViewById(R.id.activity_secao_forca_muscular_ombro_button_proximo);
        salvarESair = findViewById(R.id.activity_secao_forca_muscular_ombro_button_salvar_e_sair);
        configuraListenersDeClique();
    }

    private void configuraListenersDeClique() {
        proximo.setOnClickListener(v -> proximoForm());

        salvarESair.setOnClickListener(v -> finalizaForm());
    }


    private void finalizaForm() {
        salva();
        finish();
    }

    private void salva() {
        secoes.setForcaMuscular(true);
        secoesDao.edita(secoes);

        ombro.setTrapezioSuperiorLevantadorDaEscapulaDir(campoTrapezioSuperiorDir.getText().toString());
        ombro.setTrapezioSuperiorLevantadorDaEscapulaEsq(campoTrapezioSuperiorEsq.getText().toString());
        ombro.setTrapezioMedioDir(campoTrapezioMedioDir.getText().toString());
        ombro.setTrapezioMedioEsq(campoTrapezioMedioEsq.getText().toString());
        ombro.setTrapezioInfDir(campoTrapezioInferiorDir.getText().toString());
        ombro.setTrapezioInfEsq(campoTrapezioInferiorEsq.getText().toString());
        ombro.setRomboidesMaiorMenorDir(campoRomboidesDir.getText().toString());
        ombro.setRomboidesMaiorMenorEsq(campoRomboidesEsq.getText().toString());
        ombro.setSerratilAnteriorDir(campoSerratilDir.getText().toString());
        ombro.setSerratilAnteriorEsq(campoSerratilEsq.getText().toString());
        ombro.setDeltoideAnteriorDir(campoDeltoideAnteriorDir.getText().toString());
        ombro.setDeltoideAnteriorEsq(campoDeltoideAnteriorEsq.getText().toString());
        ombro.setCobraquialDir(campoCoracobraquialDir.getText().toString());
        ombro.setCobraquialEsq(campoCoracobraquialEsq.getText().toString());
        ombro.setGrandeDorsalDir(campoGrandeDorsalDir.getText().toString());
        ombro.setGrandeDorsalEsq(campoGrandeDorsalEsq.getText().toString());
        ombro.setRedondoMaiorDir(campoRedondoMaiorDir.getText().toString());
        ombro.setRedondoMaiorEsq(campoRedondoMaiorEsq.getText().toString());
        ombro.setSupraespinhalDir(campoSupraespinhalDir.getText().toString());
        ombro.setSupraespinhalEsq(campoSupraespinhalEsq.getText().toString());
        ombro.setDeltoideMedioDir(campoDeltoideMedioDir.getText().toString());
        ombro.setDeltoideMedioEsq(campoDeltoideMedioEsq.getText().toString());
        ombro.setDeltoidePosteriorDir(campoDeltoidePosteriorDir.getText().toString());
        ombro.setDeltoidePosteriorEsq(campoDeltoidePosteriorEsq.getText().toString());
        ombro.setPeitoralMaiorDir(campoPeitoralMaiorDir.getText().toString());
        ombro.setPeitoralMaiorEsq(campoPeitoralMaiorEsq.getText().toString());
        ombro.setSubescapularDir(campoSubescapularDir.getText().toString());
        ombro.setSubescapularEsq(campoSubescapularEsq.getText().toString());
        ombro.setInfraespinhalRedondoMenorDir(campoInfraespinhalDir.getText().toString());
        ombro.setInfraespinhalRedondoMenorEsq(campoInfraespinhalEsq.getText().toString());



        ombroDao.edita(ombro);
    }

    private void proximoForm() {
        salva();
        Intent vaiParaFormularioSecaoActivity = new Intent(this, IntermediarioSecoesEspecificasActivity.class);
        vaiParaFormularioSecaoActivity.putExtra(CHAVE_EXAME, ombro.getExame());
        vaiParaFormularioSecaoActivity.putExtra(CHAVE_SECAO, 5);
        startActivity(vaiParaFormularioSecaoActivity);
        finish();
    }


    private void carregaExame() {
        Intent dados = getIntent();

        if (dados.hasExtra(CHAVE_EXAME)) {
            ombro = ombroDao.getOmbro((int) dados.getSerializableExtra(CHAVE_EXAME));
            secoes = secoesDao.getSecao(ombro.getId());
        }
    }


    private void inicializaFormulario() {
        campoTrapezioSuperiorDir = findViewById(R.id.activity_secao_forca_muscular_ombro_trapezio_superior_ombro_dir);
        campoTrapezioSuperiorEsq = findViewById(R.id.activity_secao_forca_muscular_ombro_trapezio_superior_ombro_esq);
        campoTrapezioMedioDir = findViewById(R.id.activity_secao_forca_muscular_ombro_trapezio_medio_ombro_dir);
        campoTrapezioMedioEsq = findViewById(R.id.activity_secao_forca_muscular_ombro_trapezio_medio_ombro_esq);
        campoTrapezioInferiorDir = findViewById(R.id.activity_secao_forca_muscular_ombro_trapezio_inferior_ombro_dir);
        campoTrapezioInferiorEsq = findViewById(R.id.activity_secao_forca_muscular_ombro_trapezio_inferior_ombro_esq);
        campoRomboidesDir = findViewById(R.id.activity_secao_forca_muscular_ombro_romboides_ombro_dir);
        campoRomboidesEsq = findViewById(R.id.activity_secao_forca_muscular_ombro_romboides_ombro_esq);
        campoSerratilDir = findViewById(R.id.activity_secao_forca_muscular_ombro_serratil_ombro_dir);
        campoSerratilEsq = findViewById(R.id.activity_secao_forca_muscular_ombro_serratil_ombro_esq);
        campoDeltoideAnteriorDir = findViewById(R.id.activity_secao_forca_muscular_ombro_deltoite_anterior_ombro_dir);
        campoDeltoideAnteriorEsq = findViewById(R.id.activity_secao_forca_muscular_ombro_deltoite_anterior_ombro_esq);
        campoCoracobraquialDir = findViewById(R.id.activity_secao_forca_muscular_ombro_coracobraquial_ombro_dir);
        campoCoracobraquialEsq = findViewById(R.id.activity_secao_forca_muscular_ombro_coracobraquial_ombro_esq);
        campoGrandeDorsalDir = findViewById(R.id.activity_secao_forca_muscular_ombro_grande_dorsal_ombro_dir);
        campoGrandeDorsalEsq = findViewById(R.id.activity_secao_forca_muscular_ombro_grande_dorsal_ombro_esq);
        campoRedondoMaiorDir = findViewById(R.id.activity_secao_forca_muscular_ombro_redondo_maior_ombro_dir);
        campoRedondoMaiorEsq = findViewById(R.id.activity_secao_forca_muscular_ombro_redondo_maior_ombro_esq);
        campoSupraespinhalDir = findViewById(R.id.activity_secao_forca_muscular_ombro_supraespinhal_ombro_dir);
        campoSupraespinhalEsq = findViewById(R.id.activity_secao_forca_muscular_ombro_supraespinhal_ombro_esq);
        campoDeltoideMedioDir = findViewById(R.id.activity_secao_forca_muscular_ombro_deltoite_medio_ombro_dir);
        campoDeltoideMedioEsq = findViewById(R.id.activity_secao_forca_muscular_ombro_deltoite_medio_ombro_esq);
        campoDeltoidePosteriorDir = findViewById(R.id.activity_secao_forca_muscular_ombro_deltoite_posterior_ombro_dir);
        campoDeltoidePosteriorEsq = findViewById(R.id.activity_secao_forca_muscular_ombro_deltoite_posterior_ombro_esq);
        campoPeitoralMaiorDir = findViewById(R.id.activity_secao_forca_muscular_ombro_peitoral_maior_ombro_dir);
        campoPeitoralMaiorEsq = findViewById(R.id.activity_secao_forca_muscular_ombro_peitoral_maior_ombro_esq);
        campoSubescapularDir = findViewById(R.id.activity_secao_forca_muscular_ombro_subescapular_ombro_dir);
        campoSubescapularEsq = findViewById(R.id.activity_secao_forca_muscular_ombro_subescapular_ombro_esq);
        campoInfraespinhalDir = findViewById(R.id.activity_secao_forca_muscular_ombro_redondo_menor_ombro_dir);
        campoInfraespinhalEsq = findViewById(R.id.activity_secao_forca_muscular_ombro_redondo_menor_ombro_esq);



        if (secoes.isForcaMuscular()) {
            preencheCampos();
        }
    }

    private void preencheCampos() {
        campoTrapezioSuperiorDir.setText(ombro.getTrapezioSuperiorLevantadorDaEscapulaDir());
        campoTrapezioSuperiorEsq.setText(ombro.getTrapezioSuperiorLevantadorDaEscapulaEsq());
        campoTrapezioMedioDir.setText(ombro.getTrapezioMedioDir());
        campoTrapezioMedioEsq.setText(ombro.getTrapezioMedioEsq());
        campoTrapezioInferiorDir.setText(ombro.getTrapezioInfDir());
        campoTrapezioInferiorEsq.setText(ombro.getTrapezioInfEsq());
        campoRomboidesDir.setText(ombro.getRomboidesMaiorMenorDir());
        campoRomboidesEsq.setText(ombro.getRomboidesMaiorMenorEsq());
        campoSerratilDir.setText(ombro.getSerratilAnteriorDir());
        campoSerratilEsq.setText(ombro.getSerratilAnteriorEsq());
        campoDeltoideAnteriorDir.setText(ombro.getDeltoideAnteriorDir());
        campoDeltoideAnteriorEsq.setText(ombro.getDeltoideAnteriorEsq());
        campoCoracobraquialDir.setText(ombro.getCobraquialDir());
        campoCoracobraquialEsq.setText(ombro.getCobraquialEsq());
        campoGrandeDorsalDir.setText(ombro.getGrandeDorsalDir());
        campoGrandeDorsalEsq.setText(ombro.getGrandeDorsalEsq());
        campoRedondoMaiorDir.setText(ombro.getRedondoMaiorDir());
        campoRedondoMaiorEsq.setText(ombro.getRedondoMaiorEsq());
        campoSupraespinhalDir.setText(ombro.getSupraespinhalDir());
        campoSupraespinhalEsq.setText(ombro.getSupraespinhalEsq());
        campoDeltoideMedioDir.setText(ombro.getDeltoideMedioDir());
        campoDeltoideMedioEsq.setText(ombro.getDeltoideMedioEsq());
        campoDeltoidePosteriorDir.setText(ombro.getDeltoidePosteriorDir());
        campoDeltoidePosteriorEsq.setText(ombro.getDeltoidePosteriorEsq());
        campoPeitoralMaiorDir.setText(ombro.getPeitoralMaiorDir());
        campoPeitoralMaiorEsq.setText(ombro.getPeitoralMaiorEsq());
        campoSubescapularDir.setText(ombro.getSubescapularDir());
        campoSubescapularEsq.setText(ombro.getSubescapularEsq());
        campoInfraespinhalDir.setText(ombro.getInfraespinhalRedondoMenorDir());
        campoInfraespinhalEsq.setText(ombro.getInfraespinhalRedondoMenorEsq());
    }
}

