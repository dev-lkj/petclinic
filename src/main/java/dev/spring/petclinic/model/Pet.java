package dev.spring.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "pets")
@Setter
public class Pet extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name= "type_id")
    private PetType petType;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    public Pet(Long id) {
        super(id);
    }

    public Pet(String name) {
        this.name=name;
    }

    public Pet() {

    }

//    @Override
//    public String toString() {
//        return name;
//    }
}