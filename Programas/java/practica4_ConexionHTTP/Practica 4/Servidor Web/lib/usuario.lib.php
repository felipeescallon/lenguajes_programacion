<?php
/**
Archivo: usuario.lib.php
Autor:   Carlos Felipe Lopez
Fecha:   Mayo 2005
-----------------------------------------------------------------
 Definición de clase: USUARIO
 Utilizada para controlar las operaciones de gestión de un
 Usuario del Sistema en la Base de Datos en PostgreSql.
-----------------------------------------------------------------
*/
class Usuario {
   var $Id;
   var $Nombre;
   var $Nombre1;
   var $Nombre2;
   var $Apellido1;
   var $Apellido2;
   var $Email;
   var $Login;
   var $Password;
   var $Con;
   var $Lista;
   var $NumReg;
   var $Tp;  //CFL estuvo aqui
  
   //Constructor de la clase USUARIO
   function Usuario() {
      $this->Id = 0;
	  $this->Nombre = "";
	  $this->Nombre1 = "";
	  $this->Nombre2 = "";
	  $this->Apellido1 = "";
	  $this->Apellido2 = "";
	  $this->Email = "";
	  $this->Login = "";
	  $this->Password = "";
	  $this->Con = new postgreSql;
	  $this->Lista = null;
	  $this->NumReg = 0;
	  $this->Tp= "";  //CFL estuvo aqui
   }
   
   //Asigna atributos de la clase USUARIO //CFL estuvo aqui con $tp
   function Set($id,$n1,$n2,$ap1,$ap2,$em,$lo,$pw,$tp) {
	  $this->Id = $id;
	  $this->Nombre = $n1." ".$n2." ".$ap1." ".$ap2;
	  $this->Nombre1 = $n1;
	  $this->Nombre2 = $n2;
	  $this->Apellido1 = $ap1;
	  $this->Apellido2 = $ap2;
	  $this->Email = $em;
	  $this->Login = $lo;
	  $this->Password = $pw;
	  $this->Tp= $tp;
   }
   
   //Método para adicionar un USUARIO a la Base de Datos
   function Adicionar() {
      $this->Con->Conectarse();
	  $pwd = md5($this->Password);
	  $this->Con->Sql = "INSERT INTO usuario VALUES (0,'$this->Nombre1','$this->Nombre2','$this->Apellido1','$this->Apellido2','$this->Email','$this->Login','$pwd','$this->Tp')";
	  $this->Con->Actualizar();
	  $this->Con->Desconectarse();
   }

   //Método para modificar un USUARIO en la Base de Datos CFL estuvo aqui 
   function Modificar() {
      $this->Con->Conectarse();
	  if (!empty($this->Password)) {
	    $pwd = md5($this->Password);
	    $this->Con->Sql = "UPDATE usuario SET nombre1='$this->Nombre1', nombre2='$this->Nombre2', apellido1='$this->Apellido1', apellido2='$this->Apellido2', email='$this->Email', login='$this->Login', password='$pwd', tipo_usr= '$this->Tp' WHERE idusuario ='$this->Id'";
	  } else {
	    $this->Con->Sql = "UPDATE usuario SET nombre1='$this->Nombre1', nombre2='$this->Nombre2', apellido1='$this->Apellido1', apellido2='$this->Apellido2', email='$this->Email', login='$this->Login', tipo_usr= '$this->Tp' WHERE idusuario='$this->Id'";
	  }
	  $this->Con->Actualizar();
	  $this->Con->Desconectarse();
   }

   //Método para eliminar un USUARIO de la Base de Datos
   function Eliminar() {
      $this->Con->Conectarse();
	  $this->Con->Sql = "DELETE FROM usuario WHERE idusuario='$this->Id'";
	  $this->Con->Actualizar();
	  $this->Con->Desconectarse();
   }

   //Método para modificar el Password de un USUARIO en la Base de Datos
   function Cambiar_pwd($passnuevo,$idu) {
      $this->Con->Conectarse();
	  $this->Password = md5($passnuevo);
	  $this->Con->Sql = "UPDATE usuario SET password='$this->Password' WHERE idusuario='$idu'";
	  $this->Con->Actualizar();
	  $this->Con->Desconectarse();
   }

   //Método para consultar la información de un USUARIO de la Base de Datos CFL estuvo aqui
   function Consultar($id) {
      $this->Id = $id;
      $this->Con->Conectarse();
	  $this->Con->Sql = "SELECT * FROM usuario WHERE idusuario='$this->Id'";
	  $this->Con->Consultar();
	  if ($this->Con->NumReg > 0) {
	     $this->Nombre   = pg_fetch_result($this->Con->RtaSql,0,"nombre");
		 $this->Apellido = pg_fetch_result($this->Con->RtaSql,0,"apellido");
	     $this->Email    = pg_fetch_result($this->Con->RtaSql,0,"email");
	     $this->Login    = pg_fetch_result($this->Con->RtaSql,0,"login");
	     $this->Password = pg_fetch_result($this->Con->RtaSql,0,"password");
	  }
	  $this->Con->Desconectarse();
   }

   //Método para listar los USUARIOS de la Base de Datos
   function Lista_usuarios() {
      $this->Con->Conectarse();
	  $this->Con->Sql = "SELECT * FROM usuario ORDER BY apellido1,apellido2,nombre1,nombre2,login";
	  $this->Con->Consultar();
	  $this->Lista = $this->Con->RtaSql;
	  $this->NumReg = $this->Con->NumReg;
	  $this->Con->Desconectarse();
   }
   
   //Método para ver cada uno de los USUARIOS de la lista en la Base de Datos CFL estuvo aqui
   function Ver_usuario($num) {
       $this->Id       = pg_fetch_result($this->Lista,$num,"idusuario");
	   $this->Nombre   = pg_fetch_result($this->Con->RtaSql,0,"nombre");
	   $this->Apellido = pg_fetch_result($this->Con->RtaSql,0,"apellido");
	   $this->Email    = pg_fetch_result($this->Con->RtaSql,0,"email");
	   $this->Login    = pg_fetch_result($this->Con->RtaSql,0,"login");
	   $this->Password = pg_fetch_result($this->Con->RtaSql,0,"password");
   }
   
   function Validar($login, $pwd) {
      $this->Login = $login;
	  $this->Password = $pwd;
      $this->Con->Conectarse();
	  $this->Con->Sql = "SELECT * FROM usuario WHERE login='$this->Login'";
	  $this->Con->Consultar();
	  if ($this->Con->NumReg > 0) {	//El usuario existe, esta registrado
//	     $pwd_enc = md5($this->Password);
	     for ($n=0; $n < $this->Con->NumReg; $n++) {
		    $pwd = $this->Con->LeerCampo("password", $n);
			if (!strcmp($this->Password,$pwd)) {	//Los passwords son iguales
//			if (!strcmp($pwd_enc,$pwd)) {	//Los passwords son iguales
		       $this->Id = $this->Con->LeerCampo("idusuario",$n);
	           $this->Consultar($this->Id);
		       return(10);
			} else {
			   return(-20);		//Los passwords son diferentes
			}
		 }
	  } else {
	     return(-10);	//Usuario no registrado
	  }
	  $this->Con->Desconectarse();
   }
}
/**
-----------------------------------------------------------------
 Fin de la definicion de la clase USUARIO
-----------------------------------------------------------------
*/
?>