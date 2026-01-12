//Juan Guillermo Gomez Landinez-Samuel Andres Rey Marquez
// MARRAKECH
#include<iostream>
#include<ctime>
#include<fstream>
#include <locale.h>
#include<windows.h>

using namespace std;

struct jugador {
    string nombre;
    string color;
    int puntos;
    int monedas;
};

void funcion (char matriz[][7], int &x, int &y, string & posicion);
int pagar (int pago [][7], char alfombras[][7], char equipo,int x, int y);
void tapete (char matriz[][7], char alfombras[][7], int x, int y,char equipo);
void moneditas (int pago [][7], int cantidad, int& monedasA, int& monedasR, char equipo);
int fin_de_juego (char alfombras[][7], int monedasA, int monedasR);
int conteo(char alfombras[][7], char equipo);
string suma (int puntosR, int puntosA, int monedasR, int monedasA);
void registro (jugador player[], string equipo);
void establecer_color(char contenido);
int dado ();

int main ()
{
    setlocale(LC_ALL, "spanish");
    jugador player[2];
    char matriz[7][7], alfombras[7][7], equipo;
    int monedasR=20, monedasA=20, finalizar=49,cantidad, puntosR, puntosA, pago[7][7], a, x = 3, y = 3;
    string equipo_ganador,posicion, arriba = "arriba", abajo = "abajo", izquierda = "izquierda", derecha = "derecha";
    posicion = abajo;

    for(int i=0; i<7; i++)
    {
        for(int j=0; j<7; j++)
        {
            pago[i][j]=0;
        }
    }

    for (int i = 0; i < 7; i++)
    {
        for (int j = 0; j < 7; j++)
        {
            alfombras[i][j] = '-';
            matriz[i][j] = '-';
        }
    }

    matriz[x][y] = 'X';
    cout<<endl;
    cout<<endl;
    cout<<"***   ***  *********  *********  *********  *********  ***   ***  *********  *********  ***   ***   *  ***  ***  ***  ***  *   *  ***  ***  *   *"<<endl;
    cout<<"**** ****  *********  *********  *********  *********  ***   ***  *********  *********  ***   ***    **  ***  ***  ***  ***  *   *  ***  ***  *   *"<<endl;
    cout<<"*********  ***   ***  ***   ***  ***   ***  ***   ***  ***  ***   ***        ***        ***   ***  *   *  *   *  *   *  *   *  *  *   *        *        *   *"<<endl;
    cout<<"*** * ***  *********  *********  *********  *********  *** ***    *********  ***        ********* * *  ***  ***  ***  ***  * *    ***  *        ***"<<endl;
    cout<<"***   ***  ***   ***  ***  ***   ***  ***   ***   ***  *** ***    *********  ***        *********    *  *   *  *  *   *  *   *   *  * *    ***  *        ***"<<endl;
    cout<<"***   ***  ***   ***  ***  ***   ***  ***   ***   ***  ***  ***   ***        ***        ***   ***   *  *   *  *  *   *  *   *   *  *  *   *        *        *   *"<<endl;
    cout<<"***   ***  ***   ***  ***   ***  ***   ***  ***   ***  ***   ***  *********  *********  ***   ***   *  *   *  *   *  *   *  *   *  *   *  ***  ***  *   *"<<endl;
    cout<<"***   ***  ***   ***  ***   ***  ***   ***  ***   ***  ***   ***  *********  *********  ***   *** *  *   *  *   *  *   *  *   *  *   *  ***  ***  *   *"<<endl;
    cout<<endl;
    cout<<"                                         *     *     *                                            "<<endl;
    cout<<"                                           *   *   *                                              "<<endl;
    cout<<"                                             *****                                                "<<endl;
    cout<<"                                         *************                                            "<<endl;
    cout<<"                                             *****                                                "<<endl;
    cout<<"                                           *   *   *                                              "<<endl;
    cout<<"                                         *     *     *                                            "<<endl;
    cout<<endl;
    cout<<"                        *****     *****     *****     *****     *****                            "<<endl;
    cout<<"                      *       * *       * *       * *       * *       *                          "<<endl;
    cout<<"                     *         *         *         *         *         *                         "<<endl;
    cout<<"                    *           *         *         *         *         *                        "<<endl;
    cout<<endl;
    cout<<endl;
    cout<<endl;
    cout<<"                         B I E N V E N I D O   A   M A R R A K E C H                             "<<endl;
    cout<<endl;
    cout<<endl;
    cout<<"     ¡Bienvenidos a la plaza de Marrakech!"<<endl;
    cout<<endl;
    cout<<"     Hay un montón de vendedores con compitiendo por generar mucho dinero con sus alfombras"<<endl;
    cout<<endl;
    cout<<"     ¿Quieres ser el ganador? Expone mas alfombras que tu rival en la plaza y cobra el  uso"<<endl;
    cout<<endl;
    cout<<"     de tus alfombras                                                                      "<<endl;
    cout<<endl;
    cout<<endl;
    cout<<"                               R E G L A S   D E L   J U E G O :                           "<<endl;
    cout<<endl;
    cout<<endl;
    cout<<"     1.)Tu turno consta de 2 acciones: 1. Mover a Hassam 2. Colocar las alfombras          "<<endl;
    cout<<endl;
    cout<<endl;
    cout<<"     2.)Hassam no puede girar hacia atrás, por lo tanto el juego te indicará  la  dirección"<<endl;
    cout<<endl;
    cout<<"     hacia la que está mirando                                                             "<<endl;
    cout<<endl;
    cout<<endl;
    cout<<"     3.)Hassam estará señalizado por una 'x' dentro del tablero"<<endl;
    cout<<endl;
    cout<<endl;
    cout<<"     4.)El juego te preguntará solamente la direccion en la cual quieres realizar alguna de"<<endl;
    cout<<endl;
    cout<<"     las 2 acciones, debes responder la letra correspondiente                              "<<endl;
    cout<<endl;
    cout<<endl;
    cout<<"     5.)El juego solo recibe letras minusculas, ten cuidado al digitar                     "<<endl;
    cout<<endl;
    cout<<endl;
    cout<<"     6.)Deberas pagar una moneda  si pisas una alfombra  rival,y una   adicional  por  cada"<<endl;
    cout<<endl;
    cout<<"     alfombra rival que este junto a ella                                                  "<<endl;
    cout<<endl;
    cout<<endl;
    cout<<"     7.)Los controles del juego son:                                                       "<<endl;
    cout<<endl;
    cout<<"      a: Indicar la acción hacia arriba                                                    "<<endl;
    cout<<endl;
    cout<<"      b: Indicar la acción hacia abajo                                                     "<<endl;
    cout<<endl;
    cout<<"      c: Indicar la acción hacia la izquierda                                              "<<endl;
    cout<<endl;
    cout<<"      d: Indicar la acción hacia la derecha                                                "<<endl;
    cout<<endl;
    cout<<endl;
    cout<<"     8.)El juego termina cuando alguno de  los jugadores  se quede sin monedas o  cuando se"<<endl;
    cout<<endl;
    cout<<"     llene el Mercado de Marrakech"<<endl;
    cout<<endl;
    cout<<endl;
    cout<<"     Una vez hayas leído esto, ¡Estás listo! A JUGAR!!!"<<endl;
    cout<<endl;
    cout<<endl;
    cout<<"     Bienvenido a Marrakech"<<endl;
    cout<<endl;

    for (int i=0; i<2; i++)
    {
        cout<<"     Ingrese el nombre del jugador "<<i+1<<": ";
        cin>>player[i].nombre;
        cout<<endl;
    }

    cout<<"     "<<player[0].nombre<<" elige el color de tu equipo AZUL(a) o ROJO(r): ";
    cin>>equipo;
    cout<<endl;
    cout<<endl;

    if (equipo == 'r')
    {
        player[0].color = "rojo";
        player[1].color = "azul";
    }
    else if(equipo == 'a')
    {
        player[0].color = "azul";
        player[1].color = "rojo";
    }

    cout<<"     TABLERO INICIAL"<<endl;
    cout<<endl;

    for (int i = 0; i < 7; i++)
    {
        for (int j = 0; j < 7; j++)
        {
            cout<<"     "<<matriz[i][j] << " ";
        }
        cout << endl;
    }
    cout<<endl;

    while (finalizar > 0)
    {
        switch (equipo)
        {
        case 'r':
            cout<<"     TURNO DEL EQUIPO ROJO" << endl;
            cout<<endl;
            funcion (matriz, x, y, posicion);
            cantidad = pagar (pago, alfombras, equipo, x, y);
            moneditas(pago, cantidad, monedasA, monedasR, equipo);
            cout<<"     MONEDAS = El equipo rojo tiene "<<monedasR<<" monedas restantes, el equipo azul tiene "<<monedasA<<" monedas restantes"<<endl;
            cout<<endl;
            finalizar = fin_de_juego (alfombras, monedasA, monedasR);
            tapete (matriz, alfombras, x, y, equipo);
            finalizar = fin_de_juego (alfombras,monedasA, monedasR);
            equipo = 'a';
            break;
        case 'a':
            cout<<"     TURNO DEL EQUIPO AZUL" << endl;
            cout<<endl;
            funcion (matriz, x, y, posicion);
            cantidad = pagar (pago, alfombras, equipo, x, y);
            moneditas(pago, cantidad, monedasA, monedasR, equipo);
            cout<<"     MONEDAS = El equipo rojo tiene "<<monedasR<<" monedas restantes, el equipo azul tiene "<<monedasA<<" monedas restantes"<<endl;
            cout<<endl;
            finalizar = fin_de_juego (alfombras, monedasA, monedasR);
            tapete (matriz, alfombras, x, y, equipo);
            finalizar = fin_de_juego (alfombras, monedasA, monedasR);
            equipo = 'r';
            break;
        }
    }
    equipo = 'r';
    puntosR = conteo(alfombras, equipo);
    player[0].puntos=puntosR;
    player[0].monedas=monedasR;
    equipo = 'a';
    puntosA = conteo(alfombras, equipo);
    player[1].puntos=puntosA;
    player[1].monedas=monedasA;
    cout<<"     Es el fin del juego"<<endl;
    cout<<endl;
    equipo_ganador = suma (puntosR, puntosA, monedasR, monedasA);
    registro(player, equipo_ganador);
}

void funcion (char matriz[][7], int &x, int &y, string & posicion)
{
    char a1;
    int x1, y1, pos, mov;
    string arriba = "arriba", abajo = "abajo", izquierda = "izquierda", derecha = "derecha";
    if (posicion == "arriba")
    {
        cout<<"     POSICION = El jugador esta apuntando hacia arriba" << endl;
        cout<<endl;
        cout<<"     MOVIMIENTO = Ingrese la direccion para mover la X arriba (a), izquierda (c), derecha (d): ";
        do
        {
            cin >> a1;
            cout<<endl;
            if (a1 != 'a' && a1 != 'c' && a1 != 'd')
            {
                cout<<"     Letra invalida, ingrese nuevamente que direccion tomara: ";
            }
        }
        while (a1 != 'a' && a1 != 'c' && a1 != 'd');
    }
    else if (posicion == "abajo")
    {
        cout<<"     POSICION = El jugador esta apuntando hacia abajo" << endl;
        cout<<endl;
        cout<<"     MOVIMIENTO = Ingrese la direccion para mover la X abajo (b), izquierda (c), derecha (d): ";
        do
        {
            cin >> a1;
            cout<<endl;
            if (a1 != 'b' && a1 != 'c' && a1 != 'd')
            {
                cout<<"     Letra invalida, ingrese nuevamente que direccion tomara:";
            }
        }
        while (a1 != 'b' && a1 != 'c' && a1 != 'd');
    }
    else if (posicion == "izquierda")
    {
        cout<<"     POSICION = El jugador esta apuntando hacia la izquierda" << endl;
        cout<<endl;
        cout<<"     MOVIMIENTO = Ingrese la direcciC3n para mover la X arriba (a), abajo (b), izquierda (c): ";
        do
        {
            cin >> a1;
            cout<<endl;
            if (a1 != 'a' && a1 != 'b' && a1 != 'c')
            {
                cout<<"     Letra invalida, ingrese nuevamente que direccion tomara: ";
            }
        }
        while (a1 != 'a' && a1 != 'b' && a1 != 'c');
    }
    else if (posicion == "derecha")
    {
        cout<<"     POSICION = El jugador esta apuntando hacia la derecha" << endl;
        cout<<endl;
        cout<<"     MOVIMIENTO = Ingrese la direcciC3n para mover el 8 arriba (a), abajo (b), derecha (d): ";
        do
        {
            cin >> a1;
            cout<<endl;
            if (a1 != 'a' && a1 != 'b' && a1 != 'd')
            {
                cout<<"     Letra invalida, ingrese nuevamente que direccion tomara:" << endl;
            }
        }
        while (a1 != 'a' && a1 != 'b' && a1 != 'd');
    }
    mov = dado ();
    switch (a1)
    {
    case 'a':
        x1 = x;
        matriz[x][y] = '-';
        x = x - mov;
        if (x >= 0)
        {
            matriz[x][y] = 'X';
            posicion = arriba;
        }
        else if (x < 0 && y % 2 == 1 && y != 0)
        {
            y = y + 1;
            x = ((mov - x1) - 1);
            matriz[x][y] = 'X';
            posicion = abajo;
        }
        else if (x < 0 && y % 2 == 0 && y != 0)
        {
            y = y - 1;
            x = ((mov - x1) - 1);
            matriz[x][y] = 'X';
            posicion = abajo;
        }
        else if (x < 0 && y == 0)
        {
            y = ((mov - x1) - 1);
            x = 0;
            matriz[x][y] = 'X';
            posicion = derecha;
        }
        break;

    case 'b':
        x1 = x;
        matriz[x][y] = '-';
        x = x + mov;
        if (x <= 6)
        {
            matriz[x][y] = 'X';
            posicion = abajo;
        }
        else if (x >= 7 && y % 2 == 1 && y != 6)
        {
            y = y - 1;
            x = (7 - (mov - (7 - (x1 + 1))));
            matriz[x][y] = 'X';
            posicion = arriba;
        }
        else if (x >= 7 && y % 2 == 0 && y != 6)
        {
            y = y + 1;
            x = (7 - (mov - (7 - (x1 + 1))));
            matriz[x][y] = 'X';
            posicion = arriba;
        }
        else if (x >= 7 && y == 6)
        {
            y = (7 - (mov - (7 - (x1 + 1))));
            x = 6;
            matriz[x][y] = 'X';
            posicion = izquierda;
        }
        break;

    case 'c':
        y1 = y;
        matriz[x][y] = '-';
        y = y - mov;
        if (y >= 0)
        {
            matriz[x][y] = 'X';
            posicion = izquierda;
        }
        else if (y < 0 && x % 2 == 1 && x != 0)
        {
            x = x + 1;
            y = ((mov - y1) - 1);
            matriz[x][y] = 'X';
            posicion = derecha;
        }
        else if (y < 0 && x % 2 == 0 && x != 0)
        {
            x = x - 1;
            y = ((mov - y1) - 1);
            matriz[x][y] = 'X';
            posicion = derecha;
        }
        else if (y < 0 && x == 0)
        {
            x = ((mov - y1) - 1);
            y = 0;
            matriz[x][y] = 'X';
            posicion = abajo;
        }
        break;

    case 'd':
        y1 = y;
        matriz[x][y] = '-';
        y = y + mov;
        if (y <= 6)
        {
            matriz[x][y] = 'X';
            posicion = derecha;
        }
        else if (y >= 7 && x % 2 == 1 && x != 6)
        {
            x = x - 1;
            y = (7 - (mov - (7 - (y1 + 1))));
            matriz[x][y] = 'X';
            posicion = izquierda;
        }
        else if (y >= 7 && x % 2 == 0 && x != 6)
        {
            x = x + 1;
            y = (7 - (mov - (7 - (y1 + 1))));
            matriz[x][y] = 'X';
            posicion = izquierda;
        }
        else if (y >= 7 && x == 6)
        {
            x = (7 - (mov - (7 - (y1 + 1))));
            cout << y << endl;
            y = 6;
            matriz[x][y] = 'X';
            posicion = arriba;
        }
        break;
    }
}

int dado()
{
    int a, mov;
    cout<<"     Oprime el numero 1 para lanzar el dado: ";
    do
    {
        cin >> a;
        cout<<endl;
        if (a == 1)
        {
            srand (time (NULL));
            mov = rand () % 3 + 1;
            cout<<"     El numero del dado es: " << mov << endl;
            cout<<endl;
        }
        else if (a != 1)
        {
            cout<<"     Opcion invalida digitelo nuevamente: ";
        }
    }while (a != 1);
    return mov;
}
int pagar (int pago [][7], char alfombras[][7], char equipo, int x, int y)
{
    int contador=0;
    int desplazamiento_filas[] = {1, -1, 0, 0};
    int desplazamiento_columnas[] = {0, 0, -1, 1};
    int casillas_a_evaluar[98];
    int e = 0;
    casillas_a_evaluar[e] = x;
    e++;
    casillas_a_evaluar[e] = y;
    e++;

    while (e > 0)
    {
        e --;
        int columna_actual = casillas_a_evaluar[e];
        e--;
        int fila_actual= casillas_a_evaluar[e];

        if ((equipo == 'r' && alfombras[fila_actual][columna_actual] == 'A' && pago[fila_actual][columna_actual] == 0) ||
            (equipo == 'a' && alfombras[fila_actual][columna_actual] == 'R' && pago[fila_actual][columna_actual] == 0))
        {
            pago[fila_actual][columna_actual] = 1;
            for (int j = 0; j < 4; j++)
            {
                int nueva_fila = fila_actual + desplazamiento_filas[j];
                int nueva_columna = columna_actual + desplazamiento_columnas[j];
                if (nueva_fila >= 0 && nueva_fila < 7 && nueva_columna >= 0 && nueva_columna < 7 &&
                    pago[nueva_fila][nueva_columna] == 0 && ((equipo == 'r' && alfombras[nueva_fila][nueva_columna] == 'A') ||
                     (equipo == 'a' && alfombras[nueva_fila][nueva_columna] == 'R')))
                {
                    casillas_a_evaluar[e] = nueva_fila;
                    e ++;
                    casillas_a_evaluar[e] = nueva_columna;
                    e ++;
                }
            }
        }
    }
    for (int i=0; i<7; i++){
        for (int j=0; j<7; j++)
        {
            if (pago[i][j]==1)
            {
                contador++;
            }
        }
    }
    return contador;

}
void moneditas (int pago [][7], int cantidad, int& monedasA, int& monedasR, char equipo)
{
    for(int i=0; i<7; i++)
    {
        for(int j=0; j<7; j++)
        {
            if (pago[i][j]==1)
            {
                pago[i][j]=0;
            }
        }
    }

    switch (equipo)
    {
        case 'r':
        monedasA = monedasA + cantidad;
        monedasR = monedasR - cantidad;
        break;
        case 'a':
        monedasR = monedasR + cantidad;
        monedasA = monedasA - cantidad;
        break;
    }
}

void tapete (char matriz[][7], char alfombras[][7], int x, int y, char equipo)
{
    char color,contenido, alfombra1, alfombra2, recuperar_por_si_la_pone_mal, orig2, orig;
    recuperar_por_si_la_pone_mal = alfombras[x][y];
    int aux_x = x, aux_y = y;
    bool incorrecto=false, bandera;
    int aux_x_2, aux_y_2;
    do
    {
        if (equipo == 'r')
        {
            color = 'R';
        }
        else if (equipo == 'a')
        {
            color = 'A';
        }
        alfombras[x][y] = matriz[x][y];

        if (color=='R'){
        contenido='A';
}
         else if (color='A'){
         contenido='R';
}
    for (int i = 0; i < 7; i++) {
        for (int j = 0; j < 7; j++) {
            establecer_color(alfombras[i][j]);
            cout << "     "<< alfombras[i][j] << " ";
        }
        HANDLE hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
        SetConsoleTextAttribute(hConsole, FOREGROUND_RED | FOREGROUND_GREEN | FOREGROUND_BLUE); // Reset color
        cout << endl;
    }
        cout << endl;
        do
        {
            cout<<"     PARTE I DEL TAPETE = Ingrese en que casillas desea colocar el primer  fragmento de  la"<<endl;
            cout<<endl;
            cout<<"     alfombra: arriba (a), abajo (b), izquierda (c), derecha (d): ";
            cin >> alfombra1;
            cout<<endl;
            switch (alfombra1)
            {
            case 'a':
                x = x - 1;
                orig = alfombras[x][y];
                aux_x_2 = x;
                if (x >= 0)
                {
                    alfombras[x][y] = color;
                    cout<<"     PARTE II DEL TAPETE = Tiendo en cuenta  su  posicion  ahora en donde quiere colocar el"<<endl;
                    cout<<endl;
                    cout<<"     otro fragmento: arriba (a),izquierda(c), derecha(d): ";
                    cin>>alfombra2;
                    cout<<endl;
                }
                else
                {
                    cout<<"     Esta alfombra sobrepasa el limite del tablero, ingresela de nuevo"<<endl;
                    cout<<endl;
                    alfombras[x][y] = orig;
                    y = aux_y;
                    x = aux_x;
                    bandera=false;
                }
                break;
            case 'b':
                x = x + 1;
                aux_x_2 = x;
                orig = alfombras[x][y];
                if (x <= 6)
                {
                    alfombras[x][y] = color;
                    cout<<"     PARTE II DEL TAPETE = Tiendo en cuenta  su  posicion  ahora en donde quiere colocar el"<<endl;
                    cout<<endl;
                    cout<<"     otro fragmento: abajo (b),izquierda(c), derecha(d): ";
                    cin >> alfombra2;
                    cout<<endl;
                }
                else
                {
                    cout<<"     Esta alfombra sobrepasa el limite del tablero, ingresela de nuevo"<< endl;
                    cout<<endl;
                    alfombras[x][y] = orig;
                    y = aux_y;
                    x = aux_x;
                    bandera=false;
                }
                break;
            case 'c':
                y = y- 1;
                aux_y_2 = y;
                orig = alfombras[x][y];
                if (y >= 0)
                {
                    alfombras[x][y] = color;
                    cout<<"     PARTE II DEL TAPETE = Tiendo en cuenta  su  posicion  ahora en donde quiere colocar el"<<endl;
                    cout<<endl;
                    cout<<"     otro fragmento: arriba (a), abajo (b),izquierda(c): ";
                    cin >> alfombra2;
                    cout<<endl;
                }
                else
                {
                    cout<<"     Esta alfombra sobrepasa el limite del tablero, ingresela de nuevo"<<endl;
                    cout<<endl;
                    alfombras[x][y] = orig;
                    y = aux_y;
                    x = aux_x;
                    bandera=false;
                }
                break;
            case 'd':
                y = y + 1;
                aux_y_2 = y;
                orig = alfombras[x][y];
                if (y <= 6)
                {
                    alfombras[x][y] = color;
                    cout<<endl;
                    cout<<"     PARTE II DEL TAPETE = Tiendo en cuenta  su  posicion  ahora en donde quiere colocar el"<<endl;
                    cout<<endl;
                    cout<<"     otro fragmento: arriba (a), abajo (b),derecha(d): ";
                    cin >> alfombra2;
                    cout<<endl;
                }
                else
                {
                    cout<<"     Esta alfombra sobrepasa el limite del tablero, ingresela de nuevo"<<endl;
                    cout<<endl;
                    alfombras[x][y] = orig;
                    y = aux_y;
                    x = aux_x;
                    bandera=false;
                }
                break;
            }
            if (alfombra2 == 'a')
            {
                x = x - 1;
                orig2 = alfombras[x][y];
                if (x >= 0)
                {
                    if (alfombras[x][y]!= '-' && (orig == alfombras[x][y]))
                    {
                        cout<<"     No es posible colocar un tapete sobre otro, reingrese las casillas del tapete"<<endl;
                        cout<<endl;
                        alfombras[aux_x_2][aux_y] = orig;
                        x = aux_x;
                        y = aux_y;
                        incorrecto=true;
                    }
                    else
                    {
                        incorrecto = false;
                        alfombras[x][y] = color;

                        cout<<endl;
                        bandera=true;
                    }
                }
                else
                {
                    cout<<"     Esta alfombra sobrepasa el limite del tablero, ingresela de nuevo"<<endl;
                    cout<<endl;
                    alfombras[x + 1][y] = orig;
                    alfombras[x][y] = orig2;
                    y = aux_y;
                    x = aux_x;
                    bandera=false;
                }
            }
            else if (alfombra2 == 'b')
            {
                x = x + 1;
                orig2 = alfombras[x][y];
                if (x <= 6)
                {

                    if (alfombras[x][y]!= '-' && (orig == alfombras[x][y]))
                    {
                        cout<<"     No es posible colocar un tapete sobre otro, reingrese las casillas del tapete"<<endl;
                        cout<<endl;
                        alfombras[aux_x_2][aux_y] = orig;
                        x = aux_x;
                        y = aux_y;
                        incorrecto = true;
                    }
                    else
                    {
                        incorrecto = false;
                        alfombras[x][y] = color;
                        for (int i = 0; i < 7; i++)
                        cout<<endl;
                        bandera=true;
                    }
                 }
                 else
                 {
                     cout<<"     Esta alfombra sobrepasa el limite del tablero, ingresela de nuevo"<< endl;
                     alfombras[x - 1][y] = orig;
                     alfombras[x][y] = orig2;
                     y = aux_y;
                     x = aux_x;
                     bandera=false;
                 }

            }
            else if (alfombra2 == 'c')
            {
                y = y - 1;
                orig2 = alfombras[x][y];
                if (y >= 0)
                {
                    if (alfombras[x][y]!= '-' && (orig == alfombras[x][y])){
                    cout<<"     No es posible colocar un tapete sobre otro, reingrese las casillas del tapete"<<endl;
                    alfombras[aux_x][aux_y_2] = orig;
                    x = aux_x;
                    y = aux_y;
                    cout<<incorrecto<<endl;
                    incorrecto = true;
                }
                else {
                    incorrecto = false;
                    alfombras[x][y] = color;
                    bandera=true;
                }
                }
                else
                {
                    cout<<"     Esta alfombra sobrepasa el limite del tablero, ingresela de nuevo"<<endl;
                    cout<<endl;
                    alfombras[x][y + 1] = orig;
                    alfombras[x][y] = orig2;
                    y = aux_y;
                    x = aux_x;
                    bandera=false;
                }
            }
            else if (alfombra2 == 'd')
            {
                y = y + 1;
                orig2 = alfombras[x][y];
                if (y <= 6)
                {
                    if (alfombras[x][y]!= '-' && (orig == alfombras[x][y])){
                    cout<<"     No es posible colocar un tapete sobre otro, reingrese las casillas del tapete"<<endl;
                    cout<<endl;
                    alfombras[aux_x][aux_y_2] = orig;
                    x = aux_x;
                    y = aux_y;
                    incorrecto = true;
                }
                else {
                    incorrecto = false;
                    alfombras[x][y] = color;
                    bandera=true;
                }
                }
                else
                {
                    cout<<"     Esta alfombra sobrepasa el limite del tablero, ingresela de nuevo"<<endl;
                    cout<<endl;
                    alfombras[x][y - 1] = orig;
                    alfombras[x][y] = orig2;
                    y = aux_y;
                    x = aux_x;
                    bandera=false;
                }
            }
        }while(bandera==false);
        if (color=='r'){
        contenido='A';
}
         else if (color='a'){
         contenido='R';
}
    for (int i = 0; i < 7; i++) {
        for (int j = 0; j < 7; j++) {
            establecer_color(alfombras[i][j]);
            cout << "     "<< alfombras[i][j] << " ";
        }
        HANDLE hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
        SetConsoleTextAttribute(hConsole, FOREGROUND_RED | FOREGROUND_GREEN | FOREGROUND_BLUE); // Reset color
        cout << endl;
    }
        for(int i=0; i<7;i++)
        {
            for(int j=0; j<7; j++)
            {
                if(alfombras[i][j]=='X')
                {
                    alfombras[i][j]=recuperar_por_si_la_pone_mal;
                }
            }
        }

    }while(incorrecto);
}


int fin_de_juego (char alfombras[][7], int monedasA, int monedasR)
{
    int contador_vacios=0;
    if (monedasA<=0 || monedasR<=0){
        return 0;
    }
    else {
    for (int i=0; i<7;i++)
    {
        for (int j=0; j<7; j++)
        {
            if (alfombras[i][j]=='-')
            {
                contador_vacios++;
            }
        }
    }

    return contador_vacios;
}
}

int conteo(char alfombras[][7], char equipo)
{
    int contador = 0;
    if (equipo=='r')
    {
        for (int i=0; i<7; i++)
        {
            for (int j=0; j<7; j++)
            {
                if (alfombras[i][j]=='R')
                {
                    contador++;
                }
            }
        }
    }
    if (equipo=='a')
    {
        for (int i=0; i<7; i++)
        {
            for (int j=0; j<7; j++)
            {
                if (alfombras[i][j]=='A')
                {
                    contador++;
                }
            }
        }
    }
    return contador;
}
string suma(int puntosR, int puntosA, int monedasR, int monedasA)
{
    int sumaR = puntosR + monedasR;
    cout<<sumaR<<endl;
    int sumaA = puntosA + monedasA;
    cout<<sumaA<<endl;
    string a;
    if (sumaR>sumaA)
    {
        a="rojo";
    }
    if (sumaA>sumaR)
    {
        a= "azul";
    }
    if (sumaA == sumaR)
    {
        a= "empate";
    }
    return a;
}
void registro (jugador p[], string equipo)
{
    cout<<"     "<<equipo<<endl;
    int i=0;
    ofstream a ("partida.txt");
    ifstream d ("partida.txt");
    if (a.is_open())
    {
        a<<"     Nombre: "<<p[i].nombre<<"  Nombre: "<<p[i+1].nombre<<""<<endl;;
        a<<"     color: "<<p[i].color<<"  color: "<<p[i+1].color<<""<<endl;
        a<<"     puntos: "<<p[i].puntos<<"  puntos: "<<p[i+1].puntos<<""<<endl;
        a<<"     monedas: "<<p[i].monedas<<"  monedas: "<<p[i+1].monedas<<""<<endl;
        if (equipo == p[i].color)
        {
            a<<"     El ganador es "<<p[i].nombre<<""<<endl;
        }
        if (equipo == p[i+1].color)
        {
            a<<"     El ganador es "<<p[i+1].nombre<<""<<endl;
    }
        d.close();
        a.close();
        cout<<"     Reporte final de la partida generado, entra a la biblioteca de archivos, y en la carpeta donde está el juego, busca el documento de texto con el nombre PARTIDA"<<endl;
}
}
void establecer_color(char contenido) {
    HANDLE hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
    switch (contenido) {
        case 'A':
            SetConsoleTextAttribute(hConsole, FOREGROUND_BLUE | FOREGROUND_INTENSITY);
            break;
        case 'R':
            SetConsoleTextAttribute(hConsole, FOREGROUND_RED | FOREGROUND_INTENSITY);
            break;
        case 'X':
            SetConsoleTextAttribute(hConsole, FOREGROUND_GREEN | FOREGROUND_RED | FOREGROUND_INTENSITY);
            break;
        default:
            SetConsoleTextAttribute(hConsole, FOREGROUND_RED | FOREGROUND_GREEN | FOREGROUND_BLUE);
            break;
    }
}

