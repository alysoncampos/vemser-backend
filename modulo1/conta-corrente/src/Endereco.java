public class Endereco {
    public int tipo; // 1- residencial / 2- comercial
    public String logradouro;
    public int numero;
    public String complemento;
    public String cep;
    public String cidade;
    public String estado;
    public String pais;

    public void imprimirEndereco(){
        String tipoDoEndereco;
        switch (tipo) {
            case 1:
                tipoDoEndereco = "Residencial";
                break;
            case 2:
                tipoDoEndereco = "Comercial";
                break;
            default:
                tipoDoEndereco = "Inválido";
        }
        System.out.printf("Tipo: %d - %s %n" +
                          "Logradouro: %s %n" +
                          "Número: %d %n" +
                          "Complemento: %s %n" +
                          "CEP: %s %n" +
                          "Cidade: %s %n" +
                          "Estado: %s %n" +
                          "País: %s %n", tipo, tipoDoEndereco, logradouro,
                           numero, complemento, cep, cidade, estado, pais);
    }

}
