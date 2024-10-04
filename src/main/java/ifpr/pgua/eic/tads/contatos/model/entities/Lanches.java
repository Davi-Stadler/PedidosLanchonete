
package ifpr.pgua.eic.tads.contatos.model.entities;

public class Lanches {
    private int id;
    private String nome;
    private double valor;

    public Lanches(int id, String nome, double valor) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
    }

    public Lanches(String nome, double valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public double getValor() {
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