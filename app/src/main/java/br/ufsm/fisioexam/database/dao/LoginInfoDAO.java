package br.ufsm.fisioexam.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import br.ufsm.fisioexam.model.LoginInfo;

@Dao
public interface LoginInfoDAO {
    @Insert
    void salva(LoginInfo login);

    @Query("SELECT * FROM logininfo LIMIT 1")
    LoginInfo getLoginInfo();

    @Query("DELETE FROM logininfo")
    void remove();

    @Query("SELECT COUNT() FROM loginInfo")
    int countSize();
}
