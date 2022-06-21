import java.util.Random;
import java.util.Scanner;

public class Exercicio02 {
    public static void main(String[] args){

        Integer numEscolhido, numSorteado;
        int min = 1, max = 10;

        Random random = new Random();
        Scanner sc = new Scanner(System.in);

        numSorteado = random.nextInt(max + min) + min;
        System.out.println("Seja bem vindo!");

        while(true){
            System.out.print("Escolha um número de 1 a 10: ");
            numEscolhido = sc.nextInt();

            if(numEscolhido.equals(numSorteado)) {
                System.out.printf("Número sorteado foi %d. Parabéns, você acertou!%n",numSorteado);
                break;
            }
            else if(numEscolhido > numSorteado) {
                System.out.printf("O número a ser encontrado é MENOR que %d%n",numEscolhido);
            }
            else {
                System.out.printf("O número a ser encontrado é MAIOR que %d%n",numEscolhido);
            }
        }
        sc.close();
    }
}
