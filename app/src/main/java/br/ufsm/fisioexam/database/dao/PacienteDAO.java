package br.ufsm.fisioexam.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import br.ufsm.fisioexam.model.Paciente;

@Dao
public interface PacienteDAO extends GenericDAO<Paciente>{


    @Query("SELECT * FROM paciente ORDER BY nome")
    @Override
    List<Paciente> getAll();

    @Override
    @Query("DELETE FROM paciente ")
    void deleteAll();

    @Override
    @Query("SELECT * FROM paciente WHERE nome LIKE :termo ORDER BY nome")
    List<Paciente> search(String termo);

    @Override
    @Query("SELECT EXISTS (SELECT* FROM paciente WHERE id like :reg)")
    Boolean CheckID(String reg);

    @Override
    @Query("SELECT * FROM paciente WHERE id like :paciente")
    Paciente getOne(String paciente);

    @Override
    @Query("SELECT id FROM paciente WHERE id LIKE :registro")
    String getIdByForeign(String registro);

    @Override
    @Query("SELECT COUNT() FROM paciente")
    int countSize();
}
