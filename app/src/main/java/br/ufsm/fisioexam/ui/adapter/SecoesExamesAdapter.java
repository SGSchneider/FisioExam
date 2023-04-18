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
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;

import br.ufsm.fisioexam.R;
import br.ufsm.fisioexam.model.Secao;
import br.ufsm.fisioexam.model.Secoes;

public class SecoesExamesAdapter extends BaseAdapter {
    private final List<Secao> secoes = new ArrayList<>();
    private final Context context;

    public SecoesExamesAdapter(Context context) {
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
        TextView nome = view.findViewById(R.id.item_form_secao);
        nome.setText(secao.getNome());
        ShapeableImageView preenchido = view.findViewById(R.id.item_form_preenchido);
        if (secao.isPreenchida()) {
            preenchido.setBackground(new ColorDrawable(0xFF00AA00));
            preenchido.setImageResource(R.drawable.ic_action_done);
        } else {
            preenchido.setBackground(new ColorDrawable(0xFFAA0000));
            preenchido.setImageResource(R.drawable.ic_action_not_done);
        }
    }

    private View criaView(ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_form_exame, parent, false);
    }

    public void atualiza(Secoes secoes) {
        this.secoes.clear();
        criaLista(secoes);
    }

    public void adiciona(Secao secao) {
        secoes.add(secao);
        notifyDataSetChanged();
    }

    public void criaLista(Secoes secoes) {
        for (int i = 1; i < 26; i++) {
            Secao aux;
            switch (i) {
                case 1:
                    aux = new Secao(i, DIAGNOSTICOMEDICO, secoes.isDiagnosticoMedico());
                    break;
                case 2:
                    aux = new Secao(i, QUEIXAPRINCIPAL, secoes.isQueixaPrincipal());
                    break;
                case 3:
                    aux = new Secao(i, HISTORIADOENCAATUAL, secoes.isHistoriaDoencaAtual());
                    break;
                case 4:
                    aux = new Secao(i, DOR, secoes.isDor());
                    break;
                case 5:
                    aux = new Secao(i, TRATAMENTOANTERIOR, secoes.isTratamentoAnterior());
                    break;
                case 6:
                    aux = new Secao(i, AFASTAMENTODAFUNCAO, secoes.isAfastamentoDaFuncao());
                    break;
                case 7:
                    aux = new Secao(i, ANTECEDENTESPESSOAIS, secoes.isAntecedentesPessoais());
                    break;
                case 8:
                    aux = new Secao(i, DOENCASASSOCIADAS, secoes.isDoencasAssociadas());
                    break;
                case 9:
                    aux = new Secao(i, MEDICAMENTOSEMUSO, secoes.isMedicamentosEmUso());
                    break;
                case 10:
                    aux = new Secao(i, HISTORIAFAMILIAR, secoes.isHistoriaFamiliar());
                    break;
                case 11:
                    aux = new Secao(i, HISTORIAOCUPACIONAL, secoes.isHistoriaOcupacional());
                    break;
                case 12:
                    aux = new Secao(i, HISTORIASOCIAL, secoes.isHistoriaSocial());
                    break;
                case 13:
                    aux = new Secao(i, EXAMESCOMPLEMENTARES, secoes.isExamesComplementares());
                    break;
                case 14:
                    aux = new Secao(i, SINAISVITAIS, secoes.isSinaisVitais());
                    break;
                case 15:
                    aux = new Secao(i, INSPECAO, secoes.isInspecao());
                    break;
                case 16:
                    aux = new Secao(i, PALPACAO, secoes.isPalpacao());
                    break;
                case 17:
                    aux = new Secao(i, AMPLITUDEMOVIMENTO, secoes.isAmplitudeMovimento());
                    break;
                case 18:
                    aux = new Secao(i, PERIMETRIA, secoes.isPerimetria());
                    break;
                case 19:
                    aux = new Secao(i, FORCAMUSCULAR, secoes.isForcaMuscular());
                    break;
                case 20:
                    aux = new Secao(i, SENSIBILIDADE, secoes.isSensibilidade());
                    break;
                case 21:
                    aux = new Secao(i, TESTESESPECIAIS, secoes.isTestesEspeciais());
                    break;
                case 22:
                    aux = new Secao(i, OBSERVACOES, secoes.isObservacoes());
                    break;
                case 23:
                    aux = new Secao(i, DIAGNOSTICOFISIO, secoes.isDiagnosticoFisio());
                    break;
                case 24:
                    aux = new Secao(i, OBJETIVOSTRATAMENTO, secoes.isObjetivosTratamento());
                    break;
                case 25:
                    aux = new Secao(i, PLANOTRATAMENTO, secoes.isPlanoTratamento());
                    break;
                default:
                    aux = null;
            }
            adiciona(aux);
        }
    }
}