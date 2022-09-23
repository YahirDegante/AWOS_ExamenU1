package client;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class RPC_Client {
    static  Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws IOException, XmlRpcException {
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setServerURL(new URL("http://localhost:1200"));
        config.isEnabledForExtensions();
        XmlRpcClient client = new XmlRpcClient();
        client.setConfig(config);

        String opcion;
        String response;

            System.out.println("El siguiente programa realiza las siguientes opciones: ");
            System.out.println("1. Generarador RFC ");
            System.out.println("2. Consultar Persona");
            System.out.println("3. Salir");
            System.out.print("Selecciona una opcion: ");
            opcion = sc.next();
                switch (Integer.parseInt(opcion)) {
                    case 1:
                        System.out.println("Generador de RFC por favor ingresa los datos: ");
                            System.out.print("Ingresa tu Apellido Paterno: ");
                            String ap = sc.next();
                            System.out.print("Ingresa tu Apellido Materno: ");
                            String am = sc.next();
                            System.out.print("Ingresa tu nombre(s): ");
                            String nombre = sc.next();
                            System.out.print("Ingresa tu CURP: ");
                            String curp = sc.next();
                            System.out.println("Ingresa tu fecha de nacimiento: ");
                            System.out.print("Año (Solo los ultimos 2 digitos): ");
                            String anio = sc.next();
                            System.out.print("Mes: ");
                            String mes = sc.next();
                            System.out.print("Dia: ");
                            String dia = sc.next();
                        Object[] userInfo = {ap, am,nombre, curp,anio,mes,dia};
                        response =  (String) client.execute("Methods.generador", userInfo);
                        System.out.println("RFC GENERADO");
                        System.out.println(response);
                        break;

                    case 2:
                        System.out.println("Consulta de los datos de la persona por medio del CURP");
                        System.out.println("Ingrese el curp de la persona a buscar:");
                        String pcurp = sc.next();
                        Object[] searchInfo = {pcurp};
                        response =  (String) client.execute("Methods.infopersona", searchInfo);
                        System.out.println("");
                        System.out.println(response);
                        System.out.println("");
                        break;
                    default:
                        System.out.println("No existe esa opcion!");

        } while (!opcion.equals("3"));
                System.out.println("Has salido del programa!");
}
}