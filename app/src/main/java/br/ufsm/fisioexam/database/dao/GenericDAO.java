package br.ufsm.fisioexam.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface GenericDAO<T> {


    @Insert
    void insert(T entity);

    @Insert
    void insert(List<T> entities);

    @Update
    void update(T entity);

    @Delete
    void delete(T entity);

    @Delete
    void delete(List<T> entities);

    @Query("SELECT * From Paciente")
    List<T> getAll();

    @Query("DELETE FROM Paciente")
    void deleteAll();

    @Query("SELECT * FROM paciente WHERE nome LIKE :termo ORDER BY nome")
    List<T> search(String termo);

    @Query("SELECT EXISTS (SELECT* FROM paciente WHERE id like :reg)")
    Boolean CheckID(String reg);

    @Query("SELECT * FROM paciente WHERE id like :reg")
    T getOne(String reg);

    @Query("SELECT id FROM cotovelo WHERE exame LIKE :registro")
    String getIdByForeign(String registro);

    @Query("SELECT id FROM exame WHERE paciente LIKE :registro AND creationKey LIKE :chave")
    String getIdNovoExame(String registro, String chave);

    @Query("SELECT COUNT() FROM paciente")
    int countSize();

}