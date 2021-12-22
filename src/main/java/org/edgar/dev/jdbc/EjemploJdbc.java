package org.edgar.dev.jdbc;

import org.edgar.dev.jdbc.ejemplos.Ejemplo2;
import org.edgar.dev.jdbc.modelo.Producto;

import java.util.Date;

class EjemploJdbc {
    public static void main(String[] args) {
        Ejemplo2 ejemplo2 = new Ejemplo2();
        ejemplo2.listar();
        ejemplo2.buscarPorId(1l);
        ejemplo2.crear(new Producto(null, "Poco X3 PRO", 5600, new Date()));
        ejemplo2.listar();
        ejemplo2.actualizar(new Producto(1l, "J7 MAX", 3000, new Date()));
        ejemplo2.listar();
        ejemplo2.eliminar(1l);
        ejemplo2.listar();

    }
}
