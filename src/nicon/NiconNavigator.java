/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Dony
 */
public class NiconNavigator extends Application {
    
    @Override
    public void start(final Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Navegador.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("NiconNavigator");
        try{
        String a = String.valueOf(this.getClass().getResource("Imagenes/ProsJava.png"));
        Image img = new Image(a); 
        stage.getIcons().add(img);}catch(Exception e){System.out.println("Error al colocar icono");}
        stage.setScene(scene);
        stage.show();
        
         stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
          @Override
          public void handle(WindowEvent we) {
          if(we.isConsumed()==false){stage.close();
          System.exit(0);}
          System.out.println(we.toString());
          System.out.println("Stage is closing");
          }
      });        
       
        
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}