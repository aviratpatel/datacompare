package com.devigroups.datacompare.dtos;

import com.devigroups.datacompare.MatchKey;
import com.devigroups.datacompare.entities.GeneroData;
import com.devigroups.datacompare.entities.JavaData;
import lombok.Data;

@Data
public class ReplenishDataOutputDTO {

    private MatchKey matchKey;
    private String brCode;
    private String prodNo;
    private String suppNo;
    private String suppState;
    private String rdRepQty;
    private String unitCost;

    public void copyKey(GeneroData g){
        brCode = g.getBrCode().toString();
        prodNo = g.getProdNo().toString();
    }

    public void copyValues(GeneroData g){
        suppNo = g.getSuppNo().toString();
        suppState = g.getSuppState();
        rdRepQty = g.getRdRepQty().toString();
        unitCost =g.getUnitCost().toString();
    }

    public void copyValuesWithMismatch(GeneroData g, JavaData j){
        copyValues(g); //Copy all attribute values from GENERO first before you check for mismatch
        if ( !g.getSuppNo().toString().equalsIgnoreCase(j.getSuppNo().toString()) )
            suppNo +=  " / " + j.getSuppNo().toString();
        if ( !g.getSuppState().toString().equalsIgnoreCase(j.getSuppState().toString()) )
            suppState +=  " / " + j.getSuppState();
        if ( !g.getRdRepQty().toString().equalsIgnoreCase(j.getRdRepQty().toString()) )
            rdRepQty +=  " / " + j.getRdRepQty().toString();
        if ( !g.getUnitCost().toString().equalsIgnoreCase(j.getUnitCost().toString()) )
            unitCost +=  " / " + j.getUnitCost().toString();
    }

}
