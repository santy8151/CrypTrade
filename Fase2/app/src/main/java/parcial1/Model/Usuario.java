package parcial1.Model;

import java.util.ArrayList;
import java.util.List;



public class Usuario {
  private int id;
  private String nombre;
  private int saldo;
  List<Transacciones> bolsa = new ArrayList<>();
  List<Criptomoneda> portafolio = new ArrayList<>();

public Usuario(int id, String nombre, int saldo){
    this.id = id;
    this.nombre = nombre;
    this.saldo = saldo;
    this.bolsa = new ArrayList<>();

}

public int getSaldo(){
    return saldo;
}

public String getName(){
    return nombre;
}


public void setSaldo(int nuevoSaldo) {
    this.saldo = nuevoSaldo;
}

public List<Criptomoneda> getPortafolio() {
    return this.portafolio;
}

public void agregarAlPortafolio(Criptomoneda c) {
    if (c != null) {
        this.portafolio.add(c);
    }
}


public boolean removerDelPortafolio(Criptomoneda c) {
    if (c == null) return false;
    return this.portafolio.remove(c);
}
//get portafolio
public void limpiarPortafolio() {
    this.portafolio.clear();
}


//Muestra la cantidad de criptomonedas
 public void PortafolioUsuario(Criptomoneda criptomoneda) throws IllegalAccessException{
    if(criptomoneda == null){
        throw new IllegalAccessException("Cantidad de criptomonedas");
    }
    portafolio.size();
    }

//Hacer una trasaccion
public void agregarTransaccionHistorial(Transacciones transaccion, Usuario usuario){
        if (transaccion == null) {
            throw new IllegalArgumentException("La Transaccion no puede ser nula");
        }
        bolsa.add(transaccion);
    }

 public List<Transacciones> getTransaccionHistorial(){
    return bolsa;
 }
    


}