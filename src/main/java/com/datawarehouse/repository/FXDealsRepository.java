package com.datawarehouse.repository;

import com.datawarehouse.model.entities.FXDeals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FXDealsRepository extends JpaRepository<FXDeals, String> {
}
