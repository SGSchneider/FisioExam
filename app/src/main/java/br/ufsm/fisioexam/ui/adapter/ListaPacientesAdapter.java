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
import br.ufsm.fisioexam.model.Paciente;

public class ListaPacientesAdapter extends BaseAdapter {

    private final List<Paciente> pacientes = new ArrayList<>();
    private final Context context;

    public ListaPacientesAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return pacientes.size();
    }

    @Override
    public Paciente getItem(int position) {
        return pacientes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return pacientes.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewCriada = criaView(parent);
        Paciente pacienteDevolvido = pacientes.get(position);
        vincula(viewCriada, pacienteDevolvido);
        return viewCriada;
    }

    private void vincula(View view, Paciente paciente) {
        TextView nome = view.findViewById(R.id.item_paciente_nome);
        nome.setText(paciente.getNome());
        TextView telefone = view.findViewById(R.id.item_paciente_telefone);
        telefone.setText(paciente.getTelefone());
    }

    private View criaView(ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_paciente, parent, false);
    }

    public void atualiza(List<Paciente> pacientes) {
        this.pacientes.clear();
        this.pacientes.addAll(pacientes);
        notifyDataSetChanged();
    }

    public void remove(Paciente paciente) {
        pacientes.remove(paciente);
        notifyDataSetChanged();
    }
}
