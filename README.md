# My Retail App - Premiando a los Mejores Vendedores

## Descripción

Esta aplicación móvil para Android, desarrollada en Kotlin, tiene como objetivo premiar a los mejores vendedores de My Retail, una importante empresa de retail a nivel nacional. La aplicación permite visualizar un listado de vendedores, reproducir un audio al seleccionar un vendedor específico, capturar fotos de los vendedores destacados y visualizar un gráfico con el rendimiento de cada uno.

## Funcionalidades

*   **Pantalla de Inicio con Animación:** La aplicación cuenta con una pantalla de inicio atractiva que incluye una animación alusiva a la empresa de retail, utilizando la biblioteca Lottie.
*   **Menú Principal:** Un menú principal permite navegar entre las diferentes secciones de la aplicación.
*   **Listado de Vendedores:** Muestra un listado de vendedores con su foto, nombre y área a la que pertenecen.
*   **Reproducción de Audio:** Al seleccionar al tercer vendedor del listado, se reproduce un audio.
*   **Captura de Fotos:** Permite capturar una foto del vendedor destacado utilizando la cámara del dispositivo. La foto se guarda temporalmente en la caché de la aplicación y se muestra en el listado de vendedores.
*   **Gráfico de Ventas:** Muestra un gráfico de líneas que representa las ventas de cada vendedor, utilizando la biblioteca MPAndroidChart.
* **Navegacion:** La aplicacion cuenta con navegacion entre las distintas pantallas.

## Tecnologías Utilizadas

*   **Kotlin:** Lenguaje de programación principal.
*   **Android Studio:** Entorno de desarrollo integrado (IDE).
*   **Jetpack Compose:** Kit de herramientas moderno para la interfaz de usuario.
*   **Lottie:** Biblioteca para animaciones.
*   **MPAndroidChart:** Biblioteca para gráficos.
*   **Coil:** Biblioteca para la carga de imágenes.
*   **FileProvider:** Para el manejo de archivos temporales.
*   **MediaPlayer:** Para la reproduccion de audio.

## Estructura del Proyecto

*   **`MainActivity.kt`:** Punto de entrada de la aplicación.
*   **`AppNavigation.kt`:** Define el grafo de navegación y el menu principal.
*   **`SplashScreen.kt`:** Pantalla de inicio con la animación.
*   **`ListaVendedoresScreen.kt`:** Pantalla que muestra el listado de vendedores.
*   **`CapturaFotoActivity.kt`:** Actividad para capturar fotos con la cámara.
*   **`GraficoVentasScreen.kt`:** Pantalla que muestra el gráfico de ventas.
*   **`animacion_retail.json`:** Archivo de animación Lottie.
*   **`audio_vendedor.mp3`:** Archivo de audio.

## Instalación

1.  Clona el repositorio: `git clone https://github.com/jaderkamui/prueba5.git`
2.  Abre el proyecto en Android Studio.
3.  Sincroniza el proyecto con Gradle.
4.  Ejecuta la aplicación en un emulador o dispositivo Android.

## Consideraciones

*   **MediaStore:** Inicialmente se consideró el uso de MediaStore para el guardado de fotos, pero debido a problemas técnicos y de compatibilidad, se optó por una solución alternativa que guarda las fotos temporalmente en la caché de la aplicación.
*   **Permisos:** La aplicación solicita permisos de cámara y almacenamiento.
* **Compatibilidad:** La aplicacion es compatible con android 8 en adelante.

## Capturas de Pantalla
(Aqui puedes agregar capturas de pantalla de tu aplicacion)

## Autor

*   Jader Muñoz

## Licencia

proyecto semana 5 de universidad - ingenieria en Informatica - IACC
