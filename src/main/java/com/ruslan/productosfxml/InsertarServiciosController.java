package com.ruslan.productosfxml;

import com.ruslan.productosfxml.modelo.Producto;
import com.ruslan.productosfxml.repositorio.ProductoImplementarRepo;
import com.ruslan.productosfxml.repositorio.Repositorio;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InsertarServiciosController implements Initializable {

    @FXML
    private Button button_grabar;

    @FXML
    private ComboBox<?> cmbCategorias;

    @FXML
    private ComboBox<Producto> cmbProducto;

    @FXML
    private DatePicker dtpFechaEntrega;

    @FXML
    private DatePicker dtpFechaRegistro;

    @FXML
    private TextField tf_categoria;

    @FXML
    private TextField tf_id;

    @FXML
    private TextField tf_nombre;

    @FXML
    private TextField tf_precio;

    @FXML
    private TextField txtDirCli;

    @FXML
    private TextField txtNIFCli;

    @FXML
    private TextField txtPobCli;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //initCombos();
    }

    public void initCombos(){

        Repositorio<Producto> repo = new ProductoImplementarRepo();
        ObservableList<Producto> obs = repo.listarProductos();
        this.cmbProducto.setItems(obs);


    }



    public void closeWindows() throws IOException {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));

            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setTitle("Inserción productos:");

            stage.setScene(scene);
            stage.show();

            Stage myStage = (Stage) this.button_grabar.getScene().getWindow();
            myStage.close();



    }

    public void grabarProducto(ActionEvent event) {
    }
}




