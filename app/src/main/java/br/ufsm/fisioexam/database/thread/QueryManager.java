package br.ufsm.fisioexam.database.thread;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import br.ufsm.fisioexam.database.dao.GenericDAO;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class QueryManager<T> {

    public Boolean insert(List<T> lista, GenericDAO<T> dao) {
        Observable<Boolean> queryManager = Observable.fromCallable(() -> {
            dao.insert(lista);
            Log.i("QueryManager", "List Insert: Inserido com Sucesso");
            return true;
        });
        return(queryManager.subscribeOn(Schedulers.io()).blockingSingle());
    }

    public Boolean insert(T objeto, GenericDAO<T> dao) {
        Observable<Boolean> queryManager = Observable.fromCallable(() -> {
            dao.insert(objeto);
            Log.i("QueryManager", "Insert: Inserido com Sucesso");
            return true;
        });
        return(queryManager.subscribeOn(Schedulers.io()).blockingSingle());
    }

    public void update(T objeto, GenericDAO<T> dao) {
        Observable<Boolean> queryManager = Observable.fromCallable(() -> {
            dao.update(objeto);
            Log.i("QueryManager", "Update: Atualizado com Sucesso");
            return true;
        });

        queryManager.subscribeOn(Schedulers.io()).subscribe();
    }

    public boolean checkID(String id, GenericDAO<T> dao) {
        Observable<Boolean> queryManager = Observable.fromCallable(() -> {
            Boolean result = dao.CheckID(id);
            Log.i("QueryManager", result.toString());
            return result;
        });

        return queryManager.subscribeOn(Schedulers.io()).blockingSingle();
    }

    public List<T> atualizaLista(String search, GenericDAO<T> dao) {
        List<T> resultado = new ArrayList<>();
        Observable<List<T>> queryManager = Observable.fromCallable(() -> {
            resultado.clear();
            if (search != null) {
                resultado.addAll(dao.search(search));
                Log.i("QueryManager", "Pesquisa: Resultou em " + resultado.size() + " linhas para retornar");
            } else {
                resultado.addAll(dao.getAll());
                Log.i("QueryManager", "Get All: Resultou em " + resultado.size() + " linhas para retornar");
            }
            return resultado;
        });


        return queryManager.subscribeOn(Schedulers.io()).blockingSingle();
    }

    public void delete(T objeto, GenericDAO<T> dao) {
        Observable<Boolean> queryManager = Observable.fromCallable(() -> {
            dao.delete(objeto);
            Log.i("QueryManager", "Delete: Deletado com Sucesso");
            return true;
        });

        queryManager.subscribeOn(Schedulers.io()).subscribe();
    }

    public void delete(List<T> objeto, GenericDAO<T> dao) {
        Observable<Boolean> queryManager = Observable.fromCallable(() -> {
            dao.delete(objeto);
            Log.i("QueryManager", "List Delete: Deletado com Sucesso");
            return true;
        });

        queryManager.subscribeOn(Schedulers.io()).subscribe();
    }


    public void deleteAll(GenericDAO<T> dao) {
        Observable<Boolean> queryManager = Observable.fromCallable(() -> {
            dao.deleteAll();
            Log.i("QueryManager", "Delete All: Deletado com Sucesso");
            return true;
        });

        queryManager.subscribeOn(Schedulers.io()).subscribe();
    }

    public T getOne(@NonNull String search, GenericDAO<T> dao) {
        List<T> resultado = new ArrayList<>();
        Observable<T> queryManager = Observable.fromCallable(() -> {
            resultado.clear();
            resultado.add(dao.getOne(search));
            Log.i("QueryManager", "Get One: Resultou em " + resultado.size() + " linhas para retornar");
            return resultado.get(0);
        });

        return queryManager.subscribeOn(Schedulers.io()).blockingSingle();
    }

    public String getIdByForeign(@NonNull String search, GenericDAO<T> dao) {

        Observable<String> queryManager = Observable.fromCallable(() -> dao.getIdByForeign(search));

        return queryManager.subscribeOn(Schedulers.io()).blockingSingle();
    }

    public String getIdNovoExame(@NonNull String reg, @NonNull String creationKey, GenericDAO<T> dao) {
        List<String> resultado = new ArrayList<>();
        Observable<String> queryManager = Observable.fromCallable(() -> {
            resultado.clear();
            resultado.add(dao.getIdNovoExame(reg, creationKey));
            Log.i("QueryManager", "Id novo Exame: Resultou em " + resultado.size() + " linhas para retornar");
            return resultado.get(0);
        });

        return queryManager.subscribeOn(Schedulers.io()).blockingSingle();
    }


    public int countSize(GenericDAO<T> dao) {
        Observable<Integer> queryManager = Observable.fromCallable(dao::countSize);
        return queryManager.subscribeOn(Schedulers.io()).blockingSingle();
    }

    public List<T> getAll(GenericDAO<T> dao) {
        return atualizaLista(null, dao);
    }
}