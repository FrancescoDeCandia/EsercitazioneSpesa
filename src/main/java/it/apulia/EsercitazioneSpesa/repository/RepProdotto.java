package it.apulia.EsercitazioneSpesa.repository;

import it.apulia.EsercitazioneSpesa.model.Prodotto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RepProdotto extends MongoRepository<Prodotto, String> {
    @Query("{ 'nomeProd' :?0 }")
    Prodotto findProdottoByNome (String nomeProd);

    @Query("{ 'prod_id' :?0 }")
    Prodotto findProdottoById (String prod_id);
}
