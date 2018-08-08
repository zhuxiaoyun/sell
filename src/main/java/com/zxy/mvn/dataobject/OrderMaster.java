package com.zxy.mvn.dataobject;

import com.zxy.mvn.enums.OrderStatusEnum;
import com.zxy.mvn.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单
 */
@Entity
@DynamicUpdate
@Data
public class OrderMaster {

    @Id
    /** 订单id. */
    private String orderId;

    /** 买家名称. */
    private String buyerName;

    /** 买家手机号. */
    private String buyerPhone;

    /** 买家地址. */
    private String buyerAddress;

    /** 买家微信id. */
    private String buyerOpenid;

    /** 订单金额. */
    private BigDecimal orderAmount;

    /** 订单状态，默认0为新订单. */
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /** 支付状态，默认0为未支付. */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    /** 创建时间. */
    private Date createTime;

    /** 修改时间. */
    private Date updateTime;
}
