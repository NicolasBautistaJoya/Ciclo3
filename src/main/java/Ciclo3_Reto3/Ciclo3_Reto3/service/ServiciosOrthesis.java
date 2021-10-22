/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ciclo3_Reto3.Ciclo3_Reto3.service;


/**
 *
 * @author Nicolas
 */
import Ciclo3_Reto3.Ciclo3_Reto3.repository.OrthesisRepositorio;
import Ciclo3_Reto3.Ciclo3_Reto3.model.Orthesis;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service        
public class ServiciosOrthesis {
    @Autowired
    private OrthesisRepositorio metodosCrud;
    
    public List<Orthesis> getAll(){
        return metodosCrud.getAll();
    }
    
    public Optional<Orthesis> getOrthesis(int idOrthesis){
        return metodosCrud.getOrthesis(idOrthesis);
    }
    
    public Orthesis save(Orthesis orthesis){
        if(orthesis.getId()==null){
            return metodosCrud.save(orthesis);
        }else{
            Optional<Orthesis> evt=metodosCrud.getOrthesis(orthesis.getId());
            if(evt.isEmpty()){
                return metodosCrud.save(orthesis);
            }else{
                return orthesis;
            }
        }
    }
    public Orthesis update(Orthesis orthesis){
        if(orthesis.getId()!=null){
            Optional<Orthesis> evt=metodosCrud.getOrthesis(orthesis.getId());
            if(!evt.isEmpty()){
                if(orthesis.getName()!=null){
                    evt.get().setName(orthesis.getName());
                }
                if(orthesis.getBrand()!=null){
                    evt.get().setBrand(orthesis.getBrand());
                }
                if(orthesis.getYear()!=null){
                    evt.get().setYear(orthesis.getYear());
                }
                if(orthesis.getDescription()!=null){
                    evt.get().setDescription(orthesis.getDescription());
                }
                if(orthesis.getCategory()!=null){
                    evt.get().setCategory(orthesis.getCategory());
                }
                metodosCrud.save(evt.get());
                return evt.get();
            }else{
                return orthesis;
            }
        }else{
            return orthesis;
        }
    }


    public boolean deleteOrthesis(int orthesisId) {
        Boolean aBoolean = getOrthesis(orthesisId).map(orthesis -> {
            metodosCrud.delete(orthesis);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
