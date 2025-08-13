package parcial1;

import parcial1.Model.Criptomoneda;
import parcial1.Services.SimulacionMercado;

import java.util.List;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        try {
            //  Consumir API de Coinlore
            String apiUrl = "https://api.coinlore.net/api/tickers/";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .GET()
                    .header("Accept", "application/json")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            //  Parsear JSON
            Gson gson = new Gson();
            JsonObject jsonResponse = gson.fromJson(response.body(), JsonObject.class);
            Criptomoneda[] criptomonedasArray = gson.fromJson(
                    jsonResponse.getAsJsonArray("data"), Criptomoneda[].class
            );
            List<Criptomoneda> criptomonedas = Arrays.asList(criptomonedasArray);

            // Crear simulador (precio inicial y tasa COP ficticia)
            double precioInicial = Double.parseDouble(criptomonedas.get(0).getPrice_usd()); // Precio de la primera cripto
            double tasaCOP = 4100; // 1 USD = 4100 COP (ejemplo)

            SimulacionMercado mercado = new SimulacionMercado(precioInicial, tasaCOP, criptomonedas);

            //  Ejecutar simulación
            mercado.transaccionesRondas(10);

            //  Procesar órdenes
            mercado.procesarOrdenes();
            mercado.procesarOrdenes(); // Procesar las restantes

        } catch (Exception e) {
            System.err.println("Error al ejecutar la aplicación: " + e.getMessage());
            e.printStackTrace();
        }
}
}


