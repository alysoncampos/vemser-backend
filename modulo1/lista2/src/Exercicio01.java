import java.util.Locale;
import java.util.Scanner;

public class Exercicio01 {
    public static void main(String[] args){
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        double[] lista = new double[10];
        String nome;
        double valor;
        int desconto = 5;

        System.out.print("Produto: ");
        nome = sc.nextLine();
        System.out.print("Preço: ");
        valor = sc.nextDouble();

        for(int i = 0; i < lista.length; i++){
            lista[i] = valor - (valor * desconto / 100);
            desconto += 5;
        }

        System.out.println("\nPromoção: " + nome);
        for(int i = 0; i < lista.length; i++) {
            System.out.printf("%d x R$ %.2f = R$ %.2f%n", i+1, lista[i], (i+1) * lista[i]);
        }

        sc.close();
    }
}
