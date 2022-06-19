import java.util.Locale;
import java.util.Scanner;

public class Exercicio08 {
    public static void main(String[] args){
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        double base, altura, areaRetangulo;
        System.out.print("Entre com a base do retângulo: ");
        base = sc.nextDouble();
        System.out.print("Agora entre com a altura do retângulo: ");
        altura = sc.nextDouble();

        areaRetangulo = base * altura;
        System.out.printf("A área de um retângulo de base %.2f" +
                " e altura %.2f é %.2f%n",base,altura,areaRetangulo);
        sc.close();
    }
}
