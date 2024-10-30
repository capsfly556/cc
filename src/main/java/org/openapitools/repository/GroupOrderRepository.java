package org.openapitools.repository;

import java.util.UUID;
import org.openapitools.model.GroupOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupOrderRepository extends JpaRepository<GroupOrder, UUID> {}
