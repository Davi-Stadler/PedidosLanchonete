package ifpr.pgua.eic.tads.contatos.model.daos;

import java.util.List;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tads.contatos.model.entities.Pedido;

public interface PedidoDAO {
    Resultado<Pedido> criar(Pedido pedido);
    Resultado<List<Pedido>> listar();
     
}
