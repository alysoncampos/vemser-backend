public class Contato {
    public String descricao;
    public String telefone;
    public int tipo; // 1- residencial / 2- comercial

    public void imprimirContato(){
        String tipoDoContato;
        switch (tipo) {
            case 1:
                tipoDoContato = "Residencial";
                break;
            case 2:
                tipoDoContato = "Comercial";
                break;
            default:
                tipoDoContato = "Inválido";
        }
        System.out.printf("Descrição: %s %n" +
                          "Telefone: %s %n" +
                          "Tipo: %d - %s %n", descricao, telefone, tipo, tipoDoContato);
    }

}
