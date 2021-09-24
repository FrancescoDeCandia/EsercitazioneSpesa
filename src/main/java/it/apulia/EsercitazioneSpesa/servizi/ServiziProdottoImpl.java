package it.apulia.EsercitazioneSpesa.servizi;

import it.apulia.EsercitazioneSpesa.model.NotaSpesa;
import it.apulia.EsercitazioneSpesa.model.Prodotto;
import it.apulia.EsercitazioneSpesa.repository.RepProdotto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ServiziProdottoImpl  implements ServiziProdotto{
    private final RepProdotto repositoryProdotto;

    @Autowired
    public ServiziProdottoImpl(RepProdotto repositoryProdotto) {
        this.repositoryProdotto = repositoryProdotto;
    }

    @Override
    public List<Prodotto> getAllProdotti() {
        return repositoryProdotto.findAll();
    }

    @Override
    public void insertProdotto(Prodotto prodotto) {
        repositoryProdotto.save(prodotto);
    }

    @Override
    public void updateProdotto(Prodotto prodotto) {

        if(!prodotto.getProd_id().equals(repositoryProdotto.findProdottoById(prodotto.getProd_id())))
        {
            Prodotto prodTemp = new Prodotto(prodotto.getProd_id(), prodotto.getNomeProd(), prodotto.getPrezzoProd());
            repositoryProdotto.deleteById(prodotto.getProd_id());
            this.insertProdotto(prodTemp);

        } else log.error("Il prodotto da aggiornare non è presente all'interno del db");}

    @Override
    public void deleteProdotto(String prod_id) {
        repositoryProdotto.deleteById(prod_id);
    }

    @Override
    public Prodotto findProdottoByNome(String nomeProd) {
        return repositoryProdotto.findProdottoByNome(nomeProd);
    }

    @Override
    public Optional<Prodotto> findProdottoById(String prod_id) {
        return repositoryProdotto.findById(prod_id);
    }
}
