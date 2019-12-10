package com.example.demo1.service;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo1.entity.LstAsset;
import com.example.demo1.mapper.LstAssetMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.dynamic.datasource.annotation.DS;

 /**
 * 
 * 资产账号记录 服务实现类
 * @作者 LiuZW
 * @时间 2019-12-10
 */
@Service
@DS("esc_user")
public class LstAssetService extends ServiceImpl<LstAssetMapper, LstAsset> {
	
	@Autowired
	private LstAssetMapper lstAssetMapper;


     public List<LstAsset> listAsset() {
         QueryWrapper<LstAsset> lstAssetQueryWrapper = new QueryWrapper<>(new LstAsset());
         return  lstAssetMapper.selectList(lstAssetQueryWrapper);
     }
 }
