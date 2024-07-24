package com.packt.cardatabase.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class OwnerRepositoryTest {
    @Autowired
    private OwnerRepository ownerRepository;

    @Test
    void saveOwner() {
        Owner owner = new Owner("Lucy", "Smith");
        ownerRepository.save(owner);
        assertThat(
            ownerRepository.findByFirstname("Lucy").isPresent()
        ).isTrue();
    }

    @Test
    void deleteOwner() {
        Owner owner = new Owner("Lucy", "Smith");
        ownerRepository.save(owner);
        ownerRepository.delete(owner);
        assertThat(
            ownerRepository.findByFirstname("Lucy").isPresent()
        ).isFalse();
    }

    @Test
    void deleteAllOwners() {
        Owner owner = new Owner("Lucy", "Smith");
        ownerRepository.save(owner);
        ownerRepository.deleteAll();
        assertThat(ownerRepository.count()).isEqualTo(0);
    }
}