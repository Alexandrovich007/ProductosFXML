package com.ruslan.productosfxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    private Button button_consultar;

    @FXML
    private Button button_insertar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void insertarProducto(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("insertar-servicios.fxml"));

        // Cargo el padre
        Parent root = loader.load();

        // Obtengo el controlador
        InsertarServiciosController controlador = loader.getController();

        // Creo la scene y el stage
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        // Asocio el stage con el scene
        stage.setScene(scene);
        stage.show();

        // Indico que debe hacer al cerrar
        stage.setOnCloseRequest(e -> {
            try {
                controlador.closeWindows();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        // Ciero la ventana donde estoy
        Stage myStage = (Stage) this.button_insertar.getScene().getWindow();
        myStage.close();



    }
    @FXML
    private void consultarProductos(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("consultar-servicios.fxml"));

        // Cargo el padre
        Parent root = loader.load();

        // Obtengo el controlador
        ConsultarServiciosController controlador = loader.getController();

        // Creo la scene y el stage
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        // Asocio el stage con el scene

        stage.setScene(scene);
        stage.setTitle("Menú");
        stage.show();

        // Indico que debe hacer al cerrar
        stage.setOnCloseRequest(e -> {
            try {
                controlador.closeWindows();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        // Ciero la ventana donde estoy
        Stage myStage = (Stage) this.button_consultar.getScene().getWindow();
        myStage.close();


    }
}