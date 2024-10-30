package org.openapitools;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.openapitools.api.FoodprovidersApiController;
import org.openapitools.model.FoodProvider;
import org.openapitools.model.MenuItem;
import org.openapitools.service.FoodproviderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class foodproviderAPITest {
    @Mock
    private FoodproviderService service;
    @InjectMocks
    private FoodprovidersApiController controller;

    private FoodProvider foodProvider;
    private MenuItem menuItem1;
    private MenuItem menuItem2;
    private List<MenuItem> menuItemList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        foodProvider = new FoodProvider();
        foodProvider.setName("TestFoodProvider");
        foodProvider.setPhoneNumber("1234567890");
        foodProvider.setHoursOfOperation("8hours");
        foodProvider.setLocation("San Francisco");


        menuItem1 = new MenuItem();
        menuItem1.setName("Burger");
        menuItem1.setImage("https://news.bk.com/media-assets/menu-item-and-campaign-images");
        menuItem1.setDescription("A delicious beef burger");
        menuItem1.setCost(new BigDecimal("11.00"));
        menuItem1.setFoodProvider(foodProvider);

        menuItem2 = new MenuItem();
        menuItem2.setName("Fried chicken");
        menuItem2.setImage("https://www.allrecipes.com/recipe/8805/crispy-fried-chicken/");
        menuItem2.setDescription("Crispy golden fried chicken");
        menuItem2.setCost(new BigDecimal("11.00"));
        menuItem2.setFoodProvider(foodProvider);

        menuItemList.add(menuItem1);
        menuItemList.add(menuItem2);
        foodProvider.setMenu(menuItemList);
    }






    @Test
    void testFoodprovidersPost_Success() {


        Mockito.when(service.addFoodProvider(Mockito.any(FoodProvider.class)))
                .thenReturn(true);
        ResponseEntity<Void> response = controller.foodprovidersPost(foodProvider);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Mockito.verify(service, Mockito.times(1)).addFoodProvider(Mockito.any(FoodProvider.class));
    }


    @Test
    void testFoodprovidersPost_Failure_MissingMenu() {
        FoodProvider foodProvider = new FoodProvider();
        foodProvider.setName("Test Provider");
        foodProvider.setPhoneNumber("1234567890");

        ResponseEntity<Void> response = controller.foodprovidersPost(foodProvider);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testFoodprovidersPost_Failure_Duplicate() {
        Mockito.when(service.addFoodProvider(Mockito.any(FoodProvider.class)))
                .thenReturn(false);
        ResponseEntity<Void> response = controller.foodprovidersPost(foodProvider);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    //delete api

    @Test
    void testFoodprovidersFoodProviderIdDelete_Success() {
        UUID foodProviderId = UUID.randomUUID();

        Mockito.when(service.findById(foodProviderId.toString()))
                .thenReturn(Optional.of(foodProvider));
        Mockito.when(service.deleteFoodProvider(foodProviderId.toString()))
                .thenReturn(true);

        ResponseEntity<Void> response = controller.foodprovidersFoodProviderIdDelete(foodProviderId.toString());

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        Mockito.verify(service, Mockito.times(1)).deleteFoodProvider(foodProviderId.toString());
    }

    @Test
    void testFoodprovidersFoodProviderIdDelete_NotFound() {
        UUID foodProviderId = UUID.randomUUID();

        Mockito.when(service.findById(foodProviderId.toString()))
                .thenReturn(Optional.empty());

        ResponseEntity<Void> response = controller.foodprovidersFoodProviderIdDelete(foodProviderId.toString());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Mockito.verify(service, Mockito.times(0)).deleteFoodProvider(foodProviderId.toString());
    }

    @Test
    void testFoodprovidersFoodProviderIdDelete_InternalError() {
        UUID foodProviderId = UUID.randomUUID();

        Mockito.when(service.findById(foodProviderId.toString()))
                .thenReturn(Optional.of(foodProvider));
        Mockito.when(service.deleteFoodProvider(foodProviderId.toString()))
                .thenReturn(false);

        ResponseEntity<Void> response = controller.foodprovidersFoodProviderIdDelete(foodProviderId.toString());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Mockito.verify(service, Mockito.times(1)).deleteFoodProvider(foodProviderId.toString());
    }

    //test get id
    @Test
    void testFoodprovidersFoodProviderIdGet_Success() {
        UUID foodProviderId = UUID.randomUUID();

        Mockito.when(service.findById(foodProviderId.toString()))
                .thenReturn(Optional.of(foodProvider));

        ResponseEntity<FoodProvider> response = controller.foodprovidersFoodProviderIdGet(foodProviderId.toString());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(foodProvider, response.getBody());
        Mockito.verify(service, Mockito.times(1)).findById(foodProviderId.toString());
    }

    @Test
    void testFoodprovidersFoodProviderIdGet_NotFound() {
        UUID foodProviderId = UUID.randomUUID();

        Mockito.when(service.findById(foodProviderId.toString()))
                .thenReturn(Optional.empty());

        ResponseEntity<FoodProvider> response = controller.foodprovidersFoodProviderIdGet(foodProviderId.toString());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    //put api test

    @Test
    void testFoodprovidersFoodProviderIdPut_Success() {
        UUID foodProviderId = UUID.randomUUID();
        FoodProvider updatedProvider = new FoodProvider();


        Mockito.when(service.updateFoodProvider(foodProviderId.toString(), updatedProvider))
                .thenReturn(true);

        ResponseEntity<Void> response = controller.foodprovidersFoodProviderIdPut(foodProviderId.toString(), updatedProvider);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testFoodprovidersFoodProviderIdPut_Conflict() {
        UUID foodProviderId = UUID.randomUUID();
        FoodProvider updatedProvider = new FoodProvider();
        updatedProvider.setName("Conflict Name");

        Mockito.when(service.updateFoodProvider(foodProviderId.toString(), updatedProvider))
                .thenReturn(false);
        Mockito.when(service.checkExistsNameIdNot(updatedProvider.getName(), foodProviderId.toString()))
                .thenReturn(true);

        ResponseEntity<Void> response = controller.foodprovidersFoodProviderIdPut(foodProviderId.toString(), updatedProvider);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    void testFoodprovidersFoodProviderIdPut_NotFound() {
        UUID foodProviderId = UUID.randomUUID();
        FoodProvider updatedProvider = new FoodProvider();
        Mockito.when(service.updateFoodProvider(foodProviderId.toString(), updatedProvider))
                .thenReturn(false);
        Mockito.when(service.checkExistsNameIdNot(updatedProvider.getName(), foodProviderId.toString()))
                .thenReturn(false);
        ResponseEntity<Void> response =
                controller.foodprovidersFoodProviderIdPut(foodProviderId.toString(), updatedProvider);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    //get

    @Test
    void testFoodprovidersGet_Success() {
        List<FoodProvider> foodProviders = List.of(foodProvider,foodProvider);

        Mockito.when(service.getAllFoodProviders())
                .thenReturn(foodProviders);

        ResponseEntity<List<FoodProvider>> response = controller.foodprovidersGet();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(foodProviders, response.getBody());
    }

    @Test
    void testFoodprovidersGet_NotFound() {
        Mockito.when(service.getAllFoodProviders())
                .thenReturn(Collections.emptyList());
        ResponseEntity<List<FoodProvider>> response = controller.foodprovidersGet();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
