package org.openapitools;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.openapitools.model.FoodProvider;
import org.openapitools.model.MenuItem;
import org.openapitools.repository.FoodproviderRepository;
import org.openapitools.service.FoodproviderService;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class foodproviderServiceTest {
    @Mock
    private FoodproviderRepository repository;

    @InjectMocks
    private FoodproviderService service;

    private FoodProvider foodProvider;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        foodProvider = new FoodProvider();
        foodProvider.setName("TestFoodProvider");
        foodProvider.setPhoneNumber("1234567890");
        foodProvider.setHoursOfOperation("8hours");
        foodProvider.setLocation("San Francisco");

        MenuItem menuItem = new MenuItem();

        menuItem.setName("Fried chicken");
        menuItem.setImage("https://www.allrecipes.com/recipe/8805/crispy-fried-chicken/");
        menuItem.setDescription("Crispy golden fried chicken");
        menuItem.setCost(new BigDecimal("11.00"));


        menuItem.setFoodProvider(foodProvider);

        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(menuItem);
        foodProvider.setMenu(menuItems);

    }
    @Test
    void testFindById() {
        UUID foodProviderId = UUID.randomUUID();
        Mockito.when(repository.findById(foodProviderId)).thenReturn(Optional.of(foodProvider));

        Optional<FoodProvider> found = service.findById(foodProviderId.toString());
        assertTrue(found.isPresent());
        assertEquals(found.get().getName(),"TestFoodProvider");
    }

    @Test
    void testDeleteFoodProvider() {
        UUID foodProviderId = UUID.randomUUID();
        Mockito.when(repository.existsById(foodProviderId)).thenReturn(true);

        Boolean deleted = service.deleteFoodProvider(foodProviderId.toString());
        Mockito.verify(repository, Mockito.times(1)).deleteById(foodProviderId);
        assertTrue(deleted);
    }

    @Test
    void testDeleteFoodProviderNotFound() {
        UUID foodProviderId = UUID.randomUUID();
        Mockito.when(repository.existsById(foodProviderId)).thenReturn(false);

        Boolean deleted = service.deleteFoodProvider(foodProviderId.toString());
        Mockito.verify(repository, Mockito.times(0)).deleteById(foodProviderId);
        assertFalse(deleted);
    }

    @Test
    void testCheckExistsNameIdNot(){
        String name = "Burger King";
        UUID foodProviderId = UUID.randomUUID();

        Mockito.when(repository.existsByNameAndFoodProviderIDNot(name, foodProviderId)).thenReturn(true);
        Boolean exists = service.checkExistsNameIdNot(name, foodProviderId.toString());
        assertTrue(exists);
        Mockito.verify(repository, Mockito.times(1)).existsByNameAndFoodProviderIDNot(name, foodProviderId);

    }

    @Test
    void testCheckExistsNameIdNotNotFound(){
        String name = "Burger King";
        UUID foodProviderId = UUID.randomUUID();

        Mockito.when(repository.existsByNameAndFoodProviderIDNot(name, foodProviderId)).thenReturn(false);
        Boolean notFund = service.checkExistsNameIdNot(name, foodProviderId.toString());
        Mockito.verify(repository, Mockito.times(1)).existsByNameAndFoodProviderIDNot(name, foodProviderId);
        assertFalse(notFund);
    }

    @Test
    void testCheckExistsPhoneNumberIdNot(){
        String phoneNumber = "123445";
        UUID foodProviderId = UUID.randomUUID();

        Mockito.when(repository.existsByNameAndFoodProviderIDNot(phoneNumber, foodProviderId)).thenReturn(true);
        Boolean exists = service.checkExistsNameIdNot(phoneNumber, foodProviderId.toString());
        Mockito.verify(repository, Mockito.times(1))
                .existsByNameAndFoodProviderIDNot(phoneNumber, foodProviderId);
        assertTrue(exists);
    }

    @Test
    void testCheckExistsPhoneNumberIdNotNotFound(){
        String phoneNumber = "123445";
        UUID foodProviderId = UUID.randomUUID();

        Mockito.when(repository.existsByNameAndFoodProviderIDNot(phoneNumber, foodProviderId)).thenReturn(false);
        Boolean notFund = service.checkExistsNameIdNot(phoneNumber, foodProviderId.toString());
        Mockito.verify(repository, Mockito.times(1))
                .existsByNameAndFoodProviderIDNot(phoneNumber, foodProviderId);
        assertFalse(notFund);
    }

    @Test
    void testUpdateFoodProvider(){
        UUID foodProviderId = UUID.randomUUID();

        //created updated food provider
        FoodProvider updatedProvider = new FoodProvider();
        updatedProvider.setName("New Name");
        updatedProvider.setLocation("New Location");
        updatedProvider.setPhoneNumber("987654321");
        updatedProvider.setHoursOfOperation("10 AM - 10 PM");

        MenuItem updatedmenuItem = new MenuItem();

        updatedmenuItem.setName("Fried fries");
        updatedmenuItem.setImage("https://www.allrecipes.com/fried-fries/");
        updatedmenuItem.setDescription("Crispy golden fried fries");
        updatedmenuItem.setCost(new BigDecimal("7.00"));


        updatedmenuItem.setFoodProvider(foodProvider);

        List<MenuItem> updatedmenuItems = new ArrayList<>();
        updatedmenuItems.add(updatedmenuItem);
        updatedProvider.setMenu(updatedmenuItems);

        Mockito.when(repository.findById(foodProviderId)).thenReturn(Optional.of(foodProvider));
        Mockito.when(repository.save(foodProvider)).thenReturn(foodProvider);

        boolean updated = service.updateFoodProvider(foodProviderId.toString(), updatedProvider);
        assertTrue(updated);
        assertEquals("New Name", foodProvider.getName());
        assertEquals("New Location", foodProvider.getLocation());
        assertEquals("987654321", foodProvider.getPhoneNumber());
        assertEquals("10 AM - 10 PM", foodProvider.getHoursOfOperation());
        assertEquals(1, foodProvider.getMenu().size());
        Mockito.verify(repository, Mockito.times(1)).save(foodProvider);

    }

    @Test
    void testUpdateFoodProviderNotFound(){
        UUID foodProviderId = UUID.randomUUID();

        //created updated food provider
        FoodProvider updatedProvider = new FoodProvider();
        updatedProvider.setName("New Name");
        updatedProvider.setLocation("New Location");
        updatedProvider.setPhoneNumber("987654321");
        updatedProvider.setHoursOfOperation("10 AM - 10 PM");

        MenuItem updatedmenuItem = new MenuItem();

        updatedmenuItem.setName("Fried fries");
        updatedmenuItem.setImage("https://www.allrecipes.com/fried-fries/");
        updatedmenuItem.setDescription("Crispy golden fried fries");
        updatedmenuItem.setCost(new BigDecimal("7.00"));


        updatedmenuItem.setFoodProvider(foodProvider);

        List<MenuItem> updatedmenuItems = new ArrayList<>();
        updatedmenuItems.add(updatedmenuItem);
        updatedProvider.setMenu(updatedmenuItems);

        Mockito.when(repository.findById(foodProviderId)).thenReturn(Optional.empty());
        boolean updated = service.updateFoodProvider(foodProviderId.toString(), updatedProvider);
        assertFalse(updated);
    }

    @Test
    void testGetAllFoodProvidersWhenEmpty() {

        Mockito.when(repository.findAll()).thenReturn(Collections.emptyList());
        List<FoodProvider> foodProviders = service.getAllFoodProviders();
        Mockito.verify(repository, Mockito.times(1)).findAll();

        assertTrue(foodProviders.isEmpty());
    }

    @Test
    void testGetAllFoodProvidersWithResults() {

        List<FoodProvider> mockFoodProviders = Arrays.asList(foodProvider, foodProvider);
        Mockito.when(repository.findAll()).thenReturn(mockFoodProviders);
        List<FoodProvider> foodProviders = service.getAllFoodProviders();
        Mockito.verify(repository, Mockito.times(1)).findAll();
        assertEquals(2, foodProviders.size());
        assertEquals("TestFoodProvider", foodProviders.get(0).getName());
        assertEquals("TestFoodProvider", foodProviders.get(1).getName());
    }



    @Test
    void testAddFoodProviderExistsByName() {

        Mockito.when(repository.existsByName(foodProvider.getName())).thenReturn(true);
        boolean result = service.addFoodProvider(foodProvider);
        Mockito.verify(repository, Mockito.times(0)).save(foodProvider);

    }
    @Test
    void testAddFoodProviderExistsByPhoneNumber() {
        Mockito.when(repository.existsByPhoneNumber(foodProvider.getPhoneNumber())).thenReturn(true);
        boolean result = service.addFoodProvider(foodProvider);
        Mockito.verify(repository, Mockito.times(0)).save(foodProvider);
    }

    @Test
    void testAddFoodProvider() {
        Mockito.when(repository.existsByPhoneNumber(foodProvider.getPhoneNumber())).thenReturn(false);
        Mockito.when(repository.existsByName(foodProvider.getName())).thenReturn(false);
        boolean result = service.addFoodProvider(foodProvider);
        Mockito.verify(repository, Mockito.times(1)).save(foodProvider);
    }
}
