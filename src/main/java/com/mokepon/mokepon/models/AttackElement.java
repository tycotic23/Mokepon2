package com.mokepon.mokepon.models;

public enum AttackElement {
    CHIPS(1),
    CREAM(2),
    DOUGH(3);

    private final int value;
    AttackElement(int value){
        this.value=value;
    }

    public int getValue() {
        return value;
    }

    public boolean winTo(AttackElement attackElement){
        if(this.value== attackElement.getValue()+1){
            return true;
        }
        //3 seria el mayor de los posibles valores
        if(this.value==1 && attackElement.getValue()==3){
            return true;
        }
        return false;
    }
}
