package org.springframework.samples.petclinic.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Bill;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class BillServiceTest {

    @Autowired
    BillService billService;

    @Test
    public void save(){
        // Configuracion
        Bill b = new Bill();
        b.setTotal(10);
        b.setConcept("Prueba");
        // Ejecucion
        int facturas = billService.getAllBills().size();
        billService.saveBill(b);
        // Comprobacion
        assertEquals(facturas+1,billService.getAllBills().size());

    }
}
