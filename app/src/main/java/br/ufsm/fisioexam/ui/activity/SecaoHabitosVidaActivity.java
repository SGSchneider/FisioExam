package br.ufsm.fisioexam.ui.activity;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.APTO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CASA;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_EXAME;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import br.ufsm.fisioexam.R;
import br.ufsm.fisioexam.database.FisioExamDatabase;
import br.ufsm.fisioexam.database.dao.ExameDAO;
import br.ufsm.fisioexam.database.dao.SecoesDAO;
import br.ufsm.fisioexam.model.Exame;
import br.ufsm.fisioexam.model.Secoes;

public class SecaoHabitosVidaActivity extends AppCompatActivity {
    private Button proximo;
    private Button salvarESair;
    private Exame exame;
    private ExameDAO exameDao;
    private Secoes secoes;
    private SecoesDAO secoesDao;


    private RadioGroup campoMoradia;
    private EditText campoOutraMoradia;
    private SwitchCompat campoTabagista;
    private EditText campoQuantoCigarro;
    private SwitchCompat campoEtilista;
    private EditText campoQuantoAlcool;
    private SwitchCompat campoAtividadeFisica;
    private EditText campoQualAtividadeFisica;
    private EditText campoQuantaAtividadeFisica;
    private CheckBox campoSal;
    private CheckBox campoFritura;
    private CheckBox campoAcucar;
    private SwitchCompat campoLazer;
    private EditText campoQualLazer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secao_habitos_vida);
        inicializaDaos();
        carregaExame();
        inicializaFormulario();
        inicializaBotoes();
        setListenerSwitch();
        EscondeFormulario();
    }

    private void inicializaDaos() {
        FisioExamDatabase database = FisioExamDatabase.getInstance(this);
        exameDao = database.getRoomExameDAO();
        secoesDao = database.getRoomSecoesDAO();
    }

    private void inicializaBotoes() {
        proximo = findViewById(R.id.activity_secao_habitos_vida_button_proximo);
        salvarESair = findViewById(R.id.activity_secao_habitos_vida_button_salvar_e_sair);
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
        secoes.setHistoriaSocial(true);
        secoesDao.edita(secoes);

        //Salva as alterações na variável
        int idMoradia;
        idMoradia = campoMoradia.getCheckedRadioButtonId();
        if (idMoradia == R.id.activity_secao_habitos_vida_radio_moradia_casa) {
            exame.setMoradia(CASA);
        } else if (idMoradia == R.id.activity_secao_habitos_vida_radio_moradia_apartamento) {
            exame.setMoradia(APTO);
        } else if (idMoradia == R.id.activity_secao_habitos_vida_radio_moradia_outra) {
            exame.setMoradia(campoOutraMoradia.getText().toString());
        }


        exame.setTabagista(campoTabagista.isChecked());
        if (exame.isTabagista()) {
            exame.setQuantoCigarro(campoQuantoCigarro.getText().toString());
        }

        exame.setEtilista(campoEtilista.isChecked());
        if (exame.isEtilista()) {
            exame.setQuantoCigarro(campoQuantoAlcool.getText().toString());
        }

        exame.setAtividadeFisica(campoAtividadeFisica.isChecked());
        if (exame.isAtividadeFisica()) {
            exame.setQualAtividadeFisica(campoQualAtividadeFisica.getText().toString());
            exame.setQuantaAtividadeFisica(campoQuantaAtividadeFisica.getText().toString());
        }

        exame.setRestricaoAlimentacaoSal(campoSal.isChecked());
        exame.setRestricaoAlimentacaoAcucar(campoAcucar.isChecked());
        exame.setRestricaoAlimentacaoFritura(campoFritura.isChecked());

        exame.setLazer(campoLazer.isChecked());
        if (exame.isLazer()) {
            exame.setQualLazer(campoQualLazer.getText().toString());
        }

        //Salva no Banco de Dados
        exameDao.edita(exame);
    }

    private void proximoForm() {
        salva();
        Intent vaiParaFormularioSecaoActivity = new Intent(this, SecaoExamesComplementaresActivity.class);
        vaiParaFormularioSecaoActivity.putExtra(CHAVE_EXAME, exame.getId());
        startActivity(vaiParaFormularioSecaoActivity);
        finish();
    }


    private void carregaExame() {
        Intent dados = getIntent();

        if (dados.hasExtra(CHAVE_EXAME)) {
            exame = exameDao.getExame((String) dados.getSerializableExtra(CHAVE_EXAME));
            secoes = secoesDao.getSecao(exame.getId());
        }
    }


    private void inicializaFormulario() {
        campoMoradia = findViewById(R.id.activity_secao_habitos_vida_radio_group_moradia);
        campoOutraMoradia = findViewById(R.id.activity_secao_habitos_vida_descricao_outra_moradia);
        campoTabagista = findViewById(R.id.activity_secao_habitos_vida_switch_tabagista);
        campoQuantoCigarro = findViewById(R.id.activity_secao_habitos_vida_quantidade_cigarros);
        campoEtilista = findViewById(R.id.activity_secao_habitos_vida_switch_etilista);
        campoQuantoAlcool = findViewById(R.id.activity_secao_habitos_vida_quantidade_alcool);
        campoAtividadeFisica = findViewById(R.id.activity_secao_habitos_vida_switch_atividade_fisica);
        campoQualAtividadeFisica = findViewById(R.id.activity_secao_habitos_vida_qual_atividade_fisica);
        campoQuantaAtividadeFisica = findViewById(R.id.activity_secao_habitos_vida_atividade_fisica_semanal);
        campoSal = findViewById(R.id.activity_secao_habitos_vida_checkBox_sal);
        campoFritura = findViewById(R.id.activity_secao_habitos_vida_checkBox_frituras);
        campoAcucar = findViewById(R.id.activity_secao_habitos_vida_checkBox_acucar);
        campoLazer = findViewById(R.id.activity_secao_habitos_vida_lazer);
        campoQualLazer = findViewById(R.id.activity_secao_habitos_vida_qual_lazer);

        if (secoes.isHistoriaSocial()) {
            preencheCampos();
        }
    }


    private void setListenerSwitch() {
        campoMoradia.setOnCheckedChangeListener((buttonView, isChecked) -> EscondeFormulario());
        campoTabagista.setOnCheckedChangeListener((buttonView, isChecked) -> EscondeFormulario());
        campoEtilista.setOnCheckedChangeListener((buttonView, isChecked) -> EscondeFormulario());
        campoAtividadeFisica.setOnCheckedChangeListener((buttonView, isChecked) -> EscondeFormulario());
        campoLazer.setOnCheckedChangeListener((buttonView, isChecked) -> EscondeFormulario());
    }


    private void EscondeFormulario() {
        int visibilidade;

        if (campoMoradia.getCheckedRadioButtonId() == R.id.activity_secao_habitos_vida_radio_moradia_outra) {
            visibilidade = VISIBLE;
        } else {
            visibilidade = GONE;
        }
        campoOutraMoradia.setVisibility(visibilidade);

        if (campoTabagista.isChecked()) {
            visibilidade = VISIBLE;
        } else {
            visibilidade = GONE;
        }
        campoQuantoCigarro.setVisibility(visibilidade);

        if (campoEtilista.isChecked()) {
            visibilidade = VISIBLE;
        } else {
            visibilidade = GONE;
        }
        campoQuantoAlcool.setVisibility(visibilidade);

        if (campoAtividadeFisica.isChecked()) {
            visibilidade = VISIBLE;
        } else {
            visibilidade = GONE;
        }
        campoQuantaAtividadeFisica.setVisibility(visibilidade);
        campoQualAtividadeFisica.setVisibility(visibilidade);

        if (campoLazer.isChecked()) {
            visibilidade = VISIBLE;
        } else {
            visibilidade = GONE;
        }
        campoQualLazer.setVisibility(visibilidade);

    }

    private void preencheCampos() {


        String moradia = exame.getMoradia();
        int idMoradia;
        if (moradia != null) {
            switch (moradia) {
                case CASA:
                    idMoradia = R.id.activity_secao_habitos_vida_radio_moradia_casa;
                    break;
                case APTO:
                    idMoradia = R.id.activity_secao_habitos_vida_radio_moradia_apartamento;
                    break;
                default:
                    idMoradia = R.id.activity_secao_habitos_vida_radio_moradia_outra;
                    campoOutraMoradia.setText(moradia);
                    break;
            }
            campoMoradia.check(idMoradia);
        }


        campoTabagista.setChecked(exame.isTabagista());
        if (exame.isTabagista()) {
            campoQuantoCigarro.setText(exame.getQuantoCigarro());
        }

        campoEtilista.setChecked(exame.isEtilista());
        if (exame.isEtilista()) {
            campoQuantoAlcool.setText(exame.getQuantoAlcool());
        }

        campoAtividadeFisica.setChecked(exame.isAtividadeFisica());
        if (exame.isAtividadeFisica()) {
            campoQuantaAtividadeFisica.setText(exame.getQuantaAtividadeFisica());
            campoQualAtividadeFisica.setText(exame.getQualAtividadeFisica());
        }

        campoSal.setChecked(exame.isRestricaoAlimentacaoSal());
        campoAcucar.setChecked(exame.isRestricaoAlimentacaoAcucar());
        campoFritura.setChecked(exame.isRestricaoAlimentacaoFritura());

        campoLazer.setChecked(exame.isLazer());
        if (exame.isLazer()) {
            campoQualLazer.setText(exame.getQualLazer());
        }
    }
}
