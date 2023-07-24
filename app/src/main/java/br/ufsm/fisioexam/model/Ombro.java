package br.ufsm.fisioexam.model;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(foreignKeys = {@ForeignKey(entity = Exame.class, parentColumns = "id", childColumns = "exame", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)}, indices = {@Index(name = "idx_ombro_exame", value = {"exame"})})
public class Ombro {
    @PrimaryKey()
    private @NonNull
    String id;
    //chave estrangeira
    @NonNull
    private String exame;


    //Amplitude de movimento
    private String flexaoDir;

    private String flexaoEsq;

    private String extensaoDir;

    private String extensaoEsq;

    private String abducaoDir;

    private String abducaoEsq;

    private String aducaoHorDir;

    private String aducaoHorEsq;

    private String rotacaoMedDir;

    private String rotacaoMedEsq;

    private String rotacaoLatDir;

    private String rotacaoLatEsq;

    private Boolean alteracaoRitmoEscapuloUmeral;
    private Boolean grauI;
    private Boolean grauII;
    private Boolean grauIII;


    //Perimetria
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
    private String trapezioSuperiorLevantadorDaEscapulaDir;
    private String trapezioSuperiorLevantadorDaEscapulaEsq;
    private String trapezioMedioDir;
    private String trapezioMedioEsq;
    private String trapezioInfDir;
    private String trapezioInfEsq;
    private String romboidesMaiorMenorDir;
    private String romboidesMaiorMenorEsq;
    private String serratilAnteriorDir;
    private String serratilAnteriorEsq;
    private String deltoideAnteriorDir;
    private String deltoideAnteriorEsq;
    private String cobraquialDir;
    private String cobraquialEsq;
    private String grandeDorsalDir;
    private String grandeDorsalEsq;
    private String redondoMaiorDir;
    private String redondoMaiorEsq;
    private String supraespinhalDir;
    private String supraespinhalEsq;
    private String deltoideMedioDir;
    private String deltoideMedioEsq;
    private String deltoidePosteriorDir;
    private String deltoidePosteriorEsq;
    private String peitoralMaiorDir;
    private String peitoralMaiorEsq;
    private String subescapularDir;
    private String subescapularEsq;
    private String infraespinhalRedondoMenorDir;
    private String infraespinhalRedondoMenorEsq;

    //Sensibilidade
    private String sensibilidadeTactil;
    private String sensibilidadeTermica;
    private String sensibilidadeDolorosa;
    private String sensibilidadeLocalAvaliado;

    //Testes Especiais
    private boolean jobeDir;
    private boolean jobeEsq;
    private boolean patteDir;
    private boolean patteEsq;
    private boolean gerberLiftOffDir;
    private boolean gerberLiftOffEsq;
    private boolean neerDir;
    private boolean neerEsq;
    private boolean hawkinsDir;
    private boolean hawkinsEsq;
    private boolean palmUpSpeedDir;
    private boolean palmUpSpeedEsq;
    private boolean yergasonDir;
    private boolean yergasonEsq;
    private boolean apreensaoAnteriorDir;
    private boolean apreensaoAnteriorEsq;
    private boolean sinalSulcoDir;
    private boolean sinalSulcoEsq;
    //Escalas Utilizadas
    //DASH
    private Long dashData;
    private String dashPontuacao;
    private String dashResultados;

    //ASES
    private Long asesData;
    private String asesPontuacao;
    private String asesResultados;

    public Ombro(@NonNull String exame) {
        this.exame = exame;
        id = UUID.randomUUID().toString();
    }


    @Ignore
    public Ombro() {
        exame = UUID.randomUUID().toString();
        id = UUID.randomUUID().toString();
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    @NonNull
    public String getExame() {
        return exame;
    }

    public void setExame(@NonNull String exame) {
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

    public String getAbducaoDir() {
        return abducaoDir;
    }

    public void setAbducaoDir(String abducaoDir) {
        this.abducaoDir = abducaoDir;
    }

    public String getAbducaoEsq() {
        return abducaoEsq;
    }

    public void setAbducaoEsq(String abducaoEsq) {
        this.abducaoEsq = abducaoEsq;
    }

    public String getAducaoHorDir() {
        return aducaoHorDir;
    }

    public void setAducaoHorDir(String aducaoHorDir) {
        this.aducaoHorDir = aducaoHorDir;
    }

    public String getAducaoHorEsq() {
        return aducaoHorEsq;
    }

    public void setAducaoHorEsq(String aducaoHorEsq) {
        this.aducaoHorEsq = aducaoHorEsq;
    }

    public String getRotacaoMedDir() {
        return rotacaoMedDir;
    }

    public void setRotacaoMedDir(String rotacaoMedDir) {
        this.rotacaoMedDir = rotacaoMedDir;
    }

    public String getRotacaoMedEsq() {
        return rotacaoMedEsq;
    }

    public void setRotacaoMedEsq(String rotacaoMedEsq) {
        this.rotacaoMedEsq = rotacaoMedEsq;
    }

    public String getRotacaoLatDir() {
        return rotacaoLatDir;
    }

    public void setRotacaoLatDir(String rotacaoLatDir) {
        this.rotacaoLatDir = rotacaoLatDir;
    }

    public String getRotacaoLatEsq() {
        return rotacaoLatEsq;
    }

    public void setRotacaoLatEsq(String rotacaoLatEsq) {
        this.rotacaoLatEsq = rotacaoLatEsq;
    }

    public Boolean getAlteracaoRitmoEscapuloUmeral() {
        return alteracaoRitmoEscapuloUmeral;
    }

    public void setAlteracaoRitmoEscapuloUmeral(Boolean alteracaoRitmoEscapuloUmeral) {
        this.alteracaoRitmoEscapuloUmeral = alteracaoRitmoEscapuloUmeral;
    }

    public Boolean getGrauI() {
        return grauI;
    }

    public void setGrauI(Boolean grauI) {
        this.grauI = grauI;
    }

    public Boolean getGrauII() {
        return grauII;
    }

    public void setGrauII(Boolean grauII) {
        this.grauII = grauII;
    }

    public Boolean getGrauIII() {
        return grauIII;
    }

    public void setGrauIII(Boolean grauIII) {
        this.grauIII = grauIII;
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

    public String getTrapezioSuperiorLevantadorDaEscapulaDir() {
        return trapezioSuperiorLevantadorDaEscapulaDir;
    }

    public void setTrapezioSuperiorLevantadorDaEscapulaDir(String trapezioSuperiorLevantadorDaEscapulaDir) {
        this.trapezioSuperiorLevantadorDaEscapulaDir = trapezioSuperiorLevantadorDaEscapulaDir;
    }

    public String getTrapezioSuperiorLevantadorDaEscapulaEsq() {
        return trapezioSuperiorLevantadorDaEscapulaEsq;
    }

    public void setTrapezioSuperiorLevantadorDaEscapulaEsq(String trapezioSuperiorLevantadorDaEscapulaEsq) {
        this.trapezioSuperiorLevantadorDaEscapulaEsq = trapezioSuperiorLevantadorDaEscapulaEsq;
    }

    public String getTrapezioMedioDir() {
        return trapezioMedioDir;
    }

    public void setTrapezioMedioDir(String trapezioMedioDir) {
        this.trapezioMedioDir = trapezioMedioDir;
    }

    public String getTrapezioMedioEsq() {
        return trapezioMedioEsq;
    }

    public void setTrapezioMedioEsq(String trapezioMedioEsq) {
        this.trapezioMedioEsq = trapezioMedioEsq;
    }

    public String getTrapezioInfDir() {
        return trapezioInfDir;
    }

    public void setTrapezioInfDir(String trapezioInfDir) {
        this.trapezioInfDir = trapezioInfDir;
    }

    public String getTrapezioInfEsq() {
        return trapezioInfEsq;
    }

    public void setTrapezioInfEsq(String trapezioInfEsq) {
        this.trapezioInfEsq = trapezioInfEsq;
    }

    public String getRomboidesMaiorMenorDir() {
        return romboidesMaiorMenorDir;
    }

    public void setRomboidesMaiorMenorDir(String romboidesMaiorMenorDir) {
        this.romboidesMaiorMenorDir = romboidesMaiorMenorDir;
    }

    public String getRomboidesMaiorMenorEsq() {
        return romboidesMaiorMenorEsq;
    }

    public void setRomboidesMaiorMenorEsq(String romboidesMaiorMenorEsq) {
        this.romboidesMaiorMenorEsq = romboidesMaiorMenorEsq;
    }

    public String getSerratilAnteriorDir() {
        return serratilAnteriorDir;
    }

    public void setSerratilAnteriorDir(String serratilAnteriorDir) {
        this.serratilAnteriorDir = serratilAnteriorDir;
    }

    public String getSerratilAnteriorEsq() {
        return serratilAnteriorEsq;
    }

    public void setSerratilAnteriorEsq(String serratilAnteriorEsq) {
        this.serratilAnteriorEsq = serratilAnteriorEsq;
    }

    public String getDeltoideAnteriorDir() {
        return deltoideAnteriorDir;
    }

    public void setDeltoideAnteriorDir(String deltoideAnteriorDir) {
        this.deltoideAnteriorDir = deltoideAnteriorDir;
    }

    public String getDeltoideAnteriorEsq() {
        return deltoideAnteriorEsq;
    }

    public void setDeltoideAnteriorEsq(String deltoideAnteriorEsq) {
        this.deltoideAnteriorEsq = deltoideAnteriorEsq;
    }

    public String getCobraquialDir() {
        return cobraquialDir;
    }

    public void setCobraquialDir(String cobraquialDir) {
        this.cobraquialDir = cobraquialDir;
    }

    public String getCobraquialEsq() {
        return cobraquialEsq;
    }

    public void setCobraquialEsq(String cobraquialEsq) {
        this.cobraquialEsq = cobraquialEsq;
    }

    public String getGrandeDorsalDir() {
        return grandeDorsalDir;
    }

    public void setGrandeDorsalDir(String grandeDorsalDir) {
        this.grandeDorsalDir = grandeDorsalDir;
    }

    public String getGrandeDorsalEsq() {
        return grandeDorsalEsq;
    }

    public void setGrandeDorsalEsq(String grandeDorsalEsq) {
        this.grandeDorsalEsq = grandeDorsalEsq;
    }

    public String getRedondoMaiorDir() {
        return redondoMaiorDir;
    }

    public void setRedondoMaiorDir(String redondoMaiorDir) {
        this.redondoMaiorDir = redondoMaiorDir;
    }

    public String getRedondoMaiorEsq() {
        return redondoMaiorEsq;
    }

    public void setRedondoMaiorEsq(String redondoMaiorEsq) {
        this.redondoMaiorEsq = redondoMaiorEsq;
    }

    public String getSupraespinhalDir() {
        return supraespinhalDir;
    }

    public void setSupraespinhalDir(String supraespinhalDir) {
        this.supraespinhalDir = supraespinhalDir;
    }

    public String getSupraespinhalEsq() {
        return supraespinhalEsq;
    }

    public void setSupraespinhalEsq(String supraespinhalEsq) {
        this.supraespinhalEsq = supraespinhalEsq;
    }

    public String getDeltoideMedioDir() {
        return deltoideMedioDir;
    }

    public void setDeltoideMedioDir(String deltoideMedioDir) {
        this.deltoideMedioDir = deltoideMedioDir;
    }

    public String getDeltoideMedioEsq() {
        return deltoideMedioEsq;
    }

    public void setDeltoideMedioEsq(String deltoideMedioEsq) {
        this.deltoideMedioEsq = deltoideMedioEsq;
    }

    public String getDeltoidePosteriorDir() {
        return deltoidePosteriorDir;
    }

    public void setDeltoidePosteriorDir(String deltoidePosteriorDir) {
        this.deltoidePosteriorDir = deltoidePosteriorDir;
    }

    public String getDeltoidePosteriorEsq() {
        return deltoidePosteriorEsq;
    }

    public void setDeltoidePosteriorEsq(String deltoidePosteriorEsq) {
        this.deltoidePosteriorEsq = deltoidePosteriorEsq;
    }

    public String getPeitoralMaiorDir() {
        return peitoralMaiorDir;
    }

    public void setPeitoralMaiorDir(String peitoralMaiorDir) {
        this.peitoralMaiorDir = peitoralMaiorDir;
    }

    public String getPeitoralMaiorEsq() {
        return peitoralMaiorEsq;
    }

    public void setPeitoralMaiorEsq(String peitoralMaiorEsq) {
        this.peitoralMaiorEsq = peitoralMaiorEsq;
    }

    public String getSubescapularDir() {
        return subescapularDir;
    }

    public void setSubescapularDir(String subescapularDir) {
        this.subescapularDir = subescapularDir;
    }

    public String getSubescapularEsq() {
        return subescapularEsq;
    }

    public void setSubescapularEsq(String subescapularEsq) {
        this.subescapularEsq = subescapularEsq;
    }

    public String getInfraespinhalRedondoMenorDir() {
        return infraespinhalRedondoMenorDir;
    }

    public void setInfraespinhalRedondoMenorDir(String infraespinhalRedondoMenorDir) {
        this.infraespinhalRedondoMenorDir = infraespinhalRedondoMenorDir;
    }

    public String getInfraespinhalRedondoMenorEsq() {
        return infraespinhalRedondoMenorEsq;
    }

    public void setInfraespinhalRedondoMenorEsq(String infraespinhalRedondoMenorEsq) {
        this.infraespinhalRedondoMenorEsq = infraespinhalRedondoMenorEsq;
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

    public boolean isJobeDir() {
        return jobeDir;
    }

    public void setJobeDir(boolean jobeDir) {
        this.jobeDir = jobeDir;
    }

    public boolean isJobeEsq() {
        return jobeEsq;
    }

    public void setJobeEsq(boolean jobeEsq) {
        this.jobeEsq = jobeEsq;
    }

    public boolean isPatteDir() {
        return patteDir;
    }

    public void setPatteDir(boolean patteDir) {
        this.patteDir = patteDir;
    }

    public boolean isPatteEsq() {
        return patteEsq;
    }

    public void setPatteEsq(boolean patteEsq) {
        this.patteEsq = patteEsq;
    }

    public boolean isGerberLiftOffDir() {
        return gerberLiftOffDir;
    }

    public void setGerberLiftOffDir(boolean gerberLiftOffDir) {
        this.gerberLiftOffDir = gerberLiftOffDir;
    }

    public boolean isGerberLiftOffEsq() {
        return gerberLiftOffEsq;
    }

    public void setGerberLiftOffEsq(boolean gerberLiftOffEsq) {
        this.gerberLiftOffEsq = gerberLiftOffEsq;
    }

    public boolean isNeerDir() {
        return neerDir;
    }

    public void setNeerDir(boolean neerDir) {
        this.neerDir = neerDir;
    }

    public boolean isNeerEsq() {
        return neerEsq;
    }

    public void setNeerEsq(boolean neerEsq) {
        this.neerEsq = neerEsq;
    }

    public boolean isHawkinsDir() {
        return hawkinsDir;
    }

    public void setHawkinsDir(boolean hawkinsDir) {
        this.hawkinsDir = hawkinsDir;
    }

    public boolean isHawkinsEsq() {
        return hawkinsEsq;
    }

    public void setHawkinsEsq(boolean hawkinsEsq) {
        this.hawkinsEsq = hawkinsEsq;
    }

    public boolean isPalmUpSpeedDir() {
        return palmUpSpeedDir;
    }

    public void setPalmUpSpeedDir(boolean palmUpSpeedDir) {
        this.palmUpSpeedDir = palmUpSpeedDir;
    }

    public boolean isPalmUpSpeedEsq() {
        return palmUpSpeedEsq;
    }

    public void setPalmUpSpeedEsq(boolean palmUpSpeedEsq) {
        this.palmUpSpeedEsq = palmUpSpeedEsq;
    }

    public boolean isYergasonDir() {
        return yergasonDir;
    }

    public void setYergasonDir(boolean yergasonDir) {
        this.yergasonDir = yergasonDir;
    }

    public boolean isYergasonEsq() {
        return yergasonEsq;
    }

    public void setYergasonEsq(boolean yergasonEsq) {
        this.yergasonEsq = yergasonEsq;
    }

    public boolean isApreensaoAnteriorDir() {
        return apreensaoAnteriorDir;
    }

    public void setApreensaoAnteriorDir(boolean apreensaoAnteriorDir) {
        this.apreensaoAnteriorDir = apreensaoAnteriorDir;
    }

    public boolean isApreensaoAnteriorEsq() {
        return apreensaoAnteriorEsq;
    }

    public void setApreensaoAnteriorEsq(boolean apreensaoAnteriorEsq) {
        this.apreensaoAnteriorEsq = apreensaoAnteriorEsq;
    }

    public boolean isSinalSulcoDir() {
        return sinalSulcoDir;
    }

    public void setSinalSulcoDir(boolean sinalSulcoDir) {
        this.sinalSulcoDir = sinalSulcoDir;
    }

    public boolean isSinalSulcoEsq() {
        return sinalSulcoEsq;
    }

    public void setSinalSulcoEsq(boolean sinalSulcoEsq) {
        this.sinalSulcoEsq = sinalSulcoEsq;
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

    public Long getAsesData() {
        return asesData;
    }

    public void setAsesData(Long asesData) {
        this.asesData = asesData;
    }

    public String getAsesPontuacao() {
        return asesPontuacao;
    }

    public void setAsesPontuacao(String asesPontuacao) {
        this.asesPontuacao = asesPontuacao;
    }

    public String getAsesResultados() {
        return asesResultados;
    }

    public void setAsesResultados(String asesResultados) {
        this.asesResultados = asesResultados;
    }
}
