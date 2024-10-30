package org.openapitools.service;

import java.util.UUID;
import org.openapitools.model.GroupOrder;
import org.openapitools.repository.GroupOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupOrderService {

  @Autowired private GroupOrderRepository repository;

  /**
   * Retrieves a GroupOrder by its unique ID.
   *
   * @param id A {@code UUID} representing the unique ID of the group order.
   * @return The {@code GroupOrder} object if found, or {@code null} if no order exists with the
   *     given ID.
   */
  public GroupOrder getGroupOrderById(UUID id) {
    return repository.findById(id).orElse(null);
  }

  /**
   * Checks if a GroupOrder with the specified ID exists.
   *
   * @param id A {@code UUID} representing the unique ID of the group order.
   * @return {@code true} if a group order exists with the specified ID, otherwise {@code false}.
   */
  public boolean hasGroupOrder(UUID id) {
    return repository.findById(id).isPresent();
  }

  /**
   * Updates an existing GroupOrder with the specified ID using the details from a new GroupOrder.
   *
   * @param id A {@code UUID} representing the unique ID of the group order to update.
   * @param newOrder A {@code GroupOrder} object containing the updated details.
   * @return {@code true} if the group order was successfully updated, otherwise {@code false}.
   */
  public boolean updateGroupOrder(UUID id, GroupOrder newOrder) {
    GroupOrder order = getGroupOrderById(id);
    if (order == null || newOrder == null) {
      return false;
    }

    order.setParticipantOrderIDs(newOrder.getParticipantOrderIDs());
    order.setStatus(newOrder.getStatus());
    order.setFoodProviderID(newOrder.getFoodProviderID());
    order.setDesiredPickupTimeframe(newOrder.getDesiredPickupTimeframe());

    repository.save(order);
    return true;
  }

  /**
   * Creates a new GroupOrder and saves it to the repository.
   *
   * @param order A {@code GroupOrder} object representing the group order to be created.
   * @return {@code true} indicating the group order was successfully created.
   */
  public boolean createGroupOrder(GroupOrder order) {
    order.fillFields();
    repository.save(order);
    return true;
  }

  /**
   * Creates a new GroupOrder with a specified ID and saves it to the repository.
   *
   * @param id A {@code UUID} representing the unique ID for the new group order.
   * @return {@code true} indicating the group order was successfully created.
   */
  public boolean createGroupOrder(UUID id) {
    GroupOrder order = new GroupOrder();
    order.fillFields();
    order.setGroupOrderID(id);
    repository.save(order);
    return true;
  }

  /**
   * Deletes a GroupOrder with the specified ID from the repository.
   *
   * @param id A {@code UUID} representing the unique ID of the group order to be deleted.
   * @return {@code true} if the group order was successfully deleted, otherwise {@code false}.
   */
  public boolean deleteGroupOrder(UUID id) {
    if (!repository.existsById(id)) {
      return false;
    }
    repository.deleteById(id);
    return true;
  }
}
