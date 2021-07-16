/**
 * Conversi�n de grados cent�grados a fahrenheit:
 * F = 9/5 * C + 32
 */

import java.lang.System;   // importar la clase System

public class CApGrados
{
  // Definici�n de constantes
  final static int limInferior = -30;
  final static int limSuperior = 100;
  final static int incremento = 6;

  public static void main(String[] args)
  {
    // Declaraci�n de variables
    CGrados grados = new CGrados();
    int gradosCent = limInferior;
    float gradosFahr = 0;

    while (gradosCent <= limSuperior) // while ... hacer:
    {
      // Asignar al objeto grados el valor en grados cent�grados
      grados.Cent�gradosAsignar(gradosCent);
      // Obtener del objeto grados los grados fahrenheit
      gradosFahr = grados.FahrenheitObtener();
      // Escribir la siguiente l�nea de la tabla
      System.out.println(gradosCent + " C" + "\t" + gradosFahr + " F");
      // Siguiente valor
      gradosCent += incremento;
    }
  }
}

/**
 * Clase CGrados. Un objeto de esta clase almacena un valor
 * en grados cent�grados.
 * Atributos:
 *   gradosC
 * M�todos:
 *   Cent�gradosAsignar, FahrenheitObtener y Cent�gradosObtener
 */
class CGrados
{
  private float gradosC; // grados cent�grados
  
  public void Cent�gradosAsignar(float gC)
  {
    // Establecer el atributo grados cent�grados
    gradosC = gC;
  }
  
  public float FahrenheitObtener()
  {
    // Retornar los grados fahrenheit equivalentes a gradosC
    return 9F/5F * gradosC + 32;
  }
  
  public float Cent�gradosObtener()
  {
    return gradosC; // retornar los grados cent�grados
  }
}
