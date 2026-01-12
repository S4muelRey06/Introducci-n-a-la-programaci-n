/*
Integrantes: Samuel Rey, Juan Motta
Grupo: 3
Fecha:2024/09/14
Proyecto 1
*/

#include <iostream>
#include <cstring>
#include <fstream>
#include <ctime>
#include <cstdlib>

using namespace std;

struct user
{
    char TId[3];
    char NId[11];
    char nombres[20];
    char apellidos[20];
    char ciudad[10];
};
struct account
{
    char NId[11];
    char NCuenta[6];
    char TdeCuenta[10];
    long SaldoApertura;
    long SaldoActual;
};
struct movement
{
    int consecutivo;
    char NId[11];
    char TdeMovimiento[2];
    long valor;
};
//para usuario
void RegUser();
void adicionarUser(user regis);
//para cuenta
void RegAccount();
void AdicionarCuenta(account cuenta);
//Para consulta general, historial de movimientos y cuenta
void Consultas(int funcion);
bool buscar(char num[]);
char* buscarNdeCuenta();
user leerUser(char num[]);
account leerAccount(char num[]);
char* nombre(char *nombres, char *apellidos);
//Para movimientos
void Regmovimientos();
void AdicionarMovimiento (movement mov);
int consecutivo(char num[]);
void ActualizarCuenta(char num[], int saldo);

int main()
{
    srand(static_cast<unsigned int>(time(0)));
    short opcion;
    do
    {
        cout << "--------------------------------------------------------------------------------" << endl;
        cout << "                       Bienvenido al banco XXXXXXX                              " << endl;
        cout << "Seleccione la operacion que desea realizar:" << endl;
        cout << "1. Crear un usuario de cliente" << endl;
        cout << "2. Crear una cuenta con su usuario de cliente" << endl;
        cout << "3. Consultar informacion de su cuenta" << endl;
        cout << "4. Realizar un movimiento" << endl;
        cout << "5. Consultar el historial de movimientos" << endl;
        cout << "6. Realizar una consulta general de su cuenta y movimientos" << endl;
        cout << "7. Salir" << endl;
        cin >> opcion;
        fflush(stdin);

        switch (opcion)
        {
        case 1:
            RegUser();
            break;
        case 2:
            RegAccount();
            break;
        case 3:
            cout << "Bienvenido a la consulta de cuenta, seleccione alguna de las siguientes opciones" << endl;
            Consultas(3);
            break;
        case 4:
            Regmovimientos();
            break;
        case 5:
            cout<< "Bienvenido a la consulta de historial de transacciones"<<endl;
            Consultas(5);
            break;
        case 6:
            cout<<"Bienvenido, en esta opcion se dara un reporte general"<<endl;
            Consultas(6);
            break;
        case 7:
            cout << "Cerrando" << endl;
            break;
        default:
            cout<<"Opcion invalida"<<endl;
        }
    }
    while (opcion != 7);

    return 0;
}

void RegUser()
{
    user regis;
    cout << "Bienvenido a la creacion de usuario, por favor diligencie los siguientes datos" << endl;

    bool apropiado;
    do
    {
        apropiado = true;
        cout << "Tipo de documento: (CC, TI, CE) " << endl;
        cin.getline(regis.TId, 10);
        strupr(regis.TId);
        if (strcmp(regis.TId, "TI") != 0 && strcmp(regis.TId, "CC") != 0 && strcmp(regis.TId, "CE") != 0)
        {
            apropiado = false;
            cout << "Documento invalido" << endl;
        }
    }
    while (!apropiado);

    cout << "Numero de identificacion" << endl;
    cin.getline(regis.NId, 11);
    bool existe = buscar(regis.NId);
    if (existe)
    {
        cout << "Ya existe un usuario registrado con ese documento, vuelva al menu principal" << endl;
    }
    else
    {
        cout << "Nombres" << endl;
        cin.getline(regis.nombres, 20);
        cout << "Apellidos" << endl;
        cin.getline(regis.apellidos, 20);
        cout << "Ciudad de residencia" << endl;
        cin.getline(regis.ciudad, 10);

        bool completo = true;
        if (strcmp(regis.NId, "") == 0 || strcmp(regis.TId, "") == 0 || strcmp(regis.nombres, "") == 0 || strcmp(regis.apellidos, "") == 0 || strcmp(regis.ciudad, "") == 0)
        {
            completo = false;
        }

        if (completo)
        {
            cout << "Informacion correcta, guardando..." << endl;
            adicionarUser(regis);
        }
        else
        {
            cout << "Usted NO completo la informacion, vuelva al menu principal y reintentelo" << endl;
        }
    }
    system("pause");
    system ("cls");
}

void RegAccount()
{

    account cuenta;
    char num[11];
    int verificacion;
    int n;
    cout<<"Recuerde que para crear una cuenta, debe haberse registrado como cliente previamente"<<endl;
    cout<<"Por favor ingrese su numero de identificacion"<<endl;
    fflush(stdin);
    cin.getline(num, 11);
    bool user = buscar(num);
    if (user)
    {
        cout<<"Bienvenido estimado cliente"<<endl;
        strcpy(cuenta.NId, num);
        cout<<"Siga las instrucciones para crear su cuenta"<<endl;
        cout<<"Indique el tipo de cuenta que desea crear (Ahorros o Corriente)"<<endl;
        cin.getline(cuenta.TdeCuenta, 10);
        strupr(cuenta.TdeCuenta);
        if (strcmp(cuenta.TdeCuenta, "AHORROS")!=0 && strcmp(cuenta.TdeCuenta, "CORRIENTE")!=0)
        {
            cout<<"No existe una cuenta de ese tipo, vuelva al menu principal"<<endl;
        }
        else
        {
            cout<<"A continuacion se generara un numero de cuenta unico, el cual sera util cuando desee consultar sobre la cuenta"<<endl;
            do
            {
                cout<<"Por favor reescriba el numero, para confirmar su validez"<<endl;
                n=10000 + rand() % 99999;
                cout<<n<<endl;
                fflush(stdin);
                cin.getline(cuenta.NCuenta, 6);
                verificacion= atoi(cuenta.NCuenta);
                if (verificacion!=n)
                {
                    cout<<"Los numeros no coinciden"<<endl;
                }
            }
            while (verificacion!=n);
            fflush(stdin);
            do
            {
                cout<<"Ingrese la cantidad con la que desea abrir la cuenta (Recuerde que debe ser superior a $50.000 pesos)"<<endl;
                fflush(stdin);
                cin>>cuenta.SaldoApertura;
                if(cuenta.SaldoApertura<50000)
                {
                    cout<<"Esta cantidad es menor a la minima permitida"<<endl;
                }
            }
            while(cuenta.SaldoApertura<50000);
            cuenta.SaldoActual=cuenta.SaldoApertura;
            AdicionarCuenta(cuenta);
            fflush(stdin);
            cout<<"Cuenta creada exitosamente"<<endl;
            cout<<"La cuenta fue creada con un saldo de: "<<cuenta.SaldoActual<<endl;
        }
    }
    else
    {
        cout<<"Su documento no fue encontrado en el registro de clientes, vuelva al menu principal y cree una cuenta de cliente"<<endl;
    }
    system("pause");
    system ("cls");
}
void ActualizarCuenta(char num[], account actualizacion)
{
    account aux;
    int conteo, pos=0;
    fstream check("cuenta.dat", ios::in |ios::out | ios::binary );
    check.seekg(0);
    while (check.read((char*)&aux,sizeof(aux)))
    {
        pos=check.tellg();
        if ((strcmp(aux.NId,num )==0))
        {
            conteo = pos - sizeof(aux);
            break;
        }
    }
    check.seekp(conteo);
    check.write((char*)&actualizacion, sizeof(actualizacion));

    check.close();
}

void Regmovimientos()
{
    user a;
    account b;
    movement accion;
    int dinero;
    char *name;
    char *Ncuenta;
    bool esta=false;
    Ncuenta = buscarNdeCuenta();
    if (Ncuenta==nullptr)
    {
        cout<<"No se ha encontrado la cuenta"<<endl;
    }
    else
    {
        esta = buscar(Ncuenta);
        if (esta)
        {
            a=leerUser(Ncuenta);
            b=leerAccount(Ncuenta);
            accion.consecutivo = (consecutivo(a.NId)+1);
            strcpy(accion.NId, a.NId);
            cout<<"Escriba (R) para hacer un retiro o (C) para hacer una consignacion"<<endl;
            cin>>accion.TdeMovimiento;
            strupr(accion.TdeMovimiento);
            do
            {
                cout<<"Escriba el monto de la operacion"<<endl;
                cin>>accion.valor;
                dinero = b.SaldoActual;

                fflush(stdin);
                if (strcmp("C", accion.TdeMovimiento)==0)
                {
                    dinero= b.SaldoActual+accion.valor;
                }

                else if (strcmp("R", accion.TdeMovimiento)==0)
                {
                    cout<<"Recuerde que su cuenta no puede quedar vacia"<<endl;
                    dinero= b.SaldoActual-accion.valor;
                }
            }
            while (dinero<=0);
            b.SaldoActual=dinero;
            AdicionarMovimiento(accion);
            ActualizarCuenta(b.NId, b);
            name = nombre(a.nombres, a.apellidos);
            system("cls");
            cout << "Nombre del cliente: " <<name<< endl;
            cout << "Cuenta: " << b.NCuenta << endl;
            cout << "Tipo de movimiento: " << accion.TdeMovimiento <<"  (C: Consignacion  -  R: Retiro)"<<endl;
            cout <<"Valor de la transaccion: "<<accion.valor<<endl;
            cout << "Saldo actualizado: " << b.SaldoActual << endl;

        }
        else
        {
            cout<<"La cuenta no existe vuelva al menu principal y verifique los datos"<<endl;
        }

        delete[]Ncuenta;
    }
    system("pause");
    system ("cls");
}

bool buscar(char num[])
{
    int tam;
    int pos=0;
    bool esta=false;
    ifstream leeruser ("usuarios.dat", ios::in | ios::binary);
    user registrado;
    leeruser.seekg(0);
    while (leeruser.read((char*)&registrado, sizeof(registrado)))
    {
        if (strcmp(registrado.NId, num) == 0)
        {
            leeruser.close();
            esta=true;
        }
    }
    leeruser.close();
    return esta;
}
char* buscarNdeCuenta()
{
    char Ndecuenta[6];
    account registrado;
    cout<<"Introduzca su numero de cuenta"<<endl;
    cin.getline(Ndecuenta,6);
    char* Nid = NULL;
    ifstream leerCuenta("cuenta.dat", ios::in | ios::binary);
    if (!leerCuenta.is_open())
    {
        cerr << "Error al abrir el archivo" << endl;
        return nullptr;
    }
    leerCuenta.seekg(0);
    while (leerCuenta.read((char*)&registrado, sizeof(registrado)))
    {
        if (strcmp(registrado.NCuenta, Ndecuenta) == 0)
        {
            Nid = new char[strlen(registrado.NId) + 1];
            strcpy(Nid, registrado.NId);
            leerCuenta.close();
            return Nid;
        }
    }

    leerCuenta.close();
    return nullptr;
}

void Consultas(int funcion)
{
    short id;
    bool esta = false;
    bool correcto = true;
    user a;
    account b;
    movement aux;
    char *name;
    ifstream mov("movimientos.dat", ios::in | ios::binary);
    cout << "1. Numero de identificacion" << endl;
    cout << "2. Numero de cuenta" << endl;
    cin >> id;
    fflush(stdin);
    switch (id)
    {
    case 1:
        char NdeConsulta[11];
        cout << "Introduzca su numero de identificacion" << endl;
        cin.getline(NdeConsulta, 11);
        esta = buscar(NdeConsulta);
        if (esta)
        {
            a = leerUser(NdeConsulta);
            b = leerAccount(NdeConsulta);
        }
        else
        {
            correcto=false;
        }
        break;
    case 2:
        char *Ncuenta;
        Ncuenta = buscarNdeCuenta();
        if (Ncuenta==nullptr)
        {
            correcto=false;
        }
        else
        {
            esta = buscar(Ncuenta);
            if (esta)
            {
                a=leerUser(Ncuenta);
                b=leerAccount(Ncuenta);
            }
            else
            {

                correcto=false;
            }
            delete[]Ncuenta;
        }
        break;
    }

    if (correcto)
    {
        name=nombre(a.nombres, a.apellidos);
        switch(funcion)
        {
        case 3:
            system("cls");
            cout<<"Nombre del cliente: "<<name<<endl;
            cout<<"Cuenta: " <<b.NCuenta<<endl;
            cout<<"Tipo de cuenta: "<<b.TdeCuenta<<endl;
            cout<<"Saldo al momento de la consulta: " <<b.SaldoActual<<endl;
            break;
        case 5:
            system("cls");
            cout<<"     CONSECUTIVO    |                 CLIENTE                    |   CUENTA   |  TIPO  |   VALOR    "<<endl;
            cout<<" --------------------------------------------------------------------------------------------------"<<endl;
            while (mov.read((char*)&aux, sizeof (aux)))
            {
                if (strcmp(aux.NId, a.NId)==0)
                {
                    cout<<"          "<<aux.consecutivo<<"         |"<<name<<"                   |   "<<b.NCuenta<<"    |   "<<aux.TdeMovimiento<<"    |   "<<aux.valor<<"   ";
                    cout<<" "<<endl;
                    cout<<" --------------------------------------------------------------------------------------------------"<<endl;
                }
            }
            break;
        case 6:
            system ("cls");
            cout<<"                      REPORTE GENERAL                        "<<endl;
            cout<<"-----------------------------------------------------------"<<endl;
            cout<<endl;
            cout<<"INFORMACION GENERAL"<<endl;
            cout<< "Cliente: "<<name<<endl;
            cout<< "Cuenta: "<<b.NCuenta<<endl;
            cout<< "Tipo de cuenta: "<<b.TdeCuenta<<endl;
            cout<< "Saldo Actual: "<<b.SaldoActual<<endl;
            cout<<endl;
            cout<<"-----------------------------------------------------------"<<endl;
            cout<<"INFORMACION DE MOVIMIENTOS:                    "<<endl;
            cout<<endl;
            mov.seekg(0);
            cout<<"     CONSECUTIVO    |  TIPO  |   VALOR    "<<endl;
            cout<<" -------------------------------------------"<<endl;
            while (mov.read((char*)&aux, sizeof(aux)))
            {
                if (strcmp(a.NId, aux.NId)==0)
                {
                    cout<<"          "<<aux.consecutivo<<"         |   "<<aux.TdeMovimiento<<  "    |   " <<aux.valor<<endl;
                    cout<<" -------------------------------------------"<<endl;
                }
            }
            break;
        }
        delete[]name;
    }
    else if(!correcto)
    {
        cout<<"El numero escrito no esta asociado a ningun cliente o cuenta, intentelo nuevamente"<<endl;
    }
    system("pause");
    system ("cls");

}


user leerUser(char num[])
{
    user lectura;
    int pos;
    ifstream leeruser("usuarios.dat", ios::in | ios::binary);
    if (!leeruser)
    {
        cout << "Error al abrir el archivo de usuarios." << endl;
        return lectura;
    }
    leeruser.seekg(0);
    while (leeruser.read((char*)&lectura, sizeof(lectura)))
    {

        if (strcmp(lectura.NId, num) == 0)
        {
            leeruser.close();
            return lectura;
        }
    }

    leeruser.close();
    return lectura;
}

account leerAccount(char num[])
{
    account lectura1;
    ifstream leerAc("cuenta.dat", ios::in | ios::binary);
    if (!leerAc)
    {
        cout << "Error al abrir el archivo de cuentas." << endl;
        return lectura1;
    }
    leerAc.seekg(0);
    while (leerAc.read((char*)&lectura1, sizeof(lectura1)))
    {
        if (strcmp(lectura1.NId, num) == 0)
        {
            leerAc.close();
            return lectura1;
        }
    }
    leerAc.close();
    return lectura1;
}

void AdicionarCuenta(account cuenta)
{
    ofstream Ac("cuenta.dat", ios::app | ios::binary);
    if (!Ac)
    {
        cout << "Error al abrir el archivo para registrar la cuenta." << endl;
    }
    else
    {
        Ac.write((char*)&cuenta, sizeof(cuenta));

        Ac.close();
    }
}
void adicionarUser(user regis)
{
    ofstream Us("usuarios.dat", ios::app | ios::binary | ios::ate);
    if (!Us)
    {
        cout << "Error al abrir el archivo para registrar el usuario." << endl;
    }
    else
    {
        Us.write((char*)&regis, sizeof(regis));
        Us.close();
    }
}
void AdicionarMovimiento(movement mov)
{
    ofstream movimiento("movimientos.dat", ios::app | ios::binary | ios::ate);
    if (!movimiento)
    {
        cout << "Error al abrir el archivo para registrar el movimiento." << endl;
    }
    else
    {
        movimiento.write((char*)&mov, sizeof(mov));
        movimiento.close();
    }
}

int consecutivo (char num[])
{
    movement aux;
    int contador=0;
    ifstream conteo ("movimientos.dat", ios::in | ios::binary );
    while(conteo.read((char*)&aux, sizeof(aux)))
    {
        if (strcmp(num, aux.NId)==0)
        {
            contador++;
        }
    }
    return contador;
}

char* nombre(char *nombres, char *apellidos)
{
    char *nombreC=new char[strlen(nombres)+strlen(apellidos)+2];
    strcpy(nombreC, nombres);
    strcat(nombreC, " ");
    strcat(nombreC, apellidos);
    return nombreC;
}

