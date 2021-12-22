package org.edgar.dev.jdbc;

import org.edgar.dev.jdbc.ejemplos.Ejemplo2;
import org.edgar.dev.jdbc.modelo.Categoria;
import org.edgar.dev.jdbc.modelo.Producto;
import org.edgar.dev.jdbc.util.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

class EjemploJdbc {
    public static void main(String[] args) {
        try (Connection connection = ConexionBaseDatos.getInstance()) {
            Ejemplo2 ejemplo2 = new Ejemplo2();
            ejemplo2.listar();

            Producto producto = new Producto();
            producto.setNombre("Logitech G502");
            producto.setPrecio(1200);
            producto.setFechaRegistro(new Date());
            Categoria categoria = new Categoria();
            categoria.setId(3L);
            producto.setCategoria(categoria);
            ejemplo2.crear(producto);

            ejemplo2.listar();

            ejemplo2.buscarPorId(4L);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
