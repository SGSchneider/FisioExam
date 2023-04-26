package br.ufsm.fisioexam.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class LoginInfo implements Serializable {
    @PrimaryKey
    private @NonNull String user;
    private @NonNull String password;

    public LoginInfo(@NonNull String user, @NonNull String password){
        this.user = user;
        this.password = password;
    }

    @NonNull
    public String getUser() {
        return user;
    }

    public void setUser(@NonNull String user) {
        this.user = user;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }
}
