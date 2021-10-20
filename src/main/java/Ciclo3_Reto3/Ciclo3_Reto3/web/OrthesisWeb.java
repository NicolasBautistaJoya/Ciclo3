/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ciclo3_Reto3.Ciclo3_Reto3.web;

import Ciclo3_Reto3.Ciclo3_Reto3.service.ServiciosOrthesis;
import Ciclo3_Reto3.Ciclo3_Reto3.model.Orthesis;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Nicolas
 */

@RestController
@RequestMapping("/api/Ortopedic")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class OrthesisWeb {
    @GetMapping("/holaMundo")
    public String saludad(){
    return "Hola Mundo Tutoria nicolas";
    }

    
    @Autowired
    private ServiciosOrthesis servicio;
    @GetMapping("all")
    public List <Orthesis> getOrthesis(){
        return servicio.getAll();
    }
    
    @GetMapping("/{id}")
    public Optional<Orthesis> getOrthesis(@PathVariable("id") int idOrthesis) {
        return servicio.getOrthesis(idOrthesis);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Orthesis save(@RequestBody Orthesis orthesis) {
        return servicio.save(orthesis);
    }

    
    
}
