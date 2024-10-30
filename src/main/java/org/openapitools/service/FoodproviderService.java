package org.openapitools.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.openapitools.model.FoodProvider;
import org.openapitools.model.MenuItem;
import org.openapitools.repository.FoodproviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * This class implements business logic for food provider related api.
 */
@Service
public class FoodproviderService {

  @Autowired private FoodproviderRepository repository;

  /**
   * Retrieves the FoodProvider entity by its ID.
   *
   * @param foodProviderId A {@code String} representing the unique ID of the food provider
   *                       to be retrieved.
   * @return An {@code Optional<FoodProvider>} containing the food provider if found,
   *         or an empty {@code Optional} if not found.
   */
  public Optional<FoodProvider> findById(String foodProviderId) {
    return repository.findById(UUID.fromString(foodProviderId));
  }

  /**
   * Deletes the FoodProvider entity with the specified ID.
   *
   * @param foodProviderId A {@code String} representing the unique ID of the food provider
   *                       to be deleted.
   * @return A {@code Boolean} indicating {@code true} if the food provider was successfully
   *         deleted, or {@code false} if the food provider does not exist.
   */

  public Boolean deleteFoodProvider(String foodProviderId) {
    UUID uuid = UUID.fromString(foodProviderId);
    if(repository.existsById(uuid)) {
      repository.deleteById(uuid);
      return true;
    }
    return false;

  }


  /**
   * Checks if a FoodProvider with the specified name exists, excluding the FoodProvider
   * with the given ID.
   *
   * @param name A {@code String} representing the name of the food provider to check.
   * @param foodProviderId A {@code String} representing the unique ID of the food provider
   *                       to exclude from the check.
   * @return A {@code Boolean} indicating {@code true} if a FoodProvider with the same name
   *         exists and has a different ID, or {@code false} otherwise.
   */
  public Boolean checkExistsNameIdNot(String name, String foodProviderId) {
    UUID uuid = UUID.fromString(foodProviderId);
    return repository.existsByNameAndFoodProviderIDNot(name, uuid );
  }

  /**
   * Checks if a FoodProvider with the specified phoneNumber exists, excluding the FoodProvider
   * with the given ID.
   *
   * @param phoneNumber A {@code String} representing the phoneNumber of the food provider to check.
   * @param foodProviderId A {@code String} representing the unique ID of the food provider
   *                       to exclude from the check.
   * @return A {@code Boolean} indicating {@code true} if a FoodProvider with the same phoneNumber
   *         exists and has a different ID, or {@code false} otherwise.
   */
  public Boolean checkExistsPhoneNumberIdNot(String phoneNumber, String foodProviderId) {
    UUID uuid = UUID.fromString(foodProviderId);
    return repository.existsByPhoneNumberAndFoodProviderIDNot(phoneNumber,uuid);
  }

  /**
   * Updates the FoodProvider entity with the specified ID using the provided updated information.
   * If the FoodProvider exists, its details (name, location, phone number, hours of operation, and menu)
   * are updated. The existing menu is cleared and replaced with the updated menu items.
   *
   * @param foodProviderId A {@code String} representing the unique ID of the food provider
   *                       to be updated.
   * @param updatedProvider A {@code FoodProvider} object containing the updated information
   *                        for the food provider.
   * @return A {@code boolean} value of {@code true} if the update is successful, or
   *         {@code false} if the food provider with the given ID is not found.
   */
  public boolean updateFoodProvider(String foodProviderId, FoodProvider updatedProvider) {
    Optional<FoodProvider> existingFoodProviderOptional = repository.findById(UUID.fromString(foodProviderId));

    if (existingFoodProviderOptional.isPresent()) {
      FoodProvider retrievedFoodProvider = existingFoodProviderOptional.get();
      retrievedFoodProvider.setName(updatedProvider.getName());
      retrievedFoodProvider.setLocation(updatedProvider.getLocation());
      retrievedFoodProvider.setPhoneNumber(updatedProvider.getPhoneNumber());
      retrievedFoodProvider.setHoursOfOperation(updatedProvider.getHoursOfOperation());

      List<MenuItem> existingMenuItems = retrievedFoodProvider.getMenu();


      existingMenuItems.clear();
      for (MenuItem updatedMenuItem : updatedProvider.getMenu()) {
        updatedMenuItem.setFoodProvider(retrievedFoodProvider); // Maintain reference to the parent
        existingMenuItems.add(updatedMenuItem); // Modify the existing collection
      }
      repository.save(retrievedFoodProvider);
      return true;

    } else {
      return false;
    }
  }
  /**
   * Retrieves a list of all FoodProvider entities from the repository.
   *
   * @return A {@code List<FoodProvider>} containing all food providers in the system.
   */
  public List<FoodProvider> getAllFoodProviders() {
    return repository.findAll();
  }

  /**
   * Adds a new FoodProvider entity to the repository if a FoodProvider with the same
   * name or phone number does not already exist.
   *
   * @param foodProvider A {@code FoodProvider} object representing the food provider to be added.
   * @return A {@code boolean} value of {@code true} if the food provider is successfully added,
   *         or {@code false} if a food provider with the same name or phone number already exists.
   */
  public boolean addFoodProvider(FoodProvider foodProvider) {
    if (repository.existsByName(foodProvider.getName()) || repository.existsByPhoneNumber(foodProvider.getPhoneNumber())) {
      return false;
    }
    repository.save(foodProvider);
    return true;
  }

}
