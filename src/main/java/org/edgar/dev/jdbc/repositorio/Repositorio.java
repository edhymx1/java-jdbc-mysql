package org.edgar.dev.jdbc.repositorio;

import java.util.List;

public interface Repositorio<T> {
    List<T> listar();

    T buscarPorId(Long id);

    void guardar(T t);

    void eliminar(Long id);

}
