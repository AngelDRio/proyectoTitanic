package es.etg.dam.psp.titanic.gestionInforme;

import java.io.IOException;
import java.io.Writer;
import es.etg.dam.psp.botes.BoteData;

public class FormatoHTML implements FormatoInforme {

    private static final String MSG_NO_IMPLEMENTADO = "Función no implementada aún. Se añadirá en futuras actualizaciones.\n";

    @Override
    public void escribirEncabezado(Writer writer) throws IOException {
        throw new UnsupportedOperationException(MSG_NO_IMPLEMENTADO);
    }

    @Override
    public void escribirBote(Writer writer, String id, BoteData d) throws IOException {
        throw new UnsupportedOperationException(MSG_NO_IMPLEMENTADO);
    }

    @Override
    public void escribirTotales(Writer writer, int total, int hombres, int mujeres, int ninos) throws IOException {
        throw new UnsupportedOperationException(MSG_NO_IMPLEMENTADO);
    }
}
