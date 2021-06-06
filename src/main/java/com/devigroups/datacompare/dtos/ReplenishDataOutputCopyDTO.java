package com.devigroups.datacompare.dtos;

import com.devigroups.datacompare.entities.ReplenishData;
import lombok.Data;

@Data
public class ReplenishDataOutputCopyDTO {

    private String brCode;
    private String prodNo;
    private String suppNo;
    private String suppState;
    private String rdRepQty;
    private String unitCost;

    public void copyKey(ReplenishData r){
        brCode = r.getBrCode().toString();
        prodNo = r.getProdNo().toString();
    }

    public void copyValues(ReplenishData r){
        brCode = r.getBrCode().toString();
        prodNo = r.getProdNo().toString();
        suppNo = r.getSuppNo().toString();
        suppState = r.getSuppState();
        rdRepQty = r.getRdRepQty().toString();
        unitCost = r.getUnitCost().toString();
    }

    public void copyValuesWithMismatch(ReplenishData r1, ReplenishData r2){
        //Copy all attribute values from GENERO first before you check for mismatch
        suppNo = r1.getSuppNo().toString();
        suppState = r1.getSuppState().toString();
        rdRepQty = r1.getRdRepQty().toString();
        unitCost = r1.getUnitCost().toString();

        if (r1.getSuppNo() != r2.getSuppNo()) suppNo +=  " / " + r2.getSuppNo().toString();
        if (r1.getSuppState() != r2.getSuppState()) suppState +=  " / " + r2.getSuppState();
        if (r1.getRdRepQty() != r2.getRdRepQty())  rdRepQty +=  " / " + r2.getRdRepQty().toString();
        if (r1.getUnitCost() != r2.getUnitCost()) unitCost +=  " / " + r2.getUnitCost().toString();
    }

}
