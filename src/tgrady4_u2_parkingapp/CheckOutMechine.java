package tgrady4_u2_parkingapp;

import java.text.NumberFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CheckOutMechine {
    
    private NumberFormat n = NumberFormat.getCurrencyInstance();
    private List<ParkingSpot> parkingSpots = new ArrayList();
    private Random rand = new Random();
    private double totalWithTicket = 0.00;
    private double totalWithoutTicket = 0.00;
    private final double minimumFee = 5.00;
    private final double hourlyRate = 1.00;
    private final double allDayWithTicket = 15.00;
    private final double lostTicket = 25.00;
    private Duration timeParkedDuration;
    private long timeParked;
    private int totalCheckInsWithTicket = 0;
    private int totalCheckInsWithoutTicket = 0;
    
    public CheckOutMechine(List<ParkingSpot> parkingSpots){
        this.parkingSpots = parkingSpots;
    }
    
    public void checkOutWithTicket(){
        int hourOfNight = rand.nextInt(12);
        hourOfNight = hourOfNight + 12;
        LocalTime timeLeft = LocalTime.of(hourOfNight, 0);
        double ammountDue = 0;
         //eventually this will be a "mechine" class(maybe a second one) with the method called checkOut(some parameters)
        System.out.println("\nReceipt for vehicle " + parkingSpots.get(0).getCar().getId() + "\n");
        timeParkedDuration = Duration.between(parkingSpots.get(0).getTime(), timeLeft);
        timeParked = (timeParkedDuration.getSeconds()/60)/60;
        if(parkingSpots.get(0).getTime().toString().equals("12:00")){
            System.out.println(timeParked + "hours parked. You parked from " + parkingSpots.get(0).getTime() + "PM to " + (timeLeft.minusHours(12)) + "PM.");
        }
        else{
            System.out.println(timeParked + " hours parked.\nYou parked from " + parkingSpots.get(0).getTime() + "AM - " + (timeLeft.minusHours(12)) + "PM.");
        }
        parkingSpots.remove(0);
        ammountDue += minimumFee;
        if(timeParked > 3){
            //only calculate the time parked if it's over the minimum of 3 hours, and until you hit the max of a day at $15
            for(int i = 0; i < timeParked - 3; i++){
                if(ammountDue < allDayWithTicket){
                    ammountDue += hourlyRate;
                }
            }
        }
        totalWithTicket += ammountDue;
        System.out.println("You paid: " + n.format(ammountDue) + "\n");
        totalCheckInsWithTicket++;
    }
    
    public void checkOutWithoutTicket(){
        System.out.println("\nReceipt for vehicle " + parkingSpots.get(0).getCar().getId() + "\n");
        System.out.println("Oh no! Did you lose your ticket?!");
        System.out.println("Well, there's an extra one here that says \"A Candy Company: Damp Bobby\". I'm not sure what it means, but It'll cost ya $25.");
        System.out.println("You paid: $25.00\n");
        parkingSpots.remove(0);
        totalWithoutTicket += lostTicket;
        totalCheckInsWithoutTicket++;
    }
    
    //get values used for math :)
    public double getTotalWithTicket(){
        return totalWithTicket;
    }
    public double getTotalWithoutTicket(){
        return totalWithoutTicket;
    }
    public int getTotalCheckInsWithTicket(){
        return totalCheckInsWithTicket;
    }
    public int getTotalCheckInsWithoutTicket(){
        return totalCheckInsWithoutTicket;
    }
}
