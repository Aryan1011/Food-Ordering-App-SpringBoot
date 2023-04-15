package com.pkware.foodapp.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pkware.foodapp.requestObject.TwoDatesObject;
import com.pkware.foodapp.response.OrderRevenueObject;
import com.pkware.foodapp.services.MetricService;

@RestController
@RequestMapping({"/metric"})
public class MetricController {
	@Autowired
	private MetricService metricService;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping({"/todaymetrics"})
	public OrderRevenueObject getTodayMetrics() {
		return this.metricService.getTodayMetrics();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping({"/datemetrics"})
	public OrderRevenueObject getTodayMetrics(@RequestBody TwoDatesObject twoDatesObject) {
		return this.metricService.getDateMetrics(twoDatesObject);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping({"/todayitemquantity"})
	public HashMap<String, Integer> getTodayItemQuantity() {
		return this.metricService.getTodayItemQuantity();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping({"/dateitemquantity"})
	public HashMap<String, Integer> getDateItemQuantity(@RequestBody TwoDatesObject twoDatesObject) {
		return this.metricService.getDateItemQuantity(twoDatesObject);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping({"/todaycategorypercentage"})
	public HashMap<String, Integer> getTodayCategoryPercentage() {
		return this.metricService.getTodayCategoryPercentage();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping({"/datecategorypercentage"})
	public HashMap<String, Integer> getDateCategoryPercentage(@RequestBody TwoDatesObject twoDatesObject) {
		return this.metricService.getDateCategoryPercentage(twoDatesObject);
	}
	
	
}
