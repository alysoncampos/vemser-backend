import java.util.Random;
import java.util.Scanner;

public class Exercicio02 {
    public static void main(String[] args){
        int min = 1, max = 10, numEscolhido;

        Random random = new Random();
        Scanner sc = new Scanner(System.in);

        int numSorteado = random.nextInt(max + min) + min;
        System.out.println("Seja bem vindo!");
        System.out.print("Escolha um número de 1 a 10: ");
        numEscolhido = sc.nextInt();

        while(numEscolhido != numSorteado){
            if(numEscolhido < numSorteado) {
                System.out.println("O número a ser encontrado é menor do que o que você digitou!");
            }
            else {
                System.out.println("O número a ser encontrado é maior do que o que você digitou!");
            }
            System.out.printf("Escolha um número de 1 a 10: ");
            numEscolhido = sc.nextInt();
        }

        System.out.printf("Número sorteado foi %d. Parabéns, você acertou!", numSorteado);

        sc.close();
    }
}
