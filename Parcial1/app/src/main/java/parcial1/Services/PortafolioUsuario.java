package parcial1.Services;

import java.util.ArrayList;
import java.util.List;

import parcial1.Model.Criptomoneda;



public class PortafolioUsuario {


 private List<String> bag = new ArrayList<>();

    public void MostrarCartera() {
        System.out.println("\n=== Portafolio de criptomonedas===");
        for (String criptomoneda : bag){
            System.out.println(criptomoneda);
        }
            
       
        System.out.println("Saldo total en cartera:" + bag.size());
    }
 }
