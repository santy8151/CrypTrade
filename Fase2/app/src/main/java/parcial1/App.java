package parcial1;


import java.util.*;

import parcial1.Model.Criptomoneda;
import parcial1.Model.Transacciones;
import parcial1.Model.Usuario;
import parcial1.Services.Api;
import parcial1.Services.Reporte;
import parcial1.Services.SimulacionMercado;


public class App {
    public static void main(String[] args) throws InterruptedException {
        Api api = new Api(); // tu Api existente
     
        List<Criptomoneda> monedas = api.getCriptomonedas(); 

      
        SimulacionMercado mercado = new SimulacionMercado("USD");

        for (Criptomoneda c : monedas) {
        
            String nombre = c.getName();
            double precio = Double.parseDouble(c.getPrice_usd());
            mercado.registrarPrecioInicial(nombre, precio);
        }


        Usuario usuario = new Usuario(1, "Pepe", 10000000);
        Transacciones transacciones = new Transacciones(usuario, monedas);
        usuario.limpiarPortafolio();    
        Random rnd = new Random();
        int rondas = 10; 
      
        for (int t = 1; t <= rondas; t++) {
          
           
            Criptomoneda elegido = monedas.get(rnd.nextInt(monedas.size()));
            String nombre = elegido.getName();
            double precioAntes = mercado.obtenerPrecioActual(nombre);
         
            transacciones.realizarTransaccionAleatoria(usuario);


            double precioDespues = mercado.aplicarFluctuacionPorTransaccion(nombre);

            System.out.printf(" Precio antes: %.5f -> Precio despues: %.5f\n", precioAntes, precioDespues);

            Thread.sleep(500); 
        } 
        // Generar reporte final
        Reporte.generarReporte(usuario, usuario.getTransaccionHistorial());
       
         
        System.out.println(usuario.getPortafolio());

        System.out.println(usuario.getTransaccionHistorial());
    }
}
