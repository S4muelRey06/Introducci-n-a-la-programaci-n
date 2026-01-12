/* 
Integrantes:Samuel Rey - Juan Motta
Grupo: 3
Proyecto 2 - java
Fecha: 10/11/2024
 */
package newpackage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 *
 * @author aulasingenieria
 */
public class SinHeader extends ObjectOutputStream {

    public SinHeader(OutputStream out) throws IOException {
        super(out);
    }

    protected void writeStreamHeader() throws IOException {

    }
}
