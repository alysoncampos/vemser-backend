import java.util.Scanner;

public class Exercicio09 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        final int DIAS_NO_ANO = 365;
        final int DIAS_NO_MES = 30;
        int anos, meses, dias, totalDias;

        System.out.println("Informe a sua idade: ");
        System.out.print("Anos: ");
        anos = sc.nextInt();
        System.out.print("Meses: ");
        meses = sc.nextInt();
        System.out.print("Dias: ");
        dias = sc.nextInt();

        totalDias = (anos * DIAS_NO_ANO) + (meses * DIAS_NO_MES) + dias;

        System.out.println("A sua idade em dias é: " + totalDias);

        sc.close();
    }
}
