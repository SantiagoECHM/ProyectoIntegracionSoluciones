package uv.mx.ventas;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/* En este apartado ayuda a mejorar el rendimiento de la aplicación y reducir la carga en la base de datos, al recuperar solo los datos necesarios en lugar de cargar toda la entidad y filtrarla posteriormente en la aplicación. */

/* La interfaz CrudRepository proporciona métodos de acceso básicos (como guardar, actualizar, eliminar y buscar) que se utilizan comúnmente en operaciones de acceso a datos. */
@Repository
public interface VentaRepositorio extends CrudRepository<Venta, Integer> {
    /*
     * Las siguienetes líneas hacen anotaciones de consulta personalizadas @Query
     * para definir consultas específicas
     */

    /*
     * @Param("fecha"): Esta anotación se utiliza para enlazar el parámetro nombre
     * del método con la consulta personalizada que nos devuelve una lista de todas
     * las ventas con el nombre del cliente filtrada
     */
    List<Venta> findVentasByNombreCliente(@Param("nombreCliente") String nombre);

    /*
     * @Param("fecha"): Esta anotación se utiliza para enlazar el parámetro fecha
     * del método con la consulta que nos devuelve una lista de todos las ventas con
     * la fecha filtrada.
     */
    List<Venta> findVentasByFecha(@Param("fecha") String fecha);

    /*
     * Para esta consulta selecciona de la tabla venta donde el precio total es
     * mayor que el valor ingresado de tipo float
     */
    @Query("SELECT e FROM Venta e WHERE e.precioTotal > ?1")
    ArrayList<Venta> findVentasByVentasMayores(Float valor);

    /*
     * Para esta consulta selecciona de la tabla venta donde el precio total es
     * menor que el valor ingresado de tipo float
     */
    @Query("SELECT e FROM Venta e WHERE e.precioTotal < ?1")
    ArrayList<Venta> findVentasByVentasMenores(Float valor);
}
