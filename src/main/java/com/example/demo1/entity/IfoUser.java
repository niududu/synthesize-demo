package com.example.demo1.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.joda.time.DateTime;

/**
 *
 * 商户信息-数据库实体对象
 * @作者 LiuZW
 * @时间 2019-12-10
 */
@Data
@TableName("esc_user.ifo_user")
public class IfoUser implements Serializable  {

    private static final long serialVersionUID = 1L;



    /**
     * 顺序编号，程序内部使用，代表唯一客户
     */
	@TableId(value="usr_sqn", type= IdType.AUTO)
	private Integer usrSqn;
    /**
     * 所属机构(或商户)序号
     */
	private Integer vdrSqn;
    /**
     * 所属用户序号
     */
	private Integer bkrSqn;
    /**
     * 用户编码
     */
	private String usrCode;
    /**
     * 真实姓名
     */
	private String usrName;
    /**
     * 生日
     */
	private Data bthDay;
    /**
     * 性别，0,1->男,女
     */
	private Integer usrSex;
    /**
     * 昵称
     */
	private String usrNick;
    /**
     * 电话号码，登录账号
     */
	private String usrMobile;
    /**
     * 微信系列统一账号
     */
	private String uniIdntfir;
    /**
     * QQ系列统一账号
     */
	private String tqqIdntfir;
    /**
     * 新浪微博统一账号
     */
	private String wboIdntfir;
    /**
     * 提现口令
     */
	private String usrPass;
    /**
     * 头像
     */
	private String urlIcon;
    /**
     * 邀请码
     */
	private String urlCode;
    /**
     * 海报-应用端
     */
	private String urlPoster;
    /**
     * 海报-小程序
     */
	private String urlApplet;
    /**
     * VIP下载海报
     */
	private String urlVip;
    /**
     * 用户端邀请用户注册海报地址
     */
	private String urlIvtUsr;
    /**
     * 用户端邀请商户注册海报地址
     */
	private String urlIvtVdr;
    /**
     * 用户状态，0~2->正常,违规,非人
     */
	private Integer usrStatus;
    /**
     * 省点比率，整数
     */
	private Integer sdxRate;
    /**
     * 省市区位置
     */
	private String usrLocation;
    /**
     * 经度，五位小数
     */
	private String gpsLongi;
    /**
     * 纬度，五位小数
     */
	private String gpsLati;
    /**
     * 是否优惠提醒，0,1->否,是
     */
	private Integer flgRemind;
    /**
     * 注册时间
     */
	private DateTime tmeRegister;
    /**
     * 用户最近消费时间
     */
	private DateTime tmeConsume;
    /**
     * 此次变动被结算的日期，未结算必须为NULL
     */
	private Date daySettle;
    /**
     * 排序
     */
	private Integer sorts;
    /**
     * 名称
     */
	private String name;
    /**
     * APP主键
     */
	private String appId;
    /**
     * 状态
     */
	private Boolean status;
    /**
     * 删除
     */
	private Boolean isDel;
    /**
     * 创建人id
     */
	private String createId;
    /**
     * 创建时间
     */
	private DateTime createTime;
    /**
     * 更新时间
     */
	private DateTime updateTime;


}
