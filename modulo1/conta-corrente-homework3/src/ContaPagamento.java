public class ContaPagamento extends Conta implements Impressao {

    static final double TAXA_SAQUE = 4.25;

    public ContaPagamento(Cliente cliente, String numeroConta, Integer agencia, Double saldo) {
        super(cliente, numeroConta, agencia, saldo);
    }

    @Override
    public Boolean sacar(double valor) {
        return super.sacar(valor + TAXA_SAQUE);
    }

    @Override
    public Boolean transferir(Conta contaDestino, double valor) {
        if(super.sacar(valor)){
            return contaDestino.depositar(valor);
        }
        return false;
    }

    @Override
    public void imprimir() {
        System.out.println("--- Cliente Conta Pagamento ---");
        getCliente().imprimirCliente();
        System.out.printf("Número da conta: %s %n" +
                          "Agência: %d %n" +
                          "Saldo: R$%.2f %n" +
                          "Taxa de saque: R%.2f %n", getNumeroConta(), getAgencia(), getSaldo(), TAXA_SAQUE);
    }
}
