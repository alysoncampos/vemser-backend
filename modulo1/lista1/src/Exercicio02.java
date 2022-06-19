import java.util.Locale;
import java.util.Scanner;

public class Exercicio02 {
    public static void main(String[] args){
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        double nota1, nota2, nota3, nota4, media;
        System.out.println("Informe as notas do aluno: ");
        System.out.print("Primeira nota: ");
        nota1 = sc.nextDouble();
        System.out.print("Segunda nota: ");
        nota2 = sc.nextDouble();
        System.out.print("Terceira nota: ");
        nota3 = sc.nextDouble();
        System.out.print("Quarta nota: ");
        nota4 = sc.nextDouble();

        media = (nota1 + nota2 + nota3 + nota4) / 4;
        System.out.printf("Média: %.1f%n",media);

        if(media >= 0 && media <= 5){
            System.out.println("Situação: Reprovado");
        }
        else if(media >= 5.1 && media <= 6.9){
            System.out.println("Situação: Em exame");
        }
        else if(media >= 7 && media <= 10){
            System.out.println("Situação: Aprovado");
        }
        else {
            System.out.println("Algo deu errado, tente novamente...");
        }

        sc.close();
    }
}
