package ex2;

// Original source code: https://gist.github.com/amadamala/3cdd53cb5a6b1c1df540981ab0245479
// Modified by Fernando Porrino Serrano for academic purposes.

public class HashTable {
    private int INITIAL_SIZE = 16;
    private int size = 0;
    private HashEntry[] entries = new HashEntry[INITIAL_SIZE];

    public int size(){
//        ERROR: He tenido que anadir un size++ en el put y un size-- en el drop para poder tener un contador y que
//        devuelva los valores reales de la tabla.
       return size;
    }

    public int real_size(){
//        ERROR: He modificado el retorno ya que no devolvia el tamaño correcto de la tabla.
        int rsize = entries.length;
        return rsize;
    }

    /*REFACCIÓN: he aplicado el metodo de refacción extracción de metodo por que es un codigo que se repite
     *tanto en el put,get y drop y de esta manera el codigo se vuelve mas eficiente.
     *Se a tenido que modificar en los metodos el codigo por la siguiente linea:
     *temp = getHashEntry(key, temp);
     */
    private HashEntry getHashEntry(String key, HashEntry temp) {
        while(temp.next != null && !temp.key.equals(key))
            temp = temp.next;
        return temp;
    }

    public void put(String key, String value) {
        int hash = getHash(key);
        final HashEntry hashEntry = new HashEntry(key, value);

        if(entries[hash] == null) {
            entries[hash] = hashEntry;
//            Incluio size++ para generar un contador y tener el tamaño de las entradas en la hash table
            size++;
        }
        /*
        * ERROR: he tenido que añadir una condicion dentro del while que consulta si la clave que nosotros hemos introducido ya esta disponible,
        * de esta manera el bucle terminara cuando llegue a null o encuentre la clave introducida luego comprovara donde a parado y  si existe
        * se modifica el value de esa key, si no generara una nueva entrada.
        */
        else {
            HashTable.HashEntry temp = entries[hash];
            temp = getHashEntry(key, temp);

            if (temp.key == key){
                temp.value=value;
            }else {
                temp.next= hashEntry;
                hashEntry.prev = temp;
//                Incluio size++ para generar un contador y tener el tamaño de las entradas en la hash table
                size++;
            }
        }

    }


    /**
     * Returns 'null' if the element is not found.
     */
    public String get(String key) {
        int hash = getHash(key);
        if(entries[hash] != null) {
            HashEntry temp = entries[hash];
            temp = getHashEntry(key, temp);

            return temp.value;
        }

        return null;
    }

    public void drop(String key) {
        int hash = getHash(key);
        if(entries[hash] != null) {

            HashEntry temp = entries[hash];
            temp = getHashEntry(key, temp);
            /*
             * ERROR: he tenido que añadir una condicion dentro del while que consulta si la siguiente posicion es diferente de nulo y la previa es igual
             * a nulo (eso significaria que queremos borrar la primera posicion) si se cumple esta condicion simplemente sustituimos la posicion actual
             * por la siguiente quedando eliminada la primera entrada sin afectar a las demas.
             * Ademas de añadir otra condicion que comprueba si el elemento es unico y borra toda la entrada.
             */
            if(temp.next!= null && temp.prev == null ) entries[hash] = temp.next;
            else if(temp.prev == null && temp.next == null) entries[hash] = null;//esborrar element únic (no col·lissió)
            else{
                if(temp.next != null) temp.next.prev = temp.prev;   //esborrem temp, per tant actualitzem l'anterior al següent
                temp.prev.next = temp.next;                         //esborrem temp, per tant actualitzem el següent de l'anterior
            }
//            Al igual que en el put creo un contador inverso que resta 1 cada vez que elimino un regristro.
            size--;
        }
    }

    private int getHash(String key) {
        // piggy backing on java string
        // hashcode implementation.
        return key.hashCode() % INITIAL_SIZE;
    }

    private class HashEntry {
        String key;
        String value;

        // Linked list of same hash entries.
        HashEntry next;
        HashEntry prev;

        public HashEntry(String key, String value) {
            this.key = key;
            this.value = value;
            this.next = null;
            this.prev = null;
        }

        @Override
        public String toString() {
            return "[" + key + ", " + value + "]";
        }
    }

    @Override
    public String toString() {
        int bucket = 0;
        StringBuilder hashTableStr = new StringBuilder();
        for (HashEntry entry : entries) {
            if(entry == null) {
                continue;
            }
            hashTableStr.append("\n bucket[")
                    .append(bucket)
                    .append("] = ")
                    .append(entry.toString());
            bucket++;
            HashEntry temp = entry.next;
            while(temp != null) {
                hashTableStr.append(" -> ");
                hashTableStr.append(temp.toString());
                temp = temp.next;
            }
        }
        return hashTableStr.toString();
    }
}