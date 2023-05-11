package br.ufsm.fisioexam.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import br.ufsm.fisioexam.model.Punho;

@Dao
public interface PunhoDAO extends GenericDAO<Punho>{
    @Override
    @Query("SELECT * FROM punho WHERE exame LIKE :registro")
    List<Punho> search(String registro);

    @Override
    @Query("SELECT * FROM punho")
    List<Punho> getAll();

    @Override
    @Query("DELETE FROM punho")
    void deleteAll();

    @Query("SELECT id FROM punho WHERE exame LIKE :exame")
    String getIdPunhoPeloExame(String exame);

    @Override
    @Query("SELECT * FROM punho WHERE id LIKE :registro")
    Punho getOne(String registro);

    @Override
    @Query("SELECT EXISTS (SELECT * FROM punho WHERE id like :reg)")
    Boolean CheckID(String reg);
}
