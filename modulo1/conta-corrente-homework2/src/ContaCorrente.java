public class ContaCorrente extends Conta  implements Impressao {

    private Double chequeEspecial;

    public ContaCorrente(Cliente cliente, String numeroConta, Integer agencia, Double saldo, Double chequeEspecial) {
        super(cliente, numeroConta, agencia, saldo);
        this.chequeEspecial = chequeEspecial;
    }

    public void setChequeEspecial(double chequeEspecial) {
        this.chequeEspecial = chequeEspecial;
    }

    public Double retornarSaldoComChequeEspecial(){
        return getSaldo() + chequeEspecial;
    }

    @Override
    public Boolean sacar(double valor) {
        if(valor > retornarSaldoComChequeEspecial() || valor <= 0.0){
            return false;
        }
        setSaldo(getSaldo() - valor);
        return true;
    }

    @Override
    public void imprimir() {
        System.out.println("--- Cliente Conta Corrente ---");
        getCliente().imprimirCliente();
        System.out.printf("Número da conta: %s %n" +
                          "Agência: %d %n" +
                          "Saldo: R$%.2f %n" +
                          "Saldo Cheque Especial: R$%.2f %n", getNumeroConta(), getAgencia(), getSaldo(), chequeEspecial);
    }
}
