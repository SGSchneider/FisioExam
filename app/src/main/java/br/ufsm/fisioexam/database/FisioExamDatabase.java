package br.ufsm.fisioexam.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import br.ufsm.fisioexam.database.converter.ConversorCalendar;
import br.ufsm.fisioexam.database.dao.ExameDAO;
import br.ufsm.fisioexam.database.dao.OmbroDAO;
import br.ufsm.fisioexam.database.dao.PacienteDAO;
import br.ufsm.fisioexam.database.dao.SecoesDAO;
import br.ufsm.fisioexam.model.Exame;
import br.ufsm.fisioexam.model.Ombro;
import br.ufsm.fisioexam.model.Paciente;
import br.ufsm.fisioexam.model.Secoes;


@Database(entities = {Paciente.class, Exame.class, Secoes.class, Ombro.class}, version = 17, exportSchema = false)
@TypeConverters({ConversorCalendar.class})
public abstract class FisioExamDatabase extends RoomDatabase {

    private static final String NOME_DATABASE = "fisioExam.db";

    public abstract PacienteDAO getRoomPacienteDAO();

    public abstract ExameDAO getRoomExameDAO();

    public abstract SecoesDAO getRoomSecoesDAO();

    public abstract OmbroDAO getRoomOmbroDAO();

    public static FisioExamDatabase getInstance(Context context){
        return Room.databaseBuilder(context, FisioExamDatabase.class, NOME_DATABASE)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigrationFrom(16)
                .build();
    }
}
