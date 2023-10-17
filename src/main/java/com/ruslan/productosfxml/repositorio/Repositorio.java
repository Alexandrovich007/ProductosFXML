package com.ruslan.productosfxml.repositorio;

import com.ruslan.productosfxml.modelo.Producto;
import javafx.collections.ObservableList;

import java.util.List;

public interface Repositorio <T>{ //Interfaz genérica para la tabla producto de la base de datos productos (DAO)

    //Métodos CRUD (Create, Read, Update, Delete) para la tabla producto de la base de datos productos (CRUD)
    List<T> listar(); //Método genérico para listar todos elementos de una tabla de la base de datos
    T buscarPorId(Long id); //Método genérico para listar elementos de una tabla por su id
    void agregar(T t); //Método que recibe un objeto de tipo genérico T y lo guarda en la tabla producto de la base de datos productos
    void actualizar(T t); //Método que modifica un objeto de tipo genérico T y lo guarda en la tabla producto de la base de datos productos
    boolean eliminar(Long id); //Método que elimina un objeto de tipo genérico T por su id. Se recibe el id del objeto a eliminar como parámetro del método.
    //void rellenarIDs(T t); //Método que rellena el ArrayList de ids con los ids de los objetos de la tabla producto de la base de datos productos
    ObservableList<T> listarProductos();

}