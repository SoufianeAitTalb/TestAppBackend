package com.indatacore.test.services.impl;

import com.indatacore.test.dto.CSVDTO;
import com.indatacore.test.entities.CSV;
import com.indatacore.test.repositories.CSVRepo;
import com.indatacore.test.services.CSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CSVServiceImpl implements CSVService {

    private final CSVRepo csvRepo;

    @Autowired
    public CSVServiceImpl(CSVRepo csvRepo) {
        this.csvRepo = csvRepo;
    }

    @Override
    public CSV addNewCSV(CSVDTO csvDto) {
        if(csvDto==null) return null;
        CSV csv = CSVDTOToCSV(csvDto);
        return csvRepo.save(csv);

    }

    @Override
    public CSV addNewCSVRandomly() {
        CSV csv = new CSV();
        csv.setName("random");
        csv.setPrice(0D);
        csv.setQuantity(0L);
        csv.setCategory("random");
        return csvRepo.save(csv);
    }

    @Override
    public List<CSVDTO> listCSVDTO() {
        List<CSV> csv = csvRepo.findAll();
        return csv.stream()
                .map(this::CSVToCSVDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CSV> listCSV() {
        return csvRepo.findAll();
    }
    @Override
    public CSV updateCSV(Long id, CSVDTO csvDto) {
        CSV csv = this.csvRepo.findById(id).orElse(null);
        if(csv != null){
            csv = this.CSVDTOToCSV(csvDto);
            csv.setId(id);
            return this.csvRepo.save(csv);
        }
        return null;
    }

    @Override
    public void deleteCSV(Long id) {
        CSV csv = csvRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("recipient not found"));
        csvRepo.delete(csv);
    }


    public CSV CSVDTOToCSV(CSVDTO csvDto){
        if(csvDto==null) return null;

        CSV csv = new CSV();
        csv.setName(csvDto.getName());
        csv.setPrice(csvDto.getPrice());
        csv.setCategory(csvDto.getCategory());
        csv.setQuantity(csvDto.getQuantity());

        return csv;
    }

    public CSVDTO CSVToCSVDTO(CSV csv){
        if(csv==null) return null;

        CSVDTO csvDto = new CSVDTO();
        csvDto.setName(csv.getName());
        csvDto.setPrice(csv.getPrice());
        csvDto.setCategory(csv.getCategory());
        csvDto.setQuantity(csv.getQuantity());

        return csvDto;
    }
}
