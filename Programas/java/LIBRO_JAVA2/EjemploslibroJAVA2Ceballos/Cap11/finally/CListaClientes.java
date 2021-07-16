import java.io.*;
public class CListaClientes
{
  private String[] listaClientes;
  private int nElementos;
  public CListaClientes(int n)
  {
    nElementos = n;
    listaClientes = new String[n];
  }
  public void añadir(String nombre, int i)
  {
    listaClientes[i] = nombre + "\n";
  }
  public void escribir(PrintWriter fcli)
  {
    for (int i = 0; i < listaClientes.length; i++)
      fcli.write(listaClientes[i]);
  }
}
