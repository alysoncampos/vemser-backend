public class Cliente {

    private String nome;
    private String cpf;
    private Contato[] contatos = new Contato[2];
    private Endereco[] enderecos = new Endereco[2];

    public Cliente(){

    }

    public Cliente(String nome, String cpf, Contato contatos1, Contato contatos2,
                   Endereco enderecos1, Endereco enderecos2) {
        this.nome = nome;
        this.cpf = cpf;
        this.contatos[0] = contatos1;
        this.contatos[1] = contatos2;
        this.enderecos[0] = enderecos1;
        this.enderecos[1] = enderecos2;
    }

    public void imprimirContatos(){
        for(Contato contato : contatos){
            if(contato != null){
                contato.imprimirContato();
                System.out.println();
            }
        }
    }

    public void imprimirEnderecos(){
        for (Endereco endereco : enderecos){
            if(endereco != null){
                endereco.imprimirEndereco();
                System.out.println();
            }
        }
    }

    public void imprimirCliente(){
        System.out.printf("Nome: %s %nCPF: %s %n", nome, cpf);
    }

    @Override
    public String toString(){
        return nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Contato[] getContatos() {
        return contatos;
    }

    public void setContatos(Contato[] contatos) {
        this.contatos = contatos;
    }

    public Endereco[] getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(Endereco[] enderecos) {
        this.enderecos = enderecos;
    }
}
