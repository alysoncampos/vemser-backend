public interface Movimentacao {
    public Boolean sacar(double valor);
    public Boolean depositar(double valor);
    public Boolean transferir(Conta conta, double valor);
}
