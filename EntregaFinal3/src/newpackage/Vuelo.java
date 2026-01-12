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
public class Vuelo extends Integrante implements Serializable {

    private Aerolinea a;
    private String origen;
    private String destino;
    private Date fechayhora;
    int precio;

    public Vuelo(int codigo, Aerolinea a, String origen, String destino, Date fechayhora, int precio) {
        super(codigo);
        this.a = a;
        this.origen = origen;
        this.destino = destino;
        this.fechayhora = fechayhora;
        this.precio = precio;
    }

    public Vuelo() {
    }

    public int getCodigo() {
        return codigo;
    }

    public Aerolinea getA() {
        return a;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public Date getFechayhora() {
        return fechayhora;
    }

    public int getPrecio() {
        return precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setA(Aerolinea a) {
        this.a = a;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setFechayhora(Date fechayhora) {
        this.fechayhora = fechayhora;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    void registrarVuelo(Scanner s) {

        String origen = null;
        String destino = null;
        String nAerolinea;
        int codeAerolinea;
        SimpleDateFormat d = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Random r = new Random();
        int codigo1;
        Date di = null;
        Date hi = null;
        int precio;
        boolean ok = false;
        codigo1 = 10 + r.nextInt(100 - 10);
        System.out.println("El codigo asignado al vuelo es:" + codigo1);
        System.out.println("Ingrese el nombre de la aerolinea que llevara a cabo el vuelo:");
        nAerolinea = s.nextLine();
        Aerolinea check = new Aerolinea();
        if (!check.buscarAerolinea(nAerolinea)) {
            System.out.println("No existe la aerolinea en el sistema, registrela e intente de nuevo");
        } else {
            check.setNombre(nAerolinea);
            System.out.println("Introduzca el codigo asociado a la aerolinea ");
            codeAerolinea = s.nextInt();
            check.setCodigo(codeAerolinea);
            s.nextLine();
            System.out.println("Introduzca el lugar de origen del vuelo ");
            origen = s.nextLine();
            System.out.println("Introduzca el destino del vuelo ");
            destino = s.nextLine();
            do {
                try {
                    System.out.println("Ingrese la fecha y hora del vuelo con el formato (yyyy/MM/dd HH:mm)");
                    String fecha = s.nextLine();
                    di = d.parse(fecha);
                    ok = true;
                } catch (ParseException e) {
                    System.out.println("Por favor introduzca la fecha en el formato indicado");
                }
            } while (!ok);
            System.out.println("Introduzca el precio del vuelo");
            precio = s.nextInt();
            Vuelo v = new Vuelo(codigo1, check, origen, destino, di, precio);
            Auxiliar c = new Auxiliar();
            c.escribir(v);
        }
    }

    void modificarVuelo(String criterio, Scanner s, ArrayList<Reservas> ar) {
        boolean existeenregistro = false;
        for (Reservas i : ar) {
            if (String.valueOf(i.getV().getCodigo()).equals(criterio)) {
                existeenregistro = true;
                System.out.println("Este vuelo tiene una reserva asociada, no puede modificarlo");
            }
        }
        if (existeenregistro) {
            System.out.println("Este Plan turistico tiene una reserva asociada, no puede modificarlo");
        } else {
            int call = 0;
            String dato;
            Vuelo vo = new Vuelo();
            Auxiliar a = new Auxiliar();
            vo = (Vuelo) a.buscar(criterio, vo);
            System.out.println("Estimado usuario, recuerde que el codigo del plan es unico y no puede modificarse");
            if (vo == null) {
                System.out.println("Estimado Usuario, no existe un vuelo con ese codigo");
            }
            do {
                System.out.println("1. Modificar Aerolinea");
                System.out.println("2. Modificar Origen y destino");
                System.out.println("3. Modificar Fecha y hora");
                System.out.println("4. Modificar precio");
                System.out.println("5. Guardar cambios");
                call = s.nextInt();
                s.nextLine();
                switch (call) {
                    case 1:
                        System.out.println("Ingrese la nueva aerolinea");
                        dato = s.nextLine();
                        Aerolinea check = new Aerolinea();
                        if (!check.buscarAerolinea(dato)) {
                            System.out.println("No existe la aerolinea en el sistema, registrela e intente de nuevo");
                        } else {
                            check.setNombre(dato);
                            System.out.println("Introduzca el codigo asociado a la aerolinea ");
                            int codeAerolinea = s.nextInt();
                            check.setCodigo(codeAerolinea);
                            s.nextLine();
                            vo.setA(check);
                        }
                        break;
                    case 2:
                        String dato2;
                        System.out.println("Ingrese el nuevo Origen del plan");
                        dato = s.nextLine();
                        vo.setOrigen(dato);
                        System.out.println("Ingrese el nuevo destino del plan");
                        dato = s.nextLine();
                        vo.setDestino(dato);
                        break;
                    case 3:
                        String fechafinal;
                        Date dd = null;
                        Date df = null;
                        boolean oki = false;
                        SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
                        do {
                            try {
                                System.out.println("Ingrese la nueva fecha de vuelo con el formato (yyyy/MM/dd HH:mm)");
                                dato = s.nextLine();
                                dd = d.parse(dato);
                            } catch (ParseException e) {
                                System.out.println("Por favor introduzca la fecha en el formato indicado");
                            }
                        } while (!oki);
                        vo.setFechayhora(dd);
                        break;
                    case 4:
                        System.out.println("Ingrese el precio:");
                        dato = s.nextLine();
                        vo.setPrecio(Integer.parseInt(dato));
                        break;
                }
            } while (call != 5);
            a.actualizarContenido(criterio, vo);
        }
    }

    boolean buscarVuelo(int codigo) {
        Auxiliar b = new Auxiliar();
        Vuelo v = new Vuelo();
        boolean found = false;
        String code = String.valueOf(codigo);
        v = (Vuelo) b.buscar(code, v);
        if (v == null) {
            System.out.println("No se encontro ningun vuelo registrado con ese codigo");
            found = false;
        } else {
            found = true;
        }
        return found;

    }

    @Override
    public String toString() {
        SimpleDateFormat mostrar = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        System.out.format("Informaion del vuelo:\n Codigo: %24d\n Aerolinea: %24s\n Codigo de Aerolinea: %11d\n Origen: %30s\n Destino: %25s\n Fecha de inicio: %29s\n int precio: %24d", this.codigo, this.a.getNombre(), this.a.getCodigo(), this.origen, this.destino, mostrar.format(this.fechayhora), this.precio);

        return "";
    }

    void eliminarVuelo(int codigo, ArrayList<Reservas> a) {
        boolean existe = buscarVuelo(codigo);
        boolean existeenregistro = true;
        Vuelo v = new Vuelo();
        for (Reservas i : a) {
            if (i.getV().getCodigo() == codigo) {
                existeenregistro = true;
                System.out.println("Este vuelo tiene una reserva asociada, no puede eliminarlo");
            }
        }
        if ((existe) && (!existeenregistro)) {
            Auxiliar au = new Auxiliar();
            au.eliminar(v, String.valueOf(codigo));
        }

    }

}
