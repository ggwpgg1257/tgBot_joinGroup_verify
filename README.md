## 平台简介

给telegram群组做了一个进群验证的系统。当有新用户进群时，需要一分钟内输入验证码，否则将会封禁，并删除其之前的信息。以此保证是真实用户。  
数据库mysql数据库，数据库文件在sql文件夹下  
数据库名:tgbot  
username: root  
password: root  
#### （数据库表tg_bot_token中用于存放你的机器人token，切记一定要修改）  
数据库配置文件 https://github.com/ggwpgg1257/tgBot_joinGroup_verify/blob/main/ruoyi-admin/src/main/resources/application-druid.yml  
项目配置文件 https://github.com/ggwpgg1257/tgBot_joinGroup_verify/blob/main/ruoyi-admin/src/main/resources/application.yml  
## 后台管理（用于手动封禁/解封tg用户，管理用户信息）
 地址：127.0.0.1:6070  
 账号admin123密码admin123，可以登录后，右上角手动自行修改  
 ### 功能：  
 ####    tg用户管理：  
所有新增进入的群员都会显示在这里，点击编辑，然后选择正常就是不封账号。停用就是封号。验证中说明他还处于1分钟的验证时间内，就不要动它。  
 （PS：如果有老用户需要封号的，可以点击新增，输入tg用户唯一Id（userId）和所属会话 选择停用，然后确定即可封号）  
####     tg用户信息管理：  
显示用户发送信息，点击删除可以进行单个删除，也可以多选后删除。修改功能暂时无法使用（并不能真正修改群里的消息内容）  
（PS：里面会显示用户Id，这个用户Id就是“用户管理”功能里的tg用户唯一Id。显示的chatId就是“用户管理”功能里的所属会话。在“用户管理”功能中新增时，就放这两个字段数据即可）
## 说明
   已经发布可直接运行版本，自行查看  
   jdk1.8以上版本即可，java -jar 运行  
   部署可能需要国际服务器或者科学上网环境，自行解决
## 演示图

<table>	 
    <tr>
        <td><img src="https://img03.sogoucdn.com/app/a/100520146/5B0F4E088D1D5E4DD394A67972B588BC"/></td>
         <td><img src="https://img04.sogoucdn.com/app/a/100520146/2A5F20B3C2FFFFE8D645B8C0F7ABED9D"/></td>
    </tr>
    <tr>
        <td><img src="https://img04.sogoucdn.com/app/a/100520146/401A5E9E700AD52EC3C2A6D6CB23A79B"/></td>
        <td><img src="https://img03.sogoucdn.com/app/a/100520146/AA3FBEAB6BC04400987AF41280E684CC"/></td>
    </tr>
</table>
