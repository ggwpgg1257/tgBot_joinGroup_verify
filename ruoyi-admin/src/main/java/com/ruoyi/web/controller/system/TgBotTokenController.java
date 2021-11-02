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
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.TgBotToken;
import com.ruoyi.system.service.ITgBotTokenService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 机器人tokenController
 * 
 * @author ruoyi
 * @date 2021-11-01
 */
@Controller
@RequestMapping("/system/token")
public class TgBotTokenController extends BaseController
{
    private String prefix = "system/token";

    @Autowired
    private ITgBotTokenService tgBotTokenService;

    @RequiresPermissions("system:token:view")
    @GetMapping()
    public String token()
    {
        return prefix + "/token";
    }

    /**
     * 查询机器人token列表
     */
    @RequiresPermissions("system:token:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TgBotToken tgBotToken)
    {
        startPage();
        List<TgBotToken> list = tgBotTokenService.selectTgBotTokenList(tgBotToken);
        return getDataTable(list);
    }

    /**
     * 导出机器人token列表
     */
    @RequiresPermissions("system:token:export")
    @Log(title = "机器人token", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TgBotToken tgBotToken)
    {
        List<TgBotToken> list = tgBotTokenService.selectTgBotTokenList(tgBotToken);
        ExcelUtil<TgBotToken> util = new ExcelUtil<TgBotToken>(TgBotToken.class);
        return util.exportExcel(list, "机器人token数据");
    }

    /**
     * 新增机器人token
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存机器人token
     */
    @RequiresPermissions("system:token:add")
    @Log(title = "机器人token", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TgBotToken tgBotToken)
    {
        return toAjax(tgBotTokenService.insertTgBotToken(tgBotToken));
    }

    /**
     * 修改机器人token
     */
    @GetMapping("/edit/{token}")
    public String edit(@PathVariable("token") String token, ModelMap mmap)
    {
        TgBotToken tgBotToken = tgBotTokenService.selectTgBotTokenByToken(token);
        mmap.put("tgBotToken", tgBotToken);
        return prefix + "/edit";
    }

    /**
     * 修改保存机器人token
     */
    @RequiresPermissions("system:token:edit")
    @Log(title = "机器人token", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TgBotToken tgBotToken)
    {
        return toAjax(tgBotTokenService.updateTgBotToken(tgBotToken));
    }

    /**
     * 删除机器人token
     */
    @RequiresPermissions("system:token:remove")
    @Log(title = "机器人token", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tgBotTokenService.deleteTgBotTokenByTokens(ids));
    }
}
