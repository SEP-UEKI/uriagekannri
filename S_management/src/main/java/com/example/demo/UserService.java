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

	public Page<User2> SearchUser2(String Logind,Pageable pageable) {
		Page<User2> logindata = userRepository.findAllbyfreeword(Logind,pageable);

	    return logindata;
	}

	/**
	* ユーザー情報 全検索
	* @return 検索結果s
	*/
	public Page<User> searchUser(Pageable pageable) {
		  Page<User> wordPage = userRepository.findAllByFreeWord(pageable);
	    return wordPage;
	  }


	  /**
	   * ユーザー情報 新規登録
	   * @param user ユーザー情報
	   */
	public void create(User user) {
		User Cuser = new User();

		Cuser.setClientname(user.getClientname());
		Cuser.setDay(user.getDay());
		Cuser.setSnumber(user.getSnumber());
		Cuser.setSubject(user.getSubject());
		Cuser.setQuantity(user.getQuantity());
		Cuser.setDeliveryday(user.getDeliveryday());
		Cuser.setDeliveryday2(user.getDeliveryday2());
		Cuser.setBillingday(user.getBillingday());
		Cuser.setMoney(user.getMoney());
		Cuser.setMoney2(user.getDay());
		Cuser.setStatus(user.getStatus());
		Cuser.setDelete_flg("0");
		Cuser.setMailadd("ueki@takuya.co.jp");
	    userRepository.save(Cuser);
	  }

	 /**
     * ユーザー情報 主キー検索
     * @return 検索結果
     */
    public User findById(long id) {
        return userRepository.findById(id).get();
    }


    /**
     * ユーザー情報 更新
     * @param user ユーザー情報
     */
    public void update(User user) {
        User user2 = findById(user.getId());

        user2.setClientname(user.getClientname());
        user2.setDay(user.getDay());
        user2.setSnumber(user.getSnumber());
        user2.setSubject(user.getSubject());
        user2.setQuantity(user.getQuantity());
        user2.setDeliveryday(user.getDeliveryday());
        user2.setDeliveryday2(user.getDeliveryday2());
        user2.setBillingday(user.getBillingday());
        user2.setMoney(user.getMoney());
        user2.setMoney2(user.getDay());
        user2.setStatus(user.getStatus());
        user2.setDelete_flg("0");
        userRepository.save(user2);
    }


    /**
     * ユーザー情報 削除
     * @param user ユーザー情報
     */
    public void delete(User user) {
        User Duser = findById(user.getId());

        Duser.setClientname(user.getClientname());
        Duser.setDay(user.getDay());
        Duser.setSnumber(user.getSnumber());
        Duser.setSubject(user.getSubject());
        Duser.setDelete_flg("1");
        userRepository.save(Duser);
    }


}
