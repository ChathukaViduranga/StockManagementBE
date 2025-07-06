package com.srmobile.dashboard.rest;

import com.srmobile.dashboard.dao.RepairDAO;
import com.srmobile.dashboard.entity.Repair;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class RepairRestController {

    RepairDAO repairDAO;
    public RepairRestController(RepairDAO repairDAO) {
        this.repairDAO = repairDAO;
    }

    @GetMapping("/repairs")
   public List<Repair> getAllRepairs(){
        return repairDAO.findAll();
    }

    @PostMapping("/repairs")
    public Repair saveRepair(@RequestBody Repair repair){
        return repairDAO.save(repair);
    }

    @PutMapping("/repairs/{id}")
    public Repair changeStatus(@PathVariable Long id, @RequestBody Repair.RepairStatus status){
        return repairDAO.changeStatus(id, status);
    }

    @GetMapping("/repairs/{id}")
    public Repair getRepairById(@PathVariable Long id){
        return repairDAO.findById(id);
    }

}
