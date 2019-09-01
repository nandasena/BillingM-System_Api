package com.createvision.sivilima.dao;
import java.io.Serializable;
import java.util.List;
public interface AbstractDao <T extends Serializable, PK> {
    Long save(final T object);

    T get(PK id);

    void update(T object);

    void delete(T object);

    List<T> getAll();


}


