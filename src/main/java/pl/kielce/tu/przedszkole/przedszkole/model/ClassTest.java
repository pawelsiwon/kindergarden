package pl.kielce.tu.przedszkole.przedszkole.model;

import lombok.Data;

import javax.persistence.*;

//@Data
//@Entity
public class ClassTest {
//    @Id
//    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "classtest_seq")
    private Long id;

//    @OneToMany(mappedBy = "classTest")
    private Kid kid;


}
