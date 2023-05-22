package br.ufsm.fisioexam.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import br.ufsm.fisioexam.database.FisioExamDatabase;
import br.ufsm.fisioexam.database.dao.ExameDAO;
import br.ufsm.fisioexam.database.thread.QueryManager;
import br.ufsm.fisioexam.model.Exame;
import br.ufsm.fisioexam.ui.adapter.ListaExamesAdapter;

public class ListaExamesView {
    private final ListaExamesAdapter adapter;
    private final ExameDAO exameDAO;
    private final Context context;
    private final String id_paciente;

    private final QueryManager<Exame> queryManager;

    public ListaExamesView(Context context, String id_paciente) {
        this.context = context;
        this.adapter = new ListaExamesAdapter(context);
        this.id_paciente = id_paciente;
        exameDAO = FisioExamDatabase.getInstance(context).getRoomExameDAO();
        queryManager = new QueryManager<>();
    }

    public void confirmaRemocao(final MenuItem item) {
        new AlertDialog.Builder(context)
                .setTitle("Removendo Exame")
                .setMessage("Tem certeza que quer remover o exame?")
                .setPositiveButton("Sim", (dialog, which) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    Exame exameEscolhido = adapter.getItem(menuInfo.position);
                    removeExame(exameEscolhido);
                })
                .setNegativeButton("Não", null)
                .show();
    }

    private void removeExame(Exame exame) {
        ExclusorDeDados exclusor = new ExclusorDeDados(context);
        exclusor.ExcluiExame(exame.getId());
        exclusor.atualizaRemocoesDB();

        queryManager.delete(exame,exameDAO);
        adapter.remove(exame);
    }

    public void atualizaExames() {
        AtualizaLista(id_paciente);
    }

    private void AtualizaLista(String pesquisa) {
        adapter.atualiza(queryManager.atualizaLista(pesquisa,exameDAO));
    }

    public void configuraAdapter(ListView listaDeExames) {
        listaDeExames.setAdapter(this.adapter);
    }
}
