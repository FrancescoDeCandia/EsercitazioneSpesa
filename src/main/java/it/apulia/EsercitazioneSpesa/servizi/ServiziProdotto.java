package it.apulia.EsercitazioneSpesa.servizi;

import it.apulia.EsercitazioneSpesa.model.Prodotto;

import java.util.List;

public interface ServiziProdotto {

    List<Prodotto> getAllProdotti();
    void insertProdotto(Prodotto prodotto);
    void updateProdotto(Prodotto prodotto);
    void deleteProdotto(String prod_id);
    Prodotto findProdottoByNome(String nomeProd);

}
