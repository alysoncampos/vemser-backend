package br.com.vemser.pessoaapi.entity;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

public class Endereco {

    private Integer idEndereco;
    
    private Integer idPessoa;

    @NotNull(message = "Informe o tipo de Endereço")
    private TipoEndereco tipo;

    @NotEmpty(message = "Informe o logradouro")
    @Length(max = 250, message = "O logradouro pode ter no máximo {max} caracteres")
    private String logradouro;

    @NotNull(message= "Informe o número")
    @Min(0)
    private Integer numero;

    private String complemento;

    @NotBlank(message = "Informe um CEP válido")
    @Size(min = 8, max = 8, message = "O CEP deve conter 8 números")
    private String cep;

    @NotBlank(message = "Informe uma cidade válida")
    @Length(max = 250, message = "A cidade pode ter no máximo {max} caracteres")
    private String cidade;

    @NotNull(message = "Informe um Estado")
    private String estado;

    @NotNull(message = "Informe um País")
    private String pais;

    public Endereco() {
    }

    public Endereco(Integer idEndereco, Integer idPessoa, TipoEndereco tipo, String logradouro, Integer numero,
                    String complemento, String cep, String cidade, String estado, String pais) {
        this.idEndereco = idEndereco;
        this.idPessoa = idPessoa;
        this.tipo = tipo;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
    }

    public Integer getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }

    public Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public TipoEndereco getTipo() {
        return tipo;
    }

    public void setTipo(TipoEndereco tipo) {
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

    @Override
    public String toString() {
        return "Endereco{" +
                "idEndereco=" + idEndereco +
                ", idPessoa=" + idPessoa +
                ", tipo=" + tipo +
                ", logradouro='" + logradouro + '\'' +
                ", numero=" + numero +
                ", complemento='" + complemento + '\'' +
                ", cep='" + cep + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", pais='" + pais + '\'' +
                '}';
    }
}
