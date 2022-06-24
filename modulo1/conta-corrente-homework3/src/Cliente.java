import java.util.ArrayList;

public class Cliente {

    private String nome;
    private String cpf;
    private ArrayList<Contato> listaContatos;
    private ArrayList<Endereco> listaEnderecos;

    public Cliente(){

    }

    public Cliente(String nome, String cpf, ArrayList<Contato> listaContatos, ArrayList<Endereco> listaEnderecos) {
        this.nome = nome;
        this.cpf = cpf;
        this.listaContatos = listaContatos;
        this.listaEnderecos = listaEnderecos;
    }

    public void imprimirContatos(){
        for(Contato contato : listaContatos){
            if(contato != null){
                contato.imprimirContato();
                System.out.println();
            }
        }
    }

    public void imprimirEnderecos(){
        for (Endereco endereco : listaEnderecos){
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

    public ArrayList<Contato> getListaContatos() {
        return listaContatos;
    }

    public void setListaContatos(ArrayList<Contato> listaContatos) {
        this.listaContatos = listaContatos;
    }

    public ArrayList<Endereco> getListaEnderecos() {
        return listaEnderecos;
    }

    public void setListaEnderecos(ArrayList<Endereco> listaEnderecos) {
        this.listaEnderecos = listaEnderecos;
    }
}
