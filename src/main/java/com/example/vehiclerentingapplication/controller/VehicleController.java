package com.example.vehiclerentingapplication.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.vehiclerentingapplication.requestedto.VehicleRequest;
import com.example.vehiclerentingapplication.respondedto.VehicleResponse;
import com.example.vehiclerentingapplication.service.VehicleService;
import com.example.vehiclerentingapplication.util.ResponseStructure;

 
 

@RestController
public class VehicleController {

	private final VehicleService vehicleService;

	public VehicleController(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}

	@PostMapping("register/vehicles")
	public ResponseEntity<ResponseStructure<VehicleResponse>> registerMUV(@RequestBody VehicleRequest request) {
		VehicleResponse response = vehicleService.register(request);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ResponseStructure.create(HttpStatus.CREATED.value(), "Vehicle Created", response));
	}

	@GetMapping("/vehicles/find-all")
	public ResponseEntity<ResponseStructure<List<VehicleResponse>>> findAllVehicles() {
		List<VehicleResponse> vehicleResponses = vehicleService.findAllVehicles();
		return ResponseEntity.status(HttpStatus.FOUND)
				.body(ResponseStructure.create(HttpStatus.FOUND.value(), "All Vehicles Found", vehicleResponses));
	}

	@PutMapping("/vehicle/update-details")
	public ResponseEntity<ResponseStructure<VehicleResponse>> updateVehicle(@RequestParam("vehicleId") int vehicleId,
			@RequestBody VehicleRequest request) {
		VehicleResponse response = vehicleService.updateVehicleById(vehicleId, request);
		return ResponseEntity.status(HttpStatus.OK)
				.body(ResponseStructure.create(HttpStatus.OK.value(), "Vehicle Updated Successfully", response));
	}
}