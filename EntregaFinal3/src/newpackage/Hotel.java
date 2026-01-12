/* 
Integrantes:Samuel Rey - Juan Motta
Grupo: 3
Proyecto 2 - java
Fecha: 10/11/2024
 */
package newpackage;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author 57316
 */
public class Hotel extends Integrante implements Serializable {

    private String ubicacion;
    private int categoria;
    private int precioNoche;

    public Hotel() {
    }

    public Hotel(String nombre, int codigo, String ubicacion, int categoria, int precioNoche) {
        super(codigo, nombre);
        this.ubicacion = ubicacion;
        this.categoria = categoria;
        this.precioNoche = precioNoche;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public int getPrecioNoche() {
        return precioNoche;
    }

    public void setPrecioNoche(int precioNoche) {
        this.precioNoche = precioNoche;
    }

    void registrarHotel(Scanner s) {

        String nombre;
        Random r = new Random();
        int codigo1;
        String ubicacion;
        int categoria;
        int precio;
        System.out.println("Ingrese el nombre del hotel:");
        nombre = s.nextLine();
        codigo1 = 1000 + r.nextInt(10000 - 1000);
        System.out.println("Su codigo unico de usuario es:" + codigo1);
        System.out.println("Introduzca la ubicacion del hotel");
        ubicacion = s.nextLine();
        System.out.println("Introduzca cuantas estrellas tiene el hotel");
        categoria = s.nextInt();
        System.out.println("Introduzca el cobro por noche en dolares");
        precio = s.nextInt();
        Hotel h = new Hotel(nombre, codigo1, ubicacion, categoria, precio);
        Auxiliar c = new Auxiliar();
        c.escribir(h);
    }

    void modificarHotel(String criterio, Scanner s, ArrayList<Reservas> ar) {
        boolean existeenregistro = false;
        for (Reservas i : ar) {
            if (i.getH().getNombre().equals(criterio)) {
                existeenregistro = true;
                System.out.println("Este Plan turistico tiene una reserva asociada, no puede modificarlo");
            }
        }
        if (existeenregistro) {
            System.out.println("Este Plan turistico tiene una reserva asociada, no puede modificarlo");
        } else {
            int call = 0;
            String dato;
            Hotel ho = new Hotel();
            Auxiliar a = new Auxiliar();
            ho = (Hotel) a.buscar(criterio, ho);
            System.out.println("Estimado usuario, recuerde que el codigo del hotel es unico y no puede modificarse");
            if (ho == null) {
                System.out.println("Estimado Usuario, no existe un hotel registrado con ese nombre");
            }
            do {
                System.out.println("1. Modificar Nombre");
                System.out.println("2. Modificar Ubicacion");
                System.out.println("3. Modificar Categoria");
                System.out.println("4. Modificar Precio por noche");
                System.out.println("5. Guardar cambios");
                call = s.nextInt();
                s.nextLine();
                switch (call) {
                    case 1:
                        System.out.println("Ingrese el nombre del hotel");
                        dato = s.nextLine();
                        ho.setNombre(dato);
                        break;
                    case 2:
                        System.out.println("Ingrese la ubicacion del hotel");
                        dato = s.nextLine();
                        ho.setUbicacion(dato);
                        break;
                    case 3:
                        System.out.println("Ingrese la categoria del hotel");
                        dato = s.nextLine();
                        ho.setCategoria(Integer.parseInt(dato));
                        break;
                    case 4:
                        System.out.println("Ingrese el precio por noche");
                        dato = s.nextLine();
                        ho.setPrecioNoche(Integer.parseInt(dato));
                        break;
                }
            } while (call != 5);
            a.actualizarContenido(criterio, ho);
        }
    }

    boolean buscarHotel(String name) {
        Auxiliar b = new Auxiliar();
        boolean found = false;
        Hotel h = new Hotel();
        h = (Hotel) b.buscar(name, h);
        if (h == null) {
            System.out.println("No se encontro ningun hotel registrado con ese nombre");
            found = false;
        } else {
            found = true;
        }
        return found;

    }

    @Override
    public String toString() {
        System.out.format("Informaion del cliente:\n Nombre: %23s\n Codigo: %20d\n Ubicacion: %22s\n Categoria: %14d\n Precio: %22d", this.nombre, this.codigo, this.ubicacion, this.categoria, this.precioNoche);

        return "";
    }

    void eliminarHotel(String name, ArrayList<Reservas> a) {
        boolean existe = buscarHotel(name);
        boolean existeenregistro = true;
        Hotel h = new Hotel();
        for (Reservas i : a) {
            if (i.getH().getNombre().equals(name)) {
                existeenregistro = true;
                System.out.println("Este hotel tiene una reserva asociada, no puede eliminarlo");
            }
        }
        if ((existe) && (!existeenregistro)) {
            Auxiliar au = new Auxiliar();
            au.eliminar(h, name);
        }

    }

}
