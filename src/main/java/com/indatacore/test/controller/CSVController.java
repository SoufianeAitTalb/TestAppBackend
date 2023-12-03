package com.indatacore.test.controller;

import com.indatacore.test.dto.CSVDTO;
import com.indatacore.test.dto.UserDTO;
import com.indatacore.test.entities.CSV;
import com.indatacore.test.entities.User;
import com.indatacore.test.services.CSVService;
import com.indatacore.test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("**")
@RestController
@RequestMapping("api/csv")
public class CSVController {

    private final CSVService csvService;

    @Autowired
    public CSVController(CSVService csvService){
        this.csvService = csvService;
    }

    @GetMapping("/")
    public List<CSV> getAllCSV(){
        return this.csvService.listCSV();
    }
    @GetMapping("/dto")
    public List<CSVDTO> getAllCSVDTO(){
        return this.csvService.listCSVDTO();
    }



    @PostMapping("/add-csv")
    public ResponseEntity<Map<String, String>> addNewCSV(@RequestBody CSVDTO csvDto) {
    Map<String, String> response = new HashMap<>();

    if (this.csvService.addNewCSV(csvDto) != null) {
        response.put("status", "success");
        response.put("message", "csv line added successfully");
        return ResponseEntity.ok(response);
    }

    response.put("status", "error");
    response.put("message", "An error occurred");
    return ResponseEntity.badRequest().body(response);
}


    @PostMapping("/add-csv-random")
    public ResponseEntity<Map<String, String>> addNewCSVRandomly() {
        Map<String, String> response = new HashMap<>();

        if (this.csvService.addNewCSVRandomly() != null) {
            response.put("status", "success");
            response.put("message", "csv line added successfully");
            return ResponseEntity.ok(response);
        }

        response.put("status", "error");
        response.put("message", "An error occurred");
        return ResponseEntity.badRequest().body(response);
    }

    @PutMapping("/update-csv/{id}")
    public ResponseEntity<String> updateCSV(@PathVariable Long id,@RequestBody CSVDTO csvDto) {

        if(this.csvService.updateCSV(id, csvDto) != null )
            return ResponseEntity.ok("Edited successfully");

        return ResponseEntity.badRequest().body("Error");

    }

    @DeleteMapping("/delete-csv/{id}")
    public ResponseEntity<String> deleteCSV(@PathVariable Long id){
        this.csvService.deleteCSV(id);
        return ResponseEntity.ok("CSV Line deleted");
    }







}
