package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.TgUser;

/**
 * tg用户管理Service接口
 * 
 * @author ruoyi
 * @date 2021-10-28
 */
public interface ITgUserService 
{
    /**
     * 查询tg用户管理
     * 
     * @param id tg用户管理主键
     * @return tg用户管理
     */
    public TgUser selectTgUserById(Long id);

    /**
     * 查询tg用户管理列表
     * 
     * @param tgUser tg用户管理
     * @return tg用户管理集合
     */
    public List<TgUser> selectTgUserList(TgUser tgUser);

    /**
     * 新增tg用户管理
     * 
     * @param tgUser tg用户管理
     * @return 结果
     */
    public int insertTgUser(TgUser tgUser);

    /**
     * 修改tg用户管理
     * 
     * @param tgUser tg用户管理
     * @return 结果
     */
    public int updateTgUser(TgUser tgUser);

    /**
     * 批量删除tg用户管理
     * 
     * @param ids 需要删除的tg用户管理主键集合
     * @return 结果
     */
    public int deleteTgUserByIds(String ids);

    /**
     * 删除tg用户管理信息
     * 
     * @param id tg用户管理主键
     * @return 结果
     */
    public int deleteTgUserById(Long id);
}
