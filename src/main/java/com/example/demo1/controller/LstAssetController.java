package com.example.demo1.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.example.demo1.entity.IfoUser;
import org.springframework.beans.BeanUtils;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo1.service.LstAssetService;
import com.example.demo1.entity.LstAsset;

import lombok.extern.log4j.Log4j2;

import java.util.List;


/**
 * <p>
 * 资产账号记录 前端控制器
 * </p>
 *
 * @author LiuZW
 * @since 2019-12-10
 */
@RestController
@RequestMapping("lstAsset")
@Log4j2
public class LstAssetController {


	@Autowired
    private LstAssetService lstAssetService;

    @RequestMapping("list")
    public List<LstAsset> listAsset(){
        return lstAssetService.listAsset();
    }



}
