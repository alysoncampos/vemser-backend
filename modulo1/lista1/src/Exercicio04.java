import java.util.Scanner;

public class Exercicio04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int opcaoEstado, opcaoCidade;
        System.out.printf("Estados:%n" +
                "1- Pernambuco;%n" +
                "2- Paraíba;%n" +
                "3- Rio Grande do Norte;%n" +
                ">> ");
        opcaoEstado = sc.nextInt();
        if(opcaoEstado == 1){
            System.out.printf("Bem vindo à Pernambuco. Escolha a cidade:%n" +
                    "1- Recife%n" +
                    "2- Pesqueira%n" +
                    ">> ");
            opcaoCidade = sc.nextInt();
            switch (opcaoCidade) {
                case 1:
                    System.out.printf("Cidade: Recife%n" +
                            "População: 1.555 milhão%n" +
                            "IDH: 0.772%n" +
                            "Festa: Carnaval%n");
                    break;
                case 2:
                    System.out.printf("Cidade: Pesqueira%n" +
                            "População: 67.735%n" +
                            "IDH: 0.610%n" +
                            "Festa: Carnaval%n");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente...");
            }
        }
        else if(opcaoEstado == 2){
            System.out.printf("Bem vindo à Paraíba. Escolha a cidade:%n" +
                    "1- João Pessoa%n" +
                    "2- Campina Grande%n" +
                    ">> ");
            opcaoCidade = sc.nextInt();
            switch (opcaoCidade) {
                case 1:
                    System.out.printf("Cidade: João Pessoa%n" +
                            "População: 817.511%n" +
                            "IDH: 0.763%n" +
                            "Festa: Carnaval%n");
                    break;
                case 2:
                    System.out.printf("Cidade: Campina Grande%n" +
                            "População: 402.912%n" +
                            "IDH: 0.720%n" +
                            "Festa: São João%n");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente...");
            }
        }
        else if(opcaoEstado == 3){
            System.out.printf("Bem vindo ao Rio Grande do Norte. Escolha a cidade:%n" +
                    "1- Natal%n" +
                    "2- Timbau do Sul%n" +
                    ">> ");
            opcaoCidade = sc.nextInt();
            switch (opcaoCidade) {
                case 1:
                    System.out.printf("Cidade: Natal%n" +
                            "População: 890.480%n" +
                            "IDH: 0.763%n" +
                            "Festa: Carnaval%n");
                    break;
                case 2:
                    System.out.printf("Cidade: Timbau do Sul%n" +
                            "População: 14.440%n" +
                            "IDH: 0.645%n" +
                            "Festa: Carnaval%n");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente...");
            }
        }
        else {
            System.out.println("Opção inválida, tente novamente...");
        }

        sc.close();
    }
}
