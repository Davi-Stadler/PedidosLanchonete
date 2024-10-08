package ifpr.pgua.eic.tads.contatos.model.repositories;

import java.util.List;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tads.contatos.model.entities.Bebida;
import ifpr.pgua.eic.tads.contatos.model.entities.Lanche;
import ifpr.pgua.eic.tads.contatos.model.entities.Pedido;

public interface PedidoRepository{
    Resultado<Pedido> cadastrar(String observacao,Bebida bebida,Lanche lanche);
    Resultado<List<Pedido>> listar();
}
