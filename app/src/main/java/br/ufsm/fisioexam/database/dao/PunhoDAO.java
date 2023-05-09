package br.ufsm.fisioexam.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.ufsm.fisioexam.model.Punho;

@Dao
public interface PunhoDAO {
    @Insert
    void salva(Punho punho);

    @Delete
    void remove(Punho punho);

    @Update
    void edita(Punho punho);

    @Query("SELECT * FROM punho WHERE exame LIKE :registro")
    List<Punho> todos(String registro);

    @Query("SELECT * FROM punho")
    List<Punho> getAllPunhos();

    @Query("DELETE FROM punho")
    void deleteAllPunhos();

    @Query("SELECT id FROM punho WHERE exame LIKE :exame")
    String getIdPunhoPeloExame(String exame);

    @Insert
    void insertAllPunhos(List<Punho> punhos);

    @Query("SELECT * FROM punho WHERE id LIKE :registro")
    Punho  getPunho(String registro);
}
