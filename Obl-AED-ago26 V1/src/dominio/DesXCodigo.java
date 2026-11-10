/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

/**
 *
 * @author alumnoFI
 */
public class DesXCodigo implements Comparable<DesXCodigo>{
    private Despliegue despliegue;
    
    
    public DesXCodigo(Despliegue d) { this.despliegue = d; }
    public Despliegue getDespliegue() { return despliegue; }

    @Override
    public int compareTo(DesXCodigo o) {
        
         return despliegue.getCodigoDes().compareTo(o.despliegue.getCodigoDes());
        
    }
    
      @Override
    public boolean equals(Object o) {
        return o instanceof DesXCodigo
            && despliegue.getCodigoDes().equals(((DesXCodigo) o).despliegue.getCodigoDes());
    }
    
    
    
    @Override
    public String toString() { return despliegue.toString(); }
    
}
