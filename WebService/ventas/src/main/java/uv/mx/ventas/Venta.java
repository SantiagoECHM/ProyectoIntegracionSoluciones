package uv.mx.ventas;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

/* Esta clase JPA (Java Persistence API) que representa una tabla en una base de datos relacional. */
/* Esta anotación indica que la clase Empleado es una entidad que se mapeará a una tabla en la base de datos */
@Entity
public class Venta {
    /* indica que la propiedad id es la clave primaria de la entidad Empleado */
    @Id
    /*
     * se utiliza para especificar cómo se generará automáticamente el valor de la
     * clave primaria.
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombreCliente;
    private String metodoDePago;
    private String fecha;
    private Float montoRecibido;
    private Float precioTotal;
    private Float cambio;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getMetodoDePago() {
        return metodoDePago;
    }

    public void setMetodoDePago(String metodoDePago) {
        this.metodoDePago = metodoDePago;
    }

    public Float getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Float precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Float getMontoRecibido() {
        return montoRecibido;
    }

    public void setMontoRecibido(Float montoRecibido) {
        this.montoRecibido = montoRecibido;
    }

    /* El cambio se creará a partir del montoRecibido-precioTotal */
    public Float getCambio() {
        cambio = montoRecibido - precioTotal;
        return cambio;
    }

    public void setCambio(Float cambio) {
        this.cambio = cambio;
    }
}
