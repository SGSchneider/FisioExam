package br.ufsm.fisioexam.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(foreignKeys = {@ForeignKey(entity  = Exame.class,
        parentColumns = "id",
        childColumns = "id",
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE)})
public class Secoes implements Serializable {
    @PrimaryKey
    private @NonNull String id;

    private boolean diagnosticoMedico;
    private boolean queixaPrincipal;
    private boolean historiaDoencaAtual;
    private boolean dor;
    private boolean tratamentoAnterior;
    private boolean afastamentoDaFuncao;
    private boolean antecedentesPessoais;
    private boolean doencasAssociadas;
    private boolean medicamentosEmUso;
    private boolean historiaFamiliar;
    private boolean historiaOcupacional;
    private boolean historiaSocial;
    private boolean examesComplementares;
    private boolean sinaisVitais;
    private boolean inspecao;
    private boolean palpacao;
    private boolean amplitudeMovimento;
    private boolean perimetria;
    private boolean forcaMuscular;
    private boolean sensibilidade;
    private boolean testesEspeciais;
    private boolean observacoes;
    private boolean diagnosticoFisio;
    private boolean objetivosTratamento;
    private boolean planoTratamento;

    public Secoes(@NonNull String id) {
        this.id = id;
        this.diagnosticoMedico = false;
        this.queixaPrincipal = false;
        this.historiaDoencaAtual = false;
        this.dor = false;
        this.antecedentesPessoais = false;
        this.doencasAssociadas = false;
        this.medicamentosEmUso = false;
        this.historiaFamiliar = false;
        this.historiaOcupacional = false;
        this.historiaSocial = false;
        this.examesComplementares = false;
        this.sinaisVitais = false;
        this.inspecao = false;
        this.palpacao = false;
        this.amplitudeMovimento = false;
        this.perimetria = false;
        this.forcaMuscular = false;
        this.sensibilidade = false;
        this.testesEspeciais = false;
        this.observacoes = false;
        this.diagnosticoFisio = false;
        this.objetivosTratamento = false;
        this.planoTratamento = false;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public boolean isDiagnosticoMedico() {
        return diagnosticoMedico;
    }

    public void setDiagnosticoMedico(boolean diagnosticoMedico) {
        this.diagnosticoMedico = diagnosticoMedico;
    }

    public boolean isQueixaPrincipal() {
        return queixaPrincipal;
    }

    public void setQueixaPrincipal(boolean queixaPrincipal) {
        this.queixaPrincipal = queixaPrincipal;
    }

    public boolean isHistoriaDoencaAtual() {
        return historiaDoencaAtual;
    }

    public void setHistoriaDoencaAtual(boolean historiaDoencaAtual) {
        this.historiaDoencaAtual = historiaDoencaAtual;
    }

    public boolean isDor() {
        return dor;
    }

    public void setDor(boolean dor) {
        this.dor = dor;
    }

    public boolean isTratamentoAnterior() {
        return tratamentoAnterior;
    }

    public void setTratamentoAnterior(boolean tratamentoAnterior) {
        this.tratamentoAnterior = tratamentoAnterior;
    }

    public boolean isAfastamentoDaFuncao() {
        return afastamentoDaFuncao;
    }

    public void setAfastamentoDaFuncao(boolean afastamentoDaFuncao) {
        this.afastamentoDaFuncao = afastamentoDaFuncao;
    }

    public boolean isAntecedentesPessoais() {
        return antecedentesPessoais;
    }

    public void setAntecedentesPessoais(boolean antecedentesPessoais) {
        this.antecedentesPessoais = antecedentesPessoais;
    }

    public boolean isDoencasAssociadas() {
        return doencasAssociadas;
    }

    public void setDoencasAssociadas(boolean doencasAssociadas) {
        this.doencasAssociadas = doencasAssociadas;
    }

    public boolean isMedicamentosEmUso() {
        return medicamentosEmUso;
    }

    public void setMedicamentosEmUso(boolean medicamentosEmUso) {
        this.medicamentosEmUso = medicamentosEmUso;
    }

    public boolean isHistoriaFamiliar() {
        return historiaFamiliar;
    }

    public void setHistoriaFamiliar(boolean historiaFamiliar) {
        this.historiaFamiliar = historiaFamiliar;
    }

    public boolean isHistoriaOcupacional() {
        return historiaOcupacional;
    }

    public void setHistoriaOcupacional(boolean historiaOcupacional) {
        this.historiaOcupacional = historiaOcupacional;
    }

    public boolean isHistoriaSocial() {
        return historiaSocial;
    }

    public void setHistoriaSocial(boolean historiaSocial) {
        this.historiaSocial = historiaSocial;
    }

    public boolean isExamesComplementares() {
        return examesComplementares;
    }

    public void setExamesComplementares(boolean examesComplementares) {
        this.examesComplementares = examesComplementares;
    }

    public boolean isSinaisVitais() {
        return sinaisVitais;
    }

    public void setSinaisVitais(boolean sinaisVitais) {
        this.sinaisVitais = sinaisVitais;
    }

    public boolean isInspecao() {
        return inspecao;
    }

    public void setInspecao(boolean inspecao) {
        this.inspecao = inspecao;
    }

    public boolean isPalpacao() {
        return palpacao;
    }

    public void setPalpacao(boolean palpacao) {
        this.palpacao = palpacao;
    }

    public boolean isAmplitudeMovimento() {
        return amplitudeMovimento;
    }

    public void setAmplitudeMovimento(boolean amplitudeMovimento) {
        this.amplitudeMovimento = amplitudeMovimento;
    }

    public boolean isPerimetria() {
        return perimetria;
    }

    public void setPerimetria(boolean perimetria) {
        this.perimetria = perimetria;
    }

    public boolean isForcaMuscular() {
        return forcaMuscular;
    }

    public void setForcaMuscular(boolean forcaMuscular) {
        this.forcaMuscular = forcaMuscular;
    }

    public boolean isSensibilidade() {
        return sensibilidade;
    }

    public void setSensibilidade(boolean sensibilidade) {
        this.sensibilidade = sensibilidade;
    }

    public boolean isTestesEspeciais() {
        return testesEspeciais;
    }

    public void setTestesEspeciais(boolean testesEspeciais) {
        this.testesEspeciais = testesEspeciais;
    }

    public boolean isObservacoes() {
        return observacoes;
    }

    public void setObservacoes(boolean observacoes) {
        this.observacoes = observacoes;
    }

    public boolean isDiagnosticoFisio() {
        return diagnosticoFisio;
    }

    public void setDiagnosticoFisio(boolean diagnosticoFisio) {
        this.diagnosticoFisio = diagnosticoFisio;
    }

    public boolean isObjetivosTratamento() {
        return objetivosTratamento;
    }

    public void setObjetivosTratamento(boolean objetivosTratamento) {
        this.objetivosTratamento = objetivosTratamento;
    }

    public boolean isPlanoTratamento() {
        return planoTratamento;
    }

    public void setPlanoTratamento(boolean planoTratamento) {
        this.planoTratamento = planoTratamento;
    }
}
