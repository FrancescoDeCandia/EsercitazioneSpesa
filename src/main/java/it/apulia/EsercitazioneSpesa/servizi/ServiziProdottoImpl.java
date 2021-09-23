package it.apulia.EsercitazioneSpesa.servizi;

import it.apulia.EsercitazioneSpesa.model.Prodotto;
import it.apulia.EsercitazioneSpesa.repository.RepCarrello;
import it.apulia.EsercitazioneSpesa.repository.RepProdotto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if(this.findProdottoByNome(prodotto.getNomeProd()).equals(prodotto))
            repositoryProdotto.save(prodotto);
        else
            log.error("Prodotto da aggiornare {} non presente all'interno del db", prodotto.getNomeProd());
    }

    @Override
    public void deleteProdotto(String prod_id) {
        repositoryProdotto.deleteById(prod_id);
    }

    @Override
    public Prodotto findProdottoByNome(String nomeProd) {
        return repositoryProdotto.findProdottoByNome(nomeProd);
    }
}
