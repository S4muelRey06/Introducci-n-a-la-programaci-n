
package newpackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainProvisional {

    public static void main(String args[]) {
        ArrayList<Reservas> reporteFinal = new ArrayList();
        Reservas rev = new Reservas();
        int contadorRegistros=0;
        Auxiliar auxiliar = new Auxiliar();
        Scanner s = new Scanner(System.in);
        int opcion, opcion2 = 0;
        try {
            do{
                reporteFinal = auxiliar.RevisarReservas(rev);
                System.out.println("1. Gestion de Clientes");
                System.out.println("2. Gestion de planes turisticos");
                System.out.println("3. Gestion de Vuelos");
                System.out.println("4. Gestion de Aerolineas");
                System.out.println("5. Gestion de Hoteles");
                System.out.println("6. Gestion de Reservas");
                System.out.println("7. Salir");
                System.out.print("Ingrese una opción: ");
                opcion = s.nextInt();

                switch (opcion) {
                    case 1:
                        Cliente c = new Cliente();
                        System.out.println("1. Registrar");
                        System.out.println("2. Modificar datos");
                        System.out.println("3. Busqueda por nombre");
                        System.out.println("4. Eliminar");
                        opcion2 = s.nextInt();
                        switch (opcion2) {
                            case 1:
                                s.nextLine();
                                c.registrarCliente(s);
                                break;
                            case 2:
                                String name;
                                System.out.println("Ingrese el nombre con el que creo la cuenta de cliente");
                                s.nextLine();
                                name = s.nextLine();
                                c.modificarCliente(name, s, reporteFinal);
                                break;
                            case 3:
                                String namef;
                                System.out.println("Ingrese el nombre con el que creo la cuenta de cliente");
                                s.nextLine();
                                namef = s.nextLine();
                                c.buscarCliente(namef);
                                break;
                            case 4:
                                String nameE;
                                System.out.println("Ingrese el nombre con el que creo la cuenta de cliente");
                                s.nextLine();
                                nameE = s.nextLine();
                                c.eliminarCliente(nameE, reporteFinal);

                        }
                        break;
                    case 2:
                        PlanTuristico t = new PlanTuristico();
                        System.out.println("1. Registrar");
                        System.out.println("2. Modificar datos");
                        System.out.println("3. Busqueda por nombre");
                        System.out.println("4. Eliminar");
                        opcion2 = s.nextInt();
                        switch (opcion2) {
                            case 1:
                                s.nextLine();
                                t.registrarPlan(s);
                                break;
                            case 2:
                                String name;
                                System.out.println("Ingrese el nombre con el que creo la cuenta de cliente");
                                s.nextLine();
                                name = s.nextLine();
                                t.modificarPlan(name, s, reporteFinal);
                            case 3:
                                String namef;
                                System.out.println("Ingrese el nombre del plan turistico:");
                                s.nextLine();
                                namef = s.nextLine();
                                t.buscarPlan(namef);
                                break;
                            case 4:
                                String nameE;
                                System.out.println("Ingrese el nombre del plan turistico:");
                                s.nextLine();
                                nameE = s.nextLine();
                                t.eliminarPlan(nameE, reporteFinal);
                                break;
                        }
                        break;
                    case 3:
                        Vuelo fly = new Vuelo();
                        System.out.println("1. Registrar");
                        System.out.println("2. Modificar datos");
                        System.out.println("3. Busqueda por codigo");
                        System.out.println("4. Eliminar");
                        System.out.println("Advertencia: Deben existir aerolineas para registrar vuelos. Si no las ha creado, oprima cualquier numero para salir");
                        opcion2 = s.nextInt();
                        switch (opcion2) {
                            case 1:
                                s.nextLine();
                                fly.registrarVuelo(s);
                                break;
                            case 2: 
                                String name;
                                System.out.println("Ingrese el nombre con el que creo la cuenta de cliente");
                                s.nextLine();
                                name = s.nextLine();
                                fly.modificarVuelo(name, s, reporteFinal);
                            case 3:
                                int code;
                                System.out.println("Ingrese el codigo del vuelo:");
                                s.nextLine();
                                code = s.nextInt();
                                fly.buscarVuelo(code);
                                break;
                            case 4:
                                System.out.println("Ingrese el codigo del vuelo:");
                                s.nextLine();
                                code = s.nextInt();
                                fly.eliminarVuelo(code, reporteFinal);
                                break;
                        }
                        break;
                    case 4:
                        Aerolinea av = new Aerolinea();
                        System.out.println("1. Registrar");
                        System.out.println("2. Modificar datos");
                        System.out.println("3. Busqueda por nombre");
                        System.out.println("4. Eliminar");
                        opcion2 = s.nextInt();
                        s.nextLine();
                        switch (opcion2) {
                            case 1:
                                av.registrarAerolinea(s);
                                break;
                            case 2:
                                String name;
                                System.out.println("Ingrese el nombre con el que creo la cuenta de cliente");
                                s.nextLine();
                                name = s.nextLine();
                                av.modificarAerolinea(name, s);
                            case 3:
                                String namef;
                                System.out.println("Ingrese el nombre de la aerolinea:");
                                namef = s.nextLine();
                                av.buscarAerolinea(namef);
                                break;
                            case 4:
                                Vuelo vu = new Vuelo();
                                ArrayList<Vuelo> reg = auxiliar.RevisarVuelos(vu);
                                System.out.println("Ingrese el nombre de la aerolinea");
                                namef = s.nextLine();
                                av.eliminarAerolinea(namef, reg);
                                break;
                        }
                        break;
                    case 5:
                        Hotel ht = new Hotel();
                        System.out.println("1. Registrar");
                        System.out.println("2. Modificar datos");
                        System.out.println("3. Busqueda por nombre");
                        System.out.println("4. Eliminar");
                        opcion2 = s.nextInt();
                        s.nextLine();
                        switch (opcion2) {
                            case 1:
                                ht.registrarHotel(s);
                                break;
                            case 2:
                                String name;
                                System.out.println("Ingrese el nombre con el que creo la cuenta de cliente");
                                s.nextLine();
                                name = s.nextLine();
                                ht.modificarHotel(name, s, reporteFinal);
                            case 3:
                                String namef;
                                System.out.println("Ingrese el nombre del hotel:");
                                namef = s.nextLine();
                                ht.buscarHotel(namef);
                                break;
                            case 4:
                                System.out.println("Ingrese el nombre del hotel a eliminar:");
                                namef = s.nextLine();
                                ht.eliminarHotel(namef, reporteFinal);
                                break;
                        }
                        break;
                    case 6:
                        Reservas r = new Reservas();
                        System.out.println("1. Realizar reserva");
                        System.out.println("2. Modificar reserva");
                        System.out.println("3. Eliminar reserva");
                        System.out.println("4. Informe de reservas");
                        opcion2 = s.nextInt();
                        s.nextLine();
                        switch (opcion2) {
                            case 1:
                                r.reservar(s);
                                break;
                            case 2:
                                String namef;
                                System.out.println("Ingrese el nombre de cliente con el que hizo la reserva:");
                                namef = s.nextLine();
                                r.modificarReserva(namef, s);
                                break;
                            case 3:
                                System.out.println("Ingrese el nombre del cliente que realizo la reserva a eliminar:");
                                namef = s.nextLine();
                                r.cancelarReserva(namef);
                                break;
                            case 4:
                                Path path= Paths.get("");
                                String ruta = path.toAbsolutePath().toString();
                                File texto=new File(ruta+ File.separator + "InformeDeReservas.txt");
                                auxiliar.GenerarReporte(reporteFinal, r, texto);
                        }
                        break;
                    case 7:
                        System.out.println("Saliendo");
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }
            } while (opcion != 7);

        } catch (InputMismatchException a) {
            System.out.println("Elija una opcion dentro del menu");
        }
    }
}
