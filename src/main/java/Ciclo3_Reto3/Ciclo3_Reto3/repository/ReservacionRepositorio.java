/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ciclo3_Reto3.Ciclo3_Reto3.repository;

import Ciclo3_Reto3.Ciclo3_Reto3.ContadorClientes;
import Ciclo3_Reto3.Ciclo3_Reto3.InterfaceReservacion;
import Ciclo3_Reto3.Ciclo3_Reto3.model.Cliente;
import Ciclo3_Reto3.Ciclo3_Reto3.model.Mensaje;
import Ciclo3_Reto3.Ciclo3_Reto3.model.Reservacion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Nicolas
 */
@Repository
public class ReservacionRepositorio implements Serializable{
    @Autowired
    private InterfaceReservacion crud4;
    
    public List<Reservacion> getAll(){
        return (List<Reservacion>) crud4.findAll();
    }
    public Optional<Reservacion> getReservation(int id){
        return crud4.findById(id);
    }
    public Reservacion save(Reservacion reservation){
        return crud4.save(reservation);
    }
    
    public void delete(Reservacion reservation){
        crud4.delete(reservation);
    }
    
    public  List<Reservacion> StatusReservacion (String status) {
        return crud4.fingAllByStatus(status);
    }
             
     public List<Reservacion> ReservacionTiempoRepositorio (Date a, Date b){
         return crud4.findAllByStartDateAfterAndStartDateBefore(a, b);
     
     }
     
     public List<ContadorClientes> getClientesRepositorio(){
         List<ContadorClientes> res = new ArrayList<>();
         List<Object[]> report = crud4.countTotalReservationsByClient();
         for(int i=0; i<report.size(); i++){
             res.add(new ContadorClientes((Long)report.get(i)[1],(Cliente) report.get(i)[0]));
         }
         return res;
     }

}   
