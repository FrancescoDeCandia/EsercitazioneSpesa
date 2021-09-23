package it.apulia.EsercitazioneSpesa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prodotto {
    @Id
    String prod_id;
    String nomeProd;
    Float prezzoProd;

}
