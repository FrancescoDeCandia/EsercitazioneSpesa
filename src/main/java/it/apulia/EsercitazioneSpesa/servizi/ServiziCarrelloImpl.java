package it.apulia.EsercitazioneSpesa.servizi;

import it.apulia.EsercitazioneSpesa.model.BilancioAnnuoCarrelli;
import it.apulia.EsercitazioneSpesa.model.Carrello;
import it.apulia.EsercitazioneSpesa.model.NotaSpesa;
import it.apulia.EsercitazioneSpesa.repository.RepCarrello;
import it.apulia.EsercitazioneSpesa.repository.RepProdotto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.ArrayList;
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
    public List<Carrello> findCarrelloByAnno(Integer anno) {
        ArrayList<Carrello> carTemp = new ArrayList<>();
        for(Carrello c: repositoryCarrello.findAll())
         if(c.getData().getYear()== anno){
             carTemp.add(c);
        }
        else log.error("Non esistono carrelli creati nel {}" +anno );
        return  carTemp;
    }

    @Override
    public void deleteCarrelloById(String carr_id) {
        repositoryCarrello.deleteById(carr_id);
    }

    @Override
    public void updateCarrello(Carrello carrello) {

        if(!carrello.getCarr_id().equals(repositoryCarrello.findCarrelloById(carrello.getCarr_id())))
        {
            Carrello carrelloTemp = new Carrello(carrello.getCarr_id(),carrello.getListaSpesa(), this.calcoloData(),this.calcoloTotaleSpesa(carrello.getListaSpesa()));
            repositoryCarrello.deleteById(carrello.getCarr_id());
            repositoryCarrello.save(carrelloTemp);

        } else log.error("Il carrello da aggiornare non è presente all'interno del db");
//        carrello.setCarr_id(repositoryCarrello.findCarrelloById(carrello.getCarr_id()).getCarr_id());
//        repositoryCarrello.deleteById(carrello.getCarr_id());
//        repositoryCarrello.save(carrello);

    }

    public BilancioAnnuoCarrelli calcoloBilancio(Integer anno) {
        ArrayList<Carrello> carrelloByAnno = new ArrayList<>(this.findCarrelloByAnno(anno));

        Integer carrelliTot = carrelloByAnno.size();
        Float totDeiTot= 0F;
        for(Carrello c:carrelloByAnno)
        {
            totDeiTot += c.getPrezzoTot();
        }
        BilancioAnnuoCarrelli bilancioAnnuoCarrelli = new BilancioAnnuoCarrelli(carrelliTot, totDeiTot);
        return bilancioAnnuoCarrelli;
    }

    LocalDate calcoloData(){
        return LocalDate.now();
    }

    long calcoloTotaleSpesa(NotaSpesa listaSpesa){
        return listaSpesa.getListaSpesa().stream()
                .mapToLong(p -> (long) (p.getQuantita() *  repositoryProdotto.findProdottoByNome(p.getNome()).getPrezzoProd())).sum();
    }

}
