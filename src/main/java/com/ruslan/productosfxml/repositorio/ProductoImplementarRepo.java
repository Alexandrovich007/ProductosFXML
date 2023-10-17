package com.ruslan.productosfxml.repositorio;

import com.ruslan.productosfxml.modelo.Categoria;
import com.ruslan.productosfxml.modelo.Producto;

import com.ruslan.productosfxml.util.ConexionBBDD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductoImplementarRepo implements Repositorio<Producto> {

    /*private Connection getConnection() throws SQLException, IOException, ClassNotFoundException { //Método para obtener la conexión a la BBDD mediante el método getInstance() de la clase ConexionBBDD
        return ConexionBBDD.getInstance();
    } */




    @Override
    public Producto buscarPorId(Long id) {
        Producto producto = null;


        try (Connection conexion = ConexionBBDD.getConexion()) {
            PreparedStatement stmt = conexion.prepareStatement("SELECT p.*, c.nombre as categoria FROM producto as p INNER JOIN categorias as c ON (p.categoria_id = c.id) WHERE p.id = ?");
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    producto = crearProducto(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return producto;

    }

    @Override
    public void agregar(Producto producto) {


        try (Connection conexion = ConexionBBDD.getConexion()) {
            System.out.println("hola");
            PreparedStatement stmt = conexion.prepareStatement("INSERT INTO producto (nombre, precio, fecha_registro, categoria_id) VALUES (?, ?, ?, ?)");
            stmt.setString(1, producto.getNombre());
            stmt.setDouble(2, producto.getPrecio());
            stmt.setDate(3, new Date(producto.getFechaRegistro().getTime()));
            stmt.setLong(4, producto.getCategoria().getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void actualizar(Producto producto) {

        try (Connection conexion = ConexionBBDD.getConexion()) {
            PreparedStatement stmt = conexion.prepareStatement("UPDATE producto SET nombre = ?, precio = ?, categoria_id = ? WHERE id = ?");
            stmt.setString(1, producto.getNombre());
            stmt.setDouble(2, producto.getPrecio());
            stmt.setLong(3, producto.getCategoria().getId());
            stmt.setLong(4, producto.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean eliminar(Long id) {

        try (Connection conexion = ConexionBBDD.getConexion()) {
            PreparedStatement stmt = conexion.prepareStatement("DELETE FROM producto WHERE id = ?");
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    private static Producto crearProducto(ResultSet rs) throws SQLException {
        Producto producto = new Producto();
        producto.setId(rs.getLong("id"));
        producto.setNombre(rs.getString("nombre"));
        producto.setPrecio(rs.getDouble("precio"));
        producto.setFechaRegistro(rs.getDate("fecha_registro"));
        Categoria categoria = new Categoria();
        categoria.setId(rs.getLong("categoria_id"));
        categoria.setNombre(rs.getString("categoria"));
        producto.setCategoria(categoria);

        return producto;

    }
    @Override
    public List<Producto> listar() {
        List<Producto> productos = new ArrayList<>();

        try (Connection conexion = ConexionBBDD.getConexion()) {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT p.*, c.nombre as categoria FROM producto as p INNER JOIN categorias as c ON p.categoria_id = c.id");

            while (rs.next()) {
                Producto producto = crearProducto(rs);
                productos.add(producto);
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return productos;
    }
    public ObservableList<Producto> listarProductos()  {


        ObservableList<Producto> obs = FXCollections.observableArrayList();

        try ( Connection conexion = ConexionBBDD.getConexion()) {
            if(conexion != null){
                System.out.println("Conexion ok");
            }
            Statement stmt = conexion.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT p.*, c.nombre as categoria FROM producto as p INNER JOIN categorias as c ON p.categoria_id = c.id");
            System.out.println("HOla");
            while (rs.next()) {

                //Producto producto = crearProducto(rs);
                Long id = rs.getLong("id");
                String nombre = rs.getString("nombre");
                Double precio = rs.getDouble("precio");
                Date fecha_registro = rs.getDate("fecha_registro");

                Producto p = new Producto(id, nombre, precio, fecha_registro);
                obs.add(p);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return obs;



    }



}
