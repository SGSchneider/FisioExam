package br.ufsm.fisioexam.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import br.ufsm.fisioexam.model.Ombro;


@Dao
public interface OmbroDAO extends GenericDAO<Ombro>{

    @Override
    @Query("SELECT * FROM ombro WHERE exame LIKE :registro")
    List<Ombro> search(String registro);

    @Override
    @Query("SELECT * FROM ombro WHERE id LIKE :id")
    Ombro getOne(String id);

    @Override
    @Query("SELECT * FROM ombro ")
    List<Ombro> getAll();

    @Override
    @Query("DELETE FROM ombro ")
    void deleteAll();

    @Query("SELECT id FROM ombro WHERE exame LIKE :registro ")
    String getIdOmbroPeloExame(String registro);


    @Override
    @Query("SELECT EXISTS (SELECT * FROM ombro WHERE id like :reg)")
    Boolean CheckID(String reg);
}

