package com.shiro.build.respository;

import com.shiro.build.entity.RoleInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author FCZ
 * @since 2019/3/1 14:18
 */
public interface RoleRepository  extends CrudRepository<RoleInfo,String> {

    List<RoleInfo> getRoleInfoByUserID(String userID);

}
