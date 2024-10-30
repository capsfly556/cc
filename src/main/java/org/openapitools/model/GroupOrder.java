package org.openapitools.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.Valid;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/** GroupOrder */
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2024-10-05T01:59:00.934263-04:00[America/Toronto]",
    comments = "Generator version: 7.8.0")
@Entity
public class GroupOrder {

  @Id
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Type(type = "org.hibernate.type.UUIDCharType")
  private UUID groupOrderID;

  @Column(nullable = false)
  private String status;

  @Valid @ElementCollection private List<UUID> participantOrderIDs = new ArrayList<>();

  @Column(nullable = false)
  private String desiredPickupTimeframe;

  @Column(nullable = false)
  private UUID foodProviderID;

  /**
   * Sets the groupOrderID of the GroupOrder and returns the updated GroupOrder object.
   *
   * @param groupOrderID A {@code UUID} representing the unique ID of the group order.
   * @return The updated {@code GroupOrder} object with the specified groupOrderID.
   */
  public GroupOrder groupOrderID(UUID groupOrderID) {
    this.groupOrderID = groupOrderID;
    return this;
  }

  /**
   * Retrieves the groupOrderID of the GroupOrder.
   *
   * @return A {@code UUID} representing the unique ID of the group order.
   */
  @Valid
  @Schema(name = "groupOrderID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("groupOrderID")
  public UUID getGroupOrderID() {
    return groupOrderID;
  }

  /**
   * Sets the groupOrderID of the GroupOrder.
   *
   * @param groupOrderID A {@code UUID} representing the unique ID of the group order.
   */
  public void setGroupOrderID(UUID groupOrderID) {
    this.groupOrderID = groupOrderID;
  }

  /**
   * Sets the status of the GroupOrder and returns the updated GroupOrder object.
   *
   * @param status A {@code String} representing the current status of the group order.
   * @return The updated {@code GroupOrder} object with the specified status.
   */
  public GroupOrder status(String status) {
    this.status = status;
    return this;
  }

  /**
   * Retrieves the status of the GroupOrder.
   *
   * @return A {@code String} representing the status of the group order.
   */
  @Schema(name = "status", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("status")
  public String getStatus() {
    return status;
  }

  /**
   * Sets the status of the GroupOrder.
   *
   * @param status A {@code String} representing the status of the group order.
   */
  public void setStatus(String status) {
    this.status = status;
  }

  /**
   * Sets the list of participant order IDs for the GroupOrder and returns the updated object.
   *
   * @param participantOrderIDs A {@code List} of {@code UUID} objects representing participant
   *     order IDs.
   * @return The updated {@code GroupOrder} object with the specified participant order IDs.
   */
  public GroupOrder participantOrderIDs(List<UUID> participantOrderIDs) {
    this.participantOrderIDs = participantOrderIDs;
    return this;
  }

  /**
   * Adds a participant order ID to the GroupOrder's participant order list.
   *
   * @param participantOrderIDsItem A {@code UUID} representing a participant's order ID to add.
   * @return The updated {@code GroupOrder} object with the added participant order ID.
   */
  public GroupOrder addParticipantOrderIDsItem(UUID participantOrderIDsItem) {
    if (this.participantOrderIDs == null) {
      this.participantOrderIDs = new ArrayList<>();
    }
    this.participantOrderIDs.add(participantOrderIDsItem);
    return this;
  }

  /**
   * Retrieves the list of participant order IDs for the GroupOrder.
   *
   * @return A {@code List} of {@code UUID} representing the participant order IDs.
   */
  @Valid
  @Schema(name = "participantOrderIDs", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("participantOrderIDs")
  public List<UUID> getParticipantOrderIDs() {
    return participantOrderIDs;
  }

  /**
   * Sets the list of participant order IDs for the GroupOrder.
   *
   * @param participantOrderIDs A {@code List} of {@code UUID} representing the participant order
   *     IDs.
   */
  public void setParticipantOrderIDs(List<UUID> participantOrderIDs) {
    this.participantOrderIDs = participantOrderIDs;
  }

  /**
   * Sets the desired pickup timeframe for the GroupOrder and returns the updated object.
   *
   * @param desiredPickupTimeframe A {@code String} representing the desired pickup timeframe for
   *     the group order.
   * @return The updated {@code GroupOrder} object with the specified pickup timeframe.
   */
  public GroupOrder desiredPickupTimeframe(String desiredPickupTimeframe) {
    this.desiredPickupTimeframe = desiredPickupTimeframe;
    return this;
  }

  /**
   * Retrieves the desired pickup timeframe for the GroupOrder.
   *
   * @return A {@code String} representing the desired pickup timeframe.
   */
  @Schema(name = "desiredPickupTimeframe", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("desiredPickupTimeframe")
  public String getDesiredPickupTimeframe() {
    return desiredPickupTimeframe;
  }

  /**
   * Sets the desired pickup timeframe for the GroupOrder.
   *
   * @param desiredPickupTimeframe A {@code String} representing the desired pickup timeframe.
   */
  public void setDesiredPickupTimeframe(String desiredPickupTimeframe) {
    this.desiredPickupTimeframe = desiredPickupTimeframe;
  }

  /**
   * Sets the food provider ID for the GroupOrder and returns the updated object.
   *
   * @param foodProviderID A {@code UUID} representing the food provider's ID.
   * @return The updated {@code GroupOrder} object with the specified food provider ID.
   */
  public GroupOrder foodProviderID(UUID foodProviderID) {
    this.foodProviderID = foodProviderID;
    return this;
  }

  /**
   * Retrieves the food provider ID for the GroupOrder.
   *
   * @return A {@code UUID} representing the food provider's ID.
   */
  @Valid
  @Schema(name = "foodProviderID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("foodProviderID")
  public UUID getFoodProviderID() {
    return foodProviderID;
  }

  /**
   * Sets the food provider ID for the GroupOrder.
   *
   * @param foodProviderID A {@code UUID} representing the food provider's ID.
   */
  public void setFoodProviderID(UUID foodProviderID) {
    this.foodProviderID = foodProviderID;
  }

  /**
   * Fills default values for groupOrderID, status, desiredPickupTimeframe, and foodProviderID if
   * they are null.
   */
  public void fillFields() {
    if (groupOrderID == null) {
      groupOrderID = UUID.randomUUID();
    }
    if (status == null) {
      status = "status";
    }
    if (desiredPickupTimeframe == null) {
      desiredPickupTimeframe = "desiredPickupTimeframe";
    }
    if (foodProviderID == null) {
      foodProviderID = UUID.randomUUID();
    }
  }

  /**
   * Compares the current GroupOrder object with another object for equality.
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
    GroupOrder groupOrder = (GroupOrder) o;
    return Objects.equals(this.groupOrderID, groupOrder.groupOrderID)
        && Objects.equals(this.status, groupOrder.status)
        && Objects.equals(this.participantOrderIDs, groupOrder.participantOrderIDs)
        && Objects.equals(this.desiredPickupTimeframe, groupOrder.desiredPickupTimeframe)
        && Objects.equals(this.foodProviderID, groupOrder.foodProviderID);
  }

  /**
   * Generates the hash code for the current GroupOrder object.
   *
   * @return The hash code as an {@code int}.
   */
  @Override
  public int hashCode() {
    return Objects.hash(
        groupOrderID, status, participantOrderIDs, desiredPickupTimeframe, foodProviderID);
  }

  /**
   * Converts the GroupOrder object to a string representation.
   *
   * @return A string representing the GroupOrder object.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GroupOrder {\n");
    sb.append("    groupOrderID: ").append(toIndentedString(groupOrderID)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    participantOrderIDs: ")
        .append(toIndentedString(participantOrderIDs))
        .append("\n");
    sb.append("    desiredPickupTimeframe: ")
        .append(toIndentedString(desiredPickupTimeframe))
        .append("\n");
    sb.append("    foodProviderID: ").append(toIndentedString(foodProviderID)).append("\n");
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
