package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * tg用户管理对象 tg_user
 * 
 * @author ruoyi
 * @date 2021-10-28
 */
public class TgUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** tg用户id */
    @Excel(name = "tg用户id")
    private String userId;

    /** 所属会话 */
    @Excel(name = "所属会话")
    private String chatId;

    /** 是否封禁 */
    @Excel(name = "是否封禁")
    private Long ifBan;

    /** 验证码 */
    @Excel(name = "验证码")
    private String verificationCode;

    /** 正确结果 */
    @Excel(name = "正确结果")
    private String trueRes;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setChatId(String chatId) 
    {
        this.chatId = chatId;
    }

    public String getChatId() 
    {
        return chatId;
    }
    public void setIfBan(Long ifBan) 
    {
        this.ifBan = ifBan;
    }

    public Long getIfBan() 
    {
        return ifBan;
    }
    public void setVerificationCode(String verificationCode) 
    {
        this.verificationCode = verificationCode;
    }

    public String getVerificationCode() 
    {
        return verificationCode;
    }
    public void setTrueRes(String trueRes) 
    {
        this.trueRes = trueRes;
    }

    public String getTrueRes() 
    {
        return trueRes;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("chatId", getChatId())
            .append("ifBan", getIfBan())
            .append("verificationCode", getVerificationCode())
            .append("trueRes", getTrueRes())
            .append("createTime", getCreateTime())
            .toString();
    }
}
