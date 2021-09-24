package it.apulia.EsercitazioneSpesa.repository;

import it.apulia.EsercitazioneSpesa.model.Carrello;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RepCarrello  extends MongoRepository<Carrello, String> {

    @Query("{ 'carr_id' : ?0 }")
    Carrello findCarrelloById(String carr_id);

    @Query("{ 'data' : ?0 }")
    List<Carrello> findCarrelloByData(Date data);
}
