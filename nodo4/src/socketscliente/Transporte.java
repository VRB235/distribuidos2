/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketscliente;

import java.io.Serializable;

/**
 *
 * @author GOMEZ
 */
public class Transporte implements Serializable  {
    
    private int paquete;

    public Transporte() {
        paquete = 5;
    }

    public int getPaquete() {
        return paquete;
    }

    public void setPaquete(int paquete) {
        this.paquete = paquete;
    }
    
}
