/* 
Integrantes:Samuel Rey - Juan Motta
Grupo: 3
Proyecto 2 - java
Fecha: 10/11/2024
 */
package newpackage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author 57316
 */
public class Aerolinea extends Integrante implements Serializable {

    public Aerolinea(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Aerolinea() {
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

    void registrarAerolinea(Scanner s) {

        String nombre;
        int id;
        Random r = new Random();
        int codigo1;
        boolean ok = false;
        System.out.println("Ingrese el nombre de la aerolinea:");
        nombre = s.nextLine();
        System.out.println("Ingrese el codigo de la aerolinea (defina 2 numeros):");
        id = s.nextInt();
        Aerolinea ae = new Aerolinea(id, nombre);
        Auxiliar c = new Auxiliar();
        c.escribir(ae);
    }

    void modificarAerolinea(String criterio, Scanner s) {
        int call = 0;
        String dato;
        Aerolinea ae = new Aerolinea();
        Auxiliar a = new Auxiliar();
        ae = (Aerolinea) a.buscar(criterio, ae);
        System.out.println("Estimado usuario, recuerde que el codigo del plan es unico y no puede modificarse");
        if (ae == null) {
            System.out.println("Estimado Usuario, no existe una aerolinea registrada con ese nombre");
        }
        do {
            System.out.println("1. Modificar nombre");
            System.out.println("2. Modificar codigo de aerolinea");
            System.out.println("3. Guardar cambios");
            call = s.nextInt();
            s.nextLine();
            switch (call) {
                case 1:
                    System.out.println("Ingrese el nuevo nombre");
                    dato = s.nextLine();
                    ae.setNombre(dato);
                    break;
                case 2:
                    String dato2;
                    System.out.println("Ingrese el nuevo codigo de la aerolinea");
                    dato = s.nextLine();
                    ae.setCodigo(Integer.parseInt(dato));
                    break;
            }
        } while (call != 3);
        a.actualizarContenido(criterio, ae);
    }

    boolean buscarAerolinea(String name) {
        Auxiliar b = new Auxiliar();
        boolean found = false;
        Aerolinea a = new Aerolinea();
        a = (Aerolinea) b.buscar(name, a);
        if (a == null) {
            System.out.println("No se encontro ninguna aerolinea registrada con ese nombre");
            found = false;
        } else {
            found = true;
        }
        return found;

    }

    @Override
    public String toString() {
        System.out.format("Informaion de la aerolinea:\n Nombre: %23s\n Codigo: %20d\n ", this.nombre, this.codigo);

        return "";
    }

    void eliminarAerolinea(String name, ArrayList<Vuelo> a) {
        boolean existe = buscarAerolinea(name);
        boolean vuelosAsociados = false;
        Aerolinea ae = new Aerolinea();
        for (Vuelo i : a) {
            if (i.getA().getNombre().equals(name)) {
                vuelosAsociados = true;
            }
        }
        if (existe && !vuelosAsociados) {
            Auxiliar au = new Auxiliar();
            au.eliminar(ae, name);
        }

    }
}
