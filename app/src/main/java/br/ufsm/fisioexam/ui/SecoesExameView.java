package br.ufsm.fisioexam.ui;

import android.content.Context;
import android.widget.ListView;

import br.ufsm.fisioexam.database.FisioExamDatabase;
import br.ufsm.fisioexam.database.dao.SecoesDAO;
import br.ufsm.fisioexam.model.Secoes;
import br.ufsm.fisioexam.ui.adapter.SecoesExamesAdapter;

public class SecoesExameView {
    private final SecoesExamesAdapter adapter;
    private final SecoesDAO dao;
    private final String id_exame;

    public SecoesExameView(Context context, String id_exame) {
        this.adapter = new SecoesExamesAdapter(context);
        this.id_exame = id_exame;
        dao = FisioExamDatabase.getInstance(context).getRoomSecoesDAO();
    }

    public void atualizaSecoes() {
        Secoes secao;
        secao = dao.getOne(id_exame);
        if(secao!=null) {
            adapter.atualiza(secao);
        }
        else{
            secao = new Secoes(id_exame);
            dao.insert(secao);
            adapter.atualiza(secao);
        }
    }

    public void configuraAdapter(ListView listaSecoes) {
        listaSecoes.setAdapter(this.adapter);
    }
}
