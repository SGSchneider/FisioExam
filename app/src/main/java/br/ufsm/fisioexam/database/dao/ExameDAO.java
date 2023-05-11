package br.ufsm.fisioexam.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import br.ufsm.fisioexam.model.Exame;

@Dao
public interface ExameDAO extends GenericDAO<Exame>{

    @Query("SELECT * FROM exame WHERE paciente LIKE :registro ORDER BY tipo")
    List<Exame> todos(String registro);

    @Query("SELECT * FROM exame")
    List<Exame> getAllExames();


    @Query("SELECT id FROM exame WHERE paciente LIKE :registro AND creationKey LIKE :chave")
    String getIdNovoExame(String registro, String chave);


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
}
