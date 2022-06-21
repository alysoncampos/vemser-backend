import java.util.Scanner;

public class Exercicio06 {
    public static void main(String[] args){

        int[] numeros = {8, 5, 9, 52, 54, 90, 72, 88, 19, 13};
        int numero;
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite um número: ");
        numero = sc.nextInt();
        System.out.println("\nNúmeros do vetor: ");
        boolean encontrado = false;
        for(int i = 0; i < numeros.length; i++){
            if(numero == numeros[i]){
                encontrado = true;
            }
            System.out.printf("%d ", numeros[i]);
        }
        System.out.println();
        System.out.println();
        if(encontrado){
            System.out.printf("O número %d existe no vetor.%n", numero);
        }
        else {
            System.out.printf("O número %d não existe no vetor.%n", numero);
        }

        sc.close();
    }
}
