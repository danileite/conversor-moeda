import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ApiConversao {

    public static double obterTaxa(String moedaOrigem, String moedaDestino, String apiKey) {
        double taxa = 0.0;

        try {
            // Montando o endpoint da API
            String endpoint = String.format(
                    "https://v6.exchangerate-api.com/v6/%s/pair/%s/%s",
                    apiKey, moedaOrigem, moedaDestino
            );

            // Criando a URL para a requisição
            URL url = new URL(endpoint);

            // Abrindo a conexão HTTP
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET"); // Método GET para obter dados

            // Lendo a resposta da API
            BufferedReader reader = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            StringBuilder resposta = new StringBuilder();
            String linha;

            // Lendo o conteúdo da resposta
            while ((linha = reader.readLine()) != null) {
                resposta.append(linha);
            }
            reader.close();

            // Imprimindo a resposta JSON para verificar o que foi retornado
            System.out.println("Resposta da API: " + resposta.toString());

            // Usando GSON para parsear o JSON
            JsonObject json = JsonParser.parseString(resposta.toString()).getAsJsonObject();

            // Verifique se o campo "conversion_rate" está presente
            if (json.has("conversion_rate")) {
                // Pegando a taxa de conversão do JSON
                taxa = json.get("conversion_rate").getAsDouble();
            } else {
                System.out.println("Campo 'conversion_rate' não encontrado na resposta.");
            }

        } catch (Exception e) {
            System.out.println("Erro ao obter taxa de conversão: " + e.getMessage());
        }

        return taxa;
    }
}
