package parcial1.Services;

import java.util.LinkedList;
import java.util.Queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger; 

public class LibroOrdenes {

    private static final Logger logger = LogManager.getLogger(LibroOrdenes.class); 

    private static final Queue<String> ordenesCompra = new LinkedList<>();
    private static final Queue<String> ordenesVenta = new LinkedList<>();

    public static void agregarOrdenCompra(String orden) {
        ordenesCompra.add(orden);
        logger.info("Orden de compra agregada: {} | Total compras pendientes: {}", orden, ordenesCompra.size());
    }

    public static void agregarOrdenVenta(String orden) {
        ordenesVenta.add(orden);
        logger.info("Orden de venta agregada: {} | Total ventas pendientes: {}", orden, ordenesVenta.size());
    }

    public static void mostrarOrdenes() {
        logger.info("Libro de Órdenes del Mercado:");
        logger.info("Compras pendientes: {}", ordenesCompra);
        logger.info("Ventas pendientes: {}", ordenesVenta);
    }

    public static String procesarCompra() {
        String orden = ordenesCompra.poll();
        if (orden != null) {
            logger.info("Orden de compra procesada: {} | Compras restantes: {}", orden, ordenesCompra.size());
        } else {
            logger.warn("No hay órdenes de compra para procesar.");
        }
        return orden;
    }

    public static String procesarVenta() {
        String orden = ordenesVenta.poll();
        if (orden != null) {
            logger.info("Orden de venta procesada: {} | Ventas restantes: {}", orden, ordenesVenta.size());
        } else {
            logger.warn("No hay órdenes de venta para procesar.");
        }
        return orden;
    }
}

