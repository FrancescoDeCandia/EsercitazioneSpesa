package it.apulia.EsercitazioneSpesa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotaSpesa {
    Prodotto prodotto;
    Integer quantita;
}
