import java.util.Scanner;

public class Exercicio04 {
    public static void main(String[] args){

        int[] numeros = new int[3];
        int menorNumero = 9999, posicao = 0;

        Scanner sc = new Scanner(System.in);

        for(int i = 0; i < numeros.length; i++){
            System.out.printf("Digite o %d° número: ", i + 1);
            numeros[i] = sc.nextInt();
            if (numeros[i] < menorNumero) {
                menorNumero = numeros[i];
                posicao = i;
            }
        }

        System.out.printf("Menor número está na posição %d do vetor.", posicao + 1);
        sc.close();
    }
}
