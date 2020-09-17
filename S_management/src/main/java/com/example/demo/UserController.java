package com.example.demo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller

public class UserController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	UserService userService;

	List<User2> logindata = new ArrayList<User2>();

	/**
	 * 初期画面
	 * @param user リクエストデータ
	 * @param model Model
	 * @return ログイン画面
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String startview(Model model) {
		model.addAttribute("user2", new User2());
		return "login";
	}

	/**
	* ログイン成否画面を表示
	* @param user リクエストデータ
	* @param model Model
	* @return ログイン成否画面
	*/
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginform(@ModelAttribute User user, @Validated User2 user2, BindingResult result,
			Pageable pageable, Model model) {

		String Logind;
		String Loginname;
		Logind = user2.getMailadd();
		Loginname = user2.getName();
		List<User2> logindata = userService.SearchUser2(Loginname, Logind, pageable);

		if (logindata == null || logindata.size() == 0) {
			model.addAttribute("loginmiss", 0);
			return "login";

		} else {
			model.addAttribute("logindata", logindata);
			return "loginpage";
		}
	}

	/**
	 * 一覧表示画面表示
	 * @param user リクエストデータ
	 * @param model Model
	 * @return 一覧表示画面
	 */
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String getAllUsers(@Validated User user, @Validated User2 user2, @Validated User4 user4,
			BindingResult result,
			@PageableDefault(size = 10) Pageable pageable,
			Model model) {

		String Logname;
		String Logmail;
		Logname = user2.getName();
		Logmail = user2.getMailadd();

		List<User3> purudata = userService.findall();
		List<User4> purudata2 = userService.findAll();

		Page<User> wordPage = userService.searchUser(Logmail, pageable);
		PageWrapper<User> page = new PageWrapper<User>(wordPage, "/all");
		model.addAttribute("page", page);
		model.addAttribute("users", page.getContent());
		model.addAttribute("loginusers", Logname);
		model.addAttribute("loginmail", Logmail);
		model.addAttribute("item", purudata);
		model.addAttribute("items", purudata2);
		return "index";
	}

	/**
	 * 遷移後の一覧表示画面表示
	 * @param user リクエストデータ
	 * @param model Model
	 * @return 一覧表示画面
	 */
	@RequestMapping(value = "/all2", method = RequestMethod.GET)
	public String AllUsers(@ModelAttribute("user2") User2 user2, @Validated User user, @Validated User4 user4,
			BindingResult result,
			@PageableDefault(size = 10) Pageable pageable,
			Model model) {

		String Logname;
		String Logmail;
		Logname = user2.getName();
		Logmail = user2.getMailadd();

		List<User3> purudata = userService.findall();
		List<User4> purudata2 = userService.findAll();

		Page<User> wordPage = userService.searchUser(Logmail, pageable);
		PageWrapper<User> page = new PageWrapper<User>(wordPage, "/all");
		model.addAttribute("page", page);
		model.addAttribute("users", page.getContent());
		model.addAttribute("loginusers", Logname);
		model.addAttribute("loginmail", Logmail);
		model.addAttribute("item", purudata);
		model.addAttribute("items", purudata2);
		return "index";
	}

	/**
	* ユーザー情報 検索
	* @return 検索結果
	*/
	@RequestMapping(value = "/Search", method = RequestMethod.GET)
	public String getSearchUsers(User user, @Validated User2 user2, @Validated UserCriteria usercriteria,
			BindingResult result,
			@PageableDefault(size = 10) Pageable pageable,
			Model model) {
		String Searchinf;
		String MailInf;
		String Gclientname;
		String Gstatus;
		String Logname;
		int delete_flg;

		Logname = user2.getName();
		Searchinf = user.getSubject();
		MailInf = user.getMailadd();
		Gclientname = user.getClientname();
		Gstatus = user.getStatus();
		delete_flg = 0;

		Page<User> wordPage = userService.SearchUserCriteria(delete_flg, Gstatus, Gclientname, MailInf, Searchinf,pageable);
		PageWrapper<User> page = new PageWrapper<User>(wordPage, "/all");
		model.addAttribute("page", page);
		model.addAttribute("users", page.getContent());
		model.addAttribute("loginusers", Logname);
		model.addAttribute("loginmail", MailInf);
		return "index";
	}

	/**
	 * プルダウンvalue
	 * @return それぞれ
	 */
	private Map<String, String> getSelectedItems() {
		Map<String, String> selectMap = new LinkedHashMap<String, String>();
		selectMap.put("key_A", "ビールシステム");
		selectMap.put("key_B", "明治製作所");
		selectMap.put("key_C", "ABC");
		return selectMap;
	}

	private Map<String, String> getSelectedItems2() {
		Map<String, String> selectMap = new LinkedHashMap<String, String>();
		selectMap.put("key_A", "請求済み");
		selectMap.put("key_B", "納入済み");
		selectMap.put("key_C", "製造着手");
		selectMap.put("key_D", "受注");
		selectMap.put("key_E", "見積済み");
		selectMap.put("key_F", "製造完了");
		return selectMap;
	}

	/**
	 * 新規登録画面表示
	 * @param user リクエストデータ
	 * @param model Model
	 * @return 新規登録画面表示
	 */
	@GetMapping(value = "/add")
	public String displayAdd(@Validated User2 user2, @Validated User user, Form form, Form2 form2, Model model) {

		String Mailadd;
		Mailadd = user.getMailadd();

		String Name;
		Name = user2.getName();

		List<User3> purudata = userService.findall();
		List<User5> purudata3 = userService.FindAll();

		//model.addAttribute("user", new User());
		model.addAttribute("userRequest", new UserRequest());
		model.addAttribute("selectItems", getSelectedItems());
		model.addAttribute("selectItems2", getSelectedItems2());
		model.addAttribute("Mailadd", Mailadd);
		model.addAttribute("Name", Name);
		model.addAttribute("item", purudata);
		model.addAttribute("itemS", purudata3);
		return "add";
	}

	@GetMapping("/userC")
	public String usercreate(@Validated @ModelAttribute UserRequest userRequest, BindingResult result,
			@Validated User2 user2, User user, Model model) {

		List<User3> purudata = userService.findall();
		List<User5> purudata3 = userService.FindAll();

		//バリデーションチェック
		if (result.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("validationError", errorList);
			model.addAttribute("item", purudata);
			model.addAttribute("itemS", purudata3);
			return "add";
		} else {
		}

		user.setId(user.getId());
		user.setClientname(user.getClientname());
		user.setDay(user.getDay());
		user.setSnumber(user.getSnumber());
		user.setSubject(user.getSubject());
		user.setQuantity(user.getQuantity());
		user.setDeliveryday(user.getDeliveryday());
		user.setDeliveryday2(user.getDeliveryday2());
		user.setBillingday(user.getBillingday());
		user.setMoney(user.getMoney());
		user.setMoney2(user.getMoney2());
		user.setStatus(user.getStatus());
		user.setMailadd(user.getMailadd());
		user2.setName(user2.getName());
		model.addAttribute("user", user);
		model.addAttribute("user", user2);

		return "CreateUser";
	}

	/**
	 * ユーザー新規登録
	 * @param user リクエストデータ
	 * @param model Model
	 * @return ユーザー情報一覧画面
	 */
	@RequestMapping(value = "/usercreate", method = RequestMethod.POST)
	public String create(@Validated User2 user2, @Validated @ModelAttribute User user,
			RedirectAttributes redirectAttribute, UserRequest userRequest, BindingResult result, Model model) {

		// ユーザー情報の登録
		userService.create(user);
		user2.setMailadd(user2.getMailadd());
		user2.setName(user2.getName());
		redirectAttribute.addFlashAttribute("user2", user2);
		return "redirect:/all2";
	}


	/**
	 * 顧客登録画面表示
	 * @param user リクエストデータ
	 * @param model Model
	 * @return 顧客登録確認画面表示
	 */
	@GetMapping(value = "/clientcreate")
	public String displaycreate(@Validated User2 user2, @Validated User user, Model model) {

		String Mailadd;
		Mailadd = user.getMailadd();

		String Name;
		Name = user2.getName();

		model.addAttribute("userRequest2", new UserRequest2());
		model.addAttribute("Mailadd", Mailadd);
		model.addAttribute("Name", Name);

		return "clientcreate";
	}



	/**
	 * 顧客登録確認画面表示
	 * @param user リクエストデータ
	 * @param model Model
	 * @return 顧客登録
	 */

	@GetMapping("/clientC")
	public String clientcreate(@Validated @ModelAttribute UserRequest2 userRequest2, BindingResult result,
								User2 user2,User user,User3 user3, Model model) {


		//String Mailadd;
		//Mailadd = user.getMailadd();

		//String Name;
		//Name = user2.getName();


		//バリデーションチェック
		if (result.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("validationError", errorList);
			return "clientcreate";
		} else {
		}

		userRequest2.setClientname(user3.getClientname());
		user.setMailadd(user.getMailadd());
		user.setName( user2.getName());

		//model.addAttribute("Mailadd", Mailadd);
		//model.addAttribute("Name", Name);
		model.addAttribute("userRequest2", userRequest2);
		model.addAttribute("user", user);
		return "createClient";

	}


	/**
	 * 顧客登録
	 * @param user リクエストデータ
	 * @param model Model
	 * @return 案件一覧表示
	 */
	@RequestMapping(value = "/createClient", method = RequestMethod.POST)
	public String createclient(@Validated User2 user2, @Validated @ModelAttribute User user,
			RedirectAttributes redirectAttribute, UserRequest2 userRequest2, BindingResult result, Model model) {

		// ユーザー情報の登録
		userService.createC(userRequest2);
		user2.setMailadd(user2.getMailadd());
		user2.setName(user2.getName());
		redirectAttribute.addFlashAttribute("user2", user2);
		return "redirect://clientall";
	}

	/**
	 * ユーザー編集画面を表示
	 * @param id 表示するユーザーID
	 * @param model Model
	 * @return ユーザー編集画面
	 */
	@GetMapping("/user/{id}/edit")
	public String displayEdit(@PathVariable Long id, Form2 form2, Model model) {
		User user = userService.findById(id);

		List<User5> purudata3 = userService.FindAll();

		user.setId(user.getId());
		user.setClientname(user.getClientname());
		user.setDay(user.getDay());
		user.setSnumber(user.getSnumber());
		user.setSubject(user.getSubject());
		user.setQuantity(user.getQuantity());
		user.setDeliveryday(user.getDeliveryday());
		user.setDeliveryday2(user.getDeliveryday2());
		user.setBillingday(user.getBillingday());
		user.setMoney(user.getMoney());
		user.setMoney2(user.getMoney2());
		user.setStatus(user.getStatus());
		user.setMailadd(user.getMailadd());
		model.addAttribute("user", user);
		model.addAttribute("itemS", purudata3);
		return "UpdateU";
	}

	@GetMapping("/useredit")
	public String userEdit(@Validated @ModelAttribute User user, Model model, @RequestParam String clientname,
			@RequestParam String day, @RequestParam String snumber, @RequestParam String subject,
			@RequestParam String quantity,
			@RequestParam String deliveryday, @RequestParam String deliveryday2, @RequestParam String billingday,
			@RequestParam String money,
			@RequestParam String money2, @RequestParam String status, @RequestParam String Mailadd) {

		user.setId(user.getId());
		user.setClientname(clientname);
		user.setDay(day);
		user.setSnumber(snumber);
		user.setSubject(subject);
		user.setQuantity(quantity);
		user.setDeliveryday(deliveryday);
		user.setDeliveryday2(deliveryday2);
		user.setBillingday(billingday);
		user.setMoney(money);
		user.setMoney2(money2);
		user.setStatus(status);
		user.setMailadd(user.getMailadd());
		model.addAttribute("user", user);

		return "userEdit";
	}

	/**
	 * ユーザー更新
	 * @param userRequest リクエストデータ
	 * @param model Model
	 * @return ユーザー情報詳細画面
	 */
	@RequestMapping(value = "/userupdate", method = RequestMethod.POST)
	public String update(@Validated User2 user2, @Validated @ModelAttribute User user,
			RedirectAttributes redirectAttribute, BindingResult result, Model model) {

		// ユーザー情報の更新
		userService.update(user);
		user2.setMailadd(user2.getMailadd());
		redirectAttribute.addFlashAttribute("user2", user2);
		return "redirect:/all2";
	}

	/**
	 * ユーザー削除画面を表示
	 * @param id 表示するユーザーID
	 * @param model Model
	 * @return ユーザー削除画面
	 */
	@GetMapping("/user/{id}/delete")
	public String userDelete(@PathVariable Long id, Model model) {
		User user = userService.findById(id);

		user.setId(user.getId());
		user.setClientname(user.getClientname());
		user.setDay(user.getDay());
		user.setSnumber(user.getSnumber());
		user.setSubject(user.getSubject());
		model.addAttribute("user", user);
		return "DeleteU";
	}

	@RequestMapping(value = "/userdelete", method = RequestMethod.POST)
	public String delete(@Validated User2 user2, @Validated @ModelAttribute User user,
			RedirectAttributes redirectAttribute, BindingResult result, Model model) {
		// ユーザー情報の更新
		userService.delete(user);
		user2.setMailadd(user2.getMailadd());
		user2.setName(user2.getName());
		redirectAttribute.addFlashAttribute("user2", user2);
		return "redirect:/all2";
	}


	/**
	 * 顧客一覧表示画面表示
	 * @param user リクエストデータ
	 * @param model Model
	 * @return 顧客一覧表示画面
	 */
	@RequestMapping(value = "/clientall", method = RequestMethod.GET)
	public String getAllclient(@Validated User user, @ModelAttribute("user2") User2 user2, @Validated User4 user4,
			BindingResult result,
			@PageableDefault(size = 10) Pageable pageable,@ModelAttribute User3 user3, Model model) {

		String Mailadd;
		Mailadd = user2.getMailadd();

		user.setMailadd(user2.getMailadd());

		Page<User3> wordPage = userService.searchclient(pageable);
		PageWrapper<User3> page = new PageWrapper<User3>(wordPage, "/clientall");
		model.addAttribute("page", page);
		model.addAttribute("users", page.getContent());
		model.addAttribute("user", user);
		model.addAttribute("userRequest2", new UserRequest2());
		return "clientindex";
	}


	/**
	 * 顧客編集画面を表示
	 * @param id 表示するユーザーID
	 * @param model Model
	 * @return ユーザー編集画面
	 */
	@GetMapping("/user3/{id}/editC")
	public String displayCEdit(@PathVariable Long id, @RequestParam String mailadd, @ModelAttribute UserRequest2 userRequest2, BindingResult result,
			User2 user2,User user,Model model) {
		User3 user3 = userService.findByid(id);

		String Mailadd;
		Mailadd = mailadd;


		user3.setId(user3.getId());
		user3.setClientname(user3.getClientname());
		user2.setMailadd(user2.getMailadd());
		model.addAttribute("user", user3);
		model.addAttribute("users", user2);
		model.addAttribute("Mailadd", Mailadd);

		return "clientedit";
	}


	/**
	 * 顧客情報更新確認
	 * @param userRequest リクエストデータ
	 * @param model Model
	 * @return ユーザー情報詳細画面
	 */
	@GetMapping("/editclient")
	public String clientEdit(@Validated @ModelAttribute User user, Model model, @RequestParam String clientname,
			@RequestParam String Mailadd) {

		user.setId(user.getId());
		user.setClientname(clientname);
		user.setMailadd(user.getMailadd());
		model.addAttribute("user", user);

		return "Editclient";
	}


	/**
	 * 顧客名更新
	 * @param userRequest リクエストデータ
	 * @param model Model
	 * @return ユーザー情報詳細画面

	@RequestMapping(value = "/clientUpdate", method = RequestMethod.POST)
	public String clientupdate(@ModelAttribute User2 user2,@ModelAttribute User user,@ModelAttribute User3 user3,
			RedirectAttributes redirectAttribute, BindingResult result, Model model) {

		// ユーザー情報の更新
		userService.clientupdate(user3);
		user2.setMailadd(user2.getMailadd());
		redirectAttribute.addFlashAttribute("user2", user2);
		return "redirect:/all2";
	}
*/

	/**
	 * ステータス一覧表示画面表示
	 * @param user リクエストデータ
	 * @param model Model
	 * @return 顧客一覧表示画面
	 */
	@RequestMapping(value = "/statusall", method = RequestMethod.GET)
	public String managementStatus(@ModelAttribute User user, @ModelAttribute("user2") User2 user2, @ModelAttribute User4 user4,
			BindingResult result,@ModelAttribute User3 user3, Model model) {

		String Mailadd;
		Mailadd = user2.getMailadd();

		user.setMailadd(user2.getMailadd());
		List<User3> purudata = userService.findall();

		model.addAttribute("user", user);
		model.addAttribute("item", purudata);
		model.addAttribute("userRequest3", new UserRequest3());
		return "managementStatus";
	}


	@GetMapping("/statusadd")
	public String statuscreate(@ModelAttribute UserRequest3 userRequest3, BindingResult result,
			@Validated User2 user2, User user, Model model) {

		user.setMailadd(user.getMailadd());
		model.addAttribute("user", user);

		return "Createstatus";
	}


	@GetMapping("/createstatus")
	public String createstatus(@Validated @ModelAttribute UserRequest userRequest, BindingResult result,
								User2 user2,User user,User5 user5, Model model) {

		//バリデーションチェック
		if (result.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("validationError", errorList);
			return "Createstatus";
		} else {
		}

		userRequest.setStatus(user5.getStatus());
		user.setMailadd(user.getMailadd());
		user.setName( user2.getName());

		model.addAttribute("userRequest3", userRequest);
		model.addAttribute("user", user);
		return "statuscreate";

	}



	/**
	 * ステータス登録
	 * @param userRequest リクエストデータ
	 * @param model Model
	 * @return ユーザー情報詳細画面
	 */
	@RequestMapping(value = "/statuscreate", method = RequestMethod.POST)
	public String statusadd(@ModelAttribute User2 user2,@ModelAttribute User user,@ModelAttribute UserRequest userRequest,@ModelAttribute User4 user4,
			RedirectAttributes redirectAttribute, BindingResult result, Model model) {

		// ユーザー情報の更新
		userService.statusadd(userRequest);
		user2.setMailadd(user2.getMailadd());
		redirectAttribute.addFlashAttribute("user2", user2);
		return "redirect:/all2";
	}


	/**
	* 顧客別ステータス管理
	* @return 検索結果
	*/
	@RequestMapping(value = "/StatusEdit", method = RequestMethod.GET)
	public String StatusEdit(@ModelAttribute User user, BindingResult result,@ModelAttribute User2 user2, BindingResult result2,@ModelAttribute User3 user3,BindingResult result3,
			@ModelAttribute User4 user4,BindingResult result4,@ModelAttribute User users,Model model) {

		int id = user.getId();
		String Mailadd;
		Mailadd = user.getMailadd();

		List<User5> purudata3 = userService.FindAll();
		List<User4> SearchStatus = userService.SearchUser4(id);
		List<User3> SearchClient = userService.SearcClient(id);

		String clientname;
		clientname = user3.getClientname();

		model.addAttribute("items", SearchStatus);
		model.addAttribute("user3", SearchClient);
		model.addAttribute("item", purudata3);
		model.addAttribute("Mailadd", Mailadd);
		model.addAttribute("users", id);
		model.addAttribute("clientname", clientname);
		return "StatusEdit";
	}


	/**
	* ログイン成否画面を表示
	* @param user リクエストデータ
	* @param model Model
	* @return ログイン成否画面
	*/
	@RequestMapping(value = "/Editstatus", method = RequestMethod.GET)
	public String Editstatus(@ModelAttribute User user, BindingResult result,@ModelAttribute User2 user2, BindingResult result2,@ModelAttribute User3 user3,BindingResult result3,
			@ModelAttribute User4 user4,BindingResult result4,Model model) {

		int statusid;
		String status;
		statusid = user.getId();
		status = user.getStatus();
		user.setMailadd(user.getMailadd());
		List<User4> statusCheck = userService.searchuser4(statusid, status);

		if (!(statusCheck == null || statusCheck.size() == 0)){
			int id = user.getId();

			List<User5> purudata3 = userService.FindAll();
			List<User4> SearchStatus = userService.SearchUser4(id);
			List<User3> SearchClient = userService.SearcClient(id);

			model.addAttribute("items", SearchStatus);
			model.addAttribute("user", SearchClient);
			model.addAttribute("item", purudata3);
			model.addAttribute("statuserror", 0);
			return "StatusEdit";

		} else {
			model.addAttribute("status", status);
			model.addAttribute("id", statusid);
			model.addAttribute("statusCheck", statusCheck);
			return "Editstatus";
		}
	}


	/**
	 * ステータス登録
	 * @param userRequest リクエストデータ
	 * @param model Model
	 * @return ユーザー情報詳細画面
	 */
	@RequestMapping(value = "/clientUpdate", method = RequestMethod.POST)
	public String statusupdata(@ModelAttribute User2 user2,@ModelAttribute User user,@ModelAttribute User4 user4,
			RedirectAttributes redirectAttribute, BindingResult result, Model model) {

		// ユーザー情報の更新
		userService.statusUpdata(user4);
		user2.setMailadd(user2.getMailadd());
		redirectAttribute.addFlashAttribute("user2", user2);
		return "redirect:/all2";
	}


	/**
	 * 登録ユーザー一覧表示画面表示
	 * @param user リクエストデータ
	 * @param model Model
	 * @return 顧客一覧表示画面
	 */
	@RequestMapping(value = "/userall", method = RequestMethod.GET)
	public String useredit(@Validated User user, User2 user2, @Validated User4 user4,
			BindingResult result,@ModelAttribute UserRequest3 userRequest3,
			@ModelAttribute User3 user3, Model model) {

		String Mailadd;
		Mailadd = user2.getMailadd();

		user.setMailadd(user2.getMailadd());
		List<User2> alluser = userService.findALL();

		model.addAttribute("user", new User());
		model.addAttribute("alluser", alluser);
		model.addAttribute("Mailadd", Mailadd);
		return "userindex";
	}


	/**
	 * 新規ユーザー画面表示
	 * @param user リクエストデータ
	 * @param model Model
	 * @return 新規登録画面表示
	 */
	@GetMapping(value = "/useradd")
	public String displayuserAdd(@ModelAttribute User user, @ModelAttribute User2 user2, @ModelAttribute User4 user4,
	BindingResult result,@ModelAttribute UserRequest3 userRequest3,
	@ModelAttribute User3 user3, Model model) {

		model.addAttribute("userRequest3", new UserRequest3());
		return "useradd";
	}


	@GetMapping("/newuser")
	public String createneruser(@Validated @ModelAttribute UserRequest3 userRequest3, BindingResult result,
			@ModelAttribute User user, @ModelAttribute User2 user2, @ModelAttribute User4 user4, Model model) {

		//バリデーションチェック
		if (result.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("validationError", errorList);
			return "useradd";
		} else {
		}

		user.setId(user4.getId());
		user.setClientname(userRequest3.getName());
		user.setMailadd(userRequest3.getMailadd());
		model.addAttribute("user", user);

		return "CreateUser";
	}
}
