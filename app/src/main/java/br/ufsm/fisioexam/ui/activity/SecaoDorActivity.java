package br.ufsm.fisioexam.ui.activity;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.ACORDAR;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_EXAME;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CONSTANTE;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.DECORRER;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.FINALDIA;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;


import br.ufsm.fisioexam.R;
import br.ufsm.fisioexam.database.FisioExamDatabase;
import br.ufsm.fisioexam.database.dao.ExameDAO;
import br.ufsm.fisioexam.database.dao.SecoesDAO;
import br.ufsm.fisioexam.database.thread.QueryManager;
import br.ufsm.fisioexam.model.Exame;
import br.ufsm.fisioexam.model.Secoes;

public class SecaoDorActivity extends AppCompatActivity {

    private Button proximo;
    private Button salvarESair;
    private Exame exame;
    private ExameDAO exameDao;
    private QueryManager<Exame> exameQueryManager;
    private Secoes secoes;
    private SecoesDAO secoesDao;
    private QueryManager<Secoes> secoesQueryManager;
    private SwitchCompat campoSenteDor;
    private EditText campoDorHaQuantoTempo;
    private TextView textoTipoDor;
    private CheckBox campoDorIrradiada;
    private CheckBox campoDorQueimacao;
    private CheckBox campoDorPontada;
    private CheckBox campoDorPeso;
    private CheckBox campoDorFormigamento;
    private CheckBox campoDorOutra;
    private EditText campoTipoDeDor;
    private TextView textoAparicaoDor;
    private RadioGroup campoAparicaoDor;
    private SwitchCompat campoDorRepouso;
    private TextView textoIntensidadeDor;
    private TextView textoValorIntensidadeDor;
    private SeekBar campoIntensidadeDor;
    private TextView textoLocaisDor;
    private EditText campoLocaisDor;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secao_dor);
        inicializaDaos();
        carregaExame();
        inicializaFormulario();
        inicializaBotoes();
        setListenerSwitchDor();
        setListenerCheckOutraDor();
        setListenerUsoSeekbarIntensidadeDor();
        EscondeFormularioDor();
    }

    private void inicializaDaos() {
        FisioExamDatabase database = FisioExamDatabase.getInstance(this);
        exameDao = database.getRoomExameDAO();
        secoesDao = database.getRoomSecoesDAO();
        exameQueryManager = new QueryManager<>();
        secoesQueryManager = new QueryManager<>();
    }

    private void inicializaBotoes() {
        proximo = findViewById(R.id.activity_secao_dor_button_proximo);
        salvarESair = findViewById(R.id.activity_secao_dor_button_salvar_e_sair);
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
        secoes.setDor(true);
        secoesQueryManager.update(secoes, secoesDao);

        //Salva as alterações na variável
        exame.setSenteDor(campoSenteDor.isChecked());
        exame.setDorIrradiada(campoDorIrradiada.isChecked());
        exame.setDorQueimacao(campoDorQueimacao.isChecked());
        exame.setDorPontada(campoDorPontada.isChecked());
        exame.setDorPeso(campoDorPeso.isChecked());
        exame.setDorFormigamento(campoDorFormigamento.isChecked());
        exame.setDorOutra(campoDorOutra.isChecked());
        if (campoDorOutra.isChecked()) {
            exame.setTipoDeDor(campoTipoDeDor.getText().toString());
        } else {
            if(campoDorIrradiada.isChecked()){
                exame.setTipoDeDor("Irradiada");
            }
            if(campoDorQueimacao.isChecked()){
                exame.setTipoDeDor("Queimação");
            }
            if(campoDorPontada.isChecked()){
                exame.setTipoDeDor("Pontada");
            }
            if(campoDorPeso.isChecked()){
                exame.setTipoDeDor("Peso");
            }
            if(campoDorFormigamento.isChecked()){
                exame.setTipoDeDor("Formigamento");
            }
        }
        exame.setDorHaQuantoTempo(campoDorHaQuantoTempo.getText().toString());
        int idAparicaoDor = campoAparicaoDor.getCheckedRadioButtonId();
        if (idAparicaoDor == R.id.activity_secao_dor_radio_acordar) {
            exame.setAparicaoDor(ACORDAR);
        }
        if (idAparicaoDor == R.id.activity_secao_dor_radio_decorrer_dia) {
            exame.setAparicaoDor(DECORRER);
        }
        if (idAparicaoDor == R.id.activity_secao_dor_radio_fim_dia) {
            exame.setAparicaoDor(FINALDIA);
        }
        if (idAparicaoDor == R.id.activity_secao_dor_radio_constante) {
            exame.setAparicaoDor(CONSTANTE);
        }
        exame.setDorRepouso(campoDorRepouso.isChecked());
        exame.setIntensidadeDor(campoIntensidadeDor.getProgress());
        exame.setLocaisDor(campoLocaisDor.getText().toString());

        //Salva no Banco de Dados
        exameQueryManager.update(exame, exameDao);
    }

    private void proximoForm() {
        salva();
        Intent vaiParaFormularioSecaoActivity = new Intent(this, SecaoTratamentoAnteriorActivity.class);
        vaiParaFormularioSecaoActivity.putExtra(CHAVE_EXAME, exame.getId());
        startActivity(vaiParaFormularioSecaoActivity);
        finish();
    }


    private void carregaExame() {
        Intent dados = getIntent();

        if (dados.hasExtra(CHAVE_EXAME)) {
            exame = exameQueryManager.getOne((String) dados.getSerializableExtra(CHAVE_EXAME), exameDao);
            secoes = secoesQueryManager.getOne(exame.getId(), secoesDao);
        }
    }


    private void inicializaFormulario() {
        campoSenteDor = findViewById(R.id.activity_secao_dor_switch);
        textoTipoDor = findViewById(R.id.activity_secao_dor_texto_checkBox_tipo_dor);
        campoDorIrradiada = findViewById(R.id.activity_secao_dor_checkBox_tipo_dor_irradiada);
        campoDorQueimacao = findViewById(R.id.activity_secao_dor_checkBox_tipo_dor_queimacao);
        campoDorPontada = findViewById(R.id.activity_secao_dor_checkBox_tipo_dor_pontada);
        campoDorPeso = findViewById(R.id.activity_secao_dor_checkBox_tipo_dor_peso);
        campoDorFormigamento = findViewById(R.id.activity_secao_dor_checkBox_tipo_dor_formigamento);
        campoDorOutra = findViewById(R.id.activity_secao_dor_checkBox_tipo_dor_outra);
        campoTipoDeDor = findViewById(R.id.activity_secao_dor_descricao_outra_dor);
        campoDorHaQuantoTempo = findViewById(R.id.activity_secao_dor_ha_quanto_tempo);
        textoAparicaoDor = findViewById(R.id.activity_secao_dor_texto_radio_group_aparicao_dor);
        campoAparicaoDor = findViewById(R.id.activity_secao_dor_radio_group_aparicao_dor);
        campoDorRepouso = findViewById(R.id.activity_secao_dor_switch_dor_repouso);
        textoIntensidadeDor = findViewById(R.id.activity_secao_dor_texto_seekbar_intensidade_dor);
        textoValorIntensidadeDor = findViewById(R.id.activity_secao_dor_valor_seekbar_intensidade_dor);
        campoIntensidadeDor = findViewById(R.id.activity_secao_dor_seekBar_intensidade_dor);
        textoLocaisDor = findViewById(R.id.activity_secao_dor_texto_local_da_dor);
        campoLocaisDor = findViewById(R.id.activity_secao_dor_local_da_dor);

        if (secoes.isDor()) {
            preencheCampos();
        }
    }


    private void setListenerSwitchDor() {
        campoSenteDor.setOnCheckedChangeListener((buttonView, isChecked) -> EscondeFormularioDor());
    }

    private void setListenerCheckOutraDor() {
        campoDorOutra.setOnCheckedChangeListener((buttonView, isChecked) -> AtivaDescricaoDor());
    }

    private void setListenerUsoSeekbarIntensidadeDor() {
        campoIntensidadeDor.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textoValorIntensidadeDor.setText(String.valueOf(progress));
                switch (progress) {
                    case 0 -> textoValorIntensidadeDor.setBackgroundColor(getColor(R.color.dor0));
                    case 1 -> textoValorIntensidadeDor.setBackgroundColor(getColor(R.color.dor1));
                    case 2 -> textoValorIntensidadeDor.setBackgroundColor(getColor(R.color.dor2));
                    case 3 -> textoValorIntensidadeDor.setBackgroundColor(getColor(R.color.dor3));
                    case 4 -> textoValorIntensidadeDor.setBackgroundColor(getColor(R.color.dor4));
                    case 5 -> textoValorIntensidadeDor.setBackgroundColor(getColor(R.color.dor5));
                    case 6 -> textoValorIntensidadeDor.setBackgroundColor(getColor(R.color.dor6));
                    case 7 -> textoValorIntensidadeDor.setBackgroundColor(getColor(R.color.dor7));
                    case 8 -> textoValorIntensidadeDor.setBackgroundColor(getColor(R.color.dor8));
                    case 9 -> textoValorIntensidadeDor.setBackgroundColor(getColor(R.color.dor9));
                    case 10 -> textoValorIntensidadeDor.setBackgroundColor(getColor(R.color.dor10));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                textoValorIntensidadeDor.setVisibility(VISIBLE);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textoValorIntensidadeDor.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void EscondeFormularioDor() {
        int visibilidade;
        if (campoSenteDor.isChecked()) {
            visibilidade = VISIBLE;

        } else {
            visibilidade = GONE;
        }
        textoTipoDor.setVisibility(visibilidade);
        campoDorIrradiada.setVisibility(visibilidade);
        campoDorQueimacao.setVisibility(visibilidade);
        campoDorPontada.setVisibility(visibilidade);
        campoDorPeso.setVisibility(visibilidade);
        campoDorFormigamento.setVisibility(visibilidade);
        campoDorOutra.setVisibility(visibilidade);
        AtivaDescricaoDor(visibilidade);
        campoDorHaQuantoTempo.setVisibility(visibilidade);
        textoAparicaoDor.setVisibility(visibilidade);
        campoAparicaoDor.setVisibility(visibilidade);
        campoDorRepouso.setVisibility(visibilidade);
        textoIntensidadeDor.setVisibility(visibilidade);
        textoValorIntensidadeDor.setVisibility(visibilidade);
        campoIntensidadeDor.setVisibility(visibilidade);
        textoLocaisDor.setVisibility(visibilidade);
        campoLocaisDor.setVisibility(visibilidade);

    }

    private void AtivaDescricaoDor() {
        AtivaDescricaoDor(VISIBLE);
    }

    private void AtivaDescricaoDor(int visibilidade) {
        if (visibilidade == GONE) {
            campoTipoDeDor.setVisibility(GONE);
        } else {
            if (campoDorOutra.isChecked()) {
                campoTipoDeDor.setVisibility(VISIBLE);
            } else {
                campoTipoDeDor.setVisibility(GONE);
            }
        }
    }

    private void preencheCampos() {
        campoSenteDor.setChecked(exame.isSenteDor());
        campoDorIrradiada.setChecked(exame.isDorIrradiada());
        campoDorQueimacao.setChecked(exame.isDorQueimacao());
        campoDorPontada.setChecked(exame.isDorPontada());
        campoDorPeso.setChecked(exame.isDorPeso());
        campoDorFormigamento.setChecked(exame.isDorFormigamento());
        campoDorOutra.setChecked(exame.isDorOutra());
        campoTipoDeDor.setText(exame.getTipoDeDor());
        campoDorHaQuantoTempo.setText(exame.getDorHaQuantoTempo());
        int idAparicaoDor;
        if (exame.getAparicaoDor() != null) {
            idAparicaoDor = switch (exame.getAparicaoDor()) {
                case ACORDAR -> R.id.activity_secao_dor_radio_acordar;
                case DECORRER -> R.id.activity_secao_dor_radio_decorrer_dia;
                case FINALDIA -> R.id.activity_secao_dor_radio_fim_dia;
                case CONSTANTE -> R.id.activity_secao_dor_radio_constante;
                default -> 0;
            };
            if (idAparicaoDor != 0) {
                campoAparicaoDor.check(idAparicaoDor);
            }
        }
        campoDorRepouso.setChecked(exame.isDorRepouso());
        campoIntensidadeDor.setProgress(exame.getIntensidadeDor());
        campoLocaisDor.setText(exame.getLocaisDor());
    }
}
