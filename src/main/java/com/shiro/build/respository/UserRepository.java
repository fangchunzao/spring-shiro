package com.shiro.build.respository;

import com.shiro.build.entity.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author FCZ
 * @since 2019/1/17 16:31
 */
@Repository
public interface UserRepository extends CrudRepository<UserInfo,String> {

    UserInfo findUserInfoById(String id);

    UserInfo findUserInfoByUserName(String userName);

}
