package br.ufsm.fisioexam.ui.activity;

import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_EXAME;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_ID_PACIENTE;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_SECAO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_TIPO_COTOVELO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_TIPO_EXAME;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_TIPO_OMBRO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_TIPO_PUNHO;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

import br.ufsm.fisioexam.R;
import br.ufsm.fisioexam.database.FisioExamDatabase;
import br.ufsm.fisioexam.database.dao.CotoveloDAO;
import br.ufsm.fisioexam.database.dao.ExameDAO;
import br.ufsm.fisioexam.database.dao.OmbroDAO;
import br.ufsm.fisioexam.database.dao.PunhoDAO;
import br.ufsm.fisioexam.database.dao.SecoesDAO;
import br.ufsm.fisioexam.database.thread.QueryManager;
import br.ufsm.fisioexam.model.Cotovelo;
import br.ufsm.fisioexam.model.Exame;
import br.ufsm.fisioexam.model.Ombro;
import br.ufsm.fisioexam.model.Punho;
import br.ufsm.fisioexam.model.Secao;
import br.ufsm.fisioexam.model.Secoes;
import br.ufsm.fisioexam.ui.SecoesExameView;

public class SecoesExameActivity extends AppCompatActivity {
    private static final String TITULO_APPBAR_NOVO_EXAME = "Novo Exame";
    private static final String TITULO_APPBAR_EDITA_EXAME = "Edita Exame";
    private String tipo;
    private TextView campoTipo;
    private EditText campoData;
    private SecoesExameView secoesExameView;
    Calendar dataSelecionada = Calendar.getInstance();
    private ExameDAO exameDao;
    private QueryManager<Exame> exameQueryManager;
    private SecoesDAO secoesDao;
    private QueryManager<Secoes> secoesQueryManager;
    private Exame exame;
    private Secoes secoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secoes_exame);
        InicializaDaos();
        inicializacaoDosCampos();
        carregaExame();
        secoesExameView = new SecoesExameView(this, exame.getId());
        setListenerCalendarioData();
        atualizaDataExame();
        configuraBotaoSalvar();
        configuraLista();
        preencheExame();
    }

    private void InicializaDaos() {
        FisioExamDatabase database = FisioExamDatabase.getInstance(this);
        exameDao = database.getRoomExameDAO();
        secoesDao = database.getRoomSecoesDAO();
        exameQueryManager = new QueryManager<>();
        secoesQueryManager = new QueryManager<>();
    }

    @Override
    protected void onResume() {
        super.onResume();
        secoesExameView.atualizaSecoes();
    }

    private void configuraBotaoSalvar() {
        Button salvar = findViewById(R.id.activity_secoes_exame_botao_salvar);
        salvar.setOnClickListener(v -> finalizaExame());
    }

    private void configuraLista() {
        ListView listaDeSecoes = findViewById(R.id.activity_secoes_exame_lista);
        secoesExameView.configuraAdapter(listaDeSecoes);
        configuraListenerDeCliquePorItem(listaDeSecoes);
    }

    private void configuraListenerDeCliquePorItem(ListView listaDeSecoes) {
        listaDeSecoes.setOnItemClickListener((adapterView, view, position, id) -> {
            Secao secaoEscolhida = (Secao) adapterView.getItemAtPosition(position);
            if (secaoEscolhida.getId() == 1) {
                vaiParaActivity(SecaoDiagnosticoMedicoActivity.class);
            }
            if (secaoEscolhida.getId() == 2) {
                vaiParaActivity(SecaoQueixaPrincipalActivity.class);
            }
            if (secaoEscolhida.getId() == 3) {
                vaiParaActivity(SecaoHistoriaDoencaAtualActivity.class);
            }
            if (secaoEscolhida.getId() == 4) {
                vaiParaActivity(SecaoDorActivity.class);
            }
            if (secaoEscolhida.getId() == 5) {
                vaiParaActivity(SecaoTratamentoAnteriorActivity.class);
            }
            if (secaoEscolhida.getId() == 6) {
                vaiParaActivity(SecaoAfastamentoDaFuncaoActivity.class);
            }
            if (secaoEscolhida.getId() == 7) {
                vaiParaActivity(SecaoHistoriaPregressaActivity.class);
            }
            if (secaoEscolhida.getId() == 8) {
                vaiParaActivity(SecaoDoencasAssociadasActivity.class);
            }
            if (secaoEscolhida.getId() == 9) {
                vaiParaActivity(SecaoMedicamentosEmUsoActivity.class);
            }
            if (secaoEscolhida.getId() == 10) {
                vaiParaActivity(SecaoHistoriaFamiliarActivity.class);
            }
            if (secaoEscolhida.getId() == 11) {
                vaiParaActivity(SecaoHistoriaOcupacionalActivity.class);
            }
            if (secaoEscolhida.getId() == 12) {
                vaiParaActivity(SecaoHabitosVidaActivity.class);
            }
            if (secaoEscolhida.getId() == 13) {
                vaiParaActivity(SecaoExamesComplementaresActivity.class);
            }
            if (secaoEscolhida.getId() == 14) {
                vaiParaActivity(SecaoSinaisVitaisActivity.class);
            }
            if (secaoEscolhida.getId() == 15) {
                vaiParaActivity(SecaoInspecaoActivity.class);
            }
            if (secaoEscolhida.getId() == 16) {
                vaiParaActivity(SecaoPalpacaoActivity.class);
            }


            //Formulários específicos
            if (secaoEscolhida.getId() == 17) {
                vaiParaIntermediario(1);
            }
            if (secaoEscolhida.getId() == 18) {
                vaiParaIntermediario(2);
            }
            if (secaoEscolhida.getId() == 19) {
                vaiParaIntermediario(3);
            }
            if (secaoEscolhida.getId() == 20) {
                vaiParaIntermediario(4);
            }
            if (secaoEscolhida.getId() == 21) {
                vaiParaIntermediario(5);
            }
            //Fim Formulários específicos


            if (secaoEscolhida.getId() == 22) {
                vaiParaActivity(SecaoObservacoesActivity.class);
            }
            if (secaoEscolhida.getId() == 23) {
                vaiParaActivity(SecaoDiagnosticoFisioterapeuticoActivity.class);
            }
            if (secaoEscolhida.getId() == 24) {
                vaiParaActivity(SecaoObjetivosTratamentoActivity.class);
            }
            if (secaoEscolhida.getId() == 25) {
                vaiParaActivity(SecaoPlanoTratamentoActivity.class);
            }
            exame = exameQueryManager.getOne(exame.getId(), exameDao);
            secoesExameView.atualizaSecoes();
        });
    }

    private void vaiParaActivity(Class activity) {
        Intent vaiParaFormularioSecaoActivity = new Intent(this, activity);
        vaiParaFormularioSecaoActivity.putExtra(CHAVE_EXAME, exame.getId());
        startActivity(vaiParaFormularioSecaoActivity);
    }

    private void vaiParaIntermediario(int secao) {
        Intent vaiParaFormularioSecaoActivity = new Intent(this, IntermediarioSecoesEspecificasActivity.class);
        vaiParaFormularioSecaoActivity.putExtra(CHAVE_EXAME, exame.getId());
        vaiParaFormularioSecaoActivity.putExtra(CHAVE_SECAO, secao);
        startActivity(vaiParaFormularioSecaoActivity);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //getMenuInflater().inflate(R.menu.activity_lista_exames_menu, menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_exame_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.activity_formulario_exame_menu_next) {
            finalizaExame();
        }
        if (item.getItemId() == R.id.activity_formulario_exame_menu_print) {
            vaiParaActivity(ExportaExameActivity.class);
        }
        return super.onOptionsItemSelected(item);
    }

    private void setListenerCalendarioData() {
        DatePickerDialog.OnDateSetListener date = (view, year, month, dayOfMonth) -> {
            dataSelecionada.set(Calendar.YEAR, year);
            dataSelecionada.set(Calendar.MONTH, month);
            dataSelecionada.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            atualizaDataExame();
        };
        campoData.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                new DatePickerDialog(SecoesExameActivity.this, date, dataSelecionada.get(Calendar.YEAR), dataSelecionada.get(Calendar.MONTH), dataSelecionada.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void carregaExame() {
        //verifica a existência de dados no cabeçalho da solicitação de exame
        //para definir se será edição ou novo exame e carregar os dados em caso de edição.
        Intent dados = getIntent();

        if (dados.hasExtra(CHAVE_EXAME)) {
            exame = exameQueryManager.getOne((String) dados.getSerializableExtra(CHAVE_EXAME), exameDao);
            preencheTitulo();
            preencheCampos();
            setTitle(TITULO_APPBAR_EDITA_EXAME);
            secoes = secoesQueryManager.getOne(exame.getId(), secoesDao);
        } else {
            if (dados.hasExtra(CHAVE_TIPO_EXAME)) {
                boolean flag;
                setTitle(TITULO_APPBAR_NOVO_EXAME);
                tipo = (String) dados.getSerializableExtra(CHAVE_TIPO_EXAME);
                String idPaciente = (String) dados.getSerializableExtra(CHAVE_ID_PACIENTE);
                exame = new Exame(idPaciente, tipo, geraChaveCriacao());
                flag = exameQueryManager.insert(exame, exameDao);
                exame.setId(exameQueryManager.getIdNovoExame(idPaciente, exame.getCreationKey(), exameDao));
                secoes = new Secoes(exame.getId());
                secoesQueryManager.insert(secoes, secoesDao);
                if(flag){
                    criaEspecifica();
                }
                preencheTitulo();
            }
        }
    }

    private void criaEspecifica() {
        switch (exame.getTipo()){
            case CHAVE_TIPO_OMBRO -> {
                Ombro ombro = new Ombro(exame.getId());
                QueryManager<Ombro> ombroQueryManager = new QueryManager<>();
                OmbroDAO ombroDAO = FisioExamDatabase.getInstance(this).getRoomOmbroDAO();
                ombroQueryManager.insert(ombro, ombroDAO);
            }
            case CHAVE_TIPO_COTOVELO -> {
                Cotovelo cotovelo = new Cotovelo(exame.getId());
                QueryManager<Cotovelo> cotoveloQueryManager = new QueryManager<>();
                CotoveloDAO cotoveloDAO = FisioExamDatabase.getInstance(this).getRoomCotoveloDAO();
                cotoveloQueryManager.insert(cotovelo, cotoveloDAO);
            }
            case CHAVE_TIPO_PUNHO -> {
                Punho punho = new Punho(exame.getId());
                QueryManager<Punho> punhoQueryManager = new QueryManager<>();
                PunhoDAO punhoDAO = FisioExamDatabase.getInstance(this).getRoomPunhoDAO();
                punhoQueryManager.insert(punho,punhoDAO);
            }
        }
    }

    private String geraChaveCriacao() {
        char[] characteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz!@#$%&*()[]{}+-_/".toCharArray();
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 64; i++) //gera uma chave de 64 caracteres aleatórios
        {
            char charactere = characteres[random.nextInt(characteres.length)];
            builder.append(charactere);
        }

        return builder.toString();
    }

    private void preencheTitulo() {  //define o titulo do exame de acordo com o tipo do mesmo
        campoTipo.setText(String.format("Exame de %s", exame.getTipo()));
    }

    private void preencheData() {
        dataSelecionada.setTimeInMillis(exame.getData());
        if (dataSelecionada != null) {
            atualizaDataExame();
        }
    }

    private void preencheCampos() { //carrega os valores do banco para o formulario em caso de edição.
        preencheData();
        exame = exameQueryManager.getOne(exame.getId(), exameDao);
        secoes = secoesQueryManager.getOne(exame.getId(), secoesDao);
    }

    private void atualizaDataExame() { //formata a data para exibição
        String formatoData = "dd/MM/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatoData, new Locale("pt", "BR"));
        campoData.setText(dateFormat.format(dataSelecionada.getTime()));
    }


    private void finalizaExame() { //Fecha o formulario.
        finish();
    }

    private void inicializacaoDosCampos() { //linka as variáveis com os campos do formulario xml
        campoTipo = findViewById(R.id.activity_secoes_exame_tipo);
        campoData = findViewById(R.id.activity_secoes_exame_data);
    }


    private void preencheExame() { //Preenche a variável exame com os valores fornecidos no formulario.
        tipo = exame.getTipo();
        Calendar data = dataSelecionada;

        exame.setTipo(tipo);
        exame.setData(data.getTimeInMillis());

        exameQueryManager.update(exame, exameDao);

    }
}
