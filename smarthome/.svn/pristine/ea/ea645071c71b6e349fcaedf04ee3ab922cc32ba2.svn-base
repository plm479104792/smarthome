package com.homecoo.smarthome.schedule.work.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.homecoo.smarthome.domain.WorkContext;
import com.homecoo.smarthome.schedule.work.LoadWorkStrategy;


/**
 * 加载work管理器
 * @author hsj
 *
 */
public class LoadWorkStrategyManager {
	
	public Map<Integer , LoadWorkStrategy> workStategyManager = new HashMap<Integer , LoadWorkStrategy>();

	public void regesitorStargegy(Integer type, LoadWorkStrategy loadWorkStrategy){
		workStategyManager.put(type, loadWorkStrategy);
	}

	public List<WorkContext> loadAll(long t) {
		List<WorkContext> workContexts = new ArrayList<WorkContext>();
		for(Integer type : workStategyManager.keySet()){
			LoadWorkStrategy loadWorkStrategy = workStategyManager.get(type);
			if(loadWorkStrategy != null){
				List<WorkContext> list = loadWorkStrategy.loadWork(t, type);
				if(list != null){
					workContexts.addAll(list);
				}
			}
		}
		return workContexts;
	}
	

}
