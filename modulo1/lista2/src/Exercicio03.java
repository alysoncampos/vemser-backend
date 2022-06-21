import java.util.Locale;
import java.util.Scanner;

public class Exercicio03 {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        String nome = null, nomeMaisPesado = null;
        double altura = 0.0, maisAlto = 0.0, somaDasAlturas = 0.0, mediaDasAlturas = 0.0, peso = 0.0, maisPesado = 0.0;
        int idade, maisVelho = 0, qtdJogadores = 0,  i = 1;

        System.out.println("CADASTRO DE JOGADORES");
        System.out.println("Para sair, digite SAIR no nome do jogador.");

        while(true){
            System.out.print("Digite o nome do " + i + "º jogador: ");
            nome = sc.nextLine();
            if("SAIR".equals(nome)){
                break;
            }
            System.out.print("Digite a altura do jogador: ");
            altura = sc.nextDouble();
            System.out.print("Digite a idade do jogador: ");
            idade = sc.nextInt();
            System.out.print("Digite o peso do jogador: ");
            peso = sc.nextDouble();
            sc.nextLine();
            qtdJogadores++;
            i++;

            if(altura > maisAlto) {
                maisAlto = altura;
            }
            if(idade > maisVelho) {
                maisVelho = idade;
            }
            if(peso >  maisPesado) {
                maisPesado = peso;
                nomeMaisPesado = nome;
            }
            somaDasAlturas += altura;

            System.out.println();
        }
        mediaDasAlturas = somaDasAlturas / qtdJogadores;

        System.out.printf("Quantidade de jogadores cadastrados : %d " +
                "%nAltura do maior jogador: %.2f" +
                "%nJogador mais velho: %d" +
                "%nJogador mais pesado: %s" +
                "%nMédia das alturas: %.2f", qtdJogadores, maisAlto, maisVelho, nomeMaisPesado, mediaDasAlturas);
        sc.close();
    }
}
