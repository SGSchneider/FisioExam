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
import br.ufsm.fisioexam.database.dao.PunhoDAO;
import br.ufsm.fisioexam.database.dao.SecoesDAO;
import br.ufsm.fisioexam.database.thread.QueryManager;
import br.ufsm.fisioexam.model.Punho;
import br.ufsm.fisioexam.model.Secoes;

public class SecaoSensibilidadePunhoActivity extends AppCompatActivity {
    //Usar Parâmetros MAGEE (2010) por ser mais recente.

    private RadioGroup campoTactil;
    private RadioGroup campoTermica;
    private RadioGroup campoDolorosa;
    private EditText campoLocalAvaliado;

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
        setContentView(R.layout.activity_secao_sensibilidade_punho);
        inicializaDaos();
        carregaExame();
        inicializaFormulario();
        inicializaBotoes();
    }


    private void inicializaDaos() {
        FisioExamDatabase database = FisioExamDatabase.getInstance(this);
        punhoDao = database.getRoomPunhoDAO();
        secoesDao = database.getRoomSecoesDAO();
        secoesQueryManager = new QueryManager<>();
        punhoQueryManager = new QueryManager<>();
    }

    private void inicializaBotoes() {
        proximo = findViewById(R.id.activity_secao_sensibilidade_punho_button_proximo);
        salvarESair = findViewById(R.id.activity_secao_sensibilidade_punho_button_salvar_e_sair);
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
        secoesQueryManager.update(secoes, secoesDao);


        if (campoTactil.getCheckedRadioButtonId() == R.id.activity_secao_sensibilidade_punho_radio_tactil_presente) {
            idTactil = CHAVE_PRESENTE;
        } else if (campoTactil.getCheckedRadioButtonId() == R.id.activity_secao_sensibilidade_punho_radio_tactil_diminuida) {
            idTactil = CHAVE_DIMINUIDA;
        } else if (campoTactil.getCheckedRadioButtonId() == R.id.activity_secao_sensibilidade_punho_radio_tactil_aumentada) {
            idTactil = CHAVE_AUMENTADA;
        } else if (campoTactil.getCheckedRadioButtonId() == R.id.activity_secao_sensibilidade_punho_radio_tactil_ausente) {
            idTactil = CHAVE_AUSENTE;
        } else {
            idTactil = "";
        }
        punho.setSensibilidadeTactil(idTactil);

        if (campoTermica.getCheckedRadioButtonId() == R.id.activity_secao_sensibilidade_punho_radio_termica_presente) {
            idTermica = CHAVE_PRESENTE;
        } else if (campoTermica.getCheckedRadioButtonId() == R.id.activity_secao_sensibilidade_punho_radio_termica_diminuida) {
            idTermica = CHAVE_DIMINUIDA;
        } else if (campoTermica.getCheckedRadioButtonId() == R.id.activity_secao_sensibilidade_punho_radio_termica_aumentada) {
            idTermica = CHAVE_AUMENTADA;
        } else if (campoTermica.getCheckedRadioButtonId() == R.id.activity_secao_sensibilidade_punho_radio_termica_ausente) {
            idTermica = CHAVE_AUSENTE;
        } else {
            idTermica = "";
        }
        punho.setSensibilidadeTermica(idTermica);

        if (campoDolorosa.getCheckedRadioButtonId() == R.id.activity_secao_sensibilidade_punho_radio_dolorosa_presente) {
            idDolorosa = CHAVE_PRESENTE;
        } else if (campoDolorosa.getCheckedRadioButtonId() == R.id.activity_secao_sensibilidade_punho_radio_dolorosa_diminuida) {
            idDolorosa = CHAVE_DIMINUIDA;
        } else if (campoDolorosa.getCheckedRadioButtonId() == R.id.activity_secao_sensibilidade_punho_radio_dolorosa_aumentada) {
            idDolorosa = CHAVE_AUMENTADA;
        } else if (campoDolorosa.getCheckedRadioButtonId() == R.id.activity_secao_sensibilidade_punho_radio_dolorosa_ausente) {
            idDolorosa = CHAVE_AUSENTE;
        } else {
            idDolorosa = "";
        }
        punho.setSensibilidadeDolorosa(idDolorosa);


        punho.setSensibilidadeLocalAvaliado(campoLocalAvaliado.getText().toString());

        punhoQueryManager.update(punho, punhoDao);
    }

    private void proximoForm() {
        salva();
        Intent vaiParaFormularioSecaoActivity = new Intent(this, IntermediarioSecoesEspecificasActivity.class);
        vaiParaFormularioSecaoActivity.putExtra(CHAVE_EXAME, punho.getExame());
        vaiParaFormularioSecaoActivity.putExtra(CHAVE_SECAO, 5);
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
        campoTactil = findViewById(R.id.activity_secao_sensibilidade_punho_radio_tactil);
        campoTermica = findViewById(R.id.activity_secao_sensibilidade_punho_radio_termica);
        campoDolorosa = findViewById(R.id.activity_secao_sensibilidade_punho_radio_dolorosa);
        campoLocalAvaliado = findViewById(R.id.activity_secao_sensibilidade_punho_local_avaliado);


        if (secoes.isSensibilidade()) {
            preencheCampos();
        }
    }

    private void preencheCampos() {
        int idTactil;
        int idTermica;
        int idDolorosa;


        if (punho.getSensibilidadeTactil() != null) {
            idTactil = switch (punho.getSensibilidadeTactil()) {
                case CHAVE_PRESENTE ->
                        R.id.activity_secao_sensibilidade_punho_radio_tactil_presente;
                case CHAVE_DIMINUIDA ->
                        R.id.activity_secao_sensibilidade_punho_radio_tactil_diminuida;
                case CHAVE_AUMENTADA ->
                        R.id.activity_secao_sensibilidade_punho_radio_tactil_aumentada;
                case CHAVE_AUSENTE -> R.id.activity_secao_sensibilidade_punho_radio_tactil_ausente;
                default -> 0;
            };
            if (idTactil != 0) {
                campoTactil.check(idTactil);
            }
        }


        if (punho.getSensibilidadeTermica() != null) {
            idTermica = switch (punho.getSensibilidadeTermica()) {
                case CHAVE_PRESENTE ->
                        R.id.activity_secao_sensibilidade_punho_radio_termica_presente;
                case CHAVE_DIMINUIDA ->
                        R.id.activity_secao_sensibilidade_punho_radio_termica_diminuida;
                case CHAVE_AUMENTADA ->
                        R.id.activity_secao_sensibilidade_punho_radio_termica_aumentada;
                case CHAVE_AUSENTE -> R.id.activity_secao_sensibilidade_punho_radio_termica_ausente;
                default -> 0;
            };
            if (idTermica != 0) {
                campoTermica.check(idTermica);
            }
        }


        if (punho.getSensibilidadeDolorosa() != null) {
            idDolorosa = switch (punho.getSensibilidadeDolorosa()) {
                case CHAVE_PRESENTE ->
                        R.id.activity_secao_sensibilidade_punho_radio_dolorosa_presente;
                case CHAVE_DIMINUIDA ->
                        R.id.activity_secao_sensibilidade_punho_radio_dolorosa_diminuida;
                case CHAVE_AUMENTADA ->
                        R.id.activity_secao_sensibilidade_punho_radio_dolorosa_aumentada;
                case CHAVE_AUSENTE ->
                        R.id.activity_secao_sensibilidade_punho_radio_dolorosa_ausente;
                default -> 0;
            };
            if (idDolorosa != 0) {
                campoTactil.check(idDolorosa);
            }
        }
        campoLocalAvaliado.setText(punho.getSensibilidadeLocalAvaliado());


    }
}

