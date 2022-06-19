import java.util.Scanner;

public class Exercicio06 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        char opcao;
        String palavra;
        System.out.printf("Escolha:%n" +
                "1- Inglês para Português%n" +
                "2- Português para Inglês%n" +
                ">> ");
        opcao = sc.next().charAt(0);
        if(opcao == '1'){
            System.out.printf("Inglês para Português:%n" +
                    "1- Dog%n" +
                    "2- Time%n" +
                    "3- Love%n" +
                    "4- City%n" +
                    "5- Happy%n" +
                    "6- Sad%n" +
                    "7- Shoud%n" +
                    "8- Could%n");
            System.out.print("Escreva a palavra que deseja traduzir: ");
            sc.nextLine();
            palavra = sc.nextLine();
            switch (palavra) {
                case "Dog" :
                    System.out.println("Dog : Cachorro");
                    break;
                case "Time":
                    System.out.println("Time : Tempo");
                    break;
                case "Love":
                    System.out.println("Love : Amor");
                    break;
                case "City":
                    System.out.println("City : Cidade");
                    break;
                case "Happy":
                    System.out.println("Happy : Feliz");
                    break;
                case "Sad":
                    System.out.println("Sad : Triste");
                    break;
                case "Shoud":
                    System.out.println("Shoud : Deveria");
                    break;
                case "Could":
                    System.out.println("Could : Poderia");
                    break;
                default:
                    System.out.println("\nEssa palavra não é válida, tente novamente...");
            }
        }
        else if(opcao == '2') {
            System.out.printf("Português para Inglês:%n" +
                    "1- Cachorro%n" +
                    "2- Tempo%n" +
                    "3- Amor%n" +
                    "4- Cidade%n" +
                    "5- Feliz%n" +
                    "6- Triste%n" +
                    "7- Deveria%n" +
                    "8- Poderia%n");
            System.out.print("Escreva a palavra que deseja traduzir: ");
            sc.nextLine();
            palavra = sc.nextLine();
            switch (palavra){
                case "Cachorro":
                    System.out.println("Cachorro : Dog");
                    break;
                case "Tempo":
                    System.out.println("Tempo : Time");
                    break;
                case "Amor":
                    System.out.println("Amor : Love");
                    break;
                case "Cidade":
                    System.out.println("Cidade : City");
                    break;
                case "Feliz":
                    System.out.println("Feliz : Happy");
                    break;
                case "Triste":
                    System.out.println("Triste : Sad");
                    break;
                case "Deveria":
                    System.out.println("Deveria : Shoud");
                    break;
                case "Poderia":
                    System.out.println("Poderia : Could");
                    break;
                default:
                    System.out.println("\nEssa palavra não é válida, tente novamente...");
            }
        }
        else {
            System.out.println("Opção inválida, tente novamente...");
        }

        sc.close();
    }
}
