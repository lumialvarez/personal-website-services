package com.lumi.entities;

import java.time.LocalDate;
import javax.persistence.Entity;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Person extends PanacheEntity {

    public String name;
    public LocalDate birth;

    // return name as uppercase in the model
    public String getName() {
        return name.toUpperCase();
    }

    // store all names in lowercase in the DB
    public void setName(String name) {
        this.name = name.toLowerCase();
    }
}
