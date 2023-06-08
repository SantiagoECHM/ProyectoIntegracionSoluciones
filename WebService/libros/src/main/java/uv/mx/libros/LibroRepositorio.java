package uv.mx.libros;

import org.springframework.data.repository.CrudRepository;

//Interfaz para acceder a las operaciones de persistencia relacionadas con los empleados en la base de datos utilizando Spring Data JPA
public interface LibroRepositorio extends CrudRepository<Libro, Integer> {

}
