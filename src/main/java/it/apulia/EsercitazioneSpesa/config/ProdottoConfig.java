package it.apulia.EsercitazioneSpesa.config;


import it.apulia.EsercitazioneSpesa.model.Carrello;
import it.apulia.EsercitazioneSpesa.model.NotaSpesa;
import it.apulia.EsercitazioneSpesa.model.Prodotto;
import it.apulia.EsercitazioneSpesa.repository.RepCarrello;
import it.apulia.EsercitazioneSpesa.repository.RepProdotto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class ProdottoConfig {

    @Bean
    CommandLineRunner commandLineRunner(RepProdotto prodRepository, RepCarrello carRepository) {
        return args -> {

            Prodotto prodotto1 = new Prodotto("1", "libro", 15.50F);
            Prodotto prodotto2 = new Prodotto("2", "quaderno", 25F);

            List<NotaSpesa> lista1 = new ArrayList();
            lista1.add(new NotaSpesa(prodotto1, 3));
            lista1.add(new NotaSpesa(prodotto2, 5));

            List<NotaSpesa> lista2 = new ArrayList();
            lista2.add(new NotaSpesa(prodotto1, 1));
            lista2.add(new NotaSpesa(prodotto2, 2));


            Date data1 = new Date();
            Date data2 = new Date();

            Carrello carrello1 = new Carrello("carr1",lista1, data1, 171.5F);
            Carrello carrello2 = new Carrello("carr2",lista2, data2, 65.50F);

            List<Carrello> temp1 =  new ArrayList<>();
            temp1.add(carrello1);
            temp1.add(carrello2);
            carRepository.deleteAll();
            carRepository.saveAll(
                    temp1
            );

            List<Prodotto> temp =  new ArrayList<>();
            temp.add(prodotto1);
            temp.add(prodotto2);
            prodRepository.deleteAll();
            prodRepository.saveAll(
                    temp
            );

};}}
