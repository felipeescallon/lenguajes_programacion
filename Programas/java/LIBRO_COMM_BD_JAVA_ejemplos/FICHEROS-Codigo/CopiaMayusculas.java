public class CopiaMayusculas extends CopiaFichero {

   CopiaMayusculas(String NombreOrigen, String NombreDestino) {
     super(NombreOrigen, NombreDestino);
   }
 
   public byte[] Procesa(byte[] buffer, int NumBytes) {
      String Linea = new String(buffer,0,NumBytes);
      Linea = Linea.toUpperCase();
      return Linea.getBytes();
   }

}