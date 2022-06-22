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
        if(valor <= retornarSaldoComChequeEspecial() && valor > 0){
            saldo -= valor;
            return true;
        }
        return false;
    }

    public boolean depositar(double valor){
        if(valor > 0.0) {
            saldo += valor;
            return true;
        }
        return false;
    }

    public double retornarSaldoComChequeEspecial(){
        return saldo + chequeEspecial;
    }

    public boolean transferir(ContaCorrente cc, double valor){
        if(valor <= retornarSaldoComChequeEspecial() && valor > 0){
            this.saldo -= valor;
            cc.saldo += valor;
            return true;
        }
        return false;
    }
}
