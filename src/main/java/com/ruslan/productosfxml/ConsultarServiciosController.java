package com.ruslan.productosfxml;

import com.ruslan.productosfxml.modelo.Producto;
import com.ruslan.productosfxml.repositorio.ProductoImplementarRepo;
import com.ruslan.productosfxml.repositorio.Repositorio;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ConsultarServiciosController implements Initializable {

    @FXML
    private ComboBox<Producto> cmbProductos;

    @FXML
    private TableColumn<?, ?> colCategoria;

    @FXML
    private TableColumn<?, ?> colFechaRegistro;

    @FXML
    private TableColumn<?, ?> colPrecio;

    @FXML
    private TableColumn<?, ?> colStock;

    @FXML
    private TableColumn<?, ?> col_id;

    @FXML
    private TableColumn<?, ?> col_nombre;

    @FXML
    private DatePicker dtpFechaFinal;

    @FXML
    private DatePicker dtpFechaInicial;

    @FXML
    private TableView<?> tblServicios;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCombos();
    }

    public void initCombos(){

        Repositorio<Producto> repo = new ProductoImplementarRepo();
        ObservableList<Producto> obs = repo.listarProductos();
        this.cmbProductos.setItems(obs);


    }

    public void closeWindows() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));

        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = new Stage();


        stage.setScene(scene);
        stage.setTitle("Consulta productos:");
        stage.show();

        Stage myStage = (Stage) this.cmbProductos.getScene().getWindow();
        myStage.close();
    }
}

