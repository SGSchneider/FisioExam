package br.ufsm.fisioexam.ui.activity;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_EXAME;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_SECAO;

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

public class SecaoPalpacaoActivity extends AppCompatActivity {

    private Button proximo;
    private Button salvarESair;
    private Exame exame;
    private ExameDAO exameDao;
    private Secoes secoes;
    private SecoesDAO secoesDao;
    private CheckBox campoColoracaoNormal;
    private CheckBox campoColoracaoEquimose;
    private CheckBox campoColoracaoVermelhidao;
    private EditText campoLocalColoracaoDaPele;
    private SwitchCompat campoFeridas;
    private EditText campoLocalFeridas;
    private EditText campoTamanhoFeridas;
    private SwitchCompat campoCicatrizes;
    private SwitchCompat campoAderenciaCicatrizes;
    private EditText campoLocalCicatrizes;
    private SwitchCompat campoEdema;
    private SwitchCompat campoCacifoEdema;
    private EditText campoLocalEdema;
    private RadioGroup campoTemperatura;
    private EditText campoLocalTemperatura;
    private RadioGroup campoTensaoMuscular;
    private EditText campoLocalTensaoMuscular;
    private RadioGroup campoTecidoOsseo;
    private EditText campoLocalTecidoOsseo;
    private SwitchCompat campoDor;
    private EditText campoGrauDor;
    private EditText campoLocalDor;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secao_palpacao);
        inicializaDaos();
        carregaExame();
        inicializaFormulario();
        setListenerCheckSwitch();
        inicializaBotoes();
        EscondeFormulario();
    }

    private void EscondeFormulario() {
        int visibilidade;

        //coloracao
        if (campoColoracaoVermelhidao.isChecked() || campoColoracaoEquimose.isChecked()) {
            visibilidade = VISIBLE;
        } else {
            visibilidade = GONE;
        }
        campoLocalColoracaoDaPele.setVisibility(visibilidade);

        //cicatrizes
        if (campoCicatrizes.isChecked()) {
            visibilidade = VISIBLE;
        } else {
            visibilidade = GONE;
        }
        campoLocalCicatrizes.setVisibility(visibilidade);
        campoAderenciaCicatrizes.setVisibility(visibilidade);

        //feridas
        if (campoFeridas.isChecked()) {
            visibilidade = VISIBLE;
        } else {
            visibilidade = GONE;
        }
        campoLocalFeridas.setVisibility(visibilidade);
        campoTamanhoFeridas.setVisibility(visibilidade);

        //edema
        if (campoEdema.isChecked()) {
            visibilidade = VISIBLE;
        } else {
            visibilidade = GONE;
        }
        campoCacifoEdema.setVisibility(visibilidade);
        campoLocalEdema.setVisibility(visibilidade);

        //Temperatura
        if (campoTemperatura.getCheckedRadioButtonId() == R.id.activity_secao_palpacao_radio_palp_temperatura_aumento || campoTemperatura.getCheckedRadioButtonId() == R.id.activity_secao_palpacao_radio_palp_temperatura_diminuicao) {
            visibilidade = VISIBLE;
        } else {
            visibilidade = GONE;
        }
        campoLocalTemperatura.setVisibility(visibilidade);

        //Tensao Muscular
        if (campoTensaoMuscular.getCheckedRadioButtonId() == R.id.activity_secao_palpacao_radio_tensao_muscular_diminuida || campoTensaoMuscular.getCheckedRadioButtonId() == R.id.activity_secao_palpacao_radio_tensao_muscular_aumentada) {
            visibilidade = VISIBLE;
        } else {
            visibilidade = GONE;
        }
        campoLocalTensaoMuscular.setVisibility(visibilidade);

        //Tecido Osseo
        if (campoTecidoOsseo.getCheckedRadioButtonId() == R.id.activity_secao_palpacao_radio_palp_tecido_osseo_calo) {
            visibilidade = VISIBLE;
        } else {
            visibilidade = GONE;
        }
        campoLocalTecidoOsseo.setVisibility(visibilidade);

        //Dor
        if (campoDor.isChecked()) {
            visibilidade = VISIBLE;
        } else {
            visibilidade = GONE;
        }
        campoGrauDor.setVisibility(visibilidade);
        campoLocalDor.setVisibility(visibilidade);
    }

    private void setListenerCheckSwitch() {
        campoCicatrizes.setOnCheckedChangeListener((buttonView, isChecked) -> EscondeFormulario());
        campoFeridas.setOnCheckedChangeListener((buttonView, isChecked) -> EscondeFormulario());
        campoDor.setOnCheckedChangeListener((buttonView, isChecked) -> EscondeFormulario());
        campoTecidoOsseo.setOnCheckedChangeListener((buttonView, isChecked) -> EscondeFormulario());
        campoTensaoMuscular.setOnCheckedChangeListener((buttonView, isChecked) -> EscondeFormulario());
        campoEdema.setOnCheckedChangeListener((buttonView, isChecked) -> EscondeFormulario());
        campoTemperatura.setOnCheckedChangeListener((buttonView, isChecked) -> EscondeFormulario());
        campoEdema.setOnCheckedChangeListener((buttonView, isChecked) -> EscondeFormulario());
        campoCicatrizes.setOnCheckedChangeListener((buttonView, isChecked) -> EscondeFormulario());
        campoColoracaoEquimose.setOnCheckedChangeListener((buttonView, isChecked) -> EscondeFormulario());
        campoColoracaoVermelhidao.setOnCheckedChangeListener((buttonView, isChecked) -> EscondeFormulario());
        campoColoracaoNormal.setOnCheckedChangeListener((buttonView, isChecked) -> EscondeFormulario());
    }

    private void inicializaDaos() {
        FisioExamDatabase database = FisioExamDatabase.getInstance(this);
        exameDao = database.getRoomExameDAO();
        secoesDao = database.getRoomSecoesDAO();
    }

    private void inicializaBotoes() {
        proximo = findViewById(R.id.activity_secao_palpacao_button_proximo);
        salvarESair = findViewById(R.id.activity_secao_palpacao_button_salvar_e_sair);
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
        secoes.setPalpacao(true);
        secoesDao.update(secoes);

        //coloracao
        exame.setColoracaoPeleSAPalp(campoColoracaoNormal.isChecked());
        exame.setColoracaoPeleEquiPalp(campoColoracaoEquimose.isChecked());
        exame.setColoracaoPeleVermPalp(campoColoracaoVermelhidao.isChecked());
        exame.setLocalColoracaoPelePalp(campoLocalColoracaoDaPele.getText().toString());

        //feridas
        exame.setFeridasPalp(campoFeridas.isChecked());

        if (campoFeridas.isChecked()) {
            exame.setLocalFeridasPalp(campoLocalFeridas.getText().toString());
            exame.setTamFeridasPalp(campoTamanhoFeridas.getText().toString());
        }

        //cicatrizes
        exame.setCicatrizesPalp(campoCicatrizes.isChecked());

        if (campoCicatrizes.isChecked()) {
            exame.setAderenciaCicatrizesPalp(campoAderenciaCicatrizes.isChecked());
            exame.setLocalCicatrizesPalp(campoLocalCicatrizes.getText().toString());
        }

        //edema
        exame.setPresencaEdemaPalp(campoEdema.isChecked());

        if (campoEdema.isChecked()) {
            exame.setCacifoPresencaEdemaPalp(campoCacifoEdema.isChecked());
            exame.setLocalPresencaEdemaPalp(campoLocalEdema.getText().toString());
        }

        //temperatura
        if (campoTemperatura.getCheckedRadioButtonId() == R.id.activity_secao_palpacao_radio_palp_temperatura_aumento) {
            exame.setTemperaturaAumentoPalp(true);
            exame.setTemperaturaDiminuiPalp(false);
            exame.setTemperaturaNormalPalp(false);

            exame.setLocalTemperaturaPalp(campoLocalTemperatura.getText().toString());
        }
        if (campoTemperatura.getCheckedRadioButtonId() == R.id.activity_secao_palpacao_radio_palp_temperatura_diminuicao) {
            exame.setTemperaturaAumentoPalp(false);
            exame.setTemperaturaDiminuiPalp(true);
            exame.setTemperaturaNormalPalp(false);

            exame.setLocalTemperaturaPalp(campoLocalTemperatura.getText().toString());
        }
        if (campoTemperatura.getCheckedRadioButtonId() == R.id.activity_secao_palpacao_radio_palp_temperatura_normal) {
            exame.setTemperaturaAumentoPalp(false);
            exame.setTemperaturaDiminuiPalp(false);
            exame.setTemperaturaNormalPalp(true);
        }

        //tensao muscular
        if (campoTensaoMuscular.getCheckedRadioButtonId() == R.id.activity_secao_palpacao_radio_tensao_muscular_nao_ha) {
            exame.setTensaoMuscularNaoPalp(true);
            exame.setTensaoMuscularAumentadaPalp(false);
            exame.setTensaoMuscularDiminuidaPalp(false);
        }

        if (campoTensaoMuscular.getCheckedRadioButtonId() == R.id.activity_secao_palpacao_radio_tensao_muscular_aumentada) {
            exame.setTensaoMuscularNaoPalp(false);
            exame.setTensaoMuscularAumentadaPalp(true);
            exame.setTensaoMuscularDiminuidaPalp(false);

            exame.setLocalTensaoMuscularPalp(campoLocalTensaoMuscular.getText().toString());
        }

        if (campoTensaoMuscular.getCheckedRadioButtonId() == R.id.activity_secao_palpacao_radio_tensao_muscular_diminuida) {
            exame.setTensaoMuscularNaoPalp(false);
            exame.setTensaoMuscularAumentadaPalp(false);
            exame.setTensaoMuscularDiminuidaPalp(true);

            exame.setLocalTensaoMuscularPalp(campoLocalTensaoMuscular.getText().toString());
        }

        //tecido osseo
        if (campoTecidoOsseo.getCheckedRadioButtonId() == R.id.activity_secao_palpacao_radio_palp_tecido_osseo_calo) {
            exame.setTecidoOsseoPalp(true);
            exame.setLocalTecidoOsseoPalp(campoLocalTecidoOsseo.getText().toString());
        } else {
            exame.setTecidoOsseoPalp(false);
        }

        //dor
        exame.setDorPalp(campoDor.isChecked());
        if (campoDor.isChecked()) {
            exame.setGrauDorPalp(campoGrauDor.getText().toString());
            exame.setLocalDorPalp(campoLocalDor.getText().toString());
        }

        exameDao.update(exame);
    }

    private void proximoForm() {
        salva();
        Intent vaiParaFormularioSecaoActivity = new Intent(this, IntermediarioSecoesEspecificasActivity.class);
        vaiParaFormularioSecaoActivity.putExtra(CHAVE_EXAME, exame.getId());
        vaiParaFormularioSecaoActivity.putExtra(CHAVE_SECAO, 1);
        startActivity(vaiParaFormularioSecaoActivity);
        finish();
    }


    private void carregaExame() {
        Intent dados = getIntent();

        if (dados.hasExtra(CHAVE_EXAME)) {
            exame = exameDao.getOne((String) dados.getSerializableExtra(CHAVE_EXAME));
            secoes = secoesDao.getOne(exame.getId());
        }
    }


    private void inicializaFormulario() {

        campoColoracaoNormal = findViewById(R.id.activity_secao_palpacao_checkBox_coloracao_da_pele_normal);
        campoColoracaoEquimose = findViewById(R.id.activity_secao_palpacao_checkBox_coloracao_da_pele_esquimose);
        campoColoracaoVermelhidao = findViewById(R.id.activity_secao_palpacao_checkBox_coloracao_da_pele_vermelha);
        campoLocalColoracaoDaPele = findViewById(R.id.activity_secao_palpacao_local_coloracao_da_pele);
        campoFeridas = findViewById(R.id.activity_secao_palpacao_switch_palp_feridas);
        campoLocalFeridas = findViewById(R.id.activity_secao_palpacao_local_palp_feridas);
        campoTamanhoFeridas = findViewById(R.id.activity_secao_palpacao_tamanho_palp_feridas);
        campoCicatrizes = findViewById(R.id.activity_secao_palpacao_switch_palp_cicatrizes);
        campoAderenciaCicatrizes = findViewById(R.id.activity_secao_palpacao_switch_palp_cicatrizes_aderencias);
        campoLocalCicatrizes = findViewById(R.id.activity_secao_palpacao_local_palp_cicatrizes);
        campoEdema = findViewById(R.id.activity_secao_palpacao_switch_palp_edema);
        campoCacifoEdema = findViewById(R.id.activity_secao_palpacao_switch_palp_edema_cacifo);
        campoLocalEdema = findViewById(R.id.activity_secao_palpacao_local_palp_edema);
        campoTemperatura = findViewById(R.id.activity_secao_palpacao_radio_group_palp_temperatura);
        campoLocalTemperatura = findViewById(R.id.activity_secao_palpacao_local_palp_temperatura);
        campoTensaoMuscular = findViewById(R.id.activity_secao_palpacao_radio_group_palp_tensao_muscular);
        campoLocalTensaoMuscular = findViewById(R.id.activity_secao_palpacao_local_palp_tensao_muscular);
        campoTecidoOsseo = findViewById(R.id.activity_secao_palpacao_radio_group_palp_tecido_osseo);
        campoLocalTecidoOsseo = findViewById(R.id.activity_secao_palpacao_local_palp_tecido_osseo);
        campoDor = findViewById(R.id.activity_secao_palpacao_switch_palp_dor);
        campoGrauDor = findViewById(R.id.activity_secao_palpacao_grau_palp_dor);
        campoLocalDor = findViewById(R.id.activity_secao_palpacao_local_palp_dor);


        if (secoes.isPalpacao()) {
            preencheCampos();
        }
    }

    private void preencheCampos() {
        int temperaturaId;
        int tensaoMuscularId;

        campoColoracaoNormal.setChecked(exame.isColoracaoPeleSAPalp());
        campoColoracaoEquimose.setChecked(exame.isColoracaoPeleEquiPalp());
        campoColoracaoVermelhidao.setChecked(exame.isColoracaoPeleVermPalp());
        campoLocalColoracaoDaPele.setText(exame.getLocalColoracaoPelePalp());
        campoFeridas.setChecked(exame.isFeridasPalp());
        campoLocalFeridas.setText(exame.getLocalFeridasPalp());
        campoTamanhoFeridas.setText(exame.getTamFeridasPalp());
        campoCicatrizes.setChecked(exame.isCicatrizesPalp());
        campoAderenciaCicatrizes.setChecked(exame.isAderenciaCicatrizesPalp());
        campoLocalCicatrizes.setText(exame.getLocalCicatrizesPalp());
        campoEdema.setChecked(exame.isPresencaEdemaPalp());
        campoCacifoEdema.setChecked(exame.isCacifoPresencaEdemaPalp());
        campoLocalEdema.setText(exame.getLocalPresencaEdemaPalp());
        if(exame.isTemperaturaAumentoPalp()){
            temperaturaId = R.id.activity_secao_palpacao_radio_palp_temperatura_aumento;
        }
        else{
            if(exame.isTemperaturaDiminuiPalp()){
                temperaturaId = R.id.activity_secao_palpacao_radio_palp_temperatura_diminuicao;
            }
            else{
                temperaturaId = R.id.activity_secao_palpacao_radio_palp_temperatura_normal;
            }
        }
        campoTemperatura.check(temperaturaId);
        campoLocalTemperatura.setText(exame.getLocalTemperaturaPalp());
        if(exame.isTensaoMuscularAumentadaPalp()){
            tensaoMuscularId = R.id.activity_secao_palpacao_radio_tensao_muscular_aumentada;
        }
        else{
            if(exame.isTemperaturaDiminuiPalp()){
                tensaoMuscularId = R.id.activity_secao_palpacao_radio_tensao_muscular_diminuida;
            }
            else{
                tensaoMuscularId = R.id.activity_secao_palpacao_radio_tensao_muscular_nao_ha;
            }
        }
        campoTensaoMuscular.check(tensaoMuscularId);
        campoLocalTensaoMuscular.setText(exame.getLocalTensaoMuscularPalp());
        if(exame.isTecidoOsseoPalp()){
            campoTecidoOsseo.check(R.id.activity_secao_palpacao_radio_palp_tecido_osseo_calo);
        }
        else{

            campoTecidoOsseo.check(R.id.activity_secao_palpacao_radio_palp_tecido_osseo_normal);
        }
        campoLocalTecidoOsseo.setText(exame.getLocalTecidoOsseoPalp());
        campoDor.setChecked(exame.isDorPalp());
        campoGrauDor.setText(exame.getGrauDorPalp());
        campoLocalDor.setText(exame.getLocalDorPalp());
    }
}
