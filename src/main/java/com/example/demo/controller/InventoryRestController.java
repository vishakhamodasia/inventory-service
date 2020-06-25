package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Inventory;
import com.example.demo.service.InventoryService;

import brave.sampler.Sampler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/inventory")
public class InventoryRestController {
	
	@Autowired
	InventoryService inventoryService;
	
	@Bean
	public Sampler defaultSampler() {
	    return Sampler.ALWAYS_SAMPLE;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Integer> getAvailabilityCountByPId(@PathVariable int id) {
		
		  log.info("Inside inventory service..");
		  log.info("Now let's create some intentional delay..."); 
		/*
		 * try { Thread.sleep(20 1000); } catch (InterruptedException e) {
		 * e.printStackTrace(); }
		 */
		  log.info("returning afte delay..");
		 
		Inventory i;
		int count=0;
		Optional<Inventory> inventory=inventoryService.getCount(id);
		if(inventory.isPresent()) {
			i=inventory.get();
			count=i.getAvailabilityCount();
			return new ResponseEntity<>(new Integer(count),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(new Integer(-1),HttpStatus.OK);
		}
	}
}
