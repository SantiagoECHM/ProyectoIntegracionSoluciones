package uv.mx.ventas;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/* @Service mantiene la separaciòn de responsabilidades y promueve un diseño modular y estructurado. */
@Service
public class VentaServicio {
    /*
     * la siguiente notaciòn indica a Spring que se debe proporcionar una instancia
     * de VentaRepositorio en ese campo cuando se crea una instancia de la clase que
     * lo contiene
     */
    @Autowired
    private VentaRepositorio ventaRepositorio;

    /*
     * Todos estos mètodos utilizan el VentaRepositorio para poder realizar
     * operaciones especìficas
     */
    /*
     * Nos devuelve todas las ventas disponibles en ArrayList, con ayuda de findAll
     * recupera todas las ventas disponibles en base de datos
     */
    public ArrayList<Venta> getVentas() {
        return (ArrayList<Venta>) ventaRepositorio.findAll();
    }

    /*
     * Para buscar una venta especifica por su identificador o id con el findByID y
     * devuelve un objeto venta
     */
    public Optional<Venta> getVentasById(Integer id) {
        return ventaRepositorio.findById(id);
    }

    /*
     * Las siguiente consultas personalizadas de nuestro repositorio son utilizadas
     * para filtrar datos específicos
     */
    public List<Venta> getVentasByNameClient(String nombre) {
        return ventaRepositorio.findVentasByNombreCliente(nombre);
    }

    public List<Venta> getVentasByFecha(String fecha) {
        return ventaRepositorio.findVentasByFecha(fecha);
    }

    public ArrayList<Venta> getVentasByVentasMayores(Float valor) {
        return (ArrayList<Venta>) ventaRepositorio.findVentasByVentasMayores(valor);
    }

    public ArrayList<Venta> getVentasByVentasMenores(Float valor) {
        return (ArrayList<Venta>) ventaRepositorio.findVentasByVentasMenores(valor);
    }

    public Venta guardarVenta(Venta venta) {
        /*  */
        venta.setCambio(venta.getCambio());
        return ventaRepositorio.save(venta);
    }

    public Boolean eliminarVenta(Integer id) {
        ventaRepositorio.deleteById(id);
        return true;
    }

    public Venta editarVenta(Venta peticion, Integer id) {
        Venta venta = ventaRepositorio.findById(id).get();
        venta.setNombreCliente(peticion.getNombreCliente());
        venta.setMetodoDePago(peticion.getMetodoDePago());
        venta.setPrecioTotal(peticion.getPrecioTotal());
        venta.setFecha(peticion.getFecha());
        venta.setMontoRecibido(peticion.getMontoRecibido());
        /*
         * Para el cambio solamente se define el cambio ya que en su getCambio ya cuenta
         * con la resta del monto recibido con la del pago, no es necesario que el
         * cliente ingrese el valor cambio
         */
        venta.setCambio(venta.getCambio());
        ventaRepositorio.save(venta);
        return venta;
    }
}
