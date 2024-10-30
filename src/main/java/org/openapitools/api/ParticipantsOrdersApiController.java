package org.openapitools.api;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Generated;
import javax.validation.Valid;
import org.openapitools.model.ParticipantOrder;
import org.openapitools.service.ParticipantsOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * API Controller class that handles HTTP requests related to participant orders.
 */
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2024-10-02T19:11:02.971027-04:00[America/Toronto]",
    comments = "Generator version: 7.8.0")
@Controller
@RequestMapping("${openapi.groupGrub.base-path:}")
public class ParticipantsOrdersApiController implements ParticipantsOrdersApi {

    private final ParticipantsOrdersService participantsOrdersService;

    /**
     * Constructs the ParticipantsOrdersApiController with the given service.
     *
     * @param participantsOrdersService the service handling participant order operations
     */
    @Autowired
    public ParticipantsOrdersApiController(ParticipantsOrdersService participantsOrdersService) {
      this.participantsOrdersService = participantsOrdersService;
    }

    /**
     * Retrieves all orders for a specific participant.
     *
     * @param participantID the ID of the participant
     * @return a list of participant orders and an HTTP status of 200 if found, 404 if no orders exist
     */
    @Override
    @GetMapping("/participants/{participantID}/orders")
    public ResponseEntity<List<ParticipantOrder>> participantsParticipantIDOrdersGet(
      @PathVariable("participantID") String participantID) {
      List<ParticipantOrder> orders = participantsOrdersService.getParticipantOrders(UUID.fromString(participantID));
      if (orders.isEmpty()) {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<>(orders, HttpStatus.ACCEPTED);
    }

    /**
     * Deletes a specific participant order.
     *
     * @param participantID the ID of the participant
     * @param participantOrderID the ID of the order to delete
     * @return 204 if the order is successfully deleted, or 404 if it doesn't exist
     */
    @Override
    @DeleteMapping("/participants/{participantID}/orders/{participantOrderID}")
    public ResponseEntity<Void> participantsParticipantIDOrdersParticipantOrderIDDelete(
      @PathVariable("participantID") String participantID,
      @PathVariable("participantOrderID") String participantOrderID) {
      boolean deleted = participantsOrdersService.deleteParticipantOrder(UUID.fromString(participantID), UUID.fromString(participantOrderID));
      if (deleted) {
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    /**
     * Retrieves a specific participant order by its ID.
     *
     * @param participantID the ID of the participant
     * @param participantOrderID the ID of the order
     * @return the participant order and an HTTP status of 200 if found, 404 if not found
     */
    @Override
    @GetMapping("/participants/{participantID}/orders/{participantOrderID}")
    public ResponseEntity<ParticipantOrder> participantsParticipantIDOrdersParticipantOrderIDGet(
      @PathVariable("participantID") String participantID,
      @PathVariable("participantOrderID") String participantOrderID) {
      Optional<ParticipantOrder> order = participantsOrdersService.getParticipantOrder(UUID.fromString(participantID), UUID.fromString(participantOrderID));
      if (!order.isPresent()) {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<>(order.get(), HttpStatus.OK);
    }

    /**
     * Updates a specific participant order.
     *
     * @param participantID the ID of the participant
     * @param participantOrderID the ID of the order to update
     * @param participantOrder the updated ParticipantOrder object
     * @return the updated ParticipantOrder and an HTTP status of 200 if updated, 404 if not found, 400 on errors
     */
    @Override
    @PutMapping("/participants/{participantID}/orders/{participantOrderID}")
    public ResponseEntity<ParticipantOrder> participantsParticipantIDOrdersParticipantOrderIDPut(
      @PathVariable("participantID") String participantID,
      @PathVariable("participantOrderID") String participantOrderID,
      @Valid @RequestBody ParticipantOrder participantOrder) {
      try {
        ParticipantOrder updatedParticipantOrder = participantsOrdersService.updateParticipantOrder(UUID.fromString(participantID), 
          UUID.fromString(participantOrderID), participantOrder);
        return new ResponseEntity<>(updatedParticipantOrder, HttpStatus.OK);
      } catch (RuntimeException e) {
          if (e.getMessage().equals("Participant order not found")) {
              return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
          return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
    }

    /**
     * Creates a new participant order.
     *
     * @param participantID the ID of the participant
     * @param participantOrder the ParticipantOrder object to be created
     * @return 201 if the order is created successfully, 400 on errors
     */
    @Override
    @PostMapping("/participants/{participantID}/orders")
    public ResponseEntity<Void> participantsParticipantIDOrdersPost(
      @PathVariable("participantID") String participantID,
      @Valid @RequestBody ParticipantOrder participantOrder) {
      try {
        participantsOrdersService.createParticipantOrder(UUID.fromString(participantID), participantOrder);
        return new ResponseEntity<>(HttpStatus.CREATED);
      } catch (RuntimeException e) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
    }
}