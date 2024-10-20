import java.util.Comparator;

class ComparadorPrioridad implements Comparator<Persona> {
    @Override
    public int compare(Persona p1, Persona p2){
        //Asociado es la persona con maxima prioridad
        if(p1.tipo.equals("Asociado") && !p2.tipo.equals("Asociado")){
            return -1;
        } else if (!p1.tipo.equals("Asociado") && p2.tipo.equals("Aspcoadp")){
            return 1;
        }
        return 0; //Abonado y Boleta Suelta tiene la misma prioridad
    }
}
