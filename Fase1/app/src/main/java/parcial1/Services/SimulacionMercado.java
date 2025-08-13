package parcial1.Services;

import parcial1.Model.Criptomoneda;
import java.util.*;

public class SimulacionMercado {
    private double precioCripto; // precio actual de la criptomoneda en USD
    private double tasaCOP;      // tasa de cambio USD -> COP
    private Random random;

    // Lista de criptos
    private List<Criptomoneda> criptomonedas;

    // Libro de órdenes
    private Queue<String> libroOrdenes = new LinkedList<>();

    public SimulacionMercado(double precioInicial, double tasaCOP, List<Criptomoneda> criptomonedas) {
        this.precioCripto = precioInicial;
        this.tasaCOP = tasaCOP;
        this.criptomonedas = criptomonedas;
        this.random = new Random();
    }

    // Simula la fluctuación del precio (+/- 5%)
    public void criptomonedaFluctuando() {
        double cambioPorcentual = (random.nextDouble() * 10 - 5) / 100;
        precioCripto += precioCripto * cambioPorcentual;
        System.out.printf("Precio Fluctuando: %.2f USD%n", precioCripto);
    }

    // Convierte una cantidad de criptomoneda de ValosUSD a pesos colombianos
    public double conversionACOP(double cantidadCripto) {
        double valorUSD = cantidadCripto * precioCripto;
        return valorUSD * tasaCOP;
    }

    // Elegir criptomoneda aleatoria del portafolio
    public Criptomoneda elegirCriptomoneda() {
        return criptomonedas.get(random.nextInt(criptomonedas.size()));
    }

    // Compra aleatoria
    public void compraAleatoria() {
        Criptomoneda cripto = elegirCriptomoneda();
        double cantidad = 0.01 + (0.09 * random.nextDouble());
        double valorCOP = conversionACOP(cantidad);
        String orden = String.format("COMPRA: %.4f %s (%.2f COP)", cantidad, cripto.getName(), valorCOP);
        libroOrdenes.add(orden);
        System.out.println(orden);
    }

    // Venta aleatoria
    public void ventaAleatoria() {
        Criptomoneda cripto = elegirCriptomoneda();
        double cantidad = 0.01 + (0.09 * random.nextDouble());
        double valorCOP = conversionACOP(cantidad);
        String orden = String.format("VENTA: %.4f %s (%.2f COP)", cantidad, cripto.getName(), valorCOP);
        libroOrdenes.add(orden);
        System.out.println(orden);
    }

    // Elegir aleatoriamente si es venta o compra y luego hacer la operacion
    public void transaccionAleatoria() {
        boolean esCompraPrimero = random.nextBoolean();

        if (esCompraPrimero) {
            compraAleatoria();
            ventaAleatoria();
        } else {
            ventaAleatoria();
            compraAleatoria();
        }
    }

    // Ejecuta N rondas de compra/venta
    public void transaccionesRondas(int rondas) {
        for (int i = 0; i < rondas; i++) {
            criptomonedaFluctuando();
            transaccionAleatoria();
            System.out.println("---------------------");
        }
    }

    //Se encola en el libro de órdenes
    public void encolarOrden(String orden) {
        libroOrdenes.add(orden);
    }
    // Procesar mas de dos ordenes de la cola
    public void procesarOrdenes() {
        System.out.println("\n Procesando órdenes...");
        for (int i = 0; i < 2 && !libroOrdenes.isEmpty(); i++) {
            System.out.println(" Ejecutada: " + libroOrdenes.poll());
        }
    }




}



