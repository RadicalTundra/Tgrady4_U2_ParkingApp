/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tgrady4_u2_parkingapp;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author tgrady4
 */
public class FileOutput {
    
    public void addTicketsToFile(List<Ticket> tickets) throws FileNotFoundException, IOException{
        BufferedWriter out = new BufferedWriter(new FileWriter("ListOfTickets.txt"));
        for(int i = 0; i < tickets.size(); i++){
        out.write("Ticket id: " + tickets.get(i).getId() + "\n");
        }
        out.close();
    }
}
