package org.openapitools.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;
import org.openapitools.model.GroupOrder;
import org.openapitools.repository.GroupOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;

/**
 * Unit tests for GroupOrderService
 */
@SpringBootTest
@ComponentScan(basePackages = "org.openapitools")
public class GroupOrderServiceTest {

    @Autowired
    private GroupOrderService groupOrderService;

    @MockBean
    private GroupOrderRepository groupOrderRepository;

    private UUID existingId;
    private UUID nonExistingId;
    private GroupOrder existingGroupOrder;
    private GroupOrder newGroupOrder;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        existingId = UUID.randomUUID();
        nonExistingId = UUID.randomUUID();

        existingGroupOrder = new GroupOrder();
        existingGroupOrder.setGroupOrderID(existingId);
        existingGroupOrder.setStatus("Pending");
        existingGroupOrder.setDesiredPickupTimeframe("12:00 PM - 1:00 PM");
        existingGroupOrder.setFoodProviderID(UUID.randomUUID());

        newGroupOrder = new GroupOrder();
        newGroupOrder.setStatus("Confirmed");
        newGroupOrder.setDesiredPickupTimeframe("1:00 PM - 2:00 PM");
        newGroupOrder.setFoodProviderID(UUID.randomUUID());

        // Mocking repository behavior
        when(groupOrderRepository.findById(existingId)).thenReturn(Optional.of(existingGroupOrder));
        when(groupOrderRepository.findById(nonExistingId)).thenReturn(Optional.empty());
        when(groupOrderRepository.existsById(existingId)).thenReturn(true);
        when(groupOrderRepository.existsById(nonExistingId)).thenReturn(false);
    }

    @Test
    public void testGetGroupOrderById_existingId() {
        GroupOrder result = groupOrderService.getGroupOrderById(existingId);

        assertNotNull(result, "GroupOrder should not be null for existing ID");
        assertEquals(existingId, result.getGroupOrderID(), "GroupOrder ID should match the existing ID");
        verify(groupOrderRepository, times(1)).findById(existingId);
    }

    @Test
    public void testGetGroupOrderById_nonExistingId() {
        GroupOrder result = groupOrderService.getGroupOrderById(nonExistingId);

        assertNull(result, "GroupOrder should be null for non-existing ID");
        verify(groupOrderRepository, times(1)).findById(nonExistingId);
    }

    @Test
    public void testHasGroupOrder_existingId() {
        boolean exists = groupOrderService.hasGroupOrder(existingId);

        assertTrue(exists, "GroupOrder should exist for existing ID");
        verify(groupOrderRepository, times(1)).findById(existingId);
    }

    @Test
    public void testHasGroupOrder_nonExistingId() {
        boolean exists = groupOrderService.hasGroupOrder(nonExistingId);

        assertFalse(exists, "GroupOrder should not exist for non-existing ID");
        verify(groupOrderRepository, times(1)).findById(nonExistingId);
    }

    @Test
    public void testUpdateGroupOrder_existingId() {
        boolean updated = groupOrderService.updateGroupOrder(existingId, newGroupOrder);

        assertTrue(updated, "Update should return true for existing ID");
        ArgumentCaptor<GroupOrder> captor = ArgumentCaptor.forClass(GroupOrder.class);
        verify(groupOrderRepository, times(1)).save(captor.capture());

        GroupOrder savedOrder = captor.getValue();
        assertEquals(existingId, savedOrder.getGroupOrderID(), "GroupOrder ID should remain unchanged");
        assertEquals(newGroupOrder.getStatus(), savedOrder.getStatus(), "Status should be updated");
        assertEquals(newGroupOrder.getDesiredPickupTimeframe(), savedOrder.getDesiredPickupTimeframe(), "DesiredPickupTimeframe should be updated");
        assertEquals(newGroupOrder.getFoodProviderID(), savedOrder.getFoodProviderID(), "FoodProviderID should be updated");
    }

    @Test
    public void testUpdateGroupOrder_nonExistingId() {
        boolean updated = groupOrderService.updateGroupOrder(nonExistingId, newGroupOrder);

        assertFalse(updated, "Update should return false for non-existing ID");
        verify(groupOrderRepository, never()).save(any(GroupOrder.class));
    }

    @Test
    public void testUpdateGroupOrder_nullNewOrder() {
        boolean updated = groupOrderService.updateGroupOrder(existingId, null);

        assertFalse(updated, "Update should return false when newOrder is null");
        verify(groupOrderRepository, never()).save(any(GroupOrder.class));
    }

    @Test
    public void testCreateGroupOrder_withOrder() {
        GroupOrder orderToCreate = new GroupOrder();
        orderToCreate.setStatus("Pending");
        orderToCreate.setDesiredPickupTimeframe("2:00 PM - 3:00 PM");
        orderToCreate.setFoodProviderID(UUID.randomUUID());

        boolean created = groupOrderService.createGroupOrder(orderToCreate);

        assertTrue(created, "Create should return true");
        verify(groupOrderRepository, times(1)).save(orderToCreate);
        assertNotNull(orderToCreate.getGroupOrderID(), "GroupOrderID should be set after fillFields");
        assertEquals("Pending", orderToCreate.getStatus(), "Status should remain unchanged if already set");
    }

    @Test
    public void testCreateGroupOrder_withNullOrder() {
        assertThrows(NullPointerException.class, () -> {
            groupOrderService.createGroupOrder((GroupOrder) null);
        }, "Create should throw NullPointerException when order is null");
    }

    @Test
    public void testCreateGroupOrder_withId() {
        UUID newId = UUID.randomUUID();

        boolean created = groupOrderService.createGroupOrder(newId);

        assertTrue(created, "Create should return true");
        ArgumentCaptor<GroupOrder> captor = ArgumentCaptor.forClass(GroupOrder.class);
        verify(groupOrderRepository, times(1)).save(captor.capture());

        GroupOrder savedOrder = captor.getValue();
        assertEquals(newId, savedOrder.getGroupOrderID(), "GroupOrderID should be set to the provided ID");
        assertNotNull(savedOrder.getStatus(), "Status should be set after fillFields");
    }

    @Test
    public void testDeleteGroupOrder_existingId() {
        boolean deleted = groupOrderService.deleteGroupOrder(existingId);

        assertTrue(deleted, "Delete should return true for existing ID");
        verify(groupOrderRepository, times(1)).existsById(existingId);
        verify(groupOrderRepository, times(1)).deleteById(existingId);
    }

    @Test
    public void testDeleteGroupOrder_nonExistingId() {
        boolean deleted = groupOrderService.deleteGroupOrder(nonExistingId);

        assertFalse(deleted, "Delete should return false for non-existing ID");
        verify(groupOrderRepository, times(1)).existsById(nonExistingId);
        verify(groupOrderRepository, never()).deleteById(nonExistingId);
    }
}
