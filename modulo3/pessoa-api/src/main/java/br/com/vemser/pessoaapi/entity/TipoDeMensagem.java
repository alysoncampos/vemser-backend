package br.com.vemser.pessoaapi.entity;

import java.util.Arrays;

public enum TipoDeMensagem {
    CREATE("create"),
    UPDATE("update"),
    DELETE("delete");

    private String tipoDeMensagem;

    TipoDeMensagem(String tipoDeMensagem) {
        this.tipoDeMensagem = tipoDeMensagem;
    }

    public String getTipo() {
        return tipoDeMensagem;
    }

    public static TipoDeMensagem ofTipo(String tipoDeMensagem){
        return Arrays.stream(TipoDeMensagem.values())
                .filter(tp -> tp.getTipo().equals(tipoDeMensagem))
                .findFirst()
                .get();
    }
}
