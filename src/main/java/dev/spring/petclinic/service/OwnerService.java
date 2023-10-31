package dev.spring.petclinic.service;

import dev.spring.petclinic.model.Owner;

import java.util.List;

public interface OwnerService {
    List<Owner> findAll();

    List<Owner> findByAllLastNameLike(String lastName);
    Owner save(Owner owner);
    Owner findById(Long ownerId);
}
