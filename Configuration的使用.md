
#参考原文出处：https://www.cnblogs.com/duanxz/p/7493276.html
**1.简介：**

    　1.从Spring3.0，@Configuration用于定义配置类，可替换xml配置文件，
      2.被注解的类内部包含有一个或多个被@Bean注解的方法，
        这些方法将会被AnnotationConfigApplicationContext或AnnotationConfigWebApplicationContext类进行扫描，并用于构建bean定义，
        初始化Spring容器

**2.使用要求：**

    @Configuration注解的配置类有如下要求：
       -- @Configuration不可以是final类型；
       -- @Configuration不可以是匿名类；
       -- 嵌套的configuration必须是静态类
       
**3.使用方式：具体代码移步seckill\a-seckill\src\main\java\pers\li\aseckill\study\configuation**

    1.用@Configuration加载spring
        1.1、@Configuration配置spring并启动spring容器
        1.2、@Configuration启动容器+@Bean注册Bean
        1.3、@Configuration启动容器+@Component注册Bean
        1.4、使用 AnnotationConfigApplicationContext 注册 AppContext 类的两种方法
        1.5、配置Web应用程序(web.xml中配置AnnotationConfigApplicationContext)
    2.组合多个配置类
        2.1、在@configuration中引入spring的xml配置文件
        2.2、在@configuration中引入其它注解配置
        2.3、@configuration嵌套（嵌套的Configuration必须是静态类）
    3、@EnableXXX注解
    4、@Profile逻辑组配置
    5、使用外部变量   