package uv.mx.libros;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/* Esta clase JPA (Java Persistence API) que representa una tabla en una base de datos relacional. */
/* Esta anotación indica que la clase Empleado es una entidad que se mapeará a una tabla en la base de datos */
@Entity
/* como se llamara la tabla */
@Table(name = "libros")
public class Libro {
    /* indica que la propiedad id es la clave primaria de la entidad Empleado */
    @Id
    /*
     * se utiliza para especificar cómo se generará automáticamente el valor de la
     * clave primaria.
     */
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String categoria;
    private String autor;
    private String titulo;
    private Float precio;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }
}
