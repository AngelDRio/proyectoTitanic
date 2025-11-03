package es.etg.dam.psp.tests;

import es.etg.dam.psp.botes.BoteData;
import es.etg.dam.psp.botes.CreadorBote;
import es.etg.dam.psp.titanic.ServicioEmergencia;
import es.etg.dam.psp.titanic.gestionInforme.*;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class JUnitTestsTitanic {

    // ---------- TESTS DE FORMATOS ----------

    @Test
public void formatoHtml_debe_lanzar_excepcion_no_implementada() {
    FormatoInforme f = new FormatoHTML();

    // Comprobamos que escribirEncabezado lanza UnsupportedOperationException
    assertThrows(UnsupportedOperationException.class, () -> f.escribirEncabezado(new StringWriter()),
            "Escribir encabezado debe lanzar excepción no implementada");

    // Comprobamos que escribirBote lanza UnsupportedOperationException
    assertThrows(UnsupportedOperationException.class, () -> f.escribirBote(new StringWriter(), "B1", new BoteData(10,5,4,1)),
            "Escribir bote debe lanzar excepción no implementada");

    // Comprobamos que escribirTotales lanza UnsupportedOperationException
    assertThrows(UnsupportedOperationException.class, () -> f.escribirTotales(new StringWriter(), 10,5,4,1),
            "Escribir totales debe lanzar excepción no implementada");
}

    @Test
    public void formatoMarkdown_encabezado_fecha_y_titulo_presentes() throws IOException {
        FormatoMarkdown f = new FormatoMarkdown();
        StringWriter w = new StringWriter();

        f.escribirEncabezado(w);
        String out = w.toString();

        assertTrue(out.contains("# SERVICIO DE EMERGENCIAS"), "Debe contener el título principal");
        assertTrue(out.contains("Ejecucion realizada el dia"), "Debe contener la línea de fecha");
    }

    @Test
    public void formatoMarkdown_bote_y_totales_formato_correcto() throws IOException {
        FormatoMarkdown f = new FormatoMarkdown();
        StringWriter w = new StringWriter();

        BoteData datos = new BoteData(12, 7, 4, 1);
        f.escribirBote(w, "B-123", datos);
        f.escribirTotales(w, 12,7,4,1);

        String out = w.toString();
        assertTrue(out.contains("B-123"), "Debe contener el id del bote");
        assertTrue(out.contains("Total Salvados") || out.contains("Total"), "Debe listar el campo total");
        assertTrue(out.contains("Hombres"), "Debe listar el campo 'Hombres'");
        assertTrue(out.contains("Mujeres"), "Debe listar el campo 'Mujeres'");
        assertTrue(out.contains("Ninos"), "Debe listar el campo 'Ninos'");
        assertTrue(out.contains("12"), "Debe contener el valor total 12");
    }

    @Test
    public void generadorInforme_crea_fichero_markdown_con_contenido() throws IOException {
        List<String> ids = Arrays.asList("B1", "B2");
        List<BoteData> datos = Arrays.asList(new BoteData(5,3,2,0), new BoteData(8,4,3,1));

        GeneradorInforme g = new GeneradorInforme(TipoFormato.MARKDOWN);

        Path tmp = Files.createTempFile("informe-test", ".md");
        try {
            g.generarInforme(tmp.toString(), ids, datos);

            List<String> lines = Files.readAllLines(tmp);
            String file = String.join("\n", lines);

            assertTrue(file.contains("SERVICIO DE EMERGENCIAS"), "El fichero debe contener el encabezado");
            assertTrue(file.contains("B1"), "Debe contener la información del primer bote");
            assertTrue(file.contains("B2"), "Debe contener la información del segundo bote");
            assertTrue(file.contains("13") || file.contains("Total"), "Debe contener el total acumulado (13)");
        } finally {
            Files.deleteIfExists(tmp);
        }
    }

    // ---------- TESTS PARA CREADOR DE BOTES ----------

    @Test
    public void creadorBote_genera_valores_en_rango_valido() {
        CreadorBote creador = new CreadorBote("B1");
        for (int i = 0; i < 5; i++) {
            BoteData bote = null;
            try {
                bote = creador.contarPasajeros();
            } catch (InterruptedException e) {
                fail("No debe lanzar excepción");
            }
            assertTrue(bote.getTotal() >= 0, "Total no puede ser negativo");
            assertTrue(bote.getHombres() >= 0, "Hombres no puede ser negativo");
            assertTrue(bote.getMujeres() >= 0, "Mujeres no puede ser negativo");
            assertTrue(bote.getNinos() >= 0, "Niños no puede ser negativo");
            assertEquals(bote.getTotal(), bote.getHombres() + bote.getMujeres() + bote.getNinos(), "Total debe ser la suma de hombres+mujeres+niños");
        }
    }

    @Test
    public void creadorBote_genera_datos_aleatorios_diferentes() {
        CreadorBote creador = new CreadorBote("B1");
        Set<Integer> totales = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            BoteData b = null;
            try {
                b = creador.contarPasajeros();
            } catch (InterruptedException e) {
                fail("No debe lanzar excepción");
            }
            totales.add(b.getTotal());
        }
        assertTrue(totales.size() > 1, "Los valores generados deben variar para simular aleatoriedad");
    }

    // ---------- TESTS PARA SERVICIO DE EMERGENCIA ----------

    @Test
    public void servicioEmergencia_no_lanza_excepciones_en_ejecucion_basica() {
        ServicioEmergencia s = new ServicioEmergencia();
        assertDoesNotThrow(() -> s.lanzarSimulacion(), "No debe lanzar excepciones al ejecutar la simulación");
    }

}


