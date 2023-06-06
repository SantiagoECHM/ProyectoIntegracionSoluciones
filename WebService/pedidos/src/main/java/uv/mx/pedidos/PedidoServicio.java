package uv.mx.pedidos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoServicio {
    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    public ArrayList<Pedido> getPedidos() {
        return (ArrayList<Pedido>) pedidoRepositorio.findAll();
    }

    public Optional<Pedido> getPedidosById(Integer id) {
        return pedidoRepositorio.findById(id);
    }

    public List<Pedido> getPedidoByNombreCliente(String nombre_cliente) {
        return pedidoRepositorio.findPedidoByNombreCliente(nombre_cliente);
    }

    public List<Pedido> getPedidosByFecha(String fecha) {
        return pedidoRepositorio.findPedidosByFecha(fecha);
    }

    public ArrayList<Pedido> getPedidosByPrecioMayores(Float valor) {
        return (ArrayList<Pedido>) pedidoRepositorio.findPedidosByPrecioMayores(valor);
    }

    public ArrayList<Pedido> getPedidosByPrecioMenores(Float valor) {
        return (ArrayList<Pedido>) pedidoRepositorio.findPedidosByPrecioMenores(valor);
    }

    public Pedido guardarPedido(Pedido pedido) {
        Float precio = pedido.getPrecioTotal();
        Float monto = pedido.getMontoRecibido();
        Float cambio = monto - precio;
        pedido.setCambio(cambio);
        return pedidoRepositorio.save(pedido);
    }

    public Boolean eliminarPedido(Integer id) {
        pedidoRepositorio.deleteById(id);
        return true;
    }

    public Pedido editarPedido(Pedido peticion, Integer id) {
        Pedido pedido = pedidoRepositorio.findById(id).get();
        pedido.setNombre_cliente(peticion.getNombre_cliente());
        pedido.setApellido_cliente(peticion.getApellido_cliente());
        pedido.setDomicilio_cliente(peticion.getDomicilio_cliente());
        pedido.setEstado_pedido(peticion.getEstado_pedido());
        pedido.setMetodoDePago(peticion.getMetodoDePago());
        pedido.setFecha(peticion.getFecha());
        pedido.setMontoRecibido(peticion.getMontoRecibido());
        pedido.setPrecioTotal(peticion.getPrecioTotal());
        pedidoRepositorio.save(pedido);
        return pedido;
    }

}
