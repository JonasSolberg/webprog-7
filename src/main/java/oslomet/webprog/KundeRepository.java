package oslomet.webprog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class KundeRepository{

    @Autowired
    private JdbcTemplate db;

    private Logger logger = LoggerFactory.getLogger(KundeRepository.class);

    public boolean lagreKunde(Kunde kunde){
        String sql = "INSERT INTO Kunde (navn, adresse) VALUES(?,?)";

        try {
            db.update(sql, kunde.getNavn(), kunde.getAdresse());
            return true;
        } catch (Exception e){
            logger.error("feil i lagreKunde: " + e);
            return false;
        }

    }
    public List<Kunde> hentAlleKunder(){
        String sql = "SELECT * FROM Kunde";
        List<Kunde> alleKunder = db.query(sql, new BeanPropertyRowMapper<>(Kunde.class));
        return alleKunder;
    }
    public void slettAlleKunder (){
        String sql = "DELETE FROM Kunde";
        db.update(sql);
    }
}
