package uv.mx.ventas;

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

//Microservicio generado por Santiago Emmanuel Chávez Murrieta

/* Las siguientes anotaciones nos ayudan a mapear las rutas URL y métodos para gestionar las solicitudes y respuestas HTTP */
@RestController
/* Definimos nuestra RUTA BASE */
@RequestMapping("/tienda/ventas")
public class VentaController {
    @Autowired
    private VentaServicio ventaServicio;

    /* En esta sección utilizamos el GetMapping para obtener datos con el verbo GET */
    @GetMapping
    public ArrayList<Venta> getVentas() {
        return this.ventaServicio.getVentas();
    }

    @GetMapping(path = "/{id}")
    public Optional<Venta> getVentasById(@PathVariable("id") Integer id) {
        return this.ventaServicio.getVentasById(id);
    }

    @GetMapping(path = "/cliente/{nombre}")
    public List<Venta> getVentasByNameClient(@PathVariable("nombre") String nombre) {
        return this.ventaServicio.getVentasByNameClient(nombre);
    }

    @GetMapping(path = "/fecha/{fecha}")
    public List<Venta> getVentasByFecha(@PathVariable("fecha") String fecha) {
        return this.ventaServicio.getVentasByFecha(fecha);
    }

    @GetMapping(path = "/mayores/{valor}")
    public ArrayList<Venta> getVentasByMayores(@PathVariable Float valor) {
        return this.ventaServicio.getVentasByVentasMayores(valor);
    }

    @GetMapping(path = "/menores/{valor}")
    public ArrayList<Venta> getVentasByMenores(@PathVariable Float valor) {
        return this.ventaServicio.getVentasByVentasMenores(valor);
    }

    /* En esta sección utilizamos el postMapping para manejar solicitudes de creación de recurso */
    @PostMapping
    public Venta guardarVenta(@RequestBody Venta venta) {
        return this.ventaServicio.guardarVenta(venta);
    }


    /* DeleteMapping para eliminar un registro mediante un valor despues de nuestra ruta global */
    @DeleteMapping(path = "/{id}")
    /* Indicamos que id de nuestra ruta id lo utilice el Integer id */
    public String eliminarVenta(@PathVariable("id") Integer id) {
        boolean ok = this.ventaServicio.eliminarVenta(id);
        if (ok) {
            return "se ha eliminado correctamente";
        } else {
            return "Error al eliminar la venta";
        }
    }

    /* Funciona para editar un registro de nuestra base de datos mediante la variable id */
    @PutMapping(path = "/{id}")
    public Venta editarVenta(@RequestBody Venta venta, @PathVariable("id") Integer id) {
        return this.ventaServicio.editarVenta(venta, id);
    }

}
