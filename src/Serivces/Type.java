/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serivces;

/**
 *
 * @author DELL
 */
public class Type {

    public Type() {
    }
    int idType;
    String nameType;

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    public Type(int idType, String nameType) {
        this.idType = idType;
        this.nameType = nameType;
    }

    public Type(String nameType) {
        this.nameType = nameType;
    }

    @Override
    public String toString() {
        return "Type{" + "nameType=" + nameType + '}';
    }

  
    
    
}
