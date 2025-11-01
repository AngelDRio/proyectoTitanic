package es.etg.dam.psp.titanic;

public class AppTitanic {

    // CONSTANTES

    private static final String MSG_INICIO = "Iniciando simulación del Titanic...";
    private static final String MSG_INFORMACION = "La simulación puede tardar entre 40 y 120 segundos, espere por favor.";
    private static final String MSG_COMPLETADO = "Simulación completada. Archivo generado.";
    public static void main(String[] args) throws Exception {
        System.out.println(MSG_INICIO);
        System.out.println(MSG_INFORMACION);

        ServicioEmergencia servicio = new ServicioEmergencia();
        servicio.lanzarSimulacion();

        System.out.println(MSG_COMPLETADO);
    }
}