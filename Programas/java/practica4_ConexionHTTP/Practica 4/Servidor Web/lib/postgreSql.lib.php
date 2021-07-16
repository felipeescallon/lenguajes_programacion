<?php
/**
Archivo: postgreSql.lib.php
Autor:   Carlos Felipe Lopez
Fecha:   Abril de 2004
-----------------------------------------------------------------
 Definición de clase: POSTGRESQL
 Utilizada para controlar las operaciones de gestión con una Base
 de Datos en MySQL.
-----------------------------------------------------------------
*/

class postgreSql {
   var $Host;
   var $User;
   var $Pwd;
   var $BaseDatos;
   var $Sql;
   var $RtaSql;
   var $Enlace;
   var $NumReg;

   //Constructor de la clase CONEXION
   function postgreSql () {
      global $CFG_HOST, $CFG_USER, $CFG_DBPWD, $CFG_DBASE;
      $this->Host = $CFG_HOST;
	  $this->User = $CFG_USER;
	  $this->Pwd = $CFG_DBPWD;
	  $this->BaseDatos = $CFG_DBASE;
	  $this->Sql = "";
	  $this->RtaSql = "";
	  $this->Enlace = null;
	  $this->NumReg = 0;
   }

   //Método para la conexión a la Base de Datos
   function Conectarse() {
   $conexion = pg_connect("host=localhost dbname=VideoTienda user=aplimovil password=aplimovil") or die ("Fallo en el establecimiento de la conexión");
		if (($this->Enlace = pg_connect("host=localhost dbname=VideoTienda user=postgres password=postgres")) != null) {
	  } else {
	     print "<b>Error:</b> No se ha podido establecer una conexion con el servidor de Bases de Datos $CFG_HOST";
		 return (null);
	  }
   }

   //Método para la ejecución de consultas SQL - SELECT
   function Consultar() {
      $this->RtaSql = pg_query($this->Sql);
	  $this->NumReg = pg_num_rows($this->RtaSql);
   }

   //Método para la ejecución de consultas SQL - INSERT, UPDATE y DELETE
   function Actualizar() {
      $this->RtaSql = pg_query($this->Sql);
   }

   //Método para extraer la información de un campo de un registro
   function LeerCampo($campo, $num) {
      return (pg_fetch_result($this->RtaSql,$num,$campo));
   }

   //Método para la desconexión de la Base de Datos
   function Desconectarse() {
      pg_close();
   }

   //Método para la ejecución de consultas SQL - SELECT // Para un campo CFL usada para hallar el maximo valor
   function ConsultarBasica() {
      $this->RtaSql = pg_query($this->Sql);

   }



////////////////////////////
 //Método para la creacion de una tabla en la Base de Datos 

   function almacenarTabla($sql) {
	 return(@pg_db_query($this->BaseDatos,$sql,$this->Enlace));
	}

//////////////////////////////



}/**
-----------------------------------------------------------------
 Fin de la definicion de la clase MYSQL
-----------------------------------------------------------------
*/
?>