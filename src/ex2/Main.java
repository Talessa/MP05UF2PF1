package ex2;

public class Main {
    /*REFACCIÓN: he aplicado el metodo de refacción extracción de clase por que el metodo main y log no heran necesarios
     *en la clase Has Table ya que no forman parte de la clase Hash Table.
     */
    public static void main(String[] args) {
        HashTable hashTable = new HashTable();
        // Put some key values.
        for (int i = 0; i < 30; i++) {
            final String key = String.valueOf(i);
            hashTable.put(key, key);
        }

        // Print the HashTable structure
       log("****   HashTable  ***");
       log(hashTable.toString());
       log("\nValue for key(20) : " + hashTable.get("20"));
    }
    private static void log(String msg) {
        System.out.println(msg);
    }
}