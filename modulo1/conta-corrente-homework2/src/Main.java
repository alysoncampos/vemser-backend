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
        alysonCC.imprimir();
        System.out.println();
        System.out.println("2 - Teste de saque válido: R$100.00");
        if(alysonCC.sacar(100.00)){
            System.out.println("--- Saque efetuado com sucesso ---");
            System.out.printf("Valor do saque: R$%.2f. %n" +
                              "Novo Saldo: R$%.2f %n" +
                              "Limite com Cheque Especial: R$%.2f %n", 100.00, alysonCC.getSaldo(),
                               alysonCC.retornarSaldoComChequeEspecial());
        }
        System.out.println();
        System.out.println("3 - Teste de saque inválido: R$2000.00");
        if(!alysonCC.sacar(2000.00)){
            System.out.println("--- Saque não autorizado! ---");
            System.out.printf("Saldo atual: R$%.2f %n" +
                              "Limite com Cheque Especial: R$%.2f %n", alysonCC.getSaldo(),
                               alysonCC.retornarSaldoComChequeEspecial());
        }
        System.out.println();
        System.out.println("4 - Teste deposito válido: R$300.00");
        if(alysonCC.depositar(300.00)){
            System.out.println("--- Depósito realizado com sucesso! ---");
            System.out.printf("Valor do depósito: R$%.2f %n" +
                              "Beneficiário: %s %n", 300.00, alysonCC.getCliente().getNome());
        }
        System.out.println();
        System.out.println("5 - Teste deposito inválido: -R$300.00");
        if(!alysonCC.depositar(-300.00)){
            System.out.println("--- Depósito não autorizado! ---");
            System.out.println("Informe um valor válido!");
        }
        System.out.println();
        System.out.println("6 - Teste transferencia válida: R$200.00");
        if(alysonCC.transferir(nataliaCP, 200.00)){
            System.out.println("--- Transferência realizada com sucesso! ---");
            System.out.printf("Valor: R$%.2f %n" +
                            "Conta de Destino: %s %n" +
                            "Beneficiário: %s %n" +
                            "Seu Saldo atual: R$%.2f %n", 200.00, nataliaCP.getNumeroConta(),
                            nataliaCP.getCliente().getNome(), alysonCC.getSaldo());
        }
        System.out.println();
        System.out.println("7 - Teste transferencia inválida: R$5000.00");
        if(!alysonCC.transferir(nataliaCP, 5000.00)){
            System.out.println("--- Transferência não autorizada! ---");
            System.out.printf("Saldo atual: R$%.2f %n", alysonCC.getSaldo());
        }
        System.out.println();
        System.out.println("=========================================");
        System.out.println();
        System.out.println("TESTANDO MÉTODOS DA CLASSE CONTA POUPANÇA");
        System.out.println();
        System.out.println("8 - Teste imprimir Conta Poupança: ");
        nataliaCP.imprimir();
        System.out.println();
        System.out.println("9 - Teste de saque válido: R$300.00");
        if(alysonCC.sacar(300.00)){
            System.out.println("--- Saque efetuado com sucesso ---");
            System.out.printf("Valor do saque: R$%.2f. %n" +
                              "Novo Saldo: R$%.2f %n", 300.00, nataliaCP.getSaldo());
        }
        System.out.println();
        System.out.println("10 - Teste de saque inválido: R$5000.00");
        if(!alysonCC.sacar(5000.00)){
            System.out.println("--- Saque não autorizado! ---");
            System.out.printf("Saldo atual: R$%.2f %n", nataliaCP.getSaldo());
        }
        System.out.println();
        System.out.println("11 - Teste creditar taxa: ");
        nataliaCP.creditarTaxa();
        nataliaCP.imprimir();
    }
}
