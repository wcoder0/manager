package com.manager.system.mapper;

import com.manager.system.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hannibal
 * @since 2021-05-09
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
