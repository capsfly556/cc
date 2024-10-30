package org.openapitools.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class GroupTest {

    @Test
    public void testDefaultConstructor() {
        Group group = new Group();

        assertNotNull(group, "Group object should not be null");
        assertNull(group.getGroupID(), "groupID should be null upon creation");
        assertNull(group.getName(), "name should be null upon creation");
        assertNotNull(group.getParticipantIDs(), "participantIDs list should be initialized");
        assertNotNull(group.getGroupOrderIDs(), "groupOrderIDs list should be initialized");
        assertNull(group.getAdministratorID(), "administratorID should be null upon creation");
    }

    @Test
    public void testParameterizedConstructor() {
        // If you have a parameterized constructor, test it here.
        // Assuming you have one, otherwise this test can be omitted.
    }

    @Test
    public void testSettersAndGetters() {
        UUID groupID = UUID.randomUUID();
        String name = "Test Group";
        UUID participantID = UUID.randomUUID();
        UUID groupOrderID = UUID.randomUUID();
        UUID administratorID = UUID.randomUUID();

        List<UUID> participantIDs = new ArrayList<>();
        participantIDs.add(participantID);

        List<UUID> groupOrderIDs = new ArrayList<>();
        groupOrderIDs.add(groupOrderID);

        Group group = new Group();
        group.setGroupID(groupID);
        group.setName(name);
        group.setParticipantIDs(participantIDs);
        group.setGroupOrderIDs(groupOrderIDs);
        group.setAdministratorID(administratorID);

        assertEquals(groupID, group.getGroupID(), "groupID should be set correctly");
        assertEquals(name, group.getName(), "name should be set correctly");
        assertEquals(participantIDs, group.getParticipantIDs(), "participantIDs should be set correctly");
        assertEquals(groupOrderIDs, group.getGroupOrderIDs(), "groupOrderIDs should be set correctly");
        assertEquals(administratorID, group.getAdministratorID(), "administratorID should be set correctly");
    }

    @Test
    public void testFluentSetters() {
        UUID groupID = UUID.randomUUID();
        String name = "Fluent Group";
        UUID participantID = UUID.randomUUID();
        UUID groupOrderID = UUID.randomUUID();
        UUID administratorID = UUID.randomUUID();

        Group group = new Group()
                .groupID(groupID)
                .name(name)
                .addParticipantIDsItem(participantID)
                .addGroupOrderIDsItem(groupOrderID)
                .administratorID(administratorID);

        assertEquals(groupID, group.getGroupID(), "groupID should be set correctly");
        assertEquals(name, group.getName(), "name should be set correctly");
        assertTrue(group.getParticipantIDs().contains(participantID), "participantID should be added to the list");
        assertTrue(group.getGroupOrderIDs().contains(groupOrderID), "groupOrderID should be added to the list");
        assertEquals(administratorID, group.getAdministratorID(), "administratorID should be set correctly");
    }

    @Test
    public void testAddParticipantIDsItem() {
        Group group = new Group();
        UUID participantID1 = UUID.randomUUID();
        UUID participantID2 = UUID.randomUUID();

        group.addParticipantIDsItem(participantID1);
        group.addParticipantIDsItem(participantID2);

        List<UUID> participantIDs = group.getParticipantIDs();
        assertEquals(2, participantIDs.size(), "participantIDs should contain two items");
        assertTrue(participantIDs.contains(participantID1), "participantIDs should contain participantID1");
        assertTrue(participantIDs.contains(participantID2), "participantIDs should contain participantID2");
    }

    @Test
    public void testAddGroupOrderIDsItem() {
        Group group = new Group();
        UUID groupOrderID1 = UUID.randomUUID();
        UUID groupOrderID2 = UUID.randomUUID();

        group.addGroupOrderIDsItem(groupOrderID1);
        group.addGroupOrderIDsItem(groupOrderID2);

        List<UUID> groupOrderIDs = group.getGroupOrderIDs();
        assertEquals(2, groupOrderIDs.size(), "groupOrderIDs should contain two items");
        assertTrue(groupOrderIDs.contains(groupOrderID1), "groupOrderIDs should contain groupOrderID1");
        assertTrue(groupOrderIDs.contains(groupOrderID2), "groupOrderIDs should contain groupOrderID2");
    }

    @Test
    public void testEquals() {
        UUID groupID = UUID.randomUUID();
        String name = "Test Group";
        UUID participantID = UUID.randomUUID();
        UUID groupOrderID = UUID.randomUUID();
        UUID administratorID = UUID.randomUUID();

        Group group1 = new Group()
                .groupID(groupID)
                .name(name)
                .addParticipantIDsItem(participantID)
                .addGroupOrderIDsItem(groupOrderID)
                .administratorID(administratorID);

        Group group2 = new Group()
                .groupID(groupID)
                .name(name)
                .addParticipantIDsItem(participantID)
                .addGroupOrderIDsItem(groupOrderID)
                .administratorID(administratorID);

        assertEquals(group1, group2, "Groups with the same data should be equal");

        group2.setName("Different Name");
        assertNotEquals(group1, group2, "Groups with different names should not be equal");
    }

    @Test
    public void testHashCode() {
        UUID groupID = UUID.randomUUID();
        String name = "Test Group";
        UUID participantID = UUID.randomUUID();
        UUID groupOrderID = UUID.randomUUID();
        UUID administratorID = UUID.randomUUID();

        Group group1 = new Group()
                .groupID(groupID)
                .name(name)
                .addParticipantIDsItem(participantID)
                .addGroupOrderIDsItem(groupOrderID)
                .administratorID(administratorID);

        Group group2 = new Group()
                .groupID(groupID)
                .name(name)
                .addParticipantIDsItem(participantID)
                .addGroupOrderIDsItem(groupOrderID)
                .administratorID(administratorID);

        assertEquals(group1.hashCode(), group2.hashCode(), "Hash codes should match for equal objects");

        group2.setName("Different Name");
        assertNotEquals(group1.hashCode(), group2.hashCode(), "Hash codes should not match for non-equal objects");
    }

    @Test
    public void testToString() {
        UUID groupID = UUID.randomUUID();
        UUID participantID = UUID.randomUUID();
        UUID groupOrderID = UUID.randomUUID();
        UUID administratorID = UUID.randomUUID();

        Group group = new Group()
                .groupID(groupID)
                .name("Test Group")
                .addParticipantIDsItem(participantID)
                .addGroupOrderIDsItem(groupOrderID)
                .administratorID(administratorID);

        String groupString = group.toString();

        assertNotNull(groupString, "toString should not return null");
        assertTrue(groupString.contains("class Group"), "toString should contain class name");
        assertTrue(groupString.contains("groupID: " + groupID), "toString should contain groupID");
        assertTrue(groupString.contains("name: Test Group"), "toString should contain name");
        assertTrue(groupString.contains("participantIDs: [" + participantID + "]"), "toString should contain participantIDs");
        assertTrue(groupString.contains("groupOrderIDs: [" + groupOrderID + "]"), "toString should contain groupOrderIDs");
        assertTrue(groupString.contains("administratorID: " + administratorID), "toString should contain administratorID");
    }

    @Test
    public void testNullFields() {
        Group group = new Group();
        group.setName(null);
        group.setParticipantIDs(null);
        group.setGroupOrderIDs(null);
        group.setAdministratorID(null);

        assertNull(group.getName(), "Name should be null");
        assertNull(group.getParticipantIDs(), "participantIDs should be null");
        assertNull(group.getGroupOrderIDs(), "groupOrderIDs should be null");
        assertNull(group.getAdministratorID(), "administratorID should be null");
    }

    @Test
    public void testEmptyParticipantIDs() {
        Group group = new Group();
        group.setParticipantIDs(new ArrayList<>());

        assertNotNull(group.getParticipantIDs(), "participantIDs should not be null");
        assertTrue(group.getParticipantIDs().isEmpty(), "participantIDs should be empty");
    }

    @Test
    public void testEmptyGroupOrderIDs() {
        Group group = new Group();
        group.setGroupOrderIDs(new ArrayList<>());

        assertNotNull(group.getGroupOrderIDs(), "groupOrderIDs should not be null");
        assertTrue(group.getGroupOrderIDs().isEmpty(), "groupOrderIDs should be empty");
    }

    @Test
    public void testParticipantIDsModification() {
        Group group = new Group();
        List<UUID> participantIDs = new ArrayList<>();
        participantIDs.add(UUID.randomUUID());

        group.setParticipantIDs(participantIDs);

        participantIDs.add(UUID.randomUUID());
        assertEquals(2, group.getParticipantIDs().size(), "Modification of original " +
                "list should not affect group's list");
    }

    @Test
    public void testGroupOrderIDsModification() {
        Group group = new Group();
        List<UUID> groupOrderIDs = new ArrayList<>();
        groupOrderIDs.add(UUID.randomUUID());

        group.setGroupOrderIDs(groupOrderIDs);

        groupOrderIDs.add(UUID.randomUUID());
        assertEquals(2, group.getGroupOrderIDs().size(), "Modification of original list" +
                " should not affect group's list");
    }

    @Test
    public void testSetGroupID() {
        Group group = new Group();
        UUID groupID = UUID.randomUUID();

        group.setGroupID(groupID);
        assertEquals(groupID, group.getGroupID(), "groupID should be set correctly");
    }

    @Test
    public void testSetName() {
        Group group = new Group();
        String name = "New Group Name";

        group.setName(name);
        assertEquals(name, group.getName(), "Name should be set correctly");
    }

    @Test
    public void testSetAdministratorID() {
        Group group = new Group();
        UUID administratorID = UUID.randomUUID();

        group.setAdministratorID(administratorID);
        assertEquals(administratorID, group.getAdministratorID(), "administratorID should be set correctly");
    }

    @Test
    public void testFluentAPI() {
        Group group = new Group()
                .name("Fluent Name")
                .administratorID(UUID.randomUUID());

        assertEquals("Fluent Name", group.getName(), "Name should be set via fluent API");
        assertNotNull(group.getAdministratorID(), "administratorID should be set via fluent API");
    }

    @Test
    public void testParticipantIDsNullHandling() {
        Group group = new Group();
        group.setParticipantIDs(null);

        assertNull(group.getParticipantIDs(), "participantIDs should be null when set to null");

        group.addParticipantIDsItem(UUID.randomUUID());
        assertNotNull(group.getParticipantIDs(), "participantIDs should be initialized when adding an item");
        assertEquals(1, group.getParticipantIDs().size(), "participantIDs should contain one item");
    }

    @Test
    public void testGroupOrderIDsNullHandling() {
        Group group = new Group();
        group.setGroupOrderIDs(null);

        assertNull(group.getGroupOrderIDs(), "groupOrderIDs should be null when set to null");

        group.addGroupOrderIDsItem(UUID.randomUUID());
        assertNotNull(group.getGroupOrderIDs(), "groupOrderIDs should be initialized when adding an item");
        assertEquals(1, group.getGroupOrderIDs().size(), "groupOrderIDs should contain one item");
    }
}
