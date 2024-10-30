package org.openapitools.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.openapitools.model.FoodProvider;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FoodproviderRepository extends JpaRepository<FoodProvider, UUID> {
  Optional<FoodProvider> findByName(String name);

  Boolean existsByNameAndFoodProviderIDNot(String name, UUID foodProviderID);

  Boolean existsByName(String name);

  Boolean existsByPhoneNumber(String phoneNumber);

  Boolean existsByPhoneNumberAndFoodProviderIDNot(String phoneNumber, UUID foodProviderID);
}
