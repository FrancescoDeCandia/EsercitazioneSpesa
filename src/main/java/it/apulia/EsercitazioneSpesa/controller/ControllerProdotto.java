package it.apulia.EsercitazioneSpesa.controller;

import it.apulia.EsercitazioneSpesa.model.Prodotto;
import it.apulia.EsercitazioneSpesa.servizi.ServiziProdotto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/prodottoManager")
public class ControllerProdotto {

    private final ServiziProdotto serviziProdotto;

    @Autowired
    public ControllerProdotto(ServiziProdotto serviziProdotto) {
        this.serviziProdotto = serviziProdotto;
    }

    @GetMapping("/listaProdotti")
    ResponseEntity<List<Prodotto>> findAllProdotti(){
        return ResponseEntity.ok().body(serviziProdotto.getAllProdotti());
    }

    @PostMapping
    ResponseEntity<Prodotto> insertProdotto(@RequestBody Prodotto prodotto){
        serviziProdotto.insertProdotto(prodotto);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/prodottoManager/" + prodotto.getProd_id()).toUriString());

        log.info("Prodotto {} salvato all'interno del magazzino raggiungibile al link {} ",prodotto.getNomeProd(), uri.toString());
        return ResponseEntity.created(uri).body(prodotto);
    }

    @PutMapping("/{nomeProd}")
    ResponseEntity<?> updateProdotto(@PathVariable String nomeProd, @RequestBody Prodotto prodotto){
        serviziProdotto.updateProdotto(prodotto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{prod_id}")
    ResponseEntity<?> deleteProdotto(@PathVariable String prod_id){
        serviziProdotto.deleteProdotto(prod_id);
        return ResponseEntity.ok().build();
    }

}
