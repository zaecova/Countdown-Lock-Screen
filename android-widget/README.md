# Countdown Visa Android Widget

Este proyecto crea una aplicación Android nativa con un widget de pantalla de inicio para contar los días hasta el próximo 4 de diciembre.

## Abrir y compilar

1. Instala Android Studio y un JDK 17 o posterior.
2. En Android Studio, abre la carpeta `android-widget`.
3. Espera a que Gradle sincronice el proyecto y acepta instalar Android SDK Platform 36 si lo solicita.
4. Conecta el Samsung por USB con depuración USB activada o selecciona **Build > Build APK(s)**.
5. Instala el APK en el teléfono. Después, mantén pulsado un espacio vacío de la pantalla de inicio, toca **Widgets** y busca **Countdown Visa**.

El widget se actualiza al añadirse, cuando cambia la fecha/hora del teléfono y periódicamente. Android limita las actualizaciones periódicas de widgets a intervalos no menores de 30 minutos.

La fecha se calcula como el próximo 4 de diciembre. Si la entrevista corresponde a un año específico que no es el próximo diciembre, cambia el año objetivo en `CountdownWidgetProvider.java` antes de compilar.
