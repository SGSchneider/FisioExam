package br.ufsm.fisioexam.database.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import br.ufsm.fisioexam.model.Exclusoes;

@Dao
public interface ExclusoesDAO {
    @Insert
    void insere(List<Exclusoes> exclusoes);

    @Delete
    void remove(Exclusoes exclusoes);

    @Query("select * from exclusoes")
    List<Exclusoes> getExclusoes();


}
