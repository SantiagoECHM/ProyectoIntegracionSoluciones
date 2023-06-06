package uv.mx.pedidos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tienda/pedidos")
public class PedidoController {
    @Autowired
    private PedidoServicio pedidoServicio;

    @GetMapping
    public ArrayList<Pedido> getPedidos() {
        return this.pedidoServicio.getPedidos();
    }

    @GetMapping(path = "/id/{id}")
    public Optional<Pedido> getPedidosById(@PathVariable("id") Integer id) {
        return this.pedidoServicio.getPedidosById(id);
    }

    @GetMapping(path = "/cliente/nombre/{nombre_cliente}")
    public List<Pedido> getPedidoByNombreCliente(@PathVariable("nombre_cliente") String nombre_cliente) {
        return this.pedidoServicio.getPedidoByNombreCliente(nombre_cliente);
    }

    @GetMapping(path = "/fecha/{fecha}")
    public List<Pedido> getPedidosByFecha(@PathVariable("fecha") String fecha) {
        return this.pedidoServicio.getPedidosByFecha(fecha);
    }

    @GetMapping(path = "/mayores/{valor}")
    public ArrayList<Pedido> getPedidosByMayores(@PathVariable Float valor) {
        return this.pedidoServicio.getPedidosByPrecioMayores(valor);
    }

    @GetMapping(path = "/menores/{valor}")
    public ArrayList<Pedido> getPedidosByMenores(@PathVariable Float valor) {
        return this.pedidoServicio.getPedidosByPrecioMenores(valor);
    }

    @PostMapping
    public Pedido guardarPedido(@RequestBody Pedido pedido) {
        return this.pedidoServicio.guardarPedido(pedido);
    }

    @DeleteMapping(path = "/id/{id}")
    public String eliminarPedido(@PathVariable("id") Integer id) {
        boolean ok = this.pedidoServicio.eliminarPedido(id);
        if (ok) {
            return "se ha eliminado correctamente";
        } else {
            return "Error al eliminar la pedido";
        }
    }

    @PutMapping(path = "/id/{id}")
    public Pedido editarPedido(@RequestBody Pedido pedido, @PathVariable("id") Integer id) {
        return this.pedidoServicio.editarPedido(pedido, id);
    }
}
