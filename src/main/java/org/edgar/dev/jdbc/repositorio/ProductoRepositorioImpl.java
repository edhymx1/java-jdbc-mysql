package org.edgar.dev.jdbc.repositorio;

import org.edgar.dev.jdbc.modelo.Producto;
import org.edgar.dev.jdbc.util.ConexionBaseDatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositorioImpl implements Repositorio<Producto> {
    private Connection getConnection() throws SQLException {
        return ConexionBaseDatos.getInstance();
    }

    @Override
    public List<Producto> listar() {
        List<Producto> productos = new ArrayList<>();

        try (Statement statement = getConnection().createStatement()) {
            try (ResultSet result = statement.executeQuery("SELECT * FROM productos")) {
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
        try (PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM productos WHERE id = ?")) {
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
            sql = "UPDATE productos SET nombre = ?, precio = ? WHERE id = ?";
        } else {
            sql = "INSERT INTO productos(nombre, precio, fecha_registro) VALUES(?,?,?)";
        }
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, producto.getNombre());
            statement.setInt(2, producto.getPrecio());

            if (producto.getId() != null && producto.getId() > 0) {
                statement.setLong(3, producto.getId());
            } else {
                statement.setDate(3, new Date(producto.getFechaRegistro().getTime()));
            }

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Long id) {
        try (PreparedStatement statement = getConnection().prepareStatement("DELETE FROM productos WHERE id = ?")) {
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
        return p;
    }
}
