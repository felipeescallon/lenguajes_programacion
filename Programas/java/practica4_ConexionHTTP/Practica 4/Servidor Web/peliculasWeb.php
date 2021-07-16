<style type="text/css">
<!--
.Estilo4 {font-family: Arial, Helvetica, sans-serif; color: #FFFFFF; font-weight: bold; }
.Estilo5 {
	font-family: Arial, Helvetica, sans-serif;
	font-weight: bold;
	font-size: 24px;
	color: #006699;
}
.Estilo6 {font-family: Arial, Helvetica, sans-serif; font-weight: bold; font-size: 14px; color: #000000; }
-->
</style>
<p align="center" class="Estilo5">Video Tienda &quot;El Rapidito&quot; <br>
</p>
<p align="center" class="Estilo6">Peliculas disponibles
</p>
<?
/**
Archivo: peliculas.php
Autor:   Carlos Felipe Lopez
Fecha:   Mayo 2005
---------------------------------------------------------------------
 Utilizado para cargar las peliculas que se encuentran en el servidor.
 Utiliza las clases mysql y usuario.
---------------------------------------------------------------------
*/
include("config/config.inc.php");
include("lib/postgreSql.lib.php");
include("lib/peliculas.lib.php");

//Crea una instancia de Peliculas.
$peliculas = new Peliculas;

$peliculas->Cargar_Peliculas();
?>
<table width="200" border="1" align="center">
  <tr bgcolor="#003366">
    <td><div align="center" class="Estilo4">Titulo</div></td>
    <td><div align="center" class="Estilo4">A&ntilde;o</div></td>
    <td><div align="center" class="Estilo4">Genero</div></td>
  </tr>

<?	for ($n=0;$n<($peliculas->NumReg);$n++){
	$peliculas->Ver_Pelicula($n);
?>  <tr>
    <td><div align="center"><? echo $peliculas->Titulo;?></div></td>
    <td><div align="center"><? echo $peliculas->Año;?></div></td>
    <td><div align="center"><? echo $peliculas->Genero;?></div></td>
  </tr>
<?	}?>

</table>
