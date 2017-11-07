package controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import service.RoleService;
import service.UserService;
import util.PageUtil;
import entity.User;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private RoleService roleService;
	@Resource
	private UserService userService;

	/**
	 * 跳转到登录页面
	 * 
	 * @return
	 */
	@RequestMapping("/tologin")
	public String toLogin() {
		return "login";
	}

	/**
	 * 用户登录
	 * 
	 * @param userCode
	 * @param userPassword
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/login")
	public String login(@RequestParam String userCode,
			@RequestParam String userPassword, HttpServletRequest request,
			HttpSession session) {
		User user = (User) userService.login(userCode, userPassword);
		if (user != null) {
			session.setAttribute("user", user);
			return "redirect:/user/mian";
		} else {
			throw new RuntimeException("用户名或者密码不正确！");
			/*request.setAttribute("error", "用户名和密码错误");
			return "login";*/
		}
	}

	@RequestMapping("/mian")
	public String main(HttpSession session) {

		if (session.getAttribute("user") != null) {

			return "frame";
		} else {
			return "redirect:/user/tologin";
		}
	}

	/**
	 * 注销
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("loginout")
	public String loginout(HttpSession session) {
		session.removeAttribute("user");
		if (session.getAttribute("user") == null) {
			return "login";
		} else {
			return "redirect:/user/mian";
		}

	}

	@RequestMapping(value = "/errorLogin", method = RequestMethod.POST)
	public String errorLogin(@RequestParam String userCode,
			@RequestParam String userPassword, HttpServletRequest request,
			HttpSession session) {
		User user = userService.login(userCode, userPassword);
		if (user != null) {
			session.setAttribute("user", user);

			// 登录成功重定向
			return "redirect:/user/main";
		} else {

			throw new RuntimeException("用户名或者密码不正确！");
		}
	}

	/**
	 * 局部异常处理
	 */
	/*
	 * @ExceptionHandler(value={RuntimeException.class}) public String
	 * handlerException(RuntimeException e,HttpServletRequest request){
	 * request.setAttribute("e", e); return "error"; }
	 */

	@RequestMapping("/getUserListPage")
	public String getUserListPage(
			@RequestParam(value = "queryUserName", required = false) String queryUserName,
			@RequestParam(value = "queryUserRole", required = false) String queryUserRole,
			PageUtil page, HttpServletRequest request) {
		Integer userRole = 0;
		int totalCount = 0;// 总条数
		int totalPage = 0;// 总页数
		if (queryUserName == null) {
			queryUserName = "";
		}
		if (queryUserRole != null && !queryUserRole.equals("")) {
			userRole = Integer.parseInt(queryUserRole);
		}
		if (page == null) {
			page = new PageUtil();
		}
		totalCount = userService.getUserCount(queryUserName, userRole);
		// 获取总页数
		if (totalCount / 5 == 0) {
			totalPage = totalCount / 5;
		} else {
			totalPage = totalCount / 5 + 1;
		}
		// 如果页面传入过来的页码大于总的页码则给currentPageNo设置值
		if (page.getCurrentPageNo() > totalCount) {
			page.setCurrentPageNo(totalPage);
			// /request.getSession().setAttribute("page", page);
			// /request.getSession().setAttribute("total", totalPage);
		}
		if (page.getCurrentPageNo() < 1) {
			page.setCurrentPageNo(1);
			// request.getSession().setAttribute("page", page);
			// request.getSession().setAttribute("total", totalPage);
		}
		page.setStrartNum((page.getCurrentPageNo() - 1) * 5);
		page.setPageSize(5);
		request.getSession().setAttribute("page", page);
		request.getSession().setAttribute("total", totalPage);
		request.getSession().setAttribute("queryUserName", queryUserName);
		request.getSession().setAttribute("queryUserRole", queryUserRole);

		List<User> userList = userService.getUserList(queryUserName, userRole,
				page.getStrartNum(), page.getPageSize());
		request.getSession().setAttribute("userList", userList);
		return "UserList3";

	}

	@RequestMapping("/toaddUser")
	public String toAddUser(User user, Model mode) {
		return "addUser";
	}

	/**
	 * 
	 * 添加用户
	 * @return
	 */
	@RequestMapping(value="/addUser",method=RequestMethod.POST)
	public String addUser(@Valid User user,BindingResult result,HttpSession session) {
		User u=(User)session.getAttribute("user");
		user.setCreatedBy(u.getId());
		user.setCreationDate(new Date());
		if(result.hasErrors()){
			return "addUser";
		}
       boolean flag= userService.add(user);
       if(flag==true){
    	   return "redirect:/user/getUserListPage";
       }else{
    	   return "addUser";
       }
      
	}
	@RequestMapping(value="/getUserById/{id}")
	public String getUserById(@PathVariable String id,HttpServletRequest request){
		User user=userService.getUserById(id);
		request.setAttribute("user", user);
		return "updateUser";
	}
	@RequestMapping(value="/updateUser")
	public String updateUser(User user,HttpServletRequest request){
		user.setModifyDate(new Date());
		boolean flag=userService.modify(user);
		 if(flag==true){
	    	   return "redirect:/user/getUserListPage";
	       }else{
	    	   return "updateUser";
	       }
	      
	}
	/**
	 * 验证用户名
	 * @return
	 */
	@RequestMapping(value="/checkUser",method=RequestMethod.POST)
	@ResponseBody
	public String checkUserCode(@RequestParam  String userCode){
		//跟userCode查询用户信息admin
		User user=userService.selectUserCodeExist(userCode);
		Map map=new HashMap();
		if(user!=null){
			map.put("userCodeExsit", "exsit");
		}else{
			map.put("userCodeExsit", "ok");
		}
		return JSONArray.toJSONString(map);
	}
	//produces={"application/json;charset=UTF-8"}
	@RequestMapping(value="/userbean",method=RequestMethod.GET)
	@ResponseBody
	public String getUserBean(@RequestParam String id){
		User user=userService.getUserById(id);
		return JSON.toJSONString(user);
	}
	
	
}
