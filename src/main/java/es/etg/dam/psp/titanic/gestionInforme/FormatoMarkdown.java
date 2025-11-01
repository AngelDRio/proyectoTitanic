package es.etg.dam.psp.titanic.gestionInforme;

import es.etg.dam.psp.botes.BoteData;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormatoMarkdown implements FormatoInforme {

    // CONSTANTES
    private static final String[] CAMPOS = {"Total Salvados", "Hombres", "Mujeres", "Ninos"};

    private static final String ENCABEZADO_TITULO = "# SERVICIO DE EMERGENCIAS\n";
    private static final String ENCABEZADO_FECHA = "Ejecucion realizada el dia %s\n\n";

    private static final String FORMATO_FECHA = "dd/MM/yyyy 'a las' HH:mm:ss";

    private static final String SECCION_BOTE = "## Bote %s\n";
    private static final String SECCION_TOTAL = "## Total\n";

    private static final String PREFIJO_PRIMER_CAMPO = "- ";
    private static final String PREFIJO_RESTO_CAMPOS = "  - ";

    private static final String FORMATO_CAMPO_VALOR = "%s%s %d\n";
    private static final String SALTO_LINEA = "\n";

    @Override
    public void escribirEncabezado(Writer writer) throws IOException {
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_FECHA);

        writer.write(ENCABEZADO_TITULO);
        writer.write(String.format(ENCABEZADO_FECHA, ahora.format(formatter)));
    }

    @Override
    public void escribirBote(Writer writer, String id, BoteData d) throws IOException {
        int[] valores = {d.getTotal(), d.getHombres(), d.getMujeres(), d.getNinos()};

        writer.write(String.format(SECCION_BOTE, id));
        for (int j = 0; j < CAMPOS.length; j++) {
            String prefijo = (j == 0) ? PREFIJO_PRIMER_CAMPO : PREFIJO_RESTO_CAMPOS;
            writer.write(String.format(FORMATO_CAMPO_VALOR, prefijo, CAMPOS[j], valores[j]));
        }
        writer.write(SALTO_LINEA);
    }

    @Override
    public void escribirTotales(Writer writer, int total, int hombres, int mujeres, int ninos) throws IOException {
        writer.write(SECCION_TOTAL);
        int[] valores = {total, hombres, mujeres, ninos};

        for (int j = 0; j < CAMPOS.length; j++) {
            String prefijo = (j == 0) ? PREFIJO_PRIMER_CAMPO : PREFIJO_RESTO_CAMPOS;
            writer.write(String.format(FORMATO_CAMPO_VALOR, prefijo, CAMPOS[j], valores[j]));
        }
    }
}
