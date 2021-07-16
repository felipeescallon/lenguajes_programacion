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

// Define el nuevo usuario que va a ingresar
$peliculas = new Peliculas;

$lista = $peliculas->listarPeliculas();

echo "pelicula;".$lista;

?>