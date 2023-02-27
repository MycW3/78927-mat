using System;
using WSDL.mensajes; //se usa el namespace de mensajes

namespace WSDL.operaciones
{
    public class Operaciones : Mensajes 
    {
        public string Saludar(string nombre)
        {
            string msj = "Hola" + nombre;
            return msj;
        }
        public string Mostrar(int id){
            return "x";
        }
    }
}