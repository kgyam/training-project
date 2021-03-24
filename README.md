# training-project



# 第二次作业

- 原来的依赖查找和注入代码被放到customer-ioc这个包下，可能跟小马哥的实现有一点区别
- 关于@RequestMapping功能的问题，应该是可以路由到指定@RequestMapping配置的controller下的
- jar文件夹下存放的是可执行jar

---
# 第三次作业

- jolokia 代理servlet的需求mbean实现在customer-web-mvc的src/main/java/org/example/web/mvc/jmx路径下。功能主要实现通过jolokia的servlet代理调用DispatcherServletManagerMBean的setAttribute方法，对应的postman测试在放jar的目录下

- microprofile的实现customer-ioc的src/main/java/org/example/ioc/configuration路径下。操作系统环境变量和java的环境变量为默认配置，无需spi。

  - 读取配置文件的方式需要使用spi的方式加载类，配置文件为META-INF/customer-application.properties（配置在web工程目录下）


---
# 第四次作业
- jar文件夹下存放的是对应的war包
- **关于Servlet容器初始化相关配置和组件**，个人认为是跟web相关的一个过程，所以配置和组件的加载在**customer-web-mvc**下
	- Servlet容器初始化代码具体路径在src/main/java/org/example/web/mvc/init/ServletInitializer.java(SPI方式)
	- 监听器具体路径src/main/java/org/example/web/mvc/listener/路径下
	- Servlet配置源扩展的代码路径src/main/java/org/example/web/mvc/configuration/ServletConfigSource.java
	
- **关于通过ThreadLocal获取Config**，这里通过重新扩展一个基于ThreadLocal实现的ThreadLocalConfigProviderResolver ，具体路径src/main/java/org/example/configuration/ThreadLocalConfigProviderResolver.java。(但这里感觉个人对于需求理解有点问题)
  - ConfigProviderResolver基于SPI扩展
  - **测试方法**可使用上周的需求查询项目配置中的application.name，请求路径：/user/application

  