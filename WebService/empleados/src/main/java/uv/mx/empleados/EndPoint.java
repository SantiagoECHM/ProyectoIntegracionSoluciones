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

@Endpoint
public class EndPoint {
    private static final String nameSpace_uri = "https://t4is.mx.uv/empleados";
    private static final String respuestaMal = "[string]";
    private String nombre, turno, puesto;
    private Float sueldo;
    @Autowired
    private EmpleadoRepositorio empleadoRepositorio;

    @PayloadRoot(localPart = "RegistrarEmpleadoRequest", namespace = nameSpace_uri)
    @ResponsePayload
    public RegistrarEmpleadoResponse RegistrarEmpleado(@RequestPayload RegistrarEmpleadoRequest peticion) {
        RegistrarEmpleadoResponse respuesta = new RegistrarEmpleadoResponse();
        Empleado empleado = new Empleado();

        nombre = peticion.getNombre();
        puesto = peticion.getPuesto();
        turno = peticion.getTurno();
        sueldo = peticion.getSueldo();

        if (nombre.contains(respuestaMal) || turno.contains(respuestaMal) || puesto.contains(respuestaMal)
                || sueldo == 0 || nombre.isEmpty() || turno.isEmpty() || puesto.isEmpty()) {
            respuesta.setRespuesta("Verifique que los datos esten llenos");
        } else {
            empleado.setNombre(nombre);
            empleado.setPuesto(puesto);
            empleado.setTurno(turno);
            empleado.setSueldo(sueldo);

            empleadoRepositorio.save(empleado);

            respuesta.setRespuesta("Se ha registrado el empleado con el id: " + empleado.getId());
        }

        return respuesta;
    }

    @PayloadRoot(localPart = "EditarEmpleadoRequest", namespace = nameSpace_uri)
    @ResponsePayload
    public EditarEmpleadoResponse EditarEmpleado(@RequestPayload EditarEmpleadoRequest peticion) {
        EditarEmpleadoResponse respuesta = new EditarEmpleadoResponse();
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
                    "Se ha actualizado correctamente el empleado con id " + peticion.getId() + " " + peticion.getNombre());
        } else {
            respuesta.setRespuesta("Asegurese de que los campos esten llenos y el id sea correcto");
        }

        return respuesta;
    }

    @PayloadRoot(localPart = "BorrarEmpleadoRequest", namespace = nameSpace_uri)
    @ResponsePayload
    public BorrarEmpleadoResponse BorrarEmpleadoPorId(@RequestPayload BorrarEmpleadoRequest peticion) {
        BorrarEmpleadoResponse respuesta = new BorrarEmpleadoResponse();

        Empleado empleado = empleadoRepositorio.findById(peticion.getId()).get();
        respuesta.setRespuesta(
                "Se ha borrado correctamente el empleado con el ID: " + peticion.getId() + " " + empleado.getNombre());
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

        respuestaBuscar.setId(empleado.getId());
        respuestaBuscar.setNombre(empleado.getNombre());
        respuestaBuscar.setPuesto(empleado.getPuesto());
        respuestaBuscar.setTurno(empleado.getTurno());
        respuestaBuscar.setSueldo(empleado.getSueldo());

        respuesta.setRespuestaBuscar(respuestaBuscar);

        return respuesta;
    }

    @PayloadRoot(localPart = "MostrarEmpleadosRequest", namespace = nameSpace_uri)
    @ResponsePayload
    public MostrarEmpleadosResponse MostrarEmpleados() {
        MostrarEmpleadosResponse respuesta = new MostrarEmpleadosResponse();
        ArrayList<Empleado> empleado;
        empleado = (ArrayList<Empleado>) empleadoRepositorio.findAll();

        for (int i = 0; i < empleado.size(); i++) {
            Respuesta respuesta2 = new Respuesta();
            respuesta2.setId(empleado.get(i).getId());
            respuesta2.setNombre(empleado.get(i).getNombre());
            respuesta2.setPuesto(empleado.get(i).getPuesto());
            respuesta2.setTurno(empleado.get(i).getTurno());
            respuesta2.setSueldo(empleado.get(i).getSueldo());

            respuesta.getRespuesta().add(respuesta2);
        }

        return respuesta;
    }
}