public class ObRegistro {
   private String DNI, Nombre, Apellido;
   private int Edad;

   ObRegistro (String DNI, String Nombre, String Apellido, int Edad) {
       this.DNI = DNI;
       this.Nombre = Nombre;
       this.Apellido = Apellido;
       this.Edad = Edad;
   }

  String DameDNI() {
     return DNI;
  }

  String DameNombre() {
     return Nombre;
  }

  String DameApellido() {
     return Apellido;
  }

  int DameEdad() {
     return Edad;
  }

}