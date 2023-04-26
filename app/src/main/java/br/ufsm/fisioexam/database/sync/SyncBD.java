package br.ufsm.fisioexam.database.sync;

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
import br.ufsm.fisioexam.model.Ombro;
import br.ufsm.fisioexam.model.Paciente;

public class SyncBD {

    private final FisioExamDatabase fisioExamDatabase;
    private final DatabaseReference databaseReference;

    public SyncBD(FisioExamDatabase fisioExamDatabase, DatabaseReference databaseReference) {
        this.fisioExamDatabase = fisioExamDatabase;
        this.databaseReference = databaseReference;
    }

    public void syncNow(DatabaseReference databaseReference) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                getFromFirebase(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // lógica de tratamento de erros
            }
        });
    }

    private void getFromFirebase(@NonNull DataSnapshot dataSnapshot) {
        syncPacientes();
        syncExames();
        syncOmbros();
        List<Exame> exames = new ArrayList<>();
        List<Paciente> pacientes = new ArrayList<>();
        List<Ombro> ombros = new ArrayList<>();


        for (DataSnapshot snapshot : dataSnapshot.child("exames").getChildren()) {
            Exame exame = snapshot.getValue(Exame.class);
            exames.add(exame);
        }

        for (DataSnapshot snapshot : dataSnapshot.child("pacientes").getChildren()) {
            Paciente paciente = snapshot.getValue(Paciente.class);
            pacientes.add(paciente);
        }

        for (DataSnapshot snapshot : dataSnapshot.child("ombros").getChildren()) {
            Ombro ombro = snapshot.getValue(Ombro.class);
            ombros.add(ombro);
        }

        updateTables(exames, pacientes, ombros);

    }

    private void updateTables(List<Exame> exames, List<Paciente> pacientes, List<Ombro> ombros) {


        fisioExamDatabase.getRoomOmbroDAO().deleteAllOmbros();
        fisioExamDatabase.getRoomExameDAO().deleteAllExames();
        fisioExamDatabase.getRoomPacienteDAO().deleteAllPacientes();

        fisioExamDatabase.getRoomPacienteDAO().insertAllPacientes(pacientes);
        fisioExamDatabase.getRoomExameDAO().insertAllExames(exames);
        fisioExamDatabase.getRoomOmbroDAO().insertAllOmbros(ombros);
    }

    public void syncExames() {
        List<Exame> exames = fisioExamDatabase.getRoomExameDAO().getAllExames();

        for (Exame exame : exames) {
            databaseReference.child("exames").child(exame.getId()).setValue(exame);
        }

    }

    public void syncOmbros() {
        List<Ombro> ombros = fisioExamDatabase.getRoomOmbroDAO().getAllOmbros();

        for (Ombro ombro : ombros) {
            databaseReference.child("ombros").child(ombro.getId()).setValue(ombro);
        }
    }

    public void syncCotovelos() {
        List<Cotovelo> cotovelos = fisioExamDatabase.getRoomCotoveloDAO().getAllCotovelos();

        for (Cotovelo cotovelo : cotovelos) {
            databaseReference.child("exames").child(cotovelo.getId()).setValue(cotovelo);
        }

    }

    public void syncPacientes() {
        List<Paciente> pacientes = fisioExamDatabase.getRoomPacienteDAO().getAllPacientes();

        for (Paciente paciente : pacientes) {
            databaseReference.child("pacientes").child(paciente.getId()).setValue(paciente);
        }
    }

}
