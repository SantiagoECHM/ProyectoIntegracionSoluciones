package uv.mx.pedidos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepositorio extends JpaRepository<Pedido, Integer> {

    List<Pedido> findPedidoByNombreCliente(@Param("nombre_cliente") String nombre_cliente);

    List<Pedido> findPedidosByFecha(@Param("fecha") String fecha);

    @Query("SELECT e FROM Pedido e WHERE e.precioTotal > ?1")
    ArrayList<Pedido> findPedidosByPrecioMayores(Float valor);

    @Query("SELECT e FROM Pedido e WHERE e.precioTotal < ?1")
    ArrayList<Pedido> findPedidosByPrecioMenores(Float valor);
}
