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
import com.pengrad.telegrambot.request.DeleteMessage;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.TgUserMessage;
import com.ruoyi.system.service.ITgUserMessageService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.text.Convert;

/**
 * 用户消息Controller
 * 
 * @author ruoyi
 * @date 2021-11-01
 */
@Controller
@RequestMapping("/system/message")
public class TgUserMessageController extends BaseController
{
    private String prefix = "system/message";
    
    @Autowired
    private ITgUserMessageService tgUserMessageService;

    @RequiresPermissions("system:message:view")
    @GetMapping()
    public String message()
    {
        return prefix + "/message";
    }

    /**
     * 查询用户消息列表
     */
    @RequiresPermissions("system:message:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TgUserMessage tgUserMessage)
    {
        startPage();
        List<TgUserMessage> list = tgUserMessageService.selectTgUserMessageList(tgUserMessage);
        return getDataTable(list);
    }

    /**
     * 导出用户消息列表
     */
    @RequiresPermissions("system:message:export")
    @Log(title = "用户消息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TgUserMessage tgUserMessage)
    {
        List<TgUserMessage> list = tgUserMessageService.selectTgUserMessageList(tgUserMessage);
        ExcelUtil<TgUserMessage> util = new ExcelUtil<TgUserMessage>(TgUserMessage.class);
        return util.exportExcel(list, "用户消息数据");
    }

    /**
     * 新增用户消息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存用户消息
     */
    @RequiresPermissions("system:message:add")
    @Log(title = "用户消息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TgUserMessage tgUserMessage)
    {
        return toAjax(tgUserMessageService.insertTgUserMessage(tgUserMessage));
    }

    /**
     * 修改用户消息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TgUserMessage tgUserMessage = tgUserMessageService.selectTgUserMessageById(id);
        mmap.put("tgUserMessage", tgUserMessage);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户消息
     */
    @RequiresPermissions("system:message:edit")
    @Log(title = "用户消息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TgUserMessage tgUserMessage)
    {
        return toAjax(tgUserMessageService.updateTgUserMessage(tgUserMessage));
    }

    /**
     * 删除用户消息
     */
    @RequiresPermissions("system:message:remove")
    @Log(title = "用户消息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tgUserMessageService.deleteTgUserMessageByIds(ids));
    }
}
