package server;

import java.util.List;
public class Methods {

    public String generador(String ap, String am, String nombre, String curp, String anio,String mes,String dia) {
        String rfcName = "" + ap.charAt(0) + ap.charAt(1) + am.charAt(0) + nombre.charAt(0);
        String letras = "abcdefghijklmnopqrstuvwxyz";
        String clavel = "";
        for (int i = 0; i < 2; i++) {
            int randomLetters = (int) (Math.random() * (letras.length()) - 1);
            clavel += letras.charAt(randomLetters);
        }
        String numeros = "123456789";
        String claven = "";
        for (int i = 0; i < 1; i++) {
            int randomNumber = (int) (Math.random() * (numeros.length()) - 1);
            claven += numeros.charAt(randomNumber);
        }
        String fecha=(anio+mes+dia);
        String rfc = (rfcName +fecha+ clavel +claven+ "").toUpperCase();
        return "Felicidades "+ nombre+" su RFC es: " + rfc;
    }

    private boolean verificar(String curp) {
        Dao dao = new Dao();
        return dao.verificar(curp);
    }
    public String infoPersona(String curp) {
        boolean exist = verificar(curp);
        if (!exist) {
            return "El CURP no existe";
        }
        Dao dao = new Dao();
        Bean person = Dao.infoPersona(curp);
        String result = person.toString();
        return result;
    }
}

