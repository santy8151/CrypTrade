package parcial1.Services;

import java.util.*;


public class SimulacionMercado {
    private final Map<String, List<Double>> historialPrecios = new HashMap<>();
    private final Random rnd = new Random();
    private final String divisaBase; // ej. "USD" o "COP" (informativa)

    public SimulacionMercado(String divisaBase) {
        this.divisaBase = divisaBase;
    }

 
    public void registrarPrecioInicial(String nombreCripto, double precio) {
        historialPrecios.putIfAbsent(nombreCripto, new ArrayList<>());
        historialPrecios.get(nombreCripto).add(precio);
    }

 
    public double obtenerPrecioActual(String nombreCripto) {
        List<Double> h = historialPrecios.get(nombreCripto);
        if (h == null || h.isEmpty()) return 0.0;
        return h.get(h.size() - 1);
    }


    public double aplicarFluctuacionPorTransaccion(String nombreCripto) {
        double actual = obtenerPrecioActual(nombreCripto);
        if (actual <= 0) {
       
            return actual;
        }
        double delta = (rnd.nextDouble() * 0.10) - 0.05; // [-0.05, +0.05]
        double nuevo = actual * (1.0 + delta);

   
        nuevo = Math.round(nuevo * 100000.0) / 100000.0;

        historialPrecios.get(nombreCripto).add(nuevo);
        return nuevo;
    }

 
    public List<Double> obtenerHistorial(String nombreCripto) {
        List<Double> h = historialPrecios.get(nombreCripto);
        if (h == null) return Collections.emptyList();
        return Collections.unmodifiableList(h);
    }

 
    public void forzarPrecio(String nombreCripto, double precio) {
        historialPrecios.putIfAbsent(nombreCripto, new ArrayList<>());
        historialPrecios.get(nombreCripto).add(precio);
    }

    public String getDivisaBase() {
        return divisaBase;
    }
}
