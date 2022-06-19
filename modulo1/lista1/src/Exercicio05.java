import java.util.Locale;
import java.util.Scanner;

public class Exercicio05 {
    public static void main(String[] args){
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        double valorHora, horasTrabalhadas, horasExtra50, horasExtra100, salarioBruto;

        System.out.print("Informe o valor da hora em R$: ");
        valorHora = sc.nextDouble();
        System.out.print("Informe o número de horas trabalhadas: ");
        horasTrabalhadas = sc.nextInt();
        System.out.print("Informe o número de horas extras 50%: ");
        horasExtra50 = sc.nextInt();
        System.out.print("Informe o número de horas extras 100%: ");
        horasExtra100 = sc.nextInt();

        salarioBruto = (horasTrabalhadas * valorHora) + (horasExtra50 * valorHora * 1.5)
                + (horasExtra100 * valorHora * 2.0);

        System.out.printf("O salário bruto do funcionário é: R$%.2f", salarioBruto);

        sc.close();
    }
}
