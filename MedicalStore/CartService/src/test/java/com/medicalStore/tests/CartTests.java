package com.medicalStore.tests;
import static org.junit


import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.medicalStore.controller.CartServiceController;
import com.medicalStore.dao.ClientDao;
import com.medicalStore.dao.MedicineDao;
import com.medicalStore.dto.ClientDto;
import com.medicalStore.dto.MedicineDto;
import com.medicalStore.entity.CartServiceEntity;
import com.medicalStore.exception.ResourceNotFoundException;
import com.medicalStore.service.CartService;

public class CartTests {

    @InjectMocks
    private CartServiceController cartServiceController;

    @Mock
    private CartService cartService;

    @Mock
    private ClientDao clientDao;

    @Mock
    private MedicineDao medicineDao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddCart() {
        CartServiceEntity cartEntity = new CartServiceEntity(1L, 1L, 1L);
        when(cartService.saveCart(cartEntity)).thenReturn(cartEntity);

        ResponseEntity<CartServiceEntity> response = cartServiceController.addAdmin(cartEntity);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(cartEntity, response.getBody());
    }

    @Test
    public void testGetCart() {
        CartServiceEntity cartEntity = new CartServiceEntity(1L, 1L, 1L);
        when(cartService.getCart(1L)).thenReturn(cartEntity);

        CartServiceEntity retrievedCart = cartServiceController.getCart(1L);

        assertEquals(cartEntity, retrievedCart);
    }

    @Test
    public void testGetClientById() {
        ClientDto clientDto = new ClientDto();
        clientDto.setId(1L);
        when(clientDao.getClientById(1L)).thenReturn(ResponseEntity.ok(clientDto));

        ResponseEntity<ClientDto> response = cartServiceController.getClientById(1L);

        assertEquals(clientDto, response.getBody());
    }

    @Test
    public void testGetClientById_NotFound() {
        when(clientDao.getClientById(1L)).thenReturn(null);

        try {
            cartServiceController.getClientById(1L);
        } catch (ResourceNotFoundException ex) {
            assertEquals("Client Not Found", ex.getMessage());
        }
    }

    @Test
    public void testGetMedicineById() {
        MedicineDto medicineDto = new MedicineDto();
        medicineDto.setId(1L);
        when(medicineDao.getMedicineById(1L)).thenReturn(ResponseEntity.ok(medicineDto));

        ResponseEntity<MedicineDto> response = cartServiceController.getMedicineById(1L);

        assertEquals(medicineDto, response.getBody());
    }

    @Test
    public void testGetMedicineById_NotFound() {
        when(medicineDao.getMedicineById(1L)).thenReturn(null);

        try {
            cartServiceController.getMedicineById(1L);
        } catch (ResourceNotFoundException ex) {
            assertEquals("medicine Not Found", ex.getMessage());
        }
    }
}
