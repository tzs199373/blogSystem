# 项目简介

本项目为学习前后端分离与微服务而建

iBlog：前端vue项目

其他：springboot构建的微服务项目，省去了许多组件，后续如有需要再添加

# 建表

参考zz-doc目录下sql

# vue访问根路径设置

主要是在跨域中方便url映射，iBlog访问根路径为/iblog/,iBlog工程已做以下修改

1. iBlog工程中index.html添加 
```html
    <meta base="/iblog/">
```

2. iBlog工程中配置路由的index.js添加 mode: 'history', base: '/iblog/',
```javascript
   export default new Router({
        mode: 'history', 
        base: '/iblog/',
        routes: [
          {
            path: '/',
            name: 'HelloWorld',
            component: HelloWorld
          }, {
            path: '/article',
            name: 'Article',
            component: Article
          }
        ]
      })
```

3. iBlog工程 config\index.js中 assetsPublicPath: '/iblog/',

# ngnix解决跨域	

参考zz-doc目录下nginx.conf

主要是这段：
```
 listen       81;
 server_name  localhost;
 location /iblog/ {
 			#vue iBlog
 			proxy_pass http://127.0.0.1:8081/iblog/;
         }
 
 location /blogweb/ { 
            #springboot blogweb
 			proxy_pass   http://127.0.0.1:3333/blogweb/;
 		}
```
nginx开启81端口，8081是nodejs(iBlog)端口被81代理，3333是springboot(blogweb)端口也被81代理，从而解决跨域

# zuul解决跨域

参考zuul子工程
	
主要是配置文件中这段：
```properties
zuul.routes.iblog.url=http://127.0.0.1:8081/iblog/
zuul.routes.iblog.path=/iblog/**

zuul.routes.blogweb.url=http://127.0.0.1:3333/blogweb
zuul.routes.blogweb.path=/blogweb/**
```
# 其他方法解决跨域

不管是nginx还是zuul，都是使用网关路由，避免跨域

另外可以服务允许跨域的方法，本例不作演示

# 项目启动

1、iBlog：先安装好nodejs，该目录下先安装依赖：npm install，再运行：npm run dev

2、服务：依次启动eureka、blogweb、blogengine

3、nigix:先安装好nginx，修改conf配置并启动，见上文“ngnix解决跨域”，启动nginx

   或者使用zuul:启动zuul即可

访问：http://127.0.0.1:81/iblog，不要使用localhost，配置的是127.0.0.1，localhost也会导致跨域从而失败

# 端口
blogengine:2222
blogweb:3333
eureka:1111
zuul/nginx:81