package ch.bbw.personenverwaltung.model;

import lombok.Getter;
import lombok.ToString;
import org.aspectj.weaver.patterns.PerObject;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

@Entity
@ToString
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter private Long id;
    @Getter private String lastname;
    @Getter private String firstname;
    @Getter private char gender;
    @Getter private String email;
    @Temporal(TemporalType.DATE)
    @Getter private Date date;
}
