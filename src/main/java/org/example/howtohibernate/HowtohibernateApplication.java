package org.example.howtohibernate;

import org.example.howtohibernate.model.Owner;
import org.example.howtohibernate.model.Pet;
import org.example.howtohibernate.service.OwnerService;
import org.example.howtohibernate.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HowtohibernateApplication {

    private final OwnerService ownerService;
    private final PetService petService;

    @Autowired
    public HowtohibernateApplication(OwnerService ownerService, PetService petService) {
        this.ownerService = ownerService;
        this.petService = petService;
    }

    public static void main(String[] args) {
        SpringApplication.run(HowtohibernateApplication.class, args);
    }


    @Bean
    public CommandLineRunner demo() {
        return args -> {
            Owner owner = new Owner();
            owner.setName("John");
            owner.setEmail("john@example.com");
            owner.setPhone("123456789");
            ownerService.createOwner(owner);


            Pet pet = new Pet();
            pet.setOwner(owner);
            pet.setName("Fido");
            pet.setAnimalType("Dog");
            pet.setBreed("Golden Retriever");
            pet.setAge(3);
            pet.setVaccinations("All");
            petService.createPet(pet);


            Owner savedOwner = ownerService.getOwnerById(owner.getId());
            System.out.println("Saved Owner: " + savedOwner);


            Pet savedPet = petService.getPetById(pet.getId());
            System.out.println("Saved Pet: " + savedPet);
        };
    }
}
