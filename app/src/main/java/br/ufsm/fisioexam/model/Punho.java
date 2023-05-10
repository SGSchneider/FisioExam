package br.ufsm.fisioexam.model;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(foreignKeys = {@ForeignKey(entity = Exame.class, parentColumns = "id", childColumns = "exame", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)}, indices = {@Index(name = "idx_punho_exame", value = {"exame"})})
public class Punho {
    @PrimaryKey()
    private @NonNull
    String id;
    //chave estrangeira
    private String exame;


    //Amplitude de movimento
    private String flexaoDir;
    private String flexaoEsq;
    private String extensaoDir;
    private String extensaoEsq;
    private String desvioRadialDir;
    private String desvioRadialEsq;
    private String desvioUlnarDir;
    private String desvioUlnarEsq;
    


    //Perimetria
    private String perimetriaInfDir5;
    private String perimetriaInfDir10;
    private String perimetriaInfDir15;
    private String perimetriaInfEsq5;
    private String perimetriaInfEsq10;
    private String perimetriaInfEsq15;
    private String perimetriaEm8Dir;
    private String perimetriaEm8Esq;

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
    private Boolean testesEspeciaisPhalenDir;
    private Boolean testesEspeciaisPhalenEsq;
    private Boolean testesEspeciaisPhalenInvertidoDir;
    private Boolean testesEspeciaisPhalenInvertidoEsq;
    private Boolean testesEspeciaisTinelDir;
    private Boolean testesEspeciaisTinelEsq;
    private Boolean testesEspeciaisFinkelsteinDir;
    private Boolean testesEspeciaisFinkelsteinEsq;
    private Boolean testesEspeciaisTriadeDir;
    private Boolean testesEspeciaisTriadeEsq;

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

    public String getFlexaoDir() {
        return flexaoDir;
    }

    public void setFlexaoDir(String flexaoDir) {
        this.flexaoDir = flexaoDir;
    }

    public String getFlexaoEsq() {
        return flexaoEsq;
    }

    public void setFlexaoEsq(String flexaoEsq) {
        this.flexaoEsq = flexaoEsq;
    }

    public String getExtensaoDir() {
        return extensaoDir;
    }

    public void setExtensaoDir(String extensaoDir) {
        this.extensaoDir = extensaoDir;
    }

    public String getExtensaoEsq() {
        return extensaoEsq;
    }

    public void setExtensaoEsq(String extensaoEsq) {
        this.extensaoEsq = extensaoEsq;
    }

    public String getDesvioRadialDir() {
        return desvioRadialDir;
    }

    public void setDesvioRadialDir(String desvioRadialDir) {
        this.desvioRadialDir = desvioRadialDir;
    }

    public String getDesvioRadialEsq() {
        return desvioRadialEsq;
    }

    public void setDesvioRadialEsq(String desvioRadialEsq) {
        this.desvioRadialEsq = desvioRadialEsq;
    }

    public String getDesvioUlnarDir() {
        return desvioUlnarDir;
    }

    public void setDesvioUlnarDir(String desvioUlnarDir) {
        this.desvioUlnarDir = desvioUlnarDir;
    }

    public String getDesvioUlnarEsq() {
        return desvioUlnarEsq;
    }

    public void setDesvioUlnarEsq(String desvioUlnarEsq) {
        this.desvioUlnarEsq = desvioUlnarEsq;
    }

    public String getPerimetriaEm8Dir() {
        return perimetriaEm8Dir;
    }

    public void setPerimetriaEm8Dir(String perimetriaEm8Dir) {
        this.perimetriaEm8Dir = perimetriaEm8Dir;
    }

    public String getPerimetriaEm8Esq() {
        return perimetriaEm8Esq;
    }

    public void setPerimetriaEm8Esq(String perimetriaEm8Esq) {
        this.perimetriaEm8Esq = perimetriaEm8Esq;
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

    public Boolean getTestesEspeciaisPhalenDir() {
        return testesEspeciaisPhalenDir;
    }

    public void setTestesEspeciaisPhalenDir(Boolean testesEspeciaisPhalenDir) {
        this.testesEspeciaisPhalenDir = testesEspeciaisPhalenDir;
    }

    public Boolean getTestesEspeciaisPhalenEsq() {
        return testesEspeciaisPhalenEsq;
    }

    public void setTestesEspeciaisPhalenEsq(Boolean testesEspeciaisPhalenEsq) {
        this.testesEspeciaisPhalenEsq = testesEspeciaisPhalenEsq;
    }

    public Boolean getTestesEspeciaisPhalenInvertidoDir() {
        return testesEspeciaisPhalenInvertidoDir;
    }

    public void setTestesEspeciaisPhalenInvertidoDir(Boolean testesEspeciaisPhalenInvertidoDir) {
        this.testesEspeciaisPhalenInvertidoDir = testesEspeciaisPhalenInvertidoDir;
    }

    public Boolean getTestesEspeciaisPhalenInvertidoEsq() {
        return testesEspeciaisPhalenInvertidoEsq;
    }

    public void setTestesEspeciaisPhalenInvertidoEsq(Boolean testesEspeciaisPhalenInvertidoEsq) {
        this.testesEspeciaisPhalenInvertidoEsq = testesEspeciaisPhalenInvertidoEsq;
    }

    public Boolean getTestesEspeciaisTinelDir() {
        return testesEspeciaisTinelDir;
    }

    public void setTestesEspeciaisTinelDir(Boolean testesEspeciaisTinelDir) {
        this.testesEspeciaisTinelDir = testesEspeciaisTinelDir;
    }

    public Boolean getTestesEspeciaisTinelEsq() {
        return testesEspeciaisTinelEsq;
    }

    public void setTestesEspeciaisTinelEsq(Boolean testesEspeciaisTinelEsq) {
        this.testesEspeciaisTinelEsq = testesEspeciaisTinelEsq;
    }

    public Boolean getTestesEspeciaisFinkelsteinDir() {
        return testesEspeciaisFinkelsteinDir;
    }

    public void setTestesEspeciaisFinkelsteinDir(Boolean testesEspeciaisFinkelsteinDir) {
        this.testesEspeciaisFinkelsteinDir = testesEspeciaisFinkelsteinDir;
    }

    public Boolean getTestesEspeciaisFinkelsteinEsq() {
        return testesEspeciaisFinkelsteinEsq;
    }

    public void setTestesEspeciaisFinkelsteinEsq(Boolean testesEspeciaisFinkelsteinEsq) {
        this.testesEspeciaisFinkelsteinEsq = testesEspeciaisFinkelsteinEsq;
    }

    public Boolean getTestesEspeciaisTriadeDir() {
        return testesEspeciaisTriadeDir;
    }

    public void setTestesEspeciaisTriadeDir(Boolean testesEspeciaisTriadeDir) {
        this.testesEspeciaisTriadeDir = testesEspeciaisTriadeDir;
    }

    public Boolean getTestesEspeciaisTriadeEsq() {
        return testesEspeciaisTriadeEsq;
    }

    public void setTestesEspeciaisTriadeEsq(Boolean testesEspeciaisTriadeEsq) {
        this.testesEspeciaisTriadeEsq = testesEspeciaisTriadeEsq;
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
