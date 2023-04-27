package br.ufsm.fisioexam.model;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(foreignKeys = {@ForeignKey(entity = Exame.class, parentColumns = "id", childColumns = "exame", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)}, indices = {@Index(name = "idx_ombro_exame", value = {"exame"})})
public class Punho {
    @PrimaryKey()
    private @NonNull
    String id;
    //chave estrangeira
    private String exame;


    //Amplitude de movimento
    private String flexaoDirAtivo;
    private String flexaoDirPassivo;
    private String flexaoEsqAtivo;
    private String flexaoEsqPassivo;
    private String extensaoDirAtivo;
    private String extensaoDirPassivo;
    private String extensaoEsqAtivo;
    private String extensaoEsqPassivo;
    private String desvioRadialDirAtivo;
    private String desvioRadialDirPassivo;
    private String desvioRadialEsqAtivo;
    private String desvioRadialEsqPassivo;
    private String desvioUlnarDirAtivo;
    private String desvioUlnarDirPassivo;
    private String desvioUlnarEsqAtivo;
    private String desvioUlnarEsqPassivo;
    


    //Perimetria
    //TODO REVISAR O SUPERIOR (perimetria em 8) após resposta da prof.
    //TODO ATUALIZAR VERSÃO BD
    private String perimetriaSupDir5;
    private String perimetriaSupDir10;
    private String perimetriaSupDir15;
    private String perimetriaSupEsq5;
    private String perimetriaSupEsq10;
    private String perimetriaSupEsq15;
    private String perimetriaInfDir5;
    private String perimetriaInfDir10;
    private String perimetriaInfDir15;
    private String perimetriaInfEsq5;
    private String perimetriaInfEsq10;
    private String perimetriaInfEsq15;

    //Força Muscular
    private String forcaMuscularFlexorRadialDir;
    private String forcaMuscularFlexorRadialEsq;
    private String forcaMuscularPalmarLongoDir;
    private String forcaMuscularPalmarLongoEsq;
    private String forcaMuscularFlexorUlnarDir;
    private String forcaMuscularFlexorUlnarEsq;
    private String forcaMuscularDinamometroDir;
    private String forcaMuscularDinamometroEsq;
    private String forcaMuscularExtensorRadialLongoDir;
    private String forcaMuscularExtensorRadialLongoEsq;
    private String forcaMuscularExtensorRadialCurtoDir;
    private String forcaMuscularExtensorRadialCurtoEsq;
    private String forcaMuscularExtensorUlnarDoCarpoDir;
    private String forcaMuscularExtensorUlnarDoCarpoEsq;

    //Sensibilidade
    private String sensibilidadeTactil;
    private String sensibilidadeTermica;
    private String sensibilidadeDolorosa;
    private String sensibilidadeLocalAvaliado;

    //Testes Especiais
    private String testesEspeciaisPhalen;
    private String testesEspeciaisPhalenInvertido;
    private String testesEspeciaisTinel;
    private String testesEspeciaisFinkelstein;
    private String testesEspeciaisTriade;

    //Escalas Utilizadas
    //DASH
    private Long dashData;
    private String dashPontuacao;
    private String dashResultados;

    public Punho(String exame) {
        this.exame = exame;
        id = UUID.randomUUID().toString();
    }


    @Ignore
    public Punho() {
        id = UUID.randomUUID().toString();
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getExame() {
        return exame;
    }

    public void setExame(String exame) {
        this.exame = exame;
    }

    public String getFlexaoDirAtivo() {
        return flexaoDirAtivo;
    }

    public void setFlexaoDirAtivo(String flexaoDirAtivo) {
        this.flexaoDirAtivo = flexaoDirAtivo;
    }

    public String getFlexaoDirPassivo() {
        return flexaoDirPassivo;
    }

    public void setFlexaoDirPassivo(String flexaoDirPassivo) {
        this.flexaoDirPassivo = flexaoDirPassivo;
    }

    public String getFlexaoEsqAtivo() {
        return flexaoEsqAtivo;
    }

    public void setFlexaoEsqAtivo(String flexaoEsqAtivo) {
        this.flexaoEsqAtivo = flexaoEsqAtivo;
    }

    public String getFlexaoEsqPassivo() {
        return flexaoEsqPassivo;
    }

    public void setFlexaoEsqPassivo(String flexaoEsqPassivo) {
        this.flexaoEsqPassivo = flexaoEsqPassivo;
    }

    public String getExtensaoDirAtivo() {
        return extensaoDirAtivo;
    }

    public void setExtensaoDirAtivo(String extensaoDirAtivo) {
        this.extensaoDirAtivo = extensaoDirAtivo;
    }

    public String getExtensaoDirPassivo() {
        return extensaoDirPassivo;
    }

    public void setExtensaoDirPassivo(String extensaoDirPassivo) {
        this.extensaoDirPassivo = extensaoDirPassivo;
    }

    public String getExtensaoEsqAtivo() {
        return extensaoEsqAtivo;
    }

    public void setExtensaoEsqAtivo(String extensaoEsqAtivo) {
        this.extensaoEsqAtivo = extensaoEsqAtivo;
    }

    public String getExtensaoEsqPassivo() {
        return extensaoEsqPassivo;
    }

    public void setExtensaoEsqPassivo(String extensaoEsqPassivo) {
        this.extensaoEsqPassivo = extensaoEsqPassivo;
    }

    public String getDesvioRadialDirAtivo() {
        return desvioRadialDirAtivo;
    }

    public void setDesvioRadialDirAtivo(String desvioRadialDirAtivo) {
        this.desvioRadialDirAtivo = desvioRadialDirAtivo;
    }

    public String getDesvioRadialDirPassivo() {
        return desvioRadialDirPassivo;
    }

    public void setDesvioRadialDirPassivo(String desvioRadialDirPassivo) {
        this.desvioRadialDirPassivo = desvioRadialDirPassivo;
    }

    public String getDesvioRadialEsqAtivo() {
        return desvioRadialEsqAtivo;
    }

    public void setDesvioRadialEsqAtivo(String desvioRadialEsqAtivo) {
        this.desvioRadialEsqAtivo = desvioRadialEsqAtivo;
    }

    public String getDesvioRadialEsqPassivo() {
        return desvioRadialEsqPassivo;
    }

    public void setDesvioRadialEsqPassivo(String desvioRadialEsqPassivo) {
        this.desvioRadialEsqPassivo = desvioRadialEsqPassivo;
    }

    public String getDesvioUlnarDirAtivo() {
        return desvioUlnarDirAtivo;
    }

    public void setDesvioUlnarDirAtivo(String desvioUlnarDirAtivo) {
        this.desvioUlnarDirAtivo = desvioUlnarDirAtivo;
    }

    public String getDesvioUlnarDirPassivo() {
        return desvioUlnarDirPassivo;
    }

    public void setDesvioUlnarDirPassivo(String desvioUlnarDirPassivo) {
        this.desvioUlnarDirPassivo = desvioUlnarDirPassivo;
    }

    public String getDesvioUlnarEsqAtivo() {
        return desvioUlnarEsqAtivo;
    }

    public void setDesvioUlnarEsqAtivo(String desvioUlnarEsqAtivo) {
        this.desvioUlnarEsqAtivo = desvioUlnarEsqAtivo;
    }

    public String getDesvioUlnarEsqPassivo() {
        return desvioUlnarEsqPassivo;
    }

    public void setDesvioUlnarEsqPassivo(String desvioUlnarEsqPassivo) {
        this.desvioUlnarEsqPassivo = desvioUlnarEsqPassivo;
    }

    public String getPerimetriaSupDir5() {
        return perimetriaSupDir5;
    }

    public void setPerimetriaSupDir5(String perimetriaSupDir5) {
        this.perimetriaSupDir5 = perimetriaSupDir5;
    }

    public String getPerimetriaSupDir10() {
        return perimetriaSupDir10;
    }

    public void setPerimetriaSupDir10(String perimetriaSupDir10) {
        this.perimetriaSupDir10 = perimetriaSupDir10;
    }

    public String getPerimetriaSupDir15() {
        return perimetriaSupDir15;
    }

    public void setPerimetriaSupDir15(String perimetriaSupDir15) {
        this.perimetriaSupDir15 = perimetriaSupDir15;
    }

    public String getPerimetriaSupEsq5() {
        return perimetriaSupEsq5;
    }

    public void setPerimetriaSupEsq5(String perimetriaSupEsq5) {
        this.perimetriaSupEsq5 = perimetriaSupEsq5;
    }

    public String getPerimetriaSupEsq10() {
        return perimetriaSupEsq10;
    }

    public void setPerimetriaSupEsq10(String perimetriaSupEsq10) {
        this.perimetriaSupEsq10 = perimetriaSupEsq10;
    }

    public String getPerimetriaSupEsq15() {
        return perimetriaSupEsq15;
    }

    public void setPerimetriaSupEsq15(String perimetriaSupEsq15) {
        this.perimetriaSupEsq15 = perimetriaSupEsq15;
    }

    public String getPerimetriaInfDir5() {
        return perimetriaInfDir5;
    }

    public void setPerimetriaInfDir5(String perimetriaInfDir5) {
        this.perimetriaInfDir5 = perimetriaInfDir5;
    }

    public String getPerimetriaInfDir10() {
        return perimetriaInfDir10;
    }

    public void setPerimetriaInfDir10(String perimetriaInfDir10) {
        this.perimetriaInfDir10 = perimetriaInfDir10;
    }

    public String getPerimetriaInfDir15() {
        return perimetriaInfDir15;
    }

    public void setPerimetriaInfDir15(String perimetriaInfDir15) {
        this.perimetriaInfDir15 = perimetriaInfDir15;
    }

    public String getPerimetriaInfEsq5() {
        return perimetriaInfEsq5;
    }

    public void setPerimetriaInfEsq5(String perimetriaInfEsq5) {
        this.perimetriaInfEsq5 = perimetriaInfEsq5;
    }

    public String getPerimetriaInfEsq10() {
        return perimetriaInfEsq10;
    }

    public void setPerimetriaInfEsq10(String perimetriaInfEsq10) {
        this.perimetriaInfEsq10 = perimetriaInfEsq10;
    }

    public String getPerimetriaInfEsq15() {
        return perimetriaInfEsq15;
    }

    public void setPerimetriaInfEsq15(String perimetriaInfEsq15) {
        this.perimetriaInfEsq15 = perimetriaInfEsq15;
    }

    public String getForcaMuscularFlexorRadialDir() {
        return forcaMuscularFlexorRadialDir;
    }

    public void setForcaMuscularFlexorRadialDir(String forcaMuscularFlexorRadialDir) {
        this.forcaMuscularFlexorRadialDir = forcaMuscularFlexorRadialDir;
    }

    public String getForcaMuscularFlexorRadialEsq() {
        return forcaMuscularFlexorRadialEsq;
    }

    public void setForcaMuscularFlexorRadialEsq(String forcaMuscularFlexorRadialEsq) {
        this.forcaMuscularFlexorRadialEsq = forcaMuscularFlexorRadialEsq;
    }

    public String getForcaMuscularPalmarLongoDir() {
        return forcaMuscularPalmarLongoDir;
    }

    public void setForcaMuscularPalmarLongoDir(String forcaMuscularPalmarLongoDir) {
        this.forcaMuscularPalmarLongoDir = forcaMuscularPalmarLongoDir;
    }

    public String getForcaMuscularPalmarLongoEsq() {
        return forcaMuscularPalmarLongoEsq;
    }

    public void setForcaMuscularPalmarLongoEsq(String forcaMuscularPalmarLongoEsq) {
        this.forcaMuscularPalmarLongoEsq = forcaMuscularPalmarLongoEsq;
    }

    public String getForcaMuscularFlexorUlnarDir() {
        return forcaMuscularFlexorUlnarDir;
    }

    public void setForcaMuscularFlexorUlnarDir(String forcaMuscularFlexorUlnarDir) {
        this.forcaMuscularFlexorUlnarDir = forcaMuscularFlexorUlnarDir;
    }

    public String getForcaMuscularFlexorUlnarEsq() {
        return forcaMuscularFlexorUlnarEsq;
    }

    public void setForcaMuscularFlexorUlnarEsq(String forcaMuscularFlexorUlnarEsq) {
        this.forcaMuscularFlexorUlnarEsq = forcaMuscularFlexorUlnarEsq;
    }

    public String getForcaMuscularDinamometroDir() {
        return forcaMuscularDinamometroDir;
    }

    public void setForcaMuscularDinamometroDir(String forcaMuscularDinamometroDir) {
        this.forcaMuscularDinamometroDir = forcaMuscularDinamometroDir;
    }

    public String getForcaMuscularDinamometroEsq() {
        return forcaMuscularDinamometroEsq;
    }

    public void setForcaMuscularDinamometroEsq(String forcaMuscularDinamometroEsq) {
        this.forcaMuscularDinamometroEsq = forcaMuscularDinamometroEsq;
    }

    public String getForcaMuscularExtensorRadialLongoDir() {
        return forcaMuscularExtensorRadialLongoDir;
    }

    public void setForcaMuscularExtensorRadialLongoDir(String forcaMuscularExtensorRadialLongoDir) {
        this.forcaMuscularExtensorRadialLongoDir = forcaMuscularExtensorRadialLongoDir;
    }

    public String getForcaMuscularExtensorRadialLongoEsq() {
        return forcaMuscularExtensorRadialLongoEsq;
    }

    public void setForcaMuscularExtensorRadialLongoEsq(String forcaMuscularExtensorRadialLongoEsq) {
        this.forcaMuscularExtensorRadialLongoEsq = forcaMuscularExtensorRadialLongoEsq;
    }

    public String getForcaMuscularExtensorRadialCurtoDir() {
        return forcaMuscularExtensorRadialCurtoDir;
    }

    public void setForcaMuscularExtensorRadialCurtoDir(String forcaMuscularExtensorRadialCurtoDir) {
        this.forcaMuscularExtensorRadialCurtoDir = forcaMuscularExtensorRadialCurtoDir;
    }

    public String getForcaMuscularExtensorRadialCurtoEsq() {
        return forcaMuscularExtensorRadialCurtoEsq;
    }

    public void setForcaMuscularExtensorRadialCurtoEsq(String forcaMuscularExtensorRadialCurtoEsq) {
        this.forcaMuscularExtensorRadialCurtoEsq = forcaMuscularExtensorRadialCurtoEsq;
    }

    public String getForcaMuscularExtensorUlnarDoCarpoDir() {
        return forcaMuscularExtensorUlnarDoCarpoDir;
    }

    public void setForcaMuscularExtensorUlnarDoCarpoDir(String forcaMuscularExtensorUlnarDoCarpoDir) {
        this.forcaMuscularExtensorUlnarDoCarpoDir = forcaMuscularExtensorUlnarDoCarpoDir;
    }

    public String getForcaMuscularExtensorUlnarDoCarpoEsq() {
        return forcaMuscularExtensorUlnarDoCarpoEsq;
    }

    public void setForcaMuscularExtensorUlnarDoCarpoEsq(String forcaMuscularExtensorUlnarDoCarpoEsq) {
        this.forcaMuscularExtensorUlnarDoCarpoEsq = forcaMuscularExtensorUlnarDoCarpoEsq;
    }

    public String getSensibilidadeTactil() {
        return sensibilidadeTactil;
    }

    public void setSensibilidadeTactil(String sensibilidadeTactil) {
        this.sensibilidadeTactil = sensibilidadeTactil;
    }

    public String getSensibilidadeTermica() {
        return sensibilidadeTermica;
    }

    public void setSensibilidadeTermica(String sensibilidadeTermica) {
        this.sensibilidadeTermica = sensibilidadeTermica;
    }

    public String getSensibilidadeDolorosa() {
        return sensibilidadeDolorosa;
    }

    public void setSensibilidadeDolorosa(String sensibilidadeDolorosa) {
        this.sensibilidadeDolorosa = sensibilidadeDolorosa;
    }

    public String getSensibilidadeLocalAvaliado() {
        return sensibilidadeLocalAvaliado;
    }

    public void setSensibilidadeLocalAvaliado(String sensibilidadeLocalAvaliado) {
        this.sensibilidadeLocalAvaliado = sensibilidadeLocalAvaliado;
    }

    public String getTestesEspeciaisPhalen() {
        return testesEspeciaisPhalen;
    }

    public void setTestesEspeciaisPhalen(String testesEspeciaisPhalen) {
        this.testesEspeciaisPhalen = testesEspeciaisPhalen;
    }

    public String getTestesEspeciaisPhalenInvertido() {
        return testesEspeciaisPhalenInvertido;
    }

    public void setTestesEspeciaisPhalenInvertido(String testesEspeciaisPhalenInvertido) {
        this.testesEspeciaisPhalenInvertido = testesEspeciaisPhalenInvertido;
    }

    public String getTestesEspeciaisTinel() {
        return testesEspeciaisTinel;
    }

    public void setTestesEspeciaisTinel(String testesEspeciaisTinel) {
        this.testesEspeciaisTinel = testesEspeciaisTinel;
    }

    public String getTestesEspeciaisFinkelstein() {
        return testesEspeciaisFinkelstein;
    }

    public void setTestesEspeciaisFinkelstein(String testesEspeciaisFinkelstein) {
        this.testesEspeciaisFinkelstein = testesEspeciaisFinkelstein;
    }

    public String getTestesEspeciaisTriade() {
        return testesEspeciaisTriade;
    }

    public void setTestesEspeciaisTriade(String testesEspeciaisTriade) {
        this.testesEspeciaisTriade = testesEspeciaisTriade;
    }

    public Long getDashData() {
        return dashData;
    }

    public void setDashData(Long dashData) {
        this.dashData = dashData;
    }

    public String getDashPontuacao() {
        return dashPontuacao;
    }

    public void setDashPontuacao(String dashPontuacao) {
        this.dashPontuacao = dashPontuacao;
    }

    public String getDashResultados() {
        return dashResultados;
    }

    public void setDashResultados(String dashResultados) {
        this.dashResultados = dashResultados;
    }
}
