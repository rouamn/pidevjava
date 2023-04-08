/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serivces;

import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public interface IService <T> {
       
    public abstract void Ajouter(T t);
  
    public void suprrimer(T t);
    public void modifier(T t);
    public ArrayList<T> afficher();
    
}
