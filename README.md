# xvp demo


##项目介绍
小V铺开源商城是免费开源的移动商城，它采用国际标准HTML5+CSS3技术，你可以二次开发，将商城改成你想要的样子。顾客通过手机浏览器、微信公众号、好友/朋友圈转发打开你的微商城，随时随地在线购买。

##环境依赖
spring-boot-starter-parent v1.4.3.RELEASE<br>
spring-boot-starter-web v1.4.3.RELEASE<br>
spring-boot-starter-aop v1.4.3.RELEASE<br>
jfinal v3.0<br>
druid v1.0.15<br>
tutils2 v1.3.3<br>
rop-sdk v1.0<br>

##快速开始
1.登录融数开放平台 http://open.rongcapital.cn/ <br>
2.点击右上角`未登录`，按照要求进行注册，`成为开发者` <br>
3.登录后，点击右上角昵称，选择`应用管理`，进入应用列表页面 <br>
4.添加应用，待审核通过后，在操作一栏，点击`API信息`，API类型选择`小微铺核心API`，选中api，进行Api申请 <br>
5.通过审核后，点击左侧`文档与工具`，选择`SDK下载`，根据您的需要，下载对应的SDK版本 <br>
6.创建一个Spring Boot项目，在项目下新建一个文件夹，将SDK放在该文件夹下，在pom.xml中引入依赖