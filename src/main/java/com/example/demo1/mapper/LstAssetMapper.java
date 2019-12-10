package com.example.demo1.mapper;

import com.example.demo1.entity.LstAsset;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * 资产账号记录 Mapper 接口
 * @作者 LiuZW
 * @时间 2019-12-10
 */
@DS("esc_user")
@Mapper
public interface LstAssetMapper extends BaseMapper<LstAsset> {


 }