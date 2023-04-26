package br.ufsm.fisioexam.model;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(foreignKeys = {@ForeignKey(entity = Exame.class,
        parentColumns = "id",
        childColumns = "exame",
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE)}, indices = {@Index(name = "idx_cotovelo_exame", value = {"exame"})})
public class Cotovelo {

    @PrimaryKey()
    private @NonNull
    String id;

    // chave estrangeira
    private @NonNull
    String exame;

    //Amplitude de movimento
    private String flexaoDirAtivo;
    private String flexaoDirPassivo;
    private String flexaoEsqAtivo;
    private String flexaoEsqPassivo;
    private String extensaoDirAtivo;
    private String extensaoDirPassivo;
    private String extensaoEsqAtivo;
    private String extensaoEsqPassivo;
    private String supinacaoDirAtivo;
    private String supinacaoDirPassivo;
    private String supinacaoEsqAtivo;
    private String supinacaoEsqPassivo;
    private String pronacaoDirAtivo;
    private String pronacaoDirPassivo;
    private String pronacaoEsqAtivo;
    private String pronacaoEsqPassivo;
    private String anguloCarregamentoDirPassivo;
    private String anguloCarregamentoEsqPassivo;

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
    private String bicepsBraquialDir;
    private String bicepsBraquialEsq;
    private String braquialDir;
    private String braquialEsq;
    private String braquirradialDir;
    private String braquirradialEsq;
    private String tricepsBraquialEAnconeoDir;
    private String tricepsBraquialEAnconeoEsq;
    private String supinadorDir;
    private String supinadorEsq;
    private String pronadorQuadradoERedondoDir;
    private String pronadorQuadradoERedondoEsq;

    //Sensibilidade
    private String sensibilidadeTactil;
    private String sensibilidadeTermica;
    private String sensibilidadeDolorosa;
    private String sensibilidadeLocalAvaliado;

    //Testes Especiais
    //Epicondilite
    private String testeCozen;
    private String testeCotoveloGolfista;
    private String sinalTinel;

    //Instabilidade
    private String testeEsforcoVaro;
    private String testeEsforcoValgo;

    //Escalas Utilizadas
    //DASH
    private Long dashData;
    private String dashPontuacao;
    private String dashResultados;

    //ASES
    private Long asesData;
    private String asesPontuacao;
    private String asesResultados;

    public Cotovelo(@NonNull String exame) {
        this.exame = exame;
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

    public String getSupinacaoDirAtivo() {
        return supinacaoDirAtivo;
    }

    public void setSupinacaoDirAtivo(String supinacaoDirAtivo) {
        this.supinacaoDirAtivo = supinacaoDirAtivo;
    }

    public String getSupinacaoDirPassivo() {
        return supinacaoDirPassivo;
    }

    public void setSupinacaoDirPassivo(String supinacaoDirPassivo) {
        this.supinacaoDirPassivo = supinacaoDirPassivo;
    }

    public String getSupinacaoEsqAtivo() {
        return supinacaoEsqAtivo;
    }

    public void setSupinacaoEsqAtivo(String supinacaoEsqAtivo) {
        this.supinacaoEsqAtivo = supinacaoEsqAtivo;
    }

    public String getSupinacaoEsqPassivo() {
        return supinacaoEsqPassivo;
    }

    public void setSupinacaoEsqPassivo(String supinacaoEsqPassivo) {
        this.supinacaoEsqPassivo = supinacaoEsqPassivo;
    }

    public String getPronacaoDirAtivo() {
        return pronacaoDirAtivo;
    }

    public void setPronacaoDirAtivo(String pronacaoDirAtivo) {
        this.pronacaoDirAtivo = pronacaoDirAtivo;
    }

    public String getPronacaoDirPassivo() {
        return pronacaoDirPassivo;
    }

    public void setPronacaoDirPassivo(String pronacaoDirPassivo) {
        this.pronacaoDirPassivo = pronacaoDirPassivo;
    }

    public String getPronacaoEsqAtivo() {
        return pronacaoEsqAtivo;
    }

    public void setPronacaoEsqAtivo(String pronacaoEsqAtivo) {
        this.pronacaoEsqAtivo = pronacaoEsqAtivo;
    }

    public String getPronacaoEsqPassivo() {
        return pronacaoEsqPassivo;
    }

    public void setPronacaoEsqPassivo(String pronacaoEsqPassivo) {
        this.pronacaoEsqPassivo = pronacaoEsqPassivo;
    }


    public String getAnguloCarregamentoDirPassivo() {
        return anguloCarregamentoDirPassivo;
    }

    public void setAnguloCarregamentoDirPassivo(String anguloCarregamentoDirPassivo) {
        this.anguloCarregamentoDirPassivo = anguloCarregamentoDirPassivo;
    }


    public String getAnguloCarregamentoEsqPassivo() {
        return anguloCarregamentoEsqPassivo;
    }

    public void setAnguloCarregamentoEsqPassivo(String anguloCarregamentoEsqPassivo) {
        this.anguloCarregamentoEsqPassivo = anguloCarregamentoEsqPassivo;
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

    public String getBicepsBraquialDir() {
        return bicepsBraquialDir;
    }

    public void setBicepsBraquialDir(String bicepsBraquialDir) {
        this.bicepsBraquialDir = bicepsBraquialDir;
    }

    public String getBicepsBraquialEsq() {
        return bicepsBraquialEsq;
    }

    public void setBicepsBraquialEsq(String bicepsBraquialEsq) {
        this.bicepsBraquialEsq = bicepsBraquialEsq;
    }

    public String getBraquialDir() {
        return braquialDir;
    }

    public void setBraquialDir(String braquialDir) {
        this.braquialDir = braquialDir;
    }

    public String getBraquialEsq() {
        return braquialEsq;
    }

    public void setBraquialEsq(String braquialEsq) {
        this.braquialEsq = braquialEsq;
    }

    public String getBraquirradialDir() {
        return braquirradialDir;
    }

    public void setBraquirradialDir(String braquirradialDir) {
        this.braquirradialDir = braquirradialDir;
    }

    public String getBraquirradialEsq() {
        return braquirradialEsq;
    }

    public void setBraquirradialEsq(String braquirradialEsq) {
        this.braquirradialEsq = braquirradialEsq;
    }

    public String getTricepsBraquialEAnconeoDir() {
        return tricepsBraquialEAnconeoDir;
    }

    public void setTricepsBraquialEAnconeoDir(String tricepsBraquialEAnconeoDir) {
        this.tricepsBraquialEAnconeoDir = tricepsBraquialEAnconeoDir;
    }

    public String getTricepsBraquialEAnconeoEsq() {
        return tricepsBraquialEAnconeoEsq;
    }

    public void setTricepsBraquialEAnconeoEsq(String tricepsBraquialEAnconeoEsq) {
        this.tricepsBraquialEAnconeoEsq = tricepsBraquialEAnconeoEsq;
    }

    public String getSupinadorDir() {
        return supinadorDir;
    }

    public void setSupinadorDir(String supinadorDir) {
        this.supinadorDir = supinadorDir;
    }

    public String getSupinadorEsq() {
        return supinadorEsq;
    }

    public void setSupinadorEsq(String supinadorEsq) {
        this.supinadorEsq = supinadorEsq;
    }

    public String getPronadorQuadradoERedondoDir() {
        return pronadorQuadradoERedondoDir;
    }

    public void setPronadorQuadradoERedondoDir(String pronadorQuadradoERedondoDir) {
        this.pronadorQuadradoERedondoDir = pronadorQuadradoERedondoDir;
    }

    public String getPronadorQuadradoERedondoEsq() {
        return pronadorQuadradoERedondoEsq;
    }

    public void setPronadorQuadradoERedondoEsq(String pronadorQuadradoERedondoEsq) {
        this.pronadorQuadradoERedondoEsq = pronadorQuadradoERedondoEsq;
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

    public String getTesteCozen() {
        return testeCozen;
    }

    public void setTesteCozen(String testeCozen) {
        this.testeCozen = testeCozen;
    }

    public String getTesteCotoveloGolfista() {
        return testeCotoveloGolfista;
    }

    public void setTesteCotoveloGolfista(String testeCotoveloGolfista) {
        this.testeCotoveloGolfista = testeCotoveloGolfista;
    }

    public String getSinalTinel() {
        return sinalTinel;
    }

    public void setSinalTinel(String sinalTinel) {
        this.sinalTinel = sinalTinel;
    }

    public String getTesteEsforcoVaro() {
        return testeEsforcoVaro;
    }

    public void setTesteEsforcoVaro(String testeEsforcoVaro) {
        this.testeEsforcoVaro = testeEsforcoVaro;
    }

    public String getTesteEsforcoValgo() {
        return testeEsforcoValgo;
    }

    public void setTesteEsforcoValgo(String testeEsforcoValgo) {
        this.testeEsforcoValgo = testeEsforcoValgo;
    }

    public Long getDashData() {
        return dashData;
    }

    public void setDashData(Long dashData) {
        this.dashData = dashData;
    }

    public Long getAsesData() {
        return asesData;
    }

    public void setAsesData(Long asesData) {
        this.asesData = asesData;
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
