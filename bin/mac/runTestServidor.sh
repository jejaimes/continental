#!/bin/sh
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
# Universidad de los Andes (Bogotá - Colombia)
# Departamento de Ingeniería de Sistemas y Computación
# Licenciado bajo el esquema Academic Free License version 2.1
#
# Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
# Ejercicio: n12_continental
# Autor: Equipo Cupi2 2014
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

stty -echo

# ---------------------------------------------------------
# Ejecución de las pruebas
# ---------------------------------------------------------

cd ../..
	
java -ea -classpath ./lib/derby.jar:./lib/continentalServidor.jar:./test/lib/continentalTestServidor.jar:./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.continental.testServidor.AdministradorJugadoresTest
java -ea -classpath ./lib/derby.jar:./lib/continentalServidor.jar:./test/lib/continentalTestServidor.jar:./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.continental.testServidor.CartaTest
java -ea -classpath ./lib/derby.jar:./lib/continentalServidor.jar:./test/lib/continentalTestServidor.jar:./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.continental.testServidor.InfoJugadorTest

cd bin/mac

stty echo
