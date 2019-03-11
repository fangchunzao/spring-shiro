# spring-shiro
springboot2.0 实现shiro登录授权管理
接口说明
localhost:8081/login  登录 用户名为username 密码为123456 均采用md5加密 第一次访问可能为404，因为没有cookie，再次访问即可。项目发布不会有这种问题
localhost:8081/user/userTest 测试用户是否有User权限
localhost:8081/admin/adminTest  测试用户是否有Admin权限
