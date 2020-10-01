package spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.model.WeightEntry;


/**
 * Repository to handle database connections for application
 * 
 * @author Remy Ward
 * @version 1.0
 * @since 2020-10-1
 */
@Repository 
public interface WeightRepository extends JpaRepository<WeightEntry, Long>{

}