import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        Contato alysonContato1 = new Contato("Alyson Pessoal", "81941410227", 1 );
        Contato alysonContato2 = new Contato("Alyson Empresa", "8133526352", 2);

        Contato nataliaContato1 = new Contato("Natalia Pessoal", "81941410227", 1);
        Contato nataliaContato2 = new Contato("Natalia Empresa", "8133526352", 2);

        Endereco alysonEnd1 = new Endereco(1, "Rua Oliveira Teles", 362, "Apto 1502",
                "52089-360", "Recife", "PE", "Brasil");
        Endereco alysonEnd2 = new Endereco(2, "Rua Fonseca Tomé", 252, "Sala 3",
                "56895-201", "Recife", "PE", "Brasil");

        Endereco nataliaEnd1 = new Endereco(1, "Rua Francisco da Cunha", 951,
                "Apto 1301", "44890-250", "Recife", "PE", "Brasil");
        Endereco nataliaEnd2 = new Endereco(2, "Rua Dois Irmãos", 150, "Sala 1101",
                "20195-598", "Recife", "PE", "Brasil");

        Cliente alyson = new Cliente("Alyson Campos", "753.137.580-04", alysonContato1, alysonContato2,
                alysonEnd1, alysonEnd2);
        Cliente natalia = new Cliente("Natália Campos","357.731.085-40", nataliaContato1, nataliaContato2,
                nataliaEnd1, nataliaEnd2);

        ContaCorrente alysonCC = new ContaCorrente(alyson,"3333-X", 1990, 1000.0,
                200.0);
        ContaPoupanca nataliaCP = new ContaPoupanca(natalia, "1890-X", 8080, 2500.0);

        // TESTES
//        System.out.println("=========================================");
//        System.out.println();
//        System.out.println("TESTANDO MÉTODOS DAS CLASSES CONTATO E ENDEREÇO");
//        System.out.println();
//        alysonContato1.imprimirContato();
//        System.out.println();
//        alysonEnd1.imprimirEndereco();
//        System.out.println();
//
//        System.out.println("=========================================");
//        System.out.println();
//        System.out.println("TESTANDO MÉTODOS DA CLASSE CLIENTE");
//        System.out.println();
//        System.out.println("Imprimir Contatos: ");
//        natalia.imprimirContatos();
//        System.out.println("Imprimir Endereços: ");
//        natalia.imprimirEnderecos();
//        System.out.println("Imprimir Cliente: ");
//        natalia.imprimirCliente();
//
        System.out.println();
        System.out.println("=========================================");
        System.out.println();
        System.out.println("TESTANDO MÉTODOS DA CLASSE CONTA CORRETE");
        System.out.println();
        System.out.println("1 - Imprimir Conta Corrente: ");
        alysonCC.imprimir(); //ok
        System.out.println();
        System.out.println("2 - Teste de saque válido: R$100.00");
        alysonCC.sacar(100.00); //ok
        System.out.println();
        System.out.println("3 - Teste de saque inválido: R$2000.00");
        alysonCC.sacar(2000.00); //ok
        System.out.println();
        System.out.println("4 - Teste deposito válido: R$300.00");
        alysonCC.depositar(300.00); //ok
        System.out.println();
        System.out.println("5 - Teste deposito inválido: -R$300.00");
        alysonCC.depositar(-300.00); //ok
        System.out.println();
        System.out.println("6 - Teste transferencia válida: R$200.00");
        alysonCC.transferir(nataliaCP, 200.00);
        System.out.println();
        System.out.println("7 - Teste transferencia inválida: R$5000.00");
        alysonCC.transferir(nataliaCP, 5000.00); //ok
        System.out.println();
        System.out.println("=========================================");
        System.out.println();
        System.out.println("TESTANDO MÉTODOS DA CLASSE CONTA POUPANÇA");
        System.out.println();
        System.out.println("8 - Teste imprimir Conta Poupança: ");
        nataliaCP.imprimir(); //ok
        System.out.println();
        System.out.println("9 - Teste creditar taxa: ");
        nataliaCP.creditarTaxa();
        System.out.println();
        nataliaCP.imprimir();

    }
}
