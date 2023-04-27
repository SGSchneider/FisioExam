package br.ufsm.fisioexam.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.ufsm.fisioexam.database.dao.CotoveloDAO;
import br.ufsm.fisioexam.database.dao.ExameDAO;
import br.ufsm.fisioexam.database.dao.LoginInfoDAO;
import br.ufsm.fisioexam.database.dao.OmbroDAO;
import br.ufsm.fisioexam.database.dao.PacienteDAO;
import br.ufsm.fisioexam.database.dao.PunhoDAO;
import br.ufsm.fisioexam.database.dao.SecoesDAO;
import br.ufsm.fisioexam.model.Cotovelo;
import br.ufsm.fisioexam.model.Exame;
import br.ufsm.fisioexam.model.LoginInfo;
import br.ufsm.fisioexam.model.Ombro;
import br.ufsm.fisioexam.model.Paciente;
import br.ufsm.fisioexam.model.Punho;
import br.ufsm.fisioexam.model.Secoes;


@Database(entities = {Paciente.class, Exame.class, Secoes.class, Ombro.class, Cotovelo.class, LoginInfo.class, Punho.class}, version = 36, exportSchema = false)
public abstract class FisioExamDatabase extends RoomDatabase {

    private static final String NOME_DATABASE = "fisioExam.db";

    public abstract PacienteDAO getRoomPacienteDAO();

    public abstract ExameDAO getRoomExameDAO();

    public abstract SecoesDAO getRoomSecoesDAO();

    public abstract OmbroDAO getRoomOmbroDAO();

    public abstract CotoveloDAO getRoomCotoveloDAO();

    public abstract LoginInfoDAO getRoomLoginInfoDAO();

    public abstract PunhoDAO getRoomPunhoDAO();

    public static FisioExamDatabase getInstance(Context context){
        return Room.databaseBuilder(context, FisioExamDatabase.class, NOME_DATABASE)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }


}
