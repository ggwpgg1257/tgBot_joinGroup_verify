package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.TgUserMessage;

/**
 * 用户消息Mapper接口
 * 
 * @author ruoyi
 * @date 2021-11-01
 */
public interface TgUserMessageMapper 
{
    /**
     * 查询用户消息
     * 
     * @param id 用户消息主键
     * @return 用户消息
     */
    public TgUserMessage selectTgUserMessageById(Long id);

    /**
     * 查询用户消息列表
     * 
     * @param tgUserMessage 用户消息
     * @return 用户消息集合
     */
    public List<TgUserMessage> selectTgUserMessageList(TgUserMessage tgUserMessage);

    /**
     * 新增用户消息
     * 
     * @param tgUserMessage 用户消息
     * @return 结果
     */
    public int insertTgUserMessage(TgUserMessage tgUserMessage);

    /**
     * 修改用户消息
     * 
     * @param tgUserMessage 用户消息
     * @return 结果
     */
    public int updateTgUserMessage(TgUserMessage tgUserMessage);

    /**
     * 删除用户消息
     * 
     * @param id 用户消息主键
     * @return 结果
     */
    public int deleteTgUserMessageById(Long id);

    /**
     * 批量删除用户消息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTgUserMessageByIds(String[] ids);
}
