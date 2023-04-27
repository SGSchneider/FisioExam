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
import br.ufsm.fisioexam.model.Punho;
import br.ufsm.fisioexam.model.Secoes;

public class SecaoPerimetriaPunhoActivity extends AppCompatActivity {
    //Usar Parâmetros MAGEE (2010) por ser mais recente.


    private EditText campoInf5Esq;
    private EditText campoInf5Dir;
    private EditText campoInf10Esq;
    private EditText campoInf10Dir;
    private EditText campoInf15Esq;
    private EditText campoInf15Dir;
    private EditText campoEm8Dir;
    private EditText campoEm8Esq;

    private Button proximo;
    private Button salvarESair;
    private Punho punho;
    private PunhoDAO punhoDao;
    private Secoes secoes;
    private SecoesDAO secoesDao;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secao_perimetria_punho);
        inicializaDaos();
        carregaExame();
        inicializaFormulario();
        inicializaBotoes();
    }


    private void inicializaDaos() {
        FisioExamDatabase database = FisioExamDatabase.getInstance(this);
        punhoDao = database.getRoomPunhoDAO();
        secoesDao = database.getRoomSecoesDAO();
    }

    private void inicializaBotoes() {
        proximo = findViewById(R.id.activity_secao_perimetria_punho_button_proximo);
        salvarESair = findViewById(R.id.activity_secao_perimetria_punho_button_salvar_e_sair);
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
        secoes.setPerimetria(true);
        secoesDao.edita(secoes);
        punho.setPerimetriaInfDir5(campoInf5Dir.getText().toString());
        punho.setPerimetriaInfDir10(campoInf10Dir.getText().toString());
        punho.setPerimetriaInfDir15(campoInf15Dir.getText().toString());
        punho.setPerimetriaInfEsq5(campoInf5Esq.getText().toString());
        punho.setPerimetriaInfEsq10(campoInf10Esq.getText().toString());
        punho.setPerimetriaInfEsq15(campoInf15Esq.getText().toString());
        punho.setPerimetriaEm8Dir(campoEm8Dir.getText().toString());
        punho.setPerimetriaEm8Esq(campoEm8Esq.getText().toString());

        punhoDao.edita(punho);
    }

    private void proximoForm() {
        salva();
        Intent vaiParaFormularioSecaoActivity = new Intent(this, IntermediarioSecoesEspecificasActivity.class);
        vaiParaFormularioSecaoActivity.putExtra(CHAVE_EXAME, punho.getExame());
        vaiParaFormularioSecaoActivity.putExtra(CHAVE_SECAO, 3);
        startActivity(vaiParaFormularioSecaoActivity);
        finish();
    }


    private void carregaExame() {
        Intent dados = getIntent();

        if (dados.hasExtra(CHAVE_EXAME)) {
            punho = punhoDao.getPunho((String) dados.getSerializableExtra(CHAVE_EXAME));
            secoes = secoesDao.getSecao(punho.getExame());
        }
    }


    private void inicializaFormulario() {
        campoInf5Esq = findViewById(R.id.activity_secao_perimetria_punho_inf_5_esq);
        campoInf5Dir = findViewById(R.id.activity_secao_perimetria_punho_inf_5_dir);
        campoInf10Esq = findViewById(R.id.activity_secao_perimetria_punho_inf_10_esq);
        campoInf10Dir = findViewById(R.id.activity_secao_perimetria_punho_inf_10_dir);
        campoInf15Esq = findViewById(R.id.activity_secao_perimetria_punho_inf_15_esq);
        campoInf15Dir = findViewById(R.id.activity_secao_perimetria_punho_inf_15_dir);
        campoEm8Dir = findViewById(R.id.activity_secao_perimetria_punho_em_8_dir);
        campoEm8Esq = findViewById(R.id.activity_secao_perimetria_punho_em_8_esq);

        if (secoes.isPerimetria()) {
            preencheCampos();
        }
    }

    private void preencheCampos() {
        campoInf5Esq.setText(punho.getPerimetriaInfEsq5());
        campoInf5Dir.setText(punho.getPerimetriaInfDir5());
        campoInf10Esq.setText(punho.getPerimetriaInfEsq10());
        campoInf10Dir.setText(punho.getPerimetriaInfDir10());
        campoInf15Esq.setText(punho.getPerimetriaInfEsq15());
        campoInf15Dir.setText(punho.getPerimetriaInfDir15());
        campoEm8Dir.setText(punho.getPerimetriaEm8Dir());
        campoEm8Esq.setText(punho.getPerimetriaEm8Esq());


    }
}

