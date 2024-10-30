package org.openapitools.api;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Generated;
import javax.validation.Valid;
import org.openapitools.model.Participant;
import org.openapitools.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * API Controller class responsible for handling HTTP requests related to participants.
 */
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2024-10-02T19:11:02.971027-04:00[America/Toronto]",
    comments = "Generator version: 7.8.0")
@Controller
@RequestMapping("${openapi.groupGrub.base-path:}")
public class ParticipantsApiController implements ParticipantsApi {

  private final NativeWebRequest request;
  private final ParticipantService participantService;

  /**
   * Constructs the ParticipantsApiController with the provided service and request object.
   *
   * @param request the NativeWebRequest object for accessing request metadata
   * @param participantService the service handling participant operations
   */
  @Autowired
  public ParticipantsApiController(
      NativeWebRequest request, ParticipantService participantService) {
    this.request = request;
    this.participantService = participantService;
  }

  /**
   * Provides access to the NativeWebRequest object, if available.
   *
   * @return an Optional containing the NativeWebRequest object
   */
  @Override
  public Optional<NativeWebRequest> getRequest() {
    return Optional.ofNullable(request);
  }

  /**
   * Adds a new participant to the system.
   *
   * @param participant the Participant object to be created
   * @return a ResponseEntity containing the saved Participant and an HTTP status of 204, or 400 on failure
   */
  @Override
  @PostMapping(value = "/participants", produces = {"application/json"})
  public ResponseEntity<Participant> participantsPost(@Valid @RequestBody Participant participant) {
    try {
      Participant savedParticipant = participantService.addParticipant(participant);
      return new ResponseEntity<>(savedParticipant, HttpStatus.CREATED);
    } catch (Exception e) { 
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  /**
   * Retrieves all participants from the system.
   *
   * @return a ResponseEntity containing a list of participants and an HTTP status of 200, or 404 if no participants exist
   */
  @Override
  @GetMapping(value = "/participants", produces = {"application/json"})
  public ResponseEntity<List<Participant>> participantsGet() {
      List<Participant> participants = participantService.getAllParticipants();
      if (participants.isEmpty()) {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<>(participants, HttpStatus.OK);
  }

  /**
   * Deletes a participant by their ID.
   *
   * @param participantID the ID of the participant to be deleted
   * @return a ResponseEntity with an HTTP status of 204 if deleted, or 404 if the participant does not exist
   */
  @Override
  @DeleteMapping(value = "/participants/{participantID}", produces = {"application/json"})
  public ResponseEntity<Participant> participantsParticipantIDDelete(@PathVariable String participantID) {
    boolean deleted = participantService.deleteParticipant(UUID.fromString(participantID));
    if (deleted) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  /**
   * Retrieves a participant by their ID.
   *
   * @param participantID the ID of the participant
   * @return a ResponseEntity containing the Participant and an HTTP status of 200 if found, or 404 if not found
   */
  @Override
  @GetMapping(value = "/participants/{participantID}", produces = {"application/json"})
  public ResponseEntity<Participant> participantsParticipantIDGet(@PathVariable String participantID) {
    Optional<Participant> participant = participantService.getParticipantById(UUID.fromString(participantID));
    if (participant.isPresent()) {
        return new ResponseEntity<>(participant.get(), HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  /**
   * Updates a participant's information by their ID.
   *
   * @param participantID the ID of the participant to update
   * @param participant the updated Participant object
   * @return a ResponseEntity containing the updated Participant and an HTTP status of 200, or 404 if the participant does not exist, or 400 on error
   */
  @Override
  @PutMapping(value = "/participants/{participantID}", consumes = {"application/json"})
  public ResponseEntity<Participant> participantsParticipantIDPut(String participantID, @Valid @RequestBody Participant participant) {
      try {
        Participant updatedParticipant = participantService.updateParticipant(UUID.fromString(participantID), participant);
        return new ResponseEntity<>(updatedParticipant, HttpStatus.OK);
      } catch (RuntimeException e) {
          if (e.getMessage().equals("Participant not found")) {
              return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
          return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
  }
}