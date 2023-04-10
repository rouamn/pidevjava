/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author LENOVO
 */
public class CategorieReclamation {
     int idCategorieReclamation;
   String libelle;

    public CategorieReclamation() {
    }

   
    public CategorieReclamation(int idCategorieReclamation, String libelle) {
        this.idCategorieReclamation = idCategorieReclamation;
        this.libelle = libelle;
    }

    public CategorieReclamation(String libelle) {
        this.libelle = libelle;
    }

    public int getIdCategorieReclamation() {
        return idCategorieReclamation;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setIdCategorieReclamation(int idCategorieReclamation) {
        this.idCategorieReclamation = idCategorieReclamation;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return "CategorieReclamation{" + "idCategorieReclamation=" + idCategorieReclamation + ", libelle=" + libelle + '}';
    }
}
