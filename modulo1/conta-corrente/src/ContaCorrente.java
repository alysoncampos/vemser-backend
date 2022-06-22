public class ContaCorrente {

    public Cliente cliente;
    public String numeroConta;
    public int agencia;
    public double saldo;
    public double chequeEspecial;

    public void imprimirContaCorrente(){
        System.out.printf("Conta Corrente: %n" +
                          "Cliente: %s %n" +
                          "Número da conta: %s %n" +
                          "Agência: %d %n" +
                          "Saldo: %.2f %n" +
                          "Saldo Cheque Especial: %.2f %n", cliente, numeroConta, agencia, saldo, chequeEspecial);
    }

    public boolean sacar(double valor){
        if(valor > retornarSaldoComChequeEspecial()){
//            System.out.println("Você não possui fundos suficientes para sacar!");
            return false;
        }
        if(valor < 0.0){
//            System.out.println("Valor de saque deve ser maior do que zero!");
            return false;
        }
        if(saldo >= valor){
            saldo -= valor;
//            System.out.printf("Saque de R$ %.2f realizado com sucesso!%n",valor);
//            System.out.printf("Seu novo saldo é: R$ %.2f %n",saldo);
//            System.out.printf("Limite de Cheque Especial: R$ %.2f",chequeEspecial);
        } else if (retornarSaldoComChequeEspecial() >= valor){
            saldo -= valor;
//            System.out.printf("Saque de R$ %.2f realizado com sucesso!%n",valor);
//            System.out.printf("Seu novo saldo é: R$ %.2f %n",saldo);
//            System.out.printf("Limite de Cheque Especial: R$ %.2f",chequeEspecial);
        }
        return true;
    }

    public boolean depositar(double valor){
        if(valor <= 0.0) {
//            System.out.println("Deposite um valor válido");
            return false;
        }
        saldo += valor;
//        System.out.printf("Você depositou: R$ %.2f %n" +
//                          "Seu novo saldo: R$ %.2f %n", valor, saldo);
        return true;
    }

    public double retornarSaldoComChequeEspecial(){
        return saldo + chequeEspecial;
    }

    public boolean transferir(ContaCorrente cc, double valor){
        if(valor > retornarSaldoComChequeEspecial()){
//            System.out.println("Você não possui fundos suficientes para transferir!");
            return false;
        }
        if(valor < 0.0){
//            System.out.println("Valor deve ser maior do que zero!");
            return false;
        }
        if (this.saldo >= valor){
            cc.saldo += valor;
            this.saldo -= valor;
//            System.out.printf("Você transferiu R$ %.2f para %s %n", valor, cc.cliente.nome);
//            System.out.printf("Novo saldo: R$ %.2f %n", this.saldo);
//            System.out.printf("Limite de Cheque Especial: R$ %.2f", chequeEspecial);
        } else if (retornarSaldoComChequeEspecial() >= valor){
            cc.saldo += valor;
            this.saldo -= valor;
//            System.out.printf("Você transferiu R$ %.2f para %s %n", valor, cc.cliente.nome);
//            System.out.printf("Novo saldo: R$ %.2f %n", saldo);
//            System.out.printf("Limite de Cheque Especial: R$ %.2f", chequeEspecial);
        }
        return true;
    }
}
