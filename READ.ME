本项目为oauth授权中心
feng-cloud
├── Readme.md                   // help
├── feng-cloud-oauth-app        //应用事例
├── feng-cloud-oauth-eureka     //eureka注册中心
|   └──.cert                    //公钥
└── feng-cloud-oauth-uaa        //授权中心
    ├── sql                     //sql
    └──.jks                     //私钥

因为此版本oauth依赖的security版本大于5.x，如果请求中密码为明文应该配置加密方式(NoOpPasswordEncoder.getInstance()
),否则默认使用默认支持四种授权方式(DelegatingPasswordEncoder的BCryptPasswordEncoder加密)，数据库中也要使用对应加密后数据保存


--密码模式
curl -X POST -H "Authorization:Basic Y2xpZW50XzE6MTIzNDU2" http://localhost:5000/oauth/token
-d"grant_type=password&username=admin&password=123456"

--客户端模式
curl -X POST -H "Authorization:Basic Y2xpZW50XzE6MTIzNDU2" http://localhost:5000/oauth/token
-d"grant_type=client_credentials&client_id=client_1&client_secret=123456"

--简易模式
浏览器访问  http://localhost:5000/oauth/authorize?response_type=token&client_id=client_1&redirect_uri=https://baidu.com
取得access_token后,下次请请求中带上此参数就行
--授权码模式
浏览器访问  http://localhost:5000/oauth/authorize?response_type=code&client_id=client_1&redirect_uri=https://baidu.com
