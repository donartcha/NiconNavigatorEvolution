/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.Recursos;

import java.io.Serializable;
import java.net.URL;
import javax.swing.JProgressBar;

/**
 *
 * @author Dony
 */
public class Archivo implements Serializable{
public String Name="",Unidad="";
public Double Tam=0.0;
public URL Link;
public JProgressBar Bar;
public Archivo(String a, URL b,Double c,String d){
Bar = new JProgressBar();
Name = a;
Link = b;
Tam = c;
Unidad = d;
Bar.setValue(0);
}
public void setBar(int a){
Bar.setValue(a);
}

}
