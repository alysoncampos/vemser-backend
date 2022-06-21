import java.util.Locale;
import java.util.Scanner;

public class Exercicio08 {
    public static void main(String[] args){

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        int linha = 5, coluna = 4, maiorNotaFinal = 0, matriculaMaiorNotaFinal = 0, somaNotasFinais = 0;
        int[][] alunos = new int[linha][coluna];

        // Entrada das 3 primeiras informações de cada aluno (matrícula, média das provas e média dos trabalhos);
        for(int i = 0; i < linha; i++){
            System.out.printf("Digite as informações do %dº aluno:%n", i + 1);
            for(int j = 0; j < (coluna - 1); j++){
                if(j == 0){
                    System.out.print("Matrícula: ");
                    alunos[i][j] = sc.nextInt();
                }
                else if(j == 1){
                    System.out.print("Média das provas: ");
                    alunos[i][j] = sc.nextInt();
                }
                else if(j == 2){
                    System.out.print("Média dos trabalhos: ");
                    alunos[i][j] = sc.nextInt();
                }
            }
        }

        // Calculo da nota final, guardando o resultado na quarta coluna, e soma para cálculo da média;
        for(int i = 0; i < linha; i++){
            alunos[i][3] = (int)(alunos[i][1] * 0.6 + alunos[i][2] * 0.4);
            somaNotasFinais += alunos[i][3];
            // Verifica a Maior Nota
            if(alunos[i][3] > maiorNotaFinal){
                maiorNotaFinal = alunos[i][3];
                matriculaMaiorNotaFinal = alunos[i][0];
            }
        }

        double mediaNotasFinais = (double) somaNotasFinais / linha;
        System.out.printf("Matrícula com a maior nota: %d | Nota: %d%n", matriculaMaiorNotaFinal, maiorNotaFinal);
        System.out.printf("Média das notas finais: %.2f", mediaNotasFinais);

        sc.close();
    }
}
