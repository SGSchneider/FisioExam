package br.ufsm.fisioexam.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.ufsm.fisioexam.R;

public class fisioExamMenuActivity extends AppCompatActivity {

    private Button pacientes;
    private Button configuracoes;
    private Button creditos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        configuraBotoes();
    }



    private void configuraBotoes() {
        pacientes = findViewById(R.id.button_pacientes);
        configuracoes = findViewById(R.id.button_config);
        creditos = findViewById(R.id.button_credits);

        setaListenerDeClique();
    }

    private void setaListenerDeClique() {
        pacientes.setOnClickListener(v -> abreListaPacientes());
        configuracoes.setOnClickListener(v -> abreConfiguracoes());
        creditos.setOnClickListener(v -> abreCreditos());
    }

    private void abreCreditos() {
        startActivity(new Intent(this, CreditosActivity.class));
    }

    private void abreListaPacientes() {
        startActivity(new Intent(this, ListaPacientesActivity.class));
    }

    private void abreConfiguracoes() {
        startActivity(new Intent(this, ConfiguracoesActivity.class));
    }
}
