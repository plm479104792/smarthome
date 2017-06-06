package com.homecoo.smarthome.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.homecoo.smarthome.domain.Gateway;

public interface GatewayMapper {

    //给手机号绑定网关
    int insert(Gateway record);

    /**
     * 根据gatewayNo获取网关
     * */
    List<Gateway> selectByGatewayNo(@Param("gatewayNo")String gatewayNo);
    
    int insertSelective(Gateway record);

    int deleteByPrimaryKey(String gatewayId);

    int updateByPrimaryKeySelective(Gateway record);

    /**
     * 根据gatewayNo更新gateway
     * */
    int updateByGatewayNo(Gateway record);
}