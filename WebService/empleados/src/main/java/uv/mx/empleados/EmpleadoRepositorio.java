package uv.mx.empleados;

import org.springframework.data.repository.CrudRepository;

//Interfaz para acceder a las operaciones de persistencia relacionadas con los empleados en la base de datos utilizando Spring Data JPA
public interface EmpleadoRepositorio extends CrudRepository<Empleado, Integer> {

}
