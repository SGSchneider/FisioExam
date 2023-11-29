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
import br.ufsm.fisioexam.database.dao.PunhoDAO;
import br.ufsm.fisioexam.database.dao.SecoesDAO;
import br.ufsm.fisioexam.database.thread.QueryManager;
import br.ufsm.fisioexam.model.Punho;
import br.ufsm.fisioexam.model.Secoes;

public class SecaoForcaMuscularPunhoActivity extends AppCompatActivity {

    private EditText campoFlexorRadialDir;
    private EditText campoFlexorRadialEsq;
    private EditText campoPalmarLongoDir;
    private EditText campoPalmarLongoEsq;
    private EditText campoFlexorUlnarDir;
    private EditText campoFlexorUlnarEsq;
    private EditText campoDinamometroDir;
    private EditText campoDinamometroEsq;
    private EditText campoExtensorRadialLongoDir;
    private EditText campoExtensorRadialLongoEsq;
    private EditText campoExtensorRadialCurtoDir;
    private EditText campoExtensorRadialCurtoEsq;
    private EditText campoExtensorUlnarDir;
    private EditText campoExtensorUlnarEsq;



    private Button proximo;
    private Button salvarESair;
    private Punho punho;
    private PunhoDAO punhoDao;
    private QueryManager<Punho> punhoQueryManager;
    private Secoes secoes;
    private SecoesDAO secoesDao;
    private QueryManager<Secoes> secoesQueryManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secao_forca_muscular_punho);
        inicializaDaos();
        carregaExame();
        inicializaFormulario();
        inicializaBotoes();
    }


    private void inicializaDaos() {
        FisioExamDatabase database = FisioExamDatabase.getInstance(this);
        punhoDao = database.getRoomPunhoDAO();
        secoesDao = database.getRoomSecoesDAO();
        punhoQueryManager = new QueryManager<>();
        secoesQueryManager = new QueryManager<>();
    }

    private void inicializaBotoes() {
        proximo = findViewById(R.id.activity_secao_forca_muscular_punho_button_proximo);
        salvarESair = findViewById(R.id.activity_secao_forca_muscular_punho_button_salvar_e_sair);
        configuraListenersDeClique();
    }

    private void vaiParaAjuda(Class<?> classe) {
        Intent vaiParaAjudaActivity = new Intent(this, classe);
        startActivity(vaiParaAjudaActivity);
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
        secoes.setForcaMuscular1(true);
        secoesQueryManager.update(secoes, secoesDao);

        punho.setForcaMuscularFlexorRadialDir(campoFlexorRadialDir.getText().toString());
        punho.setForcaMuscularFlexorRadialEsq(campoFlexorRadialEsq.getText().toString());
        punho.setForcaMuscularPalmarLongoDir(campoPalmarLongoDir.getText().toString());
        punho.setForcaMuscularPalmarLongoEsq(campoPalmarLongoEsq.getText().toString());
        punho.setForcaMuscularFlexorUlnarDir(campoFlexorUlnarDir.getText().toString());
        punho.setForcaMuscularFlexorUlnarEsq(campoFlexorUlnarEsq.getText().toString());
        punho.setForcaMuscularDinamometroDir(campoDinamometroDir.getText().toString());
        punho.setForcaMuscularDinamometroEsq(campoDinamometroEsq.getText().toString());
        punho.setForcaMuscularExtensorRadialLongoDir(campoExtensorRadialLongoDir.getText().toString());
        punho.setForcaMuscularExtensorRadialLongoEsq(campoExtensorRadialLongoEsq.getText().toString());
        punho.setForcaMuscularExtensorRadialCurtoDir(campoExtensorRadialCurtoDir.getText().toString());
        punho.setForcaMuscularExtensorRadialCurtoEsq(campoExtensorRadialCurtoEsq.getText().toString());
        punho.setForcaMuscularExtensorUlnarDoCarpoDir(campoExtensorUlnarDir.getText().toString());
        punho.setForcaMuscularExtensorUlnarDoCarpoEsq(campoExtensorUlnarEsq.getText().toString());

        punhoQueryManager.update(punho, punhoDao);
    }

    private void proximoForm() {
        salva();
        Intent vaiParaFormularioSecaoActivity = new Intent(this, IntermediarioSecoesEspecificasActivity.class);
        vaiParaFormularioSecaoActivity.putExtra(CHAVE_EXAME, punho.getExame());
        vaiParaFormularioSecaoActivity.putExtra(CHAVE_SECAO, 4);
        startActivity(vaiParaFormularioSecaoActivity);
        finish();
    }


    private void carregaExame() {
        Intent dados = getIntent();

        if (dados.hasExtra(CHAVE_EXAME)) {
            punho = punhoQueryManager.getOne((String) dados.getSerializableExtra(CHAVE_EXAME), punhoDao);
            secoes = secoesQueryManager.getOne(punho.getExame(), secoesDao);
        }
    }


    private void inicializaFormulario() {
        campoFlexorRadialDir = findViewById(R.id.activity_secao_forca_muscular_punho_flexor_radial_dir);
        campoFlexorRadialEsq = findViewById(R.id.activity_secao_forca_muscular_punho_flexor_radial_esq);
        campoPalmarLongoDir = findViewById(R.id.activity_secao_forca_muscular_punho_palmar_longo_dir);
        campoPalmarLongoEsq = findViewById(R.id.activity_secao_forca_muscular_punho_palmar_longo_esq);
        campoFlexorUlnarDir = findViewById(R.id.activity_secao_forca_muscular_punho_flexor_ulnar_dir);
        campoFlexorUlnarEsq = findViewById(R.id.activity_secao_forca_muscular_punho_flexor_ulnar_esq);
        campoDinamometroDir = findViewById(R.id.activity_secao_forca_muscular_punho_dinamometro_dir);
        campoDinamometroEsq = findViewById(R.id.activity_secao_forca_muscular_punho_dinamometro_esq);
        campoExtensorRadialLongoDir = findViewById(R.id.activity_secao_forca_muscular_punho_extensor_radial_longo_punho_dir);
        campoExtensorRadialLongoEsq = findViewById(R.id.activity_secao_forca_muscular_punho_extensor_radial_longo_punho_esq);
        campoExtensorRadialCurtoDir = findViewById(R.id.activity_secao_forca_muscular_punho_extensor_radial_curto_punho_dir);
        campoExtensorRadialCurtoEsq = findViewById(R.id.activity_secao_forca_muscular_punho_extensor_radial_curto_punho_esq);
        campoExtensorUlnarDir = findViewById(R.id.activity_secao_forca_muscular_punho_extensor_ulnar_punho_dir);
        campoExtensorUlnarEsq = findViewById(R.id.activity_secao_forca_muscular_punho_extensor_ulnar_punho_esq);

        if (secoes.isForcaMuscular1()) {
            preencheCampos();
        }
    }

    private void preencheCampos() {
        campoFlexorRadialDir.setText(punho.getForcaMuscularFlexorRadialDir());
        campoFlexorRadialEsq.setText(punho.getForcaMuscularFlexorRadialEsq());
        campoPalmarLongoDir.setText(punho.getForcaMuscularPalmarLongoDir());
        campoPalmarLongoEsq.setText(punho.getForcaMuscularPalmarLongoEsq());
        campoFlexorUlnarDir.setText(punho.getForcaMuscularFlexorUlnarDir());
        campoFlexorUlnarEsq.setText(punho.getForcaMuscularFlexorUlnarEsq());
        campoDinamometroDir.setText(punho.getForcaMuscularDinamometroDir());
        campoDinamometroEsq.setText(punho.getForcaMuscularDinamometroEsq());
        campoExtensorRadialLongoDir.setText(punho.getForcaMuscularExtensorRadialLongoDir());
        campoExtensorRadialLongoEsq.setText(punho.getForcaMuscularExtensorRadialLongoEsq());
        campoExtensorRadialCurtoDir.setText(punho.getForcaMuscularExtensorRadialCurtoDir());
        campoExtensorRadialCurtoEsq.setText(punho.getForcaMuscularExtensorRadialCurtoEsq());
        campoExtensorUlnarDir.setText(punho.getForcaMuscularExtensorUlnarDoCarpoDir());
        campoExtensorUlnarEsq.setText(punho.getForcaMuscularExtensorUlnarDoCarpoEsq());

    }
}

