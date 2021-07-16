<?php
/**
Archivo: login.php
Autor:   Carlos Felipe Lopez
Fecha:   Mayo de 2005
-----------------------------------------------------------------
 Utilizado para validar el ingreso de un usuario al sistema.
 Utiliza las clases postgresql y usuario.
-----------------------------------------------------------------
*/
include("config/config.inc.php");
include("lib/postgreSql.lib.php");
include("lib/usuario.lib.php");

// Define el nuevo usuario que va a ingresar
$user = new Usuario;

// Recibe el LOGIN del usuario y lo busca en la Base de Datos
$result = $user->Validar($_GET['login'],$_GET['pwd']);

	switch ($result)	//Resultado de la validacion
	{
	case -10:		//Usuario no existente en el sistema
		echo "Invalido";
		break;
	case -20:		//El password de usuario no es correcto
		echo "Bad Password";
		break;
	case 10:		//Acceso exitoso al sistema
	    echo "Valido";
		break;
	}
?>
