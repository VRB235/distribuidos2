/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketscliente;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author GOMEZ
 */
public class Transporte implements Serializable  {
    
    private ArrayList<Paquete> _paquetes;

    public Transporte() {
    }

    public ArrayList<Paquete> getPaquetes() {
        return _paquetes;
    }

    public void setPaquetes(ArrayList<Paquete> _paquetes) {
        this._paquetes = _paquetes;
    }

    
}
