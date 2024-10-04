package ifpr.pgua.eic.tads.contatos.model.entities;

public class Bebidas {
    private int id;
    private String nome;
    private int valor;

    public Bebidas(int id, String nome, int valor) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
    }

    public Bebidas(String nome, int valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public int getValor() {
        return valor;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(int valor) {
        this.valor = valor;
    }

    public String toString() {
        return "Produto: " + nome + " Valor: " + valor;
    }

}
