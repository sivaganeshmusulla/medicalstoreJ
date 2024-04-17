package com.medicalStore.tests;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.medicalStore.controller.AdminController;
import com.medicalStore.entity.Admin;
import com.medicalStore.service.AdminService;

@ExtendWith(MockitoExtension.class)
public class AdminTests {

    @Mock
    private AdminService adminService;

    @InjectMocks
    private AdminController adminController;

    @Test
    public void testGetAllAdmins() {
        // Arrange
        List<Admin> admins = new ArrayList<>();
        admins.add(new Admin());
        admins.add(new Admin());
        when(adminService.getAllAdmins()).thenReturn(admins);

        // Act
        ResponseEntity<List<Admin>> responseEntity = adminController.getAllAdmins();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(admins, responseEntity.getBody());
    }

   
    @Test
    public void testGetAdminById() {
        // Arrange
        Long id = 1L;
        Admin admin = new Admin();
        admin.setId(id);
        admin.setUserName("John"); // Corrected line
        when(adminService.getAdminById(id)).thenReturn(admin);

        // Act
        ResponseEntity<Admin> responseEntity = adminController.getAdminById(id);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(admin, responseEntity.getBody());
    }

    @Test
    public void testAddAdmin() {
        // Arrange
        Admin admin = new Admin();
        admin.setId(1L);
        admin.setUserName("John");
        admin.setPassword("password123"); 
       
        when(adminService.saveAdmin(admin)).thenReturn(admin);

        // Act
        ResponseEntity<Admin> responseEntity = adminController.addAdmin(admin);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(admin, responseEntity.getBody());
    }

    @Test
    public void testDeleteAdmin() {
        // Arrange
        Long id = 1L;
        
        // Act
        ResponseEntity<Void> responseEntity = adminController.deleteAdmin(id);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(adminService, times(1)).deleteAdminById(id);
    }
}
