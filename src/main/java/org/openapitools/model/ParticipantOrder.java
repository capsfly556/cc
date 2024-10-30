package org.openapitools.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.*;
import javax.validation.Valid;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/** ParticipantOrder */
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2024-10-05T01:59:00.934263-04:00[America/Toronto]",
    comments = "Generator version: 7.8.0")
@Entity
public class ParticipantOrder {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Type(type="org.hibernate.type.UUIDCharType")
  private UUID participantOrderID;

  @Valid
  @ElementCollection
  @MapKeyColumn(name = "menu_item_id")
  @Column(name = "count")
  private Map<UUID, Integer> menuItemIDs = new HashMap<>();

  @Column(nullable = true)
  private String comments;

  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "participantid", nullable = false)
  private Participant participant;

  public ParticipantOrder participantOrderID(UUID participantOrderID) {
    this.participantOrderID = participantOrderID;
    return this;
  }

  /**
   * Get participantOrderID
   *
   * @return participantOrderID
   */
  @Valid
  @Schema(name = "participantOrderID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("participantOrderID")
  public UUID getParticipantOrderID() {
    return participantOrderID;
  }

  public void setParticipantOrderID(UUID participantOrderID) {
    this.participantOrderID = participantOrderID;
  }

  public ParticipantOrder menuItemIDs(Map<UUID, Integer> menuItemIDs) {
    this.menuItemIDs = menuItemIDs;
    return this;
  }

  public ParticipantOrder putMenuItemIDsItem(UUID key, Integer menuItemIDsItem) {
    this.menuItemIDs.put(key, menuItemIDsItem);
    return this;
  }

  /**
   * Get menuItemIDs
   *
   * @return menuItemIDs
   */
  @Schema(name = "menuItemIDs", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("menuItemIDs")
  public Map<UUID, Integer> getMenuItemIDs() {
    return menuItemIDs;
  }

  public void setMenuItemIDs(Map<UUID, Integer> menuItemIDs) {
    this.menuItemIDs = menuItemIDs;
  }

  public ParticipantOrder comments(String comments) {
    this.comments = comments;
    return this;
  }

  /**
   * Get comments
   *
   * @return comments
   */
  @Schema(name = "comments", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("comments")
  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  public Participant getParticipant() {
    return participant;
  }

  public void setParticipant(Participant participant) {
    this.participant = participant;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ParticipantOrder participantOrder = (ParticipantOrder) o;
    return Objects.equals(this.participantOrderID, participantOrder.participantOrderID)
        && Objects.equals(this.menuItemIDs, participantOrder.menuItemIDs)
        && Objects.equals(this.comments, participantOrder.comments)
        && Objects.equals(this.participant, participantOrder.participant);
  }

  @Override
  public int hashCode() {
    return Objects.hash(participantOrderID, menuItemIDs, comments, participant);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ParticipantOrder {\n");
    sb.append("    participantOrderID: ").append(toIndentedString(participantOrderID)).append("\n");
    sb.append("    menuItemIDs: ").append(toIndentedString(menuItemIDs)).append("\n");
    sb.append("    comments: ").append(toIndentedString(comments)).append("\n");
    sb.append("    participant: ").append(toIndentedString(participant)).append("\n");
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