package ifpr.pgua.eic.tads.contatos.model.entities;

public class Pedido {
    private int id;
    private String observacao;
    private Bebida bebida;
    private Lanche lanche;

    public Pedido(int id, String observacao) {
        this.id = id;
        this.observacao = observacao;
    }

    public Pedido(int id, String observacao, Bebida bebida, Lanche lanche) {
        this.id = id;
        this.observacao = observacao;
        this.bebida = bebida;
        this.lanche = lanche;
    }

    public Pedido(String observacao, Bebida bebida, Lanche lanche) {
        this.observacao = observacao;
        this.bebida = bebida;
        this.lanche = lanche;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Bebida getBebida() {
        return bebida;
    }

    public void setBebida(Bebida bebida) {
        this.bebida = bebida;
    }

    public Lanche getLanche() {
        return lanche;
    }

    public void setLanche(Lanche lanche) {
        this.lanche = lanche;
    }
}
