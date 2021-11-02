## 平台简介

给telegram群组做了一个进群验证的系统。当有新用户进群时，需要一分钟内输入验证码，否则将会封禁，并删除其之前的信息。以此保证是真实用户。  
数据库mysql数据库，数据库文件在sql文件夹下  
数据库名:tgbot  
username: root  
password: root  
## 后台管理（用于手动封禁/解封tg用户，管理用户信息）
 地址：127.0.0.1:6070  
 账号admin123密码admin123，可以登录后，右上角手动自行修改  
 功能：  
     tg用户管理：  
               所有新增进入的群员都会显示在这里，点击编辑，然后选择正常就是不封账号。停用就是封号。验证中说明他还处于1分钟的验证时间内，就不要动它。  
                （PS：如果有老用户需要封号的，可以点击新增，输入tg用户唯一Id（userId）和所属会话 选择停用，然后确定即可封号）  
     tg用户信息管理：  
显示用户发送信息，点击删除可以进行单个删除，也可以多选后删除。修改功能暂时无法使用（并不能真正修改群里的消息内容）  
（PS：里面会显示用户Id，这个用户Id就是“用户管理”功能里的tg用户唯一Id。显示的chatId就是“用户管理”功能里的所属会话。在“用户管理”功能中新增时，就放这两个字段数据即可）

## 演示图

<table>	 
    <tr>
        <td><img src="https://oscimg.oschina.net/oscnet/up-088edb4d531e122415a1e2342bccb1a9691.png"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/up-f886fe19bd820c0efae82f680223cac196c.png"/></td>
    </tr>
	<tr>
        <td><img src="https://oscimg.oschina.net/oscnet/up-c7a2eb71fa65d6e660294b4bccca613d638.png"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/up-e60137fb0787defe613bd83331dc4755a70.png"/></td>
    </tr>
	<tr>
        <td><img src="https://oscimg.oschina.net/oscnet/up-7c51c1b5758f0a0f92ed3c60469b7526f9f.png"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/up-15181aed45bb2461aa97b594cbf2f86ea5f.png"/></td>
    </tr>
        <td><img src="https://oscimg.oschina.net/oscnet/up-70a2225836bc82042a6785edf6299e2586a.png"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/up-0184d6ab01fdc6667a14327fcaf8b46345d.png"/></td>
    </tr>
	<tr>
        <td><img src="https://oscimg.oschina.net/oscnet/up-64d8086dc2c02c8f71170290482f7640098.png"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/up-5e4daac0bb59612c5038448acbcef235e3a.png"/></td>
    </tr>
</table>
