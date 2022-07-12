package br.com.vemser.pessoaapi.entity;

import java.util.Arrays;

public enum TipoMensagem {
    CREATE("create"),
    UPDATE("update"),
    DELETE("delete");

    private String tipoDeMensagem;

    TipoMensagem(String tipoDeMensagem) {
        this.tipoDeMensagem = tipoDeMensagem;
    }

    public String getTipo() {
        return tipoDeMensagem;
    }

    public static TipoMensagem ofTipo(String tipoDeMensagem){
        return Arrays.stream(TipoMensagem.values())
                .filter(tp -> tp.getTipo().equals(tipoDeMensagem))
                .findFirst()
                .get();
    }
}
