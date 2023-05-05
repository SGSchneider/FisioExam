package br.ufsm.fisioexam.ui.activity;

import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_AUMENTADA;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_AUSENTE;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_DIMINUIDA;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_EXAME;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_PRESENTE;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_SECAO;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.ufsm.fisioexam.R;
import br.ufsm.fisioexam.database.FisioExamDatabase;
import br.ufsm.fisioexam.database.dao.OmbroDAO;
import br.ufsm.fisioexam.database.dao.SecoesDAO;
import br.ufsm.fisioexam.model.Ombro;
import br.ufsm.fisioexam.model.Secoes;

public class SecaoSensibilidadeOmbroActivity extends AppCompatActivity {
    //Usar Parâmetros MAGEE (2010) por ser mais recente.

    private RadioGroup campoTactil;
    private RadioGroup campoTermica;
    private RadioGroup campoDolorosa;
    private EditText campoLocalAvaliado;

    private Button proximo;
    private Button salvarESair;
    private Ombro ombro;
    private OmbroDAO ombroDao;
    private Secoes secoes;
    private SecoesDAO secoesDao;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secao_sensibilidade_ombro);
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
        proximo = findViewById(R.id.activity_secao_sensibilidade_ombro_button_proximo);
        salvarESair = findViewById(R.id.activity_secao_sensibilidade_ombro_button_salvar_e_sair);
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
        String idTactil;
        String idTermica;
        String idDolorosa;
        secoes.setSensibilidade(true);
        secoesDao.edita(secoes);


        if (campoTactil.getCheckedRadioButtonId() == R.id.activity_secao_sensibilidade_ombro_radio_tactil_presente) {
            idTactil = CHAVE_PRESENTE;
        } else if (campoTactil.getCheckedRadioButtonId() == R.id.activity_secao_sensibilidade_ombro_radio_tactil_diminuida) {
            idTactil = CHAVE_DIMINUIDA;
        } else if (campoTactil.getCheckedRadioButtonId() == R.id.activity_secao_sensibilidade_ombro_radio_tactil_aumentada) {
            idTactil = CHAVE_AUMENTADA;
        } else if (campoTactil.getCheckedRadioButtonId() == R.id.activity_secao_sensibilidade_ombro_radio_tactil_ausente) {
            idTactil = CHAVE_AUSENTE;
        } else {
            idTactil = "";
        }
        ombro.setSensibilidadeTactil(idTactil);

        if (campoTermica.getCheckedRadioButtonId() == R.id.activity_secao_sensibilidade_ombro_radio_termica_presente) {
            idTermica = CHAVE_PRESENTE;
        } else if (campoTermica.getCheckedRadioButtonId() == R.id.activity_secao_sensibilidade_ombro_radio_termica_diminuida) {
            idTermica = CHAVE_DIMINUIDA;
        } else if (campoTermica.getCheckedRadioButtonId() == R.id.activity_secao_sensibilidade_ombro_radio_termica_aumentada) {
            idTermica = CHAVE_AUMENTADA;
        } else if (campoTermica.getCheckedRadioButtonId() == R.id.activity_secao_sensibilidade_ombro_radio_termica_ausente) {
            idTermica = CHAVE_AUSENTE;
        } else {
            idTermica = "";
        }
        ombro.setSensibilidadeTermica(idTermica);

        if (campoDolorosa.getCheckedRadioButtonId() == R.id.activity_secao_sensibilidade_ombro_radio_dolorosa_presente) {
            idDolorosa = CHAVE_PRESENTE;
        } else if (campoDolorosa.getCheckedRadioButtonId() == R.id.activity_secao_sensibilidade_ombro_radio_dolorosa_diminuida) {
            idDolorosa = CHAVE_DIMINUIDA;
        } else if (campoDolorosa.getCheckedRadioButtonId() == R.id.activity_secao_sensibilidade_ombro_radio_dolorosa_aumentada) {
            idDolorosa = CHAVE_AUMENTADA;
        } else if (campoDolorosa.getCheckedRadioButtonId() == R.id.activity_secao_sensibilidade_ombro_radio_dolorosa_ausente) {
            idDolorosa = CHAVE_AUSENTE;
        } else {
            idDolorosa = "";
        }
        ombro.setSensibilidadeDolorosa(idDolorosa);

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
            ombro = ombroDao.getOmbro((String) dados.getSerializableExtra(CHAVE_EXAME));
            secoes = secoesDao.getSecao(ombro.getExame());
        }
    }


    private void inicializaFormulario() {
        campoTactil = findViewById(R.id.activity_secao_sensibilidade_ombro_radio_tactil);
        campoTermica = findViewById(R.id.activity_secao_sensibilidade_ombro_radio_termica);
        campoDolorosa = findViewById(R.id.activity_secao_sensibilidade_ombro_radio_dolorosa);
        campoLocalAvaliado = findViewById(R.id.activity_secao_sensibilidade_ombro_local_avaliado);


        if (secoes.isSensibilidade()) {
            preencheCampos();
        }
    }

    private void preencheCampos() {
        int idTactil;
        int idTermica;
        int idDolorosa;


        if (ombro.getSensibilidadeTactil() != null) {
            switch (ombro.getSensibilidadeTactil()) {
                case CHAVE_PRESENTE:
                    idTactil = R.id.activity_secao_sensibilidade_ombro_radio_tactil_presente;
                    break;
                case CHAVE_DIMINUIDA:
                    idTactil = R.id.activity_secao_sensibilidade_ombro_radio_tactil_diminuida;
                    break;
                case CHAVE_AUMENTADA:
                    idTactil = R.id.activity_secao_sensibilidade_ombro_radio_tactil_aumentada;
                    break;
                case CHAVE_AUSENTE:
                    idTactil = R.id.activity_secao_sensibilidade_ombro_radio_tactil_ausente;
                    break;
                default:
                    idTactil = 0;
            }
            if (idTactil != 0) {
                campoTactil.check(idTactil);
            }
        }


        if (ombro.getSensibilidadeTermica() != null) {
            switch (ombro.getSensibilidadeTermica()) {
                case CHAVE_PRESENTE:
                    idTermica = R.id.activity_secao_sensibilidade_ombro_radio_termica_presente;
                    break;
                case CHAVE_DIMINUIDA:
                    idTermica = R.id.activity_secao_sensibilidade_ombro_radio_termica_diminuida;
                    break;
                case CHAVE_AUMENTADA:
                    idTermica = R.id.activity_secao_sensibilidade_ombro_radio_termica_aumentada;
                    break;
                case CHAVE_AUSENTE:
                    idTermica = R.id.activity_secao_sensibilidade_ombro_radio_termica_ausente;
                    break;
                default:
                    idTermica = 0;
            }
            if (idTermica != 0) {
                campoTermica.check(idTermica);
            }
        }


        if (ombro.getSensibilidadeDolorosa() != null) {
            switch (ombro.getSensibilidadeDolorosa()) {
                case CHAVE_PRESENTE:
                    idDolorosa = R.id.activity_secao_sensibilidade_ombro_radio_dolorosa_presente;
                    break;
                case CHAVE_DIMINUIDA:
                    idDolorosa = R.id.activity_secao_sensibilidade_ombro_radio_dolorosa_diminuida;
                    break;
                case CHAVE_AUMENTADA:
                    idDolorosa = R.id.activity_secao_sensibilidade_ombro_radio_dolorosa_aumentada;
                    break;
                case CHAVE_AUSENTE:
                    idDolorosa = R.id.activity_secao_sensibilidade_ombro_radio_dolorosa_ausente;
                    break;
                default:
                    idDolorosa = 0;
            }
            if (idDolorosa != 0) {
                campoTactil.check(idDolorosa);
            }
        }
        campoLocalAvaliado.setText(ombro.getSensibilidadeLocalAvaliado());


    }
}

