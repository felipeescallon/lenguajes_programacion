<?php
/**
Archivo: login.php
Autor:   Carlos Felipe Lopez
Fecha:   Mayo de 2005
-----------------------------------------------------------------
 Utilizado para validar el ingreso de un usuario al sistema. Mediante acceso Web
 Utiliza las clases Postgresql y usuario.
-----------------------------------------------------------------
*/
include("config/config.inc.php");
include("lib/postgreSql.lib.php");
include("lib/usuario.lib.php");

// Define el nuevo usuario que va a ingresar
$user = new Usuario;

//echo $_GET['login']."/".$_GET['pwd'];

// Recibe el LOGIN del usuario y lo busca en la Base de Datos
$result = $user->Validar($_POST['login'],$_POST['pwd']);

	switch ($result)	//Resultado de la validacion
	{
	case -10:		//Usuario no existente en el sistema
		print "<center><table width='95%' border='0'><tr bgcolor='#CCCCCC'><td><center>\n";
		print "<b>USUARIO INCORRECTO</b></center></td></tr></table>\n";
		print "<p>El usuario <b>$user->Login</b> no esta registrado en el sistema.<br>\n";
		print "<h5>Int&eacute;ntelo nuevamente</h5></center><hr>\n";
		print "<font color='#FF0000'><b>Nota:</b> Recuerde que su Password es sensible a may&uacute;sculas y min&uacute;sculas.</font>\n";
	    print "<meta http-equiv='refresh' content='2;URL=index.php'>";
		break;
	case -20:		//El password de usuario no es correcto
		print "<center><table width='95%' border='0'><tr bgcolor='#CCCCCC'><td><center>\n";
		print "<b>PASSWORD INCORRECTO</b></center></td></tr></table>\n";
		print "<p>El password de usuario de <b>$user->Login</b> es incorrecto.<br>\n";
		print "<h5>Int&eacute;ntelo nuevamente</h5></center><hr>\n";
		print "<font color='#FF0000'><b>Nota:</b> Recuerde que su Password es sensible a may&uacute;sculas y min&uacute;sculas.</font>\n";
	    print "<meta http-equiv='refresh' content='2;URL=index.php'>";
		break;
	case 10:		//Acceso exitoso al sistema
	    print "<table border='0' cellspacing='0' cellpadding='1' width='50%' align='center' class='q'><tr><td bgcolor='#006699'>";
	    print "<font size='2' color='#FFFFFF'>Cargando las peliculas...</font>";
	    print "</td></tr></table>";
	    print "<meta http-equiv='refresh' content='2;URL=peliculasWeb.php'>";
		break;
	}
?>
