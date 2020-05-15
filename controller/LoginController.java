package com.fabric.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fabric.dao.UserRepository;
import com.fabric.entity.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/")
public class LoginController {
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	@GetMapping("/login")
	public ModelAndView login(ModelAndView modelAndView) {
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/login")
	@ResponseBody
	public Map<String,Object> login(@RequestParam String userName, @RequestParam String password, @RequestParam int orgid,
			Model model, HttpServletRequest request, RedirectAttributes redirectmodel) throws Exception {
		Map<String,Object> map = new HashMap<String, Object>();
		List<UserVo> users = userRepository.findByUserName(userName);
		HttpSession session = request.getSession();
		System.out.println(session.getId());
		// 如果数据库中未查到该账号:
		// 这里有bug 只要有用户就能登录
		System.out.println(users + " " + users.size());
		System.out.println(users == null);
		// 修改为用size判断
		/*
		 * if (users == null) {
		 * log.warn("attempting to log in with the non-existed account"); return
		 * "该用户不存在"; }
		 */
		if (users.size() == 0) {
			log.warn("attempting to log in with the non-existed account");
			model.addAttribute("name", "The username does not exist!");
			map.put("code",303);
			map.put("msg", "The username does not exist!");
			return map;
		} else {
			UserVo user = users.get(0);
			System.out.println(" pwd " + password);
			if (user.getPassword().equals(password)) {
				// 如果密码与账号配对成功:
				if (user.getOrgid() == orgid) {
					if (orgid != 1 && orgid != 2) {//
						model.addAttribute("name", "该组织号不存在");
						log.warn(user.toString() + " failed to log in");
						map.put("code",303);
						map.put("msg", "The orgid does not exist!");
						return map;
						//return "login";
					} else {
						model.addAttribute("name", user.getUserName());
						session.setAttribute("isLogin", "yes");
						session.setAttribute("orgid", user.getOrgid());
						session.setAttribute("username", user.getUserName());
						session.setAttribute("passwd", user.getPassword());
						session.setAttribute("orgname", user.getOrgname());
						log.warn(user.toString() + " logged in");
						map.put("code",200);
						map.put("msg", "登录成功！");
						return map;
						//return "mypage";
					}

				} else {
					model.addAttribute("name", "该用户名与组织号不对应");
					log.warn(user.toString() + " failed to log in");
					map.put("code",303);
					map.put("msg", "该用户名与组织号不对应！");
					return map;
					//return "login";
				}
			} else {
				// 如果密码与邮箱不匹配:
				model.addAttribute("name", "logging failed");
				log.warn(user.toString() + " failed to log in");
				map.put("code",303);
				map.put("msg", "用户名密码不正确！");
				return map;
				//return "login";
			}
		}
	}

	/*
	 * //注意修改，使函数参数不为空 List<UserVo> users = userRepository.findByUserName(userName);
	 * // 如果数据库中未查到该账号: if (users == null) { log.warn("用户不存在");
	 * modelAndView.addObject("error","此用户不存在！"); modelAndView.setViewName("login");
	 * } else { UserVo user = users.get(0); if (user.getPassword().equals(password))
	 * { // 如果密码与邮箱配对成功: //model.addAttribute("name", user.getUserName());
	 * log.warn(user.toString()+ " 成功登陆"); modelAndView.setViewName("mypage");
	 * modelAndView.addObject("userName",userName);
	 * 
	 * } else { // 如果密码与账号不匹配: modelAndView.addObject("error","密码不正确，登陆失败！");
	 * log.warn(userName.toString()+ " 登录失败"); modelAndView.setViewName("login"); }
	 * 
	 * }
	 */
	/*
	 * if(!"admin".equals(userName)){ modelAndView.addObject("error","无此用户！");
	 * modelAndView.setViewName("login"); return modelAndView; }
	 * if(!"123456".equals(password)){ modelAndView.addObject("error","密码错误！");
	 * modelAndView.setViewName("login"); return modelAndView; }
	 */

	// return modelAndView;

	// 账号登出
	@RequestMapping(value = "/loginOut", method = { RequestMethod.GET })
	public String loginOut(HttpServletRequest request) {
		if (request.getSession().getAttribute("isLogin") == null) {
			log.info("未登录状态，不可以登出！");
			return "redirect:mypage";

		} else {
			request.getSession().removeAttribute("isLogin");
			request.getSession().removeAttribute("name");
			request.getSession().removeAttribute("username");
			request.getSession().removeAttribute("passwd");
			request.getSession().removeAttribute("orgname");
			return "redirect:login";
		}

	}
}
