package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 机器人token对象 tg_bot_token
 * 
 * @author ruoyi
 * @date 2021-11-01
 */
public class TgBotToken extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 注册得到的机器人token */
    @Excel(name = "注册得到的机器人token")
    private String token;

    public void setToken(String token) 
    {
        this.token = token;
    }

    public String getToken() 
    {
        return token;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("token", getToken())
            .toString();
    }
}
