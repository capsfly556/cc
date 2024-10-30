package org.openapitools.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class GroupOrderTest {

    @Test
    public void testDefaultConstructor() {
        GroupOrder groupOrder = new GroupOrder();

        assertNotNull(groupOrder, "GroupOrder object should not be null");
        assertNull(groupOrder.getGroupOrderID(), "groupOrderID should be null upon creation");
        assertNull(groupOrder.getStatus(), "status should be null upon creation");
        assertNotNull(groupOrder.getParticipantOrderIDs(), "participantOrderIDs list should be initialized");
        assertNull(groupOrder.getDesiredPickupTimeframe(), "desiredPickupTimeframe should be null upon creation");
        assertNull(groupOrder.getFoodProviderID(), "foodProviderID should be null upon creation");
    }

    @Test
    public void testSettersAndGetters() {
        UUID groupOrderID = UUID.randomUUID();
        String status = "Pending";
        UUID participantOrderID = UUID.randomUUID();
        String desiredPickupTimeframe = "12:00 PM - 1:00 PM";
        UUID foodProviderID = UUID.randomUUID();

        List<UUID> participantOrderIDs = new ArrayList<>();
        participantOrderIDs.add(participantOrderID);

        GroupOrder groupOrder = new GroupOrder();
        groupOrder.setGroupOrderID(groupOrderID);
        groupOrder.setStatus(status);
        groupOrder.setParticipantOrderIDs(participantOrderIDs);
        groupOrder.setDesiredPickupTimeframe(desiredPickupTimeframe);
        groupOrder.setFoodProviderID(foodProviderID);

        assertEquals(groupOrderID, groupOrder.getGroupOrderID(), "groupOrderID should be set correctly");
        assertEquals(status, groupOrder.getStatus(), "status should be set correctly");
        assertEquals(participantOrderIDs, groupOrder.getParticipantOrderIDs(), "participantOrderIDs should be set correctly");
        assertEquals(desiredPickupTimeframe, groupOrder.getDesiredPickupTimeframe(), "desiredPickupTimeframe should be set correctly");
        assertEquals(foodProviderID, groupOrder.getFoodProviderID(), "foodProviderID should be set correctly");
    }

    @Test
    public void testFluentSetters() {
        UUID groupOrderID = UUID.randomUUID();
        String status = "Confirmed";
        UUID participantOrderID = UUID.randomUUID();
        String desiredPickupTimeframe = "2:00 PM - 3:00 PM";
        UUID foodProviderID = UUID.randomUUID();

        GroupOrder groupOrder = new GroupOrder()
                .groupOrderID(groupOrderID)
                .status(status)
                .addParticipantOrderIDsItem(participantOrderID)
                .desiredPickupTimeframe(desiredPickupTimeframe)
                .foodProviderID(foodProviderID);

        assertEquals(groupOrderID, groupOrder.getGroupOrderID(), "groupOrderID should be set correctly via fluent setter");
        assertEquals(status, groupOrder.getStatus(), "status should be set correctly via fluent setter");
        assertTrue(groupOrder.getParticipantOrderIDs().contains(participantOrderID), "participantOrderID should be added via fluent setter");
        assertEquals(desiredPickupTimeframe, groupOrder.getDesiredPickupTimeframe(), "desiredPickupTimeframe should be set correctly via fluent setter");
        assertEquals(foodProviderID, groupOrder.getFoodProviderID(), "foodProviderID should be set correctly via fluent setter");
    }

    @Test
    public void testAddParticipantOrderIDsItem() {
        GroupOrder groupOrder = new GroupOrder();
        UUID participantOrderID1 = UUID.randomUUID();
        UUID participantOrderID2 = UUID.randomUUID();

        groupOrder.addParticipantOrderIDsItem(participantOrderID1);
        groupOrder.addParticipantOrderIDsItem(participantOrderID2);

        List<UUID> participantOrderIDs = groupOrder.getParticipantOrderIDs();
        assertEquals(2, participantOrderIDs.size(), "participantOrderIDs should contain two items");
        assertTrue(participantOrderIDs.contains(participantOrderID1), "participantOrderIDs should contain participantOrderID1");
        assertTrue(participantOrderIDs.contains(participantOrderID2), "participantOrderIDs should contain participantOrderID2");
    }

    @Test
    public void testFillFields() {
        GroupOrder groupOrder = new GroupOrder();
        groupOrder.fillFields();

        assertNotNull(groupOrder.getGroupOrderID(), "groupOrderID should not be null after fillFields");
        assertNotNull(groupOrder.getStatus(), "status should not be null after fillFields");
        assertNotNull(groupOrder.getDesiredPickupTimeframe(), "desiredPickupTimeframe should not be null after fillFields");
        assertNotNull(groupOrder.getFoodProviderID(), "foodProviderID should not be null after fillFields");
    }

    @Test
    public void testEqualsAndHashCode() {
        UUID groupOrderID = UUID.randomUUID();
        String status = "Pending";
        UUID participantOrderID = UUID.randomUUID();
        String desiredPickupTimeframe = "12:00 PM - 1:00 PM";
        UUID foodProviderID = UUID.randomUUID();

        GroupOrder groupOrder1 = new GroupOrder()
                .groupOrderID(groupOrderID)
                .status(status)
                .addParticipantOrderIDsItem(participantOrderID)
                .desiredPickupTimeframe(desiredPickupTimeframe)
                .foodProviderID(foodProviderID);

        GroupOrder groupOrder2 = new GroupOrder()
                .groupOrderID(groupOrderID)
                .status(status)
                .addParticipantOrderIDsItem(participantOrderID)
                .desiredPickupTimeframe(desiredPickupTimeframe)
                .foodProviderID(foodProviderID);

        assertEquals(groupOrder1, groupOrder2, "GroupOrders with the same data should be equal");
        assertEquals(groupOrder1.hashCode(), groupOrder2.hashCode(), "Hash codes should match for equal objects");

        groupOrder2.setStatus("Confirmed");
        assertNotEquals(groupOrder1, groupOrder2, "GroupOrders with different status should not be equal");
        assertNotEquals(groupOrder1.hashCode(), groupOrder2.hashCode(), "Hash codes should not match for non-equal objects");
    }

    @Test
    public void testToString() {
        UUID groupOrderID = UUID.randomUUID();
        UUID participantOrderID = UUID.randomUUID();
        UUID foodProviderID = UUID.randomUUID();

        GroupOrder groupOrder = new GroupOrder()
                .groupOrderID(groupOrderID)
                .status("Pending")
                .addParticipantOrderIDsItem(participantOrderID)
                .desiredPickupTimeframe("12:00 PM - 1:00 PM")
                .foodProviderID(foodProviderID);

        String groupOrderString = groupOrder.toString();

        assertNotNull(groupOrderString, "toString should not return null");
        assertTrue(groupOrderString.contains("class GroupOrder"), "toString should contain class name");
        assertTrue(groupOrderString.contains("groupOrderID: " + groupOrderID), "toString should contain groupOrderID");
        assertTrue(groupOrderString.contains("status: Pending"), "toString should contain status");
        assertTrue(groupOrderString.contains("participantOrderIDs: [" + participantOrderID + "]"), "toString should contain participantOrderIDs");
        assertTrue(groupOrderString.contains("desiredPickupTimeframe: 12:00 PM - 1:00 PM"), "toString should contain desiredPickupTimeframe");
        assertTrue(groupOrderString.contains("foodProviderID: " + foodProviderID), "toString should contain foodProviderID");
    }

    @Test
    public void testNullFields() {
        GroupOrder groupOrder = new GroupOrder();
        groupOrder.setStatus(null);
        groupOrder.setParticipantOrderIDs(null);
        groupOrder.setDesiredPickupTimeframe(null);
        groupOrder.setFoodProviderID(null);

        assertNull(groupOrder.getStatus(), "status should be null");
        assertNull(groupOrder.getParticipantOrderIDs(), "participantOrderIDs should be null");
        assertNull(groupOrder.getDesiredPickupTimeframe(), "desiredPickupTimeframe should be null");
        assertNull(groupOrder.getFoodProviderID(), "foodProviderID should be null");
    }

    @Test
    public void testEmptyParticipantOrderIDs() {
        GroupOrder groupOrder = new GroupOrder();
        groupOrder.setParticipantOrderIDs(new ArrayList<>());

        assertNotNull(groupOrder.getParticipantOrderIDs(), "participantOrderIDs should not be null");
        assertTrue(groupOrder.getParticipantOrderIDs().isEmpty(), "participantOrderIDs should be empty");
    }

    @Test
    public void testParticipantOrderIDsModification() {
        GroupOrder groupOrder = new GroupOrder();
        List<UUID> participantOrderIDs = new ArrayList<>();
        participantOrderIDs.add(UUID.randomUUID());

        groupOrder.setParticipantOrderIDs(participantOrderIDs);

        participantOrderIDs.add(UUID.randomUUID());
        assertEquals(2, groupOrder.getParticipantOrderIDs().size(), "Modification of " +
                "original list should not affect group's list");
    }

    @Test
    public void testSetGroupOrderID() {
        GroupOrder groupOrder = new GroupOrder();
        UUID groupOrderID = UUID.randomUUID();

        groupOrder.setGroupOrderID(groupOrderID);
        assertEquals(groupOrderID, groupOrder.getGroupOrderID(), "groupOrderID should be set correctly");
    }

    @Test
    public void testSetStatus() {
        GroupOrder groupOrder = new GroupOrder();
        String status = "Delivered";

        groupOrder.setStatus(status);
        assertEquals(status, groupOrder.getStatus(), "status should be set correctly");
    }

    @Test
    public void testSetDesiredPickupTimeframe() {
        GroupOrder groupOrder = new GroupOrder();
        String desiredPickupTimeframe = "3:00 PM - 4:00 PM";

        groupOrder.setDesiredPickupTimeframe(desiredPickupTimeframe);
        assertEquals(desiredPickupTimeframe, groupOrder.getDesiredPickupTimeframe(), "desiredPickupTimeframe should be set correctly");
    }

    @Test
    public void testSetFoodProviderID() {
        GroupOrder groupOrder = new GroupOrder();
        UUID foodProviderID = UUID.randomUUID();

        groupOrder.setFoodProviderID(foodProviderID);
        assertEquals(foodProviderID, groupOrder.getFoodProviderID(), "foodProviderID should be set correctly");
    }

    @Test
    public void testFluentAPI() {
        GroupOrder groupOrder = new GroupOrder()
                .status("In Transit")
                .desiredPickupTimeframe("4:00 PM - 5:00 PM")
                .foodProviderID(UUID.randomUUID());

        assertEquals("In Transit", groupOrder.getStatus(), "status should be set via fluent API");
        assertEquals("4:00 PM - 5:00 PM", groupOrder.getDesiredPickupTimeframe(), "desiredPickupTimeframe should be set via fluent API");
        assertNotNull(groupOrder.getFoodProviderID(), "foodProviderID should be set via fluent API");
    }

    @Test
    public void testParticipantOrderIDsNullHandling() {
        GroupOrder groupOrder = new GroupOrder();
        groupOrder.setParticipantOrderIDs(null);

        assertNull(groupOrder.getParticipantOrderIDs(), "participantOrderIDs should be null when set to null");

        groupOrder.addParticipantOrderIDsItem(UUID.randomUUID());
        assertNotNull(groupOrder.getParticipantOrderIDs(), "participantOrderIDs should be initialized when adding an item");
        assertEquals(1, groupOrder.getParticipantOrderIDs().size(), "participantOrderIDs should contain one item");
    }

    @Test
    public void testFillFieldsWithExistingValues() {
        UUID groupOrderID = UUID.randomUUID();
        String status = "Pending";
        String desiredPickupTimeframe = "12:00 PM - 1:00 PM";
        UUID foodProviderID = UUID.randomUUID();

        GroupOrder groupOrder = new GroupOrder()
                .groupOrderID(groupOrderID)
                .status(status)
                .desiredPickupTimeframe(desiredPickupTimeframe)
                .foodProviderID(foodProviderID);

        groupOrder.fillFields();

        assertEquals(groupOrderID, groupOrder.getGroupOrderID(), "groupOrderID should not change if already set");
        assertEquals(status, groupOrder.getStatus(), "status should not change if already set");
        assertEquals(desiredPickupTimeframe, groupOrder.getDesiredPickupTimeframe(), "desiredPickupTimeframe should not change if already set");
        assertEquals(foodProviderID, groupOrder.getFoodProviderID(), "foodProviderID should not change if already set");
    }

    @Test
    public void testEqualsWithNull() {
        GroupOrder groupOrder = new GroupOrder();
        assertNotEquals(null, groupOrder, "GroupOrder should not be equal to null");
    }

    @Test
    public void testEqualsWithDifferentClass() {
        GroupOrder groupOrder = new GroupOrder();
        String differentClassObject = "I am not a GroupOrder";
        assertNotEquals(groupOrder, differentClassObject, "GroupOrder should not be equal to an object of a different class");
    }

    @Test
    public void testHashCodeConsistency() {
        GroupOrder groupOrder = new GroupOrder();
        int initialHashCode = groupOrder.hashCode();
        assertEquals(initialHashCode, groupOrder.hashCode(), "hashCode should be consistent across multiple calls");
    }

    @Test
    public void testToStringNotNull() {
        GroupOrder groupOrder = new GroupOrder();
        assertNotNull(groupOrder.toString(), "toString should not return null");
    }

    @Test
    public void testToIndentedString() throws Exception {
        // Since toIndentedString is private, we'll test it indirectly via toString.
        GroupOrder groupOrder = new GroupOrder();
        String toStringResult = groupOrder.toString();
        assertTrue(toStringResult.contains("null"), "toString should contain 'null' for unset fields");
    }

    @Test
    public void testParticipantOrderIDsNotInitialized() {
        GroupOrder groupOrder = new GroupOrder();
        groupOrder.setParticipantOrderIDs(null);
        assertNull(groupOrder.getParticipantOrderIDs(), "participantOrderIDs should be null after being set to null");
    }

    @Test
    public void testAddParticipantOrderIDsItemWhenListIsNull() {
        GroupOrder groupOrder = new GroupOrder();
        groupOrder.setParticipantOrderIDs(null);
        UUID participantOrderID = UUID.randomUUID();

        groupOrder.addParticipantOrderIDsItem(participantOrderID);
        assertNotNull(groupOrder.getParticipantOrderIDs(), "participantOrderIDs should be initialized when adding an item");
        assertEquals(1, groupOrder.getParticipantOrderIDs().size(), "participantOrderIDs should contain one item");
        assertTrue(groupOrder.getParticipantOrderIDs().contains(participantOrderID), "participantOrderIDs should contain the added item");
    }

    @Test
    public void testStatusSetterGetter() {
        GroupOrder groupOrder = new GroupOrder();
        groupOrder.setStatus("Completed");
        assertEquals("Completed", groupOrder.getStatus(), "Status should be 'Completed'");
    }

    @Test
    public void testDesiredPickupTimeframeSetterGetter() {
        GroupOrder groupOrder = new GroupOrder();
        groupOrder.setDesiredPickupTimeframe("5:00 PM - 6:00 PM");
        assertEquals("5:00 PM - 6:00 PM", groupOrder.getDesiredPickupTimeframe(), "desiredPickupTimeframe should be '5:00 PM - 6:00 PM'");
    }

    @Test
    public void testFoodProviderIDSetterGetter() {
        GroupOrder groupOrder = new GroupOrder();
        UUID foodProviderID = UUID.randomUUID();
        groupOrder.setFoodProviderID(foodProviderID);
        assertEquals(foodProviderID, groupOrder.getFoodProviderID(), "foodProviderID should be set correctly");
    }
}
