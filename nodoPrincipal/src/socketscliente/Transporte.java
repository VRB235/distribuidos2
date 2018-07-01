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
    private int _paquetesFallidos;

    public Transporte() {
        _paquetes = new ArrayList<Paquete>();
        _paquetesFallidos = 0;
    }

    public int getPaquetesFallidos() {
        return _paquetesFallidos;
    }

    public void setPaquetesFallidos(int _paquetesFallidos) {
        this._paquetesFallidos = _paquetesFallidos;
    }

    public ArrayList<Paquete> getPaquetes() {
        return _paquetes;
    }

    public void setPaquetes(ArrayList<Paquete> _paquetes) {
        this._paquetes = _paquetes;
    }

    
}
