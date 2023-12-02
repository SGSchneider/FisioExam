package br.ufsm.fisioexam.ui;

import android.content.Context;
import android.widget.ListView;

import br.ufsm.fisioexam.ui.adapter.ExportarExameAdapter;

public class ExportarExameView {
    private final ExportarExameAdapter adapter;

    public ExportarExameView(Context context) {
        this.adapter = new ExportarExameAdapter(context);
    }

    public void atualizaSecoes(boolean[] secao) {
        adapter.atualiza(secao);
    }

    public void configuraAdapter(ListView listaSecoes) {
        listaSecoes.setAdapter(this.adapter);
    }
}
