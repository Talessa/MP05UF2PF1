package ex3;

import org.junit.jupiter.api.Assertions;

class HashTableTest {

    @org.junit.jupiter.api.Test
    void put() {
        int dos=2;
        double tres=2.4;
        int tres1=24;
        HashTable hashTable = new HashTable();
        Assertions.assertEquals("",hashTable.toString());

        hashTable.put("1","p1");
        Assertions.assertEquals("\n bucket[0] = [1, p1]",hashTable.toString());

        hashTable.put("2",dos);
        Assertions.assertEquals("\n bucket[0] = [1, p1]\n bucket[1] = [2, 2]",hashTable.toString());

        hashTable.put("24",tres);
        Assertions.assertEquals("\n bucket[0] = [24, 2.4]\n bucket[1] = [1, p1]\n bucket[2] = [2, 2]",hashTable.toString());

        hashTable.put("24",tres1);
        Assertions.assertEquals("\n bucket[0] = [24, 24]\n bucket[1] = [1, p1]\n bucket[2] = [2, 2]",hashTable.toString());
    }

    @org.junit.jupiter.api.Test
    void get() {
        HashTable hashTable = new HashTable();
        int v2=2;
        double v3=1.3;
        int v4=24;
        hashTable.put("1","p1");
        hashTable.put("2",v2);
        hashTable.put("13",v3);
        hashTable.put("24",v4);
        Assertions.assertEquals("\n bucket[0] = [24, 24]\n bucket[1] = [13, 1.3]\n bucket[2] = [1, p1]\n bucket[3] = [2, 2]",hashTable.toString());

        Assertions.assertEquals("p1",hashTable.get("1"));

        Assertions.assertEquals(24,hashTable.get("24"));

        Assertions.assertEquals(2,hashTable.get("2"));

        Assertions.assertEquals(1.3,hashTable.get("13"));


    }

    @org.junit.jupiter.api.Test
    void drop() {

        HashTable hashTable = new HashTable();
        int v2=2;
        double v3=1.3;
        int v4=24;
        hashTable.put("1","p1");
        hashTable.put("2",v2);
        hashTable.put("13",v3);
        hashTable.put("24",v4);
        Assertions.assertEquals("\n bucket[0] = [24, 24]\n bucket[1] = [13, 1.3]\n bucket[2] = [1, p1]\n bucket[3] = [2, 2]",hashTable.toString());

        hashTable.drop("1");
        Assertions.assertEquals("\n bucket[0] = [24, 24]\n bucket[1] = [13, 1.3]\n bucket[2] = [2, 2]",hashTable.toString());

        hashTable.drop("24");
        Assertions.assertEquals("\n bucket[0] = [13, 1.3]\n bucket[1] = [2, 2]",hashTable.toString());

        hashTable.drop("2");
        Assertions.assertEquals("\n bucket[0] = [13, 1.3]",hashTable.toString());

        hashTable.put("2",v2);
        hashTable.put("24",v4);
        Assertions.assertEquals("\n bucket[0] = [24, 24]\n bucket[1] = [13, 1.3]\n bucket[2] = [2, 2]",hashTable.toString());


        hashTable.drop("2");
        Assertions.assertEquals("\n bucket[0] = [24, 24]\n bucket[1] = [13, 1.3]",hashTable.toString());
    }
    @org.junit.jupiter.api.Test
    void size() {
        HashTable hashTable = new HashTable();
        Assertions.assertEquals(0,hashTable.size());
        int v2=2;
        double v3=1.3;
        int v4=24;
        hashTable.put("1","p1");
        Assertions.assertEquals(1,hashTable.size());
        hashTable.put("2",v2);
        Assertions.assertEquals(2,hashTable.size());
        hashTable.put("13",v3);
        Assertions.assertEquals(3,hashTable.size());
        hashTable.put("24",v4);
        Assertions.assertEquals(4,hashTable.size());
        hashTable.drop("13");
        Assertions.assertEquals(3,hashTable.size());

    }
    @org.junit.jupiter.api.Test
    void real_size() {
        HashTable hashTable = new HashTable();
        Assertions.assertEquals(16,hashTable.real_size());
        int v2=2;
        double v3=1.3;
        int v4=24;
        hashTable.put("1","p1");
        Assertions.assertEquals(16,hashTable.real_size());
        hashTable.put("2",v2);
        Assertions.assertEquals(16,hashTable.real_size());
        hashTable.put("13",v3);
        Assertions.assertEquals(32,hashTable.real_size());
        hashTable.put("24",v4);
        Assertions.assertEquals(64,hashTable.real_size());
        hashTable.drop("13");
        Assertions.assertEquals(64,hashTable.real_size());

    }
}