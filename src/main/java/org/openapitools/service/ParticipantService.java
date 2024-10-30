package org.openapitools.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.openapitools.repository.ParticipantRepository;
import org.openapitools.model.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class that provides methods for managing participants.
 */
@Service
public class ParticipantService {

  private final ParticipantRepository repository;

  /**
   * Constructs a ParticipantService with the given repository.
   *
   * @param repository the ParticipantRepository used for database operations
   */
  @Autowired
  public ParticipantService(ParticipantRepository repository) {
    this.repository = repository;
  }

  /**
   * Adds a new participant.
   *
   * @param participant the Participant object to be added
   * @return the saved Participant object
   */
  public Participant addParticipant(Participant participant) {
    System.out.println("Adding participant: " + participant.getName());
    return repository.save(participant);
  }

  /**
   * Retrieves all participants.
   *
   * @return a list of all Participant objects
   */
  public List<Participant> getAllParticipants() {
    System.out.println("Getting all participants");
    return repository.findAll();
  }

  /**
   * Retrieves a participant by their unique ID.
   *
   * @param participantID the UUID of the participant to retrieve
   * @return an Optional containing the Participant if found, or empty if not found
   */
  public Optional<Participant> getParticipantById(UUID participantID) {
    System.out.println("Getting participant with ID: " + participantID);
    return repository.findById(participantID);
  }

  /**
   * Updates an existing participant's details.
   *
   * @param participantID the UUID of the participant to update
   * @param participant the Participant object with updated details
   * @return the updated Participant object
   * @throws RuntimeException if the participant is not found
   */
  public Participant updateParticipant(UUID participantID, Participant participant) {
    System.out.println("Updating participant with ID: " + participantID);
    if (!repository.existsById(participantID)) {
      throw new RuntimeException("Participant not found");
    }
    participant.setParticipantID(participantID);
    return repository.save(participant);
  }

  /**
   * Deletes a participant by their unique ID.
   *
   * @param participantID the UUID of the participant to delete
   * @return true if the participant was deleted successfully, false if the participant was not found
   */
  public boolean deleteParticipant(UUID participantID) {
    System.out.println("Deleting participant with ID: " + participantID);
    if (repository.existsById(participantID)) {
      repository.deleteById(participantID);
      return true;
    }
    return false;
  }
}