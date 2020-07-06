package com.example.demo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

	@Repository
	public interface UserRepository extends JpaRepository<User, Long> {
		//全検索用
		@Query(value = "SELECT * FROM sales_management WHERE delete_flg = 0 AND mailadd = :freeWord",nativeQuery = true)
			Page<User> findAllByFreeWord(@Param("freeWord") String Logmail,Pageable pageable);

		//検索用
		@Query(value = "SELECT * FROM sales_management WHERE subject LIKE %:freeWord% AND delete_flg = 0 AND mailadd = :FreeWord OR clientname = %:freeword% AND status = %:freeWord2%  ",nativeQuery = true)
			Page<User> findAllByFreeword(@Param("freeword") String Gclientname,@Param("freeWord2") String Gstatus,
					@Param("FreeWord") String MailInf,@Param("freeWord") String Searchinf, Pageable pageable);
	}

