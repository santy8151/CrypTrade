package parcial1.Services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import parcial1.Model.Criptomoneda;
import parcial1.Model.Transacciones;
import parcial1.Model.Usuario;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Reporte {

    public static void generarReporte(Usuario usuario, List<Transacciones> historialTransacciones) {
        Map<String, Object> datos = new HashMap<>();

        // Saldo final
        double saldoFinalUSD = usuario.getSaldo();
        datos.put("saldo_final_usd", saldoFinalUSD);

        // Portafolio
        double valorPortafolioUSD = 0;
        Map<String, Integer> activos = new HashMap<>();
        for (Criptomoneda cripto : usuario.getPortafolio()) {
            double precioUSD = Double.parseDouble(cripto.getPrice_usd());
            valorPortafolioUSD += precioUSD;
            activos.put(cripto.getName(), activos.getOrDefault(cripto.getName(), 0) + 1);
        }
        datos.put("valor_portafolio_usd", valorPortafolioUSD);
        datos.put("activos", activos);

      
List<Map<String, Object>> historialDetallado = new ArrayList<>();

for (Transacciones transaccion : usuario.getTransaccionHistorial()) {
    for (int i = 0; i < usuario.getPortafolio().size(); i++) {

        Map<String, Object> detalle = new LinkedHashMap<>();
        detalle.put("tipo", transaccion.getTipo());

        // Obtener nombre de la criptomoneda del portafolio
        detalle.put("criptomoneda", usuario.getTransaccionHistorial().get(i).getTipo().equals("Compra") ?
                usuario.getPortafolio().get(i).getName() : "N/A");
        

        detalle.put("saldo_posterior", usuario.getSaldo());
        historialDetallado.add(detalle);
    }
}

 datos.put("historial_transacciones", historialDetallado);


        // Guardar JSON
        guardarComoJSON(datos, "app/src/main/resources/reporte_final.json");
    }

    private static void guardarComoJSON(Map<String, Object> data, String rutaArchivo) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
