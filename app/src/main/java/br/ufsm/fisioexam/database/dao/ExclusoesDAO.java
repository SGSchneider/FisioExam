package br.ufsm.fisioexam.database.dao;


import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import br.ufsm.fisioexam.model.Exclusoes;

@Dao
public interface ExclusoesDAO extends GenericDAO<Exclusoes>{
    @Override
    @Query("select * from exclusoes")
    List<Exclusoes> getAll();

    @Override
    @Query("SELECT * FROM exclusoes WHERE tipo LIKE :termo")
    List<Exclusoes> search(String termo);

    @Override
    @Query("DELETE FROM exclusoes")
    void deleteAll();

    @Override
    @Query("SELECT * FROM exclusoes WHERE tipo LIKE :registro LIMIT 1")
    Exclusoes getOne(String registro);

    @Override
    @Query("SELECT EXISTS (SELECT * FROM exclusoes WHERE tipo like :reg)")
    Boolean CheckID(String reg);
}
