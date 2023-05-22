package br.ufsm.fisioexam.ui;

import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_EXAME;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_PACIENTE;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_SECAO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_TIPO_COTOVELO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_TIPO_OMBRO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_TIPO_PUNHO;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.ufsm.fisioexam.database.FisioExamDatabase;
import br.ufsm.fisioexam.database.dao.CotoveloDAO;
import br.ufsm.fisioexam.database.dao.ExameDAO;
import br.ufsm.fisioexam.database.dao.OmbroDAO;
import br.ufsm.fisioexam.database.dao.PacienteDAO;
import br.ufsm.fisioexam.database.dao.PunhoDAO;
import br.ufsm.fisioexam.database.thread.QueryManager;
import br.ufsm.fisioexam.model.Cotovelo;
import br.ufsm.fisioexam.model.Exame;
import br.ufsm.fisioexam.model.Exclusoes;
import br.ufsm.fisioexam.model.Ombro;
import br.ufsm.fisioexam.model.Paciente;
import br.ufsm.fisioexam.model.Punho;

public class ExclusorDeDados {
    private final List<Exclusoes> exclusoes;
    private final FisioExamDatabase database;


    public ExclusorDeDados(Context context) {
        database = FisioExamDatabase.getInstance(context);
        exclusoes = new ArrayList<>();
    }

    public void ExcluiPaciente(String key) {
        QueryManager<Paciente> queryManagerPaciente = new QueryManager<>();
        QueryManager<Exame> queryManagerExame = new QueryManager<>();
        PacienteDAO pacienteDAO = database.getRoomPacienteDAO();
        Paciente pacientes =  queryManagerPaciente.getOne(key, pacienteDAO);
        List<Exame> exames = queryManagerExame.atualizaLista(pacientes.getId() ,database.getRoomExameDAO());
        for (Exame exame : exames) {
            ExcluiExame(exame.getId());
        }
        exclusoes.add(new Exclusoes(pacientes.getId(), CHAVE_PACIENTE));
    }

    public void ExcluiExame(String key) {
        ExameDAO exameDAO = database.getRoomExameDAO();
        Exame exame = exameDAO.getOne(key);
        switch (exame.getTipo()) {
            case CHAVE_TIPO_OMBRO -> {
                OmbroDAO dao;
                QueryManager<Ombro> queryManagerEspecifica= new QueryManager<>();
                dao = database.getRoomOmbroDAO();
                exclusoes.add(new Exclusoes(queryManagerEspecifica.getIdByForeign(key,dao), exame.getTipo()));
            }
            case CHAVE_TIPO_COTOVELO -> {
                CotoveloDAO dao;
                QueryManager<Cotovelo> queryManagerEspecifica= new QueryManager<>();
                dao = database.getRoomCotoveloDAO();
                exclusoes.add(new Exclusoes(queryManagerEspecifica.getIdByForeign(key,dao), exame.getTipo()));
            }
            case CHAVE_TIPO_PUNHO -> {
                PunhoDAO dao;
                QueryManager<Punho> queryManagerEspecifica= new QueryManager<>();
                dao = database.getRoomPunhoDAO();
                exclusoes.add(new Exclusoes(queryManagerEspecifica.getIdByForeign(key,dao), exame.getTipo()));
            }
        }
        exclusoes.add(new Exclusoes(key,CHAVE_SECAO));
        exclusoes.add(new Exclusoes(exame.getId(), CHAVE_EXAME));
    }

    public void atualizaRemocoesDB(){
        QueryManager<Exclusoes> queryManager = new QueryManager<>();
        queryManager.insert(exclusoes,database.getRoomExclusoesDAO());
    }
}
