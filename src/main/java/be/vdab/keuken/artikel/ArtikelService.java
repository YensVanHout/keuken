package be.vdab.keuken.artikel;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ArtikelService {

    private final ArtikelRepository artikelRepository;

    public ArtikelService(ArtikelRepository artikelRepository) {
        this.artikelRepository = artikelRepository;
    }

    Optional<Artikel> findById(long id) {
        return artikelRepository.findById(id);
    }

    List<Artikel> findByNaamContaining(String naam) {
        return artikelRepository.findByNaamContainingOrderByNaam(naam);
    }

    List<Artikel> findByMinimumWinst(BigDecimal minimum) {
        return artikelRepository.findByWinstMinstens(minimum);
    }

    @Transactional
    long create(NieuwArtikel nieuwArtikel) {
        var artikel = new Artikel(
            nieuwArtikel.naam(),
            nieuwArtikel.aankoopprijs(),
            nieuwArtikel.verkoopprijs()
        );
        return artikelRepository.save(artikel).getId();
    }
}
