package org.edgar.dev.jdbc.ejemplos;

import org.edgar.dev.jdbc.util.ConexionBaseDatos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejemplo1 {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void ejecutar() {
        try (Connection conn = ConexionBaseDatos.getInstance();
             Statement statement = conn.createStatement();
             ResultSet resultado = statement.executeQuery("SELECT * FROM productos")) {
            while (resultado.next()) {
                this.logger.info("id: {}, nombre: {}, precio: {}, fecha_registro: {}",
                        resultado.getInt("id"),
                        resultado.getString("nombre"),
                        resultado.getInt("precio"),
                        resultado.getDate("fecha_registro"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
