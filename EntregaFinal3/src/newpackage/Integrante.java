/* 
Integrantes:Samuel Rey - Juan Motta
Grupo: 3
Proyecto 2 - java
Fecha: 10/11/2024
 */
package newpackage;

import java.io.Serializable;

public class Integrante implements Serializable {

    protected int codigo;
    protected String nombre;

    public Integrante() {
    }

    public Integrante(int codigo) {
        this.codigo = codigo;
    }

    public Integrante(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

}
