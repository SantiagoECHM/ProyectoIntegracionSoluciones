package uv.mx.empleados;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/* Esta clase JPA (Java Persistence API) que representa una tabla en una base de datos relacional. */
/* Esta anotación indica que la clase Empleado es una entidad que se mapeará a una tabla en la base de datos */
@Entity
/* como se llamara la tabla */
@Table(name = "empleados")
public class Empleado {
    /* indica que la propiedad id es la clave primaria de la entidad Empleado */
    @Id
    /*
     * se utiliza para especificar cómo se generará automáticamente el valor de la
     * clave primaria.
     */
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nombre;
    private String puesto;
    private String turno;
    private Float sueldo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public Float getSueldo() {
        return sueldo;
    }

    public void setSueldo(Float sueldo) {
        this.sueldo = sueldo;
    }
}
