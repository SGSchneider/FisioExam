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
import br.ufsm.fisioexam.database.dao.ExclusoesDAO;
import br.ufsm.fisioexam.database.dao.OmbroDAO;
import br.ufsm.fisioexam.database.dao.PacienteDAO;
import br.ufsm.fisioexam.database.dao.PunhoDAO;
import br.ufsm.fisioexam.model.Exame;
import br.ufsm.fisioexam.model.Exclusoes;
import br.ufsm.fisioexam.model.Paciente;

public class ExclusorDeDados {
    private final List<Exclusoes> exclusoes;
    private final FisioExamDatabase database;


    public ExclusorDeDados(Context context) {
        database = FisioExamDatabase.getInstance(context);
        exclusoes = new ArrayList<>();
    }

    public void ExcluiPaciente(String key) {
        PacienteDAO pacienteDAO = database.getRoomPacienteDAO();
        Paciente paciente = pacienteDAO.getOne(key);
        List<Exame> exames = database.getRoomExameDAO().todos(paciente.getId());
        for (Exame exame : exames) {
            ExcluiExame(exame.getId());
        }
        exclusoes.add(new Exclusoes(paciente.getId(), CHAVE_PACIENTE));
    }

    public void ExcluiExame(String key) {
        ExameDAO exameDAO = database.getRoomExameDAO();
        Exame exame = exameDAO.getOne(key);
        switch (exame.getTipo()) {
            case CHAVE_TIPO_OMBRO:
                OmbroDAO ombroDAO = database.getRoomOmbroDAO();
                exclusoes.add(new Exclusoes(ombroDAO.getIdOmbroPeloExame(exame.getId()), CHAVE_TIPO_OMBRO));
                break;
            case CHAVE_TIPO_COTOVELO:
                CotoveloDAO cotoveloDAO = database.getRoomCotoveloDAO();
                exclusoes.add(new Exclusoes(cotoveloDAO.getIdCotoveloPeloExame(exame.getId()), CHAVE_TIPO_COTOVELO));
                break;
            case CHAVE_TIPO_PUNHO:
                PunhoDAO punhoDAO = database.getRoomPunhoDAO();
                exclusoes.add(new Exclusoes(punhoDAO.getIdPunhoPeloExame(exame.getId()), CHAVE_TIPO_PUNHO));
                break;
        }
        exclusoes.add(new Exclusoes(key,CHAVE_SECAO));
        exclusoes.add(new Exclusoes(exame.getId(), CHAVE_EXAME));
    }

    public void atualizaRemocoesDB(){
        ExclusoesDAO exclusoesDAO = database.getRoomExclusoesDAO();
        exclusoesDAO.insert(exclusoes);
    }
}
