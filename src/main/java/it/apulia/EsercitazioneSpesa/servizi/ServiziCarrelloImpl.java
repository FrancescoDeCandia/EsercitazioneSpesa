package it.apulia.EsercitazioneSpesa.servizi;

import it.apulia.EsercitazioneSpesa.model.Carrello;
import it.apulia.EsercitazioneSpesa.model.NotaSpesa;
import it.apulia.EsercitazioneSpesa.repository.RepCarrello;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;


@Service
public class ServiziCarrelloImpl implements ServiziCarrello{
    private final RepCarrello repositoryCarrello;

    @Autowired
    public ServiziCarrelloImpl(RepCarrello repositoryCarrello) {
        this.repositoryCarrello = repositoryCarrello;
    }


    @Override
    public List<Carrello> getAllCarrelli() {
        return repositoryCarrello.findAll();
    }


    @Override
    public Carrello creaCarrello(List<NotaSpesa> listaSpesa) {

        Date data = new Date();

        long prezzoTot;

        int quantita = listaSpesa.stream()
                .mapToInt(p -> p.getQuantita())
                .sum();


        prezzoTot = listaSpesa.stream()
                .mapToLong(p -> (long) (p.getQuantita() * p.getProdotto().getPrezzoProd()))
                .sum();

        Carrello carrello = new Carrello(listaSpesa, data, (float) prezzoTot);
        return repositoryCarrello.save(carrello);
    }

    @Override
    public Carrello findCarrelloById(String carr_id) {
        return null;
    }
}
