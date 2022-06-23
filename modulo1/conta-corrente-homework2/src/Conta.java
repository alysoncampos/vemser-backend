public abstract class Conta implements Movimentacao {
    private Cliente cliente;
    private String numeroConta;
    private Integer agencia;
    private Double saldo;

    public Conta(Cliente cliente, String numeroConta, Integer agencia, Double saldo) {
        this.cliente = cliente;
        this.numeroConta = numeroConta;
        this.agencia = agencia;
        this.saldo = saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public Integer getAgencia() {
        return agencia;
    }

    public void setAgencia(Integer agencia) {
        this.agencia = agencia;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    @Override
    public Boolean sacar(double valor) {
        if(valor > saldo || valor < 0.0){
            System.out.println("--- Saque não autorizado! ---");
            return false;
        }
        saldo -= valor;
        System.out.println("--- Saque efetuado com sucesso ---");
        System.out.printf("Valor do saque: R$%.2f. %n" +
                          "Novo Saldo: R$%.2f %n", valor, getSaldo());
        return true;
    }

    @Override
    public Boolean depositar(double valor) {
        if(valor <= 0.0){
            System.out.println("--- Depósito não autorizado! ---");
            System.out.println("Informe um valor válido!");
            return false;
        }
        saldo += valor;
        System.out.println("--- Depósito realizado com sucesso! ---");
        System.out.printf("Valor do depósito: R$%.2f %n" +
                          "Beneficiário: %s %n", valor, getCliente().getNome());
        return true;
    }

    @Override
    public Boolean transferir(Conta conta, double valor) {
        if(valor > saldo || valor < 0.0){
            System.out.println("--- Transferência não autorizada! ---");
            System.out.printf("Saldo atual: R$%.2f %n", getSaldo());
            return false;
        }
        saldo -= valor;
        conta.saldo += valor;
        System.out.println("--- Transferência realizada com sucesso! ---");
        System.out.printf("Valor: R$%.2f %n" +
                          "Conta de Destino: %s %n" +
                          "Beneficiário: %s %n" +
                          "Seu Saldo atual: R$%.2f %n", valor, conta.numeroConta, conta.getCliente().getNome(),
                          getSaldo());
        return true;
    }

    public abstract void imprimir();
}
