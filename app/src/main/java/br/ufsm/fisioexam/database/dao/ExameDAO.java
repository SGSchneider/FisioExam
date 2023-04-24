package br.ufsm.fisioexam.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import java.lang.String;

import br.ufsm.fisioexam.model.Exame;

@Dao
public interface ExameDAO {
    @Insert
    void salva(Exame exame);

    @Query("SELECT * FROM exame WHERE paciente LIKE :registro ORDER BY tipo")
    List<Exame> todos(String registro);

    @Query("SELECT * FROM exame")
    List<Exame> getAllExames();

    @Insert
    void insertAllExames(List<Exame> Exames);

    @Query("DELETE FROM exame")
    void deleteAllExames();

    @Delete
    void remove(Exame exame);

    @Update
    void edita(Exame exame);

    @Query("SELECT * FROM exame WHERE id LIKE :id")
    Exame getExame(String id);

    @Query("SELECT id FROM exame WHERE paciente LIKE :registro AND creationKey LIKE :chave")
    String getIdNovoExame(String registro, String chave);

}
