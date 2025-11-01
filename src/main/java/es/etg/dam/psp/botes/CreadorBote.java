package es.etg.dam.psp.botes;

import java.io.ObjectOutputStream;
import java.util.Random;

public class CreadorBote {

    // CONSTANTES

    private static final String MSG_ERROR_ID = "Falta el ID del bote.";

    private static final int NUM_MIN_DEMORA = 2000;
    private static final int NUM_MAX_DEMORA = 6000;
    private static final int NUM_MIN_PASAJEROS = 1;
    private static final int NUM_MAX_PASAJEROS = 100;

    private final String id;

    public CreadorBote(String id) {
        this.id = id;
    }
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.err.println(MSG_ERROR_ID);
            System.exit(1);
        }

        String id = args[0];
        CreadorBote bote = new CreadorBote(id);
        BoteData datos = bote.contarPasajeros();

        // Enviar el objeto boteData al proceso padre por stdout
        try (ObjectOutputStream out = new ObjectOutputStream(System.out)) {
            out.writeObject(datos);
            out.flush();
        }
    }

    public BoteData contarPasajeros() throws InterruptedException {
        int demora = generarNumero(NUM_MIN_DEMORA, NUM_MAX_DEMORA);
        Thread.sleep(demora);

        int total = generarNumero(NUM_MIN_PASAJEROS, NUM_MAX_PASAJEROS);
        int hombres = generarNumero(NUM_MIN_PASAJEROS, total);
        int restantes = total - hombres;

        int mujeres = (restantes > 0) ? generarNumero(NUM_MIN_PASAJEROS, restantes) : 0;
        restantes -= mujeres;
        int ninos = restantes;

        return new BoteData(total, hombres, mujeres, ninos);
    }

    private int generarNumero(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + NUM_MIN_PASAJEROS) + min;
    }

}
