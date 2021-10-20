
package Ciclo3_Reto3.Ciclo3_Reto3.repository;

import Ciclo3_Reto3.Ciclo3_Reto3.InterfaceOrthesis;
import Ciclo3_Reto3.Ciclo3_Reto3.model.Orthesis;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Nicolas
 */

@Repository
public class OrthesisRepositorio {
    @Autowired
    private InterfaceOrthesis crud;
    

    public List<Orthesis> getAll(){
        return (List<Orthesis>) crud.findAll();       
    }
    
    public Optional <Orthesis> getOrthesis(int id){
        return crud.findById(id);
    }
    
    public Orthesis save(Orthesis orthesis){
        return crud.save(orthesis);
    }
    
    
}
