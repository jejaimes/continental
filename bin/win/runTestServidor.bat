@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogotá - Colombia)
REM Departamento de Ingeniería de Sistemas y Computación 
REM Licenciado bajo el esquema Academic Free License version 2.1 
REM
REM Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
REM Ejercicio: n12_continental
REM Autor: Equipo Cupi2 2014
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

SET CLASSPATH=

REM ---------------------------------------------------------
REM Ejecucion de las pruebas
REM ---------------------------------------------------------
cd ../..

java -ea -classpath ./lib/derby.jar;./lib/continentalServidor.jar;./test/lib/continentalTestServidor.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.continental.testServidor.AdministradorJugadoresTest
java -ea -classpath ./lib/derby.jar;./lib/continentalServidor.jar;./test/lib/continentalTestServidor.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.continental.testServidor.CartaTest
java -ea -classpath ./lib/derby.jar;./lib/continentalServidor.jar;./test/lib/continentalTestServidor.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.continental.testServidor.InfoJugadorTest