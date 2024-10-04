package ifpr.pgua.eic.tads.contatos.controllers;

import io.javalin.http.Handler;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tads.contatos.model.entities.Lanche;
import ifpr.pgua.eic.tads.contatos.model.repositories.LancheRepository;

public class ListLancheController {
    private LancheRepository repositorio;

    public ListLancheController(LancheRepository repositorio) {
        this.repositorio = repositorio;
    }

    public Handler get = new ListarLanchesHandler();

    private class ListarLanchesHandler implements Handler {
        @Override
        public void handle(Context ctx) throws Exception {
            Resultado<List<Lanche>> resultado = repositorio.listar();

            Map<String, Object> model = new HashMap<>();

            model.put("resultado", resultado);
            if (resultado.foiSucesso()) {
                model.put("lista", resultado.comoSucesso().getObj());
            }

            ctx.render("templates/listLanche.peb", model);
        }
    }
}
