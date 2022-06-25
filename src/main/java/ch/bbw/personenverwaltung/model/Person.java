package ch.bbw.personenverwaltung.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.util.Date;

@Entity
@ToString
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter private Long id;
    @Getter @Setter private String lastname;
    @Getter @Setter String firstname;
    @Getter @Setter char gender;
    @Getter @Setter private String email;
    @Temporal(TemporalType.DATE)
    @Getter @Setter private Date birthdate;
    @Transient
    @Getter @Setter private String officialDate;
}
