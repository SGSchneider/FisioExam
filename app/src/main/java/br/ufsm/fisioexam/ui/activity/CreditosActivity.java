package br.ufsm.fisioexam.ui.activity;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.ufsm.fisioexam.R;

public class CreditosActivity extends AppCompatActivity {
    Button sair;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditos);
        configuraBotoes();
    }

    private void configuraBotoes() {
        sair = findViewById(R.id.button_sair);
        sair.setOnClickListener(v -> finish());
    }
}
