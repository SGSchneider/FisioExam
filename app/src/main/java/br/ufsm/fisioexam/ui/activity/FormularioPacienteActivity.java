package br.ufsm.fisioexam.ui.activity;

import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.AMASIADO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.ANALFABETO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CASADO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_PACIENTE;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.DIVORCIADO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.FEMININO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.FUNDAMENTAL;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.MASCULINO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.MEDIO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.POSGRADUACAO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.SOLTEIRO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.SUPERIOR;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.VIUVO;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

import br.ufsm.fisioexam.R;
import br.ufsm.fisioexam.database.FisioExamDatabase;
import br.ufsm.fisioexam.database.dao.PacienteDAO;
import br.ufsm.fisioexam.model.Paciente;

public class FormularioPacienteActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_NOVO_PACIENTE = "Novo Paciente";
    private static final String TITULO_APPBAR_EDITA_PACIENTE = "Edita Paciente";

    private EditText campoNome;
    private EditText campoIdade;
    private EditText campoNascimento;
    private String campoGenero;
    private RadioGroup grupoGenero;
    private RadioButton campoGeneroMasc;
    private RadioButton campoGeneroFem;
    private EditText campoEtnia;
    private EditText campoTelefone;
    private EditText campoEndereco;
    private String campoEstadoCivil;
    private RadioGroup grupoEstadoCivil;
    private RadioButton campoEstadoCivilSolteiro;
    private RadioButton campoEstadoCivilCasado;
    private RadioButton campoEstadoCivilDivorciado;
    private RadioButton campoEstadoCivilViuvo;
    private RadioButton campoEstadoCivilAmasiado;
    private String campoGrauInstrucao;
    private RadioGroup grupoGrauInstrucao;
    private RadioButton campoGrauInstrucaoAnalfabeto;
    private RadioButton campoGrauInstrucaoFundamental;
    private RadioButton campoGrauInstrucaoMedio;
    private RadioButton campoGrauInstrucaoSuperior;
    private RadioButton campoGrauInstrucaoPosGrad;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch campoInstrucaoCompleta;
    private TextView textoFormacao;
    private EditText campoFormacao;
    private EditText campoAtividadeProfissional;
    private EditText campoResponsavel;
    private EditText campoParentesco;
    private EditText campoMedico;
    Calendar dataSelecionada = Calendar.getInstance();
    private PacienteDAO dao;
    private Paciente paciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_paciente);
        FisioExamDatabase database = FisioExamDatabase.getInstance(this);
        dao = database.getRoomPacienteDAO();
        inicializacaoDosCampos();
        carregaPaciente();
        setListenerCalendarioNascimento();
        configuraBotaoSalvar();
        configuraToastCliqueIdade();
        ConfiguraListenerRadios();
        atualizaDataNascimento();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            }
        }
    }

    private void configuraToastCliqueIdade() {
        Toast toast = Toast.makeText(this, "A idade do paciente é preenchida automaticamente a partir da data de nascimento", Toast.LENGTH_LONG);
        campoIdade.setOnClickListener(v -> toast.show());
    }

    private void configuraBotaoSalvar() {
        Button salvar = findViewById(R.id.activity_formulario_paciente_botao_salvar);
        salvar.setOnClickListener(v -> finalizaFormulario());

    }

    private void setListenerCalendarioNascimento() {

        DatePickerDialog.OnDateSetListener date = (view, year, month, dayOfMonth) -> {
            dataSelecionada.set(Calendar.YEAR, year);
            dataSelecionada.set(Calendar.MONTH, month);
            dataSelecionada.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            atualizaDataNascimento();
        };

        campoNascimento.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                new DatePickerDialog(FormularioPacienteActivity.this, date, dataSelecionada.get(Calendar.YEAR), dataSelecionada.get(Calendar.MONTH), dataSelecionada.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void calculaIdade() {
        long currentTime;
        long nascimentoTime;
        int anos;
        Calendar idade = Calendar.getInstance();
        currentTime = idade.getTimeInMillis();
        nascimentoTime = dataSelecionada.getTimeInMillis();
        idade.setTimeInMillis(currentTime - nascimentoTime);
        anos = idade.get(Calendar.YEAR);
        anos -= 1970;
        if (anos < 0) {
            anos = 0;
        }
        campoIdade.setText(String.valueOf(anos));
    }


    private void carregaPaciente() {
        Intent dados = getIntent();

        if (dados.hasExtra(CHAVE_PACIENTE)) {
            paciente = (Paciente) dados.getSerializableExtra(CHAVE_PACIENTE);
            preencheCampos();
            setTitle(TITULO_APPBAR_EDITA_PACIENTE);
        } else {
            setTitle(TITULO_APPBAR_NOVO_PACIENTE);
            paciente = new Paciente();
        }
    }

    private void preencheCampos() {
        campoNome.setText(paciente.getNome());
        campoIdade.setText(String.valueOf(paciente.getIdade()));
        preencheNascimento();
        campoGenero = paciente.getGenero();
        setGenero();
        campoEtnia.setText(paciente.getEtnia());
        campoTelefone.setText(paciente.getTelefone());
        campoEndereco.setText(paciente.getEndereco());
        campoEstadoCivil = paciente.getEstadoCivil();
        setEstadoCivil();
        campoGrauInstrucao = paciente.getGrauInstrucao();
        setInstrucao();
        campoInstrucaoCompleta.setChecked(paciente.isInstrucaoCompleta());
        campoFormacao.setText(paciente.getFormacao());
        campoAtividadeProfissional.setText(paciente.getAtividadeProfissional());
        campoResponsavel.setText(paciente.getResponsavel());
        campoParentesco.setText(paciente.getParentesco());
        campoMedico.setText(paciente.getMedico());
    }

    private void preencheNascimento() {
        dataSelecionada.setTimeInMillis(paciente.getNascimento());
        atualizaDataNascimento();
    }


    private void atualizaDataNascimento() {
        String formatoData = "dd/MM/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatoData, new Locale("pt", "BR"));

        campoNascimento.setText(dateFormat.format(dataSelecionada.getTime()));
        calculaIdade();
    }

    private void ConfiguraListenerRadios() {
        grupoGrauInstrucao.setOnCheckedChangeListener((group, checkedId) -> checkInstrucao());
        grupoGenero.setOnCheckedChangeListener((group, checkedId) -> checkGenero());
        grupoEstadoCivil.setOnCheckedChangeListener((group, checkedId) -> checkEstadoCivil());
        checkInstrucao();
    }

    private void checkInstrucao() {
        if (campoGrauInstrucaoAnalfabeto.isChecked()) {
            campoGrauInstrucao = ANALFABETO;
            haFormacao(View.GONE);
        } else {
            if (campoGrauInstrucaoFundamental.isChecked()) {
                campoGrauInstrucao = FUNDAMENTAL;
                haFormacao(View.VISIBLE);
            } else {
                if (campoGrauInstrucaoMedio.isChecked()) {
                    campoGrauInstrucao = MEDIO;
                    haFormacao(View.VISIBLE);
                } else {
                    if (campoGrauInstrucaoSuperior.isChecked()) {
                        campoGrauInstrucao = SUPERIOR;
                        haFormacao(View.VISIBLE);
                    } else {
                        if (campoGrauInstrucaoPosGrad.isChecked()) {
                            campoGrauInstrucao = POSGRADUACAO;
                            haFormacao(View.VISIBLE);
                        } else {
                            haFormacao(View.GONE);
                        }
                    }
                }
            }
        }
    }

    private void haFormacao(int visibilidade) {
        campoInstrucaoCompleta.setVisibility(visibilidade);
        if (Objects.equals(campoGrauInstrucao, SUPERIOR) || Objects.equals(campoGrauInstrucao, POSGRADUACAO)) {
            textoFormacao.setVisibility(View.VISIBLE);
            campoFormacao.setVisibility(View.VISIBLE);
        } else {
            textoFormacao.setVisibility(View.GONE);
            campoFormacao.setVisibility(View.GONE);
        }
    }

    private void setInstrucao() {
        if (Objects.equals(campoGrauInstrucao, ANALFABETO)) {
            campoGrauInstrucaoAnalfabeto.setChecked(true);
        }
        if (Objects.equals(campoGrauInstrucao, FUNDAMENTAL)) {
            campoGrauInstrucaoFundamental.setChecked(true);
        }
        if (Objects.equals(campoGrauInstrucao, MEDIO)) {
            campoGrauInstrucaoMedio.setChecked(true);
        }
        if (Objects.equals(campoGrauInstrucao, SUPERIOR)) {
            campoGrauInstrucaoSuperior.setChecked(true);
        }
        if (Objects.equals(campoGrauInstrucao, POSGRADUACAO)) {
            campoGrauInstrucaoPosGrad.setChecked(true);
        }
    }

    private void checkEstadoCivil() {
        if (campoEstadoCivilSolteiro.isChecked()) {
            campoEstadoCivil = SOLTEIRO;
        }
        if (campoEstadoCivilCasado.isChecked()) {
            campoEstadoCivil = CASADO;
        }
        if (campoEstadoCivilDivorciado.isChecked()) {
            campoEstadoCivil = DIVORCIADO;
        }
        if (campoEstadoCivilViuvo.isChecked()) {
            campoEstadoCivil = VIUVO;
        }
        if (campoEstadoCivilAmasiado.isChecked()) {
            campoEstadoCivil = AMASIADO;
        }
    }

    private void setEstadoCivil() {
        if (Objects.equals(campoEstadoCivil, SOLTEIRO)) {
            campoEstadoCivilSolteiro.setChecked(true);
        }
        if (Objects.equals(campoEstadoCivil, CASADO)) {
            campoEstadoCivilCasado.setChecked(true);
        }
        if (Objects.equals(campoEstadoCivil, DIVORCIADO)) {
            campoEstadoCivilDivorciado.setChecked(true);
        }
        if (Objects.equals(campoEstadoCivil, VIUVO)) {
            campoEstadoCivilViuvo.setChecked(true);
        }
        if (Objects.equals(campoEstadoCivil, AMASIADO)) {
            campoEstadoCivilAmasiado.setChecked(true);
        }
    }

    private void checkGenero() {
        if (campoGeneroFem.isChecked()) {
            campoGenero = FEMININO;
        }
        if (campoGeneroMasc.isChecked()) {
            campoGenero = MASCULINO;
        }
    }

    private void setGenero() {
        if (Objects.equals(campoGenero, FEMININO)) {
            campoGeneroFem.setChecked(true);
        }
        if (Objects.equals(campoGenero, MASCULINO)) {
            campoGeneroMasc.setChecked(true);
        }
    }

    private void finalizaFormulario() {
        preenchePaciente();
        if (!dao.CheckID(paciente.getId()).isEmpty()) {
            Log.i("ID", "Tem ID");
            dao.edita(paciente);
        } else {

            Log.i("ID", "Não Tem ID");
            dao.salva(paciente);
        }
        finish();
    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.activity_formulario_paciente_nome);
        campoIdade = findViewById(R.id.activity_formulario_paciente_idade);
        campoNascimento = findViewById(R.id.activity_formulario_paciente_nascimento);
        grupoGenero = findViewById(R.id.activity_formulario_paciente_genero);
        campoGeneroMasc = findViewById(R.id.activity_formulario_paciente_radio_genero_masculino);
        campoGeneroFem = findViewById(R.id.activity_formulario_paciente_radio_genero_feminino);
        campoEtnia = findViewById(R.id.activity_formulario_paciente_etnia);
        campoTelefone = findViewById(R.id.activity_formulario_paciente_telefone);
        campoEndereco = findViewById(R.id.activity_formulario_paciente_endereco);
        grupoEstadoCivil = findViewById(R.id.activity_formulario_paciente_estado_civil);
        campoEstadoCivilSolteiro = findViewById(R.id.activity_formulario_paciente_radio_estado_civil_solteiro);
        campoEstadoCivilCasado = findViewById(R.id.activity_formulario_paciente_radio_estado_civil_casado);
        campoEstadoCivilDivorciado = findViewById(R.id.activity_formulario_paciente_radio_estado_civil_divorciado);
        campoEstadoCivilViuvo = findViewById(R.id.activity_formulario_paciente_radio_estado_civil_viuvo);
        campoEstadoCivilAmasiado = findViewById(R.id.activity_formulario_paciente_radio_estado_civil_amasiado);
        grupoGrauInstrucao = findViewById(R.id.activity_formulario_paciente_grau_instrucao);
        campoGrauInstrucaoAnalfabeto = findViewById(R.id.activity_formulario_paciente_radio_grau_instrucao_analfabeto);
        campoGrauInstrucaoFundamental = findViewById(R.id.activity_formulario_paciente_radio_grau_instrucao_fundamental);
        campoGrauInstrucaoMedio = findViewById(R.id.activity_formulario_paciente_radio_grau_instrucao_medio);
        campoGrauInstrucaoSuperior = findViewById(R.id.activity_formulario_paciente_radio_grau_instrucao_superior);
        campoGrauInstrucaoPosGrad = findViewById(R.id.activity_formulario_paciente_radio_grau_instrucao_pos_graduacao);
        campoInstrucaoCompleta = findViewById(R.id.activity_formulario_paciente_switch_instrucao_completa);
        textoFormacao = findViewById(R.id.activity_formulario_paciente_texto_formacao);
        campoFormacao = findViewById(R.id.activity_formulario_paciente_formacao);
        campoAtividadeProfissional = findViewById(R.id.activity_formulario_paciente_atividade_profissional);
        campoResponsavel = findViewById(R.id.activity_formulario_paciente_responsavel);
        campoParentesco = findViewById(R.id.activity_formulario_paciente_parentesco);
        campoMedico = findViewById(R.id.activity_formulario_paciente_medico);
    }


    private void preenchePaciente() {

        String nome = campoNome.getText().toString();
        int idade = Integer.parseInt(campoIdade.getText().toString());
        Calendar nascimento = dataSelecionada;
        String genero = campoGenero;
        String etnia = campoEtnia.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String endereco = campoEndereco.getText().toString();
        String estadoCivil = campoEstadoCivil;
        String grauInstituicao = campoGrauInstrucao;
        boolean instrucaoCompleta = campoInstrucaoCompleta.isChecked();
        String formacao = campoFormacao.getText().toString();
        String atividadeProfissional = campoAtividadeProfissional.getText().toString();
        String responsavel = campoResponsavel.getText().toString();
        String parentesco = campoParentesco.getText().toString();
        String medico = campoMedico.getText().toString();


        paciente.setNome(nome);
        paciente.setIdade(idade);
        paciente.setNascimento(nascimento.getTimeInMillis());
        paciente.setGenero(genero);
        paciente.setEtnia(etnia);
        paciente.setTelefone(telefone);
        paciente.setEndereco(endereco);
        paciente.setEstadoCivil(estadoCivil);
        paciente.setGrauInstrucao(grauInstituicao);
        paciente.setInstrucaoCompleta(instrucaoCompleta);
        paciente.setFormacao(formacao);
        paciente.setAtividadeProfissional(atividadeProfissional);
        paciente.setResponsavel(responsavel);
        paciente.setParentesco(parentesco);
        paciente.setMedico(medico);

    }
}
