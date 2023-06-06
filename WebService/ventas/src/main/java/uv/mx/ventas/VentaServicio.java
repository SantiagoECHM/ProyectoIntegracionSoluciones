package uv.mx.ventas;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaServicio {
    @Autowired
    private VentaRepositorio ventaRepositorio;

    public ArrayList<Venta> getVentas() {
        return (ArrayList<Venta>) ventaRepositorio.findAll();
    }

    public Optional<Venta> getVentasById(Integer id) {
        return ventaRepositorio.findById(id);
    }

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
        ventaRepositorio.save(venta);
        return venta;
    }
}
