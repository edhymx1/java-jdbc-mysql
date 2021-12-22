package org.edgar.dev.jdbc;

import org.edgar.dev.jdbc.ejemplos.Ejemplo2;
import org.edgar.dev.jdbc.modelo.Producto;

import java.util.Date;

class EjemploJdbc {
    public static void main(String[] args) {
        Ejemplo2 ejemplo2 = new Ejemplo2();
        ejemplo2.listar();
        ejemplo2.buscarPorId(3l);
        ejemplo2.crear(new Producto(null, "Poco X3 PRO MAX", 8900, new Date()));
        ejemplo2.listar();
        ejemplo2.actualizar(new Producto(3l, "Y9 LITE", 6500, new Date()));
        ejemplo2.listar();
        ejemplo2.eliminar(3l);
        ejemplo2.listar();
    }
}
