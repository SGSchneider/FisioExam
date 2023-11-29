package br.ufsm.fisioexam.ui.activity;

import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_EXAME;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_SECAO;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import br.ufsm.fisioexam.R;
import br.ufsm.fisioexam.database.FisioExamDatabase;
import br.ufsm.fisioexam.database.dao.PunhoDAO;
import br.ufsm.fisioexam.database.dao.SecoesDAO;
import br.ufsm.fisioexam.database.thread.QueryManager;
import br.ufsm.fisioexam.model.Punho;
import br.ufsm.fisioexam.model.Secoes;

public class SecaoTestesEspeciaisPunhoActivity extends AppCompatActivity {
    private Punho punho;
    private PunhoDAO punhoDao;
    private QueryManager<Punho> punhoQueryManager;
    private Secoes secoes;
    private SecoesDAO secoesDao;
    private QueryManager<Secoes> secoesQueryManager;

    private Button buttonSalvar;
    private Button buttonProximo;


    private CheckBox campoPhalenDir;
    private CheckBox campoPhalenEsq;
    private CheckBox campoPhalenInvertidoDir;
    private CheckBox campoPhalenInvertidoEsq;
    private CheckBox campoTinelDir;
    private CheckBox campoTinelEsq;
    private CheckBox campoTunelUlnarDir;
    private CheckBox campoTunelUlnarEsq;
    private CheckBox campoFinkelsteinDir;
    private CheckBox campoFinkelsteinEsq;


    private ImageButton ajudaPhalen;
    private ImageButton ajudaPhalenInvertido;
    private ImageButton ajudaTinel;
    private ImageButton ajudaTunelUlnar;
    private ImageButton ajudaFinkelstein;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secao_testes_especiais_punho);
        inicializaDAOS();
        carregaExame();
        inicializaBotoes();
        inicializaFormulario();
    }


    private void inicializaBotoes() {
        buttonProximo = findViewById(R.id.activity_secao_testes_especiais_punho_button_proximo);
        buttonSalvar = findViewById(R.id.activity_secao_testes_especiais_punho_button_salvar_e_sair);

        ajudaPhalen = findViewById(R.id.activity_secao_testes_especiais_punho_button_help_phalen);
        ajudaPhalenInvertido = findViewById(R.id.activity_secao_testes_especiais_punho_button_help_phalen_invertido);
        ajudaTinel = findViewById(R.id.activity_secao_testes_especiais_punho_button_help_tinel);
        ajudaTunelUlnar = findViewById(R.id.activity_secao_testes_especiais_punho_button_help_tunel_ulnar);
        ajudaFinkelstein = findViewById(R.id.activity_secao_testes_especiais_punho_button_help_finkelstein);

        setListenerBotoes();
    }

    private void vaiParaAjuda(Class<?> classe) {
        Intent vaiParaAjudaActivity = new Intent(this, classe);
        startActivity(vaiParaAjudaActivity);
    }

    private void setListenerBotoes() {
        buttonProximo.setOnClickListener(v -> proximoForm());
        buttonSalvar.setOnClickListener(v -> salvarESair());

        ajudaPhalen.setOnClickListener(v -> vaiParaAjuda(AjudaTestesEspeciaisPunhoPhalenActivity.class));
        ajudaPhalenInvertido.setOnClickListener(v -> vaiParaAjuda(AjudaTestesEspeciaisPunhoPhalenInvertidoActivity.class));
        ajudaTinel.setOnClickListener(v -> vaiParaAjuda(AjudaTestesEspeciaisPunhoTinelActivity.class));
        ajudaTunelUlnar.setOnClickListener(v -> vaiParaAjuda(AjudaTestesEspeciaisPunhoTunelUlnarActivity.class));
        ajudaFinkelstein.setOnClickListener(v -> vaiParaAjuda(AjudaTestesEspeciaisPunhoFinkelsteinActivity.class));
    }

    private void salvarESair() {
        salva();
        finish();
    }

    private void proximoForm() {
        salva();
        abreProximo();
        finish();
    }

    private void abreProximo() {
        salva();
        Intent vaiParaFormularioSecaoActivity = new Intent(this, IntermediarioSecoesEspecificasActivity.class);
        vaiParaFormularioSecaoActivity.putExtra(CHAVE_EXAME, punho.getExame());
        vaiParaFormularioSecaoActivity.putExtra(CHAVE_SECAO, 6);
        startActivity(vaiParaFormularioSecaoActivity);
        finish();
    }

    private void salva() {
        secoes.setTestesEspeciais(true);
        secoesQueryManager.update(secoes, secoesDao);
        punho.setTestesEspeciaisPhalenDir(campoPhalenDir.isChecked());
        punho.setTestesEspeciaisPhalenEsq(campoPhalenEsq.isChecked());
        punho.setTestesEspeciaisPhalenInvertidoDir(campoPhalenInvertidoDir.isChecked());
        punho.setTestesEspeciaisPhalenInvertidoEsq(campoPhalenInvertidoEsq.isChecked());
        punho.setTestesEspeciaisTinelDir(campoTinelDir.isChecked());
        punho.setTestesEspeciaisTinelEsq(campoTinelEsq.isChecked());
        punho.setTestesEspeciaisTriadeDir(campoTunelUlnarDir.isChecked());
        punho.setTestesEspeciaisTriadeEsq(campoTunelUlnarEsq.isChecked());
        punho.setTestesEspeciaisFinkelsteinDir(campoFinkelsteinDir.isChecked());
        punho.setTestesEspeciaisFinkelsteinEsq(campoFinkelsteinEsq.isChecked());
        punhoQueryManager.update(punho, punhoDao);
    }


    private void inicializaFormulario() {

        campoPhalenDir = findViewById(R.id.activity_secao_testes_especiais_punho_direita_phalen);
        campoPhalenEsq = findViewById(R.id.activity_secao_testes_especiais_punho_esquerda_phalen);
        campoPhalenInvertidoDir = findViewById(R.id.activity_secao_testes_especiais_punho_direita_phalen_invertido);
        campoPhalenInvertidoEsq = findViewById(R.id.activity_secao_testes_especiais_punho_esquerda_phalen_invertido);
        campoTinelDir = findViewById(R.id.activity_secao_testes_especiais_punho_direita_tinel);
        campoTinelEsq = findViewById(R.id.activity_secao_testes_especiais_punho_esquerda_tinel);
        campoTunelUlnarDir = findViewById(R.id.activity_secao_testes_especiais_punho_direita_tunel_ulnar);
        campoTunelUlnarEsq = findViewById(R.id.activity_secao_testes_especiais_punho_esquerda_tunel_ulnar);
        campoFinkelsteinDir = findViewById(R.id.activity_secao_testes_especiais_punho_direita_finkelstein);
        campoFinkelsteinEsq = findViewById(R.id.activity_secao_testes_especiais_punho_esquerda_finkelstein);

        preencheFormulario();
    }

    private void preencheFormulario() {
        if (secoes.isTestesEspeciais()) {
            campoPhalenDir.setChecked(punho.getTestesEspeciaisPhalenDir());
            campoPhalenEsq.setChecked(punho.getTestesEspeciaisPhalenEsq());
            campoPhalenInvertidoDir.setChecked(punho.getTestesEspeciaisPhalenInvertidoDir());
            campoPhalenInvertidoEsq.setChecked(punho.getTestesEspeciaisPhalenInvertidoEsq());
            campoTinelDir.setChecked(punho.getTestesEspeciaisTinelDir());
            campoTinelEsq.setChecked(punho.getTestesEspeciaisTinelEsq());
            campoTunelUlnarDir.setChecked(punho.getTestesEspeciaisTriadeDir());
            campoTunelUlnarEsq.setChecked(punho.getTestesEspeciaisTriadeEsq());
            campoFinkelsteinDir.setChecked(punho.getTestesEspeciaisFinkelsteinDir());
            campoFinkelsteinEsq.setChecked(punho.getTestesEspeciaisFinkelsteinEsq());
        }
    }



    private void carregaExame() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_EXAME)) {
            punho = punhoQueryManager.getOne((String) Objects.requireNonNull(dados.getSerializableExtra(CHAVE_EXAME)), punhoDao);
            secoes = secoesQueryManager.getOne(punho.getExame(), secoesDao);
        }
    }

    private void inicializaDAOS() {
        FisioExamDatabase database = FisioExamDatabase.getInstance(this);
        punhoDao = database.getRoomPunhoDAO();
        secoesDao = database.getRoomSecoesDAO();
        punhoQueryManager = new QueryManager<>();
        secoesQueryManager = new QueryManager<>();
    }
}
