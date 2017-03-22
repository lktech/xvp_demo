# xvp demo


##项目介绍
小V铺开源商城是免费开源的移动商城，它采用国际标准HTML5+CSS3技术，你可以二次开发，将商城改成你想要的样子。顾客通过手机浏览器、微信公众号、好友/朋友圈转发打开你的微商城，随时随地在线购买。


##环境依赖
spring-boot-starter-parent v1.4.3.RELEASE <br>
spring-boot-starter-web v1.4.3.RELEASE <br>
spring-boot-starter-aop v1.4.3.RELEASE <br>
jfinal v3.0 <br>
druid v1.0.15 <br>
tutils2 v1.3.3 (`git clone https://github.com/CZ-chen/tutils2.git`) <br>
rop-sdk v1.0 <br>

##快速开始
1.登录融数开放平台 http://open.rongcapital.cn/ <br>
2.点击右上角`未登录`，按照要求进行注册，`成为开发者` <br>
3.登录后，点击右上角昵称，选择`应用管理`，进入应用列表页面 <br>
4.添加应用，待审核通过后，在操作一栏，点击`API信息`，API类型选择`小微铺核心API`，选中api，进行Api申请 <br>
5.通过审核后，点击左侧`文档与工具`-->`SDK下载`，下载SDK <br>
6.解压压缩包，将RopExSdk.jar放在项目下的lib文件夹下 <br>
7.打开项目，进入src/main/resources，打开application.properties文件，修改rop和db参数，rop的参数在`融数开放平台`-->`应用管理`-->`应用列表`，在操作栏点击`账号信息`查到 <br>
8.打包项目(`mvn clean package`)，将*.jar文件部署到服务器上，启动服务器 <br>

##目录结构
<pre>                              
├── src
│   └── main
│       └── java
│           └── com
│               └── lingke
│                   └── xvp
│                       └── demo
│                           ├── controller
│                           │   ├── request                 // request body
│                           │   ├── response                // response body
│                           │   ├── seller                  // 卖方相关业务处理
│                           │   ├── user                    // 用户相关业务处理
│                           │   └── CommonController.java   // 通用类相关业务处理
│                           ├── db                          
│                           │   ├── codegen                 // 生成代码
│                           │   └── dao
│                           ├── utils                       // 工具方法
│                           ├── XvpApp.java                 
│                           ├── XvpAspect.java              // 事物
│                           ├── XvpConstants.java           // 常量
│                           ├── XvpDbConfig.java            // db配置
│                           ├── XvpInterceptorConfig.java   // 拦截器配置
│                           └── XvpRopClient.java           // rop客户端
├── src
│   └── main
│       └── resources
│           ├── static                                      // 前端页面
│           ├── application.properties                      // 配置文件
│           └── logback-online.xml                          // logback配置文件
├── JRE System Library
├── Maven Dependencies
├── lib          
│   └── RopExSdk.jar                                        // SDK
├── src                
│   └── main
├── target
├── LICENSE                                                 // 版权
├── pom.xml                                                 // pom文件
└── README.md
</pre>

##License
<pre>
BSD 2-Clause License

Copyright (c) 2017, lktech
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

* Redistributions of source code must retain the above copyright notice, this
  list of conditions and the following disclaimer.

* Redistributions in binary form must reproduce the above copyright notice,
  this list of conditions and the following disclaimer in the documentation
  and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
</pre>