/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

/**
 *
 * @author DELL
 */
public class A {
      //2 je cree une variable de type A pour stocker l'instance
    public static A instance;
    
    //1 rendre le constructeur privee
    private A() {
    }
    
    
    //3 
    public static A getInstance(){
        if(instance == null ){
            instance = new A();
            
        } 
        return instance ;
    }
    
}
