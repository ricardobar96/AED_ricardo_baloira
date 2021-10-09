/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editorhtml.controlador;

import editorhtml.modelo.Gestor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;

/**
 *
 * @author ricardo baloira armas
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private HTMLEditor editor;
    @FXML
    private RadioButton botonPlano;
    @FXML
    private ToggleGroup tipoTexto;
    @FXML
    private RadioButton botonHTML;
        
    String currentFile;
    
    private Gestor gestor;
    
    Alert errorGeneral = new Alert(AlertType.WARNING);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gestor = new Gestor();      
        errorGeneral.setContentText("Ha ocurrido un error"); 
    }    

    @FXML
    private void salir(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void nuevoArchivo(ActionEvent event) {
        editor.setHtmlText("");
    }

    @FXML
    private void abrir(ActionEvent event) {
        editor.setHtmlText("");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir fichero");
        File selectedFile = fileChooser.showOpenDialog(null);
        Path pathFile=null;
        if (selectedFile != null) {
        pathFile = selectedFile.toPath();
        currentFile = pathFile.toString();
        System.out.println("File selected: " + pathFile.toString());
        }
        try {
        BufferedReader in;
        in = new BufferedReader(new FileReader(selectedFile));
        String line = in.readLine();
        while (line != null) {
            editor.setHtmlText(editor.getHtmlText() + "\n" + line);
            line = in.readLine();
        }
        in.close();
        }
        catch(IOException ex){
            errorGeneral.showAndWait(); 
        }
    }

    @FXML
    private void guardarComo(ActionEvent event) {
        if(!botonPlano.isSelected() && !botonHTML.isSelected()){
           Alert errorTipo = new Alert(AlertType.WARNING);
           errorTipo.setContentText("Debes seleccionar el tipo de texto a guardar");
           errorTipo.showAndWait(); 
        }
        else{
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Guardar fichero");
            File selectedFile = fileChooser.showSaveDialog(null);
            try (FileWriter fw = new FileWriter(selectedFile)) {
                if(botonPlano.isSelected()){
                    String textoFinal = gestor.reemplazarHTML(editor.getHtmlText().toString());
                    fw.write(textoFinal);
                }
                if(botonHTML.isSelected()){
                    fw.write(editor.getHtmlText());
                }
                fw.close();
            }
            catch(Exception ex){
                errorGeneral.showAndWait(); 
            }
        }
    }

    @FXML
    private void guardar(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File file = new File(currentFile);
        if (file.exists()){
            try{                 
                FileWriter fw = new FileWriter(file);
                fw.write(editor.getHtmlText());
                fw.close();
            }
            catch(IOException ex){
                errorGeneral.showAndWait(); 
            }
            
        }
    }
    
}
