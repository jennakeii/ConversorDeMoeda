import java.util.Scanner;

public class ConversorMoeda {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int opcao;
        String moedaDeOrigem = "";
        String moedaDeDestino = "";

        while (true) {
            System.out.print("""
                    ╔══════════════════════════════════════╗
                    ║         CONVERSOR DE MOEDAS          ║
                    ╠══════════════════════════════════════╣
                    ║  1. BRL → USD                        ║
                    ║  2. BRL → EUR                        ║
                    ║  3. USD → BRL                        ║
                    ║  4. USD → EUR                        ║
                    ║  5. EUR → BRL                        ║
                    ║  6. EUR → USD                        ║
                    ║  0. Sair                             ║
                    ╚══════════════════════════════════════╝
                    Digite a opção desejada:
                    """);


            opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 0) {
                System.out.println("\nPrograma encerrado.");
                return;
            }

            switch (opcao) {
                case 1:
                    moedaDeOrigem = "BRL";
                    moedaDeDestino = "USD";
                    break;
                case 2:
                    moedaDeOrigem = "BRL";
                    moedaDeDestino = "EUR";
                    break;
                case 3:
                    moedaDeOrigem = "USD";
                    moedaDeDestino = "BRL";
                    break;
                case 4:
                    moedaDeOrigem = "USD";
                    moedaDeDestino = "EUR";
                    break;
                case 5:
                    moedaDeOrigem = "EUR";
                    moedaDeDestino = "BRL";
                    break;
                case 6:
                    moedaDeOrigem = "EUR";
                    moedaDeDestino = "USD";
                    break;

                default:
                    System.out.println("""
                            
                            Opção inválida.
                            Pressione qualquer tecla para tentar novamente. Se desejar sair, digite S.
                            """);

                    String escolha = scanner.nextLine().toUpperCase();
                    if (escolha.equals("S")) {
                        System.out.println("\nEncerrando o programa.");
                        return;
                    } else {
                        System.out.println();
                        continue;
                    }
            }
            break;
        }

        System.out.println("\nDigite o valor que quer converter: ");
        double valor = scanner.nextDouble();
        System.out.println("\nConvertendo " + valor + " " + moedaDeOrigem + " para " + moedaDeDestino);
        System.out.println();

        try {
            double resultado = ExchangeApi.converterMoeda(moedaDeOrigem, moedaDeDestino, valor);
            System.out.println("""
                    ----------------------------------------
                             RESULTADO DA CONVERSÃO
                    ----------------------------------------""");

            System.out.printf(" Valor original : %s %.2f\n", simboloMoeda(moedaDeOrigem), valor);
            System.out.printf(" Valor convertido: %s %.2f\n", simboloMoeda(moedaDeDestino), resultado);
            System.out.println("----------------------------------------\n");

        } catch (Exception e) {
            System.out.println("Erro ao converter moeda: " + e.getMessage());
        }
        System.out.println();
        System.out.println("Programa encerrado.");
    }

    private static String simboloMoeda(String codigo) {
        return switch (codigo) {
            case "BRL" -> "R$";
            case "USD" -> "US$";
            case "EUR" -> "€";
            default -> codigo + " ";
        };
    }

}

