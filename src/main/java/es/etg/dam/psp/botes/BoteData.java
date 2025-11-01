package es.etg.dam.psp.botes;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoteData implements Serializable {

    private int total;
    private int hombres;
    private int mujeres;
    private int ninos;
}
