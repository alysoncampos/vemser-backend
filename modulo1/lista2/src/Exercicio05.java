import java.util.Scanner;

public class Exercicio05 {
    public static void main(String[] args){

        int[] numeros = new int[20];

        Scanner sc = new Scanner(System.in);

        for(int i = 0; i < numeros.length; i++){
            System.out.printf("Digite o %dº valor: ", i+1);
            numeros[i] = sc.nextInt();
        }

        System.out.println("\nOrdem contrária à que foram digitados: ");
        for(int i = 0; i < numeros.length; i++){
            System.out.printf("%d ", numeros[(numeros.length - 1) -i]);
        }

        sc.close();
    }
}
