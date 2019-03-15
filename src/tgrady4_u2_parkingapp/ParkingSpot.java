/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tgrady4_u2_parkingapp;

import java.time.LocalTime;


/**
 *
 * @author Teddy
 */
public class ParkingSpot {
    private Vehicle car;
    private LocalTime time;
    
    public ParkingSpot(Vehicle car, LocalTime time){
        this.car = car;
        this.time = time;
    }
    
    public Vehicle getCar(){
        return car;
    }
    
    public LocalTime getTime(){
        return time;
    }
}
