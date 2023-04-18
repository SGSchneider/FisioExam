package br.ufsm.fisioexam.ui.activity;

import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_EXAME;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_ID_PACIENTE;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_SECAO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_TIPO_EXAME;

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
import br.ufsm.fisioexam.database.dao.ExameDAO;
import br.ufsm.fisioexam.database.dao.SecoesDAO;
import br.ufsm.fisioexam.model.Exame;
import br.ufsm.fisioexam.model.Secao;
import br.ufsm.fisioexam.model.Secoes;
import br.ufsm.fisioexam.ui.SecoesExameView;

public class SecoesExameActivity extends AppCompatActivity {
    private static final String TITULO_APPBAR_NOVO_EXAME = "Novo Exame";
    private static final String TITULO_APPBAR_EDITA_EXAME = "Edita Exame";
    private String tipo;
    private int idPaciente;
    private Button salvar;
    private TextView campoTipo;
    private EditText campoData;
    private SecoesExameView secoesExameView;
    Calendar dataSelecionada = Calendar.getInstance();
    private ExameDAO exameDao;
    private SecoesDAO secoesDao;
    private Exame exame;
    private Secoes secoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secoes_exame);
        FisioExamDatabase database = FisioExamDatabase.getInstance(this);
        exameDao = database.getRoomExameDAO();
        secoesDao = database.getRoomSecoesDAO();
        inicializacaoDosCampos();
        carregaExame();
        secoesExameView = new SecoesExameView(this, exame.getId());
        setListenerCalendarioData();
        atualizaDataExame();
        configuraBotaoSalvar();
        configuraLista();
    }

    @Override
    protected void onResume() {
        super.onResume();
        secoesExameView.atualizaSecoes();
    }

    private void configuraBotaoSalvar() {
        salvar = findViewById(R.id.activity_secoes_exame_botao_salvar);
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
                Intent vaiParaFormularioSecaoActivity = new Intent(this, SecaoDiagnosticoMedicoActivity.class);
                vaiParaFormularioSecaoActivity.putExtra(CHAVE_EXAME, exame.getId());
                startActivity(vaiParaFormularioSecaoActivity);
            }
            if (secaoEscolhida.getId() == 2) {
                Intent vaiParaFormularioSecaoActivity = new Intent(this, SecaoQueixaPrincipalActivity.class);
                vaiParaFormularioSecaoActivity.putExtra(CHAVE_EXAME, exame.getId());
                startActivity(vaiParaFormularioSecaoActivity);
            }
            if (secaoEscolhida.getId() == 3) {
                Intent vaiParaFormularioSecaoActivity = new Intent(this, SecaoHistoriaDoencaAtualActivity.class);
                vaiParaFormularioSecaoActivity.putExtra(CHAVE_EXAME, exame.getId());
                startActivity(vaiParaFormularioSecaoActivity);
            }
            if (secaoEscolhida.getId() == 4) {
                Intent vaiParaFormularioSecaoActivity = new Intent(this, SecaoDorActivity.class);
                vaiParaFormularioSecaoActivity.putExtra(CHAVE_EXAME, exame.getId());
                startActivity(vaiParaFormularioSecaoActivity);
            }
            if (secaoEscolhida.getId() == 5) {
                Intent vaiParaFormularioSecaoActivity = new Intent(this, SecaoTratamentoAnteriorActivity.class);
                vaiParaFormularioSecaoActivity.putExtra(CHAVE_EXAME, exame.getId());
                startActivity(vaiParaFormularioSecaoActivity);
            }
            if (secaoEscolhida.getId() == 6) {
                Intent vaiParaFormularioSecaoActivity = new Intent(this, SecaoAfastamentoDaFuncaoActivity.class);
                vaiParaFormularioSecaoActivity.putExtra(CHAVE_EXAME, exame.getId());
                startActivity(vaiParaFormularioSecaoActivity);
            }
            if (secaoEscolhida.getId() == 7) {
                Intent vaiParaFormularioSecaoActivity = new Intent(this, SecaoHistoriaPregressaActivity.class);
                vaiParaFormularioSecaoActivity.putExtra(CHAVE_EXAME, exame.getId());
                startActivity(vaiParaFormularioSecaoActivity);
            }
            if (secaoEscolhida.getId() == 8) {
                Intent vaiParaFormularioSecaoActivity = new Intent(this, SecaoDoencasAssociadasActivity.class);
                vaiParaFormularioSecaoActivity.putExtra(CHAVE_EXAME, exame.getId());
                startActivity(vaiParaFormularioSecaoActivity);
            }
            if (secaoEscolhida.getId() == 9) {
                Intent vaiParaFormularioSecaoActivity = new Intent(this, SecaoMedicamentosEmUsoActivity.class);
                vaiParaFormularioSecaoActivity.putExtra(CHAVE_EXAME, exame.getId());
                startActivity(vaiParaFormularioSecaoActivity);
            }
            if (secaoEscolhida.getId() == 10) {
                Intent vaiParaFormularioSecaoActivity = new Intent(this, SecaoHistoriaFamiliarActivity.class);
                vaiParaFormularioSecaoActivity.putExtra(CHAVE_EXAME, exame.getId());
                startActivity(vaiParaFormularioSecaoActivity);
            }
            if (secaoEscolhida.getId() == 11) {
                Intent vaiParaFormularioSecaoActivity = new Intent(this, SecaoHistoriaOcupacionalActivity.class);
                vaiParaFormularioSecaoActivity.putExtra(CHAVE_EXAME, exame.getId());
                startActivity(vaiParaFormularioSecaoActivity);
            }
            if (secaoEscolhida.getId() == 12) {
                Intent vaiParaFormularioSecaoActivity = new Intent(this, SecaoHabitosVidaActivity.class);
                vaiParaFormularioSecaoActivity.putExtra(CHAVE_EXAME, exame.getId());
                startActivity(vaiParaFormularioSecaoActivity);
            }
            if (secaoEscolhida.getId() == 13) {
                Intent vaiParaFormularioSecaoActivity = new Intent(this, SecaoExamesComplementaresActivity.class);
                vaiParaFormularioSecaoActivity.putExtra(CHAVE_EXAME, exame.getId());
                startActivity(vaiParaFormularioSecaoActivity);
            }
            if (secaoEscolhida.getId() == 14) {
                Intent vaiParaFormularioSecaoActivity = new Intent(this, SecaoSinaisVitaisActivity.class);
                vaiParaFormularioSecaoActivity.putExtra(CHAVE_EXAME, exame.getId());
                startActivity(vaiParaFormularioSecaoActivity);
            }
            if (secaoEscolhida.getId() == 15) {
                Intent vaiParaFormularioSecaoActivity = new Intent(this, SecaoInspecaoActivity.class);
                vaiParaFormularioSecaoActivity.putExtra(CHAVE_EXAME, exame.getId());
                startActivity(vaiParaFormularioSecaoActivity);
            }
            if (secaoEscolhida.getId() == 16) {
                Intent vaiParaFormularioSecaoActivity = new Intent(this, SecaoPalpacaoActivity.class);
                vaiParaFormularioSecaoActivity.putExtra(CHAVE_EXAME, exame.getId());
                startActivity(vaiParaFormularioSecaoActivity);
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
                Intent vaiParaFormularioSecaoActivity = new Intent(this, SecaoObservacoesActivity.class);
                vaiParaFormularioSecaoActivity.putExtra(CHAVE_EXAME, exame.getId());
                startActivity(vaiParaFormularioSecaoActivity);
            }
            if (secaoEscolhida.getId() == 23) {
                Intent vaiParaFormularioSecaoActivity = new Intent(this, SecaoDiagnosticoFisioterapeuticoActivity.class);
                vaiParaFormularioSecaoActivity.putExtra(CHAVE_EXAME, exame.getId());
                startActivity(vaiParaFormularioSecaoActivity);
            }
            if (secaoEscolhida.getId() == 24) {
                Intent vaiParaFormularioSecaoActivity = new Intent(this, SecaoObjetivosTratamentoActivity.class);
                vaiParaFormularioSecaoActivity.putExtra(CHAVE_EXAME, exame.getId());
                startActivity(vaiParaFormularioSecaoActivity);
            }
            if (secaoEscolhida.getId() == 25) {
                Intent vaiParaFormularioSecaoActivity = new Intent(this, SecaoPlanoTratamentoActivity.class);
                vaiParaFormularioSecaoActivity.putExtra(CHAVE_EXAME, exame.getId());
                startActivity(vaiParaFormularioSecaoActivity);
            }
            exame = exameDao.getExame(exame.getId());
            secoesExameView.atualizaSecoes();
        });
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
        if (item.getItemId() == R.id.activity_formulario_exame_menu_proximo) {
            finalizaExame();
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
                new DatePickerDialog(SecoesExameActivity.this, date,
                        dataSelecionada.get(Calendar.YEAR),
                        dataSelecionada.get(Calendar.MONTH),
                        dataSelecionada.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void carregaExame() {
        //verifica a existência de dados no cabeçalho da solicitação de exame
        //para definir se será edição ou novo exame e carregar os dados em caso de edição.
        Intent dados = getIntent();

        if (dados.hasExtra(CHAVE_EXAME)) {
            exame = exameDao.getExame((int) dados.getSerializableExtra(CHAVE_EXAME));
            preencheTitulo();
            preencheCampos();
            setTitle(TITULO_APPBAR_EDITA_EXAME);
            secoes = secoesDao.getSecao(exame.getId());
        } else {
            if (dados.hasExtra(CHAVE_TIPO_EXAME)) {
                setTitle(TITULO_APPBAR_NOVO_EXAME);
                tipo = (String) dados.getSerializableExtra(CHAVE_TIPO_EXAME);
                idPaciente = (int) dados.getSerializableExtra(CHAVE_ID_PACIENTE);
                exame = new Exame(idPaciente, tipo, geraChaveCriacao());
                exameDao.salva(exame);
                exame.setId(exameDao.getIdNovoExame(idPaciente, exame.getCreationKey()));
                secoes = new Secoes(exame.getId());
                secoesDao.salva(secoes);
                preencheTitulo();
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
        dataSelecionada = exame.getData();
        if (dataSelecionada != null) {
            atualizaDataExame();
        }
    }

    private void preencheCampos() { //carrega os valores do banco para o formulario em caso de edição.
        preencheData();
        exame = exameDao.getExame(exame.getId());
        secoes = secoesDao.getSecao(exame.getId());
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
        exame.setData(data);


    }
}
