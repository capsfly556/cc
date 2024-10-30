package org.openapitools.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.*;
import java.util.Objects;
import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/** MenuItem */
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2024-10-05T01:59:00.934263-04:00[America/Toronto]",
    comments = "Generator version: 7.8.0")
@Entity
public class MenuItem {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Type(type="org.hibernate.type.UUIDCharType")
  private UUID menuItemID;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String image;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private BigDecimal cost;



  @ManyToOne
  @JoinColumn(name = "food_provider_id", nullable = false)
  @JsonBackReference
  private FoodProvider foodProvider;



  public MenuItem menuItemID(UUID menuItemID) {
    this.menuItemID = menuItemID;
    return this;
  }

  /**
   * Get menuItemID
   *
   * @return menuItemID
   */
  @Valid
  @Schema(name = "menuItemID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("menuItemID")
  public UUID getMenuItemID() {
    return menuItemID;
  }

  public void setMenuItemID(UUID menuItemID) {
    this.menuItemID = menuItemID;
  }

  public MenuItem name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   *
   * @return name
   */
  @Schema(name = "name", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public MenuItem image(String image) {
    this.image = image;
    return this;
  }

  /**
   * Get image
   *
   * @return image
   */
  @Schema(name = "image", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("image")
  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public MenuItem description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   *
   * @return description
   */
  @Schema(name = "description", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public MenuItem cost(BigDecimal cost) {
    this.cost = cost;
    return this;
  }

  /**
   * Get cost
   *
   * @return cost
   */
  @Valid
  @Schema(name = "cost", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("cost")
  public BigDecimal getCost() {
    return cost;
  }



  public void setCost(BigDecimal cost) {
    this.cost = cost;
  }

  public FoodProvider getFoodProvider() {
    return foodProvider;
  }

  public void setFoodProvider(FoodProvider foodProvider) {
    this.foodProvider = foodProvider;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MenuItem menuItem = (MenuItem) o;
    return Objects.equals(this.menuItemID, menuItem.menuItemID)
        && Objects.equals(this.name, menuItem.name)
        && Objects.equals(this.image, menuItem.image)
        && Objects.equals(this.description, menuItem.description)
        && Objects.equals(this.cost, menuItem.cost);
  }

  @Override
  public int hashCode() {
    return Objects.hash(menuItemID, name, image, description, cost);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MenuItem {\n");
    sb.append("    menuItemID: ").append(toIndentedString(menuItemID)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    image: ").append(toIndentedString(image)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    cost: ").append(toIndentedString(cost)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
