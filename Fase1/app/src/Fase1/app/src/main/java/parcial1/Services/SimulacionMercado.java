package parcial1.Services;

import parcial1.Model.Criptomoneda;
import parcial1.Model.Usuario;
import parcial1.Model.Transacciones;

import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Random;

public class SimulacionMercado extends Transacciones {

    private double precioCripto; // precio actual de la criptomoneda en USD
    private double tasaCOP;      // tasa de cambio USD -> COP
    private Random random;

    // Libro de órdenes local
    private Queue<String> libroOrdenes = new LinkedList<>();

    public SimulacionMercado(Usuario usuario, List<Criptomoneda> criptomonedas, double precioInicial, double tasaCOP) {
        super(usuario, criptomonedas); // constructor padre
        this.precioCripto = precioInicial;
        this.tasaCOP = tasaCOP;
        this.random = new Random();
    }

    // Simula la fluctuación del precio (+/- 5%)
    public void criptomonedaFluctuando() {
        double cambioPorcentual = (random.nextDouble() * 10 - 5) / 100;
        precioCripto += precioCripto * cambioPorcentual;
        System.out.printf("Precio Fluctuando: %.2f USD%n", precioCripto);
    }

    // Convierte una cantidad de criptomoneda a pesos colombianos usando el precio actual
    public double conversionACOP(double cantidadCripto) {
        double valorUSD = cantidadCripto * precioCripto;
        return valorUSD * tasaCOP;
    }

    // Elegir criptomoneda aleatoria del mercado
    public Criptomoneda elegirCriptomoneda() {
        return portafolio.get(random.nextInt(portafolio.size()));
    }

    // Transacción aleatoria (compra y venta)
    public void transaccionAleatoria() {
        boolean esCompraPrimero = random.nextBoolean();

        if (esCompraPrimero) {
            ComprarCriptomoneda();  // método heredado
            VenderCriptomoneda();   // método heredado
        } else {
            VenderCriptomoneda();
            ComprarCriptomoneda();
        }
    }

    // Ejecutar N rondas de simulación
    public void transaccionesRondas(int rondas) {
        for (int i = 0; i < rondas; i++) {
            criptomonedaFluctuando();
            transaccionAleatoria();
            System.out.println("---------------------");
        }
    }

    // Encolar órdenes en libro local
    public void encolarOrden(String orden) {
        libroOrdenes.offer(orden);
    }

    // Procesar hasta 2 órdenes de la cola
    public void procesarTransacciones() {
        System.out.println("\nProcesando órdenes...");
        for (int i = 0; i < 2 && !libroOrdenes.isEmpty(); i++) {
            System.out.println("Ejecutada: " + libroOrdenes.poll());
        }
    }
}





