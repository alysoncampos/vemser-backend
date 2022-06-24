public class ContaPoupanca extends Conta {
    static final double JUROS_MENSAL = 1.01;

    public ContaPoupanca(Cliente cliente, String numeroConta, Integer agencia, Double saldo) {
        super(cliente, numeroConta, agencia, saldo);
    }

    public void creditarTaxa(){
        setSaldo(getSaldo() * JUROS_MENSAL);
    }

    @Override
    public void imprimir() {
        System.out.println("--- Cliente Conta Poupança ---");
        getCliente().imprimirCliente();
        System.out.printf("Número da conta: %s %n" +
                          "Agência: %d %n" +
                          "Saldo: R$%.2f %n", getNumeroConta(), getAgencia(), getSaldo());
    }
}
