
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
            --关于价格设计，通常使用整数类型，此处为了方便使用小数类型 
            --关于主键id，不建议使用主键自增，此处为方便使用
              建议id：
                --uuid去中划线
                --snowflake算法
         2.商品列表页
         3.商品详情页
         4.订单详情页
    ———————————————————————————
    8.jmeter压测：
        1.jmeter入门      --查看boodTools仓库下的 https://github.com/a982338665/buildTool/tree/master/jmeter
            --0.测试环境：
                redis 4.0.11 命令：redis-server -v
                mydql 5.6.41 命令：mysql -v 
                CentOS Linux release 7.5.1804 
                ...
            --1.商品列表测试结果 F:\github-work\seckill\a-seckill\src\main\resources\test\first      /goods/index_list
            --2.获取用户信息测试结果 F:\github-work\seckill\a-seckill\src\main\resources\test\first   /user/info
            ========================
            --优化：
                --1.增加jdbc连接数
                    spring.datasource.druid.initial-size=100
                    spring.datasource.druid.min-idle=500
                    spring.datasource.druid.max-active=1000
                --2.增加redis连接数
                    spring.redis.timeout=10
                    spring.redis.pool.max-wait=500
                    spring.redis.pool.max-idle=500
                    spring.redis.pool.max-active=1000
        2.自定义变量模拟多用户
            --见位置：F:\github-work\seckill\a-seckill\src\main\resources\test\cvs
            --
        3.命令行使用：服务器压测方式
        4.redis压测工具:redis-benchmark
        5.springboot打war包   
            --1.添加依赖--编译时使用
                <dependency>
                	<groupId>org.springframework.boot</groupId>
                	<artifactId>spring-boot-starter-tomcat</artifactId>
                	<scope>provided</scope>
                </dependency>
            --2.添加插件
                <build>
                	<finalName>${project.artifactId}</finalName>
                	<plugins>
                		<plugin>
                			<groupId>org.springframework.boot</groupId>
                			<artifactId>spring-boot-maven-plugin</artifactId>
                			<configuration>
                				<!--fork :  如果没有该项配置，肯定devtools不会起作用，即应用不会restart -->
                				<fork>true</fork>
                			</configuration>
                			<executions>
                				<execution>
                					<goals>
                						<goal>repackage</goal>
                					</goals>
                				</execution>
                			</executions>
                		</plugin>
                		<plugin>
                			<groupId>org.apache.maven.plugins</groupId>
                			<artifactId>maven-war-plugin</artifactId>
                			<configuration>
                				<failOnMissingWebXml>false</failOnMissingWebXml>
                			</configuration>
                		</plugin>
                
                	</plugins>
                </build>
            --3.修改启动类：
                public class ASeckillApplication extends SpringBootServletInitializer{
                
                	public static void main(String[] args) {
                		SpringApplication.run(ASeckillApplication.class, args);
                	}
                
                	@Override
                	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
                		return builder.sources(ASeckillApplication.class);
                	}
                }
            --4.以上修改可支持war包：如不支持项目名则部署在tomcat中webapp下的ROOT下面，访问可不带项目名
        6.jar包启动+命令行测试：--linux下：
            --0.cd /usr/local/project
            --1.linux下载jmeter
                wget http://mirrors.shu.edu.cn/apache//jmeter/binaries/apache-jmeter-5.0.tgz
            --2.传输打好的jar包进来：a-seckill.jar
            --3.后台启动并将进程日志输出到nohup.out中：nohup java -jar a-seckill.jar &
            ======================================
            --4.准备压测脚本：
                1,windows上录好jmx，传输到linux下/usr/local/project
                    drwxr-xr-x. 8 root root      138 2018-10-15 14:13:17 apache-jmeter-5.0
                    -rw-r--r--. 1 root root 53847192 2018-09-18 16:13:59 apache-jmeter-5.0.tgz
                    -rw-r--r--. 1 root root 34361400 2018-10-15 14:07:49 a-seckill.jar
                    -rw-r--r--. 1 root root     8264 2018-10-15 14:30:37 create-windows-jmx-goods-list.jmx
                    -rw-------. 1 root root     9956 2018-10-15 14:18:53 nohup.out
                2.命令行：sh jmeter,sh -n -t xxx.jmx -l result.jtl
                    ./apache-jmeter-5.0/bin/jmeter.sh -n -t create-windows-jmx-goods-list.jmx -l result.jtl
                    --打开另一个ssh：top 查看负载
                    --查看当前cpu个数：cat /proc/cpuinfo |grep processor
                    --可以看出严重负载问题
                3.将result.jtl导入jmeter --windows
                
**7.秒杀接口压测：**
 
     --UserUtils生成用户数据，模拟登陆获取token
     --写压测脚本：jmeter
         --参数：token
         --商品id：1
     --经测试;出现秒杀商品卖多的情况--需修改++++++++++++++++++++++++++++
        --1.将锁迁移到数据库保证库存大于零：减库存sql加条件
        --2.将秒杀订单表添加联合索引，保证同一个用户只能秒杀一件相同的商品

**8.秒杀优化开始:减少mysql数据库访问**

    1.页面优化技术：
        1.页面缓存+url缓存+对象缓存:商品列表缓存-商品详情缓存 HtmlGoodsController------将页面信息放在服务端渲染，并缓存
            --取缓存
            --手动渲染模板
            --结果输出
            =================
            ·页面缓存/url缓存 有效性较短，尽量保持数据更新  HtmlGoodsController
            ==更细粒度的缓存：===============
            ·对象缓存：根据key获取对象信息做永久缓存，修改也要做同步修改缓存  SUserService
            +++++++++++++++++
            优化完成jmeter压测对比
            
        2.页面静态化，前后端分离-获取数据json渲染，放弃使用Thymeleaf  --ajax渲染页面，不用每次去下载静态页面，直接缓存在浏览器端
            ------主要页面信息放在浏览器中缓存，即html页面与服务端数据交互完全采用ajax交互，不用每次访问服务端获取页面，使用ctrl+F5可以不使用浏览器缓存
            --常用Angular js、vue.js 此次使用jquery模拟  
            --优点，利用浏览器缓存
            --秒杀静态化
        3.静态资源优化
        4.CND优化
    2.
        
**9.优化后负载变化：**
    
    1.原始：
        QPS 1267   
        负载loader:15
        mysql 较高
    2.页面缓存+url缓存+对象缓存:
        QPS 2884
        负载loader:5
        mysql 较低    
    3.页面静态化，前后端分离：html+ajax   https://www.layui.com/doc/modules/laytpl.html
    
        
            
            
            
            
            
            
                      
    