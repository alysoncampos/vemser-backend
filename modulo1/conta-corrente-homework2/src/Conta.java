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
            return false;
        }
        this.setSaldo(this.getSaldo() - valor);
        return true;
    }

    @Override
    public Boolean depositar(double valor) {
        if(valor <= 0.0){
            return false;
        }
        this.setSaldo(this.getSaldo() + valor);
        return true;
    }

    @Override
    public Boolean transferir(Conta contaDestino, double valor) {
        if(this.sacar(valor)){
            return contaDestino.depositar(valor);
        }
        return false;
    }

    public abstract void imprimir();
}
