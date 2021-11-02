package com.ruoyi.quartz.task;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.TgUser;
import com.ruoyi.system.service.ITgUserMessageService;
import com.ruoyi.system.service.ITgUserService;

/**
 * 定时任务调度测试
 * 
 * @author ruoyi
 */
@Component("ryTask")
public class RyTask{
	
	 @Autowired
	 private ITgUserService tgUserService;
	 @Autowired
	 private ITgUserMessageService messageService;
	 @Resource(name = "captchaTg")
	 private DefaultKaptcha defaultKaptcha;
	 
    public void ryMultipleParams(String s, Boolean b, Long l, Double d, Integer i)
    {
        System.out.println(StringUtils.format("执行多参方法： 字符串类型{}，布尔类型{}，长整型{}，浮点型{}，整形{}", s, b, l, d, i));
    }

    public void ryParams(String params)
    {
        System.out.println("执行有参方法：" + params);
    }

    public void ryNoParams()
    {
        System.out.println("执行无参方法");
    }
    
    public void tgRegisterCodeVery(){//tg进群验证
		/*
		 * TgUser tgUser=new TgUser(); tgUser.setIfBan(0L); List<TgUser>
		 * userList=tgUserService.selectTgUserList(tgUser);
		 */
    	
    	 // bot.execute(new BanChatMember(chatId, update.message().from().id()).untilDate(4099741261).revokeMessages(true));
	    // bot.execute(new UnbanChatMember(chatId, 2089936270).onlyIfBanned(true));
    	
    }
}
