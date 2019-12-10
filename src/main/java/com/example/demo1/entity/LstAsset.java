package com.example.demo1.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
/**
 *
 * 资产账号记录-数据库实体对象
 * @作者 LiuZW
 * @时间 2019-12-10
 */
@Data
@TableName("esc_user.lst_asset")
public class LstAsset implements Serializable  {

    private static final long serialVersionUID = 1L;



    /**
     * 序号
     */
	@TableId(value="ast_sqn", type= IdType.AUTO)
	private Integer astSqn;
    /**
     * 商户序号
     */
	private Integer usrSqn;
    /**
     * 资产类型，对应ifo_asset表
     */
	private Integer astType;
    /**
     * 子类型，0,1->公,私
     */
	private Integer lsnType;
    /**
     * 余额，必须>=0
     */
	private BigDecimal balance;
    /**
     * 累计收入，必须>=0
     */
	private BigDecimal aclIncome;
    /**
     * 累计支出，必须>=0
     */
	private BigDecimal aclOutcome;
    /**
     * 累计收入数量，必须>=0
     */
	private Integer cntIncome;
    /**
     * 累计支出数量，必须>=0
     */
	private Integer cntOutcome;
    /**
     * 累计贡献上级1，必须>=0
     */
	private BigDecimal aclDonate1;
    /**
     * 累计贡献上级2，必须>=0
     */
	private BigDecimal aclDonate2;
    /**
     * 账号信息，支付宝(user_id)和微信(openid)
     */
	private String accIdntfir;
    /**
     * 头像地址，支付宝和微信
     */
	private String urlHead;
    /**
     * 昵称，支付宝和微信
     */
	private String usrNick;
    /**
     * 银行名称，银联
     */
	private String bnkName;
    /**
     * 银行位置
     */
	private String bnkLocation;
    /**
     * 银行户名，银联
     */
	private String accName;
    /**
     * 银行卡号，银联
     */
	private String accNumber;
    /**
     * 是否有效，0,1->否,是
     */
	private Integer flgValid;
    /**
     * 是否启用，0,1->否,是
     */
	private Integer flgEnable;
    /**
     * 此次申请被结算的日期，未结算必须为NULL
     */
	private LocalDate daySettle;
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
	private LocalDateTime createTime;
    /**
     * 更新时间
     */
	private LocalDateTime updateTime;


}
