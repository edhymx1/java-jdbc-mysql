package org.edgar.dev.jdbc.ejemplos;

import org.edgar.dev.jdbc.modelo.Producto;
import org.edgar.dev.jdbc.repositorio.ProductoRepositorioImpl;
import org.edgar.dev.jdbc.repositorio.Repositorio;
import org.edgar.dev.jdbc.util.Base;

public class Ejemplo2 extends Base {
    Repositorio<Producto> repositorio = new ProductoRepositorioImpl();

    public void listar() {
        repositorio.listar().forEach(p -> getLogger().info("{}", p));
    }

    public void buscarPorId(Long id) {
        getLogger().info("{}", repositorio.buscarPorId(id));
    }

    public void crear(Producto producto) {
        getLogger().info("Guardando producto");
        repositorio.guardar(producto);
        getLogger().info("Producto guardado correctamente");
    }

    public void actualizar(Producto producto) {
        getLogger().info("Actualizando producto");
        repositorio.guardar(producto);
        getLogger().info("Producto actualizado correctamente");
    }

    public void eliminar(Long id) {
        getLogger().info("Eliminando producto");
        repositorio.eliminar(id);
        getLogger().info("Producto eliminado");
    }
}
