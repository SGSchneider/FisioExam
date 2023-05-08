package br.ufsm.fisioexam.ui.activity;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.ufsm.fisioexam.R;

public class AjudaTestesEspeciaisDashOmbroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajuda_testes_especiais_dash_ombro);
        inicializaButtons();
    }


    

    private void inicializaButtons() {
        Button sair = findViewById(R.id.activity_ajuda_testes_especiais_dash_ombro_button_sair);
        sair.setOnClickListener(v -> finalizaAjuda());
    }

    private void finalizaAjuda() {
        finish();
    }
}
