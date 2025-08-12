package parcial1.Services;


import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.LogManager;


public class OrdenMercado {
    
    private Queue<OrdenMercado> fila = new LinkedList<>();

    public void registrarUsuarios(List<OrdenMercado> Usuario) {
        
        fila.addAll(Usuario);
    }

    public void OrdenesComprayVenta() {
        System.out.println("\n===Generar compra y venta===");
        List<OrdenMercado> list = new LinkedList<>(fila);
    }
}


