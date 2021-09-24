package it.apulia.EsercitazioneSpesa.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class NotaSpesa {
   List<ProdottoSpesa> listaSpesa;

    public NotaSpesa() {
        listaSpesa = new ArrayList<ProdottoSpesa>();
    }
}
