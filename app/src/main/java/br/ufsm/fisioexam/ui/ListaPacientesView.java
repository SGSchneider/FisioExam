package br.ufsm.fisioexam.ui;


import android.app.AlertDialog;
import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import br.ufsm.fisioexam.database.FisioExamDatabase;
import br.ufsm.fisioexam.database.dao.PacienteDAO;
import br.ufsm.fisioexam.model.Paciente;
import br.ufsm.fisioexam.ui.adapter.ListaPacientesAdapter;

public class ListaPacientesView {

    private final ListaPacientesAdapter adapter;
    private final PacienteDAO pacienteDAO;
    private final Context context;

    public ListaPacientesView(Context context) {
        this.context = context;
        this.adapter = new ListaPacientesAdapter(context);
        pacienteDAO = FisioExamDatabase.getInstance(context).getRoomPacienteDAO();

    }

    public void confirmaRemocao(final MenuItem item) {
        new AlertDialog.Builder(context)
                .setTitle("Removendo Paciente")
                .setMessage("Tem certeza que quer remover o paciente?")
                .setPositiveButton("Sim", (dialog, which) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    Paciente pacienteEscolhido = adapter.getItem(menuInfo.position);
                    removePaciente(pacienteEscolhido);
                })
                .setNegativeButton("Não", null)
                .show();
    }

    public Paciente retornaPaciente(final MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        return(adapter.getItem(menuInfo.position));
    }

    private void removePaciente(Paciente paciente) {
        ExclusorDeDados exclusor = new ExclusorDeDados(context);
        exclusor.ExcluiPaciente(paciente.getId());
        exclusor.atualizaRemocoesDB();
        pacienteDAO.remove(paciente);
        adapter.remove(paciente);
    }

    public void atualizaPacientes() {
        adapter.atualiza(pacienteDAO.todos());
    }

    public void configuraAdapter(ListView listaDePacientes) { listaDePacientes.setAdapter(this.adapter);}

    public void pesquisaPacientes(String pacientes){
        adapter.atualiza(pacienteDAO.pesquisa(pacientes));
    }
}
