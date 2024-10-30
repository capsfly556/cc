package org.openapitools.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openapitools.model.Group;
import org.openapitools.model.GroupOrder;
import org.openapitools.repository.GroupRepository;

class GroupServiceTest {

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private GroupOrderService groupOrderService;

    @InjectMocks
    private GroupService groupService;

    private Group group;
    private UUID groupId;
    private UUID orderId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        groupId = UUID.randomUUID();
        orderId = UUID.randomUUID();
        group = new Group();
        group.setGroupOrderIDs(new ArrayList<>());
    }

    @Test
    void testAddGroup() {
        when(groupRepository.save(any(Group.class))).thenReturn(group);

        groupService.addGroup(group);


        verify(groupRepository, times(1)).save(group);
    }

    @Test
    void testGetAllGroup() {
        List<Group> groupList = new ArrayList<>();
        groupList.add(group);
        when(groupRepository.findAll()).thenReturn(groupList);

        List<Group> result = groupService.getAllGroup();

        assertEquals(1, result.size());
        verify(groupRepository, times(1)).findAll();
    }

    @Test
    void testGetGroupById() {
        when(groupRepository.findById(groupId)).thenReturn(Optional.of(group));

        Group result = groupService.getGroupById(groupId);

        assertNotNull(result);
        verify(groupRepository, times(1)).findById(groupId);
    }

    @Test
    void testGetGroupByIdReturnsNull() {
        when(groupRepository.findById(groupId)).thenReturn(Optional.empty());

        Group result = groupService.getGroupById(groupId);

        assertNull(result);
        verify(groupRepository, times(1)).findById(groupId);
    }

    @Test
    void testDeleteGroupById() {
        groupService.deleteGroupById(groupId);

        verify(groupRepository, times(1)).deleteById(groupId);
    }

    @Test
    void testUpdateGroupById() {
        Group newGroup = new Group();
        when(groupRepository.findById(groupId)).thenReturn(Optional.of(group));

        groupService.updateGroupById(groupId, newGroup);

        verify(groupRepository, times(1)).save(any(Group.class));
    }

    @Test
    void testUpdateGroupByIdGroupNotFound() {
        when(groupRepository.findById(groupId)).thenReturn(Optional.empty());

        groupService.updateGroupById(groupId, new Group());

        verify(groupRepository, times(0)).save(any());
    }

    @Test
    void testGroupHasOrderId() {
        List<UUID> groupOrderIDs = new ArrayList<>();
        groupOrderIDs.add(orderId);
        group.setGroupOrderIDs(groupOrderIDs);

        Boolean result = groupService.groupHasOrderId(group, orderId);

        assertTrue(result);
    }

    @Test
    void testGroupHasOrderIdReturnsFalse() {
        Boolean result = groupService.groupHasOrderId(group, orderId);

        assertFalse(result);
    }

    @Test
    void testDeleteGroupOrder() {
        List<UUID> groupOrderIDs = new ArrayList<>();
        groupOrderIDs.add(orderId);
        group.setGroupOrderIDs(groupOrderIDs);
        when(groupRepository.findById(groupId)).thenReturn(Optional.of(group));
        when(groupOrderService.hasGroupOrder(orderId)).thenReturn(true);

        Boolean result = groupService.deleteGroupOrder(groupId, orderId);

        assertTrue(result);
        verify(groupOrderService, times(1)).deleteGroupOrder(orderId);
    }

    @Test
    void testDeleteGroupOrderGroupNotFound() {
        when(groupRepository.findById(groupId)).thenReturn(Optional.empty());

        Boolean result = groupService.deleteGroupOrder(groupId, orderId);

        assertFalse(result);
    }

    @Test
    void testHasGroupOrder() {
        List<UUID> groupOrderIDs = new ArrayList<>();
        groupOrderIDs.add(orderId);
        group.setGroupOrderIDs(groupOrderIDs);
        when(groupRepository.findById(groupId)).thenReturn(Optional.of(group));

        boolean result = groupService.hasGroupOrder(groupId, orderId);

        assertTrue(result);
    }

    @Test
    void testHasGroupOrderReturnsFalse() {
        when(groupRepository.findById(groupId)).thenReturn(Optional.empty());

        boolean result = groupService.hasGroupOrder(groupId, orderId);

        assertFalse(result);
    }

    @Test
    void testGetGroupOrdersByGroupId() {
        List<UUID> groupOrderIDs = new ArrayList<>();
        groupOrderIDs.add(orderId);
        group.setGroupOrderIDs(groupOrderIDs);
        when(groupRepository.findById(groupId)).thenReturn(Optional.of(group));
        GroupOrder groupOrder = new GroupOrder();
        when(groupOrderService.getGroupOrderById(orderId)).thenReturn(groupOrder);

        List<GroupOrder> result = groupService.getGroupOrdersByGroupId(groupId);

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testAddGroupOrder() {
        List<UUID> groupOrderIDs = new ArrayList<>();
        group.setGroupOrderIDs(groupOrderIDs);
        when(groupRepository.findById(groupId)).thenReturn(Optional.of(group));
        GroupOrder groupOrder = new GroupOrder();
        groupOrder.setGroupOrderID(orderId);

        boolean result = groupService.addGroupOrder(groupId, groupOrder);

        assertTrue(result);
        verify(groupOrderService, times(1)).createGroupOrder(groupOrder);
    }
}
