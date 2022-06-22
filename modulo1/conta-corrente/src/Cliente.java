public class Cliente {

    public String nome;
    public String cpf;
    public Contato[] contatos = new Contato[2];
    public Endereco[] enderecos = new Endereco[2];

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
//        System.out.println("Endereços: ");
//        imprimirEnderecos();
//        System.out.println("Contatos: ");
//        imprimirContatos();
    }

    @Override
    public String toString(){
        return nome;
    }
}
