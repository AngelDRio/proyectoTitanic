# Proyecto Titanic — Manual de Usuario y Plan de Pruebas

## Integrantes del proyecto
- **Ángel Del Río García y Alejandro Rodríguez Rodríguez**
- Módulo: PSP (Programación de Servicios y Procesos)
- Curso: 2º DAM

---

## Índice
1. [Análisis del problema](#análisis-del-problema)
2. [Diseño de la solución](#diseño-de-la-solución)
   - [Arquitectura](#arquitectura)
   - [Componentes](#componentes)
   - [Protocolo de comunicación](#protocolo-de-comunicación)
3. [Herramientas utilizadas](#herramientas-utilizadas)

---

## Manual de Usuario

## Cómo ejecutar el programa

Para que el programa funcione correctamente, es suficiente con ejecutar la clase principal `AppTitanic`. A continuación, se detallan los pasos:

### Requisitos previos
- Tener instalado **Java JDK 8 o superior**.
- Contar con un entorno de desarrollo como **VScode** o ejecutar desde la línea de comandos.

### Ejecución desde un IDE
1. Abrir el proyecto `PROYECTOTITANIC` en tu IDE favorito.
2. Navegar a la clase `AppTitanic.java`, ubicada en:
 ```bash
 src/main/java/es/etg/dam/psp/titanic/AppTitanic.java
 ```
3. Ejecutar la clase como **Java Application**.
4. El programa iniciará y realizará las operaciones definidas automáticamente.

### Ejecución desde línea de comandos
1. Abrir una terminal y navegar a la carpeta raíz del proyecto (`PROYECTOTITANIC`).
2. Compilar el proyecto:
```bash
mvn clean compile
```
3. Ejecutar la clase principal:
```bash
java -cp target/classes es.etg.dam.psp.titanic.AppTitanic

```
**NOTA IMPORTANTE**: Para este proyecto se ha usado la versión 17 de java, por tanto para ejecutar el programa por la terminal se ha usado una terminal JavaSE-17. Se comenta esto ya que al ejecutarlo en versiones como, por ejemplo JDK 25, el programa no compila ni ejecuta correctamente al ser una versión tan reciente. Se recomienda el uso de la versión 17 o 21 y revisar que la terminal sea de una de estás dos versiones.



