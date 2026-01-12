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
public class PlanTuristico extends Integrante implements Serializable {

    String descripcion;
    String destino;
    Date fechaInicio;
    Date fechaSalida;
    int precio;

    public PlanTuristico(int codigo, String nombre, String descripcion, String destino, Date fechaInicio, Date fechaSalida, int precio) {
        super(codigo, nombre);
        this.descripcion = descripcion;
        this.destino = destino;
        this.fechaInicio = fechaInicio;
        this.fechaSalida = fechaSalida;
        this.precio = precio;
    }

    public PlanTuristico() {

    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getDestino() {
        return destino;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public int getPrecio() {
        return precio;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    void registrarPlan(Scanner s) {

        String nombre;
        String destino;
        String descripcion;
        SimpleDateFormat d = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Random r = new Random();
        int codigo1;
        Date di = null;
        Date df = null;
        int precio;
        boolean ok = false;
        System.out.println("Ingrese el nombre del plan turistico:");
        nombre = s.nextLine();
        codigo1 = 100 + r.nextInt(1000 - 100);
        System.out.println("El codigo del plan es:" + codigo1);
        System.out.println("Ingrese el destino de su viaje:");
        destino = s.nextLine();
        System.out.println("Ingrese la descripcion del plan turistico:");
        descripcion = s.nextLine();
        do {
            try {
                System.out.println("Ingrese la fecha de inicio del plan con el formato (yyyy/MM/dd HH:mm)");
                String fecha = s.nextLine();
                di = d.parse(fecha);
                System.out.println("Ingrese la fecha donde finaliza su plan con el formato (yyyy/MM/dd HH:mm)");
                fecha = s.nextLine();
                df = d.parse(fecha);
                ok = true;
            } catch (ParseException e) {
                System.out.println("Por favor introduzca la fecha en el formato indicado");
            }
        } while (!ok);
        System.out.println("Introduzca el precio del plan");
        precio = s.nextInt();
        PlanTuristico t = new PlanTuristico(codigo1, nombre, descripcion, destino, di, df, precio);
        Auxiliar c = new Auxiliar();
        c.escribir(t);
    }

    boolean buscarPlan(String name) {
        Auxiliar b = new Auxiliar();
        boolean found = false;
        PlanTuristico t = new PlanTuristico();
        t = (PlanTuristico) b.buscar(name, t);
        if (t == null) {
            System.out.println("No se encontro ningun plan registrado con ese nombre");
            found = false;
        } else {
            found = true;
        }
        return found;

    }

    void modificarPlan(String criterio, Scanner s, ArrayList<Reservas> ar) {
        boolean existeenregistro = false;
        for (Reservas i : ar) {
            if (i.getT().getNombre().equals(criterio)) {
                existeenregistro = true;
                System.out.println("Este Plan turistico tiene una reserva asociada, no puede modificarlo");
            }
        }
        if (existeenregistro) {
            System.out.println("Este Plan turistico tiene una reserva asociada, no puede modificarlo");
        } else {
            int call = 0;
            String dato;
            PlanTuristico p = new PlanTuristico();
            Auxiliar a = new Auxiliar();
            p = (PlanTuristico) a.buscar(criterio, p);
            System.out.println("Estimado usuario, recuerde que el codigo del plan es unico y no puede modificarse");
            if (p == null) {
                System.out.println("Estimado Usuario, no existe un plan registrado con ese nombre");
            }
            do {
                System.out.println("1. Modificar nombre");
                System.out.println("2. Modificar destino y descripcion");
                System.out.println("3. Modificar Fechas");
                System.out.println("4. Modificar precio");
                System.out.println("5. Guardar cambios");
                call = s.nextInt();
                s.nextLine();
                switch (call) {
                    case 1:
                        System.out.println("Ingrese el nuevo nombre del plan");
                        dato = s.nextLine();
                        p.setNombre(dato);
                        break;
                    case 2:
                        String dato2;
                        System.out.println("Ingrese el nuevo destino del plan");
                        dato = s.nextLine();
                        p.setDestino(dato);
                        System.out.println("Ingrese la nueva descripcion del plan");
                        dato = s.nextLine();
                        p.setDescripcion(dato);
                        break;
                    case 3:
                        String fechafinal;
                        Date dd = null;
                        Date df = null;
                        boolean oki = false;
                        SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
                        do {
                            try {
                                System.out.println("Ingrese la nueva fecha de inicio con el formato (yyyy/MM/dd HH:mm)");
                                dato = s.nextLine();
                                dd = d.parse(dato);
                                System.out.println("Ingrese la nueva fecha de finalizacion con el formato (yyyy/MM/dd HH:mm)");
                                dato = s.nextLine();
                                df = d.parse(dato);
                                oki = true;
                            } catch (ParseException e) {
                                System.out.println("Por favor introduzca la fecha en el formato indicado");
                            }
                        } while (!oki);
                        p.setFechaInicio(dd);
                        p.setFechaSalida(df);
                        break;
                    case 4:
                        System.out.println("Ingrese el precio:");
                        dato = s.nextLine();
                        p.setPrecio(Integer.parseInt(dato));
                        break;
                }
            } while (call != 5);
            a.actualizarContenido(criterio, p);
        }
    }

    @Override
    public String toString() {
        SimpleDateFormat mostrar = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        System.out.format("Informaion del plan turistico:\n Nombre : %23s\n Codigo: %22d\n Descripcion: %21s\n Destino: %29s\n Fecha de inicio: %26s\n Fecha final: %30s\n Precio: %25d", this.nombre, this.codigo, this.descripcion, this.destino, mostrar.format(this.fechaInicio), mostrar.format(this.fechaSalida), this.precio);

        return "";
    }

    void eliminarPlan(String name, ArrayList<Reservas> a) {
        boolean existe = buscarPlan(name);
        boolean existeenregistro = true;
        PlanTuristico t = new PlanTuristico();
        for (Reservas i : a) {
            if (i.getC().getNombre().equals(name)) {
                existeenregistro = true;
                System.out.println("Este Plan turistico tiene una reserva asociada, no puede eliminarlo");
            }
        }
        if ((existe) && (!existeenregistro)) {
            Auxiliar au = new Auxiliar();
            au.eliminar(t, name);
        }

    }

}
