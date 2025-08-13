package parcial1.Services;

import java.util.LinkedList;
import java.util.Queue;

public class LibroOrdenes {

    private static final Queue<String> ordenesCompra = new LinkedList<>();
    private static final Queue<String> ordenesVenta = new LinkedList<>();


    public static void agregarOrdenCompra(String orden) {
        ordenesCompra.add(orden);
    }

    public static void agregarOrdenVenta(String orden) {
        ordenesVenta.add(orden);
    }

    public static void mostrarOrdenes() {
        System.out.println("Libro de Órdenes del Mercado:");
        System.out.println("Compras pendientes: " + ordenesCompra);
        System.out.println("Ventas pendientes: " + ordenesVenta);
    }

    public static String procesarCompra() {
        return ordenesCompra.poll();
    }

    public static String procesarVenta() {
        return ordenesVenta.poll();
    }
}
