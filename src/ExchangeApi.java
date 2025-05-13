import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

    public class ExchangeApi {
        private static final String API_KEY = "your_api_key_here";

        public static double converterMoeda(String origem, String destino, double valor) throws Exception {
            String url = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/" + origem + "/" + destino + "/" + valor;

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Gson gson = new Gson();
            ExchangeResponse resultado = gson.fromJson(response.body(), ExchangeResponse.class);

            if (!resultado.result.equals("success")) {
                throw new RuntimeException("Erro da API: " + resultado.result);
            }

            return resultado.conversion_result;
        }
    }