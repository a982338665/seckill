
**1.技术栈：**
    
    1.前端：
        Thymeleaf   --模板引擎
        Bootstrap
        Jquery
    2.后端：
        SpringBoot
        JSR303
        Mybatis
    3.中间件：
        RabbitMQ    --异步中间件 
        Redis       --缓存服务器，相对于memache可以持久化到磁盘
        Druid       --数据库连接池：监控最大连接，最大并发等数据(alibaba开源)
        
**2.开发流程：**
    
    主要目标：应对大并发-->
    1.分布式会话
    2.商品列表页
    3.商品详情页
    4.订单详情页
    5.系统压测
    6.缓存优化
    7.消息队列
    8.系统压测比对-大并发优化最重要的是db优化
    9.接口安全
    
**3.开发详情：**

    1.项目基础框架搭建：
        1.springboot环境搭建
        2.集成Thymeleaf，Result结果封装
        3.集成mybatis+Druid
        4.集成Jedis+redis安装+通用缓存key封装
    2.登录功能实现;
        1.数据库设计
        2.明文密码两次md5
        3.JSR303参数校验+全局异常处理器
        4.分布式session解决方式
    3.秒杀实现：
        1.数据库设计
        2.商品列表页
        3.商品详情页
        4.订单详情页
    4.jmeter系统压测：
        1.jmeter模拟多用户
        2.命令行使用
        3.springboot打war包
    5.页面优化技术：-->应对大并发，不单指秒杀
        1.页面缓存+url缓存+对象缓存-->减少数据库压力
        2.页面静态化，前后端分离
        3.静态资源优化，css，js...
        4.CDN优化
    6.接口优化：
        1.Redis预检库存减少数据库访问
        2.内存标记减少Redis访问
        3.RabbitMQ队列缓冲，异步下单，增强用户体验
        4.RabbitMQ安装与springboot集成
        5.访问nginx水平拓展
        6.压测
    7.安全优化：
        1.秒杀借口地址隐藏
        2.数学公式验证码
        3.接口防刷-->每个用户最多访问十次-->限流-通用手段
        
**4.报错提示：**

    1.Unregistering JMX-exposed beans on shutdown
        问题：内置tomcat没有正确启动
        解决：添加注释
            <dependency>
            	<groupId>org.springframework.boot</groupId>
          		<artifactId>spring-boot-starter-tomcat</artifactId>
          		<!--<scope>provided</scope>-->
            </dependency>
            
**5.springboot项目添加热部署支持：**

    1.添加依赖：
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <optional>true</optional>
        </dependency>
    2.添加插件：
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <configuration>
            <!--fork:设置True，否则可能devtools不会起作用-->
                <fork>true</fork>
            </configuration>
        </plugin>
    3.修改IDEA的设置File--->Other Settings--->Default Settings----->Compiler--->Bulid project automatically打上勾
    4.在application.properties还有下面两行代码：
        #配置热部署
        spring.devtools.restart.enabled= true
        #关闭thymeleaf缓存：实现静态热部署
        spring.thymeleaf.cache=false
    5.Ctrl+s保存代码，Ctrl+F9编译 查看到新的结果
    【6.ctrl+shift+alt+ /  
       Registry...
       compiler.automake.allow.when.app.running 打钩
       加上此步骤，针对静态文件修改可直接ctrl+s保存后查看结果 
    】
        
        
**6.项目构建：**
    
    1.集成springboot+thyemleaf
    2.集成mybatis：http://www.mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/
        --1.添加依赖 --查看链接
        --2.添加配置 --查看链接
    3.redis安装：
    4.redis集成：
        --添加Jedis依赖
        --添加Fastjson依赖：
            protostuff-优点：性能高,但不可读，存二进制对象
            fastjson可读：存字符串，效率相比protostuff低一倍，便于测试选fastjson
    5.redis封装--
    ——————————————————————————
    6.登录功能实现：
        --数据库设计
        --密码MD5
        --jsr303参数校验
        --全局异常处理器
        --分布式session: 
            1.原生session同步-->性能消耗大不建议
            2.登陆成功后生成token，之后操作凭token取session信息，跟单台服务器的session原理相同
            ----
            3.实现：每次请求都会得到一个新的token以便进行下次访问-->会刷新token，等同于延时cookie
    ——————————————————————————
    7.秒杀功能实现：
         1.数据库设计：商品表-商品基本信息，订单表，秒杀商品表--开始时间,结束时间,数量等
         2.商品列表页
         3.商品详情页
         4.订单详情页
    
    
    