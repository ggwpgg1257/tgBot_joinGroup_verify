package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.TgBotToken;

/**
 * 机器人tokenService接口
 * 
 * @author ruoyi
 * @date 2021-11-01
 */
public interface ITgBotTokenService 
{
    /**
     * 查询机器人token
     * 
     * @param token 机器人token主键
     * @return 机器人token
     */
    public TgBotToken selectTgBotTokenByToken(String token);

    /**
     * 查询机器人token列表
     * 
     * @param tgBotToken 机器人token
     * @return 机器人token集合
     */
    public List<TgBotToken> selectTgBotTokenList(TgBotToken tgBotToken);

    /**
     * 新增机器人token
     * 
     * @param tgBotToken 机器人token
     * @return 结果
     */
    public int insertTgBotToken(TgBotToken tgBotToken);

    /**
     * 修改机器人token
     * 
     * @param tgBotToken 机器人token
     * @return 结果
     */
    public int updateTgBotToken(TgBotToken tgBotToken);

    /**
     * 批量删除机器人token
     * 
     * @param tokens 需要删除的机器人token主键集合
     * @return 结果
     */
    public int deleteTgBotTokenByTokens(String tokens);

    /**
     * 删除机器人token信息
     * 
     * @param token 机器人token主键
     * @return 结果
     */
    public int deleteTgBotTokenByToken(String token);
}
