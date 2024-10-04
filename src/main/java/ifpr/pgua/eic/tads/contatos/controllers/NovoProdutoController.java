package ifpr.pgua.eic.tads.contatos.controllers;

import java.util.HashMap;
import java.util.Map;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tads.contatos.model.daos.BebidaDAO;
import ifpr.pgua.eic.tads.contatos.model.daos.LancheDAO;
import ifpr.pgua.eic.tads.contatos.model.entities.Lanches;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class NovoProdutoController {

    private BebidaDAO bebidaDAO;
    private LancheDAO lancheDAO;

    public Handler get = (Context ctx) -> {
        ctx.render("templates/novo_produto.peb");
    };

    public Handler post = (Context ctx) -> {
        String nomeProduto = ctx.formParam("nome_produto");
        String tipoProduto = ctx.formParam("tipo_produto");
        double precoProduto = Double.parseDouble(ctx.formParam("preco_produto"));
        Map<String, Object> model = new HashMap<>();

        if (tipoProduto.equals("Lanche")) {

            Resultado<Lanches> resultado = lancheDAO.salvarLanche(nomeProduto, precoProduto);
            model.put("resultado", resultado);
            if (resultado.foiErro()) {

                model.put("nome", nomeProduto);
                model.put("valor", precoProduto);
            }

        } else if (tipoProduto.equals("bebida")) {

            bebidaDAO.salvarBebida(nomeProduto, precoProduto);
        }

        ctx.render("templates/novo_produto.peb", model);
    };
}
