package org.edgar.dev.jdbc.ejemplos;

import org.edgar.dev.jdbc.modelo.Producto;
import org.edgar.dev.jdbc.repositorio.ProductoRepositorioImpl;
import org.edgar.dev.jdbc.repositorio.Repositorio;
import org.edgar.dev.jdbc.util.Base;

public class Ejemplo2 extends Base {
    Repositorio<Producto> repositorio = new ProductoRepositorioImpl();

    public void listar() {
        getLogger().info("Listando productos");
        repositorio.listar().forEach(p -> getLogger().info("{}", p));
        getLogger().info("\n\n\n");
    }

    public void buscarPorId(Long id) {
        getLogger().info("Buscando producto");
        getLogger().info("{}", repositorio.buscarPorId(id));
        getLogger().info("\n\n\n");
    }

    public void crear(Producto producto) {
        getLogger().info("Guardando producto");
        repositorio.guardar(producto);
        getLogger().info("Producto guardado correctamente");
        getLogger().info("\n\n\n");
    }

    public void actualizar(Producto producto) {
        getLogger().info("Actualizando producto");
        repositorio.guardar(producto);
        getLogger().info("Producto actualizado correctamente");
        getLogger().info("\n\n\n");
    }

    public void eliminar(Long id) {
        getLogger().info("Eliminando producto");
        repositorio.eliminar(id);
        getLogger().info("Producto eliminado");
        getLogger().info("\n\n\n");
    }
}
