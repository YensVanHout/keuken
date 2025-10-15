package be.vdab.keuken.artikel;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ArtikelRepository extends JpaRepository<Artikel, Long> {
    List<Artikel> findByNaamContainingOrderByNaam(String naam);

    @Query(
        "from Artikel where verkoopprijs - aankoopprijs >= :minimum order by verkoopprijs"
    )
    List<Artikel> findByWinstMinstens(BigDecimal minimum);
}
