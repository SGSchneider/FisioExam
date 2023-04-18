package br.ufsm.fisioexam.database.sync;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

import br.ufsm.fisioexam.database.FisioExamDatabase;
import br.ufsm.fisioexam.model.Exame;
import br.ufsm.fisioexam.model.Ombro;
import br.ufsm.fisioexam.model.Paciente;

public class SyncBD {

    private FisioExamDatabase fisioExamDatabase;
    private DatabaseReference databaseReference;

    public SyncBD(FisioExamDatabase fisioExamDatabase, DatabaseReference databaseReference) {
        this.fisioExamDatabase = fisioExamDatabase;
        this.databaseReference = databaseReference;
    }

    public void syncExames() {
        List<Exame> exames = fisioExamDatabase.getRoomExameDAO().getAllExames();
        List<Paciente> pacientes = fisioExamDatabase.getRoomPacienteDAO().getAllPacientes();
        List<Ombro> ombros = fisioExamDatabase.getRoomOmbroDAO().getAllOmbros();


        for (Exame exame : exames) {
            databaseReference.child("exames").child(Integer.toString(exame.getId())).setValue(exame);
        }

        for (Paciente paciente : pacientes) {
            databaseReference.child("pacientes").child(Integer.toString(paciente.getId())).setValue(paciente);
        }

        for (Ombro ombro : ombros) {
            databaseReference.child("ombros").child(Integer.toString(ombro.getId())).setValue(ombro);
        }

    }
}
