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
}