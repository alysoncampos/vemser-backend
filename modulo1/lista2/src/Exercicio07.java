import java.util.Scanner;

public class Exercicio07 {
    public static void main(String[] args){

        int[][] numeros = new int[4][4];
        int contador = 0;

        Scanner sc = new Scanner(System.in);

        for(int i = 0; i < numeros.length; i++){
            for (int j = 0; j < numeros.length; j++){
                System.out.printf("Digite um número para linha [%d] coluna [%d] : ", i+1, j+1);
                numeros[i][j] = sc.nextInt();
            }
        }

        System.out.println("\nMatriz gerada:");
        for(int i = 0; i < numeros.length; i++){
            for (int j = 0; j < numeros.length; j++){
                System.out.printf("%d\t", numeros[i][j]);
                if(numeros[i][j] > 10){
                    contador++;
                }
            }
            System.out.print("\n");
        }

        System.out.printf("\nNúmeros maiores que 10: %d", contador);

        sc.close();
    }
}
