package uv.mx.ventas;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepositorio extends CrudRepository<Venta, Integer> {
    List<Venta> findVentasByNombreCliente(@Param("nombreCliente") String nombre);

    List<Venta> findVentasByFecha(@Param("fecha") String fecha);

    @Query("SELECT e FROM Venta e WHERE e.precioTotal > ?1")
    ArrayList<Venta> findVentasByVentasMayores(Float valor);

    @Query("SELECT e FROM Venta e WHERE e.precioTotal < ?1")
    ArrayList<Venta> findVentasByVentasMenores(Float valor);
}
