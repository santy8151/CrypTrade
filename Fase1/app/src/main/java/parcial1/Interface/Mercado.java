package parcial1.Interface;

import parcial1.Model.Usuario;
import parcial1.Services.LibroOrdenes;

public interface Mercado {

    void simularTurno();

    void agregarUsuario(Usuario usuario);

    void mostrarPortafolioUsuario(Usuario usuario);

    void ordenMercado(LibroOrdenes orden);

    void historialTransacciones(Usuario usuario);
}

