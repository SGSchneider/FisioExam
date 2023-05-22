package br.ufsm.fisioexam.database.thread;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.ufsm.fisioexam.database.dao.GenericDAO;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class QueryManager<T> {

    public void insert(List<T> lista, GenericDAO<T> dao) {
        Observable<Boolean> queryManager = Observable.fromCallable(() -> {
            dao.insert(lista);
            return true;
        });
        queryManager.subscribeOn(Schedulers.newThread()).subscribe();
    }

    public void insert(T objeto, GenericDAO<T> dao) {
        Observable<Boolean> queryManager = Observable.fromCallable(() -> {
            dao.insert(objeto);
            return true;
        });
        queryManager.subscribeOn(Schedulers.newThread()).subscribe();
    }

    public void update(T objeto, GenericDAO<T> dao) {
        Observable<Boolean> queryManager = Observable.fromCallable(() -> {
            dao.update(objeto);
            return true;
        });

        queryManager.subscribeOn(Schedulers.newThread()).subscribe();
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
                Log.i("QueryManager", String.valueOf(resultado.size()));
            } else {
                resultado.addAll(dao.getAll());
                Log.i("QueryManager", String.valueOf(resultado.size()));
            }
            return resultado;
        });


        return queryManager.subscribeOn(Schedulers.io()).blockingSingle();
    }

    public void delete(T objeto, GenericDAO<T> dao) {
        Observable<Boolean> queryManager = Observable.fromCallable(() -> {
            dao.delete(objeto);
            return true;
        });

        queryManager.subscribeOn(Schedulers.newThread()).subscribe();
    }

    public void delete(List<T> objeto, GenericDAO<T> dao) {
        Observable<Boolean> queryManager = Observable.fromCallable(() -> {
            dao.delete(objeto);
            return true;
        });

        queryManager.subscribeOn(Schedulers.newThread()).subscribe();
    }


    public void deleteAll(GenericDAO<T> dao) {
        Observable<Boolean> queryManager = Observable.fromCallable(() -> {
            dao.deleteAll();
            return true;
        });

        queryManager.subscribeOn(Schedulers.newThread()).subscribe();
    }

    public T getOne(String search, GenericDAO<T> dao) {
        List<T> resultado = new ArrayList<>();
        Observable<T> queryManager = Observable.fromCallable(() -> {
            resultado.clear();
            if (search != null) {
                resultado.add(dao.getOne(search));
                Log.i("QueryManager", String.valueOf(resultado.size()));
            }
            return resultado.get(0);
        });

        return queryManager.subscribeOn(Schedulers.io()).blockingSingle();
    }

    public String getIdByForeign(String search, GenericDAO<T> dao) {
        List<String> resultado = new ArrayList<>();
        Observable<String> queryManager = Observable.fromCallable(() -> {
            resultado.clear();
            if (search != null) {
                resultado.add(dao.getIdByForeign(search));
                Log.i("QueryManager", String.valueOf(resultado.size()));
            }
            return resultado.get(0);
        });

        return queryManager.subscribeOn(Schedulers.io()).blockingSingle();
    }

    public String getIdNovoExame(String reg, String creationKey, GenericDAO<T> dao) {
        List<String> resultado = new ArrayList<>();
        Observable<String> queryManager = Observable.fromCallable(() -> {
            resultado.clear();
            if (reg != null && creationKey != null) {
                resultado.add(dao.getIdNovoExame(reg, creationKey));
                Log.i("QueryManager", String.valueOf(resultado.size()));
            }
            return resultado.get(0);
        });

        return queryManager.subscribeOn(Schedulers.io()).blockingSingle();
    }


    public int countSize(GenericDAO<T> dao) {
        Observable<Integer> queryManager = Observable.fromCallable(dao::countSize);
        return queryManager.subscribeOn(Schedulers.io()).blockingSingle();
    }

}