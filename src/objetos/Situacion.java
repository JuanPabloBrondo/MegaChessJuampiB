package objetos;

public class Situacion {

 
    private Pieza atacante;//El atacante es la pieza que podemos mover (no necesariamente ataca a una pieza rival)
    private Pieza victima;
    private boolean enRiesgo;//Es verdadero si la pieza nuestra (atacante) está apuntada por otra rival
    private boolean muereDespues;//Es verdadero si al mover la pieza queda expuesta a otra pieza rival
    private boolean custodiado;//Es verdadero si hay una pieza nuestra apuntando a la ubicación inicial del atacante (para que en caso de no mover el atacante y este morir, el custodio lo vengue)
    private boolean dobleProteccion;//Es verdadera si el lugar al que se está moviendo el atacante está al alcance de otra pieza nuestra
    private Pieza tapado; //Acá va la pieza que está tapada de más valor si y solo si corre riesgo al destaparse con la información del atacante y la victima.
                          //En caso de moverse nuestra pieza y seguir en la mira de la misma pieza rival el atributo atacante es el mismo que el tapado
    private Integer ubicacionInicial;
    private Integer ubicacionFinal;
       
    private Integer beneficio;

    public boolean getCustodiado() {
        return custodiado;
    }

    public void setCustodiado(boolean custodiado) {
        this.custodiado = custodiado;
    }

    
    
    public boolean getDobleProteccion() {
        return dobleProteccion;
    }

    public void setDobleProteccion(boolean dobleProteccion) {
        this.dobleProteccion = dobleProteccion;
    }

        
    public boolean getEnRiesgo() {
        return enRiesgo;
    }

    public void setEnRiesgo(boolean enRiesgo) {
        this.enRiesgo = enRiesgo;
    }

    public boolean getMuereDespues() {
        return muereDespues;
    }

    public void setMuereDespues(boolean muereDespues) {
        this.muereDespues = muereDespues;
    }

    
    
   

    public Pieza getAtacante() {
        return atacante;
    }

    public void setAtacante(Pieza atacante) {
        this.atacante = atacante;
    }

    public Pieza getVictima() {
        return victima;
    }

    public void setVictima(Pieza victima) {
        this.victima = victima;
    }

    public Pieza getTapado() {
        return tapado;
    }

    public void setTapado(Pieza tapado) {
        this.tapado = tapado;
    }

    public Integer getBeneficio() {
        return beneficio;
    }

    public void setBeneficio(Integer beneficio) {
        this.beneficio = beneficio;
    }

    public Integer getUbicacionInicial() {
        return ubicacionInicial;
    }

    public void setUbicacionInicial(Integer ubicacionInicial) {
        this.ubicacionInicial = ubicacionInicial;
    }

    public Integer getUbicacionFinal() {
        return ubicacionFinal;
    }

    public void setUbicacionFinal(Integer ubicacionFinal) {
        this.ubicacionFinal = ubicacionFinal;
    }

}
