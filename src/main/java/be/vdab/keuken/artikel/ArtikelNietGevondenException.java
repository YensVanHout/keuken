package be.vdab.keuken.artikel;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ArtikelNietGevondenException extends RuntimeException {
    ArtikelNietGevondenException() {
        super("Artikel niet gevonden.");
    }
}
