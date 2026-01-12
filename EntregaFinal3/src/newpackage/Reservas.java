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

public class Reservas implements Serializable {

    private Cliente c;
    private PlanTuristico t;
    private Vuelo v;
    private Hotel h;
    Date fechaReserva;

    public Reservas() {
    }

    public Reservas(Cliente c, Date fechaReserva) {
        this.c = c;
        this.fechaReserva = fechaReserva;
    }

    public Cliente getC() {
        return c;
    }

    public void setC(Cliente c) {
        this.c = c;
    }

    public PlanTuristico getT() {
        return t;
    }

    public void setT(PlanTuristico t) {
        this.t = t;
    }

    public Vuelo getV() {
        return v;
    }

    public void setV(Vuelo v) {
        this.v = v;
    }

    public Hotel getH() {
        return h;
    }

    public void setH(Hotel h) {
        this.h = h;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    void reservar(Scanner s) {
        String nombre;
        SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
        Date dd = null;
        Auxiliar obtener = new Auxiliar();
        int codigo = 0;
        int call = 0;
        boolean ok = false;
        boolean encontrado = false;
        Cliente p = new Cliente();
        System.out.println("Ingrese el nombre con el que creo su cuenta de cliente:");
        nombre = s.nextLine();
        p = (Cliente) obtener.buscar(nombre, p);
        if (p == null) {
            System.out.println("El cliente no existe");
        } else {
            do {
                try {
                    System.out.println("Ingrese la fecha del dia de hoy (dd/MM/yyyy)");
                    String fecha = s.nextLine();
                    dd = d.parse(fecha);
                    ok = true;
                } catch (ParseException e) {
                    System.out.println("Por favor introduzca la fecha en el formato indicado");
                }
            } while (!ok);
            Reservas r = new Reservas(p, dd);
            do {
                System.out.println("Elija una de las siguientes opciones");
                System.out.println("1. Agregar plan turistico");
                System.out.println("2. Agregar vuelo");
                System.out.println("3. Agregar hotel");
                System.out.println("4. Completar registro (Elija esta opcion si su reserva esta lista)");
                call = s.nextInt();
                s.nextLine();
                switch (call) {
                    case 1:
                        PlanTuristico pt = new PlanTuristico();
                        System.out.println("Ingrese el nombre del plan turistico que desea agregar");
                        nombre = s.nextLine();
                        pt = (PlanTuristico) obtener.buscar(nombre, pt);
                        if (pt == null) {
                            System.out.println("No existe un plan turistico con ese nombre");
                            break;
                        } else {
                            r.setT(pt);
                        }
                        break;
                    case 2:
                        Vuelo v = new Vuelo();
                        System.out.println("Ingrese el codigo del vuelo que desea agregar");
                        nombre = s.nextLine();
                        v = (Vuelo) obtener.buscar(nombre, v);
                        if (v == null) {
                            System.out.println("No existe un vuelo con ese codigo");
                            break;
                        }
                        r.setV(v);
                        break;
                    case 3:
                        Hotel h = new Hotel();
                        System.out.println("Ingrese el nombre hotel que desea agregar");
                        nombre = s.nextLine();
                        h = (Hotel) obtener.buscar(nombre, h);
                        if (h == null) {
                            System.out.println("No existe un hotel con ese nombre");
                            break;
                        }
                        r.setH(h);
                        break;
                    case 4:
                        obtener.escribir(r);
                        break;
                }
            } while (call != 4);
        }

    }

    void modificarReserva(String criterio, Scanner s) {
        int call = 0;
        int call2;
        String nombre;
        Reservas rv = new Reservas();
        Auxiliar a = new Auxiliar();
        rv = (Reservas) a.buscar(criterio, rv);
        if (rv == null) {
            System.out.println("Estimado Usuario, no existe ninguna reserva asociada al nombre");
        }
        do {
            System.out.println("1. Agregar/modificar/eliminar plan turistico");
            System.out.println("2. Agregar/modificar/eliminar vuelo");
            System.out.println("3. Agregar/modifica/eliminar hotel");
            System.out.println("4. Guardar cambios");
            call = s.nextInt();
            switch (call) {
                case 1:
                    System.out.println("1. Agregar/modificar");
                    System.out.println("2. Eliminar");
                    s.nextLine();
                    call2 = s.nextInt();
                    s.nextLine();
                    switch (call2) {
                        case 1:
                            PlanTuristico pt = new PlanTuristico();
                            System.out.println("Ingrse el nombre del plan turistico que desea agregar/modificar");
                            nombre = s.nextLine();
                            pt = (PlanTuristico) a.buscar(nombre, pt);
                            rv.setT(pt);
                            break;
                        case 2:
                            rv.setT(null);
                            break;
                    }
                    break;
                case 2:
                    System.out.println("1. Agregar/modificar");
                    System.out.println("2. Eliminar");
                    call2 = s.nextInt();
                    s.nextLine();
                    switch (call2) {
                        case 1:
                            Vuelo v = new Vuelo();
                            System.out.println("Ingrse el codigo del vuelo que quiere agregar");
                            nombre = s.nextLine();
                            v = (Vuelo) a.buscar(nombre, v);
                            rv.setV(v);
                            break;
                        case 2:
                            rv.setV(null);
                            break;
                    }
                    break;
                case 3:
                    System.out.println("1. Agregar/modificar");
                    System.out.println("2. Eliminar");
                    call2 = s.nextInt();
                    s.nextLine();
                    switch (call2) {
                        case 1:
                            Hotel ht = new Hotel();
                            System.out.println("Ingrse el nombre del hotel que desea agregar/modificar");
                            nombre = s.nextLine();
                            ht = (Hotel) a.buscar(nombre, ht);
                            rv.setH(ht);
                            break;
                        case 2:
                            rv.setH(null);
                            break;
                    }
                    break;
            }
        } while (call != 4);
        a.actualizarContenido(criterio, rv);
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Funciones auxiliares
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    boolean buscarReserva(String name) {
        Auxiliar b = new Auxiliar();
        boolean found = false;
        Reservas rv = new Reservas();
        rv = (Reservas) b.buscar(name, rv);
        if (rv == null) {
            System.out.println("No se encontro ninguna reserva asociada al cliente");
            found = false;
        } else {
            found = true;
        }
        return found;
    }

    void cancelarReserva(String criterio) {
        boolean condicion1 = buscarReserva(criterio);
        if (condicion1) {
            Auxiliar au = new Auxiliar();
            Reservas rv = new Reservas();
            au.eliminar(rv, criterio);
        }

    }

    public String getNombreCliente() {
        if (c != null) {
            return c.getNombre();
        } else {
            return "N/A";
        }
    }

    public String getNombrePlan() {
        if (t != null) {
            return t.getNombre();
        } else {
            return "N/A";
        }
    }

    public String getFechaInicioPlan(SimpleDateFormat mostrar) {
        if (t != null && t.getFechaInicio() != null) {
            return mostrar.format(t.getFechaInicio());
        } else {
            return "N/A";
        }
    }

    public String getFechaSalidaPlan(SimpleDateFormat mostrar) {
        if (t != null && t.getFechaSalida() != null) {
            return mostrar.format(t.getFechaSalida());
        } else {
            return "N/A";
        }
    }

    public String getCodigoVuelo() {
        if (v != null) {
            return String.valueOf(v.getCodigo());
        } else {
            return "N/A";
        }
    }

    public String getOrigenVuelo() {
        if (v != null) {
            return v.getOrigen();
        } else {
            return "N/A";
        }
    }

    public String getDestinoVuelo() {
        if (v != null) {
            return v.getDestino();
        } else {
            return "N/A";
        }
    }

    public String getPrecioVuelo() {
        if (v != null) {
            return String.valueOf(v.getPrecio());
        } else {
            return "N/A";
        }
    }

    public String getNombreHotel() {
        if (h != null) {
            return h.getNombre();
        } else {
            return "N/A";
        }
    }

    public String getUbicacionHotel() {
        if (h != null) {
            return h.getUbicacion();
        } else {
            return "N/A";
        }
    }

}
