import java.util.ArrayList;

public class Runner {
    public static void main(String[] args){
        ArrayList <String>list = new ArrayList();
        Producer producer = new Producer(list);
        Consumer consumer = new Consumer(list);
        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);
        producerThread.start();
        consumerThread.start();
    }
}
