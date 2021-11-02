package com.ruoyi;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.BanChatMember;
import com.pengrad.telegrambot.request.DeleteMessage;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import com.pengrad.telegrambot.request.UnbanChatMember;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.TgBotToken;
import com.ruoyi.system.domain.TgUser;
import com.ruoyi.system.domain.TgUserMessage;
import com.ruoyi.system.service.ITgBotTokenService;
import com.ruoyi.system.service.ITgUserMessageService;
import com.ruoyi.system.service.ITgUserService;
import com.ruoyi.system.service.impl.TgUserMessageServiceImpl;


@Order(1)//指定执行顺序
@Component
public class TgBotInitRunner implements ApplicationRunner{
	
	 @Autowired
	 private ITgUserService tgUserService;
	 @Autowired
	 private ITgUserMessageService messageService;
	 @Resource(name = "captchaTg")
	 private DefaultKaptcha defaultKaptcha;
	 @Autowired
	 private ITgBotTokenService tgBotTokenService;
	
	@Override
    public void run(ApplicationArguments args) throws Exception {
    	System.err.println("启动tgbot系统");
    	List<TgBotToken> tokens=tgBotTokenService.selectTgBotTokenList(new TgBotToken());
    	if(StringUtils.isNotEmpty(tokens)) {
    		TgUserMessageServiceImpl.bot = new TelegramBot(tokens.get(0).getToken());
    	}else {
    		TgUserMessageServiceImpl.bot = new TelegramBot("2083591015:AAEm4Ek8stZqHiBsSo8ozSwCPqy00b-GvDU");
    	}

    	TgUserMessageServiceImpl.bot.setUpdatesListener(updates -> {
		   for(Update update:updates) {
			   try {
				   System.err.println(update.message());
				   long chatId = update.message().chat().id();
				   //有新加入的人就插入数据库
				   if(StringUtils.isNotEmpty(update.message().newChatMembers())) {
					   insertUser(update, chatId,TgUserMessageServiceImpl.bot);
				   }else {
					   //判断是否是验证中用户
					   TgUser tgUser=new TgUser();
				    	tgUser.setIfBan(0L);
				    	tgUser.setUserId(update.message().from().id()+"");
				    	tgUser.setChatId(chatId+"");
				    	List<TgUser> userList=tgUserService.selectTgUserList(tgUser);
				    	if(StringUtils.isNotEmpty(userList)) {//是验证中用户
				    		tgUser=userList.get(0);
				    		Calendar calendar= Calendar.getInstance();
				    		calendar.add(Calendar.MINUTE, -1);
				    		Date d=calendar.getTime();
				    		if(tgUser.getCreateTime().compareTo(d)==-1) {//超时
				    			tgUser.setIfBan(2L);
				    			tgUserService.updateTgUser(tgUser);
				    			TgUserMessage tgUserMessage=new TgUserMessage();
			    			    tgUserMessage.setUserId(update.message().from().id()+"");
				    			tgUserMessage.setChatId(chatId+"");
				    			List<TgUserMessage> messageList=messageService.selectTgUserMessageList(tgUserMessage);
				    			for (TgUserMessage userMessage : messageList) {
				    				messageService.deleteTgUserMessageById(userMessage.getId());
//				    				bot.execute(new DeleteMessage(userMessage.getChatId(),userMessage.getMessageId().intValue()));
								}
				    			TgUserMessageServiceImpl.bot.execute(new BanChatMember(chatId, update.message().from().id()).untilDate(2147483633).revokeMessages(true));
				    		}else {
				    			String messageContent="";
				    			if(StringUtils.isNotEmpty(update.message().text())) {
				    				if(update.message().text().length()>253) {
				    					messageContent=update.message().text().substring(0,250);
				    				}else {
				    					messageContent=update.message().text();
				    				}
				    			}
				    			   TgUserMessage tgUserMessage=new TgUserMessage();
								   tgUserMessage.setUserId(update.message().from().id()+"");
								   tgUserMessage.setChatId(chatId+"");
								   tgUserMessage.setMessageId(Long.valueOf(update.message().messageId()));
								   tgUserMessage.setMessageContent(messageContent);
								   messageService.insertTgUserMessage(tgUserMessage);
								   if(StringUtils.isNotEmpty(messageContent)) {
									   String username="@"+update.message().from().username();
										 if(StringUtils.isEmpty(update.message().from().username())||update.message().from().username().equals("null")) {
											 username=update.message().from().firstName()+update.message().from().lastName();
										 }
									   if(!messageContent.equals(tgUser.getTrueRes())) {
										   TgUserMessageServiceImpl.bot.execute(new SendMessage(chatId, "<b>"+username+" </b>验证码输入错误，请重新输入！！！").parseMode(ParseMode.HTML));
									   }else {
										   tgUser.setIfBan(1L);
							    			tgUserService.updateTgUser(tgUser);
							    			TgUserMessageServiceImpl.bot.execute(new SendMessage(chatId, "<b>"+username+" </b>验证码正确,通过审核校验！").parseMode(ParseMode.HTML));
									   }
								   }
				    		}
				    	}else {
				    		TgUserMessage tgUserMessage=new TgUserMessage();
				    		String messageContent="";
			    			if(StringUtils.isNotEmpty(update.message().text())) {
			    				if(update.message().text().length()>253) {
			    					messageContent=update.message().text().substring(0,250);
			    				}else {
			    					messageContent=update.message().text();
			    				}
			    			}
						   tgUserMessage.setUserId(update.message().from().id()+"");
						   tgUserMessage.setChatId(chatId+"");
						   tgUserMessage.setMessageId(Long.valueOf(update.message().messageId()));
						   tgUserMessage.setMessageContent(messageContent);
						   String username="@"+update.message().from().username();
						   String nickName=update.message().from().firstName()+update.message().from().lastName();
						   if(username.indexOf("null")!=-1) {
							   username="-";
						   }if(nickName.indexOf("null")!=-1) {
							   nickName="-";
						   }
						   tgUserMessage.setUserName(username);
						   tgUserMessage.setNickName(nickName);
						   messageService.insertTgUserMessage(tgUserMessage);
				    	}
					   
				   }
				   // bot.execute(new BanChatMember(chatId, update.message().from().id()).untilDate(4099741261).revokeMessages(true));
				   // bot.execute(new UnbanChatMember(chatId, 2089936270).onlyIfBanned(true));
			   }catch (Exception e) {
				   System.err.println(update);
			   }
			   
			  
	        }
		    return UpdatesListener.CONFIRMED_UPDATES_ALL;
		});
    	
    }

	private void insertUser(Update update, long chatId,TelegramBot bot) {
		 String code = defaultKaptcha.createText();  // 获取验证码文本内容
		 System.out.println("验证码为" + code);
			TgUser tgUser=new TgUser();
			tgUser.setUserId(update.message().from().id()+"");
			tgUser.setChatId(chatId+"");
			List<TgUser> list=tgUserService.selectTgUserList(tgUser);
			if(list.size()>0) {
					tgUser.setVerificationCode(code);
					tgUser.setTrueRes(code);
					tgUser.setChatId(chatId+"");
					tgUser.setIfBan(0L);
					tgUser.setCreateTime(new Date());
					tgUser.setId(list.get(0).getId());
					tgUserService.updateTgUser(tgUser);
			}else {
				tgUser.setVerificationCode(code);
				tgUser.setTrueRes(code);
				tgUser.setChatId(chatId+"");
				tgUser.setIfBan(0L);
				tgUser.setCreateTime(new Date());
				tgUserService.insertTgUser(tgUser);
			}
			 String username="@"+update.message().from().username();
			 if(StringUtils.isEmpty(update.message().from().username())||update.message().from().username().equals("null")) {
				 username=update.message().from().firstName()+update.message().from().lastName();
			 }
			 	byte[] byteImg=getByte(defaultKaptcha.createImage(code));
		        bot.execute(new SendMessage(chatId, "<b>"+username+" </b>请在1分钟内输入图片中的验证码(直接给结果即可)：").parseMode(ParseMode.HTML));
		        bot.execute(new SendPhoto(chatId,byteImg));
//		        bot.execute(new DeleteMessage(chatId,111));
		
	}
	
	public static byte[] getByte(BufferedImage image){
		byte[] byteImg=null;
		try {
			Integer width = image.getWidth();
			Integer height = image.getHeight();
			System.out.println("宽：" + width + " 高:"+height);
			
			//输出流
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			ImageIO.write(image, "jpg", stream);
			byteImg=stream.toByteArray();
            stream.flush();
            stream.close();
		} catch (IOException e) {
			System.err.println("图片异常");
		}
		return byteImg;
	}
	
 }
	

    