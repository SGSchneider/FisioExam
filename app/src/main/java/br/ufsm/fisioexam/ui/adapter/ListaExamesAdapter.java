
package br.ufsm.fisioexam.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.ufsm.fisioexam.R;
import br.ufsm.fisioexam.model.Exame;

public class ListaExamesAdapter extends BaseAdapter {
    private final List<Exame> exames = new ArrayList<>();
    private final Context context;

    public ListaExamesAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return exames.size();
    }

    @Override
    public Exame getItem(int position) {
        return exames.get(position);
    }

    @Override
    public long getItemId(int position) {
        return exames.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewCriada = criaView(parent);
        Exame exameDevolvido = exames.get(position);
        vincula(viewCriada, exameDevolvido);
        return viewCriada;
    }

    private void vincula(View view, Exame exame) {
        TextView tipo = view.findViewById(R.id.item_exame_tipo);
        tipo.setText(exame.getTipo());
        TextView data = view.findViewById(R.id.item_exame_data);
        data.setText(exame.getDataString());
    }

    private View criaView(ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_exame, parent, false);
    }

    public void atualiza(List<Exame> exames) {
        this.exames.clear();
        this.exames.addAll(exames);
        notifyDataSetChanged();
    }

    public void remove(Exame exame) {
        exames.remove(exame);
        notifyDataSetChanged();
    }
}
