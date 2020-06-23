package com.example.demo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

	@Repository
	public interface UserRepository extends JpaRepository<User2, Long> {
		@Query(value = "SELECT * FROM userinf WHERE mailadd LIKE %:freeWord%",nativeQuery = true)
			Page<User2> findAllbyfreeword(@Param("freeWord") String Logind,Pageable pageable);

		@Query(value = "SELECT * FROM sales_management WHERE delete_flg = 0",nativeQuery = true)
			Page<User> findAllByFreeWord(Pageable pageable);

		@Query(value = "SELECT * FROM sales_management WHERE address LIKE %:freeWord% AND delete_flg = 0",nativeQuery = true)
			Page<User> findAllByFreeword(@Param("freeWord") String Searchinf, Pageable pageable);
	}

