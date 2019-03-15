package cn.my.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.my.application.mapper.yuedulijieMapper;
import cn.my.application.pojo.yuedulijie;

@Controller
public class controller {
	@Autowired
	private yuedulijieMapper yuedumapper;
	@RequestMapping("/index")
		public ModelAndView  itemsList() {
			Integer id = 1;
			yuedulijie yuedu = yuedumapper.SelectById(id);
			ModelAndView mav = new ModelAndView("index");
			mav.addObject("yuedu", yuedu);
			return mav;
	}
}
