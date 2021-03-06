package com.example.demo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

	@Repository
	public interface UserRepository2 extends JpaRepository<User2, Long> {
		@Query(value = "SELECT * FROM userinf WHERE delete_flg = 0 AND mailadd = :freeWord AND name = :freeword",nativeQuery = true)
			List<User2> findAllbyfreeword(@Param("freeword") String Loginname,@Param("freeWord") String Logind,Pageable pageable);


		@Query(value = "SELECT * FROM userinf WHERE delete_flg = 0",nativeQuery = true)
		List<User2> findAll();

}
