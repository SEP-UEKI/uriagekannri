package com.example.demo;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * ユーザー情報 Service
 */
@Service
@Transactional(rollbackOn = Exception.class)

public class UserService {

	@Autowired
	private UserRepository userRepository;

	/**
	* ユーザー情報 全検索
	* @return 検索結果
	*/
	public Page<User> searchUser(Pageable pageable) {
		  Page<User> wordPage = userRepository.findAllByFreeWord(pageable);
	    return wordPage;
	  }
}
