package com.example.demo1.mapper;


import org.apache.ibatis.annotations.Mapper;
import com.example.demo1.entity.IfoUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;

/**
 * 
 * 商户信息 Mapper 接口
 * @作者 LiuZW
 * @时间 2019-12-10
 */
@DS("esc_user")
@Mapper
public interface IfoUserMapper extends BaseMapper<IfoUser> {
 

}