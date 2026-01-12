/* 
Integrantes:Samuel Rey - Juan Motta
Grupo: 3
Proyecto 2 - java
Fecha: 10/11/2024
 */
 /*
 Esta clase fue creada con el fin de hacer mas eficiente el manejo de los archivos, tanto lectura, como escritura y es utilizada en todas las demás
 */
package newpackage;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 57316
 */
public class Auxiliar {

    FileOutputStream bin = null;
    ObjectOutputStream bina = null;
    FileInputStream l = null;
    ObjectInputStream lee = null;
    FileWriter fw = null;
    BufferedWriter bf = null;
    File last = null;

    public File inicial(Object a) {
        Path path = Paths.get("");
        String ruta = path.toAbsolutePath().toString();
        File directorio = new File(ruta + File.separator + "Archivos de registro auxiliares");
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Listo");
            } else {
                System.out.println("Error al crear carpeta");
            }
        } else {
        }
        if (a instanceof Cliente) {
            last = new File(directorio.getAbsolutePath() + File.separator + "Clientes.dat");

        } else if (a instanceof PlanTuristico) {
            last = new File(directorio.getAbsolutePath() + File.separator + "PlanTuristico.dat");
        } else if (a instanceof Vuelo) {
            last = new File(directorio.getAbsolutePath() + File.separator + "Vuelo.dat");
        } else if (a instanceof Aerolinea) {
            last = new File(directorio.getAbsolutePath() + File.separator + "Aerolinea.dat");
        } else if (a instanceof Hotel) {
            last = new File(directorio.getAbsolutePath() + File.separator + "Hotel.dat");
        } else if (a instanceof Reservas) {
            last = new File(directorio.getAbsolutePath() + File.separator + "Reservas.dat");
        }

        return last;
    }

    public char indicar(Object a) {
        char aux = 0;
        if (a instanceof Cliente) {
            aux = 'c';
        } else if (a instanceof PlanTuristico) {
            aux = 'p';
        } else if (a instanceof Vuelo) {
            aux = 'v';
        } else if (a instanceof Aerolinea) {
            aux = 'a';
        } else if (a instanceof Hotel) {
            aux = 'h';
        } else if (a instanceof Reservas) {
            aux = 'r';
        }
        return aux;
    }

    public void escribir(Object a) {
        File last = inicial(a);
        boolean lleno = last.exists() && last.length() > 0;
        try {
            bin = new FileOutputStream(last.getAbsolutePath(), true);
            if (!lleno) {
                bina = new ObjectOutputStream(bin);
            } else {
                bina = new SinHeader(bin);
            }
            bina.writeObject(a);
            bina.close();
            bin.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Object buscar(String criterio, Object a) {
        boolean found = false;
        File last = inicial(a);
        char ind = indicar(a);
        try {
            l = new FileInputStream(last);
            lee = new ObjectInputStream(l);
            boolean endOfFile = false;

            do {
                try {
                    switch (ind) {
                        case 'c':
                            Cliente cl = (Cliente) lee.readObject();
                            if (criterio.contains(cl.getNombre())) {
                                System.out.println("Cliente encontrado");
                                System.out.println(cl);
                                return cl;
                            }
                            break;

                        case 'p':
                            PlanTuristico pl = (PlanTuristico) lee.readObject();
                            if (criterio.equals(pl.getNombre())) {
                                System.out.println("Plan turístico encontrado");
                                System.out.println(pl);
                                return pl;
                            }
                            break;
                        case 'v':
                            Vuelo v = (Vuelo) lee.readObject();
                            if (criterio.equals(String.valueOf(v.getCodigo()))) {
                                System.out.println("Vuelo encontrado");
                                System.out.println(v);
                                return v;
                            }
                            break;
                        case 'a':
                            Aerolinea av = (Aerolinea) lee.readObject();
                            if (criterio.equals(av.getNombre())) {
                                System.out.println("Aerolinea encontrada");
                                System.out.println(av);
                                return av;
                            }
                            break;
                        case 'h':
                            Hotel h = (Hotel) lee.readObject();
                            if (criterio.equals(h.getNombre())) {
                                System.out.println("Hotel encontrado");
                                System.out.println(h);
                                return h;
                            }
                            break;
                        case 'r':
                            Reservas re = (Reservas) lee.readObject();
                            if (criterio.equals(re.getC().getNombre())) {
                                return re;
                            }
                            break;

                    }

                } catch (EOFException e) {
                    lee = null;  // 
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                    lee = null;
                } catch (IOException e) {
                    e.printStackTrace();
                    lee = null;
                }
            } while (lee != null);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (lee != null) {
                    lee.close();
                }
                if (l != null) {
                    l.close();
                }
            } catch (IOException exc) {
                System.out.println("Error al cerrar");
            }

        }
        return null;
    }

    void eliminar(Object a, String criterio) {
        File last = inicial(a);
        char ind = indicar(a);
        ArrayList<Object> items = new ArrayList<>();

        try {
            FileInputStream l = new FileInputStream(last);
            ObjectInputStream lee = new ObjectInputStream(l);

            boolean found = false;

            do {
                try {
                    switch (ind) {
                        case 'c':
                            Cliente cl = (Cliente) lee.readObject();
                            if (criterio.equals(cl.getNombre())) {
                                System.out.println("Cliente eliminado");
                                found = true;
                            } else {
                                items.add(cl);
                            }
                            break;
                        case 'p':
                            PlanTuristico pl = (PlanTuristico) lee.readObject();
                            if (criterio.equals(pl.getNombre())) {
                                System.out.println("Plan turístico eliminado");
                                found = true;
                            } else {
                                items.add(pl);
                            }
                            break;
                        case 'v':
                            Vuelo f = (Vuelo) lee.readObject();
                            if (String.valueOf(f.getCodigo()).equals(criterio)) {
                                System.out.println("Vuelo eliminado");
                                found = true;
                            } else {
                                items.add(f);
                            }

                            break;
                        case 'a':
                            Aerolinea av = (Aerolinea) lee.readObject();
                            if (criterio.equals(av.getNombre())) {
                                System.out.println("Aerolinea eliminada");
                                found = true;
                            } else {
                                items.add(av);
                            }
                            break;
                        case 'h':
                            Hotel h = (Hotel) lee.readObject();
                            if (criterio.equals(h.getNombre())) {
                                System.out.println("Hotel eliminado");
                                found = true;
                            } else {
                                items.add(h);
                            }
                            break;
                        case 'r':
                            Reservas re = (Reservas) lee.readObject();
                            if (criterio.equals(re.getC().getNombre())) {
                                System.out.println("Reserva cancelada");
                                found = true;
                            } else {
                                items.add(re);
                            }
                            break;

                    }
                } catch (EOFException e) {
                    lee = null;
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                    lee = null;
                }
            } while (lee != null);
            if (lee != null) {
                lee.close();
                l.close();
            }

            if (found) {
                last.delete();

                FileOutputStream outFile = new FileOutputStream(last);
                ObjectOutputStream out = new ObjectOutputStream(outFile);

                for (Object item : items) {
                    out.writeObject(item);
                }

                out.close();
                outFile.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Vuelo> RevisarVuelos(Object a) {
        File last = inicial(a);
        ArrayList<Vuelo> all = new ArrayList();
        try {
            FileInputStream l = new FileInputStream(last);
            ObjectInputStream lee = new ObjectInputStream(l);

            do {
                try {

                    Vuelo fl = (Vuelo) lee.readObject();
                    all.add(fl);

                } catch (EOFException e) {
                    lee = null;  // 
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                    lee = null;
                } catch (IOException e) {
                    e.printStackTrace();
                    lee = null;
                }
            } while (lee != null);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (lee != null) {
                    lee.close();
                }
                if (l != null) {
                    l.close();
                }
            } catch (IOException exc) {
                System.out.println("Error al cerrar");
            }

        }
        return all;
    }

    public ArrayList<Reservas> RevisarReservas(Reservas a) {
        File last = inicial(a);
        ArrayList<Reservas> all = new ArrayList();
        if (!last.exists()) {
            return all;
        }
        try {
            FileInputStream l = new FileInputStream(last);
            ObjectInputStream lee = new ObjectInputStream(l);

            do {
                try {

                    Reservas res = (Reservas) lee.readObject();
                    all.add(res);

                } catch (EOFException e) {
                    lee = null;  // 
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                    lee = null;
                } catch (IOException e) {
                    e.printStackTrace();
                    lee = null;
                }
            } while (lee != null);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (lee != null) {
                    lee.close();
                }
                if (l != null) {
                    l.close();
                }
            } catch (IOException exc) {
                System.out.println("Error al cerrar");
            }

        }
        return all;
    }

    void actualizarContenido(String criterio, Object a) {
        File last = inicial(a);
        char ind = indicar(a);
        ArrayList<Object> items = new ArrayList<>();

        try {
            FileInputStream l = new FileInputStream(last);
            ObjectInputStream lee = new ObjectInputStream(l);

            boolean found = false;

            do {
                try {
                    switch (ind) {
                        case 'r':
                            Reservas rse = new Reservas();
                            rse = (Reservas) lee.readObject();
                            if (criterio.equals(rse.getC().getNombre())) {
                                found = true;
                            } else {
                                items.add(rse);
                            }
                            break;
                        case 'c':
                            Cliente cl = new Cliente();
                            cl = (Cliente) lee.readObject();
                            if (criterio.equals(cl.getNombre())) {
                                found = true;
                            } else {
                                items.add(cl);
                            }
                            break;
                        case 'p':
                            PlanTuristico pl = new PlanTuristico();
                            pl = (PlanTuristico) lee.readObject();
                            if (criterio.equals(pl.getNombre())) {
                                found = true;
                            } else {
                                items.add(pl);
                            }
                            break;
                        case 'v':
                            Vuelo v = new Vuelo();
                            v = (Vuelo) lee.readObject();
                            if (criterio.equals(String.valueOf(v.getCodigo()))) {
                                found = true;
                            } else {
                                items.add(v);
                            }
                            break;
                        case 'a':
                            Aerolinea ae = new Aerolinea();
                            ae = (Aerolinea) lee.readObject();
                            if (criterio.equals(ae.getNombre())) {
                                found = true;
                            } else {
                                items.add(ae);
                            }
                            break;
                        case 'h':
                            Hotel h = new Hotel();
                            h = (Hotel) lee.readObject();
                            if (criterio.equals(h.getNombre())) {
                                found = true;
                            } else {
                                items.add(h);
                            }
                            break;
                    }
                } catch (EOFException e) {
                    lee = null;
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                    lee = null;
                }
            } while (lee != null);
            if (lee != null) {
                lee.close();
                l.close();
            }
            items.add(a);

            if (found) {
                last.delete();

                FileOutputStream outFile = new FileOutputStream(last);
                ObjectOutputStream out = new ObjectOutputStream(outFile);

                for (Object item : items) {
                    out.writeObject(item);
                }

                out.close();
                outFile.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void GenerarReporte(ArrayList<Reservas> a, Reservas r, File l) {
        SimpleDateFormat mostrar = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        FileWriter fw = null;
        BufferedWriter bf = null;

        try {
            fw = new FileWriter(l);
            bf = new BufferedWriter(fw);
            for (Reservas i : a) {
                bf.write("Cliente: " + i.getNombreCliente() + " ,Plan turistico:" + i.getNombrePlan() + " ,Fecha de inicio del plan " + i.getFechaInicioPlan(mostrar) + " ,Fecha finalizacion del plan " + i.getFechaSalidaPlan(mostrar) + " ,Codigo de vuelo: "
                        + i.getCodigoVuelo() + " ,Lugar de origen del vuelo: " + i.getOrigenVuelo() + " ,Lugar de destino del vuelo:  "
                        + i.getDestinoVuelo() + " ,Precio del vuelo: " + i.getPrecioVuelo() + " ,Hotel: "
                        + i.getNombreHotel() + " ,Ubicacion del hotel: " + i.getUbicacionHotel() + "\n");
            }
        } catch (IOException e) {
        } finally {
            try {
                if (bf != null) {
                    bf.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
            }
        }
    }
}
