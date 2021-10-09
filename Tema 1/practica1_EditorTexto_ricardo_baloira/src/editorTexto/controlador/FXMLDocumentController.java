/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editorTexto.controlador;

import editorTexto.modelo.Gestor;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

/**
 *
 * @author ricardo baloira armas
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private TextArea Texto;
    
    String currentFile;
    
    Gestor gestor = new Gestor();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void guardarComo(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar fichero");
        File selectedFile = fileChooser.showSaveDialog(null);
        try (FileWriter fw = new FileWriter(selectedFile)) {
            fw.write(Texto.getText());
            fw.close();
        }
        catch(Exception ex){
            Texto.setText("Ha ocurrido un error");
        }
    }

    @FXML
    private void Salir(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void Abrir(ActionEvent event) {
        Texto.setText("");
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
            Texto.setText(Texto.getText() + "\n" + line);
            line = in.readLine();
        }
        in.close();
        }
        catch(IOException ex){
            Texto.setText("Ha ocurrido un error");
        }
    }

    @FXML
    private void nuevoArchivo(ActionEvent event) {
        Texto.setText("");
    }

    @FXML
    private void guardar(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File file = new File(currentFile);
        if (file.exists()){
            try{                 
                FileWriter fw = new FileWriter(file);
                fw.write(Texto.getText());
                fw.close();
            }
            catch(IOException ex){
                Texto.setText("Ha ocurrido un error");
            }
            
        }
    }
}
