/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.Recursos;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;

/**
 *
 * @author Dony
 */
public class TABs extends Tab{
    public WebView web = new WebView();
    public String url = "",title ="";
    public AnchorPane pane = new AnchorPane();
    private String Name;
    public List<URL> History = new ArrayList();
public TABs(String a){
    title = a;
    this.setText(title);
    this.setContent(pane);
    pane.autosize();
    
    AnchorPane.setTopAnchor(web, 0.0);
    AnchorPane.setLeftAnchor(web, 0.0);
    AnchorPane.setRightAnchor(web, 0.0);
    AnchorPane.setBottomAnchor(web, 0.0);
    pane.getChildren().add(web);
    
        try {
            URL iniurl = new URL("http://www.google.es/");
              this.addURL(iniurl);
        } catch (MalformedURLException ex) {
            Logger.getLogger(TABs.class.getName()).log(Level.SEVERE, null, ex);
        }
  
}

public String getURL(){
String a;
a = web.getEngine().getLocation();
return a;
}
public final void addURL(URL a){
History.add(a);
}

public void setTitle(String a){
this.setText(a);
}
public void setTitle(){
url = web.getEngine().getLocation();
switch(title){
    case "Inicio":
        
        break;
    case "Marcadores":
        
        break;
    case "Opciones":
        
        break;    
        
        
    case "+":
        
        break;    
    default:
        Name = url;
        Name = Name.replaceFirst("www.", "");
        Name = Name.replaceFirst(".com", "");
        Name = Name.replaceFirst(".es", "");
        
        if(Name.length()>30){
        Name = Name.substring(0, 20);
        }
        this.setText(Name);
        title = Name;
        
        break;
}}

public void setPage(String a){
String URL = a;

URL = URL.replaceFirst("http://", "");
URL = URL.replaceFirst("https://", "");

url = URL;

try{
web.getEngine().load("http://"+url);
}catch(Exception e){
web.getEngine().load("https://"+url);
}

switch(title){
    case "Inicio":
        
        break;
        
    case "+":
        
        break;    
    default:
        Name = url;
        Name = Name.replaceFirst("www.", "");
        Name = Name.replaceFirst(".com", "");
        Name = Name.replaceFirst(".es", "");
        
        if(Name.length()>30){
        Name = Name.substring(0, 20);
        }
        this.setText(Name);
        title = Name;
        
        break;
        
}

}
}
