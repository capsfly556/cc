package org.openapitools.api;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.*;
import org.openapitools.model.FoodProvider;
import org.openapitools.service.FoodproviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;


/**
 * This class contains all the food provider related API routes.
 */
@Generated(
        value = "org.openapitools.codegen.languages.SpringCodegen",
        date = "2024-10-02T19:11:02.971027-04:00[America/Toronto]",
        comments = "Generator version: 7.8.0")
@Controller
@RequestMapping("${openapi.groupGrub.base-path:}")
public class FoodprovidersApiController implements FoodprovidersApi {

  private final NativeWebRequest request;
  private final FoodproviderService foodproviderService;

  @Autowired
  public FoodprovidersApiController(
          NativeWebRequest request, FoodproviderService foodproviderService) {
    this.request = request;
    this.foodproviderService = foodproviderService;
  }

  @Override
  public Optional<NativeWebRequest> getRequest() {
    return Optional.ofNullable(request);
  }

  /**
   * Creates a new FoodProvider entity and adds it to the system.
   *
   * @param foodProvider A {@code FoodProvider} object representing the food provider
   *                     and its menu to be added. The input is validated to ensure
   *                     it contains a non-empty menu.
   *
   * @return A {@code ResponseEntity} object with an HTTP 201 response if the food provider
   *         is successfully added, or an HTTP 400 response if the input is invalid
   *         or the addition fails.
   */
  @Override
  public ResponseEntity<Void> foodprovidersPost(@Valid @RequestBody FoodProvider foodProvider) {
    if (foodProvider.getMenu() == null || foodProvider.getMenu().isEmpty()) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    foodProvider.getMenu().forEach(menuItem -> {

      menuItem.setFoodProvider(foodProvider);
    });

    Boolean added = foodproviderService.addFoodProvider(foodProvider);
    if (added) {
      return new ResponseEntity<>(HttpStatus.CREATED);
    }
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }

  /**
   * Deletes the FoodProvider entity with the specified ID.
   *
   * @param foodProviderId A {@code String} representing the unique ID of the food provider
   *                       to be deleted. The ID is expected to be a valid UUID.
   *
   * @return A {@code ResponseEntity} object with an HTTP 204 (NO CONTENT) response if the
   *         food provider is successfully deleted, an HTTP 404 (NOT FOUND) response if the
   *         food provider does not exist, or an HTTP 500 (INTERNAL SERVER ERROR) response if
   *         an error occurs during the operation.
   */
  @Override
  public ResponseEntity<Void> foodprovidersFoodProviderIdDelete(String foodProviderId){
    try {
      System.out.println("Received foodProviderId: " + foodProviderId);
      UUID uuid = UUID.fromString(foodProviderId);
      System.out.print("uuid"+uuid);
      Optional<FoodProvider> foodProviderOptional = foodproviderService.findById(foodProviderId);

      if (foodProviderOptional.isPresent()) {
        Boolean deleteStatus = foodproviderService.deleteFoodProvider(foodProviderId);

        if (deleteStatus) {
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * Retrieves the FoodProvider entity with the specified ID.
   *
   * @param foodProviderId A {@code String} representing the unique ID of the food provider
   *                       to retrieve.
   *
   * @return A {@code ResponseEntity} object containing the {@code FoodProvider} with an HTTP 200
   *         (OK) response if found, an HTTP 404 (NOT FOUND) response if the food provider
   *         does not exist, or an HTTP 500 (INTERNAL SERVER ERROR) response if an error occurs
   *         during the operation.
   */
  @Override
  public ResponseEntity<FoodProvider> foodprovidersFoodProviderIdGet(String foodProviderId) {
    try {
      Optional<FoodProvider> foodProviderOptional = foodproviderService.findById(foodProviderId);

      if (foodProviderOptional.isPresent()) {
        return new ResponseEntity<>(foodProviderOptional.get(), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * Updates the FoodProvider entity with the specified ID.
   *
   * @param foodProviderId A {@code String} representing the unique ID of the food provider
   *                       to update.
   * @param foodProvider A {@code FoodProvider} object containing the updated information
   *                     for the food provider.
   *
   * @return A {@code ResponseEntity} object with an HTTP 200 (OK) response if the update is
   *         successful, an HTTP 409 (CONFLICT) response if there is a conflict with the name or
   *         phone number, an HTTP 404 (NOT FOUND) response if the food provider does not exist,
   *         or an HTTP 400 (BAD REQUEST) response if there is an error in the request.
   */
  @Override
  public ResponseEntity<Void> foodprovidersFoodProviderIdPut(String foodProviderId,
                                                             @Valid @RequestBody FoodProvider foodProvider) {
    try {
      boolean updated = foodproviderService.updateFoodProvider(foodProviderId, foodProvider);

      if(updated){
        return new ResponseEntity<>(HttpStatus.OK);
      }else if (foodproviderService.checkExistsPhoneNumberIdNot(foodProvider.getPhoneNumber(), foodProviderId) ||
              foodproviderService.checkExistsNameIdNot(foodProvider.getName(), foodProviderId)) {
        return new ResponseEntity<>(HttpStatus.CONFLICT);}
      else{
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  /**
   * Retrieves a list of all FoodProvider entities.
   *
   * @return A {@code ResponseEntity} object containing a list of {@code FoodProvider} entities
   *         with an HTTP 200 (OK) response if the list is not empty, or an HTTP 404 (NOT FOUND)
   *         response if no food providers are found.
   */
  @Override
  public ResponseEntity<List<FoodProvider>> foodprovidersGet() {
    List<FoodProvider> foodProviders = foodproviderService.getAllFoodProviders();

    if (foodProviders.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<>(foodProviders, HttpStatus.OK);
    }
  }
}
