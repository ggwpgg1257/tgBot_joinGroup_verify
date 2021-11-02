package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TgUserMessageMapper;
import com.ruoyi.system.domain.TgUserMessage;
import com.ruoyi.system.service.ITgUserMessageService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.DeleteMessage;
import com.ruoyi.common.core.text.Convert;

/**
 * 用户消息Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-11-01
 */
@Service
public class TgUserMessageServiceImpl implements ITgUserMessageService 
{
    @Autowired
    private TgUserMessageMapper tgUserMessageMapper;
    
    public static TelegramBot bot = null;
    
    /**
     * 查询用户消息
     * 
     * @param id 用户消息主键
     * @return 用户消息
     */
    @Override
    public TgUserMessage selectTgUserMessageById(Long id)
    {
        return tgUserMessageMapper.selectTgUserMessageById(id);
    }

    /**
     * 查询用户消息列表
     * 
     * @param tgUserMessage 用户消息
     * @return 用户消息
     */
    @Override
    public List<TgUserMessage> selectTgUserMessageList(TgUserMessage tgUserMessage)
    {
        return tgUserMessageMapper.selectTgUserMessageList(tgUserMessage);
    }

    /**
     * 新增用户消息
     * 
     * @param tgUserMessage 用户消息
     * @return 结果
     */
    @Override
    public int insertTgUserMessage(TgUserMessage tgUserMessage)
    {
        return tgUserMessageMapper.insertTgUserMessage(tgUserMessage);
    }

    /**
     * 修改用户消息
     * 
     * @param tgUserMessage 用户消息
     * @return 结果
     */
    @Override
    public int updateTgUserMessage(TgUserMessage tgUserMessage)
    {
        return tgUserMessageMapper.updateTgUserMessage(tgUserMessage);
    }

    /**
     * 批量删除用户消息
     * 
     * @param ids 需要删除的用户消息主键
     * @return 结果
     */
    @Override
    public int deleteTgUserMessageByIds(String ids)
    {
    	String[] idsString=Convert.toStrArray(ids);
    	for(String id:idsString) {
    		TgUserMessage message=selectTgUserMessageById(Long.parseLong(id));
    		bot.execute(new DeleteMessage(message.getChatId(),message.getMessageId().intValue()));
    	}
        return tgUserMessageMapper.deleteTgUserMessageByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户消息信息
     * 
     * @param id 用户消息主键
     * @return 结果
     */
    @Override
    public int deleteTgUserMessageById(Long id)
    {
    	TgUserMessage message=selectTgUserMessageById(id);
		bot.execute(new DeleteMessage(message.getChatId(),message.getMessageId().intValue()));
        return tgUserMessageMapper.deleteTgUserMessageById(id);
    }
}
