package br.ufsm.fisioexam.ui;

import android.content.Context;
import android.widget.ListView;

import br.ufsm.fisioexam.database.FisioExamDatabase;
import br.ufsm.fisioexam.database.dao.SecoesDAO;
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
        adapter.atualiza(dao.getSecao(id_exame));
    }

    public void configuraAdapter(ListView listaSecoes) {
        listaSecoes.setAdapter(this.adapter);
    }
}
