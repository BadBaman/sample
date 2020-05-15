package com.fabric.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.hyperledger.fabric.sdk.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fabric.common.CommonResult;
import com.fabric.config.Config;
import com.fabric.pojo.Organization;
import com.fabric.service.OrgService;
import com.fabric.service.impl.ChannelService;

@Controller
@RequestMapping("/channel")
public class ChannelController {

	@Autowired
	ChannelService channelService;
	
	@Autowired
	OrgService orgService;
	
	/**
	 * 
	 * @param session
	 * @param channel_name
	 * @return
	 */
	@RequestMapping(value="/create",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> create(HttpSession session,
			@RequestParam(value = "channel", defaultValue = "mychannel") String channel_name,
			@RequestParam(value = "orgname", defaultValue = "mychannel") String org_na) {
		//System.out.println(""+channel_name);
		Map<String,Object> map = new HashMap<String, Object>();
		String username = (String) session.getAttribute("username");
		String isLogin = (String) session.getAttribute("isLogin");
		String orgname = (String) session.getAttribute("orgname");
		if(StringUtils.isEmpty(isLogin) || !isLogin.equals("yes")) {
			map.put("code", 303);
			map.put("status", false);
			map.put("msg", "您还未登录，请先登录！");
			return map;
		}
		Organization org = orgService.getOrg(org_na);
		if(org==null) {
			map.put("code", 303);
			map.put("status", false);
			map.put("msg", "您输入的组织名称无效！");
			return map;
		}
		if(!org_na.equals(orgname)) {
			map.put("code", 303);
			map.put("status", false);
			map.put("msg", "您不是该组织的成员！");
			return map;
		}

		CommonResult result = channelService.constructChannel(channel_name, orgname);
		map.put("code", result.getCode());
		map.put("status", true);
		map.put("msg", result.getMessage());
		return map;
	}

	/**
	 * 节点加入通道控制器
	 * @param session
	 * @param channel_name
	 * @return
	 */
	@RequestMapping(value="/join",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> join(HttpSession session,
			@RequestParam(value = "channel_name") String channel_name,
			@RequestParam(value = "orgname") String org_na) {
		Map<String,Object> map = new HashMap<String, Object>();
		String username = (String) session.getAttribute("username");
		String isLogin = (String) session.getAttribute("isLogin");
		String orgname = (String) session.getAttribute("orgname");
		if(StringUtils.isEmpty(isLogin) || !isLogin.equals("yes")) {
			map.put("code", 303);
			map.put("status", false);
			map.put("msg", "您还未登录，请先登录！");
			return map;
		}
		Organization org = orgService.getOrg(org_na);
		if(org==null) {
			map.put("code", 303);
			map.put("status", false);
			map.put("msg", "您输入的组织名称无效！");
			return map;
		}
		if(!org_na.equals(orgname)) {
			map.put("code", 303);
			map.put("status", false);
			map.put("msg", "您不是该组织的成员！");
			return map;
		}
		if(Config.channelMap.get(channel_name+org_na)!=null) {
			map.put("code", 303);
			map.put("status", false);
			map.put("msg", "组织节点已经加入到通道中！");
			return map;
		}
		System.out.println(channel_name);
		CommonResult result = channelService.joinChannel(channel_name, orgname);
		map.put("code", result.getCode());
		map.put("status", true);
		map.put("msg", result.getMessage());
		return map;
	}
}
