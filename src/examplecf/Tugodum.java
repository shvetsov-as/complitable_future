/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examplecf;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class Tugodum {
    
    public static String getResult(int i){
        try {
            Thread.sleep(1000);
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Tugodum.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "SQR = " + i*i;
    }
    
}
