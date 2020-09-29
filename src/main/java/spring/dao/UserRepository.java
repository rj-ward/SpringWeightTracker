package spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
