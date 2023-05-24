package mx.uv.practica03;

import java.util.ArrayList;

import javax.xml.ws.Response;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import https.t4is_uv_mx.saludos.BuscarRequest;
import https.t4is_uv_mx.saludos.BuscarResponse;
import https.t4is_uv_mx.saludos.EliminarRequest;
import https.t4is_uv_mx.saludos.EliminarResponse;
import https.t4is_uv_mx.saludos.MostrarRequest;
import https.t4is_uv_mx.saludos.MostrarResponse;
import https.t4is_uv_mx.saludos.SaludarRequest;
import https.t4is_uv_mx.saludos.SaludarResponse;

@Endpoint
public class EndPoint {

    public static ArrayList<String> saludos = new ArrayList<>();

    @PayloadRoot(localPart = "SaludarRequest", namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public SaludarResponse Saludar(@RequestPayload SaludarRequest peticion) {
        SaludarResponse response = new SaludarResponse();
        response.setRespuesta("Hola " + peticion.getNombre());
        saludos.add(peticion.getNombre());
        return response;
    }

    /**
     * @param peticion
     * @return
     */
    @PayloadRoot(localPart = "MostrarRequest", namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public MostrarResponse Mostrar(@RequestPayload MostrarRequest peticion) {
        MostrarResponse response = new MostrarResponse();
        if (peticion.getId() >= saludos.size())
            response.setNombre("El id no valido");
        else
            response.setNombre(saludos.get(peticion.getId()));
        return response;
    }

    /**
     * @param peticion
     * @return
     */
    @PayloadRoot(localPart = "EliminarRequest", namespace="https://t4is.uv.mx/saludos")
    @ResponsePayload
    public EliminarResponse Eliminar( @RequestPayload  EliminarRequest peticion) {
        EliminarResponse response= new EliminarResponse();
        if(peticion.getId() < saludos.size()){
            response.setNombre("eliminado: " + saludos.get(peticion.getId()));
            saludos.remove(peticion.getId());
        }
        else
            response.setNombre("El id no valido");
        return response;
    }

    /**
     * @param peticion
     * @return
     */
    @PayloadRoot(localPart = "BuscarRequest", namespace="https://t4is.uv.mx/saludos")
    @ResponsePayload
    public BuscarResponse Buscar( @RequestPayload  BuscarRequest peticion) {
        final BuscarResponse response= new BuscarResponse();
        int posicion = saludos.indexOf(peticion.getNombre());
        if(posicion >= 0){
            response.setNombre("El elemento " + peticion.getNombre() + " está en la lista. En la posición " + posicion);
        }
        else
            response.setNombre("El elemento " + peticion.getNombre() + " NO está en la lista");
        return response;
    }

}
