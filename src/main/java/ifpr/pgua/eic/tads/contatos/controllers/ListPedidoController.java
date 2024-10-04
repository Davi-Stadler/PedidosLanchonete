package ifpr.pgua.eic.tads.contatos.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tads.contatos.model.entities.Pedido;
import ifpr.pgua.eic.tads.contatos.model.repositories.PedidoRepository;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class ListPedidoController {

    private PedidoRepository repository;

    public ListPedidoController(PedidoRepository repository) {
        this.repository = repository;
    }

    public Handler get = (Context ctx) -> {

        Resultado<List<Pedido>> resultado = repository.listar();

        Map<String, Object> model = new HashMap<>();

        model.put("resultado", resultado);
        if (resultado.foiSucesso()) {
            model.put("lista", resultado.comoSucesso().getObj());
        }

        ctx.render("templates/listPedido.peb", model);
    };

}
