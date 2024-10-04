package ifpr.pgua.eic.tads.contatos.controllers;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class NovoPedidoController {
    
    public Handler get = (Context ctx)->{
        ctx.render("templates/novo pedido.peb");
    };

}
