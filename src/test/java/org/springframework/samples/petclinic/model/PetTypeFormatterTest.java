package org.springframework.samples.petclinic.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.web.PetTypeFormatter;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class PetTypeFormatterTest {


    @Autowired
    PetService petService;

    PetTypeFormatter formatter;
    Locale locale;

    @BeforeEach
    public void CommonConfiguration(){
        formatter = new PetTypeFormatter(petService);
    }

    @Test
    public void printTest(){
        PetTypeFormatter petTypeFormatter = new PetTypeFormatter(null);

        PetType petType = new PetType();
        petType.setName("Hamster");
        String petTestName = petTypeFormatter.print(petType, Locale.ENGLISH);
        assertEquals("Hamster",petTestName);

    }

    @Test
    public void parseTest(){
        // COnfiguracion

        String name="dog";
        PetType petType = new PetType();
        petType.setName(name);

        // Ejecucion
        PetType  petType1 = null;
        try {
            petType1 = formatter.parse(name,locale);
        }catch (ParseException e){
            fail("Esto no deberia ocurrir");
            e.printStackTrace();
        }
        // Comprobacion
        assertEquals(petType.getName(),petType1.getName());
    }
}
