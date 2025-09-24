package com.cp.dasa.service;

import com.cp.dasa.model.Item;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class AdminServiceTest {

    @Autowired
    private AdminService adminService;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Deve criar um item quando tudo estiver OK")
    void  criarItemCaso1() throws  Exception {
        Item itemMock = new Item("Seringa Tubo 7''", "Seringa para coleta de sangue", "Seringa");

        Item itemSalvo = adminService.criarItem(itemMock);

        assertNotNull(itemSalvo, "O item não deve ser null após ser salvo");
        assertNotNull(itemSalvo.getID_Item(), "O ID não deve ser null");
        assertEquals("Seringa Tubo 7''", itemSalvo.getNome(), "O nome do item deve ser o mesmo");

        Item itemDB = entityManager.find(Item.class, itemSalvo.getID_Item());
        assertNotNull(itemDB, "O item deve ser encontrado no BD");
        assertEquals("Seringa Tubo 7''", itemDB.getNome());
    }

    @Test
    @DisplayName("Deve capturar uma Exception quando um dos dados for inválido")
    void  criarItemCaso2() {

    }



}