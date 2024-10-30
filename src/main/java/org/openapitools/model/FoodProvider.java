package org.openapitools.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/** FoodProvider */
@Generated(
        value = "org.openapitools.codegen.languages.SpringCodegen",
        date = "2024-10-05T01:59:00.934263-04:00[America/Toronto]",
        comments = "Generator version: 7.8.0")
@Entity
public class FoodProvider {


  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Type(type="org.hibernate.type.UUIDCharType")
  private UUID foodProviderID;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String location;

  @Column(nullable = false)
  private String phoneNumber;

  @Column(nullable = false)
  private String hoursOfOperation;

  @Valid
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  private List<@Valid MenuItem> menu = new ArrayList<>();

  public FoodProvider foodProviderID(UUID foodProviderID) {
    this.foodProviderID = foodProviderID;
    return this;
  }


  /**
   * Get foodProviderID
   *
   * @return foodProviderID
   */
  @Valid
  @Schema(name = "foodProviderID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("foodProviderID")
  public UUID getFoodProviderID() {
    return foodProviderID;
  }



  public FoodProvider name(String name) {
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

  public FoodProvider location(String location) {
    this.location = location;
    return this;
  }

  /**
   * Get location
   *
   * @return location
   */
  @Schema(name = "location", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("location")
  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public FoodProvider phoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  /**
   * Get phoneNumber
   *
   * @return phoneNumber
   */
  @Schema(name = "phoneNumber", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("phoneNumber")
  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public FoodProvider hoursOfOperation(String hoursOfOperation) {
    this.hoursOfOperation = hoursOfOperation;
    return this;
  }

  /**
   * Get hoursOfOperation
   *
   * @return hoursOfOperation
   */
  @Schema(name = "hoursOfOperation", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("hoursOfOperation")
  public String getHoursOfOperation() {
    return hoursOfOperation;
  }

  public void setHoursOfOperation(String hoursOfOperation) {
    this.hoursOfOperation = hoursOfOperation;
  }

  public FoodProvider menu(List<@Valid MenuItem> menu) {
    this.menu = menu;
    return this;
  }

  public FoodProvider addMenuItem(MenuItem menuItem) {
    if (this.menu == null) {
      this.menu = new ArrayList<>();
    }
    this.menu.add(menuItem);
    return this;
  }

  /**
   * Get menu
   *
   * @return menu
   */
  @Valid
  @Schema(name = "menu", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("menu")
  public List<@Valid MenuItem> getMenu() {
    return menu;
  }

  public void setMenu(List<@Valid MenuItem> menu) {
    this.menu = menu;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FoodProvider foodProvider = (FoodProvider) o;
    return Objects.equals(this.foodProviderID, foodProvider.foodProviderID)
            && Objects.equals(this.name, foodProvider.name)
            && Objects.equals(this.location, foodProvider.location)
            && Objects.equals(this.phoneNumber, foodProvider.phoneNumber)
            && Objects.equals(this.hoursOfOperation, foodProvider.hoursOfOperation)
            && Objects.equals(this.menu, foodProvider.menu);
  }


  @Override
  public int hashCode() {
    return Objects.hash(foodProviderID, name, location, phoneNumber, hoursOfOperation, menu);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FoodProvider {\n");
    sb.append("    foodProviderID: ").append(toIndentedString(foodProviderID)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    phoneNumber: ").append(toIndentedString(phoneNumber)).append("\n");
    sb.append("    hoursOfOperation: ").append(toIndentedString(hoursOfOperation)).append("\n");
    sb.append("    menu: ").append(toIndentedString(menu)).append("\n");
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
