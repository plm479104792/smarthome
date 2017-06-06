package com.homecoo.smarthome.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homecoo.smarthome.domain.Infrared;
import com.homecoo.smarthome.persistence.InfraredMapper;
import com.homecoo.smarthome.service.IInfrared;

@Service
public class InfraredService implements IInfrared {
	
	@Autowired
	private InfraredMapper infraredMapper;

	@Override
	public void SaveOrUpdateInfrared(Infrared infrared) {
		try {
			
		if (infrared.getInfraredId()==null || infrared.getInfraredId().equals("")) {
			infraredMapper.insertInfrared(infrared);
		}else{
//			infraredMapper.updateBydeviceNoAndTypeId(infrared);
		}
		} catch (Exception e) {
			
		}
		
	}

	@Override
	public void deleteInfraredBydeviceNo(String deviceNo) {
		infraredMapper.deleteInfraredByDeviceNo(deviceNo);
	}

	@Override
	public void deleteInfraredBydeviceNoAndTypeId(Infrared infrared) {
		infraredMapper.deleteInfraredByTypeIdAndthemeNo(infrared);
	}

	@Override
	public List<Infrared> getAllInfraredBygatewayNo(String gatewayNo) {
		List<Infrared> list= infraredMapper.selectInfraredByGatewayNo(gatewayNo);
		return list;
	}

	@Override
	public void deleteInfraredBygatewayNo(Infrared infrared) {
		infraredMapper.deleteInfraredBygatewayNo(infrared);
	}


}
