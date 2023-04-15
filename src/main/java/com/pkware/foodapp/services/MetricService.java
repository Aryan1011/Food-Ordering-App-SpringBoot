package com.pkware.foodapp.services;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pkware.foodapp.dao.MetricsDao;
import com.pkware.foodapp.requestObject.TwoDatesObject;
import com.pkware.foodapp.response.OrderRevenueObject;

@Service
public class MetricService {

	@Autowired
	private MetricsDao metricsDao;
	public OrderRevenueObject getTodayMetrics(){
		return metricsDao.getTodayMetrics();
	}

	public OrderRevenueObject getDateMetrics(TwoDatesObject twoDatesObject) {
		return metricsDao.getDateMetrics(twoDatesObject);
	}

	public HashMap<String, Integer> getTodayItemQuantity() {
		return metricsDao.getTodayItemQuantity();
	}

	public HashMap<String, Integer> getDateItemQuantity(TwoDatesObject twoDatesObject) {
		return metricsDao.getDateItemQuantity(twoDatesObject);
	}

	public HashMap<String, Integer> getTodayCategoryPercentage() {
		return metricsDao.getTodayCategoryPercentage();
	}

	public HashMap<String, Integer> getDateCategoryPercentage(TwoDatesObject twoDatesObject) {
		return metricsDao.getDateCategoryPercentage(twoDatesObject);
	}

}
