package com.ruoyi.web.controller.system;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.BanChatMember;
import com.pengrad.telegrambot.request.UnbanChatMember;
import com.ruoyi.TgBotInitRunner;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.TgUser;
import com.ruoyi.system.service.ITgUserService;
import com.ruoyi.system.service.impl.TgUserMessageServiceImpl;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * tg用户管理Controller
 * 
 * @author ruoyi
 * @date 2021-10-28
 */
@Controller
@RequestMapping("/system/tgUser")
public class TgUserController extends BaseController
{
    private String prefix = "system/tgUser";
//    TelegramBot bot = new TelegramBot("2083591015:AAEm4Ek8stZqHiBsSo8ozSwCPqy00b-GvDU");
    
    @Autowired
    private ITgUserService tgUserService;

    @RequiresPermissions("system:tgUser:view")
    @GetMapping()
    public String tgUser()
    {
        return prefix + "/tgUser";
    }

    /**
     * 查询tg用户管理列表
     */
    @RequiresPermissions("system:tgUser:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TgUser tgUser)
    {
        startPage();
        List<TgUser> list = tgUserService.selectTgUserList(tgUser);
        return getDataTable(list);
    }

    /**
     * 导出tg用户管理列表
     */
    @RequiresPermissions("system:tgUser:export")
    @Log(title = "tg用户管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TgUser tgUser)
    {
        List<TgUser> list = tgUserService.selectTgUserList(tgUser);
        ExcelUtil<TgUser> util = new ExcelUtil<TgUser>(TgUser.class);
        return util.exportExcel(list, "tg用户管理数据");
    }

    /**
     * 新增tg用户管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存tg用户管理
     */
    @RequiresPermissions("system:tgUser:add")
    @Log(title = "tg用户管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TgUser tgUser)
    {
    	if(tgUser.getIfBan()==2L) {	//封禁
    		TgUserMessageServiceImpl.bot.execute(new BanChatMember(Long.parseLong(tgUser.getChatId()),Long.parseLong(tgUser.getUserId())).untilDate(2147483633).revokeMessages(true));
    	}
        return toAjax(tgUserService.insertTgUser(tgUser));
    }

    /**
     * 修改tg用户管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TgUser tgUser = tgUserService.selectTgUserById(id);
        mmap.put("tgUser", tgUser);
        return prefix + "/edit";
    }

    /**
     * 修改保存tg用户管理
     */
    @RequiresPermissions("system:tgUser:edit")
    @Log(title = "tg用户管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TgUser tgUser)
    {
    	
    	if(tgUser.getIfBan()==1L) {	//解除封禁
    		TgUserMessageServiceImpl.bot.execute(new UnbanChatMember(Long.parseLong(tgUser.getChatId()),Long.parseLong(tgUser.getUserId())).onlyIfBanned(true));
    	}else if(tgUser.getIfBan()==2L) {	//封禁
    		TgUserMessageServiceImpl.bot.execute(new BanChatMember(Long.parseLong(tgUser.getChatId()),Long.parseLong(tgUser.getUserId())).untilDate(2147483633).revokeMessages(true));
    	}
    	
        return toAjax(tgUserService.updateTgUser(tgUser));
    }

    /**
     * 删除tg用户管理
     */
    @RequiresPermissions("system:tgUser:remove")
    @Log(title = "tg用户管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tgUserService.deleteTgUserByIds(ids));
    }
}
