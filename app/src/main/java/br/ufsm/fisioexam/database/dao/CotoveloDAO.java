package br.ufsm.fisioexam.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import br.ufsm.fisioexam.model.Cotovelo;

@Dao
public interface CotoveloDAO extends GenericDAO<Cotovelo>{

    @Query("SELECT id FROM cotovelo WHERE exame LIKE :registro")
    String getIdCotoveloPeloExame(String registro);



    @Override
    @Query("SELECT * FROM Cotovelo WHERE id LIKE :id")
    Cotovelo getOne(String id);

    @Override
    @Query("select * from Cotovelo")
    List<Cotovelo> getAll();

    @Override
    @Query("delete from Cotovelo")
    void deleteAll();

    @Override
    @Query("SELECT * FROM Cotovelo WHERE exame like :termo")
    List<Cotovelo> search(String termo);

    @Override
    @Query("SELECT EXISTS (SELECT* FROM Cotovelo WHERE id like :reg)")
    Boolean CheckID(String reg);

}
