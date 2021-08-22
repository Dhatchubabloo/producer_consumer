import java.io.*;
import java.util.ArrayList;

public class Producer implements Runnable {
    ArrayList<String> list;

    public Producer(ArrayList<String> list) {
        this.list = list;
    }

    public void run() {
        try {
            synchronized (list) {
                while (true) {
                    if (list.size() > 0) {
                        list.wait();
                    } else
                        produce();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void produce() throws IOException {
        File f = new File("C:\\Users\\ELCOT\\IdeaProjects\\MultiThread\\src\\Runtime.txt");
        FileReader fr = null;
        try {
            fr = new FileReader(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(fr);
        String str = "";
        while ((str = br.readLine()) != null) {
            if (list.size() != 15) {
                list.add(str);
                System.out.println("Producer produced " + str);
            }
            try {
                Thread.sleep(1000);
            } catch (Exception e) {

            }
        }
        list.notifyAll();
    }
}
