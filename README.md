<h1>Práctica Final de Estructuras de Datos</h1>

Este repositorio contiene el código y la documentación para un proyecto web de Mascotas y Veterinarias. El sistema utiliza estructuras de datos de tipo grafo para modelar las relaciones entre las mascotas y las veterinarias, con pesos calculados de acuerdo a atributos específicos (como la especie en el caso de Mascota y la ciudad de la veterinaria). El proyecto tiene como objetivo realizar una ejercitación sobre las estructuras de datos de tipo grafo.

![image](https://github.com/user-attachments/assets/c38936e0-dabf-43d6-ba1c-5eab95954035)

Estructura del Proyecto
Este proyecto está dividido en varias secciones clave:

Frontend: Una interfaz web donde los usuarios pueden consultar mascotas y veterinarias.
Backend: La lógica de negocio que maneja las relaciones entre las entidades y la implementación de los grafos.
Modelo de Datos: Estructura de datos utilizada para modelar las relaciones entre mascotas y veterinarias.
Características

Grafo de Mascotas: Representa las mascotas como nodos y las conexiones entre ellas, basadas en su especie.

![image](https://github.com/user-attachments/assets/946f9824-38d0-49d6-ae42-7758e0ac9fa7)


Grafo de Veterinarias: Representa las veterinarias como nodos, los cuales se pueden conectar con un peso determinado por la ciudad en la que se encuentran.

![image](https://github.com/user-attachments/assets/8aaf47d4-617d-40a6-9d30-7eab24498447)


Pesos Calculados: Los enlaces entre nodos tienen un peso calculado según diversos factores como la cercanía geográfica y la especie de las mascotas.

------------------------------------------------------------------------------------------------------------------------------------------------------------------------

<h2>Instalación</h2>
<h3>Requisitos</h3>

Para correr este proyecto, necesitarás tener instalados los siguientes programas:

- python3
- Java 8
  
git clone https://github.com/PacchaDavid/PracticaCuatroEstructuras

<div>Navega a la carpeta del proyecto:</div>
cd PracticaCuatroEstructuras/<br>

<h3>Instalar Dependencias (solo para bash)</h3>

<div>Ejecuta los siguientes comandos para instalar las dependencias necesarias del backend (java):</div>
cd graph-service/<br>
mvn clean install<br>

<div>Ejecuta los siguientes comandos para instalar las dependencias necesarias del front (python):</div>
cd ../front<br>
python3 -m venv virtual<br>
source virtual/bin/activate<br>
pip install flask requests<br>

<h3>Iniciar el Servidor</h3>
<div>Para iniciar el servidor de desarrollo, ejecuta (backend):</div>
cd ../graph-service/; mvn clean compile; mvn exec:java<br>

<div>en otro proceso distinto puede ejecutar el front:</div>
python3 ../front/index.py<br>
<br>
Con esto, puede empezar a utilizar la aplicación dirigiéndose a la siguiente ruta en su navegador de preferencia: localhost:5000/<br>

El template html fue sacado de: https://startbootstrap.com/theme/sb-admin-2/
