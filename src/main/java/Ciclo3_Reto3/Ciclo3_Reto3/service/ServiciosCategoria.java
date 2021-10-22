/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ciclo3_Reto3.Ciclo3_Reto3.service;

import Ciclo3_Reto3.Ciclo3_Reto3.repository.CategoriaRepositorio;
import Ciclo3_Reto3.Ciclo3_Reto3.model.Categoria;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nicolas
 */
@Service
public class ServiciosCategoria {
    @Autowired
    private CategoriaRepositorio metodosCrud;
    
    public List<Categoria> getAll(){
        return metodosCrud.getAll();
    }
    
    public Optional<Categoria> getCategoria(int idCategoria){
        return metodosCrud.getCategoria(idCategoria);
    }
    
    public Categoria save(Categoria categoria){
        if(categoria.getId()==null){
            return metodosCrud.save(categoria);
        }else{
            Optional<Categoria> evt=metodosCrud.getCategoria(categoria.getId());
            if(evt.isEmpty()){
                return metodosCrud.save(categoria);
            }else{
                return categoria;
            }
        }
    }
    
    public Categoria update(Categoria categoria){
        if(categoria.getId()!=null){
            Optional<Categoria> evt=metodosCrud.getCategoria(categoria.getId());
            if(!evt.isEmpty()){
                if(categoria.getDescription()!=null){
                    evt.get().setDescription(categoria.getDescription());
                }
                if(categoria.getName()!=null){
                    evt.get().setName(categoria.getName());
                }
                return metodosCrud.save(evt.get());
            }
        }
        return categoria;
    }
    public boolean deletecategoria(int categoriaId){
        Boolean d=getCategoria(categoriaId).map(categoria -> {
            metodosCrud.delete(categoria);
            return true;
        }).orElse(false);
        return d;
    }
}
