/**
 * Conversión de grados centígrados a fahrenheit:
 * F = 9/5 * C + 32
 */

import java.lang.System;   // importar la clase System

public class CApGrados
{
  // Definición de constantes
  final static int limInferior = -30;
  final static int limSuperior = 100;
  final static int incremento = 6;

  public static void main(String[] args)
  {
    // Declaración de variables
    CGrados grados = new CGrados();
    int gradosCent = limInferior;
    float gradosFahr = 0;

    while (gradosCent <= limSuperior) // while ... hacer:
    {
      // Asignar al objeto grados el valor en grados centígrados
      grados.CentígradosAsignar(gradosCent);
      // Obtener del objeto grados los grados fahrenheit
      gradosFahr = grados.FahrenheitObtener();
      // Escribir la siguiente línea de la tabla
      System.out.println(gradosCent + " C" + "\t" + gradosFahr + " F");
      // Siguiente valor
      gradosCent += incremento;
    }
  }
}

/**
 * Clase CGrados. Un objeto de esta clase almacena un valor
 * en grados centígrados.
 * Atributos:
 *   gradosC
 * Métodos:
 *   CentígradosAsignar, FahrenheitObtener y CentígradosObtener
 */
class CGrados
{
  private float gradosC; // grados centígrados
  
  public void CentígradosAsignar(float gC)
  {
    // Establecer el atributo grados centígrados
    gradosC = gC;
  }
  
  public float FahrenheitObtener()
  {
    // Retornar los grados fahrenheit equivalentes a gradosC
    return 9F/5F * gradosC + 32;
  }
  
  public float CentígradosObtener()
  {
    return gradosC; // retornar los grados centígrados
  }
}
