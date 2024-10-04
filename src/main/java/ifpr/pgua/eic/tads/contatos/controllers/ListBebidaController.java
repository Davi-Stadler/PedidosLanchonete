package ifpr.pgua.eic.tads.contatos.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tads.contatos.model.entities.Bebida;
import ifpr.pgua.eic.tads.contatos.model.repositories.BebidaRepository;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class ListBebidaController {

    private BebidaRepository repositorio;

    public ListBebidaController(BebidaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public Handler get = (Context ctx) -> {

        Resultado<List<Bebida>> resultado = repositorio.listar();

        Map<String, Object> model = new HashMap<>();

        model.put("resultado", resultado);
        if (resultado.foiSucesso()) {
            model.put("lista", resultado.comoSucesso().getObj());
        }

        ctx.render("templates/listBebida.peb", model);

    };

}
