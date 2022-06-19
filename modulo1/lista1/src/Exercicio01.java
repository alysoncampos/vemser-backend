import java.util.Scanner;

public class Exercicio01 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String nome, cidade, estado;
        int idade;

        System.out.println("Digite as seguintes informações: ");
        System.out.print("Nome: ");
        nome = sc.nextLine();
        System.out.print("Idade: ");
        idade = sc.nextInt();
        System.out.print("Cidade: ");
        sc.nextLine();
        cidade = sc.nextLine();
        System.out.print("Estado: ");
        estado = sc.nextLine();

        System.out.printf("Olá seu nome é %s, você tem %d anos, é da cidade de %s," +
                " situada no estado de %s", nome, idade, cidade, estado);
        sc.close();
    }
}
