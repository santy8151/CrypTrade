package parcial1.Model;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Transacciones {

private Queue<Usuario> fila = new LinkedList<>();
//Desencolar OrdenMercado
public void RegistrarTransacciones(List<Usuario> usuario){
 fila.addAll(usuario);


}

public void CompraOVenta(Usuario usuario){
   int numeroAL = (int)(Math.random()*((2 - 1)+ 1) + 1);

    if(numeroAL == 1){
        ComprarCriptomoneda(usuario);
    }

    if(numeroAL == 2){
        VenderCriptomoneda();
    }

}


private void ComprarCriptomoneda(Usuario usuario){
  
 int total = fila.size();
 int numeroAL = (int)(Math.random()*((100 - 1)+ 1) + 1);

 List<Usuario> lista = new LinkedList<>(fila);

    for (int i = 0; i < total; i++) {
       Usuario p1 = lista.get(i);

       if(numeroAL == Criptomoneda.getRank(i)){

       }
      
        System.out.println("Compra #" + (i + 1 ) + ": " + p1.getName() + " - "  + usuario.getName());
    }

 

}

private void VenderCriptomoneda(){
   

 

}


}
