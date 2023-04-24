package br.ufsm.fisioexam.model;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.lang.String;
import java.util.UUID;

@Entity(foreignKeys = {@ForeignKey(entity = Exame.class,
        parentColumns = "id",
        childColumns = "exame",
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE)},
        indices = {@Index(name = "idx_ombro_exame",
                value = {"exame"})})
public class Ombro {
    @PrimaryKey()
    private @NonNull String id;
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
    private String abducaoDirAtivo;
    private String abducaoDirPassivo;
    private String abducaoEsqAtivo;
    private String abducaoEsqPassivo;
    private String aducaoHorDirAtivo;
    private String aducaoHorDirPassivo;
    private String aducaoHorEsqAtivo;
    private String aducaoHorEsqPassivo;
    private String rotacaoMedDirAtivo;
    private String rotacaoMedDirPassivo;
    private String rotacaoMedEsqAtivo;
    private String rotacaoMedEsqPassivo;
    private String rotacaoLatDirAtivo;
    private String rotacaoLatDirPassivo;
    private String rotacaoLatEsqAtivo;
    private String rotacaoLatEsqPassivo;
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
    //Integridade de Manguito Rotador
    private String jobe;
    private String patte;
    private String gerberLiffOff;
    //Irritativos
    private String neer;
    private String hawkins;
    //Porção Longa do Biceps Braquial
    private String palmUpSpeed;
    private String yergason;
    //Instabilidade
    private String apreensaoAnterior;
    private String sinalSulco;

    //Escalas Utilizadas
    //DASH
    private Long dashData;
    private String dashPontuacao;
    private String dashResultados;

    //ASES
    private Long asesData;
    private String asesPontuacao;
    private String asesResultados;

    public Ombro(String exame) {
        this.exame = exame;
        id = UUID.randomUUID().toString();
    }


    @Ignore
    public Ombro(){
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

    public String getAbducaoDirAtivo() {
        return abducaoDirAtivo;
    }

    public void setAbducaoDirAtivo(String abducaoDirAtivo) {
        this.abducaoDirAtivo = abducaoDirAtivo;
    }

    public String getAbducaoDirPassivo() {
        return abducaoDirPassivo;
    }

    public void setAbducaoDirPassivo(String abducaoDirPassivo) {
        this.abducaoDirPassivo = abducaoDirPassivo;
    }

    public String getAbducaoEsqAtivo() {
        return abducaoEsqAtivo;
    }

    public void setAbducaoEsqAtivo(String abducaoEsqAtivo) {
        this.abducaoEsqAtivo = abducaoEsqAtivo;
    }

    public String getAbducaoEsqPassivo() {
        return abducaoEsqPassivo;
    }

    public void setAbducaoEsqPassivo(String abducaoEsqPassivo) {
        this.abducaoEsqPassivo = abducaoEsqPassivo;
    }

    public String getAducaoHorDirAtivo() {
        return aducaoHorDirAtivo;
    }

    public void setAducaoHorDirAtivo(String aducaoHorDirAtivo) {
        this.aducaoHorDirAtivo = aducaoHorDirAtivo;
    }

    public String getAducaoHorDirPassivo() {
        return aducaoHorDirPassivo;
    }

    public void setAducaoHorDirPassivo(String aducaoHorDirPassivo) {
        this.aducaoHorDirPassivo = aducaoHorDirPassivo;
    }

    public String getAducaoHorEsqAtivo() {
        return aducaoHorEsqAtivo;
    }

    public void setAducaoHorEsqAtivo(String aducaoHorEsqAtivo) {
        this.aducaoHorEsqAtivo = aducaoHorEsqAtivo;
    }

    public String getAducaoHorEsqPassivo() {
        return aducaoHorEsqPassivo;
    }

    public void setAducaoHorEsqPassivo(String aducaoHorEsqPassivo) {
        this.aducaoHorEsqPassivo = aducaoHorEsqPassivo;
    }

    public String getRotacaoMedDirAtivo() {
        return rotacaoMedDirAtivo;
    }

    public void setRotacaoMedDirAtivo(String rotacaoMedDirAtivo) {
        this.rotacaoMedDirAtivo = rotacaoMedDirAtivo;
    }

    public String getRotacaoMedDirPassivo() {
        return rotacaoMedDirPassivo;
    }

    public void setRotacaoMedDirPassivo(String rotacaoMedDirPassivo) {
        this.rotacaoMedDirPassivo = rotacaoMedDirPassivo;
    }

    public String getRotacaoMedEsqAtivo() {
        return rotacaoMedEsqAtivo;
    }

    public void setRotacaoMedEsqAtivo(String rotacaoMedEsqAtivo) {
        this.rotacaoMedEsqAtivo = rotacaoMedEsqAtivo;
    }

    public String getRotacaoMedEsqPassivo() {
        return rotacaoMedEsqPassivo;
    }

    public void setRotacaoMedEsqPassivo(String rotacaoMedEsqPassivo) {
        this.rotacaoMedEsqPassivo = rotacaoMedEsqPassivo;
    }

    public String getRotacaoLatDirAtivo() {
        return rotacaoLatDirAtivo;
    }

    public void setRotacaoLatDirAtivo(String rotacaoLatDirAtivo) {
        this.rotacaoLatDirAtivo = rotacaoLatDirAtivo;
    }

    public String getRotacaoLatDirPassivo() {
        return rotacaoLatDirPassivo;
    }

    public void setRotacaoLatDirPassivo(String rotacaoLatDirPassivo) {
        this.rotacaoLatDirPassivo = rotacaoLatDirPassivo;
    }

    public String getRotacaoLatEsqAtivo() {
        return rotacaoLatEsqAtivo;
    }

    public void setRotacaoLatEsqAtivo(String rotacaoLatEsqAtivo) {
        this.rotacaoLatEsqAtivo = rotacaoLatEsqAtivo;
    }

    public String getRotacaoLatEsqPassivo() {
        return rotacaoLatEsqPassivo;
    }

    public void setRotacaoLatEsqPassivo(String rotacaoLatEsqPassivo) {
        this.rotacaoLatEsqPassivo = rotacaoLatEsqPassivo;
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

    public String getJobe() {
        return jobe;
    }

    public void setJobe(String jobe) {
        this.jobe = jobe;
    }

    public String getPatte() {
        return patte;
    }

    public void setPatte(String patte) {
        this.patte = patte;
    }

    public String getGerberLiffOff() {
        return gerberLiffOff;
    }

    public void setGerberLiffOff(String gerberLiffOff) {
        this.gerberLiffOff = gerberLiffOff;
    }

    public String getNeer() {
        return neer;
    }

    public void setNeer(String neer) {
        this.neer = neer;
    }

    public String getHawkins() {
        return hawkins;
    }

    public void setHawkins(String hawkins) {
        this.hawkins = hawkins;
    }

    public String getPalmUpSpeed() {
        return palmUpSpeed;
    }

    public void setPalmUpSpeed(String palmUpSpeed) {
        this.palmUpSpeed = palmUpSpeed;
    }

    public String getYergason() {
        return yergason;
    }

    public void setYergason(String yergason) {
        this.yergason = yergason;
    }

    public String getApreensaoAnterior() {
        return apreensaoAnterior;
    }

    public void setApreensaoAnterior(String apreensaoAnterior) {
        this.apreensaoAnterior = apreensaoAnterior;
    }

    public String getSinalSulco() {
        return sinalSulco;
    }

    public void setSinalSulco(String sinalSulco) {
        this.sinalSulco = sinalSulco;
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
