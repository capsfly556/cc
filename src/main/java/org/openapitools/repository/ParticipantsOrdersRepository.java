package org.openapitools.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.openapitools.model.Participant;
import org.openapitools.model.ParticipantOrder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ParticipantsOrdersRepository extends JpaRepository<ParticipantOrder, UUID> {

  @Query("SELECT o FROM ParticipantOrder o WHERE o.participant.id = :participantId")
  List<ParticipantOrder> findByParticipantId(UUID participantId);
}
