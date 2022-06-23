public class Endereco {
    private Integer tipo; // 1- residencial / 2- comercial
    private String logradouro;
    private Integer numero;
    private String complemento;
    private String cep;
    private String cidade;
    private String estado;
    private String pais;

    public Endereco(){

    }

    public Endereco(Integer tipo, String logradouro, Integer numero, String complemento, String cep, String cidade, String estado, String pais) {
        this.tipo = tipo;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
    }

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

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

}
