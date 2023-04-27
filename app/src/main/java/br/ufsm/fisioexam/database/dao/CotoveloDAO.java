package br.ufsm.fisioexam.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.ufsm.fisioexam.model.Cotovelo;

@Dao
public interface CotoveloDAO {
    @Insert
    void salva(Cotovelo cotovelo);

    @Query("SELECT * FROM cotovelo WHERE exame LIKE :registro")
    List<Cotovelo> todos(String registro);

    @Delete
    void remove(Cotovelo cotovelo);

    @Update
    void edita(Cotovelo cotovelo);

    @Query("SELECT * FROM cotovelo WHERE id LIKE :registro")
    Cotovelo getCotovelo(String registro);

    @Query("SELECT * FROM cotovelo")
    List<Cotovelo> getAllCotovelos();

    @Query("SELECT id FROM cotovelo WHERE exame LIKE :registro")
    String getIdCotoveloPeloExame(String registro);

    @Query("DELETE FROM cotovelo")
    void deleteAllCotovelos();
}
