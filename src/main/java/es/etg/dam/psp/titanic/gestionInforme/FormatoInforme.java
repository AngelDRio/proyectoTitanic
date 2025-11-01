package es.etg.dam.psp.titanic.gestionInforme;

import java.io.IOException;
import java.io.Writer;

import es.etg.dam.psp.botes.BoteData;

public interface FormatoInforme {
    void escribirEncabezado(Writer writer) throws IOException;
    void escribirBote(Writer writer, String id, BoteData datos) throws IOException;
    void escribirTotales(Writer writer, int total, int hombres, int mujeres, int ninos) throws IOException;
}
