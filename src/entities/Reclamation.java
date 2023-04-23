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
public class Reclamation {
     int idReclamation ;
    String  email_reclamation ,objet_reclamation,contenue_reclamation ;

    public Reclamation() {
    }

    public Reclamation(int idReclamation, String email_reclamation, String objet_reclamation, String contenue_reclamation) {
        this.idReclamation = idReclamation;
        this.email_reclamation = email_reclamation;
        this.objet_reclamation = objet_reclamation;
        this.contenue_reclamation = contenue_reclamation;
    }

    public Reclamation(String email_reclamation, String objet_reclamation, String contenue_reclamation) {
        this.email_reclamation = email_reclamation;
        this.objet_reclamation = objet_reclamation;
        this.contenue_reclamation = contenue_reclamation;
    }

    public Reclamation(int idReclamation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getIdReclamation() {
        return idReclamation;
    }

    public String getEmail_reclamation() {
        return email_reclamation;
    }

    public String getObjet_reclamation() {
        return objet_reclamation;
    }

    public String getContenue_reclamation() {
        return contenue_reclamation;
    }

    public void setIdReclamation(int idReclamation) {
        this.idReclamation = idReclamation;
    }

    public void setEmail_reclamation(String email_reclamation) {
        this.email_reclamation = email_reclamation;
    }

    public void setObjet_reclamation(String objet_reclamation) {
        this.objet_reclamation = objet_reclamation;
    }

    public void setContenue_reclamation(String contenue_reclamation) {
        this.contenue_reclamation = contenue_reclamation;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "idReclamation=" + idReclamation + ", email_reclamation=" + email_reclamation + ", objet_reclamation=" + objet_reclamation + ", contenue_reclamation=" + contenue_reclamation + '}';
    }
    
    
}
