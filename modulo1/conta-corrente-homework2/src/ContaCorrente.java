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
            System.out.println("--- Saque não autorizado! ---");
            System.out.printf("Saldo atual: R$%.2f %n" +
                              "Limite de Cheque Especial: R$%.2f %n" +
                              "Limite total para saque: R$%.2f %n", getSaldo(), chequeEspecial,
                               retornarSaldoComChequeEspecial());
            return false;
        }
        setSaldo(getSaldo() - valor);
        System.out.println("--- Saque efetuado com sucesso ---");
        System.out.printf("Valor do saque: R$%.2f. %n" +
                          "Novo Saldo: R$%.2f %n" +
                          "Limite de Cheque Especial: R$%.2f %n", valor, getSaldo(), chequeEspecial);
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
