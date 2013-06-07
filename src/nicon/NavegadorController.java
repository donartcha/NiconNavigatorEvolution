/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.PopupFeatures;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Callback;
import nicon.DownloadController.Descargar;
import nicon.Recursos.Archivo;
import nicon.Recursos.ExplorarCarpeta;
import nicon.Recursos.Marcador;
import nicon.Recursos.PCData;
import nicon.Recursos.TABs;

/**
 *
 * @author Dony
 */
public class NavegadorController implements Initializable {
    private String INIURL,carpeta,settings,marcador,name,linki,readed,descname,CarpDesc;
    private TABs NABTABs;
    private WebView web = new WebView(); 
    private PCData info = new PCData();
    private File Carpeta,MArcador,Settings,CDescarga,CDFinal;
    private List<Marcador> listmarcs,reader;
    private int mar,hisINDEX;
    private URL Durl,IMGurl;
    private List<URL> history = new ArrayList(); 
    private URLConnection urlCon;
    private Descargar Dventana = new Descargar();
    
    @FXML
    private AnchorPane Navegador;
    
    @FXML
    private Label DName;
    
    @FXML
    private TextField Direccion;
    
    @FXML
    private TextField MName;
    
    @FXML
    private TextField MLink;
    
    @FXML
    private TextField INIPAGE;
    
    @FXML
    private TextField Dfield;
    
    @FXML
    private Button navegar;
    
    @FXML
    private Button marcadores;
    
    @FXML
    private Button opciones;
    
    @FXML
    private Button IRMarc;
    
    @FXML
    private Button DeleteMarc;
    
    @FXML
    private Button AMarcs;
    
    @FXML
    private Button AMarc;
    
    @FXML
    private Button Home;
    
    @FXML
    private Button Dsave;
    
    @FXML
    private Button Explorar;
    
    @FXML
    private Button DescImage;
    @FXML
    private Button back;
    @FXML
    private Button adel;
    
  
    
    @FXML
    private TabPane Tabs;
    
    @FXML
    private TabPane MTabs;
    
    @FXML
    private ProgressIndicator Prog;
    
  
    
    @FXML
    private Tab MTab;
    
    @FXML
    private Tab Marcs;
    
    @FXML
    private Tab Option;
    
   
    
    @FXML
    private ListView listM;
   
    
    
    @FXML
    private void DefaultAction1(ActionEvent event) {
    String dir = Direccion.getText();
  
    if(dir.contains("http")==true || dir.contains("www")==true|| dir.contains(".com")==true|| dir.contains(".es")==true){
    
    dir = dir.replace("http://","");
    dir = dir.replace("https://","");
    
    try{
    web.getEngine().load("http://"+dir);
    }catch(Exception e){
    web.getEngine().load("https://"+dir);
    }}else{
    String search = this.getWords(dir);
    try{web.getEngine().load(search);}catch(Exception e){}
    
        
    }
    
    }
    
    
     @FXML
    private void DesImageAction(ActionEvent event) {
    descIMG();
    }
     
    @FXML
    private void backButtonAction(ActionEvent event) {
    try{
    if(hisINDEX >=1){
    hisINDEX--;}
    web.getEngine().load(history.get(hisINDEX).toString());
    }
    
    catch(Exception e){
    
    }
    }
    
    @FXML
    private void adelButtonAction(ActionEvent event) {
        
    try{
    if(hisINDEX <history.size()){
    hisINDEX++;}
    web.getEngine().load(history.get(hisINDEX).toString());
    }
    
    catch(Exception e){
    
    }
    } 
     
  
    
    @FXML
    private void HomeAction(ActionEvent event) {
    
        try{    
    web.getEngine().load("http://"+INIURL);
    }catch(Exception e){
    web.getEngine().load("https://"+INIURL);
    }
    }
    
    @FXML
    private void PressEnter(KeyEvent event) {

    String pulsado = event.getCode().toString();
    if ("ENTER".equals(pulsado)){
    String dir = Direccion.getText();
    
    if(dir.contains("http")==true || dir.contains("www")==true|| dir.contains(".com")==true|| dir.contains(".es")==true){
    
    dir = dir.replace("http://","");
    dir = dir.replace("https://","");
    
    try{
    web.getEngine().load("http://"+dir);
    }catch(Exception e){
    web.getEngine().load("https://"+dir);
    }}else{
    String search = this.getWords(dir);
    try{web.getEngine().load(search);}catch(Exception e){}
    
        
    }
    }}
    
    public String getWords(String a) { 
    String b;
    String c="http://www.google.com/search?q=";
    
    String Separadores = "[ .,?!]+";
    String [] words = a.split(Separadores);
    for(int i = 0; i<words.length; i++){
    b= words[i];
    c= c+"+"+b;
   
    }
    c = c.replace("?q=+", "?q=");
    System.out.println(c);
    return c; 
    }
    
    @FXML
    private void OptionButton(ActionEvent event) {
    if (Tabs.getTabs().contains(Option)==false){
        Tabs.getTabs().add(Option);
    SingleSelectionModel<Tab> selectionModel = Tabs.getSelectionModel();
    selectionModel.select(Option);
    INIPAGE.setText(INIURL);
    this.cargaDesc();
    Dfield.setText(CDFinal.toString());
    }
    
    }
    

    
    
     @FXML
    private void INISaveAction(ActionEvent event) {
    String a = INIPAGE.getText();
    this.Guardar(Settings, a);
    INIURL = this.Cargar(Settings);
    }
    
    @FXML
    private void MarcadoresButton(ActionEvent event) {
    if (Tabs.getTabs().contains(Marcs)==false){    
    Tabs.getTabs().add(Marcs);
    SingleSelectionModel<Tab> selectionModel = Tabs.getSelectionModel();
    selectionModel.select(Marcs);
    MTabs.getTabs().clear();
    listmarcs = this.CargarList(MArcador);
    listM.getItems().clear();
    int ItemIndex = listmarcs.size();
    System.out.println("ItemIndex = "+ItemIndex);
    int inicio = 0;
    
    
    
   while(inicio < ItemIndex){
   Marcador marca = (Marcador) listmarcs.get(inicio);
   listM.getItems().add(marca.Name);
   inicio++;
   }
    }
    }
    
    @FXML
    private void MarcsButton(ActionEvent event) {
  
    if(mar==0){
         
    MTabs.getTabs().add(MTab);
    name = Direccion.getText();
    linki = Direccion.getText();
    if (name.length()>20){
    
    name = name.replaceFirst("http://", "");
    name = name.replaceFirst("https://", "");
    name = name.replaceFirst("www.", "");
    name = name.replaceFirst(".com", "");
    name = name.replaceFirst(".es", "");
    name = name.substring(0,20);}
    
    linki = linki.replaceFirst("http://", "");
    linki = linki.replaceFirst("https://", "");
    MName.setText(name);
    MLink.setText(linki);
    mar = 1;
    }
    
    }
    
    @FXML
    private void IRMarcButton(ActionEvent event) {
   
    try{int selectedItem = listM.getSelectionModel().getSelectedIndex();
    ObservableList listItems = listM.getItems();
    
     TABs nueva = new TABs("Nueva Ventana");
       nueva.setClosable(true);
       Tabs.getTabs().add(nueva);
       SingleSelectionModel<Tab> selectionModel = Tabs.getSelectionModel();
       selectionModel.select(nueva);
       nueva.setPage(listmarcs.get(selectedItem).Link);
       Direccion.setText(nueva.getURL());
  
    }catch(Exception e){}
        
        
    }
    
      @FXML
    private void DeleteMarcAction(ActionEvent event) {
    try{int selectedItem = listM.getSelectionModel().getSelectedIndex();
    ObservableList listItems = listM.getItems();
    listItems.remove(selectedItem);
    listmarcs.remove(selectedItem);
    this.GuardarList(MArcador,listmarcs);
    }catch(Exception e){}
    
    }
    
      @FXML
    private void AddMarcAction(ActionEvent event) {
    Marcador marca = new Marcador(MName.getText(),MLink.getText());
    listmarcs = this.CargarList(MArcador);
    listmarcs.add(marca);
    listM.getItems().add(marca.Name);
    System.out.println(marca.Name+marca.Link);
    this.GuardarList(MArcador,listmarcs);
    MTabs.getTabs().clear();
    mar = 0;
    }  
      
    @FXML
    private void SaveFolderButton(ActionEvent event) {
    String a = Dfield.getText();
    this.Guardar(CDescarga, a);
    CarpDesc = this.Cargar(CDescarga);
    CDFinal = new File(CarpDesc);
    }  
    
    @FXML
    private void SelectFolder(ActionEvent event) {
    ExplorarCarpeta chooser = new ExplorarCarpeta();
    chooser.setVisible(true);
    chooser.setAlwaysOnTop(true);
    while(chooser.isVisible()==true){
    
    }
    System.out.println("Se ha conseguido la carpeta"+chooser.Carpeta);
    Dfield.setText(chooser.Carpeta);
    }  
      
    /**
     * Control de Pestañas
     */  
    public void AddTabsChangeListener(){
    Tabs.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
    @Override public void changed(ObservableValue<? extends Tab> tab, Tab oldTab, Tab newTab) {
    
       switch(newTab.getText()){
       case "Inicio":
           NABTABs = (TABs) newTab;
           Direccion.setText(NABTABs.getURL());
           web = NABTABs.web;
           hisINDEX = history.size();
           AddWebsChangeListener();
         
           break;
           
       case "Marcadores": 
           
           break;
           
       case "Opciones": 
           
           break;
       
       case "Descarga Activa": 
           
           break;
       case "+":
       TABs nueva = new TABs("Nueva Ventana");
       nueva.setClosable(true);
       Tabs.getTabs().add(nueva);
       SingleSelectionModel<Tab> selectionModel = Tabs.getSelectionModel();
       selectionModel.select(nueva);
       nueva.setPage(INIURL);
       Direccion.setText(nueva.getURL());
       history = nueva.History;
       hisINDEX = history.size();
       AddWebsChangeListener();
    
           break;
           
       default:
           NABTABs = (TABs) newTab;
           Direccion.setText(NABTABs.getURL());
           web = NABTABs.web;
           hisINDEX = history.size();
           AddWebsChangeListener();
    
           
    break;
           
   }}});
    }
    
    /**
     * Control de webs
     */
    public void AddWebsChangeListener(){
    web.getEngine().getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
    @Override
    public void changed(ObservableValue<? extends Worker.State> ov,Worker.State oldState, Worker.State newState) {
    
    if("SUCCEEDED".equals(newState.toString())){
    Prog.setProgress(1);
    }
    if("RUNNING".equals(newState.toString())){
    Prog.setProgress(-1);
    
    Direccion.setText(web.getEngine().getLocation());
    URL grul;
    
    try{
        history = NABTABs.History;  
        grul = new URL(web.getEngine().getLocation());
          
           
           NABTABs.addURL(grul);
            
                
       }catch(Exception e){System.out.println(e);}   
            
        
    name = web.getEngine().getLocation();
    if (name.length()>30){
    name = name.replaceFirst("http://", "");
    name = name.replaceFirst("https://", "");
    name = name.replaceFirst("www.", "");
    name = name.replaceFirst(".com", "");
    name = name.replaceFirst(".es", "");
    name = name.substring(0,20);}else{    
    name = name.replaceFirst("http://", "");
    name = name.replaceFirst("https://", "");
    name = name.replaceFirst("www.", "");
    name = name.replaceFirst(".com", "");
    name = name.replaceFirst(".es", "");}
    try{NABTABs.setTitle(name);}catch(Exception e){}
    }
    
    
    if("FAILED".equals(newState.toString())){
    Prog.setProgress(1);
    String fail = Direccion.getText();
    Direccion.setText("La dirección: "+fail+" ha fallado. Compruebela");
    }
            
    }});
    
 
  
    web.getEngine().setCreatePopupHandler(new Callback<PopupFeatures, WebEngine>() {
    @Override public WebEngine call(PopupFeatures config) {
    TABs nueva = new TABs("Nueva Ventana");
    nueva.setClosable(true);
    Tabs.getTabs().add(nueva);
      
    SingleSelectionModel<Tab> selectionModel = Tabs.getSelectionModel();
    selectionModel.select(nueva);
    web = nueva.web;
    Direccion.setText(nueva.getURL()); 
    
    return web.getEngine();}
    }
    );      
    
    
    web.getEngine().locationProperty().addListener(new ChangeListener<String>() {
    @Override public void changed(ObservableValue<? extends String> observableValue, String oldLoc, String newLoc) {
    

try {
  Durl = new URL(newLoc);
  urlCon = Durl.openConnection();
   
   String tipe = urlCon.getContentType();
   if(tipe.contains("text")==true){tipe = "text";}
   if(tipe.contains("image")==true){tipe = "image";}
   switch(tipe){
       case "text":
           DescImage.setVisible(false);
       break;
       case "image": 
           IMGurl = Durl;
            DescImage.setVisible(true);
       break;
           
       default:
           DescImage.setVisible(false);
           System.out.println("Tipo de archivo:" +tipe);
           desc();
           break;
   }
} catch (Exception e) {
System.out.println("Se produjo un error");
System.out.println(e);
}
    
    }
    });
    
    } 
    
    private void desc(){
Dventana.run();
System.out.println("Comenzando desc");        
Dventana.setVisible(true);    
Dventana.setAlwaysOnTop(true);
Dventana.setAlwaysOnTop(false);
String filename = Durl.getFile();

int num = filename.lastIndexOf("/");
String fname = filename.substring(num+1, filename.length());
System.out.println(fname);  
int Bites = urlCon.getContentLength();

Double tmax = Double.valueOf(Bites);
String unidad = "Bytes";
if(Bites>1024){
tmax = Double.valueOf(Bites/1024);  
unidad = "KBs";
}
if(tmax>1024){
tmax = Double.valueOf(tmax/1024);  
unidad = "MBs";
}
if(tmax>1024){
tmax = Double.valueOf(tmax/1024);  
unidad = "GBs";
}
DecimalFormat df = new DecimalFormat("0.00"); 
System.out.println(df.format(tmax)+unidad);
String zeta = df.format(tmax);
zeta = zeta.replaceAll(",",".");
Double doub = Double.parseDouble(zeta);

try{ 
Archivo archivi;
archivi= new Archivo(fname,Durl,doub,unidad);
System.out.println(archivi);  
Dventana.addDescarga(archivi);
    }catch(Exception e){System.out.println(e);}

   if (CDFinal.exists()==false){
   CDFinal.mkdir();
   }
        System.out.println(CDFinal);
        
        Dventana.setCarpeta(CDFinal);
    
    
        
    }  
    
    private void descIMG(){
Dventana.run();

System.out.println("Comenzando desc");        
Dventana.setVisible(true);       
Dventana.setAlwaysOnTop(true);
Dventana.setAlwaysOnTop(false);
String filename = IMGurl.getFile();
try{urlCon = IMGurl.openConnection();}catch(Exception e){}
int num = filename.lastIndexOf("/");
String fname = filename.substring(num+1, filename.length());
System.out.println(fname);  
int Bites = urlCon.getContentLength();

Double tmax = Double.valueOf(Bites);
String unidad = "Bytes";
if(Bites>1024){
tmax = Double.valueOf(Bites/1024);  
unidad = "KBs";
}
if(tmax>1024){
tmax = Double.valueOf(tmax/1024);  
unidad = "MBs";
}
if(tmax>1024){
tmax = Double.valueOf(tmax/1024);  
unidad = "GBs";
}
DecimalFormat df = new DecimalFormat("0.00"); 
System.out.println(df.format(tmax)+unidad);
String zeta = df.format(tmax);
zeta = zeta.replaceAll(",",".");
Double doub = Double.parseDouble(zeta);

try{ 
Archivo archivi;
archivi= new Archivo(fname,IMGurl,doub,unidad);
System.out.println(archivi);  
Dventana.addDescarga(archivi);
    }catch(Exception e){System.out.println(e);}

   if (CDFinal.exists()==false){
   CDFinal.mkdir();
   }
        System.out.println(CDFinal);
        
        Dventana.setCarpeta(CDFinal);
    
    
        
    }  
    
       
    public final void SaveMarcs(List a){
    System.out.println("Comprobando Archivos");
    System.out.println("\nPaso nº1");
    Carpeta= new File(carpeta);
    if(Carpeta.exists()==true){
    System.out.println("\t[OK]");
    }else{
    System.out.println("Creando Carpeta en: "+carpeta);
    Carpeta.mkdir();
    System.out.println("\t[OK]");}
    
    System.out.println("\nPaso nº2");
    MArcador= new File(marcador);
    if(MArcador.exists()==true){
    System.out.println("\t[OK]");
    
    System.out.println("\nPaso nº3");
    listmarcs = this.CargarList(MArcador);
    }else{
        
    this.GuardarList(MArcador, a);
    System.out.println("\t[OK]");
       
    
    }}
    public final List CargarList(File a){
        try{
        FileInputStream entrada;
        entrada = new FileInputStream(a); 
        ObjectInputStream in;
        in = new ObjectInputStream(entrada);
        
        reader = (List) in.readObject();
        
        entrada.close();
        in.close();
        }catch(IOException | ClassNotFoundException e){}
        
        return reader;
    }
    public final void GuardarList(File a,List b){
        try{
        FileOutputStream salida;
        salida = new FileOutputStream(a); 
        ObjectOutputStream dos;
        dos = new ObjectOutputStream(salida);
        
        dos.writeObject(b);
        
        salida.close();
        dos.close();
        }catch(Exception e){}
        
        
    }
    
            
    public final void SaveHome(String a){
    System.out.println("Comprobando Archivos");
    System.out.println("\nPaso nº1");
    Carpeta= new File(carpeta);
    if(Carpeta.exists()==true){
    System.out.println("\t[OK]");
    }else{
    System.out.println("Creando Carpeta en: "+carpeta);
    Carpeta.mkdir();
    System.out.println("\t[OK]");}
    
    System.out.println("\nPaso nº2");
    Settings= new File(settings);
    if(Settings.exists()==true){
    System.out.println("\t[OK]");
    
    System.out.println("\nPaso nº3");
    INIURL = this.Cargar(Settings);
    System.out.println(INIURL);
    }else{
    System.out.println("Creando Settings en: "+settings);
    this.Guardar(Settings, a);
    System.out.println("\t[OK]");
    INIURL = "www.google.es";
    }
    
}
    
    public final void Guardar(File a,String b){
        try{
        FileOutputStream salida;
        salida = new FileOutputStream(a); 
        DataOutputStream dos;
        dos = new DataOutputStream(salida);
        
        dos.writeUTF(b);
        
        salida.close();
        dos.close();
        }catch(Exception e){}
        
        
    }
    
        public final String Cargar(File a){
        try{
        FileInputStream entrada;
        entrada = new FileInputStream(a); 
        DataInputStream in;
        in = new DataInputStream(entrada);
        
        readed = in.readUTF();
        
        entrada.close();
        in.close();
        }catch(Exception e){}
        
        return readed;
    }
        
        public final void cargaDesc(){
        System.out.println("Comprobando Archivos");
        System.out.println("\nPaso nº1");
        Carpeta= new File(carpeta);
        if(Carpeta.exists()==true){
        System.out.println("\t[OK]");
        }else{
        System.out.println("Creando Carpeta en: "+carpeta);
        Carpeta.mkdir();
        System.out.println("\t[OK]");}
        
        System.out.println("\nPaso nº2");
        CDescarga= new File(descname);
        if(CDescarga.exists()==true){
        System.out.println("\tCargando configuración local");
        CarpDesc = this.Cargar(CDescarga);
        CDFinal = new File(CarpDesc);}else{
        this.Guardar(CDescarga, carpeta+"\\Descargas");
        CarpDesc = this.Cargar(CDescarga);
        CDFinal = new File(CarpDesc);
        System.out.println(CDFinal);
        }
        }
    
   
    @FXML
    private void backButtonEntred(MouseEvent event) {
    back.getStyleClass().remove("Dback");
    back.getStyleClass().add("back");
    }
    @FXML
    private void backButtonExited(MouseEvent event) {
    back.getStyleClass().remove("back");
    back.getStyleClass().add("Dback");
    }
    
    @FXML
    private void adelButtonEntred(MouseEvent event) {
    adel.getStyleClass().remove("Dadel");
    adel.getStyleClass().add("adel");
    }
    @FXML
    private void adelButtonExited(MouseEvent event) {
    adel.getStyleClass().remove("adel");
    adel.getStyleClass().add("Dadel");
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
   
    DescImage.setCursor(javafx.scene.Cursor.HAND);
    Home.setCursor(javafx.scene.Cursor.HAND);
    navegar.setCursor(javafx.scene.Cursor.HAND);
    marcadores.setCursor(javafx.scene.Cursor.HAND);
    opciones.setCursor(javafx.scene.Cursor.HAND);
    back.setCursor(javafx.scene.Cursor.HAND);
    adel.setCursor(javafx.scene.Cursor.HAND);
    try{
    Dventana.setIconImage(Dventana.getIconImage());}catch(Exception e){System.out.println("Error al colocar Icono de Dventana");}
    
        switch (info.So){
        case "Windows XP":
        carpeta = info.Home+"\\prosNavegator";
        settings = info.Home+"\\prosNavegator\\settings.cfg";
        marcador = info.Home+"\\prosNavegator\\marcador.cfg";
        descname = info.Home+"\\prosNavegator\\descarga.cfg";
       
            break;
            
        case "Windows 7":
        carpeta = info.Home+"\\prosNavegator";
        settings = info.Home+"\\prosNavegator\\settings.cfg";
        marcador = info.Home+"\\prosNavegator\\marcador.cfg";
        descname = info.Home+"\\prosNavegator\\descarga.cfg";
            break;
            
        default: 
            
        carpeta = info.Home+"\\prosNavegator";
        settings = info.Home+"\\prosNavegator\\settings.cfg";
        marcador = info.Home+"\\prosNavegator\\marcador.cfg";
        descname = info.Home+"\\prosNavegator\\descarga.cfg";
        
            break;
    }
    try{this.SaveHome("www.google.es");
    this.cargaDesc();
    }catch(Exception e){System.out.println("Error al cargar ");}
    
    Marcador marca = new Marcador("Google","www.google.es");
    listmarcs = new ArrayList();
    listmarcs.add(marca);
    this.SaveMarcs(listmarcs);
    DescImage.setVisible(false);
    
  
    
   
    Tabs.getTabs().clear();
    TABs Suma= new TABs("+");
    Suma.setClosable(false);
    Tabs.getTabs().add(Suma);
    
    this.AddTabsChangeListener();
  
    
   
    TABs Inicio= new TABs("Inicio");
    Inicio.setClosable(false);
    Tabs.getTabs().add(Inicio);
    Inicio.setPage(INIURL);
    Direccion.setText(Inicio.getURL());
    SingleSelectionModel<Tab> selectionModel = Tabs.getSelectionModel();
    selectionModel.select(Inicio);
    
    
    
    
    
    
    }    }

  