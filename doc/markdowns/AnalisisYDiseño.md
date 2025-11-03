# Proyecto Titanic — Análisis Y Diseño

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

## Análisis del problema

El proyecto plantea simular una situación de emergencia similar al naufragio del Titanic, donde un **servicio de emergencias** debe gestionar la información de varios botes salvavidas.
Cada bote, en total habrá 20 de estos, recoge un número variable de personas (hombres, mujeres y niños), y se requiere **generar un informe con los datos de todos los botes**, mostrando los totales por bote y estadísticas globales del salvamento.

---

## Diseño de la solución

### Arquitectura

A continuación se muestra un enfoque general del proyecto planteando un diseño de solución inicial, en este se muestran dos procesos diferentes: **Titanic** y **Botes**.

![Imagen no disponible](../src/main/resources/imagenes/arquitecturaTitanic.png)


### Componentes

A continuación se muestra cada componenete del proyecto que se va a diseñar de forma más específica para comprender su funcionamiento, sus métodos y como se va a implementar una vez se codifique.

**Clase Titanic.**

![Imagen no disponible](../src/main/resources/imagenes/AppTitanic.png)

---

**Clase ServicioEmergencia.**

![Imagen no disponible](../src/main/resources/imagenes/ServicioEmergencia.png)

---

**Clase CreadorBote.**

![Imagen no disponible](../src/main/resources/imagenes/CreadorBote.png)

---

**Clase BoteData.**

![Imagen no disponible](../src/main/resources/imagenes/Botedata.png)

---

**Clase FormatoMarkdown.**

![Imagen no disponible](../src/main/resources/imagenes/FormatoMarkdown.png)

---

**Clase FormatoInforme.**

![Imagen no disponible](../src/main/resources/imagenes/FormatoInforme.png)

---

**Enumeración TipoFormato.**

![Imagen no disponible](../src/main/resources/imagenes/TipoFormato.png)

---

**Clase GeneradorInforme.**

![Imagen no disponible](../src/main/resources/imagenes/GeneradorInforme.png)

---

### Protocolo de comunicación

Habiendo diseñado cada componente previamente, a continuación se muestra como cada uno de estos comparten información entre sí y como son sus relaciones internas.

Servicio de emergencias y el proceso botes:

![Imagen no disponible](../src/main/resources/imagenes/relacionesBotes.png)

Servicio de emergencias y la generación de informes:

![Imagen no disponible](../src/main/resources/imagenes/relacionesInforme.png)

---