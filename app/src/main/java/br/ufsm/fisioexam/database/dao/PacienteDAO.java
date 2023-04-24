package br.ufsm.fisioexam.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.ufsm.fisioexam.model.Paciente;

@Dao
public interface PacienteDAO {

    @Insert
    void salva(Paciente paciente);

    @Query("SELECT * FROM paciente ORDER BY nome")
    List<Paciente> todos();

    @Query("SELECT * FROM paciente")
    List<Paciente> getAllPacientes();

    @Insert
    void insertAllPacientes(List<Paciente> Pacientes);

    @Query("DELETE FROM paciente ")
    void deleteAllPacientes();

    @Query("SELECT * FROM paciente WHERE nome LIKE :termo ORDER BY nome")
    List<Paciente> pesquisa(String termo);

    @Delete
    void remove(Paciente paciente);

    @Update
    void edita(Paciente paciente);

    @Query("SELECT * FROM paciente WHERE id like :paciente")
    List<Paciente> CheckID(String paciente);
}
