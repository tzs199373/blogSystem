# 项目简介

本项目为学习前后端分离与微服务而建

iBlog：前端vue项目

其他：springboot构建的微服务项目，省去了许多组件，后续如有需要再添加

# 建表

参考zz-doc目录下sql

# 跨域	

参考zz-doc目录下nginx.conf

主要是这段：
```
 listen       81;
 server_name  localhost;
 location / {
 			#vue iBlog
 			proxy_pass http://127.0.0.1:8081/;
         }
 
 location /blogweb/ { 
            #springboot blogweb
 			proxy_pass   http://127.0.0.1:3333/blogweb/;
 		}
```
nginx开启81端口，8081是nodejs端口被81代理，3333是springboot(blogweb)端口也被81代理，从而解决跨域

# 项目启动

iBlog：该目录下先安装依赖：npm install，再运行：npm run dev

其他：依次启动eureka、web、engine

nigix:修改conf配置并启动，见上文“关于跨域”

访问：http://127.0.0.1:81/