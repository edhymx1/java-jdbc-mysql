package org.edgar.dev.jdbc.repositorio;

import org.edgar.dev.jdbc.modelo.Categoria;
import org.edgar.dev.jdbc.modelo.Producto;
import org.edgar.dev.jdbc.util.ConexionBaseDatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositorioImpl implements Repositorio<Producto> {
    private Connection getConnection() throws SQLException {
        return ConexionBaseDatos.getConnection();
    }

    @Override
    public List<Producto> listar() {
        List<Producto> productos = new ArrayList<>();

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            String sql = "SELECT p.*, c.nombre as categoria FROM productos p JOIN categorias c ON p.categoria_id = c.id";
            try (ResultSet result = statement.executeQuery(sql)) {
                while (result.next()) {
                    Producto producto = crearProducto(result);
                    productos.add(producto);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return productos;
    }

    @Override
    public Producto buscarPorId(Long id) {
        Producto producto = null;
        String sql = "SELECT p.*, c.nombre as categoria FROM productos p JOIN categorias c ON p.categoria_id = c.id WHERE p.id = ?";
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    producto = crearProducto(rs);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return producto;
    }

    @Override
    public void guardar(Producto producto) {
        String sql;
        if (producto.getId() != null && producto.getId() > 0) {
            sql = "UPDATE productos SET nombre = ?, precio = ?, categoria_id = ? WHERE id = ?";
        } else {
            sql = "INSERT INTO productos(nombre, precio, categoria_id, fecha_registro) VALUES(?,?,?,?)";
        }
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, producto.getNombre());
            statement.setInt(2, producto.getPrecio());
            statement.setLong(3, producto.getCategoria().getId());

            if (producto.getId() != null && producto.getId() > 0) {
                statement.setLong(4, producto.getId());
            } else {
                statement.setDate(4, new Date(producto.getFechaRegistro().getTime()));
                statement.setDate(4, new Date(producto.getFechaRegistro().getTime()));
            }

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Long id) {
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement("DELETE FROM productos WHERE id = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Producto crearProducto(ResultSet result) throws SQLException {
        Producto p = new Producto();
        p.setId(result.getLong("id"));
        p.setNombre(result.getString("nombre"));
        p.setPrecio(result.getInt("precio"));
        p.setFechaRegistro(result.getDate("fecha_registro"));
        Categoria categoria = new Categoria();
        categoria.setId(result.getLong("categoria_id"));
        categoria.setNombre(result.getString("categoria"));
        p.setCategoria(categoria);
        return p;
    }
}
