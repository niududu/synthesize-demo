package com.example.demo1.service;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo1.entity.IfoUser;
import com.example.demo1.mapper.IfoUserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.dynamic.datasource.annotation.DS;

 /**
 * 
 * 商户信息 服务实现类
 * @作者 LiuZW
 * @时间 2019-12-10
 */
@Service
@DS("esc_user")
public class IfoUserService extends ServiceImpl<IfoUserMapper, IfoUser> {
	
	@Autowired
	private IfoUserMapper ifoUserMapper;


     public List<IfoUser> listUser() {
         QueryWrapper<IfoUser> ifoUserQueryWrapper = new QueryWrapper<>(new IfoUser());
         return  ifoUserMapper.selectList(ifoUserQueryWrapper);
     }

     public List<IfoUser> listUser(String nickName) {
         return null;
     }
 }
