package com.ruslan.productosfxml;

import com.ruslan.productosfxml.modelo.Producto;
import com.ruslan.productosfxml.repositorio.ProductoImplementarRepo;
import com.ruslan.productosfxml.repositorio.Repositorio;

public class ListarProductos {
    public static void main(String[] args) {

        Repositorio<Producto> repo = new ProductoImplementarRepo();
        System.out.println("------ Listar productos ------ ");
        repo.listar().forEach(System.out::println); //Usamos el método forEach() de la interfaz List para imprimir cada elemento de la lista

    }
}
