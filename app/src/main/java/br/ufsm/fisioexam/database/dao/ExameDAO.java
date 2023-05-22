package br.ufsm.fisioexam.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import br.ufsm.fisioexam.model.Exame;

@Dao
public interface ExameDAO extends GenericDAO<Exame>{

    @Override
    @Query("SELECT * From exame")
    List<Exame> getAll();

    @Override
    @Query("DELETE FROM exame")
    void deleteAll();

    @Override
    @Query("SELECT * FROM exame WHERE paciente LIKE :termo ORDER BY tipo")
    List<Exame> search(String termo);

    @Override
    @Query("SELECT EXISTS (SELECT* FROM exame WHERE id like :reg)")
    Boolean CheckID(String reg);

    @Override
    @Query("SELECT * FROM exame WHERE id like :reg")
    Exame getOne(String reg);

    @Override
    @Query("SELECT id FROM exame WHERE id like :registro")
    String getIdByForeign(String registro);

    @Override
    @Query("SELECT COUNT() FROM exame")
    int countSize();
}
