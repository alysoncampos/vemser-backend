import java.util.Locale;
import java.util.Scanner;

public class Exercicio08 {
    public static void main(String[] args){

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        int linha = 5;
        int coluna = 4;
        int[][] alunos = new int[linha][coluna];

        // Entrada das 3 primeiras informações de cada aluno (matrícula, média das provas e média dos trabalhos);
        for(int i = 0; i < linha; i++){
            System.out.printf("Digite as informações do %dº aluno:%n", i + 1);
            for(int j = 0; j < (coluna - 1); j++){;
                alunos[i][j] = sc.nextInt();
            }
        }

        // Calculo da nota final e resultado na quarta coluna;
        for(int i = 0; i < linha; i++){
            alunos[i][3] = (int) (alunos[i][1] * 0.6 + alunos[i][2] * 0.4);
        }

        // Maior Nota e Soma para cálculo da média;
        int maiorNota = 0;
        int matricula = 0;
        int soma = 0;
        for(int i = 0; i < linha; i++){
            if(alunos[i][3] > maiorNota){
                maiorNota = alunos[i][3];
                matricula = alunos[i][0];
            }
            soma += alunos[i][3];
        }

        System.out.println("Matrícula com a maior nota: " + matricula);
        System.out.printf("Média das notas finais: %.2f", (double) soma / linha);

        sc.close();
    }
}
