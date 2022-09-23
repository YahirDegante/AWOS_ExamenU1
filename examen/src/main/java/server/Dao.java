package server;

import utils.MySQLConnetion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class Dao {
    public boolean registerPersons(String ap, String am, String nombre, String curp, String rfc, String fecha){
        boolean result = false;

        try(
                Connection con = new MySQLConnetion().getConnection();
                PreparedStatement pstm = con.prepareStatement("INSERT INTO examen values(?,?,?,?,?,?)");
        ){
            pstm.setString(1,nombre);
            pstm.setString(2, ap);
            pstm.setString(3,am);
            pstm.setString(4,rfc);
            pstm.setString(5,curp);
            pstm.setString(6, fecha);
            result = pstm.executeUpdate() == 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public static Bean infoPersona(String curp) {

        Bean usuario = new Bean();

        try(
                Connection con = new MySQLConnetion().getConnection();
                PreparedStatement pstm = con.prepareStatement("SELECT * FROM examen WHERE curp=?");
        ) {
            pstm.setString(1,curp);
            ResultSet resultSet = pstm.executeQuery();

            while (resultSet.next()){
                usuario.setAp(resultSet.getString("apelidopaterno"));
                usuario.setAm(resultSet.getString("apellidomaterno"));
                usuario.setNombre(resultSet.getString("nombre"));
                usuario.setRfc(resultSet.getString("rfc"));
                usuario.setCurp(resultSet.getString("curp"));
                usuario.setFecha(resultSet.getString("fechanac"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return usuario;
    }

    public boolean verificar(String curp) {
        boolean curpexiste = false;
        try(
                Connection con = new MySQLConnetion().getConnection();
                PreparedStatement pstm = con.prepareStatement("SELECT * FROM examen WHERE curp=?");
        ) {
            pstm.setString(1,curp);
            ResultSet resultSet = pstm.executeQuery();
            curpexiste = resultSet.next();
        } catch (Exception e){
            e.printStackTrace();
        }
        return curpexiste;
    }
}
