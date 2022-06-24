import java.util.ArrayList;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        ArrayList<Contato> listaContAlyson = new ArrayList<>();
        ArrayList<Endereco> listaEndAlyson = new ArrayList<>();
        listaContAlyson.add(new Contato("Alyson Pessoal", "81941410227", 1 ));
        listaEndAlyson.add(new Endereco(1, "Rua Oliveira Teles", 362, "Apto 1502",
                "52089-360", "Recife", "PE", "Brasil"));
        Cliente alyson = new Cliente("Alyson Campos", "753.137.580-04", listaContAlyson, listaEndAlyson);
        ContaCorrente alysonCCorrente = new ContaCorrente(alyson,"3333-X", 1990, 1000.0,
                200.0);
        ContaPagamento alysonCPagamento = new ContaPagamento(alyson, "532-XY", 181, 3500.00);

        ArrayList<Contato> listaContNatalia = new ArrayList<>();
        ArrayList<Endereco> listaEndNatalia = new ArrayList<>();
        listaContNatalia.add(new Contato("Natalia Pessoal", "81941410227", 1));
        listaEndNatalia.add(new Endereco(1, "Rua Francisco da Cunha", 951,
                "Apto 1301", "44890-250", "Recife", "PE", "Brasil"));
        Cliente natalia = new Cliente("Natália Campos","357.731.085-40", listaContNatalia, listaEndNatalia);
        ContaPoupanca nataliaCPoupanca = new ContaPoupanca(natalia, "1890-X", 8080, 2500.0);

        // TESTES
        System.out.println("=========================================");
        System.out.println();
        System.out.println("TESTANDO IMPRIMIR CONTATOS");
        System.out.println();
        System.out.println("1 - Teste imprimir Contatos Cliente Alyson: ");
        alyson.imprimirContatos();
        System.out.println("2 - Teste imprimir Contatos Cliente Natalia: ");
        natalia.imprimirContatos();
        System.out.println();
        System.out.println("=========================================");
        System.out.println();
        System.out.println("TESTANDO IMPRIMIR ENDEREÇOS");
        System.out.println();
        System.out.println("3 - Teste imprimir Endereços Cliente Alyson: ");
        alyson.imprimirEnderecos();
        System.out.println("4 - Teste imprimir Endereços Cliente Natalia: ");
        natalia.imprimirEnderecos();
        System.out.println();
        System.out.println("=========================================");
        System.out.println();
        System.out.println("TESTANDO MÉTODOS DA CLASSE CONTA CORRETE");
        System.out.println();
        System.out.println("5 - Imprimir Conta Corrente: ");
        alysonCCorrente.imprimir();
        System.out.println();
        System.out.println("6 - Teste de saque válido: R$100.00");
        if(alysonCCorrente.sacar(100.00)){
            System.out.println("--- Saque efetuado com sucesso ---");
            System.out.printf("Valor do saque: R$%.2f. %n" +
                              "Novo Saldo: R$%.2f %n" +
                              "Limite com Cheque Especial: R$%.2f %n", 100.00, alysonCCorrente.getSaldo(),
                               alysonCCorrente.retornarSaldoComChequeEspecial());
        }
        System.out.println();
        System.out.println("7 - Teste de saque inválido: R$2000.00");
        if(!alysonCCorrente.sacar(2000.00)){
            System.out.println("--- Saque não autorizado! ---");
            System.out.printf("Saldo atual: R$%.2f %n" +
                              "Limite com Cheque Especial: R$%.2f %n", alysonCCorrente.getSaldo(),
                               alysonCCorrente.retornarSaldoComChequeEspecial());
        }
        System.out.println();
        System.out.println("8 - Teste deposito válido: R$300.00");
        if(alysonCCorrente.depositar(300.00)){
            System.out.println("--- Depósito realizado com sucesso! ---");
            System.out.printf("Valor do depósito: R$%.2f %n" +
                              "Beneficiário: %s %n", 300.00, alysonCCorrente.getCliente().getNome());
        }
        System.out.println();
        System.out.println("9 - Teste deposito inválido: -R$300.00");
        if(!alysonCCorrente.depositar(-300.00)){
            System.out.println("--- Depósito não autorizado! ---");
            System.out.println("Informe um valor válido!");
        }
        System.out.println();
        System.out.println("10 - Teste transferencia válida: R$200.00");
        if(alysonCCorrente.transferir(nataliaCPoupanca, 200.00)){
            System.out.println("--- Transferência realizada com sucesso! ---");
            System.out.printf("Valor: R$%.2f %n" +
                            "Conta de Destino: %s %n" +
                            "Beneficiário: %s %n" +
                            "Seu Saldo atual: R$%.2f %n", 200.00, nataliaCPoupanca.getNumeroConta(),
                            nataliaCPoupanca.getCliente().getNome(), alysonCCorrente.getSaldo());
        }
        System.out.println();
        System.out.println("11 - Teste transferencia inválida: R$5000.00");
        if(!alysonCCorrente.transferir(nataliaCPoupanca, 5000.00)){
            System.out.println("--- Transferência não autorizada! ---");
            System.out.printf("Saldo atual: R$%.2f %n", alysonCCorrente.getSaldo());
        }
        System.out.println();
        System.out.println("=========================================");
        System.out.println();
        System.out.println("TESTANDO MÉTODOS DA CLASSE CONTA PAGAMENTO");
        System.out.println();
        System.out.println("12 - Teste imprimir Conta Pagamento");
        alysonCPagamento.imprimir();
        System.out.println();
        System.out.println("13 - Teste saque válido Conta Pagamento: R$1000.00 ");
        if(alysonCPagamento.sacar(1000.00)){
            System.out.println("--- Saque efetuado com sucesso ---");
            System.out.printf("Valor do saque: R$%.2f %n" +
                              "Taxa de Saque: R%.2f %n" +
                              "Novo Saldo: R$%.2f %n", 1000.00, ContaPagamento.TAXA_SAQUE, alysonCPagamento.getSaldo());
        }
        System.out.println();
        System.out.println("14 - Teste saque inválido Conta Pagamento: R$4000.00 ");
        if(!alysonCPagamento.sacar(4000.00)){
            System.out.println("--- Saque não autorizado! ---");
            System.out.printf("Saldo atual: R$%.2f %n" +
                              "Taxa de saque: R%.2f %n", alysonCPagamento.getSaldo(), ContaPagamento.TAXA_SAQUE);
        }
        System.out.println();
        System.out.println("15 - Teste Tranferencia Conta Pagamento: R$200.00 ");
        if(alysonCPagamento.transferir(nataliaCPoupanca, 200.00)){
            System.out.println("-- Transferência realizada com sucesso! --");
            System.out.printf("Valor: R$%.2f %n" +
                              "Conta de Destino: %s %n" +
                              "Beneficiário: %s %n" +
                              "Saldo atual: R$%.2f %n", 200.00, nataliaCPoupanca.getNumeroConta(),
                               nataliaCPoupanca.getCliente().getNome(), alysonCPagamento.getSaldo());
        }
        System.out.println();
        System.out.println("=========================================");
        System.out.println();
        System.out.println("TESTANDO MÉTODOS DA CLASSE CONTA POUPANÇA");
        System.out.println();
        System.out.println("16 - Teste imprimir Conta Poupança: ");
        nataliaCPoupanca.imprimir();
        System.out.println();
        System.out.println("17 - Teste de saque válido Conta Poupança: R$300.00");
        if(nataliaCPoupanca.sacar(300.00)){
            System.out.println("--- Saque efetuado com sucesso ---");
            System.out.printf("Valor do saque: R$%.2f. %n" +
                              "Novo Saldo: R$%.2f %n", 300.00, nataliaCPoupanca.getSaldo());
        }
        System.out.println();
        System.out.println("18 - Teste de saque inválido Conta Poupança: R$5000.00");
        if(!nataliaCPoupanca.sacar(5000.00)){
            System.out.println("--- Saque não autorizado! ---");
            System.out.printf("Saldo atual: R$%.2f %n", nataliaCPoupanca.getSaldo());
        }
        System.out.println();
        System.out.println("19 - Teste creditar taxa Conta Poupança: ");
        nataliaCPoupanca.creditarTaxa();
        nataliaCPoupanca.imprimir();
    }
}
