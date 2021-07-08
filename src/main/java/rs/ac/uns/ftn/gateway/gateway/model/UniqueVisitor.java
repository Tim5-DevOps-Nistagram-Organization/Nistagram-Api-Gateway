package rs.ac.uns.ftn.gateway.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UniqueVisitor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String address;
    public String browser;
    public Long timestamp;

    public UniqueVisitor(String address, String browser, long timeStampMillis) {
        this.id = null;
        this.address = address;
        this.browser = browser;
        this.timestamp = timeStampMillis;
    }

}