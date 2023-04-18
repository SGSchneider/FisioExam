package br.ufsm.fisioexam.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.ufsm.fisioexam.model.Ombro;


@Dao
public interface OmbroDAO {
    @Insert
    void salva(Ombro ombro);

    @Query("SELECT * FROM ombro WHERE exame LIKE :registro")
    List<Ombro> todos(int registro);

    @Delete
    void remove(Ombro ombro);

    @Query("SELECT * FROM ombro WHERE id LIKE :id")
    Ombro getOmbro(int id);

    @Update
    void edita(Ombro ombro);

    @Query("SELECT id FROM ombro WHERE exame LIKE :registro ")
    int getIdOmbroPeloExame(int registro);
}

