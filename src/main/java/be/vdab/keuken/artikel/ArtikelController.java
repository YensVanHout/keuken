package be.vdab.keuken.artikel;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("artikels")
public class ArtikelController {

    private final ArtikelService artikelService;

    public ArtikelController(ArtikelService artikelService) {
        this.artikelService = artikelService;
    }

    @GetMapping("{id}")
    public Artikel getArtikel(@PathVariable long id) {
        return artikelService
            .findById(id)
            .orElseThrow(ArtikelNietGevondenException::new);
    }

    @GetMapping(params = "naam")
    public List<Artikel> searchArtikels(
        @RequestParam(required = false) String naam
    ) {
        return artikelService.findByNaamContaining(naam);
    }

    @GetMapping(params = "minimumVerkoopprijs")
    public List<Artikel> findByVerkoopprijs(
        @RequestParam BigDecimal minimumVerkoopprijs
    ) {
        return artikelService.findByVerkoopprijs(minimumVerkoopprijs);
    }

    @PostMapping
    public long createArtikel(@RequestBody NieuwArtikel nieuwArtikel) {
        return artikelService.create(nieuwArtikel);
    }
}
