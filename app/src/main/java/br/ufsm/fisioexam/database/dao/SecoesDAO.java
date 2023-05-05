package br.ufsm.fisioexam.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.ufsm.fisioexam.model.Secoes;


@Dao
public interface SecoesDAO {
    @Insert
    void salva(Secoes secoes);

    @Insert
    void insertAllSecoes(List<Secoes> secoes);

    @Delete
    void remove(Secoes secoes);

    @Update
    void edita(Secoes secoes);

    @Query("SELECT * FROM secoes WHERE id LIKE :id")
    Secoes getSecao(String id);

    @Query("select * from secoes")
    List<Secoes> getAllSecoes();

    @Query("delete from secoes")
    void deleteAllSecoes();
}
