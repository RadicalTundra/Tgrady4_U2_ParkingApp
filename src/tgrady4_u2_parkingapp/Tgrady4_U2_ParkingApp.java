package tgrady4_u2_parkingapp;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tgrady4_U2_ParkingApp {


    public static void main(String[] args) throws IOException {
        boolean a = false;
        NumberFormat n = NumberFormat.getCurrencyInstance();
        List<Vehicle> vehicles = new ArrayList();
        List<ParkingSpot> parkingSpots = new ArrayList();
        List<Ticket> tickets = new ArrayList();
        FileOutput fo = new FileOutput();
        FileInput fi = new FileInput();
        fi.getAllIds(tickets);
        CheckInMechine checkIn = new CheckInMechine(vehicles, parkingSpots, tickets);
        CheckOutMechine checkOut = new CheckOutMechine(parkingSpots);
        
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("============================\n");
            System.out.println("Most Convient Parking Garage");
            System.out.println("\n============================\n");
            System.out.println("1. Check In");
            System.out.println("2. Check Out");
            System.out.println("3. Close Garage");
            System.out.println("4. LOST TICKET\n");
            System.out.print("=>");
            try{
                int userInput = sc.nextInt();
                switch(userInput){
                    case 1: 
                        //go to CheckInMechine and check that car in baby!
                        checkIn.newCarCheckIn();
                    break;
                    case 2:
                        //Heyy, we got a ticket! go to the CheckOutMechine and give them their bill!
                        checkOut.checkOutWithTicket();
                    break;
                    case 3:
                        //Kept this in here because I acted as if this was a terminal that the master computer would see
                        System.out.println("\n");
                        if(checkOut.getTotalCheckInsWithTicket() > 0){
                            System.out.println("From " + checkOut.getTotalCheckInsWithTicket() + " Check Ins, a total of " + n.format(checkOut.getTotalWithTicket()) + " was acquired.\n");
                        }
                        if(checkOut.getTotalCheckInsWithoutTicket() > 0){
                            System.out.println("From " + checkOut.getTotalCheckInsWithoutTicket() + " Lost Tickets, a total of " + n.format(checkOut.getTotalWithoutTicket()) + " was acquired.\n");
                        }
                        System.out.println("Overall, a total of " + n.format(checkOut.getTotalWithTicket() + checkOut.getTotalWithoutTicket()) + " was made today.\n");
                        a = true;
                        fo.addTicketsToFile(tickets);
                    break; 
                    case 4:
                        //Uh-oh. Someone lost a ticket! make them pay $25 for the werid one.
                        checkOut.checkOutWithoutTicket();
                    break;
                    default:
                        System.out.println("\n==============================\n");
                        System.out.println("Please enter a valid menu option");
                        System.out.println("\n==============================\n");
                    break;
                }
            }catch(NumberFormatException  e){
                //output this if they don't use a number
                System.out.println("\n==============================\n");
                System.out.println("HEY, YOU NEED A NUMBA MY DUDE.");
                System.out.println("\n==============================\n");
            }
            catch(IndexOutOfBoundsException e){
                // this exception will be caught if someone tries to remove a parking spot
                System.out.println("\n==============================\n");
                System.out.println("No cars left in the garage.");
                System.out.println("\n==============================\n");
            }
            catch(Exception e){
                System.out.println("\n==========================\n");
                System.out.println("Something wrong with file.");
                System.out.println("\n===========================\n");
            }
        }while(a == false);
    }
    
}
