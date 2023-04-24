package br.ufsm.fisioexam.model;

import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_TIPO_VAZIO;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;

@Entity(foreignKeys = {@ForeignKey(entity = Paciente.class, parentColumns = "id", childColumns = "paciente", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)}, indices = {@Index(name = "idx_exame_paciente", value = {"paciente"})})
public class Exame implements Serializable {
    @PrimaryKey()
    private @NonNull
    String id;

    //    Chave estrangeira.
    private String paciente;

    //    Chave Criacao.
    private String creationKey;

    @NonNull
    private String tipo;

    private long data;
    private String diagnosticoMedico;
    private String queixaPrincipal;
    private String historiaDoencaAtual;
    private boolean senteDor;
    private String dorHaQuantoTempo;
    private boolean dorIrradiada;
    private boolean dorQueimacao;
    private boolean dorPontada;
    private boolean dorPeso;
    private boolean dorFormigamento;
    private boolean dorOutra;
    private String tipoDeDor;
    private String aparicaoDor;
    private boolean dorRepouso;
    private int intensidadeDor;
    private String locaisDor;
    private boolean tratamentoPassado;
    private String qualTratamentoPassado;
    private String tempoTratamentoPassado;
    private String duracaoTratamentoPassado;
    private boolean tratamentoPassadoMelhora;
    private boolean afastamentoFuncao;
    private String qualAfastamentoFuncao;
    private String tempoAfastamento;
    private String antecedentesPessoais;
    private boolean doencasAssociadasHipertensao;
    private boolean doencasAssociadasDiabetes;
    private boolean doencasAssociadasCardiovascular;
    private boolean doencasAssociadasPulmonar;
    private boolean doencasAssociadasCancer;
    private boolean doencasAssociadasOutra;
    private String outraDoencasAssociadas;
    private String medicamentosEmUso;
    private boolean historiaFamiliarHipertensao;
    private boolean historiaFamiliarDiabetes;
    private boolean historiaFamiliarCardiovascular;
    private boolean historiaFamiliarPulmonar;
    private boolean historiaFamiliarCancer;
    private boolean historiaFamiliarOutra;
    private String outraHistoriaFamiliar;
    private String ocupacaoAtual;
    private String tempoTrabalhoAtual;
    private String ocupacaoAnterior;
    private String tempoTrabalhoAnterior;
    private String moradia;
    private boolean tabagista;
    private String quantoCigarro;
    private boolean etilista;
    private String quantoAlcool;
    private boolean atividadeFisica;
    private String qualAtividadeFisica;
    private String quantaAtividadeFisica;
    private boolean restricaoAlimentacaoSal;
    private boolean restricaoAlimentacaoAcucar;
    private boolean restricaoAlimentacaoFritura;
    private boolean lazer;
    private String qualLazer;
    private String examesComplementares;
    private String PA;
    private String FC;
    private String FR;
    private String tempCorporal;

    //Inspecao
    private boolean coloracaoPeleNormalInsp;
    private boolean coloracaoPeleCianoseInsp;
    private boolean coloracaoPeleVermelhidaoInsp;
    private boolean hidratacaoPeleHidrInsp;
    private boolean hidratacaoPeleDesidrInsp;
    private boolean espessuraPeleNormalInsp;
    private boolean espessuraPeleFinaInsp;
    private boolean espessuraPeleEspessaInsp;
    private boolean presencaCicatrizesInsp;
    private String localCicatrizesInsp;
    private String tamanhoCicatrizesInsp;
    private boolean presencaFeridasInsp;
    private String localFeridasInsp;
    private String tamanhoFeridasInsp;
    private boolean trofismoMuscularEutroficoInsp;
    private boolean trofismoMuscularHipotroficoInsp;
    private boolean trofismoMuscularHipertroficoInsp;
    private String localTrofismoMuscularInsp;
    private boolean presencaEdemaInsp;
    private String localEdemaInsp;
    private boolean tecidoOsseoNormalInsp;
    private boolean tecidoOsseoCaloInsp;
    private boolean tecidoOsseoDeformInsp;
    private boolean marchaNormalInsp;
    private boolean marchaAntargicaInsp;
    private boolean marchaComDorOmbroInsp;
    private boolean presencaProtesesInsp;
    private String localProtesesInsp;
    private boolean presencaOrtesesInsp;
    private String localOrtesesInsp;
    private boolean presencaCateteresESondasInsp;
    private String localCateteresESondasInsp;

    //Palpacao
    private boolean coloracaoPeleSAPalp;
    private boolean coloracaoPeleVermPalp;
    private boolean coloracaoPeleEquiPalp;
    private String localColoracaoPelePalp;
    private boolean feridasPalp;
    private String tamFeridasPalp;
    private String localFeridasPalp;
    private boolean cicatrizesPalp;
    private boolean aderenciaCicatrizesPalp;
    private String localCicatrizesPalp;
    private boolean presencaEdemaPalp;
    private boolean cacifoPresencaEdemaPalp;
    private String localPresencaEdemaPalp;
    private boolean temperaturaNormalPalp;
    private boolean temperaturaAumentoPalp;
    private boolean temperaturaDiminuiPalp;
    private String localTemperaturaPalp;
    private boolean tensaoMuscularNaoPalp;
    private boolean tensaoMuscularAumentadaPalp;
    private boolean tensaoMuscularDiminuidaPalp;
    private String localTensaoMuscularPalp;
    private boolean tecidoOsseoPalp;
    private String localTecidoOsseoPalp;
    private boolean dorPalp;
    private String grauDorPalp;
    private String localDorPalp;

    //Resultados
    private String observacoes;
    private String diagnosticoFisioterapeutico;
    private String objetivosTratamento;
    private String planoTratamento;
    private String evolucaoFisioterapia;

    public Exame(@NonNull String paciente, @NonNull String tipo, String creationKey) {
        this.paciente = paciente;
        this.tipo = tipo;
        this.creationKey = creationKey;
        id = UUID.randomUUID().toString();
    }

    @Ignore
    public Exame() {
        id = UUID.randomUUID().toString();
        tipo = CHAVE_TIPO_VAZIO;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public String getCreationKey() {
        return creationKey;
    }


    public long getData() {
        return data;
    }

    public void setData(long data) {
        this.data = data;
    }

    public String getDiagnosticoMedico() {
        return diagnosticoMedico;
    }

    public void setDiagnosticoMedico(String diagnosticoMedico) {
        this.diagnosticoMedico = diagnosticoMedico;
    }

    public String getQueixaPrincipal() {
        return queixaPrincipal;
    }

    public void setQueixaPrincipal(String queixaPrincipal) {
        this.queixaPrincipal = queixaPrincipal;
    }

    public String getHistoriaDoencaAtual() {
        return historiaDoencaAtual;
    }

    public void setHistoriaDoencaAtual(String historiaDoencaAtual) {
        this.historiaDoencaAtual = historiaDoencaAtual;
    }

    public boolean isSenteDor() {
        return senteDor;
    }

    public void setSenteDor(boolean senteDor) {
        this.senteDor = senteDor;
    }

    public String getDorHaQuantoTempo() {
        return dorHaQuantoTempo;
    }

    public void setDorHaQuantoTempo(String dorHaQuantoTempo) {
        this.dorHaQuantoTempo = dorHaQuantoTempo;
    }

    public boolean isDorIrradiada() {
        return dorIrradiada;
    }

    public void setDorIrradiada(boolean dorIrradiada) {
        this.dorIrradiada = dorIrradiada;
    }

    public boolean isDorQueimacao() {
        return dorQueimacao;
    }

    public void setDorQueimacao(boolean dorQueimacao) {
        this.dorQueimacao = dorQueimacao;
    }

    public boolean isDorPontada() {
        return dorPontada;
    }

    public void setDorPontada(boolean dorPontada) {
        this.dorPontada = dorPontada;
    }

    public boolean isDorPeso() {
        return dorPeso;
    }

    public void setDorPeso(boolean dorPeso) {
        this.dorPeso = dorPeso;
    }

    public boolean isDorFormigamento() {
        return dorFormigamento;
    }

    public void setDorFormigamento(boolean dorFormigamento) {
        this.dorFormigamento = dorFormigamento;
    }

    public boolean isDorOutra() {
        return dorOutra;
    }

    public void setDorOutra(boolean dorOutra) {
        this.dorOutra = dorOutra;
    }

    public String getTipoDeDor() {
        return tipoDeDor;
    }

    public void setTipoDeDor(String tipoDeDor) {
        this.tipoDeDor = tipoDeDor;
    }

    public String getAparicaoDor() {
        return aparicaoDor;
    }

    public void setAparicaoDor(String aparicaoDor) {
        this.aparicaoDor = aparicaoDor;
    }

    public boolean isDorRepouso() {
        return dorRepouso;
    }

    public void setDorRepouso(boolean dorRepouso) {
        this.dorRepouso = dorRepouso;
    }

    public int getIntensidadeDor() {
        return intensidadeDor;
    }

    public void setIntensidadeDor(int intensidadeDor) {
        this.intensidadeDor = intensidadeDor;
    }

    public String getLocaisDor() {
        return locaisDor;
    }

    public void setLocaisDor(String locaisDor) {
        this.locaisDor = locaisDor;
    }

    public boolean isTratamentoPassado() {
        return tratamentoPassado;
    }

    public void setTratamentoPassado(boolean tratamentoPassado) {
        this.tratamentoPassado = tratamentoPassado;
    }

    public String getQualTratamentoPassado() {
        return qualTratamentoPassado;
    }

    public void setQualTratamentoPassado(String qualTratamentoPassado) {
        this.qualTratamentoPassado = qualTratamentoPassado;
    }

    public String getTempoTratamentoPassado() {
        return tempoTratamentoPassado;
    }

    public void setTempoTratamentoPassado(String tempoTratamentoPassado) {
        this.tempoTratamentoPassado = tempoTratamentoPassado;
    }

    public String getDuracaoTratamentoPassado() {
        return duracaoTratamentoPassado;
    }

    public void setDuracaoTratamentoPassado(String duracaoTratamentoPassado) {
        this.duracaoTratamentoPassado = duracaoTratamentoPassado;
    }

    public boolean isTratamentoPassadoMelhora() {
        return tratamentoPassadoMelhora;
    }

    public void setTratamentoPassadoMelhora(boolean tratamentoPassadoMelhora) {
        this.tratamentoPassadoMelhora = tratamentoPassadoMelhora;
    }

    public boolean isAfastamentoFuncao() {
        return afastamentoFuncao;
    }

    public void setAfastamentoFuncao(boolean afastamentoFuncao) {
        this.afastamentoFuncao = afastamentoFuncao;
    }

    public String getQualAfastamentoFuncao() {
        return qualAfastamentoFuncao;
    }

    public void setQualAfastamentoFuncao(String qualAfastamentoFuncao) {
        this.qualAfastamentoFuncao = qualAfastamentoFuncao;
    }

    public String getTempoAfastamento() {
        return tempoAfastamento;
    }

    public void setTempoAfastamento(String tempoAfastamento) {
        this.tempoAfastamento = tempoAfastamento;
    }

    public String getAntecedentesPessoais() {
        return antecedentesPessoais;
    }

    public void setAntecedentesPessoais(String antecedentesPessoais) {
        this.antecedentesPessoais = antecedentesPessoais;
    }

    public boolean isDoencasAssociadasHipertensao() {
        return doencasAssociadasHipertensao;
    }

    public void setDoencasAssociadasHipertensao(boolean doencasAssociadasHipertensao) {
        this.doencasAssociadasHipertensao = doencasAssociadasHipertensao;
    }

    public boolean isDoencasAssociadasDiabetes() {
        return doencasAssociadasDiabetes;
    }

    public void setDoencasAssociadasDiabetes(boolean doencasAssociadasDiabetes) {
        this.doencasAssociadasDiabetes = doencasAssociadasDiabetes;
    }

    public boolean isDoencasAssociadasCardiovascular() {
        return doencasAssociadasCardiovascular;
    }

    public void setDoencasAssociadasCardiovascular(boolean doencasAssociadasCardiovascular) {
        this.doencasAssociadasCardiovascular = doencasAssociadasCardiovascular;
    }

    public boolean isDoencasAssociadasPulmonar() {
        return doencasAssociadasPulmonar;
    }

    public void setDoencasAssociadasPulmonar(boolean doencasAssociadasPulmonar) {
        this.doencasAssociadasPulmonar = doencasAssociadasPulmonar;
    }

    public boolean isDoencasAssociadasCancer() {
        return doencasAssociadasCancer;
    }

    public void setDoencasAssociadasCancer(boolean doencasAssociadasCancer) {
        this.doencasAssociadasCancer = doencasAssociadasCancer;
    }

    public boolean isDoencasAssociadasOutra() {
        return doencasAssociadasOutra;
    }

    public void setDoencasAssociadasOutra(boolean doencasAssociadasOutra) {
        this.doencasAssociadasOutra = doencasAssociadasOutra;
    }

    public String getOutraDoencasAssociadas() {
        return outraDoencasAssociadas;
    }

    public void setOutraDoencasAssociadas(String outraDoencasAssociadas) {
        this.outraDoencasAssociadas = outraDoencasAssociadas;
    }

    public String getMedicamentosEmUso() {
        return medicamentosEmUso;
    }

    public void setMedicamentosEmUso(String medicamentosEmUso) {
        this.medicamentosEmUso = medicamentosEmUso;
    }

    public boolean isHistoriaFamiliarHipertensao() {
        return historiaFamiliarHipertensao;
    }

    public void setHistoriaFamiliarHipertensao(boolean historiaFamiliarHipertensao) {
        this.historiaFamiliarHipertensao = historiaFamiliarHipertensao;
    }

    public boolean isHistoriaFamiliarDiabetes() {
        return historiaFamiliarDiabetes;
    }

    public void setHistoriaFamiliarDiabetes(boolean historiaFamiliarDiabetes) {
        this.historiaFamiliarDiabetes = historiaFamiliarDiabetes;
    }

    public boolean isHistoriaFamiliarCardiovascular() {
        return historiaFamiliarCardiovascular;
    }

    public void setHistoriaFamiliarCardiovascular(boolean historiaFamiliarCardiovascular) {
        this.historiaFamiliarCardiovascular = historiaFamiliarCardiovascular;
    }

    public boolean isHistoriaFamiliarPulmonar() {
        return historiaFamiliarPulmonar;
    }

    public void setHistoriaFamiliarPulmonar(boolean historiaFamiliarPulmonar) {
        this.historiaFamiliarPulmonar = historiaFamiliarPulmonar;
    }

    public boolean isHistoriaFamiliarCancer() {
        return historiaFamiliarCancer;
    }

    public void setHistoriaFamiliarCancer(boolean historiaFamiliarCancer) {
        this.historiaFamiliarCancer = historiaFamiliarCancer;
    }

    public boolean isHistoriaFamiliarOutra() {
        return historiaFamiliarOutra;
    }

    public void setHistoriaFamiliarOutra(boolean historiaFamiliarOutra) {
        this.historiaFamiliarOutra = historiaFamiliarOutra;
    }

    public String getOutraHistoriaFamiliar() {
        return outraHistoriaFamiliar;
    }

    public void setOutraHistoriaFamiliar(String outraHistoriaFamiliar) {
        this.outraHistoriaFamiliar = outraHistoriaFamiliar;
    }

    public String getOcupacaoAtual() {
        return ocupacaoAtual;
    }

    public void setOcupacaoAtual(String ocupacaoAtual) {
        this.ocupacaoAtual = ocupacaoAtual;
    }

    public String getTempoTrabalhoAtual() {
        return tempoTrabalhoAtual;
    }

    public void setTempoTrabalhoAtual(String tempoTrabalhoAtual) {
        this.tempoTrabalhoAtual = tempoTrabalhoAtual;
    }

    public String getOcupacaoAnterior() {
        return ocupacaoAnterior;
    }

    public void setOcupacaoAnterior(String ocupacaoAnterior) {
        this.ocupacaoAnterior = ocupacaoAnterior;
    }

    public String getTempoTrabalhoAnterior() {
        return tempoTrabalhoAnterior;
    }

    public void setTempoTrabalhoAnterior(String tempoTrabalhoAnterior) {
        this.tempoTrabalhoAnterior = tempoTrabalhoAnterior;
    }

    public String getMoradia() {
        return moradia;
    }

    public void setMoradia(String moradia) {
        this.moradia = moradia;
    }

    public boolean isTabagista() {
        return tabagista;
    }

    public void setTabagista(boolean tabagista) {
        this.tabagista = tabagista;
    }

    public String getQuantoCigarro() {
        return quantoCigarro;
    }

    public void setQuantoCigarro(String quantoCigarro) {
        this.quantoCigarro = quantoCigarro;
    }

    public boolean isEtilista() {
        return etilista;
    }

    public void setEtilista(boolean etilista) {
        this.etilista = etilista;
    }

    public String getQuantoAlcool() {
        return quantoAlcool;
    }

    public void setQuantoAlcool(String quantoAlcool) {
        this.quantoAlcool = quantoAlcool;
    }

    public boolean isAtividadeFisica() {
        return atividadeFisica;
    }

    public void setAtividadeFisica(boolean atividadeFisica) {
        this.atividadeFisica = atividadeFisica;
    }

    public String getQualAtividadeFisica() {
        return qualAtividadeFisica;
    }

    public void setQualAtividadeFisica(String qualAtividadeFisica) {
        this.qualAtividadeFisica = qualAtividadeFisica;
    }

    public String getQuantaAtividadeFisica() {
        return quantaAtividadeFisica;
    }

    public void setQuantaAtividadeFisica(String quantoAtividadeFisica) {
        this.quantaAtividadeFisica = quantoAtividadeFisica;
    }

    public boolean isRestricaoAlimentacaoSal() {
        return restricaoAlimentacaoSal;
    }

    public void setRestricaoAlimentacaoSal(boolean restricaoAlimentacaoSal) {
        this.restricaoAlimentacaoSal = restricaoAlimentacaoSal;
    }

    public boolean isRestricaoAlimentacaoAcucar() {
        return restricaoAlimentacaoAcucar;
    }

    public void setRestricaoAlimentacaoAcucar(boolean restricaoAlimentacaoAcucar) {
        this.restricaoAlimentacaoAcucar = restricaoAlimentacaoAcucar;
    }

    public boolean isRestricaoAlimentacaoFritura() {
        return restricaoAlimentacaoFritura;
    }

    public void setRestricaoAlimentacaoFritura(boolean restricaoAlimentacaoFritura) {
        this.restricaoAlimentacaoFritura = restricaoAlimentacaoFritura;
    }

    public boolean isLazer() {
        return lazer;
    }

    public void setLazer(boolean lazer) {
        this.lazer = lazer;
    }

    public String getQualLazer() {
        return qualLazer;
    }

    public void setQualLazer(String qualLazer) {
        this.qualLazer = qualLazer;
    }

    public String getExamesComplementares() {
        return examesComplementares;
    }

    public void setExamesComplementares(String examesComplementares) {
        this.examesComplementares = examesComplementares;
    }

    public String getPA() {
        return PA;
    }

    public void setPA(String PA) {
        this.PA = PA;
    }

    public String getFC() {
        return FC;
    }

    public void setFC(String FC) {
        this.FC = FC;
    }

    public String getFR() {
        return FR;
    }

    public void setFR(String FR) {
        this.FR = FR;
    }

    public String getTempCorporal() {
        return tempCorporal;
    }

    public void setTempCorporal(String tempCorporal) {
        this.tempCorporal = tempCorporal;
    }

    public boolean isColoracaoPeleNormalInsp() {
        return coloracaoPeleNormalInsp;
    }

    public void setColoracaoPeleNormalInsp(boolean coloracaoPeleNormalInsp) {
        this.coloracaoPeleNormalInsp = coloracaoPeleNormalInsp;
    }

    public boolean isColoracaoPeleCianoseInsp() {
        return coloracaoPeleCianoseInsp;
    }

    public void setColoracaoPeleCianoseInsp(boolean coloracaoPeleCianoseInsp) {
        this.coloracaoPeleCianoseInsp = coloracaoPeleCianoseInsp;
    }

    public boolean isColoracaoPeleVermelhidaoInsp() {
        return coloracaoPeleVermelhidaoInsp;
    }

    public void setColoracaoPeleVermelhidaoInsp(boolean coloracaoPeleVermelhidaoInsp) {
        this.coloracaoPeleVermelhidaoInsp = coloracaoPeleVermelhidaoInsp;
    }

    public boolean isHidratacaoPeleHidrInsp() {
        return hidratacaoPeleHidrInsp;
    }

    public void setHidratacaoPeleHidrInsp(boolean hidratacaoPeleHidrInsp) {
        this.hidratacaoPeleHidrInsp = hidratacaoPeleHidrInsp;
    }

    public boolean isHidratacaoPeleDesidrInsp() {
        return hidratacaoPeleDesidrInsp;
    }

    public void setHidratacaoPeleDesidrInsp(boolean hidratacaoPeleDesidrInsp) {
        this.hidratacaoPeleDesidrInsp = hidratacaoPeleDesidrInsp;
    }

    public boolean isEspessuraPeleNormalInsp() {
        return espessuraPeleNormalInsp;
    }

    public void setEspessuraPeleNormalInsp(boolean espessuraPeleNormalInsp) {
        this.espessuraPeleNormalInsp = espessuraPeleNormalInsp;
    }

    public boolean isEspessuraPeleFinaInsp() {
        return espessuraPeleFinaInsp;
    }

    public void setEspessuraPeleFinaInsp(boolean espessuraPeleFinaInsp) {
        this.espessuraPeleFinaInsp = espessuraPeleFinaInsp;
    }

    public boolean isEspessuraPeleEspessaInsp() {
        return espessuraPeleEspessaInsp;
    }

    public void setEspessuraPeleEspessaInsp(boolean espessuraPeleEspessaInsp) {
        this.espessuraPeleEspessaInsp = espessuraPeleEspessaInsp;
    }

    public boolean isPresencaCicatrizesInsp() {
        return presencaCicatrizesInsp;
    }

    public void setPresencaCicatrizesInsp(boolean presencaCicatrizesInsp) {
        this.presencaCicatrizesInsp = presencaCicatrizesInsp;
    }

    public String getLocalCicatrizesInsp() {
        return localCicatrizesInsp;
    }

    public void setLocalCicatrizesInsp(String localCicatrizesInsp) {
        this.localCicatrizesInsp = localCicatrizesInsp;
    }

    public String getTamanhoCicatrizesInsp() {
        return tamanhoCicatrizesInsp;
    }

    public void setTamanhoCicatrizesInsp(String tamanhoCicatrizesInsp) {
        this.tamanhoCicatrizesInsp = tamanhoCicatrizesInsp;
    }

    public boolean isPresencaFeridasInsp() {
        return presencaFeridasInsp;
    }

    public void setPresencaFeridasInsp(boolean presencaFeridasInsp) {
        this.presencaFeridasInsp = presencaFeridasInsp;
    }

    public String getLocalFeridasInsp() {
        return localFeridasInsp;
    }

    public void setLocalFeridasInsp(String localFeridasInsp) {
        this.localFeridasInsp = localFeridasInsp;
    }

    public String getTamanhoFeridasInsp() {
        return tamanhoFeridasInsp;
    }

    public void setTamanhoFeridasInsp(String tamanhoFeridasInsp) {
        this.tamanhoFeridasInsp = tamanhoFeridasInsp;
    }

    public boolean isTrofismoMuscularEutroficoInsp() {
        return trofismoMuscularEutroficoInsp;
    }

    public void setTrofismoMuscularEutroficoInsp(boolean trofismoMuscularEutroficoInsp) {
        this.trofismoMuscularEutroficoInsp = trofismoMuscularEutroficoInsp;
    }

    public boolean isTrofismoMuscularHipotroficoInsp() {
        return trofismoMuscularHipotroficoInsp;
    }

    public void setTrofismoMuscularHipotroficoInsp(boolean trofismoMuscularHipotroficoInsp) {
        this.trofismoMuscularHipotroficoInsp = trofismoMuscularHipotroficoInsp;
    }

    public boolean isTrofismoMuscularHipertroficoInsp() {
        return trofismoMuscularHipertroficoInsp;
    }

    public void setTrofismoMuscularHipertroficoInsp(boolean trofismoMuscularHipertroficoInsp) {
        this.trofismoMuscularHipertroficoInsp = trofismoMuscularHipertroficoInsp;
    }

    public String getLocalTrofismoMuscularInsp() {
        return localTrofismoMuscularInsp;
    }

    public void setLocalTrofismoMuscularInsp(String localTrofismoMuscularInsp) {
        this.localTrofismoMuscularInsp = localTrofismoMuscularInsp;
    }

    public boolean isPresencaEdemaInsp() {
        return presencaEdemaInsp;
    }

    public void setPresencaEdemaInsp(boolean presencaEdemaInsp) {
        this.presencaEdemaInsp = presencaEdemaInsp;
    }

    public String getLocalEdemaInsp() {
        return localEdemaInsp;
    }

    public void setLocalEdemaInsp(String localEdemaInsp) {
        this.localEdemaInsp = localEdemaInsp;
    }

    public boolean isTecidoOsseoNormalInsp() {
        return tecidoOsseoNormalInsp;
    }

    public void setTecidoOsseoNormalInsp(boolean tecidoOsseoNormalInsp) {
        this.tecidoOsseoNormalInsp = tecidoOsseoNormalInsp;
    }

    public boolean isTecidoOsseoCaloInsp() {
        return tecidoOsseoCaloInsp;
    }

    public void setTecidoOsseoCaloInsp(boolean tecidoOsseoCaloInsp) {
        this.tecidoOsseoCaloInsp = tecidoOsseoCaloInsp;
    }

    public boolean isTecidoOsseoDeformInsp() {
        return tecidoOsseoDeformInsp;
    }

    public void setTecidoOsseoDeformInsp(boolean tecidoOsseoDeformInsp) {
        this.tecidoOsseoDeformInsp = tecidoOsseoDeformInsp;
    }

    public boolean isMarchaNormalInsp() {
        return marchaNormalInsp;
    }

    public void setMarchaNormalInsp(boolean marchaNormalInsp) {
        this.marchaNormalInsp = marchaNormalInsp;
    }

    public boolean isMarchaAntargicaInsp() {
        return marchaAntargicaInsp;
    }

    public void setMarchaAntargicaInsp(boolean marchaAntargicaInsp) {
        this.marchaAntargicaInsp = marchaAntargicaInsp;
    }

    public boolean isMarchaComDorOmbroInsp() {
        return marchaComDorOmbroInsp;
    }

    public void setMarchaComDorOmbroInsp(boolean marchaComDorOmbroInsp) {
        this.marchaComDorOmbroInsp = marchaComDorOmbroInsp;
    }

    public boolean isPresencaProtesesInsp() {
        return presencaProtesesInsp;
    }

    public void setPresencaProtesesInsp(boolean presencaProtesesInsp) {
        this.presencaProtesesInsp = presencaProtesesInsp;
    }

    public String getLocalProtesesInsp() {
        return localProtesesInsp;
    }

    public void setLocalProtesesInsp(String localProtesesInsp) {
        this.localProtesesInsp = localProtesesInsp;
    }

    public boolean isPresencaOrtesesInsp() {
        return presencaOrtesesInsp;
    }

    public void setPresencaOrtesesInsp(boolean presencaOrtesesInsp) {
        this.presencaOrtesesInsp = presencaOrtesesInsp;
    }

    public String getLocalOrtesesInsp() {
        return localOrtesesInsp;
    }

    public void setLocalOrtesesInsp(String localOrtesesInsp) {
        this.localOrtesesInsp = localOrtesesInsp;
    }

    public boolean isPresencaCateteresESondasInsp() {
        return presencaCateteresESondasInsp;
    }

    public void setPresencaCateteresESondasInsp(boolean presencaCateteresESondasInsp) {
        this.presencaCateteresESondasInsp = presencaCateteresESondasInsp;
    }

    public String getLocalCateteresESondasInsp() {
        return localCateteresESondasInsp;
    }

    public void setLocalCateteresESondasInsp(String localCateteresESondasInsp) {
        this.localCateteresESondasInsp = localCateteresESondasInsp;
    }

    public boolean isColoracaoPeleSAPalp() {
        return coloracaoPeleSAPalp;
    }

    public void setColoracaoPeleSAPalp(boolean coloracaoPeleSAPalp) {
        this.coloracaoPeleSAPalp = coloracaoPeleSAPalp;
    }

    public boolean isColoracaoPeleVermPalp() {
        return coloracaoPeleVermPalp;
    }

    public void setColoracaoPeleVermPalp(boolean coloracaoPeleVermPalp) {
        this.coloracaoPeleVermPalp = coloracaoPeleVermPalp;
    }

    public boolean isColoracaoPeleEquiPalp() {
        return coloracaoPeleEquiPalp;
    }

    public void setColoracaoPeleEquiPalp(boolean coloracaoPeleEquiPalp) {
        this.coloracaoPeleEquiPalp = coloracaoPeleEquiPalp;
    }

    public String getLocalColoracaoPelePalp() {
        return localColoracaoPelePalp;
    }

    public void setLocalColoracaoPelePalp(String localColoracaoPelePalp) {
        this.localColoracaoPelePalp = localColoracaoPelePalp;
    }

    public boolean isFeridasPalp() {
        return feridasPalp;
    }

    public void setFeridasPalp(boolean feridasPalp) {
        this.feridasPalp = feridasPalp;
    }

    public String getTamFeridasPalp() {
        return tamFeridasPalp;
    }

    public void setTamFeridasPalp(String tamFeridasPalp) {
        this.tamFeridasPalp = tamFeridasPalp;
    }

    public String getLocalFeridasPalp() {
        return localFeridasPalp;
    }

    public void setLocalFeridasPalp(String localFeridasPalp) {
        this.localFeridasPalp = localFeridasPalp;
    }

    public boolean isCicatrizesPalp() {
        return cicatrizesPalp;
    }

    public void setCicatrizesPalp(boolean cicatrizesPalp) {
        this.cicatrizesPalp = cicatrizesPalp;
    }

    public boolean isAderenciaCicatrizesPalp() {
        return aderenciaCicatrizesPalp;
    }

    public void setAderenciaCicatrizesPalp(boolean aderenciaCicatrizesPalp) {
        this.aderenciaCicatrizesPalp = aderenciaCicatrizesPalp;
    }

    public String getLocalCicatrizesPalp() {
        return localCicatrizesPalp;
    }

    public void setLocalCicatrizesPalp(String localCicatrizesPalp) {
        this.localCicatrizesPalp = localCicatrizesPalp;
    }

    public boolean isPresencaEdemaPalp() {
        return presencaEdemaPalp;
    }

    public void setPresencaEdemaPalp(boolean presencaEdemaPalp) {
        this.presencaEdemaPalp = presencaEdemaPalp;
    }

    public boolean isCacifoPresencaEdemaPalp() {
        return cacifoPresencaEdemaPalp;
    }

    public void setCacifoPresencaEdemaPalp(boolean cacifoPresencaEdemaPalp) {
        this.cacifoPresencaEdemaPalp = cacifoPresencaEdemaPalp;
    }

    public String getLocalPresencaEdemaPalp() {
        return localPresencaEdemaPalp;
    }

    public void setLocalPresencaEdemaPalp(String localPresencaEdemaPalp) {
        this.localPresencaEdemaPalp = localPresencaEdemaPalp;
    }

    public boolean isTemperaturaNormalPalp() {
        return temperaturaNormalPalp;
    }

    public void setTemperaturaNormalPalp(boolean temperaturaNormalPalp) {
        this.temperaturaNormalPalp = temperaturaNormalPalp;
    }

    public boolean isTemperaturaAumentoPalp() {
        return temperaturaAumentoPalp;
    }

    public void setTemperaturaAumentoPalp(boolean temperaturaAumentoPalp) {
        this.temperaturaAumentoPalp = temperaturaAumentoPalp;
    }

    public boolean isTemperaturaDiminuiPalp() {
        return temperaturaDiminuiPalp;
    }

    public void setTemperaturaDiminuiPalp(boolean temperaturaDiminuiPalp) {
        this.temperaturaDiminuiPalp = temperaturaDiminuiPalp;
    }

    public String getLocalTemperaturaPalp() {
        return localTemperaturaPalp;
    }

    public void setLocalTemperaturaPalp(String localTemperaturaPalp) {
        this.localTemperaturaPalp = localTemperaturaPalp;
    }

    public boolean isTensaoMuscularNaoPalp() {
        return tensaoMuscularNaoPalp;
    }

    public void setTensaoMuscularNaoPalp(boolean tensaoMuscularNaoPalp) {
        this.tensaoMuscularNaoPalp = tensaoMuscularNaoPalp;
    }

    public boolean isTensaoMuscularAumentadaPalp() {
        return tensaoMuscularAumentadaPalp;
    }

    public void setTensaoMuscularAumentadaPalp(boolean tensaoMuscularAumentadaPalp) {
        this.tensaoMuscularAumentadaPalp = tensaoMuscularAumentadaPalp;
    }

    public boolean isTensaoMuscularDiminuidaPalp() {
        return tensaoMuscularDiminuidaPalp;
    }

    public void setTensaoMuscularDiminuidaPalp(boolean tensaoMuscularDiminuidaPalp) {
        this.tensaoMuscularDiminuidaPalp = tensaoMuscularDiminuidaPalp;
    }

    public String getLocalTensaoMuscularPalp() {
        return localTensaoMuscularPalp;
    }

    public void setLocalTensaoMuscularPalp(String localTensaoMuscularPalp) {
        this.localTensaoMuscularPalp = localTensaoMuscularPalp;
    }

    public boolean isTecidoOsseoPalp() {
        return tecidoOsseoPalp;
    }

    public void setTecidoOsseoPalp(boolean tecidoOsseoPalp) {
        this.tecidoOsseoPalp = tecidoOsseoPalp;
    }

    public String getLocalTecidoOsseoPalp() {
        return localTecidoOsseoPalp;
    }

    public void setLocalTecidoOsseoPalp(String localTecidoOsseoPalp) {
        this.localTecidoOsseoPalp = localTecidoOsseoPalp;
    }

    public boolean isDorPalp() {
        return dorPalp;
    }

    public void setDorPalp(boolean dorPalp) {
        this.dorPalp = dorPalp;
    }

    public String getGrauDorPalp() {
        return grauDorPalp;
    }

    public void setGrauDorPalp(String grauDorPalp) {
        this.grauDorPalp = grauDorPalp;
    }

    public String getLocalDorPalp() {
        return localDorPalp;
    }

    public void setLocalDorPalp(String localDorPalp) {
        this.localDorPalp = localDorPalp;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getDiagnosticoFisioterapeutico() {
        return diagnosticoFisioterapeutico;
    }

    public void setDiagnosticoFisioterapeutico(String diagnosticoFisioterapeutico) {
        this.diagnosticoFisioterapeutico = diagnosticoFisioterapeutico;
    }

    public String getObjetivosTratamento() {
        return objetivosTratamento;
    }

    public void setObjetivosTratamento(String objetivosTratamento) {
        this.objetivosTratamento = objetivosTratamento;
    }

    public String getPlanoTratamento() {
        return planoTratamento;
    }

    public void setPlanoTratamento(String planoTratamento) {
        this.planoTratamento = planoTratamento;
    }

    public String getEvolucaoFisioterapia() {
        return evolucaoFisioterapia;
    }

    public void setEvolucaoFisioterapia(String evolucaoFisioterapia) {
        this.evolucaoFisioterapia = evolucaoFisioterapia;
    }

    public String getDataString() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(data);
        String formatoData = "dd/MM/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatoData, new Locale("pt", "BR"));
        return (dateFormat.format(calendar.getTime()));
    }

    @NonNull
    public String getTipo() {
        return tipo;
    }

    public void setTipo(@NonNull String tipo) {
        this.tipo = tipo;
    }
}
