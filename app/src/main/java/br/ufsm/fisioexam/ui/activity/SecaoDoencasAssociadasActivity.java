package br.ufsm.fisioexam.ui.activity;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_EXAME;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.ufsm.fisioexam.R;
import br.ufsm.fisioexam.database.FisioExamDatabase;
import br.ufsm.fisioexam.database.dao.ExameDAO;
import br.ufsm.fisioexam.database.dao.SecoesDAO;
import br.ufsm.fisioexam.model.Exame;
import br.ufsm.fisioexam.model.Secoes;

public class SecaoDoencasAssociadasActivity extends AppCompatActivity {
    private Button proximo;
    private Button salvarESair;
    private Exame exame;
    private ExameDAO exameDao;
    private Secoes secoes;
    private SecoesDAO secoesDao;
    private CheckBox campoHipertensao;
    private CheckBox campoDoencaCardiovascular;
    private CheckBox campoDoencaPulmonar;
    private CheckBox campoDiabetes;
    private CheckBox campoCancer;
    private CheckBox campoOutraDoenca;
    private EditText campoOutraQual;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secao_doencas_associadas);
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
        proximo = findViewById(R.id.activity_secao_doencas_associadas_button_proximo);
        salvarESair = findViewById(R.id.activity_secao_doencas_associadas_button_salvar_e_sair);
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
        secoes.setDoencasAssociadas(true);
        secoesDao.edita(secoes);

        //Salva as alterações na variável
        exame.setDoencasAssociadasHipertensao(campoHipertensao.isChecked());
        exame.setDoencasAssociadasCardiovascular(campoDoencaCardiovascular.isChecked());
        exame.setDoencasAssociadasPulmonar(campoDoencaPulmonar.isChecked());
        exame.setDoencasAssociadasDiabetes(campoDiabetes.isChecked());
        exame.setDoencasAssociadasCancer(campoCancer.isChecked());
        exame.setDoencasAssociadasOutra(campoOutraDoenca.isChecked());
        exame.setOutraDoencasAssociadas(campoOutraQual.getText().toString());

        //Salva no Banco de Dados
        exameDao.edita(exame);
    }

    private void proximoForm() {
        salva();
        Intent vaiParaFormularioSecaoActivity = new Intent(this, SecaoMedicamentosEmUsoActivity.class);
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
        campoHipertensao = findViewById(R.id.activity_secao_doencas_associadas_checkBox_hipertensao);
        campoDoencaCardiovascular = findViewById(R.id.activity_secao_doencas_associadas_checkBox_doenca_cardiovascular);
        campoDoencaPulmonar = findViewById(R.id.activity_secao_doencas_associadas_checkBox_doenca_pulmonar);
        campoDiabetes = findViewById(R.id.activity_secao_doencas_associadas_checkBox_diabete);
        campoCancer = findViewById(R.id.activity_secao_doencas_associadas_checkBox_cancer);
        campoOutraDoenca = findViewById(R.id.activity_secao_doencas_associadas_checkBox_outra_doenca_associada);
        campoOutraQual = findViewById(R.id.activity_secao_doencas_associadas_descricao_outra_doenca_associada);

        if (secoes.isDoencasAssociadas()) {
            preencheCampos();
        }
    }


    private void setListenerSwitch() {
        campoOutraDoenca.setOnCheckedChangeListener((buttonView, isChecked) -> EscondeFormulario());
    }


    private void EscondeFormulario() {
        int visibilidade;
        if (campoOutraDoenca.isChecked()) {
            visibilidade = VISIBLE;
        } else {
            visibilidade = GONE;
        }
        campoOutraQual.setVisibility(visibilidade);
    }

    private void preencheCampos() {
        campoHipertensao.setChecked(exame.isDoencasAssociadasHipertensao());
        campoDoencaCardiovascular.setChecked(exame.isDoencasAssociadasCardiovascular());
        campoDoencaPulmonar.setChecked(exame.isDoencasAssociadasPulmonar());
        campoDiabetes.setChecked(exame.isDoencasAssociadasDiabetes());
        campoCancer.setChecked(exame.isDoencasAssociadasCancer());
        campoOutraDoenca.setChecked(exame.isDoencasAssociadasOutra());
        campoOutraQual.setText(exame.getOutraDoencasAssociadas());
    }
}
