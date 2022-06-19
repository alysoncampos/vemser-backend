import java.util.Locale;
import java.util.Scanner;

public class Exercicio10 {
    public static void main(String[] args){
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        int totalEleitores, votosBrancos, votosNulos, votosValidos;
        double percBrancos, percNulos, percValidos;
        System.out.print("Total de eleitores: ");
        totalEleitores = sc.nextInt();
        System.out.print("Votos Válidos: ");
        votosValidos = sc.nextInt();
        System.out.print("Votos em Brancos: ");
        votosBrancos = sc.nextInt();
        System.out.print("Votos Nulos: ");
        votosNulos = sc.nextInt();

        percBrancos =  ((double) votosBrancos / totalEleitores) * 100;
        percNulos =  ((double) votosNulos / totalEleitores) * 100;
        percValidos =  ((double) votosValidos / totalEleitores) * 100;

        System.out.println("\nNúmero Total de Eleitores: " + totalEleitores + " (100%)");
        System.out.printf("Votos Válidos = %d (%.2f%%) ",votosValidos, percValidos);
        System.out.printf("\nVotos em Branco = %d (%.2f%%)",votosBrancos, percBrancos);
        System.out.printf("\nVotos Nulos = %d (%.2f%%)",votosNulos, percNulos);

        sc.close();
    }
}
