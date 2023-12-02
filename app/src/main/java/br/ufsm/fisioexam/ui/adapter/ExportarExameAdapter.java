package br.ufsm.fisioexam.ui.adapter;

import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.AFASTAMENTODAFUNCAO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.AMPLITUDEMOVIMENTO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.ANTECEDENTESPESSOAIS;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.DIAGNOSTICOFISIO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.DIAGNOSTICOMEDICO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.DOENCASASSOCIADAS;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.DOR;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.EXAMESCOMPLEMENTARES;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.FORCAMUSCULAR;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.HISTORIADOENCAATUAL;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.HISTORIAFAMILIAR;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.HISTORIAOCUPACIONAL;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.HISTORIASOCIAL;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.INSPECAO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.MEDICAMENTOSEMUSO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.OBJETIVOSTRATAMENTO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.OBSERVACOES;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.PALPACAO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.PERIMETRIA;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.PLANOTRATAMENTO;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.QUEIXAPRINCIPAL;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.SENSIBILIDADE;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.SINAISVITAIS;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.TESTESESPECIAIS;
import static br.ufsm.fisioexam.ui.activity.ConstantesActivities.TRATAMENTOANTERIOR;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.ufsm.fisioexam.R;
import br.ufsm.fisioexam.model.Secao;

public class ExportarExameAdapter extends BaseAdapter {
    private final List<Secao> secoes = new ArrayList<>();
    private final Context context;

    public ExportarExameAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return secoes.size();
    }

    @Override
    public Secao getItem(int position) {
        return secoes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return secoes.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewCriada = criaView(parent);
        Secao secaoDevolvida = secoes.get(position);
        vincula(viewCriada, secaoDevolvida);
        return viewCriada;
    }

    private void vincula(View view, Secao secao) {
        TextView nome = view.findViewById(R.id.item_export_nome);
        nome.setText(secao.getNome());
        CheckBox preenchido = view.findViewById(R.id.item_export_selection);
        preenchido.setChecked(secao.isPreenchida());
    }

    private View criaView(ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_exporta_exame, parent, false);
    }

    public void atualiza(boolean[] secoes) {
        this.secoes.clear();
        criaLista(secoes);
    }

    public void adiciona(Secao secao) {
        secoes.add(secao);
        notifyDataSetChanged();
    }

    public void criaLista(boolean[] secoes) {
        for (int i = 1; i < 26; i++) {
            Secao aux = switch (i) {
                case 1 -> new Secao(i, DIAGNOSTICOMEDICO, secoes[i - 1]);
                case 2 -> new Secao(i, QUEIXAPRINCIPAL, secoes[i - 1]);
                case 3 -> new Secao(i, HISTORIADOENCAATUAL, secoes[i - 1]);
                case 4 -> new Secao(i, DOR, secoes[i - 1]);
                case 5 -> new Secao(i, TRATAMENTOANTERIOR, secoes[i - 1]);
                case 6 -> new Secao(i, AFASTAMENTODAFUNCAO, secoes[i - 1]);
                case 7 -> new Secao(i, ANTECEDENTESPESSOAIS, secoes[i - 1]);
                case 8 -> new Secao(i, DOENCASASSOCIADAS, secoes[i - 1]);
                case 9 -> new Secao(i, MEDICAMENTOSEMUSO, secoes[i - 1]);
                case 10 -> new Secao(i, HISTORIAFAMILIAR, secoes[i - 1]);
                case 11 -> new Secao(i, HISTORIAOCUPACIONAL, secoes[i - 1]);
                case 12 -> new Secao(i, HISTORIASOCIAL, secoes[i - 1]);
                case 13 -> new Secao(i, EXAMESCOMPLEMENTARES, secoes[i - 1]);
                case 14 -> new Secao(i, SINAISVITAIS, secoes[i - 1]);
                case 15 -> new Secao(i, INSPECAO, secoes[i - 1]);
                case 16 -> new Secao(i, PALPACAO, secoes[i - 1]);
                case 17 -> new Secao(i, AMPLITUDEMOVIMENTO, secoes[i - 1]);
                case 18 -> new Secao(i, PERIMETRIA, secoes[i - 1]);
                case 19 -> new Secao(i, FORCAMUSCULAR, secoes[i - 1]);
                case 20 -> new Secao(i, SENSIBILIDADE, secoes[i - 1]);
                case 21 -> new Secao(i, TESTESESPECIAIS, secoes[i - 1]);
                case 22 -> new Secao(i, OBSERVACOES, secoes[i - 1]);
                case 23 -> new Secao(i, DIAGNOSTICOFISIO, secoes[i - 1]);
                case 24 -> new Secao(i, OBJETIVOSTRATAMENTO, secoes[i - 1]);
                case 25 -> new Secao(i, PLANOTRATAMENTO, secoes[i - 1]);
                default -> null;
            };
            adiciona(aux);
        }
    }
}