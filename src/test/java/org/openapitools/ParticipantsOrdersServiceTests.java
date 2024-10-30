package org.openapitools;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openapitools.model.Participant;
import org.openapitools.model.ParticipantOrder;
import org.openapitools.repository.ParticipantRepository;
import org.openapitools.repository.ParticipantsOrdersRepository;
import org.openapitools.service.ParticipantsOrdersService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.Map;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ParticipantsOrdersServiceTests {

    @Mock
    private ParticipantsOrdersRepository repository;

    @Mock
    private ParticipantRepository participantRepository;

    @InjectMocks
    private ParticipantsOrdersService participantsOrdersService;

    private UUID participantID;
    private UUID participantOrderID;
    private Participant participant;
    private ParticipantOrder participantOrder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        participantID = UUID.randomUUID();
        participantOrderID = UUID.randomUUID();
        participant = new Participant();
        participant.setParticipantID(participantID);
        participantOrder = new ParticipantOrder();
        participantOrder.setParticipantOrderID(participantOrderID);
    }

    @Test
    void testGetParticipantOrders() {
        when(repository.findByParticipantId(participantID)).thenReturn(Collections.singletonList(participantOrder));

        List<ParticipantOrder> orders = participantsOrdersService.getParticipantOrders(participantID);

        assertNotNull(orders);
        assertEquals(1, orders.size());
        assertEquals(participantOrderID, orders.get(0).getParticipantOrderID());
        verify(repository).findByParticipantId(participantID);
    }

    @Test
    void testGetParticipantOrder() {
        when(repository.findById(participantOrderID)).thenReturn(Optional.of(participantOrder));

        Optional<ParticipantOrder> order = participantsOrdersService.getParticipantOrder(participantID, participantOrderID);

        assertTrue(order.isPresent());
        assertEquals(participantOrderID, order.get().getParticipantOrderID());
        verify(repository).findById(participantOrderID);
    }

    @Test
    void testDeleteParticipantOrder_Success() {
        when(repository.findById(participantOrderID)).thenReturn(Optional.of(participantOrder));

        boolean deleted = participantsOrdersService.deleteParticipantOrder(participantID, participantOrderID);

        assertTrue(deleted);
        verify(repository).delete(participantOrder);
    }

    @Test
    void testDeleteParticipantOrder_NotFound() {
        when(repository.findById(participantOrderID)).thenReturn(Optional.empty());

        boolean deleted = participantsOrdersService.deleteParticipantOrder(participantID, participantOrderID);

        assertFalse(deleted);
        verify(repository, never()).delete(any());
    }

    @Test
	void testUpdateParticipantOrder_Success() {
		ParticipantOrder participantOrder = new ParticipantOrder();
		participantOrder.setComments("Original comments");
		participantOrder.setParticipantOrderID(participantOrderID);
		participantOrder.setMenuItemIDs(new HashMap<>());

		ParticipantOrder updatedOrder = new ParticipantOrder();
		updatedOrder.setComments("Updated comments");
		Map<UUID, Integer> menuItemIDsMap = new HashMap<>();
		menuItemIDsMap.put(UUID.randomUUID(), 1);
		updatedOrder.setMenuItemIDs(menuItemIDsMap);

		System.out.println(updatedOrder.toString());

		when(repository.findById(participantOrderID)).thenReturn(Optional.of(participantOrder));
		when(participantRepository.findById(participantID)).thenReturn(Optional.of(participant));

		when(repository.save(any(ParticipantOrder.class))).thenAnswer(invocation -> invocation.getArgument(0));

		ParticipantOrder result = participantsOrdersService.updateParticipantOrder(participantID, participantOrderID, updatedOrder);

		assertEquals("Updated comments", result.getComments());
		verify(repository).save(participantOrder);
	}


    @Test
    void testUpdateParticipantOrder_NotFound() {
        when(repository.findById(participantOrderID)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            participantsOrdersService.updateParticipantOrder(participantID, participantOrderID, participantOrder);
        });

        assertEquals("Participant order not found", exception.getMessage());
    }

    @Test
    void testCreateParticipantOrder_Success() {
        participantOrder.setParticipantOrderID(null); // Simulate a new order
        when(participantRepository.findById(participantID)).thenReturn(Optional.of(participant));
        when(repository.existsById(any())).thenReturn(false);

        participantsOrdersService.createParticipantOrder(participantID, participantOrder);

        assertNotNull(participantOrder.getParticipantOrderID());
        verify(repository).save(participantOrder);
    }

    @Test
    void testCreateParticipantOrder_ParticipantNotFound() {
        when(participantRepository.findById(participantID)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            participantsOrdersService.createParticipantOrder(participantID, participantOrder);
        });

        assertEquals("Participant not found with ID: " + participantID, exception.getMessage());
    }

    @Test
    void testCreateParticipantOrder_OrderAlreadyExists() {
        participantOrder.setParticipantOrderID(participantOrderID);
        when(participantRepository.findById(participantID)).thenReturn(Optional.of(participant));
        when(repository.existsById(participantOrderID)).thenReturn(true);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            participantsOrdersService.createParticipantOrder(participantID, participantOrder);
        });

        assertEquals("ParticipantOrder with ID: " + participantOrderID + " already exists.", exception.getMessage());
    }
}
