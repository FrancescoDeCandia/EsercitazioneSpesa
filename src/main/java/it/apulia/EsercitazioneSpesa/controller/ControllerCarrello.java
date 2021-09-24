package it.apulia.EsercitazioneSpesa.controller;

import it.apulia.EsercitazioneSpesa.model.Carrello;
import it.apulia.EsercitazioneSpesa.model.NotaSpesa;
import it.apulia.EsercitazioneSpesa.servizi.ServiziCarrello;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/carrelloManager")
public class ControllerCarrello {
    private final ServiziCarrello serviziCarrello;

    @Autowired
    public ControllerCarrello(ServiziCarrello serviziCarrello) {
        this.serviziCarrello = serviziCarrello;
    }

    @GetMapping("/listaCarrelli")
    ResponseEntity<List<Carrello>> findAllCarrelli(){
        return ResponseEntity.ok().body(serviziCarrello.getAllCarrelli());
    }

    @PostMapping
    ResponseEntity<Carrello> insertCarrello(@RequestBody NotaSpesa listaSpesa){
        Carrello carrello = serviziCarrello.creaCarrello(listaSpesa);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/carrelloManager/" + carrello.getCarr_id()).toUriString());

        log.info("Il carrello {} creato raggiungibile al link {} ", carrello.getCarr_id(), uri.toString());
        return ResponseEntity.created(uri).body(carrello);
    }

    @DeleteMapping("/{carr_id}")
    public ResponseEntity<?> deleteCarrello(@PathVariable String carr_id){
        serviziCarrello.deleteCarrelloById(carr_id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{carr_id}")
    public  ResponseEntity<?> updateCarrello(@PathVariable String carr_id, @RequestBody Carrello carrello){
        serviziCarrello.updateCarrello(carrello);
        return ResponseEntity.ok().body(carrello);
    }

    @GetMapping("/listaCarrelliPerAnno/{anno}")
    public ResponseEntity<?> findCarrelloByAnno(@PathVariable Integer anno){

        return ResponseEntity.ok().body(serviziCarrello.calcoloBilancio(anno));
    }

//    @GetMapping("/ricerca/{anno}")
//    ResponseEntity<List<Carrello>> getAllCarrelliByAnno(@PathVariable Integer anno) {
//        List<> serviziCarrello.getAllCarrelli()
//
//
//        return ResponseEntity.ok().body(bookService.getLibriByAnno(anno));
//    }


}
