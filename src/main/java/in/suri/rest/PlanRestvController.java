package in.suri.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.suri.entity.Plan;
import in.suri.service.PlanService;

@RestController
public class PlanRestvController {
	
	@Autowired
	private PlanService planService;
	
	@GetMapping("/categories")
	public ResponseEntity<Map<Integer, String>> planCategories(){
		Map<Integer, String> categories = planService.getPlanCategories();
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}
	
	@PostMapping("/plan")
	public ResponseEntity<String> savePlan(@RequestBody Plan plan){
		
		String responseMsg = "";
		
		boolean isSaved = planService.savePlan(plan);
	
		if(isSaved) {
			responseMsg = "Plan saved";
		} else {
			responseMsg = "Plan not saved";
		}
		return new ResponseEntity<String>(responseMsg, HttpStatus.CREATED);
	}
	
	@GetMapping("/plans")
	public ResponseEntity<List<Plan>> plans(){
		List<Plan> allPlans = planService.getAllPlans();
		return new ResponseEntity<>(allPlans, HttpStatus.OK);
	}
	
	@GetMapping("plan/{planId}")
	public ResponseEntity<Plan> editPlan(@PathVariable Integer planId){
		Plan plan = planService.getPlanById(planId); 
		return new ResponseEntity<Plan>(plan, HttpStatus.OK);
	}
	
	@PutMapping("/plan")
	public ResponseEntity<String> updatePlan(@RequestBody Plan plan){
		
		boolean isUpdated = planService.updatePlan(plan);
		
        String responseMsg = "";
		
		if(isUpdated) {
			responseMsg = "Plan updated";
		} else {
			responseMsg = "Plan not updated";
		}
		return new ResponseEntity<String>(responseMsg, HttpStatus.OK);
	
	}
	
	@DeleteMapping("delete/{planId}")
	public ResponseEntity<String> deletePlan(@PathVariable Integer planId){
		  
		boolean isDeleted = planService.deletePlanById(planId);
		
		String msg = "";
		
		if(isDeleted) {
			msg = "Plan deleted";
		} else {
			msg = "Plan Not deleted";
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	
	@PutMapping("/status-change")
	public ResponseEntity<String> statusChange(@PathVariable Integer planId,@PathVariable String status){
		
	    boolean isStatusChanged = planService.planStatusChange(planId, status);
	    
        String msg = "";
		
		if(isStatusChanged) {
			msg = "Status Changed";
		} else {
			msg = "Status Not Changed";
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	    
}


