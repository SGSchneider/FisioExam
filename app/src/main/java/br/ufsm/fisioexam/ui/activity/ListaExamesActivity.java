package br.ufsm.fisioexam.ui.activity;

import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_EXAME;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_ID_PACIENTE;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_TIPO_COTOVELO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_TIPO_EXAME;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.CHAVE_TIPO_OMBRO;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.PopupMenu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.ufsm.fisioexam.R;
import br.ufsm.fisioexam.model.Exame;
import br.ufsm.fisioexam.ui.ListaExamesView;

public class ListaExamesActivity extends AppCompatActivity {
    public static final String TITULO_APPBAR = "Lista de Exames";
    private ListaExamesView listaExamesView;
    private String id_paciente;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_exames);
        setTitle(TITULO_APPBAR);
        carregaIdPaciente();
        listaExamesView = new ListaExamesView(this, id_paciente);
        configuraFabNovoExame();
        configuraLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_exames_menu, menu);
    }


    private void configuraFabNovoExame() {
        FloatingActionButton botaoNovoExame = findViewById(R.id.activity_lista_exames_fab_novo_exame);
        botaoNovoExame.setOnClickListener(this::showPopup);
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(item -> {
            boolean b = true;
            if (item.getItemId() == R.id.activity_lista_exames_menu_add_exame_cotovelo) {
                abreFormularioModoNovoExame(CHAVE_TIPO_COTOVELO);
                return b;
            } else if (item.getItemId() == R.id.activity_lista_exames_menu_add_exame_ombro) {
                abreFormularioModoNovoExame(CHAVE_TIPO_OMBRO);
                return b;
            } else {
                return false;
            }
        });
        popup.inflate(R.menu.activity_lista_exames_add_exame);
        popup.show();
    }


    @Override
    protected void onResume() {
        super.onResume();
        listaExamesView.atualizaExames();
    }

    private void configuraLista() {
        ListView listaDeExames = findViewById(R.id.activity_lista_exames_listview);
        listaExamesView.configuraAdapter(listaDeExames);
        configuraListenerDeCliquePorItem(listaDeExames);
        registerForContextMenu(listaDeExames);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_lista_exames_menu_remover) {
            listaExamesView.confirmaRemocao(item);
        }
        return super.onContextItemSelected(item);
    }

    private void configuraListenerDeCliquePorItem(ListView listaDeExames) {
        listaDeExames.setOnItemClickListener((adapterView, view, posicao, id) -> {
            Exame ExameEscolhido = (Exame) adapterView.getItemAtPosition(posicao);
            abreFormularioModoEditaExame(ExameEscolhido);
        });
    }

    private void carregaIdPaciente() {
        Intent dados = getIntent();

        if (dados.hasExtra(CHAVE_ID_PACIENTE)) {
            id_paciente = (String) dados.getSerializableExtra(CHAVE_ID_PACIENTE);
        }
    }

    private void abreFormularioModoNovoExame(String tipo) {
        Intent vaiParaFormularioActivity = new Intent(this, SecoesExameActivity.class);
        vaiParaFormularioActivity.putExtra(CHAVE_TIPO_EXAME, tipo);
        vaiParaFormularioActivity.putExtra(CHAVE_ID_PACIENTE, id_paciente);
        startActivity(vaiParaFormularioActivity);
    }

    private void abreFormularioModoEditaExame(Exame ExameEscolhido) {
        Intent vaiParaFormularioActivity = new Intent(this, SecoesExameActivity.class);
        vaiParaFormularioActivity.putExtra(CHAVE_EXAME, ExameEscolhido.getId());
        startActivity(vaiParaFormularioActivity);
    }


}
