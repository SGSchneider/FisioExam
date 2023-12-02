package br.ufsm.fisioexam.ui.activity;

import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.AFASTAMENTODAFUNCAO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.AMPLITUDEMOVIMENTO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.ANALFABETO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.ANTECEDENTESPESSOAIS;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_EXAME;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_POSGRADUACAO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_SUPERIOR;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_TIPO_COTOVELO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_TIPO_OMBRO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.DIAGNOSTICOFISIO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.DIAGNOSTICOMEDICO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.DOENCASASSOCIADAS;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.DOR;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.EXAMESCOMPLEMENTARES;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.FORCAMUSCULAR;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.HISTORIADOENCAATUAL;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.HISTORIAFAMILIAR;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.HISTORIAOCUPACIONAL;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.HISTORIASOCIAL;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.INSPECAO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.MEDICAMENTOSEMUSO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.OBJETIVOSTRATAMENTO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.OBSERVACOES;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.PALPACAO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.PERIMETRIA;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.PLANOTRATAMENTO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.QUEIXAPRINCIPAL;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.SENSIBILIDADE;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.SINAISVITAIS;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.TESTESESPECIAIS;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.TRATAMENTOANTERIOR;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Objects;

import br.ufsm.fisioexam.R;
import br.ufsm.fisioexam.database.FisioExamDatabase;
import br.ufsm.fisioexam.database.dao.CotoveloDAO;
import br.ufsm.fisioexam.database.dao.ExameDAO;
import br.ufsm.fisioexam.database.dao.OmbroDAO;
import br.ufsm.fisioexam.database.dao.PacienteDAO;
import br.ufsm.fisioexam.database.dao.SecoesDAO;
import br.ufsm.fisioexam.database.thread.QueryManager;
import br.ufsm.fisioexam.model.Cotovelo;
import br.ufsm.fisioexam.model.Exame;
import br.ufsm.fisioexam.model.Ombro;
import br.ufsm.fisioexam.model.Paciente;
import br.ufsm.fisioexam.model.Secao;
import br.ufsm.fisioexam.model.Secoes;
import br.ufsm.fisioexam.ui.ExportarExameView;
import br.ufsm.fisioexam.util.PdfCreator;

public class ExportaExameActivity extends AppCompatActivity {
    private ExportarExameView exportarExameView;
    private ExameDAO exameDao;
    private QueryManager<Exame> exameQueryManager;
    private SecoesDAO secoesDao;
    private QueryManager<Secoes> secoesQueryManager;
    private PacienteDAO pacienteDAO;
    private QueryManager<Paciente> pacienteQueryManager;
    private Exame exame;
    private Secoes secoes;
    private Uri uri;
    private Paciente paciente;

    private boolean secoesAExportar[];

    public ExportaExameActivity() {
        this.secoesAExportar = new boolean[25];
        for(int i=0; i<25; i++){
            this.secoesAExportar[i] = true;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exporta_exame);
        InicializaDaos();
        carregaExame();
        exportarExameView = new ExportarExameView(this);
        configuraBotaoSalvar();
        configuraLista();
    }

    private void InicializaDaos() {
        FisioExamDatabase database = FisioExamDatabase.getInstance(this);
        exameDao = database.getRoomExameDAO();
        secoesDao = database.getRoomSecoesDAO();
        pacienteDAO = database.getRoomPacienteDAO();
        pacienteQueryManager = new QueryManager<>();
        exameQueryManager = new QueryManager<>();
        secoesQueryManager = new QueryManager<>();
    }

    @Override
    protected void onResume() {
        super.onResume();
        exportarExameView.atualizaSecoes(secoesAExportar);
    }

    private void configuraBotaoSalvar() {
        Button salvar = findViewById(R.id.activity_exportar_exame_botao_salvar);
        salvar.setOnClickListener(v -> {
            try {
                exportaExame();
                showToast("O exame foi exportado em PDF para a pasta Documentos/Exames." , Toast.LENGTH_LONG);
            } catch (FileNotFoundException | NullPointerException | DocumentException e) {
                throw new RuntimeException(e);
            }

            finish();
        });
    }

    private void showToast(String texto, int length) {
        Toast toast = Toast.makeText(this, texto, length);
        toast.show();
    }
    private void exportaExame() throws FileNotFoundException, NullPointerException, DocumentException {
        uri = null;
        String formatoData = "dd-MM-yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatoData, new Locale("pt", "BR"));
        OutputStream outputStream;

        Document document = new Document();
        PdfCreator pdfCreator = new PdfCreator();

        ContentValues contentValues = new ContentValues();

        contentValues.put(MediaStore.MediaColumns.MIME_TYPE,"application/pdf");

        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME,"Exame_"+exame.getTipo()+"_"+
                pacienteQueryManager.getOne(exame.getPaciente(), pacienteDAO).getNome()+"_"+
                dateFormat.format(exame.getData()));

        contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOCUMENTS+"/Exames/");

        ContentResolver contentResolver = getContentResolver();

        uri = contentResolver.insert(MediaStore.Files.getContentUri("external"),contentValues);

        assert uri != null;
        ParcelFileDescriptor parcelFileDescriptor = contentResolver.openFileDescriptor(uri, "rw");

        assert parcelFileDescriptor != null;
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();

        outputStream = new FileOutputStream(fileDescriptor);






        PdfWriter pdfWriter = PdfWriter.getInstance(document,outputStream);

        pdfWriter.setBoxSize("box", new Rectangle(0,0,0,0));
        pdfWriter.setPageEvent(pdfCreator);



        estruturaPdf(document);


//        File file = new File(Environment.DIRECTORY_DOCUMENTS);
//        Uri uriPasta = FileProvider.getUriForFile(this,this.getApplicationContext().getPackageName()+".provider",file);
//        Intent intent = new Intent(Intent.ACTION_PICK);
//        intent.setData(uriPasta);
//        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        this.startActivity(intent);

    }

    private void estruturaPdf(Document document) throws DocumentException {
        String formatoData = "dd/MM/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatoData, new Locale("pt", "BR"));

        String instrucao;
        document.open();

        //Paciente Dados
        document.add(paragrafoTitulo("Paciente:"+ paciente.getNome()));
        document.add(paragrafoCorpo("Idade:"+paciente.getIdade()+ ". Data de Nascimento: "+ dateFormat.format(paciente.getNascimento())+"."));
        document.add(paragrafoCorpo("Gênero: "+paciente.getGenero()+". Etnia: "+paciente.getEtnia()+"."));
        document.add(paragrafoCorpo("Tel.: "+paciente.getTelefone()+". Endereço: "+paciente.getEndereco()+"."));
        document.add(paragrafoCorpo("Estado Civil: "+paciente.getEstadoCivil()+"."));
        if(paciente.isInstrucaoCompleta()){
            instrucao = "Completo(a)";
        }else{
            instrucao = "Incompleto(a)";
        }
        if(Objects.equals(paciente.getGrauInstrucao(), CHAVE_SUPERIOR) || Objects.equals(paciente.getGrauInstrucao(), CHAVE_POSGRADUACAO)){
            document.add(paragrafoCorpo("Formação: "+paciente.getGrauInstrucao()+" "+ instrucao +" em "+ paciente.getFormacao()+"."));
        }else{
            if(Objects.equals(paciente.getGrauInstrucao(), ANALFABETO)){
                document.add(paragrafoCorpo("Analfabeto(a)"));
            }else{
                document.add(paragrafoCorpo("Instrução: "+paciente.getGrauInstrucao() + instrucao+"."));
            }
        }
        document.add(paragrafoCorpo("Responsável: "+paciente.getResponsavel()+". Parentesco: "+paciente.getParentesco()+"."));
        document.add(paragrafoCorpo("Médico: "+paciente.getMedico()+"."));
        //Exame
        document.add(paragrafoTitulo("Avaliação Terapêutica de "+exame.getTipo()));
        document.add(paragrafoTitulo("Data:"));
        document.add(paragrafoCorpo(exame.getDataString()));


        if(secoesAExportar[0]){
            document.add(paragrafoTitulo(DIAGNOSTICOMEDICO+":"));
            document.add(paragrafoCorpo(exame.getDiagnosticoMedico()));
        }
        if(secoesAExportar[1]){
            document.add(paragrafoTitulo(QUEIXAPRINCIPAL+":"));
            document.add(paragrafoCorpo(exame.getQueixaPrincipal()));
        }
        if(secoesAExportar[2]){
            document.add(paragrafoTitulo(HISTORIADOENCAATUAL+":"));
            document.add(paragrafoCorpo(exame.getHistoriaDoencaAtual()));
        }
        if(secoesAExportar[3]){
            document.add(paragrafoTitulo(DOR+":"));
            if(exame.isSenteDor()){
                document.add(paragrafoCorpo("Sente dor do tipo "+ exame.getTipoDeDor()+" com intensidade "+exame.getIntensidadeDor()+ " no(a) "+ exame.getLocaisDor() +" há "+ exame.getDorHaQuantoTempo()+"."));
                if(exame.isDorRepouso()){
                    document.add(paragrafoCorpo("A dor aparece "+ exame.getAparicaoDor()+ " e some com o repouso."));
                }else{
                    document.add(paragrafoCorpo("A dor aparece "+ exame.getAparicaoDor()+ " e não some com o repouso."));
                }
            }else{
                document.add(paragrafoCorpo("Não sente dor."));
            }
        }
        if(secoesAExportar[4]){
            document.add(paragrafoTitulo(TRATAMENTOANTERIOR+":"));
            if(exame.isTratamentoPassado()){
                document.add(paragrafoCorpo("Já fez tratamento. "+exame.getQualTratamentoPassado()+
                        " durante "+exame.getDuracaoTratamentoPassado()+
                        " há "+exame.getTempoTratamentoPassado()+"."));
                if(exame.isTratamentoPassadoMelhora()){
                    document.add(paragrafoCorpo("Houve melhora!"));
                }else {
                    document.add(paragrafoCorpo("Não houve melhora!"));
                }
            }else{
                document.add(paragrafoCorpo("Não possui tratamento anterior."));
            }
        }
        if(secoesAExportar[5]){
            document.add(paragrafoTitulo(AFASTAMENTODAFUNCAO+":"));
            if(exame.isAfastamentoFuncao()){
                document.add(paragrafoCorpo("Houve afastamento durante "+exame.getTempoAfastamento()+
                        " devido "+ exame.getQualAfastamentoFuncao()+"."));
            }else{
                document.add(paragrafoCorpo("Não houve afastamento."));
            }
        }
        if(secoesAExportar[6]){
            document.add(paragrafoTitulo(ANTECEDENTESPESSOAIS+":"));
            document.add(paragrafoCorpo(exame.getAntecedentesPessoais()));
        }
        if(secoesAExportar[7]){
            String aux = "";
            if(exame.isDoencasAssociadasHipertensao()){
                aux+="Hipertensão, ";
            }
            if(exame.isDoencasAssociadasDiabetes()){
                aux+="Diabetes, ";
            }
            if(exame.isDoencasAssociadasCardiovascular()){
                aux+="Doença Cardiovascular, ";
            }
            if(exame.isDoencasAssociadasPulmonar()){
                aux+="Doença Pulmonar, ";
            }
            if(exame.isDoencasAssociadasCancer()){
                aux+="Câncer, ";
            }
            if(exame.isDoencasAssociadasOutra()){
                aux+=exame.getOutraDoencasAssociadas();
            }
            if(aux.endsWith(", ")){
                aux= aux.substring(0,aux.length()-3)+ ".";
            }
            document.add(paragrafoTitulo(DOENCASASSOCIADAS+":"));
            document.add(paragrafoCorpo(aux));
        }
        if(secoesAExportar[8]){
            document.add(paragrafoTitulo(MEDICAMENTOSEMUSO+":"));
            document.add(paragrafoCorpo(exame.getMedicamentosEmUso()));
        }
        if(secoesAExportar[9]){
            String aux = "";
            if(exame.isHistoriaFamiliarHipertensao()){
                aux+="Hipertensão, ";
            }
            if(exame.isHistoriaFamiliarDiabetes()){
                aux+="Diabetes, ";
            }
            if(exame.isHistoriaFamiliarCardiovascular()){
                aux+="Doença Cardiovascular, ";
            }
            if(exame.isHistoriaFamiliarPulmonar()){
                aux+="Doença Pulmonar, ";
            }
            if(exame.isHistoriaFamiliarCancer()){
                aux+="Câncer, ";
            }
            if(exame.isHistoriaFamiliarOutra()){
                aux+=exame.getOutraHistoriaFamiliar();
            }
            if(aux.endsWith(", ")){
                aux= aux.substring(0,aux.length()-3)+ ".";
            }
            document.add(paragrafoTitulo(HISTORIAFAMILIAR+":"));
            document.add(paragrafoCorpo(aux));
        }
        if(secoesAExportar[10]){
            document.add(paragrafoTitulo(HISTORIAOCUPACIONAL+":"));
            document.add(paragrafoCorpo("Atualmente: "+ exame.getOcupacaoAtual() + " Durante: "+exame.getTempoTrabalhoAtual()));
            document.add(paragrafoCorpo("Anteriormente: "+ exame.getOcupacaoAnterior() + " Durante: "+exame.getTempoTrabalhoAnterior()));
        }
        if(secoesAExportar[11]){
            document.add(paragrafoTitulo(HISTORIASOCIAL+":"));
            document.add(paragrafoCorpo("Moradia: "+exame.getMoradia()));
            if(exame.isTabagista()){
                document.add(paragrafoCorpo("Tabagista: "+exame.getQuantoCigarro()+" por dia."));
            }
            if(exame.isEtilista()){
                document.add(paragrafoCorpo("Etilista: "+exame.getQuantoAlcool()+" por dia."));
            }
            if(exame.isAtividadeFisica()){
                document.add(paragrafoCorpo("Pratica Exercício:: "+exame.getQuantaAtividadeFisica()+" vezes por semana."));
            }
            if(exame.isRestricaoAlimentacaoAcucar()||exame.isRestricaoAlimentacaoSal()||exame.isRestricaoAlimentacaoFritura()){
                String aux = "";
                if(exame.isRestricaoAlimentacaoAcucar()){
                    aux+="Açúcar, ";
                }
                if(exame.isRestricaoAlimentacaoSal()){
                    aux+="Sal, ";
                }
                if(exame.isRestricaoAlimentacaoFritura()){
                    aux+="Fritura.";
                }
                if(aux.endsWith(", ")){
                    aux= aux.substring(0,aux.length()-3)+ ".";
                }
                document.add(paragrafoCorpo("Restrição alimentar: "+aux));
            }
            if(exame.isLazer()){
                document.add(paragrafoCorpo("Lazer: "+exame.getQualLazer()));
            }
        }
        if(secoesAExportar[12]){
            document.add(paragrafoTitulo(EXAMESCOMPLEMENTARES+":"));
            document.add(paragrafoCorpo(exame.getExamesComplementares()));
        }
        if(secoesAExportar[13]){
            document.add(paragrafoTitulo(SINAISVITAIS+":"));
            document.add(paragrafoCorpo(
                    " PA: "+exame.getPA()+
                    " FC: "+exame.getFC()+
                    " FR: "+exame.getFR()+
                    " Temperatura Corporal: "+exame.getTempCorporal()));
        }
        if(secoesAExportar[14]){
            String aux = "";
            aux += "Coloração: ";
            if(exame.isColoracaoPeleNormalInsp()){
                aux+="Normal. ";
            }
            if(exame.isColoracaoPeleVermelhidaoInsp()){
                aux+="Vermelhidão. ";
            }
            if(exame.isColoracaoPeleCianoseInsp()){
                aux+="Cianose. ";
            }
            aux+= "Hidratação: ";
            if(exame.isHidratacaoPeleDesidrInsp()){
                aux+= "Desidratada. ";
            }
            if(exame.isHidratacaoPeleHidrInsp()){
                aux+= "Hidratada. ";
            }
            aux+= "Espessura: ";
            if(exame.isEspessuraPeleNormalInsp()){
                aux+="Normal.";
            }
            if(exame.isEspessuraPeleFinaInsp()){
                aux+="Fina.";
            }
            if(exame.isEspessuraPeleEspessaInsp()){
                aux+="Espessa.";
            }
            document.add(paragrafoTitulo(INSPECAO+":"));
            document.add(paragrafoCorpo(aux));
            if(exame.isPresencaCicatrizesInsp()){
                document.add(paragrafoCorpo("Possui cicatrizes em: " + exame.getLocalCicatrizesInsp()));
            }
            if(exame.isPresencaFeridasInsp()){
                document.add(paragrafoCorpo("Possui feridas em: " + exame.getLocalFeridasInsp()));
            }
            if(exame.isTrofismoMuscularEutroficoInsp()|exame.isTrofismoMuscularHipotroficoInsp()|exame.isTrofismoMuscularHipertroficoInsp()){
                aux = "";
                if(exame.isTrofismoMuscularEutroficoInsp()){
                    aux = "Trofismo Muscular Eutrófico em: ";
                }
                if(exame.isTrofismoMuscularHipotroficoInsp()){
                    aux = "Trofismo Muscular Hipotrófico em: ";
                }
                if(exame.isTrofismoMuscularHipertroficoInsp()){
                    aux = "Trofismo Muscular Hipertrófico em: ";
                }
                document.add(paragrafoCorpo(aux + exame.getLocalTrofismoMuscularInsp()));
            }

            if(exame.isPresencaEdemaInsp()){
                document.add(paragrafoCorpo("Possui edemas em: " + exame.getLocalEdemaInsp()));
            }
            if(exame.isTecidoOsseoNormalInsp()|exame.isTecidoOsseoCaloInsp()|exame.isTecidoOsseoDeformInsp()){
                aux = "";
                if(exame.isTecidoOsseoNormalInsp()){
                    aux = "Tecido ósseo: Normal.";
                }
                if(exame.isTecidoOsseoCaloInsp()){
                    aux = "Tecido ósseo: Calo ósseo.";
                }
                if(exame.isTecidoOsseoDeformInsp()){
                    aux = "Tecido ósseo: Desalinhamento/Deformidade.";
                }
                document.add(paragrafoCorpo(aux));
            }

            if(exame.isMarchaNormalInsp()){
                document.add(paragrafoCorpo("Marcha normal."));
            }
            if(exame.isMarchaAntargicaInsp()){
                document.add(paragrafoCorpo("Marcha antálgica."));
            }
            if(exame.isMarchaComDorOmbroInsp()){
                document.add(paragrafoCorpo("Apresentou dor durante a marcha."));
            }

            if(exame.isPresencaProtesesInsp()){
                document.add(paragrafoCorpo("Possui próteses em: " + exame.getLocalProtesesInsp()));
            }
            if(exame.isPresencaOrtesesInsp()){
                document.add(paragrafoCorpo("Possui orteses em: " + exame.getLocalOrtesesInsp()));
            }
            if(exame.isPresencaCateteresESondasInsp()){
                document.add(paragrafoCorpo("Possui cateteres e/ou sondas em: " + exame.getLocalCateteresESondasInsp()));
            }
        }
        if(secoesAExportar[15]){
            document.add(paragrafoTitulo(PALPACAO+":"));
            if(exame.isColoracaoPeleSAPalp()){
                document.add(paragrafoCorpo("Coloração: Sem Alterações."));
            }
            if(exame.isColoracaoPeleVermPalp()){
                document.add(paragrafoCorpo("Coloração: Vermelha. Local: " + exame.getLocalColoracaoPelePalp()));
            }
            if(exame.isColoracaoPeleEquiPalp()){
                document.add(paragrafoCorpo("Coloração: Equimose (roxa). Local: " + exame.getLocalColoracaoPelePalp()));
            }
            if(exame.isFeridasPalp()){
                document.add(paragrafoCorpo("Possui feridas de tamanho: "+exame.getTamFeridasPalp()+" no local: "+exame.getLocalFeridasPalp()));
            }
            if(exame.isCicatrizesPalp()){
                if(exame.isAderenciaCicatrizesPalp()){
                    document.add(paragrafoCorpo("Possui cicatrizes com aderências no local: "+ exame.getLocalCicatrizesPalp()));
                }else{
                    document.add(paragrafoCorpo("Possui cicatrizes sem aderências no local: "+ exame.getLocalCicatrizesPalp()));
                }
            }
            if(exame.isPresencaEdemaPalp()){
                if(exame.isCacifoPresencaEdemaPalp()){
                    document.add(paragrafoCorpo("Possui edemas com cacifos no local: "+ exame.getLocalPresencaEdemaPalp()));
                }else{
                    document.add(paragrafoCorpo("Possui edemas sem cacifos no local: "+ exame.getLocalPresencaEdemaPalp()));
                }
            }
//            Temperatura
            if(exame.isTemperaturaAumentoPalp()|exame.isTemperaturaDiminuiPalp()){
                if(exame.isTemperaturaAumentoPalp()){
                    document.add(paragrafoCorpo("Aumento da temperatura em: "+exame.getLocalTemperaturaPalp()));
                }else{
                    document.add(paragrafoCorpo("Diminuição da temperatura em: "+exame.getLocalTemperaturaPalp()));
                }
            }else{
                document.add(paragrafoCorpo("Temperatura normal."));
            }
            if(exame.isTensaoMuscularAumentadaPalp()|exame.isTensaoMuscularDiminuidaPalp()){
                if(exame.isTensaoMuscularAumentadaPalp()){
                    document.add(paragrafoCorpo("Pontos de tensão muscular aumentada em: "+exame.getLocalTensaoMuscularPalp()));
                }else{
                    document.add(paragrafoCorpo("Pontos de tensão muscular diminuída em: "+exame.getLocalTensaoMuscularPalp()));
                }
            }else{
                document.add(paragrafoCorpo("Não há pontos de tensão muscular."));
            }
            if(exame.isTecidoOsseoPalp()){
                document.add(paragrafoCorpo("Calo ósseo em:"+ exame.getLocalTecidoOsseoPalp()));
            }else{
                document.add(paragrafoCorpo("Sem tecido ósseo."));
            }
            if(exame.isDorPalp()){
                document.add(paragrafoCorpo("Houve dor de grau "+exame.getGrauDorPalp()+" durante a palpação no local: "+exame.getLocalDorPalp()));
            }
        }

        SecoesEspecificas(document);

        if(secoesAExportar[21]){
            document.add(paragrafoTitulo(OBSERVACOES+":"));
            document.add(paragrafoCorpo(exame.getObservacoes()));
        }
        if(secoesAExportar[22]){
            document.add(paragrafoTitulo(DIAGNOSTICOFISIO+":"));
            document.add(paragrafoCorpo(exame.getDiagnosticoFisioterapeutico()));
        }
        if(secoesAExportar[23]){
            document.add(paragrafoTitulo(OBJETIVOSTRATAMENTO+":"));
            document.add(paragrafoCorpo(exame.getObjetivosTratamento()));
        }
        if(secoesAExportar[24]){
            document.add(paragrafoTitulo(PLANOTRATAMENTO+":"));
            document.add(paragrafoCorpo(exame.getPlanoTratamento()));
        }
        document.close();
    }

    private void SecoesEspecificas(Document document) throws DocumentException {
        switch (exame.getTipo()){
            case CHAVE_TIPO_OMBRO -> SecoesOmbro(document);
            case CHAVE_TIPO_COTOVELO -> SecoesCotovelo(document);
        }
    }

    private void SecoesCotovelo(Document document) throws DocumentException {
        Cotovelo cotovelo;
        CotoveloDAO cotoveloDAO = FisioExamDatabase.getInstance(this).getRoomCotoveloDAO();
        QueryManager<Cotovelo> cotoveloQueryManager = new QueryManager<>();
        cotovelo = cotoveloQueryManager.getOne(cotoveloQueryManager.getIdByForeign(exame.getId(),cotoveloDAO),cotoveloDAO);
        if(secoesAExportar[16]){
            document.add(paragrafoTitulo(AMPLITUDEMOVIMENTO+":"));
            document.add(paragrafoCorpo("Flexão: Direito "+cotovelo.getFlexaoDir()+" Esquerdo "+ cotovelo.getFlexaoEsq()+" Normalidade: 0-140°/150°"));
            document.add(paragrafoCorpo("Extensão: Direito "+cotovelo.getExtensaoDir()+" Esquerdo "+ cotovelo.getExtensaoEsq()+" Normalidade: 0-140°/150°"));
            document.add(paragrafoCorpo("Supinação: Direito "+cotovelo.getSupinacaoDir()+" Esquerdo "+ cotovelo.getSupinacaoEsq()+" Normalidade: 0-85°/90°"));
            document.add(paragrafoCorpo("Pronação: Direito "+cotovelo.getPronacaoDir()+" Esquerdo "+ cotovelo.getPronacaoEsq()+" Normalidade: 0-85°/90°"));
            document.add(paragrafoCorpo("Ângulo de Carregamento: Direito "+cotovelo.getAnguloCarregamentoDir()+" Esquerdo "+ cotovelo.getAnguloCarregamentoEsq()+" Normalidade: Homens: 5-10° Mulheres: 10-15"));
        }
        if(secoesAExportar[17]){
            document.add(paragrafoTitulo(PERIMETRIA+":"));
            document.add(paragrafoCorpo("Superior 5cm Direito: "+cotovelo.getPerimetriaSupDir5() +" Esquerdo: "+cotovelo.getPerimetriaSupEsq5()));
            document.add(paragrafoCorpo("Superior 10cm Direito: "+cotovelo.getPerimetriaSupDir10() +" Esquerdo: "+cotovelo.getPerimetriaSupEsq10()));
            document.add(paragrafoCorpo("Superior 15cm Direito: "+cotovelo.getPerimetriaSupDir15() +" Esquerdo: "+cotovelo.getPerimetriaSupEsq15()));
            document.add(paragrafoCorpo("Inferior 5cm Direito: "+cotovelo.getPerimetriaInfDir5() +" Esquerdo: "+cotovelo.getPerimetriaInfEsq5()));
            document.add(paragrafoCorpo("Inferior 10cm Direito: "+cotovelo.getPerimetriaInfDir10() +" Esquerdo: "+cotovelo.getPerimetriaInfEsq10()));
            document.add(paragrafoCorpo("Inferior 15cm Direito: "+cotovelo.getPerimetriaInfDir15() +" Esquerdo: "+cotovelo.getPerimetriaInfEsq15()));
        }
        if(secoesAExportar[18]){
            document.add(paragrafoTitulo(FORCAMUSCULAR+":"));
            document.add(paragrafoCorpo("Bíceps Braquial Direito: "+cotovelo.getBicepsBraquialDir() +" Esquerdo: "+cotovelo.getBicepsBraquialEsq()));
            document.add(paragrafoCorpo("Braquial Direito: "+cotovelo.getBraquialDir() +" Esquerdo: "+cotovelo.getBraquialEsq()));
            document.add(paragrafoCorpo("Braquirradial Direito: "+cotovelo.getBraquirradialDir() +" Esquerdo: "+cotovelo.getBraquirradialEsq()));
            document.add(paragrafoCorpo("Tríceps Braquial e Ânconeo: "+cotovelo.getTricepsBraquialEAnconeoDir() +" Esquerdo: "+cotovelo.getTricepsBraquialEAnconeoEsq()));
            document.add(paragrafoCorpo("Supinador Direito: "+cotovelo.getSupinadorDir() +" Esquerdo: "+cotovelo.getSupinadorEsq()));
            document.add(paragrafoCorpo("Pronador Quadrado e Redondo Direito: "+cotovelo.getPronadorQuadradoERedondoDir() +" Esquerdo: "+cotovelo.getPronadorQuadradoERedondoEsq()));
        }
        if(secoesAExportar[19]){
            document.add(paragrafoTitulo(SENSIBILIDADE+":"));
            document.add(paragrafoCorpo("Sensibilidade táctil: "+cotovelo.getSensibilidadeTactil()));
            document.add(paragrafoCorpo("Sensibilidade térmica: "+cotovelo.getSensibilidadeTermica()));
            document.add(paragrafoCorpo("Sensibilidade dolorosa: "+cotovelo.getSensibilidadeDolorosa()));
            document.add(paragrafoCorpo("Local Avaliado: "+cotovelo.getSensibilidadeLocalAvaliado()));
        }
        if(secoesAExportar[20]){
            String aux;
            document.add(paragrafoTitulo(TESTESESPECIAIS+":"));
            //Cozen
            aux = "Cozen: Direito ";
            if(cotovelo.getTesteCozenDir()){
                aux+="+,";
            }else{
                aux+="-,";
            }
            aux += " Esquerdo ";
            if(cotovelo.getTesteCozenEsq()){
                aux+="+";
            }else{
                aux+="-";
            }
            document.add(paragrafoCorpo(aux));

            //Golfista
            aux = "Teste para Cotovelo de Golfista: Direito ";
            if(cotovelo.getTesteCotoveloGolfistaDir()){
                aux+="+,";
            }else{
                aux+="-,";
            }
            aux += " Esquerdo ";
            if(cotovelo.getTesteCotoveloGolfistaEsq()){
                aux+="+";
            }else{
                aux+="-";
            }
            document.add(paragrafoCorpo(aux));

            //Sinal Tinel
            aux = "Sinal de Tinel: Direito ";
            if(cotovelo.getTesteSinalTinelDir()){
                aux+="+,";
            }else{
                aux+="-,";
            }
            aux += " Esquerdo ";
            if(cotovelo.getTesteSinalTinelEsq()){
                aux+="+";
            }else{
                aux+="-";
            }
            document.add(paragrafoCorpo(aux));

            //LCM
            aux = "Teste de Esforço em Adução (Varo) (LCL): Direito ";
            if(cotovelo.getTesteEsforcoVaroDir()){
                aux+="+,";
            }else{
                aux+="-,";
            }
            aux += " Esquerdo ";
            if(cotovelo.getTesteEsforcoVaroEsq()){
                aux+="+";
            }else{
                aux+="-";
            }
            document.add(paragrafoCorpo(aux));

            //LCM
            aux = "Teste de Esforço em Abdução (Valgo) (LCM): Direito ";
            if(cotovelo.getTesteEsforcoValgoDir()){
                aux+="+,";
            }else{
                aux+="-,";
            }
            aux += " Esquerdo ";
            if(cotovelo.getTesteEsforcoValgoEsq()){
                aux+="+";
            }else{
                aux+="-";
            }
            document.add(paragrafoCorpo(aux));

        }
    }

    private void SecoesOmbro(Document document) throws DocumentException {
        Ombro ombro;
        OmbroDAO ombroDAO = FisioExamDatabase.getInstance(this).getRoomOmbroDAO();
        QueryManager<Ombro> ombroQueryManager = new QueryManager<>();
        ombro = ombroQueryManager.getOne(ombroQueryManager.getIdByForeign(exame.getId(),ombroDAO),ombroDAO);
        if(secoesAExportar[16]){
            document.add(paragrafoTitulo(AMPLITUDEMOVIMENTO+":"));
            document.add(paragrafoCorpo("Flexão: Direito "+ombro.getFlexaoDir()+" Esquerdo "+ ombro.getFlexaoEsq()+" Normalidade: 0-170°/180°"));
            document.add(paragrafoCorpo("Extensão: Direito "+ombro.getExtensaoDir()+" Esquerdo "+ ombro.getExtensaoEsq()+" Normalidade: 0-50°/60°"));
            document.add(paragrafoCorpo("Abdução: Direito "+ombro.getAbducaoDir()+" Esquerdo "+ ombro.getAbducaoEsq()+" Normalidade: 0-170°/180°"));
            document.add(paragrafoCorpo("Adução horizontal: Direito "+ombro.getAducaoHorDir()+" Esquerdo "+ ombro.getAducaoHorEsq()+" Normalidade: 0-50°/75°"));
            document.add(paragrafoCorpo("Rotação medial: Direito "+ombro.getRotacaoMedDir()+" Esquerdo "+ ombro.getRotacaoMedEsq()+" Normalidade: 0-60°/100°"));
            document.add(paragrafoCorpo("Rotação lateral: Direito "+ombro.getRotacaoLatDir()+" Esquerdo "+ ombro.getRotacaoLatEsq()+" Normalidade: 0-80°/90°"));
            if(ombro.getAlteracaoRitmoEscapuloUmeral()){
                if(ombro.getGrauI()){
                    document.add(paragrafoCorpo("Houve alteração no ritmo escápulo-umeral de Grau I, ângulo inferior da escápula proeminente."));
                }
                if(ombro.getGrauII()){
                    document.add(paragrafoCorpo("Houve alteração no ritmo escápulo-umeral de Grau II, ângulo inferior e metade do bordo medial da escápula proeminentes."));

                }
                if(ombro.getGrauIII()){
                    document.add(paragrafoCorpo("Houve alteração no ritmo escápulo-umeral de Grau III, ângulo inferior e do bordo medial da escápula proeminentes."));

                }
            }
        }
        if(secoesAExportar[17]){
            document.add(paragrafoTitulo(PERIMETRIA+":"));
            document.add(paragrafoCorpo("Superior 5cm Direito: "+ombro.getPerimetriaSupDir5() +" Esquerdo: "+ombro.getPerimetriaSupEsq5()));
            document.add(paragrafoCorpo("Superior 10cm Direito: "+ombro.getPerimetriaSupDir10() +" Esquerdo: "+ombro.getPerimetriaSupEsq10()));
            document.add(paragrafoCorpo("Superior 15cm Direito: "+ombro.getPerimetriaSupDir15() +" Esquerdo: "+ombro.getPerimetriaSupEsq15()));
            document.add(paragrafoCorpo("Inferior 5cm Direito: "+ombro.getPerimetriaInfDir5() +" Esquerdo: "+ombro.getPerimetriaInfEsq5()));
            document.add(paragrafoCorpo("Inferior 10cm Direito: "+ombro.getPerimetriaInfDir10() +" Esquerdo: "+ombro.getPerimetriaInfEsq10()));
            document.add(paragrafoCorpo("Inferior 15cm Direito: "+ombro.getPerimetriaInfDir15() +" Esquerdo: "+ombro.getPerimetriaInfEsq15()));
        }
        if(secoesAExportar[18]){
            document.add(paragrafoTitulo(FORCAMUSCULAR+":"));
            document.add(paragrafoCorpo("Trapézio Superior Levantador da Escápula Direito: "+ombro.getTrapezioSuperiorLevantadorDaEscapulaDir() +" Esquerdo: "+ombro.getTrapezioSuperiorLevantadorDaEscapulaEsq()));
            document.add(paragrafoCorpo("Trapézio Médio Direito: "+ombro.getTrapezioMedioDir() +" Esquerdo: "+ombro.getTrapezioMedioEsq()));
            document.add(paragrafoCorpo("Trapézio Inferior Direito: "+ombro.getTrapezioInfDir() +" Esquerdo: "+ombro.getTrapezioInfEsq()));
            document.add(paragrafoCorpo("Romboides Maior e Menor Direito: "+ombro.getRomboidesMaiorMenorDir() +" Esquerdo: "+ombro.getRomboidesMaiorMenorEsq()));
            document.add(paragrafoCorpo("Serrátil Anterior Direito: "+ombro.getSerratilAnteriorDir() +" Esquerdo: "+ombro.getSerratilAnteriorEsq()));
            document.add(paragrafoCorpo("Deltoide Anterior Direito: "+ombro.getDeltoideAnteriorDir() +" Esquerdo: "+ombro.getDeltoideAnteriorEsq()));
            document.add(paragrafoCorpo("Coracobraquial Direito: "+ombro.getCobraquialDir() +" Esquerdo: "+ombro.getCobraquialEsq()));
            document.add(paragrafoCorpo("Grande Dorsal Direito: "+ombro.getGrandeDorsalDir() +" Esquerdo: "+ombro.getGrandeDorsalEsq()));
            document.add(paragrafoCorpo("Redondo Maior Direito: "+ombro.getRedondoMaiorDir() +" Esquerdo: "+ombro.getRedondoMaiorEsq()));
            document.add(paragrafoCorpo("Supraespinhal Direito: "+ombro.getSupraespinhalDir() +" Esquerdo: "+ombro.getSupraespinhalEsq()));
            document.add(paragrafoCorpo("Deltoide Médio Direito: "+ombro.getDeltoideMedioDir() +" Esquerdo: "+ombro.getDeltoideMedioEsq()));
            document.add(paragrafoCorpo("Deltoide Posterior Direito: "+ombro.getDeltoidePosteriorDir() +" Esquerdo: "+ombro.getDeltoidePosteriorEsq()));
            document.add(paragrafoCorpo("Peitoral Maior Direito: "+ombro.getPeitoralMaiorDir() +" Esquerdo: "+ombro.getPeitoralMaiorEsq()));
            document.add(paragrafoCorpo("Subescapular Direito: "+ombro.getSubescapularDir() +" Esquerdo: "+ombro.getSubescapularEsq()));
            document.add(paragrafoCorpo("Infraespinhal e Redondo Menor Direito: "+ombro.getInfraespinhalRedondoMenorDir() +" Esquerdo: "+ombro.getInfraespinhalRedondoMenorEsq()));
        }
        if(secoesAExportar[19]){
            document.add(paragrafoTitulo(SENSIBILIDADE+":"));
            document.add(paragrafoCorpo("Sensibilidade táctil: "+ombro.getSensibilidadeTactil()));
            document.add(paragrafoCorpo("Sensibilidade térmica: "+ombro.getSensibilidadeTermica()));
            document.add(paragrafoCorpo("Sensibilidade dolorosa: "+ombro.getSensibilidadeDolorosa()));
            document.add(paragrafoCorpo("Local Avaliado: "+ombro.getSensibilidadeLocalAvaliado()));
        }
        if(secoesAExportar[20]){
            String aux;
            document.add(paragrafoTitulo(TESTESESPECIAIS+":"));

            //Jobe
            aux = "Jobe: Direito ";
            if(ombro.isJobeDir()){
                aux+="+,";
            }else{
                aux+="-,";
            }
            aux += " Esquerdo ";
            if(ombro.isJobeEsq()){
                aux+="+";
            }else{
                aux+="-";
            }
            document.add(paragrafoCorpo(aux));

            //Patte
            aux = "Patte: Direito ";
            if(ombro.isPatteDir()){
                aux+="+,";
            }else{
                aux+="-,";
            }
            aux += " Esquerdo ";
            if(ombro.isPatteEsq()){
                aux+="+";
            }else{
                aux+="-";
            }
            document.add(paragrafoCorpo(aux));

            //Gerber
            aux = "Gerber ou Lift Off: Direito ";
            if(ombro.isGerberLiftOffDir()){
                aux+="+,";
            }else{
                aux+="-,";
            }
            aux += " Esquerdo ";
            if(ombro.isGerberLiftOffEsq()){
                aux+="+";
            }else{
                aux+="-";
            }
            document.add(paragrafoCorpo(aux));

            //Neer
            aux = "Neer: Direito ";
            if(ombro.isNeerDir()){
                aux+="+,";
            }else{
                aux+="-,";
            }
            aux += " Esquerdo ";
            if(ombro.isNeerEsq()){
                aux+="+";
            }else{
                aux+="-";
            }
            document.add(paragrafoCorpo(aux));

            //Hawkins-Kennedy
            aux = "Hawkins-Kennedy: Direito ";
            if(ombro.isHawkinsDir()){
                aux+="+,";
            }else{
                aux+="-,";
            }
            aux += " Esquerdo ";
            if(ombro.isHawkinsEsq()){
                aux+="+";
            }else{
                aux+="-";
            }
            document.add(paragrafoCorpo(aux));

            //Speed
            aux = "Speed ou Palm-up: Direito ";
            if(ombro.isPalmUpSpeedDir()){
                aux+="+,";
            }else{
                aux+="-,";
            }
            aux += " Esquerdo ";
            if(ombro.isPalmUpSpeedEsq()){
                aux+="+";
            }else{
                aux+="-";
            }
            document.add(paragrafoCorpo(aux));

            //Yergason
            aux = "Yergason: Direito ";
            if(ombro.isYergasonDir()){
                aux+="+,";
            }else{
                aux+="-,";
            }
            aux += " Esquerdo ";
            if(ombro.isYergasonEsq()){
                aux+="+";
            }else{
                aux+="-";
            }
            document.add(paragrafoCorpo(aux));

            //Apreensao Anterior
            aux = "Apreensão Anterior: Direito ";
            if(ombro.isApreensaoAnteriorDir()){
                aux+="+,";
            }else{
                aux+="-,";
            }
            aux += " Esquerdo ";
            if(ombro.isApreensaoAnteriorEsq()){
                aux+="+";
            }else{
                aux+="-";
            }
            document.add(paragrafoCorpo(aux));

            //Sinal de Sulco
            aux = "Sinal de Sulco: Direito ";
            if(ombro.isSinalSulcoDir()){
                aux+="+,";
            }else{
                aux+="-,";
            }
            aux += " Esquerdo ";
            if(ombro.isSinalSulcoEsq()){
                aux+="+";
            }else{
                aux+="-";
            }
            document.add(paragrafoCorpo(aux));
        }
    }

    private Paragraph paragrafoTitulo(String texto){
        Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
        return (new Paragraph(texto, titleFont));
    }

    private Paragraph paragrafoCorpo(String texto){
        Font bodyFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
        return (new Paragraph(texto, bodyFont));
    }




    private void configuraLista() {
        ListView listaDeSecoes = findViewById(R.id.activity_exportar_exame_lista);
        exportarExameView.configuraAdapter(listaDeSecoes);
        configuraListenerDeCliquePorItem(listaDeSecoes);
    }

    private void configuraListenerDeCliquePorItem(ListView listaDeSecoes) {
        listaDeSecoes.setOnItemClickListener((adapterView, view, position, id) -> {
            Secao secaoEscolhida = (Secao) adapterView.getItemAtPosition(position);
            secoesAExportar[secaoEscolhida.getId()-1] = !secoesAExportar[secaoEscolhida.getId()-1];
            exportarExameView.atualizaSecoes(secoesAExportar);
        });
    }

    private void carregaExame() { // pega os dados do Serializable para identificar o exame
        Intent dados = getIntent();

        if (dados.hasExtra(CHAVE_EXAME)) {
            exame = exameQueryManager.getOne((String) Objects.requireNonNull(dados.getSerializableExtra(CHAVE_EXAME)), exameDao);
            preencheCampos();
            setTitle(getString(R.string.exportar_exame));
            secoes = secoesQueryManager.getOne(exame.getId(), secoesDao);
            paciente = pacienteQueryManager.getOne(exame.getPaciente(),pacienteDAO);
        }
    }

    private void preencheCampos() { //carrega os valores do banco
        exame = exameQueryManager.getOne(exame.getId(), exameDao);
        secoes = secoesQueryManager.getOne(exame.getId(), secoesDao);
        paciente = pacienteQueryManager.getOne(exame.getPaciente(), pacienteDAO);
    }

}
