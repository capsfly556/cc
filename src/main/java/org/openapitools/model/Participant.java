package org.openapitools.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.*;
import javax.validation.Valid;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/** Participant */
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2024-10-05T01:59:00.934263-04:00[America/Toronto]",
    comments = "Generator version: 7.8.0")
@Entity
public class Participant {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "participantid", updatable = false, nullable = false)
  @Type(type="org.hibernate.type.UUIDCharType")
  private UUID participantID;

  @Column(name = "name", nullable = false)
  private String name;

  @OneToMany(mappedBy = "participant", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ParticipantOrder> participantOrders = new ArrayList<>();

  public Participant participantID(UUID participantID) {
    this.participantID = participantID;
    return this;
  }

  @Valid
  @Schema(name = "participantID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("participantID")
  public UUID getParticipantID() {
    return participantID;
  }

  public void setParticipantID(UUID participantID) {
    this.participantID = participantID;
  }

  public Participant name(String name) {
    this.name = name;
    return this;
  }

  @Schema(name = "name", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Participant participantOrders(List<ParticipantOrder> participantOrders) {
    this.participantOrders = participantOrders;
    return this;
  }

  public Participant addParticipantOrder(ParticipantOrder participantOrder) {
    participantOrders.add(participantOrder);
    participantOrder.setParticipant(this);
    return this;
  }

  public List<ParticipantOrder> getParticipantOrders() {
    return participantOrders;
  }

  public void setParticipantOrders(List<ParticipantOrder> participantOrders) {
    this.participantOrders = participantOrders;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Participant participant = (Participant) o;
    return Objects.equals(this.participantID, participant.participantID)
        && Objects.equals(this.name, participant.name)
        && Objects.equals(this.participantOrders, participant.participantOrders);
  }

  @Override
  public int hashCode() {
    return Objects.hash(participantID, name, participantOrders);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Participant {\n");
    sb.append("    participantID: ").append(toIndentedString(participantID)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    participantOrders: ")
        .append(toIndentedString(participantOrders))
        .append("\n");
    sb.append("}");
    return sb.toString();
  }

  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
