package br.ufsm.fisioexam.ui.activity;

import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_ID_PACIENTE;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_PACIENTE;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.ufsm.fisioexam.R;
import br.ufsm.fisioexam.model.Paciente;
import br.ufsm.fisioexam.ui.ListaPacientesView;

public class ListaPacientesActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de Pacientes";
    private ListaPacientesView listaPacientesView;
    private SearchView searchBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pacientes);
        setTitle(TITULO_APPBAR);
        listaPacientesView = new ListaPacientesView(this);
        configuraFabNovoPaciente();
        configuraLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_pacientes_menu, menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_lista_pacientes_search, menu);

        MenuItem menuItem = menu.findItem(R.id.activity_lista_pacientes_menu_search);
        searchBar = (SearchView) menuItem.getActionView();
        configuraListenersSearchBar();

        return super.onCreateOptionsMenu(menu);
    }

    private void configuraListenersSearchBar() {
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                realizarPesquisa();
                return false;
            }


            @Override
            public boolean onQueryTextChange(String newText) {
                realizarPesquisa();
                return false;
            }
        });

        searchBar.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                listaPacientesView.atualizaPacientes();
                return false;
            }
        });
    }


    private void realizarPesquisa() {
        String pesquisa;
        pesquisa = "%" + searchBar.getQuery() + "%";
        pesquisaPaciente(pesquisa);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_lista_pacientes_menu_search) {
            searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    pesquisaPaciente(query);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    pesquisaPaciente(newText);
                    return false;
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }

    private void pesquisaPaciente(String pesquisa) {
        listaPacientesView.pesquisaPacientes(pesquisa);
    }

    private void configuraFabNovoPaciente() {
        FloatingActionButton botaoNovoPaciente = findViewById(R.id.activity_lista_pacientes_fab_novo_paciente);
        botaoNovoPaciente.setOnClickListener(v -> abreFormularioModoInserePaciente());
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaPacientesView.atualizaPacientes();
    }

    private void configuraLista() {
        ListView listaDePacientes = findViewById(R.id.activity_lista_pacientes_listview);
        listaPacientesView.configuraAdapter(listaDePacientes);
        configuraListenerDeCliquePorItem(listaDePacientes);
        registerForContextMenu(listaDePacientes);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_lista_pacientes_menu_remover) {
            listaPacientesView.confirmaRemocao(item);
        }
        if (itemId == R.id.activity_lista_pacientes_menu_editar) {
            abreFormularioModoEditaPaciente(listaPacientesView.retornaPaciente(item));
        }
        return super.onContextItemSelected(item);
    }

    private void configuraListenerDeCliquePorItem(ListView listaDePacientes) {
        listaDePacientes.setOnItemClickListener((adapterView, view, posicao, id) -> {
            Paciente PacienteEscolhido = (Paciente) adapterView.getItemAtPosition(posicao);
            abreListaExames(PacienteEscolhido);
        });
    }

    private void abreListaExames(Paciente pacienteEscolhido) {
        Intent vaiParaListaExames = new Intent(this, ListaExamesActivity.class);
        vaiParaListaExames.putExtra(CHAVE_ID_PACIENTE, pacienteEscolhido.getId());
        startActivity(vaiParaListaExames);
    }

    private void abreFormularioModoEditaPaciente(Paciente PacienteEscolhido) {
        Intent vaiParaFormularioActivity = new Intent(this, FormularioPacienteActivity.class);
        vaiParaFormularioActivity.putExtra(CHAVE_PACIENTE, PacienteEscolhido);
        startActivity(vaiParaFormularioActivity);
    }

    private void abreFormularioModoInserePaciente() {
        startActivity(new Intent(this, FormularioPacienteActivity.class));
    }
}
