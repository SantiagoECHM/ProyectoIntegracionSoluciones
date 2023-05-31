package uv.mx.libros;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import https.t4is_mx_uv.libros.BorrarLibroRequest;
import https.t4is_mx_uv.libros.BorrarLibroResponse;
import https.t4is_mx_uv.libros.BuscarLibroRequest;
import https.t4is_mx_uv.libros.BuscarLibroResponse;
import https.t4is_mx_uv.libros.EditarLibroRequest;
import https.t4is_mx_uv.libros.EditarLibroResponse;
import https.t4is_mx_uv.libros.MostrarLibrosResponse;
import https.t4is_mx_uv.libros.RegistrarLibroRequest;
import https.t4is_mx_uv.libros.RegistrarLibroResponse;
import https.t4is_mx_uv.libros.MostrarLibrosResponse.Respuesta;
import https.t4is_mx_uv.libros.BuscarLibroResponse.RespuestaBuscar;;

@Endpoint
public class EndPoint {
    private static final String nameSpace_uri = "https://t4is.mx.uv/libros";
    private static final String respuestaMal = "[string]";
    private String titulo, categoria, autor;
    private Float precio;
    @Autowired
    private LibroRepositorio libroRepositorio;

    @PayloadRoot(localPart = "RegistrarLibroRequest", namespace = nameSpace_uri)
    @ResponsePayload
    public RegistrarLibroResponse RegistrarLibro(@RequestPayload RegistrarLibroRequest peticion) {
        RegistrarLibroResponse respuesta = new RegistrarLibroResponse();
        Libro libro = new Libro();

        titulo = peticion.getTitulo();
        autor = peticion.getAutor();
        categoria = peticion.getCategoria();
        precio = peticion.getPrecio();

        if (titulo.contains(respuestaMal) || categoria.contains(respuestaMal) || autor.contains(respuestaMal)
                || precio == 0 || titulo.isEmpty() || categoria.isEmpty() || autor.isEmpty()) {
            respuesta.setRespuesta("Verifique que los datos esten llenos");
        } else {
            libro.setTitulo(titulo);
            libro.setAutor(autor);
            libro.setCategoria(categoria);
            libro.setPrecio(precio);

            libroRepositorio.save(libro);

            respuesta.setRespuesta("Se ha registrado el libro con el id: " + libro.getId());
        }

        return respuesta;
    }

    @PayloadRoot(localPart = "EditarLibroRequest", namespace = nameSpace_uri)
    @ResponsePayload
    public EditarLibroResponse EditarLibro(@RequestPayload EditarLibroRequest peticion) {
        EditarLibroResponse respuesta = new EditarLibroResponse();
        Libro libro = libroRepositorio.findById(peticion.getId()).get();

        titulo = peticion.getTitulo();
        autor = peticion.getAutor();
        categoria = peticion.getCategoria();
        precio = peticion.getPrecio();

        if (peticion.getId() == libro.getId().intValue()
                && !(titulo.contains(respuestaMal) || categoria.contains(respuestaMal) || autor.contains(respuestaMal)
                        || precio == 0 || titulo.isEmpty() || categoria.isEmpty() || autor.isEmpty())) {
            libro.setTitulo(titulo);
            libro.setAutor(autor);
            libro.setCategoria(categoria);
            libro.setPrecio(precio);
            libroRepositorio.save(libro);
            respuesta.setRespuesta(
                    "Se ha actualizado correctamente el libro con id " + peticion.getId() + " " + peticion.getTitulo());
        } else {
            respuesta.setRespuesta("Asegurese de que los campos esten llenos y el id sea correcto");
        }

        return respuesta;
    }

    @PayloadRoot(localPart = "BorrarLibroRequest", namespace = nameSpace_uri)
    @ResponsePayload
    public BorrarLibroResponse BorrarLibroPorId(@RequestPayload BorrarLibroRequest peticion) {
        BorrarLibroResponse respuesta = new BorrarLibroResponse();

        Libro libro = libroRepositorio.findById(peticion.getId()).get();
        respuesta.setRespuesta(
                "Se ha borrado correctamente el libro con el ID: " + peticion.getId() + " " + libro.getTitulo());
        libroRepositorio.delete(libro);
        return respuesta;
    }

    @PayloadRoot(localPart = "BuscarLibroRequest", namespace = nameSpace_uri)
    @ResponsePayload
    public BuscarLibroResponse BuscarLibro(@RequestPayload BuscarLibroRequest peticion) {
        BuscarLibroResponse respuesta = new BuscarLibroResponse();
        RespuestaBuscar respuestaBuscar = new RespuestaBuscar();
        Libro libro;
        libro = libroRepositorio.findById(peticion.getId()).get();

        respuestaBuscar.setId(libro.getId());
        respuestaBuscar.setTitulo(libro.getTitulo());
        respuestaBuscar.setAutor(libro.getAutor());
        respuestaBuscar.setCategoria(libro.getCategoria());
        respuestaBuscar.setPrecio(libro.getPrecio());

        respuesta.setRespuestaBuscar(respuestaBuscar);

        return respuesta;
    }

    @PayloadRoot(localPart = "MostrarLibrosRequest", namespace = nameSpace_uri)
    @ResponsePayload
    public MostrarLibrosResponse MostrarLibros() {
        MostrarLibrosResponse respuesta = new MostrarLibrosResponse();
        ArrayList<Libro> libro;
        libro = (ArrayList<Libro>) libroRepositorio.findAll();

        for (int i = 0; i < libro.size(); i++) {
            Respuesta respuesta2 = new Respuesta();
            respuesta2.setId(libro.get(i).getId());
            respuesta2.setTitulo(libro.get(i).getTitulo());
            respuesta2.setAutor(libro.get(i).getAutor());
            respuesta2.setCategoria(libro.get(i).getCategoria());
            respuesta2.setPrecio(libro.get(i).getPrecio());

            respuesta.getRespuesta().add(respuesta2);
        }

        return respuesta;
    }
}