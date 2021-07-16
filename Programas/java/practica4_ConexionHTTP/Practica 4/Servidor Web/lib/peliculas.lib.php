<?php
/**
Archivo: usuario.lib.php
Autor:   Carlos Felipe Lopez
Fecha:   Mayo 2005
-----------------------------------------------------------------
 Definición de clase: PELICULAS
 Utilizada para controlar las operaciones de gestión de un
 Usuario del Sistema en la Base de Datos en PostgreSql.
-----------------------------------------------------------------
*/ 

class Peliculas{

   var $Titulo;
   var $Año;
   var $Genero;
   var $Con;
   var $Lista;
   var $NumReg;
   var $Tp;  //CFL estuvo aqui
   var $pelicula;
   var $peliculas;

// Metodo para desplegar las Peliculas
   //Método para listar los USUARIOS de la Base de Datos

	function Peliculas(){
     $this->Titulo = "";
	 $this->Año = "";
	 $this->Genero = "";
	 $this->pelicula = "";
     $this->peliculas="";

     $this->Con = new postgreSql;
	 $this->Lista = null;
	 $this->NumReg = 0;

	}

   function Cargar_Peliculas() {
      $this->Con->Conectarse();
	  $this->Con->Sql = "SELECT * FROM peliculas ORDER BY titulo,ano,genero";
	  $this->Con->Consultar();
	  $this->Lista = $this->Con->RtaSql;
	  $this->NumReg = $this->Con->NumReg;
	  $this->Con->Desconectarse();
   }

   function Ver_Pelicula($num) {
       $this->Titulo       = pg_fetch_result($this->Lista,$num,"titulo");
	   $this->Genero       = pg_fetch_result($this->Lista,$num,"genero");
	   $this->Año          = pg_fetch_result($this->Lista,$num,"ano");
   }

    function  listarPeliculas(){
		$this->peliculas="&";
		$this->Cargar_Peliculas();
		if($this->NumReg > 0){		
           for($i=0; $i < $this->NumReg; $i++) {
  			   $this->Ver_Pelicula($i);		
               $this->pelicula = $this->Titulo.";".$this->Año.";".$this->Genero;
               $this->peliculas=$this->pelicula.":".$this->peliculas;
			   
            }
        return $this->peliculas;
        }else
          return "error";
       // return $this->peliculas;
    }

}
