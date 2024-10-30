package org.openapitools;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openapitools.api.ParticipantsOrdersApiController;
import org.openapitools.model.ParticipantOrder;
import org.openapitools.service.ParticipantsOrdersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ParticipantsOrdersApiControllerTest {

    @InjectMocks
    private ParticipantsOrdersApiController participantsOrdersApiController;

    @Mock
    private ParticipantsOrdersService participantsOrdersService;

    private UUID participantID;
    private UUID participantOrderID;
    private ParticipantOrder participantOrder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        participantID = UUID.randomUUID();
        participantOrderID = UUID.randomUUID();
        participantOrder = new ParticipantOrder();
        participantOrder.setParticipantOrderID(participantOrderID);
        participantOrder.setComments("Test comments");
        participantOrder.setMenuItemIDs(new HashMap<>());
    }

    @Test
    void testGetParticipantOrders_Success() {
        List<ParticipantOrder> orders = Collections.singletonList(participantOrder);
        when(participantsOrdersService.getParticipantOrders(participantID)).thenReturn(orders);

        ResponseEntity<List<ParticipantOrder>> response = participantsOrdersApiController.participantsParticipantIDOrdersGet(participantID.toString());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(participantOrder, response.getBody().get(0));
    }

    @Test
    void testGetParticipantOrders_NotFound() {
        when(participantsOrdersService.getParticipantOrders(participantID)).thenReturn(Collections.emptyList());

        ResponseEntity<List<ParticipantOrder>> response = participantsOrdersApiController.participantsParticipantIDOrdersGet(participantID.toString());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGetParticipantOrder_Success() {
        when(participantsOrdersService.getParticipantOrder(participantID, participantOrderID)).thenReturn(Optional.of(participantOrder));

        ResponseEntity<ParticipantOrder> response = participantsOrdersApiController.participantsParticipantIDOrdersParticipantOrderIDGet(participantID.toString(), participantOrderID.toString());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(participantOrder, response.getBody());
    }

    @Test
    void testGetParticipantOrder_NotFound() {
        when(participantsOrdersService.getParticipantOrder(participantID, participantOrderID)).thenReturn(Optional.empty());

        ResponseEntity<ParticipantOrder> response = participantsOrdersApiController.participantsParticipantIDOrdersParticipantOrderIDGet(participantID.toString(), participantOrderID.toString());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testDeleteParticipantOrder_Success() {
        when(participantsOrdersService.deleteParticipantOrder(participantID, participantOrderID)).thenReturn(true);

        ResponseEntity<Void> response = participantsOrdersApiController.participantsParticipantIDOrdersParticipantOrderIDDelete(participantID.toString(), participantOrderID.toString());

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testDeleteParticipantOrder_NotFound() {
        when(participantsOrdersService.deleteParticipantOrder(participantID, participantOrderID)).thenReturn(false);

        ResponseEntity<Void> response = participantsOrdersApiController.participantsParticipantIDOrdersParticipantOrderIDDelete(participantID.toString(), participantOrderID.toString());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testUpdateParticipantOrder_Success() {
        ParticipantOrder updatedOrder = new ParticipantOrder();
        updatedOrder.setComments("Updated comments");
        updatedOrder.setMenuItemIDs(new HashMap<>());

        when(participantsOrdersService.updateParticipantOrder(participantID, participantOrderID, updatedOrder)).thenReturn(updatedOrder);

        ResponseEntity<ParticipantOrder> response = participantsOrdersApiController.participantsParticipantIDOrdersParticipantOrderIDPut(participantID.toString(), participantOrderID.toString(), updatedOrder);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedOrder, response.getBody());
    }

    @Test
    void testUpdateParticipantOrder_NotFound() {
        ParticipantOrder updatedOrder = new ParticipantOrder();
        updatedOrder.setComments("Updated comments");
        updatedOrder.setMenuItemIDs(new HashMap<>());

        when(participantsOrdersService.updateParticipantOrder(participantID, participantOrderID, updatedOrder)).thenThrow(new RuntimeException("Participant order not found"));

        ResponseEntity<ParticipantOrder> response = participantsOrdersApiController.participantsParticipantIDOrdersParticipantOrderIDPut(participantID.toString(), participantOrderID.toString(), updatedOrder);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}