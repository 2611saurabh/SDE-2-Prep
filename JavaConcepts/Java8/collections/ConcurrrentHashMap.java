package Java8.collections;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrrentHashMap {

    public static void main(String[] args) {

        ConcurrentHashMap<String,Integer> map = new ConcurrentHashMap<>();
        //java 7 :segment based locking--> 16 segmanet --> smaller hashmap
        //Only being segment be writtento or read from locked
        //read: do not require locking unless there is a write operation happening on the same segment
        //write: lock

        //java 8: no segmentation
        //---compare and swap approach--no locking expect resizing or collision
        //Thread A last saw -- x = 45
        //Tread A work --> x == 40
        //if x is still 45 then change it to 50 else retry
        //put --> index

    }
}
