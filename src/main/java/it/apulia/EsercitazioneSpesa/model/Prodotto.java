package it.apulia.EsercitazioneSpesa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "prodotti")
public class Prodotto {

    @Id
    String prod_id;
    String nomeProd;
    Float prezzoProd;

    public Prodotto(String nomeProd, Float prezzoProd) {
        this.nomeProd = nomeProd;
        this.prezzoProd = prezzoProd;
    }
}
