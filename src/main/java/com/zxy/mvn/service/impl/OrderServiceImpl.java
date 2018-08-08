package com.zxy.mvn.service.impl;

import com.zxy.mvn.converter.OrderMaster2OrderDTOConverter;
import com.zxy.mvn.dataobject.OrderDetail;
import com.zxy.mvn.dataobject.OrderMaster;
import com.zxy.mvn.dataobject.Product;
import com.zxy.mvn.dto.CartDTO;
import com.zxy.mvn.dto.OrderDTO;
import com.zxy.mvn.enums.OrderStatusEnum;
import com.zxy.mvn.enums.PayStatusEnum;
import com.zxy.mvn.enums.ResultEnum;
import com.zxy.mvn.exception.MvnException;
import com.zxy.mvn.repository.OrderDetailRepository;
import com.zxy.mvn.repository.OrderMasterRepository;
import com.zxy.mvn.repository.ProductRepository;
import com.zxy.mvn.service.OrderService;
import com.zxy.mvn.service.ProductService;
import com.zxy.mvn.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import sun.text.CollatorUtilities;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        //订单id
        String orderId = KeyUtil.genUniqueKey();
        //订单总价
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

        //1. 查询商品（数量、价格）
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            Product product = productRepository.findOne(orderDetail.getProductId());
            if (product == null) {
                throw new MvnException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            //2. 计算订单总价
            orderAmount = product.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductNum()))
                    .add(orderAmount);

            //订单详情入库
            BeanUtils.copyProperties(product, orderDetail);
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);

            orderDetailRepository.save(orderDetail);
        }

        //3. 写入订单数据库（orderMaster + orderDetail）
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);

        //4. 扣库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductNum()))
                .collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if (orderMaster == null) {
            throw new MvnException(ResultEnum.ORDER_NOT_EXIST);
        }

        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new MvnException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());
        Page<OrderDTO> orderDTOPage = new PageImpl<OrderDTO>(orderDTOList, pageable, orderMasterPage.getTotalElements());
        return orderDTOPage;
    }

    @Override
    public OrderDTO pay(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO cancle(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }
}
