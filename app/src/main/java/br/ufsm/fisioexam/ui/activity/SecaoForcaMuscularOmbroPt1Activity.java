package br.ufsm.fisioexam.ui.activity;

import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_EXAME;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
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

public class SecaoForcaMuscularOmbroPt1Activity extends AppCompatActivity {

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


    private ImageButton botaoHelpTrapezioSuperior;
    private ImageButton botaoHelpTrapezioMedio;
    private ImageButton botaoHelpTrapezioInferior;
    private ImageButton botaoHelpRomboides;
    private ImageButton botaoHelpSerratil;
    private ImageButton botaoHelpDeltoideAnterior;
    private ImageButton botaoHelpCoracobraquial;
    private ImageButton botaoHelpGrandeDorsal;


    private Button proximo;
    private Ombro ombro;
    private OmbroDAO ombroDao;
    private QueryManager<Ombro> ombroQueryManager;
    private Secoes secoes;
    private SecoesDAO secoesDao;
    private QueryManager<Secoes> secoesQueryManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secao_forca_muscular_ombro_pt1);
        inicializaDaos();
        carregaExame();
        inicializaFormulario();
        inicializaBotoes();
    }


    private void inicializaDaos() {
        FisioExamDatabase database = FisioExamDatabase.getInstance(this);
        ombroDao = database.getRoomOmbroDAO();
        secoesDao = database.getRoomSecoesDAO();
        ombroQueryManager = new QueryManager<>();
        secoesQueryManager = new QueryManager<>();
    }

    private void inicializaBotoes() {
        proximo = findViewById(R.id.activity_secao_forca_muscular_ombro_button_parte_2);

        botaoHelpTrapezioSuperior = findViewById(R.id.activity_secao_forca_muscular_ombro_button_help_trapezio_superior);
        botaoHelpTrapezioMedio = findViewById(R.id.activity_secao_forca_muscular_ombro_button_help_trapezio_medio);
        botaoHelpTrapezioInferior = findViewById(R.id.activity_secao_forca_muscular_ombro_button_help_trapezio_inferior);
        botaoHelpRomboides = findViewById(R.id.activity_secao_forca_muscular_ombro_button_help_romboides);
        botaoHelpSerratil = findViewById(R.id.activity_secao_forca_muscular_ombro_button_help_serratil);
        botaoHelpDeltoideAnterior = findViewById(R.id.activity_secao_forca_muscular_ombro_button_help_deltoite_anterior);
        botaoHelpCoracobraquial = findViewById(R.id.activity_secao_forca_muscular_ombro_button_help_coracobraquial);
        botaoHelpGrandeDorsal = findViewById(R.id.activity_secao_forca_muscular_ombro_button_help_grande_dorsal);

        configuraListenersDeClique();
    }

    private void configuraListenersDeClique() {
        proximo.setOnClickListener(v -> proximoForm());

        botaoHelpTrapezioSuperior.setOnClickListener(v -> vaiParaAjuda(AjudaForcaMuscularOmbroTrapezioSuperiorActivity.class));
        botaoHelpTrapezioMedio.setOnClickListener(v -> vaiParaAjuda(AjudaForcaMuscularOmbroTrapezioMedioActivity.class));
        botaoHelpTrapezioInferior.setOnClickListener(v -> vaiParaAjuda(AjudaForcaMuscularOmbroTrapezioInferiorActivity.class));
        botaoHelpRomboides.setOnClickListener(v -> vaiParaAjuda(AjudaForcaMuscularOmbroRomboidesActivity.class));
        botaoHelpSerratil.setOnClickListener(v -> vaiParaAjuda(AjudaForcaMuscularOmbroSerratilActivity.class));
        botaoHelpDeltoideAnterior.setOnClickListener(v -> vaiParaAjuda(AjudaForcaMuscularOmbroDeltoideAnteriorActivity.class));
        botaoHelpCoracobraquial.setOnClickListener(v -> vaiParaAjuda(AjudaForcaMuscularOmbroCoracobraquialActivity.class));
        botaoHelpGrandeDorsal.setOnClickListener(v -> vaiParaAjuda(AjudaForcaMuscularOmbroGrandeDorsalActivity.class));


    }

    private void vaiParaAjuda(Class<?> classe) {
        Intent intent = new Intent(this, classe);
        startActivity(intent);
    }


    private void salva() {
        secoes.setForcaMuscular1(true);
        secoesQueryManager.update(secoes, secoesDao);

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

        ombroQueryManager.update(ombro, ombroDao);
    }

    private void proximoForm() {
        salva();
        Intent vaiParaFormularioSecaoActivity = new Intent(this, SecaoForcaMuscularOmbroPt2Activity.class);
        vaiParaFormularioSecaoActivity.putExtra(CHAVE_EXAME, ombro.getId());
        startActivity(vaiParaFormularioSecaoActivity);
        finish();
    }


    private void carregaExame() {
        Intent dados = getIntent();

        if (dados.hasExtra(CHAVE_EXAME)) {
            ombro = ombroQueryManager.getOne((String) dados.getSerializableExtra(CHAVE_EXAME), ombroDao);
            secoes = secoesQueryManager.getOne(ombro.getExame(), secoesDao);
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
        campoDeltoideAnteriorDir = findViewById(R.id.activity_secao_forca_muscular_ombro_deltoide_anterior_ombro_dir);
        campoDeltoideAnteriorEsq = findViewById(R.id.activity_secao_forca_muscular_ombro_deltoide_anterior_ombro_esq);
        campoCoracobraquialDir = findViewById(R.id.activity_secao_forca_muscular_ombro_coracobraquial_ombro_dir);
        campoCoracobraquialEsq = findViewById(R.id.activity_secao_forca_muscular_ombro_coracobraquial_ombro_esq);
        campoGrandeDorsalDir = findViewById(R.id.activity_secao_forca_muscular_ombro_grande_dorsal_ombro_dir);
        campoGrandeDorsalEsq = findViewById(R.id.activity_secao_forca_muscular_ombro_grande_dorsal_ombro_esq);


        if (secoes.isForcaMuscular1()) {
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
    }
}

