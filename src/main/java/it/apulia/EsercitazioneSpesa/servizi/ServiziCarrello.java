package it.apulia.EsercitazioneSpesa.servizi;

import it.apulia.EsercitazioneSpesa.model.BilancioAnnuoCarrelli;
import it.apulia.EsercitazioneSpesa.model.Carrello;
import it.apulia.EsercitazioneSpesa.model.NotaSpesa;

import java.util.List;

public interface ServiziCarrello {

    List<Carrello> getAllCarrelli();
    Carrello creaCarrello(NotaSpesa listaSpesa);
    Carrello findCarrelloById(String carr_id);
    List<Carrello> findCarrelloByAnno(Integer anno);

    void deleteCarrelloById(String carr_id);
    void updateCarrello(Carrello carrello);
    public BilancioAnnuoCarrelli calcoloBilancio(Integer anno);
}
