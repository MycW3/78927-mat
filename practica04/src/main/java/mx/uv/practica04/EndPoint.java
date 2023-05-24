package mx.uv.practica04;

import java.io.Console;
import java.io.ObjectInputStream.GetField;
import java.util.Optional;

import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.fasterxml.jackson.core.sym.Name;

import https.t4is_uv_mx.saludos.EliminarRequest;
import https.t4is_uv_mx.saludos.EliminarResponse;
import https.t4is_uv_mx.saludos.MostrarRequest;
import https.t4is_uv_mx.saludos.MostrarResponse;
import https.t4is_uv_mx.saludos.SaludarRequest;
import https.t4is_uv_mx.saludos.SaludarResponse;
import https.t4is_uv_mx.saludos.ModificarRequest;
import https.t4is_uv_mx.saludos.ModificarResponse;

@Endpoint
public class EndPoint {

    @Autowired
    private ISaludador iSaludador;

    @PayloadRoot(localPart = "SaludarRequest", namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public SaludarResponse Saludar(@RequestPayload SaludarRequest peticion) {
        SaludarResponse response = new SaludarResponse();
        response.setRespuesta("Hola " + peticion.getNombre());

        // persistencia a la bd
        Saludador saludador = new Saludador();
        saludador.setNombre(peticion.getNombre());
        iSaludador.save(saludador);

        return response;
    }

    @PayloadRoot(localPart = "ModificarRequest", namespace="https://t4is.uv.mx/saludos")
    @ResponsePayload
    public ModificarResponse Modificar( @RequestPayload  ModificarRequest peticion) {
        ModificarResponse response= new ModificarResponse();

        //Saludador saludador = new Saludador();
        //saludador.setId(peticion.getId());
        //saludador.setNombre(peticion.getNombre());
        Optional<Saludador> x = iSaludador.findById(peticion.getId());
        Saludador y = x.get();
        y.setNombre(peticion.getNombre());
        iSaludador.save(y);
        response.setNombre(y.getId() + ": "+ y.getNombre());
        return response;
    }

    @PayloadRoot(localPart = "EliminarRequest", namespace="https://t4is.uv.mx/saludos")
    @ResponsePayload
    public EliminarResponse Eliminar( @RequestPayload  EliminarRequest peticion) {
        EliminarResponse response= new EliminarResponse();
        Optional<Saludador> x = iSaludador.findById(peticion.getId());
        Saludador y = x.get();
        iSaludador.deleteById(peticion.getId());
        response.setNombre("Eliminado: "+ y.getNombre());
        return response;
    }

    @PayloadRoot(localPart = "MostrarRequest", namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public MostrarResponse Mostrar(@RequestPayload MostrarRequest peticion) {
        MostrarResponse response = new MostrarResponse();
        String listaString="id:  nombre\n";
        Iterable<Saludador> saludadores = iSaludador.findAll();
        for (Saludador x : saludadores){
            listaString = listaString + x.getId() + ": " + x .getNombre() + "\n";
        }
        response.setNombre(listaString);
        return response;
    }

}
