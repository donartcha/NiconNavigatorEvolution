/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.Recursos;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author donaldo
 */
public class PCData {
public String User, Arch, So, Version, Home;
public Dimension Screen;
public PCData(){
User =      System.getProperty("user.name");    System.out.println("Nombre de usuario: "+User);
Arch =      System.getProperty("os.arch");      System.out.println("Arquitectura: "+Arch);  
So =        System.getProperty("os.name");      System.out.println("Sistema Operativo: "+So);
Version =   System.getProperty("os.version");   System.out.println("Vesion del Sistema: "+Version);
Home =      System.getProperty("user.home");    System.out.println("Carpeta de Usuario: "+Home);

Toolkit tk = Toolkit.getDefaultToolkit();
Screen = tk.getScreenSize();
System.out.println("La pantalla es de " + Screen.getWidth() + " x " + Screen.getHeight() );
  }
}

