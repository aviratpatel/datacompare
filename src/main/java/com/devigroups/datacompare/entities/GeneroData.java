package com.devigroups.datacompare.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="GENERO_REPLENISH_DATA")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneroData {
//    @Getter
//    @Setter

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id; // will be set when persisting

    private Integer brCode;
    private Integer prodNo;
    private Integer suppNo;
    private String suppState;
    private Integer rdRepQty;
    private Integer unitCost;

//    public ReplenishData(int brCode, int prodNo, int suppNo, String suppState, int rdRepQty, int unitCost) {
//    }
    @JsonIgnore
    public String getKey(){
        return brCode + "-" + prodNo;
    }

    @JsonIgnore
    public String getValues(){
        return suppNo + "-" + suppState + "-" + rdRepQty + "-" + unitCost;
    }

}
