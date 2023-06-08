package uv.mx.empleados;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import https.t4is_mx_uv.empleados.BorrarEmpleadoRequest;
import https.t4is_mx_uv.empleados.BorrarEmpleadoResponse;
import https.t4is_mx_uv.empleados.BuscarEmpleadoRequest;
import https.t4is_mx_uv.empleados.BuscarEmpleadoResponse;
import https.t4is_mx_uv.empleados.EditarEmpleadoRequest;
import https.t4is_mx_uv.empleados.EditarEmpleadoResponse;
import https.t4is_mx_uv.empleados.MostrarEmpleadosResponse;
import https.t4is_mx_uv.empleados.RegistrarEmpleadoRequest;
import https.t4is_mx_uv.empleados.RegistrarEmpleadoResponse;
import https.t4is_mx_uv.empleados.BuscarEmpleadoResponse.RespuestaBuscar;
import https.t4is_mx_uv.empleados.MostrarEmpleadosResponse.Respuesta;;

/* componente que maneja las solicitudes entrantes y genera las respuestas correspondientes. Proporciona la lógica para procesar las solicitudes y realizar las operaciones requeridas. */
@Endpoint
public class EndPoint {
    /* Para el namespace uri se generará a partir de la uri del namespace que estará nuestro esquema.xsd para conectar */
    private static final String nameSpace_uri = "https://t4is.mx.uv/empleados";
    private static final String respuestaMal = "[string]";
    private String nombre, turno, puesto;
    private Float sueldo;

    /*
     * Se utiliza para inyectar automáticamente dependencias en un componente de
     * Spring, como una clase de servicio, un controlador o un punto final.
     */
    @Autowired
    private EmpleadoRepositorio empleadoRepositorio;

    /*
     * para mapear una solicitud entrante a un método específico en un punto final
     * (endpoint) basado en el elemento raíz y el espacio de nombres de la
     * solicitud.
     */
    @PayloadRoot(localPart = "RegistrarEmpleadoRequest", namespace = nameSpace_uri)
    @ResponsePayload
     /* Al recibir un request lo tomamos como variable peticion que se tomará cuando ingresemos datos*/
    public RegistrarEmpleadoResponse RegistrarEmpleado(@RequestPayload RegistrarEmpleadoRequest peticion) {
        /* En este apartado respuesta se crea a partir de nuestro elemento RegistrarEmpleadoResponse que tiene como elemennto respuesta de nombre en el cual pondremos un string */
        RegistrarEmpleadoResponse respuesta = new RegistrarEmpleadoResponse();
        Empleado empleado = new Empleado();

       /* En este apartado se obtienen los datos de la petición */
        nombre = peticion.getNombre();
        puesto = peticion.getPuesto();
        turno = peticion.getTurno();
        sueldo = peticion.getSueldo();

        /* Se hace una condicional para retroalimentar si hay o no datos en blancos */
        if (nombre.contains(respuestaMal) || turno.contains(respuestaMal) || puesto.contains(respuestaMal)
                || sueldo == 0 || nombre.isEmpty() || turno.isEmpty() || puesto.isEmpty()) {
            respuesta.setRespuesta("Verifique que los datos esten llenos");
        } else {
            /* Si no se cumple la condición de la entidad empleado se estableceran sus atributos */
            empleado.setNombre(nombre);
            empleado.setPuesto(puesto);
            empleado.setTurno(turno);
            empleado.setSueldo(sueldo);

            /* .save para guardar en base de datos, guardará el empleado con todos sus atributos */
            empleadoRepositorio.save(empleado);

            /* Al realizar la función mandará en post del wsdl un mensaje dentro de respuesta */
            respuesta.setRespuesta("Se ha registrado el empleado con el id: " + empleado.getId());
        }

        return respuesta;
    }

    @PayloadRoot(localPart = "EditarEmpleadoRequest", namespace = nameSpace_uri)
    @ResponsePayload
    public EditarEmpleadoResponse EditarEmpleado(@RequestPayload EditarEmpleadoRequest peticion) {
        EditarEmpleadoResponse respuesta = new EditarEmpleadoResponse();
        /* Método proporcionado por JPA que ayuda a encontrar datos por el id en la base de datos, en este caso desde nuestra peticion obtendremos el id ingresado */
        Empleado empleado = empleadoRepositorio.findById(peticion.getId()).get();

        nombre = peticion.getNombre();
        puesto = peticion.getPuesto();
        turno = peticion.getTurno();
        sueldo = peticion.getSueldo();

        if (peticion.getId() == empleado.getId().intValue()
                && !(nombre.contains(respuestaMal) || turno.contains(respuestaMal) || puesto.contains(respuestaMal)
                        || sueldo == 0 || nombre.isEmpty() || turno.isEmpty() || puesto.isEmpty())) {
            empleado.setNombre(nombre);
            empleado.setPuesto(puesto);
            empleado.setTurno(turno);
            empleado.setSueldo(sueldo);
            empleadoRepositorio.save(empleado);
            respuesta.setRespuesta(
                    "Se ha actualizado correctamente el empleado con id " + peticion.getId() + " "
                            + peticion.getNombre());
        } else {
            respuesta.setRespuesta("Asegurese de que los campos esten llenos y el id sea correcto");
        }

        return respuesta;
    }

    @PayloadRoot(localPart = "BorrarEmpleadoRequest", namespace = nameSpace_uri)
    @ResponsePayload
    public BorrarEmpleadoResponse BorrarEmpleadoPorId(@RequestPayload BorrarEmpleadoRequest peticion) {
        BorrarEmpleadoResponse respuesta = new BorrarEmpleadoResponse();

        /* Se le pide un id y ñuego se eliminará una vez encontrado con el id */
        Empleado empleado = empleadoRepositorio.findById(peticion.getId()).get();
        respuesta.setRespuesta(
                "Se ha borrado correctamente el empleado con el ID: " + peticion.getId() + " " + empleado.getNombre());
        /* Para poder borrar en base de datos se utiliza el método .delete y dentro la entidad a borrar */
        empleadoRepositorio.delete(empleado);
        return respuesta;
    }

    @PayloadRoot(localPart = "BuscarEmpleadoRequest", namespace = nameSpace_uri)
    @ResponsePayload
    public BuscarEmpleadoResponse BuscarEmpleado(@RequestPayload BuscarEmpleadoRequest peticion) {
        BuscarEmpleadoResponse respuesta = new BuscarEmpleadoResponse();
        RespuestaBuscar respuestaBuscar = new RespuestaBuscar();
        Empleado empleado;
        empleado = empleadoRepositorio.findById(peticion.getId()).get();

        /* En este apartado mostraremos nuestro elemento con sus elementos, desde una ArrayList para mostrar más de uno osea el maxOccurs que veríamos en el esquema.xsd */
        respuestaBuscar.setId(empleado.getId());
        respuestaBuscar.setNombre(empleado.getNombre());
        respuestaBuscar.setPuesto(empleado.getPuesto());
        respuestaBuscar.setTurno(empleado.getTurno());
        respuestaBuscar.setSueldo(empleado.getSueldo());

        /* Dentro de respuesta tendremos que enviarle un objeto respuestaBuscar que funciona como un arrayList que desplegara elemento por elemento en lugar de solo uno */
        respuesta.setRespuestaBuscar(respuestaBuscar);

        return respuesta;
    }

    @PayloadRoot(localPart = "MostrarEmpleadosRequest", namespace = nameSpace_uri)
    @ResponsePayload
    /* No recibe ningún request o petición, simplemente requiere de un response que mostrara datos sin recibir nada a cambio */
    public MostrarEmpleadosResponse MostrarEmpleados() {
        MostrarEmpleadosResponse respuesta = new MostrarEmpleadosResponse();
        ArrayList<Empleado> empleado;
        /* Para encontrar a todos los empleados utilizamos un casteo de ArrayList empleado y buscando cada empleado un método findAll que Buscará a todos */
        empleado = (ArrayList<Empleado>) empleadoRepositorio.findAll();

       /* Para que muestre cada dato debemos saber el tamaño de nuestro tamaño del empleado y seguido de establecer sus atributos */
        for (int i = 0; i < empleado.size(); i++) {
            Respuesta respuesta2 = new Respuesta();
            respuesta2.setId(empleado.get(i).getId());
            respuesta2.setNombre(empleado.get(i).getNombre());
            respuesta2.setPuesto(empleado.get(i).getPuesto());
            respuesta2.setTurno(empleado.get(i).getTurno());
            respuesta2.setSueldo(empleado.get(i).getSueldo());

            /* Por ultimo se le agrega una respuesta a nuestra respuesta que en este caso en un getRespuesta en lugar de setRepsuesta, ya que estamos trabajando con MxOccurs=unbounded */
            respuesta.getRespuesta().add(respuesta2);
        }

        return respuesta;
    }
}