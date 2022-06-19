import java.util.Locale;
import java.util.Scanner;

public class Exercicio03 {
    public static void main(String[] args){
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        double valorTotal, valorPago, valorTroco;
        System.out.println("Bem vindo à lachonete do Alyson");
        System.out.print("Digite o valor total consumido: R$ ");
        valorTotal = sc.nextDouble();
        System.out.print("Digite o valor pago: R$ ");
        valorPago = sc.nextDouble();

        if(valorPago < valorTotal){
            System.out.println("O valor pago deve ser maior ou igual ao valor consumido");
        }
        else {
            valorTroco = valorPago - valorTotal;
            System.out.printf("Troco: R$ %.2f%n",valorTroco);
        }

        sc.close();
    }
}
