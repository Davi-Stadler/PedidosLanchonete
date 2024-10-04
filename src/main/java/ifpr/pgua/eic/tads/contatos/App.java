package ifpr.pgua.eic.tads.contatos;

import ifpr.pgua.eic.tads.contatos.controllers.AddBebidaController;
import ifpr.pgua.eic.tads.contatos.controllers.AddLancheController;
import ifpr.pgua.eic.tads.contatos.controllers.AddPedidoController;
import ifpr.pgua.eic.tads.contatos.controllers.IndexController;
import ifpr.pgua.eic.tads.contatos.controllers.ListBebidaController;
import ifpr.pgua.eic.tads.contatos.controllers.ListLancheController;
import ifpr.pgua.eic.tads.contatos.controllers.ListPedidoController;
import ifpr.pgua.eic.tads.contatos.model.daos.BebidaDAO;
import ifpr.pgua.eic.tads.contatos.model.daos.JDBCBebidaDAO;
import ifpr.pgua.eic.tads.contatos.model.daos.JDBCLancheDAO;
import ifpr.pgua.eic.tads.contatos.model.daos.JDBCPedidoDAO;
import ifpr.pgua.eic.tads.contatos.model.daos.LancheDAO;
import ifpr.pgua.eic.tads.contatos.model.daos.PedidoDAO;
import ifpr.pgua.eic.tads.contatos.model.entities.FabricaConexoes;
import ifpr.pgua.eic.tads.contatos.model.repositories.BebidaRepository;
import ifpr.pgua.eic.tads.contatos.model.repositories.ImplBebidaRepository;
import ifpr.pgua.eic.tads.contatos.model.repositories.ImplLancheRepository;
import ifpr.pgua.eic.tads.contatos.model.repositories.ImplPedidoRepository;
import ifpr.pgua.eic.tads.contatos.model.repositories.LancheRepository;
import ifpr.pgua.eic.tads.contatos.model.repositories.PedidoRepository;
import ifpr.pgua.eic.tads.contatos.utils.JavalinUtils;
import io.javalin.Javalin;

public class App {
    public static void main(String[] args) {
        Javalin app = JavalinUtils.makeApp(8080);

        LancheDAO lancheDAO = new JDBCLancheDAO(FabricaConexoes.getInstance());
        LancheRepository lancheRepository = new ImplLancheRepository(lancheDAO);

        BebidaDAO bebidaDAO = new JDBCBebidaDAO(FabricaConexoes.getInstance());
        BebidaRepository bebidaRepository = new ImplBebidaRepository(bebidaDAO);

        PedidoDAO pedidoDAO = new JDBCPedidoDAO(FabricaConexoes.getInstance());
        PedidoRepository pedidoRepository = new ImplPedidoRepository(pedidoDAO, lancheDAO, bebidaDAO);

        IndexController indexController = new IndexController();
        AddLancheController addLancheController = new AddLancheController(lancheRepository);
        ListLancheController listLancheController = new ListLancheController(lancheRepository);
        AddBebidaController addBebidaController = new AddBebidaController(bebidaRepository);
        ListBebidaController listBebidaController = new ListBebidaController(bebidaRepository);

        AddPedidoController addPedidoController = new AddPedidoController(pedidoRepository, lancheRepository,
                bebidaRepository);
        ListPedidoController listPedidoController = new ListPedidoController(pedidoRepository);

        app.get("/", indexController.get);
        app.get("/addLanche", addLancheController.get);
        app.post("/addLanche", addLancheController.post);
        app.get("/listLanche", listLancheController.get);
        app.get("/addBebida", addBebidaController.get);
        app.post("/addBebida", addBebidaController.post);
        app.get("/listBebida", listBebidaController.get);

        app.get("/addPedido", addPedidoController.get);
        app.post("/addPedido", addPedidoController.post);
        app.get("/listPedido", listPedidoController.get);
    }
}
