package spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.model.WeightEntry;


@Repository
public interface WeightRepository extends JpaRepository<WeightEntry, Long>{

}