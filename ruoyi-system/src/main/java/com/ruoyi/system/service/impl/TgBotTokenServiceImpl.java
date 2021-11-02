package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TgBotTokenMapper;
import com.ruoyi.system.domain.TgBotToken;
import com.ruoyi.system.service.ITgBotTokenService;
import com.ruoyi.common.core.text.Convert;

/**
 * 机器人tokenService业务层处理
 * 
 * @author ruoyi
 * @date 2021-11-01
 */
@Service
public class TgBotTokenServiceImpl implements ITgBotTokenService 
{
    @Autowired
    private TgBotTokenMapper tgBotTokenMapper;

    /**
     * 查询机器人token
     * 
     * @param token 机器人token主键
     * @return 机器人token
     */
    @Override
    public TgBotToken selectTgBotTokenByToken(String token)
    {
        return tgBotTokenMapper.selectTgBotTokenByToken(token);
    }

    /**
     * 查询机器人token列表
     * 
     * @param tgBotToken 机器人token
     * @return 机器人token
     */
    @Override
    public List<TgBotToken> selectTgBotTokenList(TgBotToken tgBotToken)
    {
        return tgBotTokenMapper.selectTgBotTokenList(tgBotToken);
    }

    /**
     * 新增机器人token
     * 
     * @param tgBotToken 机器人token
     * @return 结果
     */
    @Override
    public int insertTgBotToken(TgBotToken tgBotToken)
    {
        return tgBotTokenMapper.insertTgBotToken(tgBotToken);
    }

    /**
     * 修改机器人token
     * 
     * @param tgBotToken 机器人token
     * @return 结果
     */
    @Override
    public int updateTgBotToken(TgBotToken tgBotToken)
    {
        return tgBotTokenMapper.updateTgBotToken(tgBotToken);
    }

    /**
     * 批量删除机器人token
     * 
     * @param tokens 需要删除的机器人token主键
     * @return 结果
     */
    @Override
    public int deleteTgBotTokenByTokens(String tokens)
    {
        return tgBotTokenMapper.deleteTgBotTokenByTokens(Convert.toStrArray(tokens));
    }

    /**
     * 删除机器人token信息
     * 
     * @param token 机器人token主键
     * @return 结果
     */
    @Override
    public int deleteTgBotTokenByToken(String token)
    {
        return tgBotTokenMapper.deleteTgBotTokenByToken(token);
    }
}
