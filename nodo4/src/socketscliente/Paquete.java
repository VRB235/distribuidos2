/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketscliente;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.Date;

/**
 *
 * @author GOMEZ
 */
public class Paquete implements Serializable {
    
    private long _time;
    
    public Paquete(){
        _time = new Date().getTime();
    }

    public long getTime() {
        return _time;
    }

    public void setTime(long _time) {
        this._time = _time;
    }
    
    
    
}
