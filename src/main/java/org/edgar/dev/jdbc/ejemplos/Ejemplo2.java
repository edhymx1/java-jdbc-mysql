package org.edgar.dev.jdbc.ejemplos;

import org.edgar.dev.jdbc.modelo.Producto;
import org.edgar.dev.jdbc.repositorio.ProductoRepositorioImpl;
import org.edgar.dev.jdbc.repositorio.Repositorio;
import org.edgar.dev.jdbc.util.Base;

public class Ejemplo2 extends Base {
    Repositorio<Producto> repositorio = new ProductoRepositorioImpl();

    public void listar() {
        getLogger().info("==== Listando productos ====");
        repositorio.listar().forEach(p -> getLogger().info("{}", p));
        getLogger().info("==== Fin Listando productos ====\n\n");
    }

    public void buscarPorId(Long id) {
        getLogger().info("==== Buscando producto ====");
        getLogger().info("id: {}", id);
        getLogger().info("{}", repositorio.buscarPorId(id));
        getLogger().info("==== FIn Buscando producto ====\n\n");
    }

    public void crear(Producto producto) {
        getLogger().info("==== Guardando producto ====");
        getLogger().info("{}", producto);
        repositorio.guardar(producto);
        getLogger().info("==== Fin Guardando producto ====\n\n");
    }

    public void actualizar(Producto producto) {
        getLogger().info("==== Actualizando producto ====");
        getLogger().info("{}", producto);
        repositorio.guardar(producto);
        getLogger().info("==== Fin Actualizando producto ====\n\n");
    }

    public void eliminar(Long id) {
        getLogger().info("==== Eliminando producto ====");
        getLogger().info("id: {}", id);
        repositorio.eliminar(id);
        getLogger().info("==== Fin Eliminando producto ====\n\n");
    }
}
