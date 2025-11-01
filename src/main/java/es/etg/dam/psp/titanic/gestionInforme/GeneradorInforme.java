package es.etg.dam.psp.titanic.gestionInforme;

import es.etg.dam.psp.botes.BoteData;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class GeneradorInforme {

    private static final String MSG_ERROR_FORMATO = "Formato no soportado: ";

    private final FormatoInforme formato;

    public GeneradorInforme(TipoFormato tipoFormato) {
        switch (tipoFormato) {
            case MARKDOWN -> this.formato = new FormatoMarkdown();
            case HTML -> this.formato = new FormatoHTML();
            default -> throw new IllegalArgumentException(MSG_ERROR_FORMATO + tipoFormato);
        }
    }

    public void generarInforme(String nombreArchivo, List<String> idsBotes, List<BoteData> datosBotes) {
        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            formato.escribirEncabezado(writer);

            int total = 0, hombres = 0, mujeres = 0, ninos = 0;

            for (int i = 0; i < idsBotes.size(); i++) {
                String id = idsBotes.get(i);
                BoteData datos = datosBotes.get(i);

                formato.escribirBote(writer, id, datos);

                total += datos.getTotal();
                hombres += datos.getHombres();
                mujeres += datos.getMujeres();
                ninos += datos.getNinos();
            }

            formato.escribirTotales(writer, total, hombres, mujeres, ninos);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
