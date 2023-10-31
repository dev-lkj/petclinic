package dev.spring.petclinic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "types")
public class PetType extends BaseEntity {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @JoinColumn(referencedColumnName = "type_id")
//    private int id;


    @Builder
    public PetType(Long id, String name) {
        super(id);
        this.name = name;
    }

    @Column(name ="name")
    private String name;


    @Override
    public String toString() {
        return name;
    }
}
