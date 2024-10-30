package org.openapitools.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/** Group */
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2024-10-05T01:59:00.934263-04:00[America/Toronto]",
    comments = "Generator version: 7.8.0")
@Entity
@Table(name = "`group`")
public class Group {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Type(type = "org.hibernate.type.UUIDCharType")
  private UUID groupID;

  @Column(nullable = false)
  private String name;

  @Valid
  @ElementCollection
  @Type(type = "org.hibernate.type.UUIDCharType")
  private List<UUID> participantIDs = new ArrayList<>();

  @Valid
  @ElementCollection
  @Type(type = "org.hibernate.type.UUIDCharType")
  private List<UUID> groupOrderIDs = new ArrayList<>();

  @Column(nullable = false)
  @Type(type = "org.hibernate.type.UUIDCharType")
  private UUID administratorID;

  /**
   * Sets the groupID of the Group and returns the updated Group object.
   *
   * @param groupID A {@code UUID} representing the unique ID of the group.
   * @return The updated {@code Group} object with the specified groupID.
   */
  public Group groupID(UUID groupID) {
    this.groupID = groupID;
    return this;
  }

  /**
   * Retrieves the groupID of the Group.
   *
   * @return A {@code UUID} representing the unique ID of the group.
   */
  @Valid
  @Schema(name = "groupID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("groupID")
  public UUID getGroupID() {
    return groupID;
  }

  /**
   * Sets the groupID of the Group.
   *
   * @param groupID A {@code UUID} representing the unique ID of the group.
   */
  public void setGroupID(UUID groupID) {
    this.groupID = groupID;
  }

  /**
   * Sets the name of the Group and returns the updated Group object.
   *
   * @param name A {@code String} representing the name of the group.
   * @return The updated {@code Group} object with the specified name.
   */
  public Group name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Retrieves the name of the Group.
   *
   * @return A {@code String} representing the name of the group.
   */
  @Schema(name = "name", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the Group.
   *
   * @param name A {@code String} representing the name of the group.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Sets the participant IDs of the Group and returns the updated Group object.
   *
   * @param participantIDs A {@code List} of {@code UUID} objects representing the participant IDs.
   * @return The updated {@code Group} object with the specified participant IDs.
   */
  public Group participantIDs(List<UUID> participantIDs) {
    this.participantIDs = participantIDs;
    return this;
  }

  /**
   * Adds a participant ID to the Group's participant list.
   *
   * @param participantIDsItem A {@code UUID} representing a participant's ID to add.
   * @return The updated {@code Group} object with the added participant ID.
   */
  public Group addParticipantIDsItem(UUID participantIDsItem) {
    if (this.participantIDs == null) {
      this.participantIDs = new ArrayList<>();
    }
    this.participantIDs.add(participantIDsItem);
    return this;
  }

  /**
   * Retrieves the list of participant IDs for the Group.
   *
   * @return A {@code List} of {@code UUID} representing the participant IDs.
   */
  @Valid
  @Schema(name = "participantIDs", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("participantIDs")
  public List<UUID> getParticipantIDs() {
    return participantIDs;
  }

  /**
   * Sets the list of participant IDs for the Group.
   *
   * @param participantIDs A {@code List} of {@code UUID} objects representing the participant IDs.
   */
  public void setParticipantIDs(List<UUID> participantIDs) {
    this.participantIDs = participantIDs;
  }

  /**
   * Sets the group order IDs of the Group and returns the updated Group object.
   *
   * @param groupOrderIDs A {@code List} of {@code UUID} objects representing the group order IDs.
   * @return The updated {@code Group} object with the specified group order IDs.
   */
  public Group groupOrderIDs(List<UUID> groupOrderIDs) {
    this.groupOrderIDs = groupOrderIDs;
    return this;
  }

  /**
   * Adds a group order ID to the Group's order list.
   *
   * @param groupOrderIDsItem A {@code UUID} representing a group order ID to add.
   * @return The updated {@code Group} object with the added group order ID.
   */
  public Group addGroupOrderIDsItem(UUID groupOrderIDsItem) {
    if (this.groupOrderIDs == null) {
      this.groupOrderIDs = new ArrayList<>();
    }
    this.groupOrderIDs.add(groupOrderIDsItem);
    return this;
  }

  /**
   * Retrieves the list of group order IDs for the Group.
   *
   * @return A {@code List} of {@code UUID} representing the group order IDs.
   */
  @Valid
  @Schema(name = "groupOrderIDs", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("groupOrderIDs")
  public List<UUID> getGroupOrderIDs() {
    return groupOrderIDs;
  }

  /**
   * Sets the list of group order IDs for the Group.
   *
   * @param groupOrderIDs A {@code List} of {@code UUID} objects representing the group order IDs.
   */
  public void setGroupOrderIDs(List<UUID> groupOrderIDs) {
    this.groupOrderIDs = groupOrderIDs;
  }

  /**
   * Sets the administrator ID of the Group and returns the updated Group object.
   *
   * @param administratorID A {@code UUID} representing the administrator's ID.
   * @return The updated {@code Group} object with the specified administrator ID.
   */
  public Group administratorID(UUID administratorID) {
    this.administratorID = administratorID;
    return this;
  }

  /**
   * Retrieves the administrator ID of the Group.
   *
   * @return A {@code UUID} representing the administrator's ID.
   */
  @Valid
  @Schema(name = "administratorID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("administratorID")
  public UUID getAdministratorID() {
    return administratorID;
  }

  /**
   * Sets the administrator ID of the Group.
   *
   * @param administratorID A {@code UUID} representing the administrator's ID.
   */
  public void setAdministratorID(UUID administratorID) {
    this.administratorID = administratorID;
  }

  /**
   * Compares the current Group object with another object for equality.
   *
   * @param o The object to be compared.
   * @return {@code true} if the objects are equal, {@code false} otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Group group = (Group) o;
    return Objects.equals(this.groupID, group.groupID)
        && Objects.equals(this.name, group.name)
        && Objects.equals(this.participantIDs, group.participantIDs)
        && Objects.equals(this.groupOrderIDs, group.groupOrderIDs)
        && Objects.equals(this.administratorID, group.administratorID);
  }

  /**
   * Generates the hash code for the current Group object.
   *
   * @return The hash code as an {@code int}.
   */
  @Override
  public int hashCode() {
    return Objects.hash(groupID, name, participantIDs, groupOrderIDs, administratorID);
  }

  /**
   * Converts the Group object to a string representation.
   *
   * @return A string representing the Group object.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Group {\n");
    sb.append("    groupID: ").append(toIndentedString(groupID)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    participantIDs: ").append(toIndentedString(participantIDs)).append("\n");
    sb.append("    groupOrderIDs: ").append(toIndentedString(groupOrderIDs)).append("\n");
    sb.append("    administratorID: ").append(toIndentedString(administratorID)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Converts the given object to a string with each line indented by 4 spaces.
   *
   * @param o The object to convert to string.
   * @return A string representation of the object with each line indented.
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
