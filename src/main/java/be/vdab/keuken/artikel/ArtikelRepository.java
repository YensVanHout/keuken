package be.vdab.keuken.artikel;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ArtikelRepository extends JpaRepository<Artikel, Long> {
    List<Artikel> findByNaamContainingOrderByNaam(String naam);

    @Query(
        "select a from Artikel a where a.verkoopprijs - a.aankoopprijs >= ?1 order by a.verkoopprijs"
    )
    List<Artikel> findByVerkoopprijs(BigDecimal minimumWinst);
}
