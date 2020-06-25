package com.example.demo.service;

import java.util.Optional;

import com.example.demo.model.Inventory;

public interface InventoryService {
	Optional<Inventory> getCount(int id);
}
