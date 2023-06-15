package br.ufsm.fisioexam.ui;

import android.content.Context;
import android.widget.ListView;

import br.ufsm.fisioexam.database.FisioExamDatabase;
import br.ufsm.fisioexam.database.dao.SecoesDAO;
import br.ufsm.fisioexam.database.thread.QueryManager;
import br.ufsm.fisioexam.model.Secoes;
import br.ufsm.fisioexam.ui.adapter.SecoesExamesAdapter;

public class SecoesExameView {
    private final SecoesExamesAdapter adapter;
    private final SecoesDAO dao;
    private final String id_exame;
    private final QueryManager<Secoes> secoesQueryManager;

    public SecoesExameView(Context context, String id_exame) {
        this.adapter = new SecoesExamesAdapter(context);
        this.id_exame = id_exame;
        dao = FisioExamDatabase.getInstance(context).getRoomSecoesDAO();
        secoesQueryManager = new QueryManager<>();
    }

    public void atualizaSecoes() {
        Secoes secao;
        secao = secoesQueryManager.getOne(id_exame, dao);
        if(secao!=null) {
            adapter.atualiza(secao);
        }
        else{
            secao = new Secoes(id_exame);
            secoesQueryManager.insert(secao, dao);
            adapter.atualiza(secao);
        }
    }

    public void configuraAdapter(ListView listaSecoes) {
        listaSecoes.setAdapter(this.adapter);
    }
}
