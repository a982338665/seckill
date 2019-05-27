**linux安装：**

    1.安装erlang
        0.安装依赖：
            yum install ncurse-devel 或者
            yum -y install make gcc gcc-c++ kernel-devel m4 ncurses-devel openssl-devel unixODBC-devel
        1.http://www.erlang.org/downloads/20.3 --> http://erlang.org/download/otp_src_20.3.tar.gz
            wget http://erlang.org/download/otp_src_20.3.tar.gz
            tar xf otp_src_20.3.tar.gz
            cd otp_src_20.3
            ./configure --prefix=/usr/local/erlang --with-ssl -enable-threads -enable-smmp-support -enable-kernel-poll --enable-hipe --without-javac
            make 
            make install
            验证：
            cd erlang/bin
            ./erl
            进入shell 
            exit 退出
        2.必要操作--> 添加系统环境变量
            vim /etc/profile
                export PATH=$PATH:/usr/local/erlang/bin
            soucre /etc/profile
    2.安装rabbitmq:
        1.安装python：yum install python -y
        2.安装simplejson：yum install xmlto -y
        3.yum install python-simplejson -y
        4.https://www.rabbitmq.com/install-generic-unix.html 
          wget https://github.com/rabbitmq/rabbitmq-server/releases/download/v3.7.15/rabbitmq-server-generic-unix-3.7.15.tar.xz
            xz -d rabbitmq-server-generic-unix-3.6.3.tar.xz
            tar -xvf rabbitmq-server-generic-unix-3.6.3.tar       
        5.解压可使用
        6.验证：
            cd sbin
            ./rabbirmq-server
            tail -f ..log
        7.关闭： ./rabbitmqctl stop
            ./rabbitmqctl start
            ./rabbitmq-server           --启动
            netstat -nap |grep 5672     --查看端口
            ps -ef |grep rabbitmq
        8.添加系统环境变量 根目录启动
            vim /etc/profile
                添加：
                export PATH=$PATH:/usr/local/ruby/bin;/usr/local/erlang/bin;/usr/local/rabbitmq/sbin
            esc
            :wq
            source /etc/profile
            rabbitmq-server
        9.启用web管理界面
          ./rabbitmq-plugins enable rabbitmq_management
          启动
          ./rabbitmq-server -detached
          添加用户
          ./rabbitmqctl add_user admin 111111
          设置权限
          ./rabbitmqctl set_user_tags admin administrator
        
**2.程序远程连接时配置：**

    1.账号：guest
      密码：guest
      以上只支持本地连接，若要远程需要在服务器配置 
      www.rabbitmq.com/access-control.html
       1.cd /usr/local/rabbitmq/etc/rabbitmq/
       2.vim rabbitmq.config
            [{rabbit, [{loopback_users, []}]}].
       3.重启
    2.www.rabbitmq.com/download.html