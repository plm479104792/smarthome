package com.homecoo.smarthome.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.homecoo.smarthome.domain.Volume;
import com.homecoo.smarthome.persistence.VolumeMapper;
import com.homecoo.smarthome.service.IVolume;

@Service
public class VolumeServiceImpl  implements IVolume{

	@Autowired
	private VolumeMapper volumeMapper;
	
	
	@Override
	public int updateVolume(Volume volume) {
		int row =volumeMapper.updateVolume(volume);
		return row;
	}

	@Override
	public int deleteVolume(String gatewayNo) {
		int row=volumeMapper.deleteVolume(gatewayNo);
		return row;
	}

	@Override
	public int insertVolume(Volume volume) {
		int row=0;
		Volume record=volumeMapper.selectVolumeByGatewayNo(volume.getGatewayNo());
		if (record!=null) {
			row=volumeMapper.updateVolume(volume);
		}else{
			row=volumeMapper.insertVolume(volume);
		}
		return row;
	}

	@Override
	public Volume getVolume(String gatewayNo) {
		Volume volume=volumeMapper.selectVolumeByGatewayNo(gatewayNo);
		return volume;
	}

}
