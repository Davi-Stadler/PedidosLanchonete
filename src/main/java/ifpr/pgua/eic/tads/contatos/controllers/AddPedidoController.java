package ifpr.pgua.eic.tads.contatos.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tads.contatos.model.entities.Bebida;
import ifpr.pgua.eic.tads.contatos.model.entities.Lanche;
import ifpr.pgua.eic.tads.contatos.model.entities.Pedido;
import ifpr.pgua.eic.tads.contatos.model.repositories.BebidaRepository;
import ifpr.pgua.eic.tads.contatos.model.repositories.LancheRepository;
import ifpr.pgua.eic.tads.contatos.model.repositories.PedidoRepository;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class AddPedidoController {

    private PedidoRepository repository;
    private LancheRepository repositorioLanche;
    private BebidaRepository repositorioBebida;

    public AddPedidoController(PedidoRepository repository, LancheRepository repositorioLanche,
            BebidaRepository repositorioBebida) {
        this.repository = repository;
        this.repositorioLanche = repositorioLanche;
        this.repositorioBebida = repositorioBebida;
    }

    public Handler get = (Context ctx) -> {
        Map<String, Object> model = new HashMap<>();

        Resultado<List<Lanche>> resultado = repositorioLanche.listar();
        Resultado<List<Bebida>> resultado2 = repositorioBebida.listar();

        if (resultado.foiSucesso()) {
            model.put("lanches", resultado.comoSucesso().getObj());
            model.put("bebidas", resultado2.comoSucesso().getObj());

        }
        ctx.render("templates/addPedido.peb", model);
    };

    public Handler post = (Context ctx) -> {

        String observacao = ctx.formParam("observacao");

        String bebidaId = ctx.formParam("bebidaId");
        String lancheId = ctx.formParam("lancheId");

        Resultado<Bebida> resultadoBebida = repositorioBebida.getById(Integer.valueOf(bebidaId));
        Resultado<Lanche> resultadoLanche = repositorioLanche.getById(Integer.valueOf(lancheId));

        Bebida bebida = resultadoBebida.comoSucesso().getObj();
        Lanche lanche = resultadoLanche.comoSucesso().getObj();

        Resultado<Pedido> resultado = repository.cadastrar(observacao, bebida, lanche);

        Map<String, Object> model = new HashMap<>();
        model.put("resultado", resultado);

        if (resultado.foiErro()) {
            model.put("observacao", observacao);
            model.put("idbebida", Integer.valueOf(bebidaId));
            model.put("idlanche", Integer.valueOf(lancheId));

        }
        Resultado<List<Bebida>> ret2 = repositorioBebida.listar();
        model.put("bebidas", ret2.comoSucesso().getObj());
        Resultado<List<Lanche>> ret = repositorioLanche.listar();
        model.put("lanches", ret.comoSucesso().getObj());
        ctx.render("templates/addPedido.peb", model);

    };
}
