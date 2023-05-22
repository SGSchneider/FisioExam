package br.ufsm.fisioexam.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import br.ufsm.fisioexam.model.LoginInfo;

@Dao
public interface LoginInfoDAO extends GenericDAO<LoginInfo>{

    @Override
    @Query("SELECT COUNT() FROM loginInfo")
    int countSize();


    @Override
    @Query("SELECT * FROM LoginInfo WHERE user LIKE :registro")
    List<LoginInfo> search(String registro);

    @Override
    @Query("SELECT * FROM LoginInfo LIMIT 1")
    List<LoginInfo> getAll();

    @Override
    @Query("DELETE FROM LoginInfo")
    void deleteAll();

    @Override
    @Query("SELECT * FROM LoginInfo WHERE user LIKE :registro")
    LoginInfo getOne(String registro);

    @Override
    @Query("SELECT EXISTS (SELECT * FROM LoginInfo WHERE user like :reg)")
    Boolean CheckID(String reg);

    @Override
    @Query("SELECT user FROM logininfo WHERE user LIKE :registro")
    String getIdByForeign(String registro);
}
