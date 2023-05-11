package br.ufsm.fisioexam.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import br.ufsm.fisioexam.model.Secoes;


@Dao
public interface SecoesDAO extends GenericDAO<Secoes>{

    @Override
    @Query("SELECT * FROM secoes WHERE id LIKE :id")
    Secoes getOne(String id);

    @Override
    @Query("select * from secoes")
    List<Secoes> getAll();

    @Override
    @Query("delete from secoes")
    void deleteAll();

    @Override
    @Query("SELECT * FROM secoes WHERE id like :termo")
    List<Secoes> search(String termo);

    @Override
    @Query("SELECT EXISTS (SELECT* FROM secoes WHERE id like :reg)")
    Boolean CheckID(String reg);
}
