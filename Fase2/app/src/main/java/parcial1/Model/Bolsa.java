package parcial1.Model;

import java.util.*;

/**
 * Clase genérica que representa una bolsa de elementos (multiset).
 * Almacena la cantidad de cada elemento usando un Map.
 * 
 * @param <T> Tipo de elemento que se almacenará en la bolsa.
 */
public class Bolsa<T> {

    private Map<T, Integer> contenido = new HashMap<>();
    private Random random = new Random();

    /**
     * Agrega un elemento a la bolsa.
     */
    public void agregar(T item) {
        contenido.put(item, contenido.getOrDefault(item, 0) + 1);
    }

    /**
     * Remueve una unidad del elemento de la bolsa.
     */
    public boolean remover(T item) {
        if (contenido.containsKey(item) && contenido.get(item) > 0) {
            int cantidad = contenido.get(item);
            if (cantidad == 1) {
                contenido.remove(item);
            } else {
                contenido.put(item, cantidad - 1);
            }
            return true;
        }
        return false;
    }

    /**
     * Verifica si la bolsa contiene al menos una unidad del elemento.
     */
    public boolean contiene(T item) {
        return contenido.containsKey(item) && contenido.get(item) > 0;
    }

    /**
     * Devuelve el contenido completo de la bolsa.
     */
    public Map<T, Integer> getContenido() {
        return Collections.unmodifiableMap(contenido);
    }

    /**
     * Calcula el valor total en USD si el contenido es de Criptomonedas.
     */
    public double valorTotalUSD() {
        double total = 0;
        for (T item : contenido.keySet()) {
            if (item instanceof Criptomoneda c) {
                try {
                    total += Double.parseDouble(c.getPrice_usd()) * contenido.get(item);
                } catch (NumberFormatException e) {
                    // Ignorar precios no numéricos
                }
            }
        }
        return total;
    }

    /**
     * Obtiene un elemento aleatorio de la bolsa (basado en la cantidad).
     */
    public T getRandom() {
        if (contenido.isEmpty()) return null;

        List<T> listaExpandida = new ArrayList<>();
        for (Map.Entry<T, Integer> entry : contenido.entrySet()) {
            listaExpandida.addAll(Collections.nCopies(entry.getValue(), entry.getKey()));
        }

        return listaExpandida.get(random.nextInt(listaExpandida.size()));
    }

    /**
     * Verifica si la bolsa está vacía.
     */
    public boolean isEmpty() {
        return contenido.isEmpty();
    }

    /**
     * Método alternativo a agregar(T) que permite cumplir con
     * una interfaz más genérica.
     */
    public void add(T item) {
        agregar(item);
    }
}

