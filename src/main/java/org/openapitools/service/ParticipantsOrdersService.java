package org.openapitools.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.openapitools.model.Participant;
import org.openapitools.model.ParticipantOrder;
import org.openapitools.repository.ParticipantRepository;
import org.openapitools.repository.ParticipantsOrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class that provides methods for managing participant orders.
 */
@Service
public class ParticipantsOrdersService {

  private final ParticipantRepository participantRepository;
  private final ParticipantsOrdersRepository repository;

  /**
   * Constructs a ParticipantsOrdersService with the given repositories.
   *
   * @param repository the ParticipantsOrdersRepository used for order-related operations
   * @param participantRepository the ParticipantRepository used for participant-related operations
   */
  @Autowired
  public ParticipantsOrdersService(ParticipantsOrdersRepository repository, ParticipantRepository participantRepository) {
    this.repository = repository;
    this.participantRepository = participantRepository;
  }

  /**
   * Retrieves all orders for a specific participant.
   *
   * @param participantID the UUID of the participant whose orders are to be retrieved
   * @return a list of ParticipantOrder objects for the given participant
   */
  public List<ParticipantOrder> getParticipantOrders(UUID participantID) {
    return repository.findByParticipantId(participantID);
  }

  /**
   * Retrieves a specific order for a participant.
   *
   * @param participantID the UUID of the participant
   * @param participantOrderID the UUID of the order to retrieve
   * @return an Optional containing the ParticipantOrder if found, or empty if not found
   */
  public Optional<ParticipantOrder> getParticipantOrder(UUID participantID, UUID participantOrderID) {
    return repository.findById(participantOrderID);
  }

  /**
   * Deletes a specific order for a participant.
   *
   * @param participantID the UUID of the participant
   * @param participantOrderID the UUID of the order to delete
   * @return true if the order was deleted successfully, false if the order was not found
   */
  public boolean deleteParticipantOrder(UUID participantID, UUID participantOrderID) {
    Optional<ParticipantOrder> order = repository.findById(participantOrderID);
    if (order.isPresent()) {
      repository.delete(order.get());
      return true;
    }
    return false;
  }

  /**
   * Updates an existing order for a participant.
   *
   * @param participantID the UUID of the participant
   * @param participantOrderID the UUID of the order to update
   * @param updatedOrder the updated ParticipantOrder object
   * @return the updated ParticipantOrder object
   * @throws RuntimeException if the participant or order is not found
   */
  public ParticipantOrder updateParticipantOrder(UUID participantID, UUID participantOrderID, ParticipantOrder updatedOrder) {
    Optional<ParticipantOrder> existingOrderOpt = repository.findById(participantOrderID);
    if (!existingOrderOpt.isPresent()) {
      throw new RuntimeException("Participant order not found");
    }
    
    Optional<Participant> participantOpt = participantRepository.findById(participantID);
    if (!participantOpt.isPresent()) {
      throw new RuntimeException("Participant not found");
    }

    ParticipantOrder existingOrder = existingOrderOpt.get();
    Participant participant = participantOpt.get();
    
    if (existingOrder.getParticipant() == null) {
      existingOrder.setParticipant(participant);
    }

    existingOrder.setComments(updatedOrder.getComments());
    existingOrder.setMenuItemIDs(updatedOrder.getMenuItemIDs());
    
    return repository.save(existingOrder);
  }

  /**
   * Creates a new order for a participant.
   *
   * @param participantID the UUID of the participant
   * @param participantOrder the ParticipantOrder object to be created
   * @throws RuntimeException if the participant is not found or if an order with the same ID already exists
   */
  public void createParticipantOrder(UUID participantID, ParticipantOrder participantOrder) {
    Optional<Participant> participantOptional = participantRepository.findById(participantID);

    if (participantOptional.isPresent()) {
      participantOrder.setParticipant(participantOptional.get());
    } else {
      throw new RuntimeException("Participant not found with ID: " + participantID);
    }

    if (participantOrder.getParticipantOrderID() != null) {
      if (repository.existsById(participantOrder.getParticipantOrderID())) {
        throw new RuntimeException("ParticipantOrder with ID: " 
          + participantOrder.getParticipantOrderID() + " already exists.");
      }
    } else {
      participantOrder.setParticipantOrderID(UUID.randomUUID());
    }

    repository.save(participantOrder);
  }
}