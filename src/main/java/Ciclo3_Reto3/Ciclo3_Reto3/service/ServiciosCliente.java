/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ciclo3_Reto3.Ciclo3_Reto3.service;

import Ciclo3_Reto3.Ciclo3_Reto3.repository.ClienteRepositorio;
import Ciclo3_Reto3.Ciclo3_Reto3.model.Cliente;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nicolas
 */
@Service
public class ServiciosCliente {
    @Autowired
    private ClienteRepositorio metodosCrud;
    
    public List<Cliente> getAll(){
        return metodosCrud.getAll();
    }
    
    public Optional<Cliente> getCliente(int clientId){
        return metodosCrud.getCliente(clientId);
    }
    
    public Cliente save(Cliente client){
        if(client.getIdClient()==null){
            return metodosCrud.save(client);
        }else{
            Optional<Cliente> evt=metodosCrud.getCliente(client.getIdClient());
            if(evt.isEmpty()){
                return metodosCrud.save(client);
            }else{
                return client;
            }
        }
    }
    
    public Cliente update(Cliente client){
        if(client.getIdClient()!=null){
            Optional<Cliente> evt= metodosCrud.getCliente(client.getIdClient());
            if(!evt.isEmpty()){
                if(client.getName()!=null){
                    evt.get().setName(client.getName());
                }
                if(client.getAge()!=null){
                    evt.get().setAge(client.getAge());
                }
                if(client.getPassword()!=null){
                    evt.get().setPassword(client.getPassword());
                }
                metodosCrud.save(evt.get());
                return evt.get();
            }else{
                return client;
            }
        }else{
            return client;
        }
    }

    public boolean deleteClient(int clientId) {
        Boolean aBoolean = getCliente(clientId).map(client -> {
            metodosCrud.delete(client);
            return true;
        }).orElse(false);
        return aBoolean;
    }
    
    
    
}
