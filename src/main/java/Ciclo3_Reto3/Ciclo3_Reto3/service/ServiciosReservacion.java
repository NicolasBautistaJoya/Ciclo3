/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ciclo3_Reto3.Ciclo3_Reto3.service;

import Ciclo3_Reto3.Ciclo3_Reto3.ContadorClientes;
  import Ciclo3_Reto3.Ciclo3_Reto3.model.Reservacion;
import Ciclo3_Reto3.Ciclo3_Reto3.reportes.StatusReservas;
import Ciclo3_Reto3.Ciclo3_Reto3.repository.ReservacionRepositorio;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nicolas
 */
@Service
public class ServiciosReservacion {
    
    //Declaracion de metoddos crud
    @Autowired    
    /**Declaracion de metoddos crud**/
    private ReservacionRepositorio metodosCrud;
    
    //metodo paara obtener todas las variables
    /**metodo paara obtener todas las variables**/
    public List<Reservacion> getAll(){
        return metodosCrud.getAll();
    }

    //metodo para obtener el id de la reservacion
    /**metodo para obtener el id de la reservacion**/
    public Optional<Reservacion> getReservation(int reservationId) {
        return metodosCrud.getReservation(reservationId);
    }

    //metodo para guardar una nueva reservacion
    /**metodo para guardar una nueva reservacion**/
    public Reservacion save(Reservacion reservation){
        if(reservation.getIdReservation()==null){
            return metodosCrud.save(reservation);
        }else{
            Optional<Reservacion> evt= metodosCrud.getReservation(reservation.getIdReservation());
            if(evt.isEmpty()){
                return metodosCrud.save(reservation);
            }else{
                return reservation;
            }
        }
    }

    //metodo para actualizar una reservacion
    /**metodo para actualizar una reservacion**/
    public Reservacion update(Reservacion reservation){
        if(reservation.getIdReservation()!=null){
            Optional<Reservacion> evt= metodosCrud.getReservation(reservation.getIdReservation());
            if(!evt.isEmpty()){

                if(reservation.getStartDate()!=null){
                    evt.get().setStartDate(reservation.getStartDate());
                }
                if(reservation.getDevolutionDate()!=null){
                    evt.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if(reservation.getStatus()!=null){
                    evt.get().setStatus(reservation.getStatus());
                }
                metodosCrud.save(evt.get());
                return evt.get();
            }else{
                return reservation;
            }
        }else{
            return reservation;
        }
    }

    //metodo para borrar una reservacion
    /**metodo para borrar una reservacion**/
    public boolean deleteReservation(int reservationId) {
        Boolean aBoolean = getReservation(reservationId).map(reservation -> {
            metodosCrud.delete(reservation);
            return true;
        }).orElse(false);
        return aBoolean;
    }
    
    public StatusReservas  statusReporte () {
        List<Reservacion>completada=metodosCrud.StatusReservacion("completed");
        List<Reservacion>cancelada=metodosCrud.StatusReservacion("cancelled");
        
        return new StatusReservas(completada.size(), cancelada.size() );
    }
        
    public List<Reservacion> reporteTiempoServicio (String datoA, String datoB){
        SimpleDateFormat parser = new SimpleDateFormat ("yyyy-MM-dd");
        
        Date datoUno = new Date();
        Date datoDos = new Date();
        
        try{
             datoUno = parser.parse(datoA);
             datoDos = parser.parse(datoB);
        }catch(ParseException evt){
            evt.printStackTrace();
        }if(datoUno.before(datoDos)){
            return metodosCrud.ReservacionTiempoRepositorio(datoUno, datoDos);
        }else{
            return new ArrayList<>();
        
        } 
    } 
    public List<ContadorClientes> reporteClientesServicio(){
            return metodosCrud.getClientesRepositorio();
    }
}
