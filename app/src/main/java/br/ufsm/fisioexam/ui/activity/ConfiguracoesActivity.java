package br.ufsm.fisioexam.ui.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.ufsm.fisioexam.R;

public class ConfiguracoesActivity extends AppCompatActivity {
    public static final String TITULO_APPBAR = "Configurações";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);
        setTitle(TITULO_APPBAR);
    }
}
