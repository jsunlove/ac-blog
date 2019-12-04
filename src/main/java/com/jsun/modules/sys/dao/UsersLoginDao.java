package com.jsun.modules.sys.dao;

import com.jsun.modules.sys.entity.VO.UsersVOEntity;
import org.springframework.stereotype.Repository;

/**
 * @Author: jsun
 * @ProjectName: adminsystem
 * @Package: com.jsun.modules.sys.dao
 * @Description:
 * @Date: 2019/8/17 0017 12:44
 **/
@Repository
public interface UsersLoginDao {
    UsersVOEntity findByPhone(String phone);

}
