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
public class Cliente extends Integrante implements Serializable {

    private Date fechaNacimiento;
    private String informacionDeContacto;

    public Cliente(int codigo, String nombre, Date fechaNacimiento, String informacionDeContacto) {
        super(codigo, nombre);
        this.fechaNacimiento = fechaNacimiento;
        this.informacionDeContacto = informacionDeContacto;
    }

    public Cliente() {
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getInformacionDeContacto() {
        return informacionDeContacto;
    }

    public void setInformacionDeContacto(String informacionDeContacto) {
        this.informacionDeContacto = informacionDeContacto;
    }

    void registrarCliente(Scanner s) {

        String nombre;
        SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
        Random r = new Random();
        int codigo1;
        Date dd = null;
        String info;
        boolean ok = false;
        System.out.println("Ingrese su nombre:");
        nombre = s.nextLine();
        codigo1 = 100000 + r.nextInt(1000000 - 100000);
        System.out.println("Su codigo unico de usuario es:" + codigo1);
        do {
            try {
                System.out.println("Ingrese su fecha de nacimiento con el formato (dd/MM/yyyy)");
                String fecha = s.nextLine();
                dd = d.parse(fecha);
                ok = true;
            } catch (ParseException e) {
                System.out.println("Por favor introduzca la fecha en el formato indicado");
            }
        } while (!ok);
        System.out.println("Introduzca su numero de celular");
        info = s.nextLine();
        Cliente n = new Cliente(codigo1, nombre, dd, info);
        Auxiliar c = new Auxiliar();
        c.escribir(n);
    }

    boolean buscarCliente(String name) {
        Auxiliar b = new Auxiliar();
        boolean found = false;
        Cliente c = new Cliente();
        c = (Cliente) b.buscar(name, c);
        if (c == null) {
            System.out.println("No se encontro ningun usuario registrado con ese nombre");
            found = false;
        } else {
            found = true;
        }
        return found;

    }

    @Override
    public String toString() {
        SimpleDateFormat mostrar = new SimpleDateFormat("dd/MM/yyyy");
        System.out.format("Informaion del cliente:\n Nombre: %24s\n Codigo: %24d\n Fecha de nacimiento: %15s\n Info. de contacto: %15s", this.nombre, this.codigo, mostrar.format(this.fechaNacimiento), this.informacionDeContacto);

        return "";
    }

    void modificarCliente(String criterio, Scanner s, ArrayList<Reservas> ar) {
        boolean existeenregistro = false;
        for (Reservas i : ar) {
            if (i.getC().getNombre().equals(criterio)) {
                existeenregistro = true;
                System.out.println("Este cliente tiene una reserva asociada, no puede modificarlo");
            }
        }
        if (existeenregistro) {
            System.out.println("Este Plan turistico tiene una reserva asociada, no puede modificarlo");
        } else {
            int call = 0;
            String dato;
            Cliente cl = new Cliente();
            Auxiliar a = new Auxiliar();
            cl = (Cliente) a.buscar(criterio, cl);
            System.out.println("Estimado usuario, recuerde que su codigo es unico y no puede modificarse");
            if (cl == null) {
                System.out.println("Estimado Usuario, no existe un cliente registrado con ese nombre");
            }
            do {
                System.out.println("1. Modificar nombre");
                System.out.println("2. Modificar fecha de nacimiento");
                System.out.println("3. Modificar datos de contacto");
                System.out.println("4. Guardar cambios");
                call = s.nextInt();
                s.nextLine();
                switch (call) {
                    case 1:
                        System.out.println("Ingrese el nuevo nombre de usuario");
                        dato = s.nextLine();
                        cl.setNombre(dato);
                        break;
                    case 2:
                        String fecha;
                        Date dd = null;
                        boolean oki = false;
                        SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
                        do {
                            try {
                                System.out.println("Ingrese la nueva fecha de nacimiento con el formato (dd/MM/yyyy)");
                                dato = s.nextLine();
                                dd = d.parse(dato);
                                oki = true;
                            } catch (ParseException e) {
                                System.out.println("Por favor introduzca la fecha en el formato indicado");
                            }
                        } while (!oki);
                        cl.setFechaNacimiento(dd);
                        break;
                    case 3:
                        System.out.println("Ingrese su nueva informacion de contacto");
                        dato = s.nextLine();
                        cl.setInformacionDeContacto(dato);
                        break;
                }
            } while (call != 4);
            a.actualizarContenido(criterio, cl);
        }
    }

    void eliminarCliente(String name, ArrayList<Reservas> a) {
        boolean existe = buscarCliente(name);
        boolean existeenregistro = false;
        Cliente c = new Cliente();
        for (Reservas i : a) {
            if (i.getC().getNombre().equals(name)) {
                existeenregistro = true;
                System.out.println("Este cliente tiene una reserva asociada, no puede eliminarlo");
            }
        }
        if ((existe) && (!existeenregistro)) {
            Auxiliar au = new Auxiliar();
            au.eliminar(c, name);
        }

    }

}
