package org.openapitools;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openapitools.model.Participant;
import org.openapitools.repository.ParticipantRepository;
import org.openapitools.service.ParticipantService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ParticipantServiceTests {

    @Mock
    private ParticipantRepository repository;

    @InjectMocks
    private ParticipantService participantService;

    private Participant participant;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        participant = new Participant();
        participant.setParticipantID(UUID.randomUUID());
        participant.setName("Test Participant");
    }

    @Test
    void addParticipant_shouldSaveParticipant() {
        when(repository.save(any(Participant.class))).thenReturn(participant);

        Participant savedParticipant = participantService.addParticipant(participant);

        assertNotNull(savedParticipant);
        assertEquals(participant.getName(), savedParticipant.getName());
        verify(repository, times(1)).save(participant);
    }

    @Test
    void getAllParticipants_shouldReturnListOfParticipants() {
        List<Participant> participants = new ArrayList<>();
        participants.add(participant);
        when(repository.findAll()).thenReturn(participants);

        List<Participant> result = participantService.getAllParticipants();

        assertEquals(1, result.size());
        assertEquals(participant.getName(), result.get(0).getName());
        verify(repository, times(1)).findAll();
    }

    @Test
    void getParticipantById_shouldReturnParticipant() {
        UUID participantID = participant.getParticipantID();
        when(repository.findById(participantID)).thenReturn(Optional.of(participant));

        Optional<Participant> result = participantService.getParticipantById(participantID);

        assertTrue(result.isPresent());
        assertEquals(participant.getName(), result.get().getName());
        verify(repository, times(1)).findById(participantID);
    }

    @Test
    void getParticipantById_shouldReturnEmptyWhenNotFound() {
        UUID participantID = UUID.randomUUID();
        when(repository.findById(participantID)).thenReturn(Optional.empty());

        Optional<Participant> result = participantService.getParticipantById(participantID);

        assertFalse(result.isPresent());
        verify(repository, times(1)).findById(participantID);
    }

    @Test
    void updateParticipant_shouldUpdateExistingParticipant() {
        UUID participantID = participant.getParticipantID();
        when(repository.existsById(participantID)).thenReturn(true);
        when(repository.save(any(Participant.class))).thenReturn(participant);

        Participant updatedParticipant = participantService.updateParticipant(participantID, participant);

        assertEquals(participant.getName(), updatedParticipant.getName());
        verify(repository, times(1)).existsById(participantID);
        verify(repository, times(1)).save(participant);
    }

    @Test
    void updateParticipant_shouldThrowExceptionWhenNotFound() {
        UUID participantID = participant.getParticipantID();
        when(repository.existsById(participantID)).thenReturn(false);

        RuntimeException thrown = assertThrows(RuntimeException.class, () ->
                participantService.updateParticipant(participantID, participant));

        assertEquals("Participant not found", thrown.getMessage());
        verify(repository, times(1)).existsById(participantID);
        verify(repository, never()).save(any(Participant.class));
    }

    @Test
    void deleteParticipant_shouldDeleteExistingParticipant() {
        UUID participantID = participant.getParticipantID();
        when(repository.existsById(participantID)).thenReturn(true);

        boolean result = participantService.deleteParticipant(participantID);

        assertTrue(result);
        verify(repository, times(1)).deleteById(participantID);
    }

    @Test
    void deleteParticipant_shouldReturnFalseWhenNotFound() {
        UUID participantID = UUID.randomUUID();
        when(repository.existsById(participantID)).thenReturn(false);

        boolean result = participantService.deleteParticipant(participantID);

        assertFalse(result);
        verify(repository, never()).deleteById(participantID);
    }
}