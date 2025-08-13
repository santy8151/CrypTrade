package parcial1.Model;

import java.util.List;
import java.util.Random;

import parcial1.Services.LibroOrdenes;

public class Transacciones {

    private Usuario usuario;
    private List<Criptomoneda> mercado;
    private Random random;

    public Transacciones(Usuario usuario, List<Criptomoneda> mercado) {
        if (usuario == null || mercado == null) {
            throw new IllegalArgumentException("Usuario y mercado no pueden ser nulos");
        }
        this.usuario = usuario;
        this.mercado = mercado;
        this.random = new Random();
    }

    public void realizarTransaccionAleatoria() {
        if (mercado.isEmpty()) {
            System.out.println("No hay criptomonedas disponibles.");
            return;
        }

        int numeroAL = (int)(Math.random()*(2) + 1);

    if(numeroAL == 1){
        ComprarCriptomoneda();
    }

    if(numeroAL == 2){
        VenderCriptomoneda();
    }
    }

private void ComprarCriptomoneda() {
    int idx = random.nextInt(mercado.size());
    Criptomoneda seleccion = mercado.get(idx);

    double precioCOP = parsePrecioCOP(seleccion);
    int saldoActual = usuario.getSaldo();

    if (saldoActual >= precioCOP && precioCOP > 0) {
        usuario.setSaldo((int) (saldoActual - precioCOP));
        usuario.agregarAlPortafolio(seleccion);

        // Registrar en el libro de órdenes
        LibroOrdenes.agregarOrdenCompra(usuario.getName() +
                " compra " + seleccion.getName() +
                " por $" + precioCOP + " COP");

        System.out.println("Compra realizada: " + seleccion.getName() +
                " por $" + precioCOP + " COP. Saldo actual: $" + usuario.getSaldo() + " COP");
    } else {
        System.out.println("Saldo insuficiente para comprar " + seleccion.getName());
    }
}

private void VenderCriptomoneda() {
    List<Criptomoneda> port = usuario.getPortafolio();
    if (port.isEmpty()) {
        System.out.println("No tienes criptomonedas para vender.");
        return;
    }

    int idx = random.nextInt(port.size());
    Criptomoneda venta = port.remove(idx);

    double precioCOP = parsePrecioCOP(venta);
    usuario.setSaldo((int) (usuario.getSaldo() + precioCOP));

    // Registrar en el libro de órdenes
    LibroOrdenes.agregarOrdenVenta(usuario.getName() +
            " vende " + venta.getName() +
            " por $" + precioCOP + " COP");

    System.out.println("Venta realizada: " + venta.getName() +
            " por $" + precioCOP + " COP. Saldo actual: $" + usuario.getSaldo() + " COP");
}

    private double parsePrecioCOP(Criptomoneda criptomoneda) {
        final double pesoCop = 4000;
        if (criptomoneda == null) return 0;
        try {
            String s = criptomoneda.getPrice_usd();
            if (s == null) return 0;
            s = s.replaceAll("[^0-9.\\-]", "");
            double precioUSD = Double.parseDouble(s);
            return precioUSD * pesoCop;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Transacciones{" +
                "usuario=" + usuario.getName() +
                ", mercado=" + mercado.size() +
                ", saldo=" + usuario.getSaldo() +
                '}';
    }


}