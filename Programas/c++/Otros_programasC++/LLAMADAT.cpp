/* CREADO POR FELIPE ESCALLON PORTILLA : SEP/2002 */
/*PROGRAMA QUE SIMULA UNA LLAMADA TELEFONICA*/

#include<iostream.h>

class Central
{
 int numAbonados;
 int numConexiones;//~contador

 public:
    Central(){numAbonados=numConexiones=0;};
    ~Central(){cout<<"central destruida..."<<endl;};
    int peticionDeTono();
    int verificarTelefonoMarcado();
    void establecerConexion(){numConexiones+=1;};
};



class Telefono
{
 long int numero;
 int estado;//desconectado=0; conectado=1;

 public:
   Telefono(long int numtel){numero=numtel; estado=0;};//crea una linea telefonica
   ~Telefono(){cout<<"phone destroyed!"<<endl;};
   long int recibirNumtel_aLlamar();
   void marcarNumeroTelefono(Central *c);
     //estas dos funciones reciben una ref. al obj. de la calse central
   int levantarAuricular(Central *c);
};






long int Telefono::recibirNumtel_aLlamar()
{
 long int numAllamar;
 cout<<"digite el numero de telefono al que desea marcar"<<endl;
 cin>>numAllamar;
 cin.ignore();
 return numAllamar;
}

 int Telefono::levantarAuricular(Central *c)
 {
   estado=c->peticionDeTono();
   return estado;

 }

 void Telefono::marcarNumeroTelefono(Central *c);
 {

  if(c->verificarTelefonoMarcado)==1){
   c->establecerConexion();          }


   else
       cout<<"no es posible establecer comunicacion!!"<<endl;
 }


 int Central::peticionDetono() { return 0;};

 int Central::verificarTelefonoMarcado(long int numtel)
 {
  if(numtel!0) return 1;
   else return 0;
 }


 int main()
 {
  Telefono phone(7290017);

  Central *c = new Central();

   phone->levantarAuricular(c);
   phone->recibirNumtel_aLlamar(c);
   phone->marcarNumeroTelefono();


   delete c;

  return 0;
 }





