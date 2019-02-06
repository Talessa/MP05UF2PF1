package ex2;

import org.junit.jupiter.api.Assertions;

class HashTableTest {

    @org.junit.jupiter.api.Test
    void put() {
        HashTable hashTable = new HashTable();
        Assertions.assertEquals("",hashTable.toString());

        hashTable.put("1","p1");
        Assertions.assertEquals("\n bucket[0] = [1, p1]",hashTable.toString());

        hashTable.put("2","p2");
        Assertions.assertEquals("\n bucket[0] = [1, p1]\n bucket[1] = [2, p2]",hashTable.toString());

        hashTable.put("24","p17");
        Assertions.assertEquals("\n bucket[0] = [1, p1]\n bucket[1] = [2, p2] -> [24, p17]",hashTable.toString());

        hashTable.put("24","p24");
        Assertions.assertEquals("\n bucket[0] = [1, p1]\n bucket[1] = [2, p2] -> [24, p24]",hashTable.toString());
    }

    @org.junit.jupiter.api.Test
    void get() {
        HashTable hashTable = new HashTable();
        hashTable.put("1","p1");
        hashTable.put("2","p2");
        hashTable.put("13","p13");
        hashTable.put("24","p24");
        Assertions.assertEquals("\n bucket[0] = [1, p1]\n bucket[1] = [2, p2] -> [13, p13] -> [24, p24]",hashTable.toString());

        Assertions.assertEquals("p1",hashTable.get("1"));

        Assertions.assertEquals("p24",hashTable.get("24"));

        Assertions.assertEquals("p2",hashTable.get("2"));

        Assertions.assertEquals("p13",hashTable.get("13"));


    }

    @org.junit.jupiter.api.Test
    void drop() {

        HashTable hashTable = new HashTable();
        hashTable.put("1","p1");
        hashTable.put("2","p2");
        hashTable.put("13","p13");
        hashTable.put("24","p24");
        Assertions.assertEquals("\n bucket[0] = [1, p1]\n bucket[1] = [2, p2] -> [13, p13] -> [24, p24]",hashTable.toString());

        hashTable.drop("1");
        Assertions.assertEquals("\n bucket[0] = [2, p2] -> [13, p13] -> [24, p24]",hashTable.toString());

        hashTable.drop("24");
        Assertions.assertEquals("\n bucket[0] = [2, p2] -> [13, p13]",hashTable.toString());

        hashTable.drop("2");
        Assertions.assertEquals("\n bucket[0] = [13, p13]",hashTable.toString());

        hashTable.put("2","p2");
        hashTable.put("24","p24");
        Assertions.assertEquals("\n bucket[0] = [13, p13] -> [2, p2] -> [24, p24]",hashTable.toString());

        hashTable.drop("2");
        Assertions.assertEquals("\n bucket[0] = [13, p13] -> [24, p24]",hashTable.toString());
    }
    @org.junit.jupiter.api.Test
    void size() {

        HashTable hashTable = new HashTable();
        Assertions.assertEquals(0,hashTable.size());
        hashTable.put("1","p1");
        Assertions.assertEquals(1,hashTable.size());
        hashTable.put("2","p2");
        Assertions.assertEquals(2,hashTable.size());
        hashTable.put("13","p13");
        Assertions.assertEquals(3,hashTable.size());
        hashTable.put("24","p24");
        Assertions.assertEquals(4,hashTable.size());
        hashTable.drop("13");
        Assertions.assertEquals(3,hashTable.size());
    }
    @org.junit.jupiter.api.Test
    void real_size() {
        HashTable hashTable = new HashTable();
        Assertions.assertEquals(16,hashTable.real_size());
        hashTable.put("1","p1");
        Assertions.assertEquals(16,hashTable.real_size());
        hashTable.put("2","p2");
        Assertions.assertEquals(16,hashTable.real_size());
        hashTable.put("13","p13");
        Assertions.assertEquals(16,hashTable.real_size());
        hashTable.put("24","p24");
        Assertions.assertEquals(16,hashTable.real_size());
        hashTable.drop("13");
        Assertions.assertEquals(16,hashTable.real_size());
    }
}