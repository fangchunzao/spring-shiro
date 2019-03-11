package com.shiro.build.shiro;

import com.shiro.build.entity.RoleInfo;
import com.shiro.build.entity.UserInfo;
import com.shiro.build.respository.RoleRepository;
import com.shiro.build.respository.UserRepository;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * @author FCZ
 * @since 2019/3/1 16:22
 */
public class MyShiroRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    /**
     * 权限认证 给登陆的用户授予角色和权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("执行Shiro权限认证");
        //获取当前登录的用户信息
        UserInfo userInfo = (UserInfo) super.getAvailablePrincipal(principalCollection);

        // 到数据库查是否有此对象
        UserInfo loginUserInfo = userRepository.findUserInfoByUserName(userInfo.getUserName());
        if (loginUserInfo != null) {
            // 取得权限信息
            List<RoleInfo> roleInfoList = roleRepository.getRoleInfoByUserID(loginUserInfo.getId());
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            Set<String> userRoles = new HashSet<>();
            for(RoleInfo roleInfo : roleInfoList) {
                userRoles.add(roleInfo.getRoleName());
            }
//            info.addStringPermissions(userPermissions);
            info.addRoles(userRoles);
            return info;
        }
        // 返回null的话，就会导致任何用户访问被拦截的请求时，都会自动跳转到unauthorizedUrl指定的地址
        return null;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //UsernamePasswordToken 对象用来存放提交的登录信息
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //查出是否有此用户
        UserInfo loginUserInfo = userRepository.findUserInfoByUserName(token.getUsername());

        if (loginUserInfo != null) {
            // 若存在，将此用户存放到登录认证info中
            // 第一个参数为成功后存储到session中的信息 获取通过   SecurityUtils.getSubject().getPrincipal()进行获取
            return new SimpleAuthenticationInfo(loginUserInfo, loginUserInfo.getPassword(), getName());
        }
        return null;
    }

}
