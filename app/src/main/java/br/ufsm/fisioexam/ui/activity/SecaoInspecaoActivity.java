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
import androidx.appcompat.widget.SwitchCompat;

import br.ufsm.fisioexam.R;
import br.ufsm.fisioexam.database.FisioExamDatabase;
import br.ufsm.fisioexam.database.dao.ExameDAO;
import br.ufsm.fisioexam.database.dao.SecoesDAO;
import br.ufsm.fisioexam.model.Exame;
import br.ufsm.fisioexam.model.Secoes;

public class SecaoInspecaoActivity extends AppCompatActivity {

    private Button proximo;
    private Button salvarESair;
    private Exame exame;
    private ExameDAO exameDao;
    private Secoes secoes;
    private SecoesDAO secoesDao;
    private CheckBox campoColoracaoNormal;
    private CheckBox campoColoracaoCianose;
    private CheckBox campoColoracaoVermelhidao;
    private CheckBox campoHidratacaoHidratada;
    private CheckBox campoHidratacaoDesidratada;
    private CheckBox campoEspessuraNormal;
    private CheckBox campoEspessuraFina;
    private CheckBox campoEspessuraEspessa;
    private SwitchCompat campoCicatrizes;
    private EditText campoLocalCicatrizes;
    private EditText campoTamanhoCicatrizes;
    private SwitchCompat campoFeridas;
    private EditText campoLocalFeridas;
    private EditText campoTamanhoFeridas;
    private CheckBox campoTrofismoEutrofico;
    private CheckBox campoTrofismoHipotrofico;
    private CheckBox campoTrofismoHipertrofico;
    private EditText campoLocalTrofismo;
    private SwitchCompat campoEdema;
    private EditText campoLocalEdema;
    private CheckBox campoTecidoOsseoNormal;
    private CheckBox campoTecidoOsseoCalo;
    private CheckBox campoTecidoOsseoDeformidade;
    private CheckBox campoMarchaNormal;
    private CheckBox campoMarchaAntalgica;
    private CheckBox campoMarchaComDorNoOmbro;
    private SwitchCompat campoProtese;
    private EditText campoQualProtese;
    private SwitchCompat campoOrtese;
    private EditText campoQualOrtese;
    private SwitchCompat campoCateter;
    private EditText campoLocalCateter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secao_inspecao);
        inicializaDaos();
        carregaExame();
        inicializaFormulario();
        setListenerCheckSwitch();
        inicializaBotoes();
        EscondeFormulario();
    }

    private void EscondeFormulario() {
        int visibilidade;
        //cicatrizes
        if(campoCicatrizes.isChecked()){
            visibilidade = VISIBLE;
        }
        else{
            visibilidade = GONE;
        }
        campoLocalCicatrizes.setVisibility(visibilidade);
        campoTamanhoCicatrizes.setVisibility(visibilidade);
        //feridas
        if(campoFeridas.isChecked()){
            visibilidade = VISIBLE;
        }
        else{
            visibilidade = GONE;
        }
        campoLocalFeridas.setVisibility(visibilidade);
        campoTamanhoFeridas.setVisibility(visibilidade);
        //trofismo
        if(campoTrofismoEutrofico.isChecked() || campoTrofismoHipertrofico.isChecked() || campoTrofismoHipotrofico.isChecked()){
            visibilidade = VISIBLE;
        }else{
            visibilidade = GONE;
        }
        campoLocalTrofismo.setVisibility(visibilidade);
        //edema
        if(campoEdema.isChecked()){
            visibilidade = VISIBLE;
        }
        else{
            visibilidade = GONE;
        }
        campoLocalEdema.setVisibility(visibilidade);
        //proteses
        if(campoProtese.isChecked()){
            visibilidade = VISIBLE;
        }
        else{
            visibilidade = GONE;
        }
        campoQualProtese.setVisibility(visibilidade);
        //orteses
        if(campoOrtese.isChecked()){
            visibilidade = VISIBLE;
        }
        else{
            visibilidade = GONE;
        }
        campoQualOrtese.setVisibility(visibilidade);
        //cateter
        if(campoCateter.isChecked()){
            visibilidade = VISIBLE;
        }
        else{
            visibilidade = GONE;
        }
        campoLocalCateter.setVisibility(visibilidade);
    }

    private void setListenerCheckSwitch() {
        campoCicatrizes.setOnCheckedChangeListener((buttonView, isChecked) -> EscondeFormulario());
        campoFeridas.setOnCheckedChangeListener((buttonView, isChecked) -> EscondeFormulario());
        campoTrofismoEutrofico.setOnCheckedChangeListener((buttonView, isChecked) -> EscondeFormulario());
        campoTrofismoHipotrofico.setOnCheckedChangeListener((buttonView, isChecked) -> EscondeFormulario());
        campoTrofismoHipertrofico.setOnCheckedChangeListener((buttonView, isChecked) -> EscondeFormulario());
        campoEdema.setOnCheckedChangeListener((buttonView, isChecked) -> EscondeFormulario());
        campoProtese.setOnCheckedChangeListener((buttonView, isChecked) -> EscondeFormulario());
        campoOrtese.setOnCheckedChangeListener((buttonView, isChecked) -> EscondeFormulario());
        campoCateter.setOnCheckedChangeListener((buttonView, isChecked) -> EscondeFormulario());
    }

    private void inicializaDaos() {
        FisioExamDatabase database = FisioExamDatabase.getInstance(this);
        exameDao = database.getRoomExameDAO();
        secoesDao = database.getRoomSecoesDAO();
    }

    private void inicializaBotoes() {
        proximo = findViewById(R.id.activity_secao_inspecao_button_proximo);
        salvarESair = findViewById(R.id.activity_secao_inspecao_button_salvar_e_sair);
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
        secoes.setInspecao(true);
        secoesDao.edita(secoes);

        //coloracao
        exame.setColoracaoPeleNormalInsp(campoColoracaoNormal.isChecked());
        exame.setColoracaoPeleCianoseInsp(campoColoracaoCianose.isChecked());
        exame.setColoracaoPeleVermelhidaoInsp(campoColoracaoVermelhidao.isChecked());
        //hidratacao
        exame.setHidratacaoPeleHidrInsp(campoHidratacaoHidratada.isChecked());
        exame.setHidratacaoPeleDesidrInsp(campoHidratacaoDesidratada.isChecked());
        //espessura
        exame.setEspessuraPeleNormalInsp(campoEspessuraNormal.isChecked());
        exame.setEspessuraPeleFinaInsp(campoEspessuraFina.isChecked());
        exame.setEspessuraPeleEspessaInsp(campoEspessuraEspessa.isChecked());
        //cicatrizes
        exame.setPresencaCicatrizesInsp(campoCicatrizes.isChecked());
        exame.setLocalCicatrizesInsp(campoLocalCicatrizes.getText().toString());
        exame.setTamanhoCicatrizesInsp(campoTamanhoCicatrizes.getText().toString());
        //feridas
        exame.setPresencaFeridasInsp(campoFeridas.isChecked());
        exame.setLocalFeridasInsp(campoLocalFeridas.getText().toString());
        exame.setTamanhoFeridasInsp(campoTamanhoFeridas.getText().toString());
        //trofismo
        exame.setTrofismoMuscularEutroficoInsp(campoTrofismoEutrofico.isChecked());
        exame.setTrofismoMuscularHipertroficoInsp(campoTrofismoHipertrofico.isChecked());
        exame.setTrofismoMuscularHipotroficoInsp(campoTrofismoHipotrofico.isChecked());
        exame.setLocalTrofismoMuscularInsp(campoLocalTrofismo.getText().toString());
        //edema
        exame.setPresencaEdemaInsp(campoEdema.isChecked());
        exame.setLocalEdemaInsp(campoLocalEdema.getText().toString());
        //tecido osseo
        exame.setTecidoOsseoNormalInsp(campoTecidoOsseoNormal.isChecked());
        exame.setTecidoOsseoCaloInsp(campoTecidoOsseoCalo.isChecked());
        exame.setTecidoOsseoDeformInsp(campoTecidoOsseoDeformidade.isChecked());
        //marcha
        exame.setMarchaNormalInsp(campoMarchaNormal.isChecked());
        exame.setMarchaAntargicaInsp(campoMarchaAntalgica.isChecked());
        exame.setMarchaComDorOmbroInsp(campoMarchaComDorNoOmbro.isChecked());
        //protese
        exame.setPresencaProtesesInsp(campoProtese.isChecked());
        exame.setLocalProtesesInsp(campoQualProtese.getText().toString());
        //ortese
        exame.setPresencaOrtesesInsp(campoOrtese.isChecked());
        exame.setLocalOrtesesInsp(campoQualOrtese.getText().toString());
        //cateter e sonda
        exame.setPresencaCateteresESondasInsp(campoCateter.isChecked());
        exame.setLocalCateteresESondasInsp(campoLocalCateter.getText().toString());

        exameDao.edita(exame);
    }

    private void proximoForm() {
        salva();
        Intent vaiParaFormularioSecaoActivity = new Intent(this, SecaoPalpacaoActivity.class);
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
        campoColoracaoNormal = findViewById(R.id.activity_secao_inspecao_checkBox_pele_normal);
        campoColoracaoCianose = findViewById(R.id.activity_secao_inspecao_checkBox_pele_cianose);
        campoColoracaoVermelhidao = findViewById(R.id.activity_secao_inspecao_checkBox_pele_vermelhidao);
        campoHidratacaoHidratada = findViewById(R.id.activity_secao_inspecao_checkBox_pele_hidratada);
        campoHidratacaoDesidratada = findViewById(R.id.activity_secao_inspecao_checkBox_pele_desidratada);
        campoEspessuraNormal = findViewById(R.id.activity_secao_inspecao_checkBox_pele_espessura_normal);
        campoEspessuraFina = findViewById(R.id.activity_secao_inspecao_checkBox_pele_espessura_fina);
        campoEspessuraEspessa = findViewById(R.id.activity_secao_inspecao_checkBox_pele_espessura_espessa);
        campoCicatrizes = findViewById(R.id.activity_secao_inspecao_switch_cicatrizes);
        campoLocalCicatrizes = findViewById(R.id.activity_secao_inspecao_local_cicatrizes);
        campoTamanhoCicatrizes = findViewById(R.id.activity_secao_inspecao_tamanho_cicatrizes);
        campoFeridas = findViewById(R.id.activity_secao_inspecao_switch_feridas);
        campoLocalFeridas = findViewById(R.id.activity_secao_inspecao_local_feridas);
        campoTamanhoFeridas = findViewById(R.id.activity_secao_inspecao_tamanho_feridas);
        campoTrofismoEutrofico = findViewById(R.id.activity_secao_inspecao_checkBox_trofismo_muscular_eutrofico);
        campoTrofismoHipertrofico = findViewById(R.id.activity_secao_inspecao_checkBox_trofismo_muscular_hipertrofico);
        campoTrofismoHipotrofico = findViewById(R.id.activity_secao_inspecao_checkBox_trofismo_muscular_hipotrofico);
        campoLocalTrofismo = findViewById(R.id.activity_secao_inspecao_local_trofismo_muscular);
        campoEdema = findViewById(R.id.activity_secao_inspecao_switch_edema);
        campoLocalEdema = findViewById(R.id.activity_secao_inspecao_local_edema);
        campoTecidoOsseoNormal = findViewById(R.id.activity_secao_inspecao_checkBox_tecido_osseo_normal);
        campoTecidoOsseoCalo = findViewById(R.id.activity_secao_inspecao_checkBox_tecido_osseo_calo_osseo);
        campoTecidoOsseoDeformidade = findViewById(R.id.activity_secao_inspecao_checkBox_tecido_osseo_desalinhamento_deformidade);
        campoMarchaNormal = findViewById(R.id.activity_secao_inspecao_checkBox_marcha_normal);
        campoMarchaAntalgica = findViewById(R.id.activity_secao_inspecao_checkBox_marcha_antalgica);
        campoMarchaComDorNoOmbro = findViewById(R.id.activity_secao_inspecao_checkBox_marcha_dor_ombro);
        campoProtese = findViewById(R.id.activity_secao_inspecao_switch_protese);
        campoQualProtese = findViewById(R.id.activity_secao_inspecao_qual_protese);
        campoOrtese = findViewById(R.id.activity_secao_inspecao_switch_orteses);
        campoQualOrtese = findViewById(R.id.activity_secao_inspecao_qual_ortese);
        campoCateter = findViewById(R.id.activity_secao_inspecao_switch_cateter_sonda);
        campoLocalCateter = findViewById(R.id.activity_secao_inspecao_local_cateter_sonda);


        if (secoes.isInspecao()) {
            preencheCampos();
        }
    }

    private void preencheCampos() {
        campoColoracaoNormal.setChecked(exame.isColoracaoPeleNormalInsp());
        campoColoracaoCianose.setChecked(exame.isColoracaoPeleCianoseInsp());
        campoColoracaoVermelhidao.setChecked(exame.isColoracaoPeleVermelhidaoInsp());
        campoHidratacaoHidratada.setChecked(exame.isHidratacaoPeleHidrInsp());
        campoHidratacaoDesidratada.setChecked(exame.isHidratacaoPeleDesidrInsp());
        campoEspessuraNormal.setChecked(exame.isEspessuraPeleNormalInsp());
        campoEspessuraFina.setChecked(exame.isEspessuraPeleFinaInsp());
        campoEspessuraEspessa.setChecked(exame.isEspessuraPeleEspessaInsp());
        campoCicatrizes.setChecked(exame.isPresencaCicatrizesInsp());
        campoLocalCicatrizes.setText(exame.getLocalCicatrizesInsp());
        campoTamanhoCicatrizes.setText(exame.getTamanhoCicatrizesInsp());
        campoFeridas.setChecked(exame.isPresencaFeridasInsp());
        campoLocalFeridas.setText(exame.getLocalFeridasInsp());
        campoTamanhoFeridas.setText(exame.getTamanhoFeridasInsp());
        campoTrofismoEutrofico.setChecked(exame.isTrofismoMuscularEutroficoInsp());
        campoTrofismoHipotrofico.setChecked(exame.isTrofismoMuscularHipotroficoInsp());
        campoTrofismoHipertrofico.setChecked(exame.isTrofismoMuscularHipertroficoInsp());
        campoLocalTrofismo.setText(exame.getLocalTrofismoMuscularInsp());
        campoEdema.setChecked(exame.isPresencaEdemaInsp());
        campoLocalEdema.setText(exame.getLocalEdemaInsp());
        campoTecidoOsseoNormal.setChecked(exame.isTecidoOsseoNormalInsp());
        campoTecidoOsseoCalo.setChecked(exame.isTecidoOsseoCaloInsp());
        campoTecidoOsseoDeformidade.setChecked(exame.isTecidoOsseoDeformInsp());
        campoMarchaNormal.setChecked(exame.isMarchaNormalInsp());
        campoMarchaAntalgica.setChecked(exame.isMarchaAntargicaInsp());
        campoMarchaComDorNoOmbro.setChecked(exame.isMarchaComDorOmbroInsp());
        campoProtese.setChecked(exame.isPresencaProtesesInsp());
        campoQualProtese.setText(exame.getLocalProtesesInsp());
        campoOrtese.setChecked(exame.isPresencaOrtesesInsp());
        campoQualOrtese.setText(exame.getLocalOrtesesInsp());
        campoCateter.setChecked(exame.isPresencaCateteresESondasInsp());
        campoLocalCateter.setText(exame.getLocalCateteresESondasInsp());
    }
}
