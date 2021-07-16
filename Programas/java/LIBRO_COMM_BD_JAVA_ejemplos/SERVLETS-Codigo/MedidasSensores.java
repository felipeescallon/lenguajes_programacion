public class MedidasSensores {

  private static int[][] Medidas = new int[5][2];

  public synchronized static void Inserta(int Sensor, int Temperatura, int Presion) {
    Medidas[Sensor][0] = Temperatura;
    Medidas[Sensor][1] = Presion;
  }

  public synchronized static int[][] DameMedidas() {
    return Medidas;
  }

}