package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TgUserMapper;
import com.ruoyi.system.domain.TgUser;
import com.ruoyi.system.service.ITgUserService;
import com.ruoyi.common.core.text.Convert;

/**
 * tg用户管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-10-28
 */
@Service
public class TgUserServiceImpl implements ITgUserService 
{
    @Autowired
    private TgUserMapper tgUserMapper;

    /**
     * 查询tg用户管理
     * 
     * @param id tg用户管理主键
     * @return tg用户管理
     */
    @Override
    public TgUser selectTgUserById(Long id)
    {
        return tgUserMapper.selectTgUserById(id);
    }

    /**
     * 查询tg用户管理列表
     * 
     * @param tgUser tg用户管理
     * @return tg用户管理
     */
    @Override
    public List<TgUser> selectTgUserList(TgUser tgUser)
    {
        return tgUserMapper.selectTgUserList(tgUser);
    }

    /**
     * 新增tg用户管理
     * 
     * @param tgUser tg用户管理
     * @return 结果
     */
    @Override
    public int insertTgUser(TgUser tgUser)
    {
        tgUser.setCreateTime(DateUtils.getNowDate());
        return tgUserMapper.insertTgUser(tgUser);
    }

    /**
     * 修改tg用户管理
     * 
     * @param tgUser tg用户管理
     * @return 结果
     */
    @Override
    public int updateTgUser(TgUser tgUser)
    {
        return tgUserMapper.updateTgUser(tgUser);
    }

    /**
     * 批量删除tg用户管理
     * 
     * @param ids 需要删除的tg用户管理主键
     * @return 结果
     */
    @Override
    public int deleteTgUserByIds(String ids)
    {
        return tgUserMapper.deleteTgUserByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除tg用户管理信息
     * 
     * @param id tg用户管理主键
     * @return 结果
     */
    @Override
    public int deleteTgUserById(Long id)
    {
        return tgUserMapper.deleteTgUserById(id);
    }
}
