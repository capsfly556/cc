package org.openapitools.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.openapitools.model.Group;
import org.openapitools.model.GroupOrder;
import org.openapitools.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

  @Autowired private GroupRepository groupRepository;
  @Autowired private GroupOrderService groupOrderService;

  /**
   * Adds a new group to the repository and ensures that all associated group orders exist.
   *
   * @param group A {@code Group} object representing the group to be added.
   * @return The saved {@code Group} object.
   */
  public Group addGroup(Group group) {
    List<UUID> groupOrders = group.getGroupOrderIDs();
    for (UUID orderId : groupOrders) {
      if (!groupOrderService.hasGroupOrder(orderId)) {
        groupOrderService.createGroupOrder(orderId);
      }
    }
    return groupRepository.save(group);
  }

  /**
   * Retrieves all groups from the database.
   *
   * @return A {@code List} of {@code Group} objects.
   */
  public List<Group> getAllGroup() {
    return groupRepository.findAll();
  }

  /**
   * Retrieves a group by its unique ID.
   *
   * @param groupId A {@code UUID} representing the unique ID of the group.
   * @return The {@code Group} object if found, or {@code null} if no group exists with the given
   *     ID.
   */
  public Group getGroupById(UUID groupId) {
    return groupRepository.findById(groupId).orElse(null);
  }

  /**
   * Deletes a group by its unique ID.
   *
   * @param groupId A {@code UUID} representing the unique ID of the group to be deleted.
   */
  public void deleteGroupById(UUID groupId) {
    groupRepository.deleteById(groupId);
  }

  /**
   * Updates a group by its unique ID with the details from a new group object.
   *
   * @param groupId A {@code UUID} representing the unique ID of the group to update.
   * @param newGroup A {@code Group} object containing the updated details.
   */
  public void updateGroupById(UUID groupId, Group newGroup) {
    Group group = groupRepository.findById(groupId).orElse(null);
    if (group == null) {
      return;
    }
    group.setName(newGroup.getName());
    group.setGroupOrderIDs(newGroup.getGroupOrderIDs());
    group.setAdministratorID(newGroup.getAdministratorID());
    group.setParticipantIDs(newGroup.getParticipantIDs());
    groupRepository.save(group);
  }

  /**
   * Checks if a group contains a specific order ID.
   *
   * @param group A {@code Group} object representing the group to check.
   * @param orderId A {@code UUID} representing the order ID to check for.
   * @return {@code true} if the group contains the specified order ID, otherwise {@code false}.
   */
  public Boolean groupHasOrderId(Group group, UUID orderId) {
    List<UUID> groupOrderIds = group.getGroupOrderIDs();
    if (groupOrderIds == null) {
      return false;
    }
    for (UUID uuid : groupOrderIds) {
      if (uuid.equals(orderId)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Deletes a group order from a group and the associated group order in the repository.
   *
   * @param groupId A {@code UUID} representing the unique ID of the group.
   * @param orderId A {@code UUID} representing the order ID to delete.
   * @return {@code true} if the group order was successfully deleted, otherwise {@code false}.
   */
  public Boolean deleteGroupOrder(UUID groupId, UUID orderId) {
    Optional<Group> group = groupRepository.findById(groupId);
    if (!group.isPresent()) {
      return false;
    }
    if (!groupHasOrderId(group.get(), orderId)) {
      return false;
    }
    List<UUID> groupOrderIds = group.get().getGroupOrderIDs();
    groupOrderIds.remove(orderId);
    group.get().setGroupOrderIDs(groupOrderIds);

    groupOrderService.deleteGroupOrder(orderId);
    groupRepository.save(group.get());
    return true;
  }

  /**
   * Checks if a group contains a specific order ID.
   *
   * @param groupId A {@code UUID} representing the unique ID of the group.
   * @param orderId A {@code UUID} representing the order ID to check for.
   * @return {@code true} if the group contains the specified order ID, otherwise {@code false}.
   */
  public boolean hasGroupOrder(UUID groupId, UUID orderId) {
    Group group = getGroupById(groupId);
    if (group == null) {
      return false;
    }
    List<UUID> orderIdList = group.getGroupOrderIDs();
    return orderIdList.contains(orderId);
  }

  /**
   * Retrieves all group orders associated with a specific group ID.
   *
   * @param id A {@code UUID} representing the unique ID of the group.
   * @return A {@code List} of {@code GroupOrder} objects, or {@code null} if no orders are found.
   */
  public List<GroupOrder> getGroupOrdersByGroupId(UUID id) {
    Group group = getGroupById(id);
    if (group == null) {
      return null;
    }

    List<UUID> groupOrderIds = group.getGroupOrderIDs();
    if (groupOrderIds == null || groupOrderIds.isEmpty()) {
      return null;
    }

    List<GroupOrder> groupOrderList = new ArrayList<>();
    for (UUID groupOrderId : groupOrderIds) {
      GroupOrder groupOrder = groupOrderService.getGroupOrderById(groupOrderId);
      if (groupOrder == null) {
        continue;
      }
      groupOrderList.add(groupOrder);
    }
    return groupOrderList;
  }

  /**
   * Adds a group order to a group and saves it in the repository.
   *
   * @param groupId A {@code UUID} representing the unique ID of the group.
   * @param order A {@code GroupOrder} object representing the group order to be added.
   * @return {@code true} if the group order was successfully added, otherwise {@code false}.
   */
  public boolean addGroupOrder(UUID groupId, GroupOrder order) {
    Group group = getGroupById(groupId);
    if (group == null) {
      return false;
    }
    order.fillFields();
    List<UUID> groupOrders = group.getGroupOrderIDs();
    if (!groupOrders.contains(order.getGroupOrderID())) {
      groupOrders.add(order.getGroupOrderID());
    }
    group.setGroupOrderIDs(groupOrders);
    groupOrderService.createGroupOrder(order);
    return true;
  }
}
