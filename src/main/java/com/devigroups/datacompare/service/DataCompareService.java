package com.devigroups.datacompare.service;

import com.devigroups.datacompare.MatchKey;
import com.devigroups.datacompare.controllers.DataCompareController;
import com.devigroups.datacompare.dtos.ReplenishDataOutputCopyDTO;
import com.devigroups.datacompare.dtos.ReplenishDataOutputDTO;
import com.devigroups.datacompare.entities.GeneroData;
import com.devigroups.datacompare.entities.JavaData;
import com.devigroups.datacompare.entities.ReplenishData;
import com.devigroups.datacompare.repository.GeneroDataRepository;
import com.devigroups.datacompare.repository.JavaDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DataCompareService {

//    @Autowired
//    static
//    GeneroDataRepository gRepo;
//    @Autowired
//    static
//    JavaDataRepository jRepo;
//
//    static Logger logger = LoggerFactory.getLogger(DataCompareController.class);

    public static ReplenishDataOutputDTO AddGeneroObject(GeneroData g){
        ReplenishDataOutputDTO rdOutputDTO = new ReplenishDataOutputDTO();
        rdOutputDTO.setMatchKey(MatchKey.OnlyInGenero);
        rdOutputDTO.setBrCode(g.getBrCode().toString());
        rdOutputDTO.setProdNo(g.getProdNo().toString());
        rdOutputDTO.setSuppNo(g.getSuppNo().toString());
        rdOutputDTO.setSuppState(g.getSuppState());
        rdOutputDTO.setRdRepQty(g.getRdRepQty().toString());
        rdOutputDTO.setUnitCost(g.getUnitCost().toString());
        return rdOutputDTO;
    }

    public  static ReplenishDataOutputDTO AddJavaObject(JavaData g){
        ReplenishDataOutputDTO rdOutputDTO = new ReplenishDataOutputDTO();
        rdOutputDTO.setMatchKey(MatchKey.OnlyInJava);
        rdOutputDTO.setBrCode(g.getBrCode().toString());
        rdOutputDTO.setProdNo(g.getProdNo().toString());
        rdOutputDTO.setSuppNo(g.getSuppNo().toString());
        rdOutputDTO.setSuppState(g.getSuppState());
        rdOutputDTO.setRdRepQty(g.getRdRepQty().toString());
        rdOutputDTO.setUnitCost(g.getUnitCost().toString());
        return rdOutputDTO;
    }

    public static ReplenishDataOutputDTO CompareObjects(GeneroData g, JavaData j)
    {
        ReplenishDataOutputDTO rdOutputDTO = new ReplenishDataOutputDTO();
        if(g.getKey().equalsIgnoreCase(j.getKey())){
            rdOutputDTO.setMatchKey(MatchKey.Matched);
            rdOutputDTO.copyKey(g);
            if(g.getValues().equalsIgnoreCase(j.getValues())){
                System.out.println("All attributes are matching");
                rdOutputDTO.copyValues(g);
                return rdOutputDTO;
            }
            else
            {
                System.out.println("One or more attributes are not matching");
                rdOutputDTO.copyValuesWithMismatch(g,j);
                return rdOutputDTO;
            }
        }
        // TODO: Create global exception class and hanndle this exception along with 404 error
        System.out.println("Wrong Comparison: Keys are different");
        return rdOutputDTO;
    }


    public  static ReplenishDataOutputCopyDTO CompareObjectSample(ReplenishData r1, ReplenishData r2)
    {
        ReplenishDataOutputCopyDTO rdOutputDTO = new ReplenishDataOutputCopyDTO();

        System.out.println(r1);
        System.out.println(r2);
        if(r1.getKey().equalsIgnoreCase(r2.getKey())){

            rdOutputDTO.copyKey(r1);
            if(r1.getValues().equalsIgnoreCase(r2.getValues())){
                System.out.println("All attributes are matching");
                rdOutputDTO.copyValues(r1);
                return rdOutputDTO;
            }
            else
            {
                System.out.println("One or more attributes are not matching");
                rdOutputDTO.copyValuesWithMismatch(r1,r2);
                return rdOutputDTO;
            }
        }

        System.out.println("Wrong Comparison: Keys are different");
        return rdOutputDTO;
    }
}
