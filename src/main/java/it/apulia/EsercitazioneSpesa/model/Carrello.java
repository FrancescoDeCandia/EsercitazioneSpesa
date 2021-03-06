package it.apulia.EsercitazioneSpesa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "carrelli")
public class Carrello {

    @Id
    String carr_id;
    NotaSpesa listaSpesa;
    LocalDate data;
    Float prezzoTot;

    public Carrello(NotaSpesa listaSpesa, LocalDate data, Float prezzoTot) {
        this.listaSpesa = listaSpesa;
        this.data = data;
        this.prezzoTot = prezzoTot;
    }


    public Carrello(NotaSpesa notaSpesa) {
        this.listaSpesa= notaSpesa;
    }

    public Carrello(String carr_id, NotaSpesa listaSpesa) {
        this.carr_id = carr_id;
        this.listaSpesa = listaSpesa;
    }

    public Carrello(String carr_id, NotaSpesa listaSpesa, LocalDate setData, float prezzoTot) {
        this.carr_id = carr_id;
        this.listaSpesa = listaSpesa;
        this.data=setData;
        this.prezzoTot=prezzoTot;
    }

}

