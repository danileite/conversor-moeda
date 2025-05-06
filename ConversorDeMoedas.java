import java.util.Scanner;

public class ConversorDeMoedas {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String apiKey = "7e42b6f9add895372198560d";

        while (true) {
            // Exibe o menu para o usuário
            System.out.println("\n*** Menu de Conversão de Moedas ***");
            System.out.println("1 - Real (BRL) para Dólar (USD)");
            System.out.println("2 - Dólar (USD) para Real (BRL)");
            System.out.println("3 - Euro (EUR) para Real (BRL)");
            System.out.println("4 - Real (BRL) para Euro (EUR)");
            System.out.println("5 - Libra Esterlina (GBP) para Real (BRL)");
            System.out.println("6 - Real (BRL) para Libra Esterlina (GBP)");
            System.out.println("7 - Sair");
            System.out.print("Escolha uma opção: ");

            // Captura a opção do usuário
            int opcao = sc.nextInt();

            // Verifica se o usuário escolheu sair
            if (opcao == 7) {
                System.out.println("Saindo... Até mais!");
                break;  // Sai do loop e encerra o programa
            }

            // Variáveis para armazenar as moedas de origem e destino
            String moedaOrigem = "";
            String moedaDestino = "";

            // Definir as moedas com base na opção escolhida
            switch (opcao) {
                case 1:
                    moedaOrigem = "BRL";
                    moedaDestino = "USD";
                    break;
                case 2:
                    moedaOrigem = "USD";
                    moedaDestino = "BRL";
                    break;
                case 3:
                    moedaOrigem = "EUR";
                    moedaDestino = "BRL";
                    break;
                case 4:
                    moedaOrigem = "BRL";
                    moedaDestino = "EUR";
                    break;
                case 5:
                    moedaOrigem = "GBP";
                    moedaDestino = "BRL";
                    break;
                case 6:
                    moedaOrigem = "BRL";
                    moedaDestino = "GBP";
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    continue;  // Retorna ao início do loop se a opção for inválida
            }

            // Solicitar o valor a ser convertido
            System.out.print("Digite o valor a converter: ");
            double valor = sc.nextDouble();

            // Chamar a API para obter a taxa de conversão
            double taxa = ApiConversao.obterTaxa(moedaOrigem, moedaDestino, apiKey);

            // Verificar se a taxa foi obtida com sucesso
            if (taxa == 0.0) {
                System.out.println("Erro ao obter a taxa de conversão. Tente novamente.");
                continue;  // Retorna ao início do loop se a taxa não for obtida
            }

            // Calcular o valor convertido
            double valorConvertido = valor * taxa;

            // Exibir o valor convertido para o usuário
            System.out.printf("O valor de %.2f %s é: %.2f %s\n", valor, moedaOrigem, valorConvertido, moedaDestino);
        }

        // Fecha o scanner
        sc.close();
    }
}
