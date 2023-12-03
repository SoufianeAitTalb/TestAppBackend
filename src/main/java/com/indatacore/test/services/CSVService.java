package com.indatacore.test.services;



import com.indatacore.test.dto.CSVDTO;
import com.indatacore.test.dto.UserDTO;
import com.indatacore.test.entities.CSV;
import com.indatacore.test.entities.User;

import java.util.List;

public interface CSVService {

    CSV addNewCSV(CSVDTO csvdto);

    CSV addNewCSVRandomly();



    List<CSVDTO> listCSVDTO();
    List<CSV> listCSV();

    CSV updateCSV(Long id,CSVDTO csvDto);


    void deleteCSV(Long id);
}
