/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ciclo3_Reto3.Ciclo3_Reto3.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Nicolas
 */

@Entity
@Table(name = "ortopedic")
public class Orthesis implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        
    //id   
    private Integer id;
    
    //Name
    @Column(length = 45) 
    /**Nombre**/
    private String name;
    
    //Brand
    @Column(length = 45)   
    /**marca**/
    private String  brand;
    
    //Year
    @Column(length = 4)  
    /**año**/
    private Integer year; 
    
    //Description
    @Column(length = 250)
    /**descrpcion**/
    private String description;
    
    //Relacion con la tabla categoria
    @ManyToOne
    @JoinColumn(name="categoryid")
    @JsonIgnoreProperties("ortopedics")
    /**Categoria**/
    private Categoria category;
    
    //Relacion con la tabla mensaje
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "ortopedic")
    @JsonIgnoreProperties({"ortopedic","client"})
    /**Mensaje**/
    private List<Mensaje> messages;

    //Relacion con la tabla reservaciones
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "ortopedic")
    @JsonIgnoreProperties({"ortopedic","message"})
    /**Reservacion**/
    public List<Reservacion> reservations;

    //Obtener el id
    public Integer getId() {
        return id;
    }

    //Establecer el id
    public void setId(Integer id) {
        this.id = id;
    }

    //Obtener el nombre
    public String getName() {
        return name;
    }

    //Establecer el nombre
    public void setName(String name) {
        this.name = name;
    }

    //Obtener la marca
    public String getBrand() {
        return brand;
    }

    //Establecer la marca
    public void setBrand(String brand) {
        this.brand = brand;
    }

    //Obtener el año
    public Integer getYear() {
        return year;
    }

    //Establecer el año
    public void setYear(Integer year) {
        this.year = year;
    }

    //Obtener descrpcion
    public String getDescription() {
        return description;
    }

    //Establecer descripcion
    public void setDescription(String description) {
        this.description = description;
    }

    
    //Obtener categoria
    public Categoria getCategory() {
        return category;
    }

    //Establecer categoria
    public void setCategory(Categoria category) {
        this.category = category;
    }

    //Obtener mensaje
    public List<Mensaje> getMessages() {
        return messages;
    }

    //Establecer mensaje
    public void setMessages(List<Mensaje> messages) {
        this.messages = messages;
    }

    //Obtener reservacion
    public List<Reservacion> getReservations() {
        return reservations;
    }

    //Establecer reservacion
    public void setReservations(List<Reservacion> reservations) {
        this.reservations = reservations;
    }
    
}
