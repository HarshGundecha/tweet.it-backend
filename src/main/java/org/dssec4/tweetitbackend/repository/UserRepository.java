package org.dssec4.tweetitbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import org.dssec4.tweetitbackend.entity.User;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
	User findUserByUsername(String username);
	User findByEmail(String email);

	String sql1="select u from User u where similarity(name, ?1)>0.20 order by similarity(name, ?1) desc"; // working
	String sql2="SELECT u, LEVENSHTEIN(name, ?1) FROM User u ORDER BY LEVENSHTEIN(name, ?1)"; //not working
	String sql3="SELECT u FROM User u ORDER BY SIMILARITY(METAPHONE(name,10), METAPHONE(?1,10))"; //working
	@Query(sql1)
	List<User> findUserBySimilarName(String name);

//	List<User> findAllById(List<Long> usersId);


}