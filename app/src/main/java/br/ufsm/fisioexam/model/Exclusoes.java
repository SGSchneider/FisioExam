package br.ufsm.fisioexam.model;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Exclusoes implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int keyPrimaria;

    private @NonNull String id;

    private @NonNull String tipo;

    public Exclusoes(@NonNull String id, @NonNull String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public int getKeyPrimaria() {
        return keyPrimaria;
    }

    public void setKeyPrimaria(int keyPrimaria) {
        this.keyPrimaria = keyPrimaria;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    @NonNull
    public String getTipo() {
        return tipo;
    }

    public void setTipo(@NonNull String tipo) {
        this.tipo = tipo;
    }
}
