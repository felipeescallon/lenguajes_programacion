package holaComponentes;
import javax.naming.*;
import javax.rmi.*;

public class MiPrimerCliente {
public MiPrimerCliente() {
}
public static void main(String[] args) {
try{
Context initial = new InitialContext();	//se pide el contexto inicial
System.out.println("Trayendo el contexto");
Object objref= initial.lookup("JNDIHOLAMUNDO");
System.out.println("Trayendo Interface remote");
HolaEJBHome holaHome=(HolaEJBHome)

javax.rmi.PortableRemoteObject.narrow(objref,HolaEJBHome.class);
System.out.println("Creando el bean");
HolaEJB hola = holaHome.create();	//me devuelve un objeto que responde 
					//a metodos de negocio
System.out.println("Llamando el método remoto");
System.out.println(hola.saludar());
hola.remove();
}
catch(Exception ex){
System.out.println("Mi primer error de
Componentes"+ex.getMessage());
}
}
}