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
# Asegura la creación del directorio docs/api
# ---------------------------------------------------------

cd ../../docs
mkdir apiCliente
cd ../bin

# ---------------------------------------------------------
# Genera la documentación
# ---------------------------------------------------------

javadoc -sourcepath ../source -d ../docs/api -subpackages uniandes.cupi2.continental.cliente

stty echo
