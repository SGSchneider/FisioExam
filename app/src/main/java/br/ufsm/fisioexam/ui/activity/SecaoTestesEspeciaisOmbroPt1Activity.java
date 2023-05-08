package br.ufsm.fisioexam.ui.activity;

import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_DIR_MAIS;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_DIR_MENOS;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_ESQ_MAIS;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_ESQ_MENOS;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_EXAME;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.ufsm.fisioexam.R;
import br.ufsm.fisioexam.database.FisioExamDatabase;
import br.ufsm.fisioexam.database.dao.OmbroDAO;
import br.ufsm.fisioexam.database.dao.SecoesDAO;
import br.ufsm.fisioexam.model.Ombro;
import br.ufsm.fisioexam.model.Secoes;

public class SecaoTestesEspeciaisOmbroPt1Activity extends AppCompatActivity {
    private Ombro ombro;
    private OmbroDAO ombroDao;
    private Secoes secoes;
    private SecoesDAO secoesDao;
    private Button buttonParteDois;


    private RadioGroup radioJobe;
    private RadioGroup radioPatte;
    private RadioGroup radioGerber;
    private RadioGroup radioNeer;
    private RadioGroup radioHawkins;
    private RadioGroup radioSpeed;
    private RadioGroup radioYergason;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secao_testes_especiais_ombro_pt1);
        inicializaDAOS();
        carregaExame();
        inicializaBotoes();
        inicializaFormulario();
    }


    private void inicializaBotoes() {
        buttonParteDois = findViewById(R.id.activity_secao_testes_especiais_ombro_button_parte2);
        setListenerBotoes();
    }

    private void setListenerBotoes() {
        buttonParteDois.setOnClickListener(v -> proximoForm());
    }


    private void proximoForm() {
        salva();
        vaiParaFormParteDois();
        finish();
    }

    private void vaiParaFormParteDois() {
        salva();
        Intent vaiParaFormularioParteDoisActivity = new Intent(this, IntermediarioSecoesEspecificasActivity.class);
        vaiParaFormularioParteDoisActivity.putExtra(CHAVE_EXAME, ombro.getId());
        startActivity(vaiParaFormularioParteDoisActivity);
        finish();
    }

    private void salva() {
        secoes.setTestesEspeciais(true);
        secoesDao.edita(secoes);

        setaJobe();
        setaPatte();
        setaGerber();
        setaNeer();
        setaHawkins();
        setaSpeed();
        setaYergason();


        ombroDao.edita(ombro);
    }

    private void setaJobe() {
        String idJobe;

        if (radioJobe.getCheckedRadioButtonId() == R.id.activity_secao_testes_especiais_ombro_radio_direita_mais_jobe) {
            idJobe = CHAVE_DIR_MAIS;
        } else if (radioJobe.getCheckedRadioButtonId() == R.id.activity_secao_testes_especiais_ombro_radio_direita_menos_jobe) {
            idJobe = CHAVE_DIR_MENOS;
        } else if (radioJobe.getCheckedRadioButtonId() == R.id.activity_secao_testes_especiais_ombro_radio_esquerda_mais_jobe) {
            idJobe = CHAVE_ESQ_MAIS;
        } else if (radioJobe.getCheckedRadioButtonId() == R.id.activity_secao_testes_especiais_ombro_radio_esquerda_menos_jobe) {
            idJobe = CHAVE_ESQ_MENOS;
        } else {
            idJobe = "";
        }
        ombro.setJobe(idJobe);
    }

    private void setaPatte() {
        String idPatte;
        if (radioPatte.getCheckedRadioButtonId() == R.id.activity_secao_testes_especiais_ombro_radio_direita_mais_patte) {
            idPatte = CHAVE_DIR_MAIS;
        } else if (radioPatte.getCheckedRadioButtonId() == R.id.activity_secao_testes_especiais_ombro_radio_direita_menos_patte) {
            idPatte = CHAVE_DIR_MENOS;
        } else if (radioPatte.getCheckedRadioButtonId() == R.id.activity_secao_testes_especiais_ombro_radio_esquerda_mais_patte) {
            idPatte = CHAVE_ESQ_MAIS;
        } else if (radioPatte.getCheckedRadioButtonId() == R.id.activity_secao_testes_especiais_ombro_radio_esquerda_menos_patte) {
            idPatte = CHAVE_ESQ_MENOS;
        } else {
            idPatte = "";
        }
        ombro.setPatte(idPatte);
    }

    private void setaGerber() {
        String idGerber;
        if (radioGerber.getCheckedRadioButtonId() == R.id.activity_secao_testes_especiais_ombro_radio_direita_mais_gerber) {
            idGerber = CHAVE_DIR_MAIS;
        } else if (radioGerber.getCheckedRadioButtonId() == R.id.activity_secao_testes_especiais_ombro_radio_direita_menos_gerber) {
            idGerber = CHAVE_DIR_MENOS;
        } else if (radioGerber.getCheckedRadioButtonId() == R.id.activity_secao_testes_especiais_ombro_radio_esquerda_mais_gerber) {
            idGerber = CHAVE_ESQ_MAIS;
        } else if (radioGerber.getCheckedRadioButtonId() == R.id.activity_secao_testes_especiais_ombro_radio_esquerda_menos_gerber) {
            idGerber = CHAVE_ESQ_MENOS;
        } else {
            idGerber = "";
        }
        ombro.setGerberLiffOff(idGerber);
    }

    private void setaNeer() {
        String idNeer;
        if (radioNeer.getCheckedRadioButtonId() == R.id.activity_secao_testes_especiais_ombro_radio_direita_mais_neer) {
            idNeer = CHAVE_DIR_MAIS;
        } else if (radioNeer.getCheckedRadioButtonId() == R.id.activity_secao_testes_especiais_ombro_radio_direita_menos_neer) {
            idNeer = CHAVE_DIR_MENOS;
        } else if (radioNeer.getCheckedRadioButtonId() == R.id.activity_secao_testes_especiais_ombro_radio_esquerda_mais_neer) {
            idNeer = CHAVE_ESQ_MAIS;
        } else if (radioNeer.getCheckedRadioButtonId() == R.id.activity_secao_testes_especiais_ombro_radio_esquerda_menos_neer) {
            idNeer = CHAVE_ESQ_MENOS;
        } else {
            idNeer = "";
        }
        ombro.setNeer(idNeer);
    }

    private void setaHawkins() {
        String idHawkins;
        if (radioHawkins.getCheckedRadioButtonId() == R.id.activity_secao_testes_especiais_ombro_radio_direita_mais_hk) {
            idHawkins = CHAVE_DIR_MAIS;
        } else if (radioHawkins.getCheckedRadioButtonId() == R.id.activity_secao_testes_especiais_ombro_radio_direita_menos_hk) {
            idHawkins = CHAVE_DIR_MENOS;
        } else if (radioHawkins.getCheckedRadioButtonId() == R.id.activity_secao_testes_especiais_ombro_radio_esquerda_mais_hk) {
            idHawkins = CHAVE_ESQ_MAIS;
        } else if (radioHawkins.getCheckedRadioButtonId() == R.id.activity_secao_testes_especiais_ombro_radio_esquerda_menos_hk) {
            idHawkins = CHAVE_ESQ_MENOS;
        } else {
            idHawkins = "";
        }
        ombro.setHawkins(idHawkins);
    }

    private void setaSpeed() {
        String idSpeed;
        if (radioSpeed.getCheckedRadioButtonId() == R.id.activity_secao_testes_especiais_ombro_radio_direita_mais_speed) {
            idSpeed = CHAVE_DIR_MAIS;
        } else if (radioSpeed.getCheckedRadioButtonId() == R.id.activity_secao_testes_especiais_ombro_radio_direita_menos_speed) {
            idSpeed = CHAVE_DIR_MENOS;
        } else if (radioSpeed.getCheckedRadioButtonId() == R.id.activity_secao_testes_especiais_ombro_radio_esquerda_mais_speed) {
            idSpeed = CHAVE_ESQ_MAIS;
        } else if (radioSpeed.getCheckedRadioButtonId() == R.id.activity_secao_testes_especiais_ombro_radio_esquerda_menos_speed) {
            idSpeed = CHAVE_ESQ_MENOS;
        } else {
            idSpeed = "";
        }
        ombro.setPalmUpSpeed(idSpeed);
    }

    private void setaYergason() {
        String idYergason;
        if (radioYergason.getCheckedRadioButtonId() == R.id.activity_secao_testes_especiais_ombro_radio_direita_mais_yergason) {
            idYergason = CHAVE_DIR_MAIS;
        } else if (radioYergason.getCheckedRadioButtonId() == R.id.activity_secao_testes_especiais_ombro_radio_direita_menos_yergason) {
            idYergason = CHAVE_DIR_MENOS;
        } else if (radioYergason.getCheckedRadioButtonId() == R.id.activity_secao_testes_especiais_ombro_radio_esquerda_mais_yergason) {
            idYergason = CHAVE_ESQ_MAIS;
        } else if (radioYergason.getCheckedRadioButtonId() == R.id.activity_secao_testes_especiais_ombro_radio_esquerda_menos_yergason) {
            idYergason = CHAVE_ESQ_MENOS;
        } else {
            idYergason = "";
        }
        ombro.setYergason(idYergason);
    }


    private void inicializaFormulario() {
        radioJobe = findViewById(R.id.activity_secao_testes_especiais_ombro_radio_jobe);
        radioPatte = findViewById(R.id.activity_secao_testes_especiais_ombro_radio_patte);
        radioGerber = findViewById(R.id.activity_secao_testes_especiais_ombro_radio_gerber);
        radioNeer = findViewById(R.id.activity_secao_testes_especiais_ombro_radio_neer);
        radioHawkins = findViewById(R.id.activity_secao_testes_especiais_ombro_radio_hk);
        radioSpeed = findViewById(R.id.activity_secao_testes_especiais_ombro_radio_speed);
        radioYergason = findViewById(R.id.activity_secao_testes_especiais_ombro_radio_yergason);

        preencheFormulario();
    }

    private void preencheFormulario() {
        if (secoes.isTestesEspeciais()) {

            getDadosRadio(ombro.getJobe(),
                    R.id.activity_secao_testes_especiais_ombro_radio_direita_mais_jobe,
                    R.id.activity_secao_testes_especiais_ombro_radio_direita_menos_jobe,
                    R.id.activity_secao_testes_especiais_ombro_radio_esquerda_mais_jobe,
                    R.id.activity_secao_testes_especiais_ombro_radio_esquerda_menos_jobe,
                    radioJobe);

            getDadosRadio(ombro.getPatte(),
                    R.id.activity_secao_testes_especiais_ombro_radio_direita_mais_patte,
                    R.id.activity_secao_testes_especiais_ombro_radio_direita_menos_patte,
                    R.id.activity_secao_testes_especiais_ombro_radio_esquerda_mais_patte,
                    R.id.activity_secao_testes_especiais_ombro_radio_esquerda_menos_patte,
                    radioPatte);

            getDadosRadio(ombro.getGerberLiffOff(),
                    R.id.activity_secao_testes_especiais_ombro_radio_direita_mais_gerber,
                    R.id.activity_secao_testes_especiais_ombro_radio_direita_menos_gerber,
                    R.id.activity_secao_testes_especiais_ombro_radio_esquerda_mais_gerber,
                    R.id.activity_secao_testes_especiais_ombro_radio_esquerda_menos_gerber,
                    radioGerber);

            getDadosRadio(ombro.getNeer(),
                    R.id.activity_secao_testes_especiais_ombro_radio_direita_mais_neer,
                    R.id.activity_secao_testes_especiais_ombro_radio_direita_menos_neer,
                    R.id.activity_secao_testes_especiais_ombro_radio_esquerda_mais_neer,
                    R.id.activity_secao_testes_especiais_ombro_radio_esquerda_menos_neer,
                    radioNeer);

            getDadosRadio(ombro.getHawkins(),
                    R.id.activity_secao_testes_especiais_ombro_radio_direita_mais_hk,
                    R.id.activity_secao_testes_especiais_ombro_radio_direita_menos_hk,
                    R.id.activity_secao_testes_especiais_ombro_radio_esquerda_mais_hk,
                    R.id.activity_secao_testes_especiais_ombro_radio_esquerda_menos_hk,
                    radioHawkins);

            getDadosRadio(ombro.getPalmUpSpeed(),
                    R.id.activity_secao_testes_especiais_ombro_radio_direita_mais_speed,
                    R.id.activity_secao_testes_especiais_ombro_radio_direita_menos_speed,
                    R.id.activity_secao_testes_especiais_ombro_radio_esquerda_mais_speed,
                    R.id.activity_secao_testes_especiais_ombro_radio_esquerda_menos_speed,
                    radioSpeed);

            getDadosRadio(ombro.getYergason(),
                    R.id.activity_secao_testes_especiais_ombro_radio_direita_mais_yergason,
                    R.id.activity_secao_testes_especiais_ombro_radio_direita_menos_yergason,
                    R.id.activity_secao_testes_especiais_ombro_radio_esquerda_mais_yergason,
                    R.id.activity_secao_testes_especiais_ombro_radio_esquerda_menos_yergason,
                    radioYergason);


        }
    }

    private void getDadosRadio(@Nullable String radioChecked, int idDM, int idDm, int idEM, int idEm, RadioGroup radio) {
        if (radioChecked != null) {
            switch (radioChecked) {
                case CHAVE_DIR_MAIS:
                    radio.check(idDM);
                    break;
                case CHAVE_DIR_MENOS:
                    radio.check(idDm);
                    break;
                case CHAVE_ESQ_MAIS:
                    radio.check(idEM);
                    break;
                case CHAVE_ESQ_MENOS:
                    radio.check(idEm);
                    break;
            }
        }
    }

    private void carregaExame() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_EXAME)) {
            ombro = ombroDao.getOmbro((String) dados.getSerializableExtra(CHAVE_EXAME));
            secoes = secoesDao.getSecao(ombro.getExame());
        }
    }

    private void inicializaDAOS() {
        FisioExamDatabase database = FisioExamDatabase.getInstance(this);
        ombroDao = database.getRoomOmbroDAO();
        secoesDao = database.getRoomSecoesDAO();
    }
}
