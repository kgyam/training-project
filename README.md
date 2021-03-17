# training-project



# 第二次作业

- 原来的依赖查找和注入代码被放到customer-ioc这个包下，可能跟小马哥的实现有一点区别
- 关于@RequestMapping功能的问题，应该是可以路由到指定@RequestMapping配置的controller下的
- jar文件夹下存放的是可执行jar

# 第三次作业

- jolokia 代理servlet的需求mbean实现在customer-web-mvc的src/main/java/org/example/web/mvc/jmx路径下。功能主要实现通过jolokia的servlet代理调用DispatcherServletManagerMBean的setAttribute方法，对应的postman测试在放jar的目录下

- microprofile的实现customer-ioc的src/main/java/org/example/ioc/configuration路径下。操作系统环境变量和java的环境变量为默认配置，无需spi。

  - 读取配置文件的方式需要使用spi的方式加载类，配置文件为META-INF/customer-application.properties（配置在web工程目录下）

  