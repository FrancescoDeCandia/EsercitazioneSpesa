package it.apulia.EsercitazioneSpesa.servizi;

import it.apulia.EsercitazioneSpesa.model.Carrello;
import it.apulia.EsercitazioneSpesa.model.NotaSpesa;
import it.apulia.EsercitazioneSpesa.model.Prodotto;
import it.apulia.EsercitazioneSpesa.repository.RepCarrello;
import it.apulia.EsercitazioneSpesa.repository.RepProdotto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class ServiziCarrelloImpl implements ServiziCarrello{
    private final RepCarrello repositoryCarrello;
    private final RepProdotto repositoryProdotto;

    @Autowired
    public ServiziCarrelloImpl(RepCarrello repositoryCarrello, RepProdotto repositoryProdotto) {
        this.repositoryCarrello = repositoryCarrello;
        this.repositoryProdotto = repositoryProdotto;
    }


    @Override
    public List<Carrello> getAllCarrelli() {
        return repositoryCarrello.findAll();
    }


    @Override
    public Carrello creaCarrello(NotaSpesa listaSpesa) {


        Carrello carrello = new Carrello(listaSpesa, this.calcoloData(), (float) this.calcoloTotaleSpesa(listaSpesa));
        return repositoryCarrello.save(carrello);
    }

    @Override
    public Carrello findCarrelloById(String carr_id) {
        return repositoryCarrello.findCarrelloById(carr_id);
    }

    @Override
    public void deleteCarrelloById(String carr_id) {
        repositoryCarrello.deleteById(carr_id);
    }

    @Override
    public void updateCarrello(Carrello carrello) {

        if(!carrello.getCarr_id().equals(repositoryCarrello.findCarrelloById(carrello.getCarr_id())))
        {
            String idTemp = carrello.getCarr_id();
            NotaSpesa notaTemp = carrello.getListaSpesa();
            repositoryCarrello.deleteById(carrello.getCarr_id());
            this.creaCarrelloUpdate(idTemp, notaTemp);

        } else log.error("Il carrello da aggiornare non è presente all'interno del db");
//        carrello.setCarr_id(repositoryCarrello.findCarrelloById(carrello.getCarr_id()).getCarr_id());
//        repositoryCarrello.deleteById(carrello.getCarr_id());
//        repositoryCarrello.save(carrello);

    }

    Date calcoloData(){
        return new Date();
    }

    Long calcoloTotaleSpesa(NotaSpesa listaSpesa){
        return listaSpesa.getListaSpesa().stream()
                .mapToLong(p -> (long) (p.getQuantita() *  repositoryProdotto.findProdottoByNome(p.getNome()).getPrezzoProd())).sum();
    }

    public Carrello creaCarrelloUpdate(String id,NotaSpesa listaSpesa) {


        Carrello carrello = new Carrello(id, listaSpesa, this.calcoloData(), (float) this.calcoloTotaleSpesa(listaSpesa));
        return repositoryCarrello.save(carrello);
    }
}
