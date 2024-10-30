package org.openapitools;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openapitools.api.ParticipantsApiController;
import org.openapitools.model.Participant;
import org.openapitools.service.ParticipantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ParticipantsApiControllerTest {
  @InjectMocks
  private ParticipantsApiController participantsApiController;

  @Mock
  private ParticipantService participantService;

  private UUID participantID;
  private Participant participant;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    participantID = UUID.randomUUID();
    participant = new Participant();
    participant.setParticipantID(participantID);
    participant.setName("John Doe");
  }

  @Test
  void testAddParticipant_Success() {
    when(participantService.addParticipant(participant)).thenReturn(participant);

    ResponseEntity<Participant> response = participantsApiController.participantsPost(participant);
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(participant, response.getBody());
  }

  @Test
  void testAddParticipant_BadRequest() {
    when(participantService.addParticipant(participant)).thenThrow(new RuntimeException("Failed to add participant"));

    ResponseEntity<Participant> response = participantsApiController.participantsPost(participant);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }

	@Test
  void testGetAllParticipants_Success() {
    List<Participant> participants = Collections.singletonList(participant);
    when(participantService.getAllParticipants()).thenReturn(participants);

    ResponseEntity<List<Participant>> response = participantsApiController.participantsGet();

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(1, response.getBody().size());
    assertEquals(participant, response.getBody().get(0));
  }

  @Test
  void testGetAllParticipants_NotFound() {
    when(participantService.getAllParticipants()).thenReturn(Collections.emptyList());

    ResponseEntity<List<Participant>> response = participantsApiController.participantsGet();
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  @Test
  void testGetParticipantById_Success() {
    when(participantService.getParticipantById(participantID)).thenReturn(Optional.of(participant));

    ResponseEntity<Participant> response = participantsApiController.participantsParticipantIDGet(participantID.toString());

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(participant, response.getBody());
  }

  @Test
  void testGetParticipantById_NotFound() {
    when(participantService.getParticipantById(participantID)).thenReturn(Optional.empty());

    ResponseEntity<Participant> response = participantsApiController.participantsParticipantIDGet(participantID.toString());

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }
}
