package parcial1.Services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import parcial1.Model.Criptomoneda;
import parcial1.Model.Usuario;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reporte {

    public static void generarReporte(Usuario usuario, List<String> historialTransacciones) {
        Map<String, Object> datos = new HashMap<>();

     
        double saldoFinalUSD = usuario.getSaldo();
        datos.put("saldo_final_usd", saldoFinalUSD);

   
        double valorPortafolioUSD = 0;
        Map<String, Integer> activos = new HashMap<>();

        for (Criptomoneda cripto : usuario.getPortafolio()) {
            double precioUSD = Double.parseDouble(cripto.getPrice_usd());
            valorPortafolioUSD += precioUSD;

            activos.put(cripto.getName(), activos.getOrDefault(cripto.getName(), 0) + 1);
        }

        datos.put("valor_portafolio_usd", valorPortafolioUSD);
        datos.put("activos", activos);

        datos.put("historial_transacciones", historialTransacciones);

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
