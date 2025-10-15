package be.vdab.keuken.artikel;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@Sql("/artikels.sql")
class ArtikelControllerTest {
    private final MockMvcTester mockMvcTester;
    private final JdbcClient jdbcClient;

    ArtikelControllerTest(JdbcClient jdbcClient, MockMvcTester mockMvcTester) {
        this.mockMvcTester = mockMvcTester;
        this.jdbcClient = jdbcClient;
    }

    private int idVanTestArtikel() {
        return jdbcClient.sql("select id from artikels where naam = 'test'")
                .query(Integer.class)
                .single();
    }
    @Test
    void findByIdMetBestaandeIdVindtArtikel() {
        assertThat(mockMvcTester.get().uri("/artikels/{id}", idVanTestArtikel()))
                .hasStatusOk()
                .bodyJson().extractingPath("naam").isEqualTo("test");
    }
    @Test
    void findByIdMetOnbestaandeIdVindtGeenArtikel() {
        assertThat(mockMvcTester.get().uri("/artikels/{id}", Long.MAX_VALUE))
                .hasStatus(HttpStatus.NOT_FOUND);
    }
}