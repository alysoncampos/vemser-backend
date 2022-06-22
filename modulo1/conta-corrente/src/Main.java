import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        Contato alysonContato1 = new Contato();
        alysonContato1.tipo = 1;
        alysonContato1.telefone = "81941410227";
        alysonContato1.descricao = "Alyson Pessoal";

        Contato alysonContato2 = new Contato();
        alysonContato2.tipo = 2;
        alysonContato2.telefone = "8133526352";
        alysonContato2.descricao = "Alyson Empresa";

        Contato nataliaContato1 = new Contato();
        nataliaContato1.tipo = 1;
        nataliaContato1.telefone = "81941410227";
        nataliaContato1.descricao = "Natalia Pessoal";

        Contato nataliaContato2 = new Contato();
        nataliaContato2.tipo = 2;
        nataliaContato2.telefone = "8133526352";
        nataliaContato2.descricao = "Natalia Empresa";

        Endereco alysonEnd1 = new Endereco();
        alysonEnd1.tipo = 1;
        alysonEnd1.logradouro = "Rua Oliveira Teles";
        alysonEnd1.numero = 362;
        alysonEnd1.complemento = "Apto 1502";
        alysonEnd1.cep = "52089-360";
        alysonEnd1.cidade = "Recife";
        alysonEnd1.estado = "PE";
        alysonEnd1.pais = "Brasil";

        Endereco alysonEnd2 = new Endereco();
        alysonEnd2.tipo = 2;
        alysonEnd2.logradouro = "Rua Fonseca Tomé";
        alysonEnd2.numero = 252;
        alysonEnd2.complemento = "Sala 3";
        alysonEnd2.cep = "56895-201";
        alysonEnd2.cidade = "Recife";
        alysonEnd2.estado = "PE";
        alysonEnd2.pais = "Brasil";

        Endereco nataliaEnd1 = new Endereco();
        nataliaEnd1.tipo = 1;
        nataliaEnd1.logradouro = "Rua Francisco da Cunha";
        nataliaEnd1.numero = 951;
        nataliaEnd1.complemento = "Apto 1301";
        nataliaEnd1.cep = "44890-250";
        nataliaEnd1.cidade = "Recife";
        nataliaEnd1.estado = "PE";
        nataliaEnd1.pais = "Brasil";

        Endereco nataliaEnd2 = new Endereco();
        nataliaEnd2.tipo = 2;
        nataliaEnd2.logradouro = "Rua Dois Irmãos";
        nataliaEnd2.numero = 150;
        nataliaEnd2.complemento = "Sala 1101";
        nataliaEnd2.cep = "20195-598";
        nataliaEnd2.cidade = "Recife";
        nataliaEnd2.estado = "PE";
        nataliaEnd2.pais = "Brasil";

        Cliente alyson = new Cliente();
        alyson.nome = "Alyson";
        alyson.cpf = "753.137.580-04";
        alyson.contatos[0] = alysonContato1;
        alyson.contatos[1] = alysonContato2;
        alyson.enderecos[0] = alysonEnd1;
        alyson.enderecos[1] = alysonEnd2;

        Cliente natalia = new Cliente();
        natalia.nome = "Natália";
        natalia.cpf = "357.731.085-40";
        natalia.contatos[0] = nataliaContato1;
        natalia.contatos[1] = nataliaContato2;
        natalia.enderecos[0] = nataliaEnd1;
        natalia.enderecos[1] = nataliaEnd2;

        ContaCorrente alysonConta = new ContaCorrente();
        alysonConta.cliente = alyson;
        alysonConta.agencia = 3333;
        alysonConta.numeroConta = "1990";
        alysonConta.saldo = 1000.0;
        alysonConta.chequeEspecial = 200.0;

        ContaCorrente nataliaConta = new ContaCorrente();
        nataliaConta.cliente = natalia;
        nataliaConta.agencia = 1890;
        nataliaConta.numeroConta = "8080";
        nataliaConta.saldo = 2500.0;
        nataliaConta.chequeEspecial = 1000.0;

//        // TESTES
        System.out.println("=========================================");
        System.out.println();
        System.out.println("TESTANDO MÉTODOS DAS CLASSES CONTATO E ENDEREÇO");
        System.out.println();
        alysonContato1.imprimirContato();
        System.out.println();
        alysonEnd1.imprimirEndereco();
        System.out.println();

        System.out.println("=========================================");
        System.out.println();
        System.out.println("TESTANDO MÉTODOS DA CLASSE CLIENTE");
        System.out.println();
        System.out.println("Imprimir Contatos: ");
        natalia.imprimirContatos();
        System.out.println("Imprimir Endereços: ");
        natalia.imprimirEnderecos();
        System.out.println("Imprimir Cliente: ");
        natalia.imprimirCliente();

        System.out.println();
        System.out.println("=========================================");
        System.out.println();
        System.out.println("TESTANDO MÉTODOS DA CLASSE CONTA CORRETE");
        System.out.println();
        System.out.println("Imprimir Conta Corrente: ");
        alysonConta.imprimirContaCorrente();
        System.out.println();
        nataliaConta.imprimirContaCorrente();
        System.out.println("------------------------------");
        System.out.println();
        System.out.println("Teste de saque válido: R$100.00");
        if(alysonConta.sacar(100.00)){
            System.out.println("Saque autorizado!");
        }
        System.out.println();
        System.out.println("Teste de saque inválido: R$2000.00");
        if(!alysonConta.sacar(2000.00)){
            System.out.println("Saque não autorizado!");
        }
        System.out.println();
        System.out.println("------------------------------");
        System.out.println();
        System.out.println("Teste deposito válido: R$300.00");
        if(alysonConta.depositar(300.00)){
            System.out.println("Deposito realizado com sucesso!");
        }
        System.out.println();
        System.out.println("Teste deposito inválido: -R$300.00");
        if(!alysonConta.depositar(-300.00)){
            System.out.println("Deposito não autorizado!");
        }
        System.out.println();
        System.out.println("------------------------------");
        System.out.println();
        System.out.println("Teste transferencia válida: R$200.00");
        if(alysonConta.transferir(nataliaConta, 200.00)){
            System.out.println("Tranferencia realizada com sucesso!");
        }
        System.out.println();
        System.out.println("Teste transferencia inválida: R$5000.00");
        if(!alysonConta.transferir(nataliaConta, 5000.00)){
            System.out.println("Tranferencia não autorizada!");
        }
        System.out.println();
        System.out.println("------------------------------");
        System.out.println();
        System.out.println("Teste de retorno do Saldo + Cheque Especial");
        System.out.println("Conta Alyson:");
        System.out.println("R$: " + alysonConta.retornarSaldoComChequeEspecial());
        System.out.println();
        System.out.println("Conta Natalia:");
        System.out.println("R$: " + nataliaConta.retornarSaldoComChequeEspecial());
        System.out.println();
    }
}
