package com.devigroups.datacompare.controllers;

import com.devigroups.datacompare.dtos.ReplenishDataOutputCopyDTO;
import com.devigroups.datacompare.dtos.ReplenishDataOutputDTO;
import com.devigroups.datacompare.entities.GeneroData;
import com.devigroups.datacompare.entities.JavaData;
import com.devigroups.datacompare.entities.ReplenishData;
import com.devigroups.datacompare.repository.GeneroDataRepository;
import com.devigroups.datacompare.repository.JavaDataRepository;
import com.devigroups.datacompare.service.DataCompareService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class DataCompareController {

    @Autowired
    GeneroDataRepository gRepo;
    @Autowired
    JavaDataRepository jRepo;
    Logger logger = LoggerFactory.getLogger(DataCompareController.class);


    @GetMapping("/home")
    private String sampleMethod(){
        return "hi there!!!";
    }

    @GetMapping("/datacompare")
    public Optional<List<ReplenishDataOutputDTO>> dataCompare() throws Exception {
        int i=0,j=0;
        Boolean generoProcessFlag = null;
        List<ReplenishDataOutputDTO> replenishDataOutputDTOList = new ArrayList();

//        GeneroData repData1 = new GeneroData(1000001L, 3200, 113110, 1000, "Active", 10, 110);
//        JavaData repData2 = new JavaData(1000002L, 3200, 113110, 1111, "Active", 10, 110);
        GeneroData generoData = new GeneroData();
        JavaData javaData = new JavaData();

        @SuppressWarnings("SpellCheckingInspection") List<GeneroData> generoAll = gRepo.findAll();
        List<JavaData> javaAll=  jRepo.findAll();

        for (i=0; i<generoAll.size(); i++){
            generoProcessFlag = false;
            generoData = generoAll.get(i);

            for(j=0; j<javaAll.size(); j++)
                if(generoAll.get(i).getKey().equalsIgnoreCase(javaAll.get(j).getKey())){
                    javaData = javaAll.get(j);
                    if (replenishDataOutputDTOList != null) {
                        replenishDataOutputDTOList.add(DataCompareService.CompareObjects(generoData, javaData));
                    }
                    javaAll.remove(j);
                    generoProcessFlag = true;
                    break;
                }
//                else
//                    continue;
            // Add Genero record if key is not matched with Java data
            if(!generoProcessFlag)
                replenishDataOutputDTOList.add(DataCompareService.AddGeneroObject(generoData));
        }


        logger.info("Method-dataCompare: Add remaining Java records (not present in Genero): " + javaAll.size());
        for(j=0; j<javaAll.size(); j++){
            javaData = javaAll.get(j);
            replenishDataOutputDTOList.add(DataCompareService.AddJavaObject(javaData));
        }

        Optional<List<ReplenishDataOutputDTO>> optionalReplenishDataOutputDTOS
                = replenishDataOutputDTOList.size() == 0 ? Optional.empty()
                : Optional.of(replenishDataOutputDTOList);
        return optionalReplenishDataOutputDTOS;
    }

//    @GetMapping("/datacompare1")
//    private ReplenishDataOutputCopyDTO dataCompareSample(){
//        ReplenishData repData1 = new ReplenishData(1000001L, 3200, 113110, 1000, "Active", 10, 110);
//        ReplenishData repData2 = new ReplenishData(1000002L, 3200, 113110, 1111, "Active", 10, 110);
//
//        ReplenishDataOutputCopyDTO replenishDataOutputCopyDTO = DataCompareService.CompareObjectSample(repData1, repData2);
//        return replenishDataOutputCopyDTO;
//    }



  }