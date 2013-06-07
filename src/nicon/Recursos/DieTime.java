/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.Recursos;

/**
 *
 * @author donaldo
 */

public class  DieTime {
public DieTime(){    
}
public void tiempo(int a){
try {
    
Thread.sleep (a*100); // 1 * 100 = 0,1 segundo por tanto si a = 10 -> 10 * 100 = 1 segundo
} catch (Exception e) {
    System.out.println(e);
// Mensaje en caso de que falle
}  
}
}
