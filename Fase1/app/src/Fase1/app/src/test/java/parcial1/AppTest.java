package parcial1;

import parcial1.Model.Criptomoneda;
import parcial1.Services.SimulacionMercado;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class AppTest {
    public static void main(String[] args) {
        try {
            // Consumir API de Coinlore
            String apiUrl = "https://api.coinlore.net/api/tickers/";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .GET()
                    .header("Accept", "application/json")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Parsear JSON
            Gson gson = new Gson();
            JsonObject jsonResponse = gson.fromJson(response.body(), JsonObject.class);
            Criptomoneda[] criptomonedasArray = gson.fromJson(
                    jsonResponse.getAsJsonArray("data"), Criptomoneda[].class
            );
            List<Criptomoneda> criptomonedas = Arrays.asList(criptomonedasArray);

            //  Verificación 1: que la lista no esté vacía
            if (criptomonedas.isEmpty()) {
                throw new AssertionError("La lista de criptomonedas está vacía");
            } else {
                System.out.println(" API devolvió " + criptomonedas.size() + " criptomonedas");
            }

            //  Verificación 2: que los datos tengan nombre y precio
            Criptomoneda primera = criptomonedas.get(0);
            if (primera.getName() == null || primera.getPrice_usd() == null) {
                throw new AssertionError("Los datos de la criptomoneda no están completos");
            } else {
                System.out.println(" Primera cripto: " + primera.getName() +
                        " - " + primera.getPrice_usd() + " USD");
            }

            //  Probar Simulación
            double precioInicial = Double.parseDouble(primera.getPrice_usd());
            double tasaCOP = 4100;

            SimulacionMercado mercado = new SimulacionMercado(precioInicial, tasaCOP, criptomonedas);
            mercado.transaccionesRondas(3);

            //  Verificación 3: que haya órdenes en la cola
            mercado.procesarOrdenes();

            System.out.println(" Pruebas completadas con éxito");

        } catch (Exception e) {
            System.err.println(" Error en el test: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

