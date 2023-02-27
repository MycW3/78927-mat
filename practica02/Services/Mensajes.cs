using System;
using System.ServiceModel;

namespace WSDL.mensajes // "mensajes" nombre de la clase
{
    [ServiceContract] //anotación para que sea una super clase la clase sea un contrato de servicio
    public interface Mensajes{ // "mensajes "nombre de la clase
        [OperationContract] //operacion del contrato para que cumpla un proposito 
        string Saludar(string nombre); //metodo saludar
        [OperationContract]
        string Mostrar(int id); //metodo mostrar
    }
}

//contrato es un wisdol