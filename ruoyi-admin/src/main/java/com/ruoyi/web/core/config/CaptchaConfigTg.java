package com.ruoyi.web.core.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.google.code.kaptcha.Constants.KAPTCHA_NOISE_COLOR;
import static com.google.code.kaptcha.Constants.KAPTCHA_NOISE_IMPL;

/**
 * @Description: 通过开源kaptcha配置验证码
 * @Author: liyingxia
 * @CreateDate: 2021/6/08 12:58
 */

import java.util.Properties;

@Configuration
public class CaptchaConfigTg {
    @Bean("captchaTg")
    public DefaultKaptcha getDefaultCaptcha() {
        //验证码生成器
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        //配置
        Properties properties = new Properties();
        //是否有边框
        properties.setProperty("kaptcha.border", "yes");
        //设置边框颜色
        properties.setProperty("kaptcha.border.color", "105,179,90");
        //验证码
        properties.setProperty("kaptcha.session.key", "code");
        //验证码文本字符颜色 默认为黑色
        properties.setProperty("kaptcha.textproducer.font.color", "blue");
        //设置字体样式
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        //字体大小 默认40
        properties.setProperty("kaptcha.textproducer.font.size", "30");
        //验证码文本字符内容范围 默认为abced23456789gfynmnpwx
        properties.setProperty("kaptcha.textproducer.char.string", "");
        //字符长度 默认为5
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        //字符间距 默认为2
        properties.setProperty("kaptcha.textproducer.char.space", "4");
        //验证码图片宽度 默认为200
        properties.setProperty("kaptcha.image.width", "100");
        //验证码图片高度 默认为40
        properties.setProperty("kaptcha.image.height", "40");
        properties.setProperty("kaptcha.obscurificator.impl","com.ruoyi.web.core.config.NoWaterRipple");
//        properties.setProperty(KAPTCHA_NOISE_COLOR, "black");
//        properties.setProperty(KAPTCHA_NOISE_IMPL, "com.google.code.kaptcha.impl.DefaultNoise");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
