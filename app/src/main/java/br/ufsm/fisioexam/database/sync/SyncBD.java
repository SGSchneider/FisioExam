package br.ufsm.fisioexam.database.sync;

import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_EXAME;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_PACIENTE;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_TIPO_COTOVELO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_TIPO_OMBRO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_TIPO_PUNHO;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.ufsm.fisioexam.database.FisioExamDatabase;
import br.ufsm.fisioexam.model.Cotovelo;
import br.ufsm.fisioexam.model.Exame;
import br.ufsm.fisioexam.model.Exclusoes;
import br.ufsm.fisioexam.model.Ombro;
import br.ufsm.fisioexam.model.Paciente;
import br.ufsm.fisioexam.model.Punho;

public class SyncBD {

    private final FisioExamDatabase fisioExamDatabase;
    private final DatabaseReference databaseReference;

    public SyncBD(FisioExamDatabase fisioExamDatabase, DatabaseReference databaseReference) {
        this.fisioExamDatabase = fisioExamDatabase;
        this.databaseReference = databaseReference;
    }

    public void syncNow(DatabaseReference databaseReference) {
        syncExclusoes();
        syncPacientes();
        syncExames();
        syncOmbros();
        syncCotovelos();
        syncPunhos();
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                getFromFirebase(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // lógica de tratamento de erros
            }
        };

        databaseReference.addValueEventListener(eventListener);
        databaseReference.removeEventListener(eventListener);
    }

    private void getFromFirebase(@NonNull DataSnapshot dataSnapshot) {
        updateTables(dataSnapshot);
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

    private void updateTables(@NonNull DataSnapshot dataSnapshot) {
        int count = 0;
        List<Boolean> resultadoSnapshot= new ArrayList<>();

        count += dataSnapshot.child(CHAVE_EXAME).getChildrenCount();
        count += dataSnapshot.child(CHAVE_PACIENTE).getChildrenCount();
        count += dataSnapshot.child(CHAVE_TIPO_OMBRO).getChildrenCount();
        count += dataSnapshot.child(CHAVE_TIPO_COTOVELO).getChildrenCount();
        count += dataSnapshot.child(CHAVE_TIPO_PUNHO).getChildrenCount();


        List<Exame> exames = new ArrayList<>();
        List<Paciente> pacientes = new ArrayList<>();
        List<Ombro> ombros = new ArrayList<>();
        List<Cotovelo> cotovelos = new ArrayList<>();
        List<Punho> punhos = new ArrayList<>();

        for (DataSnapshot snapshot : dataSnapshot.child(CHAVE_EXAME).getChildren()) {
            Exame exame = snapshot.getValue(Exame.class);
            exames.add(exame);
            resultadoSnapshot.add(true);
        }

        for (DataSnapshot snapshot : dataSnapshot.child(CHAVE_PACIENTE).getChildren()) {
            Paciente paciente = snapshot.getValue(Paciente.class);
            pacientes.add(paciente);
            resultadoSnapshot.add(true);
        }

        for (DataSnapshot snapshot : dataSnapshot.child(CHAVE_TIPO_OMBRO).getChildren()) {
            Ombro ombro = snapshot.getValue(Ombro.class);
            ombros.add(ombro);
            resultadoSnapshot.add(true);
        }

        for (DataSnapshot snapshot : dataSnapshot.child(CHAVE_TIPO_COTOVELO).getChildren()) {
            Cotovelo cotovelo = snapshot.getValue(Cotovelo.class);
            cotovelos.add(cotovelo);
            resultadoSnapshot.add(true);
        }

        for (DataSnapshot snapshot : dataSnapshot.child(CHAVE_TIPO_PUNHO).getChildren()) {
            Punho punho = snapshot.getValue(Punho.class);
            punhos.add(punho);
            resultadoSnapshot.add(true);
        }

        if(resultadoSnapshot.size() == count){
            updateTables(exames, pacientes, ombros, cotovelos, punhos);
        }
    }

    private void updateTables(List<Exame> exames, List<Paciente> pacientes, List<Ombro> ombros, List<Cotovelo> cotovelos, List<Punho> punhos) {
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

}
