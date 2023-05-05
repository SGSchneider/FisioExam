package br.ufsm.fisioexam.database.sync;

import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_EXAME;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_PACIENTE;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_SECAO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_TIPO_COTOVELO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_TIPO_OMBRO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_TIPO_PUNHO;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

import br.ufsm.fisioexam.database.FisioExamDatabase;
import br.ufsm.fisioexam.model.Cotovelo;
import br.ufsm.fisioexam.model.Exame;
import br.ufsm.fisioexam.model.Exclusoes;
import br.ufsm.fisioexam.model.Ombro;
import br.ufsm.fisioexam.model.Paciente;
import br.ufsm.fisioexam.model.Punho;
import br.ufsm.fisioexam.model.Secoes;

public class SyncBD {

    private final FisioExamDatabase fisioExamDatabase;
    private final DatabaseReference databaseReference;

    private List<Exame> exames;
    private List<Paciente> pacientes;
    private List<Ombro> ombros;
    private List<Cotovelo> cotovelos;
    private List<Punho> punhos;

    private List<Secoes> secoes;

    public SyncBD(FisioExamDatabase fisioExamDatabase, DatabaseReference databaseReference) {
        this.fisioExamDatabase = fisioExamDatabase;
        this.databaseReference = databaseReference;
    }

    public void syncNow() {
        syncExclusoes();
        syncPacientes();
        syncExames();
        syncSecoes();
        syncOmbros();
        syncCotovelos();
        syncPunhos();
        getFromFirebase();
    }

    private void getFromFirebase() {
        final DataSnapshot[] dataSnapshot = new DataSnapshot[1];
        databaseReference.get().addOnCompleteListener(task -> {
            dataSnapshot[0] = task.getResult();
            updateTables(dataSnapshot[0]);
        });
    }


    private void syncExclusoes() {
        if(!fisioExamDatabase.getRoomExclusoesDAO().getExclusoes().isEmpty()){
            List<Exclusoes> exclusoes = fisioExamDatabase.getRoomExclusoesDAO().getExclusoes();
            for(Exclusoes exclusao : exclusoes){
                databaseReference.child(exclusao.getTipo()).child(exclusao.getId()).removeValue();
                fisioExamDatabase.getRoomExclusoesDAO().remove(exclusao);
            }
        }
    }

    private void updateTables(DataSnapshot dataSnapshot) {

        exames = new ArrayList<>();
        pacientes = new ArrayList<>();
        ombros = new ArrayList<>();
        cotovelos = new ArrayList<>();
        punhos = new ArrayList<>();
        secoes = new ArrayList<>();

        for (DataSnapshot snapshot : dataSnapshot.child(CHAVE_EXAME).getChildren()) {
            Exame exame = snapshot.getValue(Exame.class);
            exames.add(exame);
        }

        for (DataSnapshot snapshot : dataSnapshot.child(CHAVE_PACIENTE).getChildren()) {
            Paciente paciente = snapshot.getValue(Paciente.class);
            pacientes.add(paciente);
        }

        for (DataSnapshot snapshot : dataSnapshot.child(CHAVE_TIPO_OMBRO).getChildren()) {
            Ombro ombro = snapshot.getValue(Ombro.class);
            ombros.add(ombro);
        }

        for (DataSnapshot snapshot : dataSnapshot.child(CHAVE_TIPO_COTOVELO).getChildren()) {
            Cotovelo cotovelo = snapshot.getValue(Cotovelo.class);
            cotovelos.add(cotovelo);
        }

        for (DataSnapshot snapshot : dataSnapshot.child(CHAVE_TIPO_PUNHO).getChildren()) {
            Punho punho = snapshot.getValue(Punho.class);
            punhos.add(punho);
        }

        for (DataSnapshot snapshot : dataSnapshot.child(CHAVE_SECAO).getChildren()){
            Secoes secao = snapshot.getValue(Secoes.class);
            secoes.add(secao);
        }

            updateTables();
    }

    private void updateTables() {


        fisioExamDatabase.getRoomSecoesDAO().deleteAllSecoes();
        fisioExamDatabase.getRoomPunhoDAO().deleteAllPunhos();
        fisioExamDatabase.getRoomCotoveloDAO().deleteAllCotovelos();
        fisioExamDatabase.getRoomOmbroDAO().deleteAllOmbros();
        fisioExamDatabase.getRoomExameDAO().deleteAllExames();
        fisioExamDatabase.getRoomPacienteDAO().deleteAllPacientes();


        fisioExamDatabase.getRoomPacienteDAO().insertAllPacientes(pacientes);
        fisioExamDatabase.getRoomExameDAO().insertAllExames(exames);
        fisioExamDatabase.getRoomOmbroDAO().insertAllOmbros(ombros);
        fisioExamDatabase.getRoomCotoveloDAO().insertAllCotovelos(cotovelos);
        fisioExamDatabase.getRoomPunhoDAO().insertAllPunhos(punhos);
        fisioExamDatabase.getRoomSecoesDAO().insertAllSecoes(secoes);
    }

    public void syncExames() {
        List<Exame> exames = fisioExamDatabase.getRoomExameDAO().getAllExames();

        for (Exame exame : exames) {
            databaseReference.child(CHAVE_EXAME).child(exame.getId()).setValue(exame);
        }

    }

    public void syncOmbros() {
        List<Ombro> ombros = fisioExamDatabase.getRoomOmbroDAO().getAllOmbros();

        for (Ombro ombro : ombros) {
            databaseReference.child(CHAVE_TIPO_OMBRO).child(ombro.getId()).setValue(ombro);
        }
    }

    public void syncCotovelos() {
        List<Cotovelo> cotovelos = fisioExamDatabase.getRoomCotoveloDAO().getAllCotovelos();

        for (Cotovelo cotovelo : cotovelos) {
            databaseReference.child(CHAVE_TIPO_COTOVELO).child(cotovelo.getId()).setValue(cotovelo);
        }

    }

    public void syncPacientes() {
        List<Paciente> pacientes = fisioExamDatabase.getRoomPacienteDAO().getAllPacientes();

        for (Paciente paciente : pacientes) {
            databaseReference.child(CHAVE_PACIENTE).child(paciente.getId()).setValue(paciente);
        }
    }

    public void syncPunhos() {
        List<Punho> punhos = fisioExamDatabase.getRoomPunhoDAO().getAllPunhos();

        for (Punho punho : punhos) {
            databaseReference.child(CHAVE_TIPO_PUNHO).child(punho.getId()).setValue(punho);
        }
    }

    public void syncSecoes() {
        List<Secoes> secoes = fisioExamDatabase.getRoomSecoesDAO().getAllSecoes();

        for(Secoes secao : secoes){
            databaseReference.child(CHAVE_SECAO).child(secao.getId()).setValue(secao);
        }
    }

}
