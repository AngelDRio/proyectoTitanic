package es.etg.dam.psp.titanic;

import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import es.etg.dam.psp.botes.BoteData;
import es.etg.dam.psp.titanic.gestionInforme.GeneradorInforme;
import es.etg.dam.psp.titanic.gestionInforme.TipoFormato;

public class ServicioEmergencia {

    //CONSTANTES

    private static final int NUM_BOTES = 20;

    private static final String CLASE_CREADOR_BOTE = "es.etg.dam.psp.botes.CreadorBote";
    private static final String CP = "-cp";
    private static final String JAVA_CMD = "java";
    private static final String CLASSPATH = System.getProperty("java.class.path");

    private static final String FORMATO_ID = "B%02d";

    private static final String TIPO_ARCHIVO_MARKDOWN = ".md";
    private static final String TIPO_ARCHIVO_HTML = ".html";
    private static final String RUTA_INFORME = "src/main/resources/informe" + TIPO_ARCHIVO_HTML;

    private static final String MSG_ERROR_BOTE = "El proceso del bote %s terminó con error (%s)";

    public void lanzarSimulacion() throws Exception {

        List<String> idsBotes = new ArrayList<>();
        List<BoteData> datosBotes = new ArrayList<>();

        for (int i = 0; i < NUM_BOTES; i++) {
            String id = String.format(FORMATO_ID, i);
            idsBotes.add(id);

            BoteData datos = llamadaCreadorBote(id);
            datosBotes.add(datos);
        }

        // Usamos el generador de informes con el formato MARKDOWN
        GeneradorInforme generador = new GeneradorInforme(TipoFormato.HTML);
        generador.generarInforme(RUTA_INFORME, idsBotes, datosBotes);
    }

    private BoteData llamadaCreadorBote(String id) throws Exception {
        String[] comando = {JAVA_CMD, CP, CLASSPATH, CLASE_CREADOR_BOTE, id};
        Process proceso = Runtime.getRuntime().exec(comando);

        BoteData datos;
        try (ObjectInputStream in = new ObjectInputStream(proceso.getInputStream())) {
            datos = (BoteData) in.readObject();
        }

        int codigo = proceso.waitFor();
        if (codigo != 0) {
            System.err.println(String.format(MSG_ERROR_BOTE, id, codigo));
        }

        return datos;
    }
}
