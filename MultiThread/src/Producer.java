import java.io.*;
import java.util.ArrayList;

public class Producer implements Runnable {
    ArrayList<String> list;
    private int size=5;
    public Producer(ArrayList<String> list) {
        this.list = list;
    }

    public void run() {
        ArrayList<String> readline = new ArrayList();
        File f = new File("/home/inc5/IdeaProjects/producer_consumer/MultiThread/src/Runtime.txt");
        FileReader fr = null;
        try {
            fr = new FileReader(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(fr);
        String str = "";
        try {
            while ((str = br.readLine()) != null) {
                readline.add(str);
            }
        } catch (Exception e) {
        }
        try {
            synchronized (list) {
                while (true) {
                    if (list.size() > 0) {
                        list.wait();
                    } else
                        produce(readline);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    private void produce(ArrayList<String> lineList) {
        for(int i=0;i<size;i++){
            if(list.size()!=size) {
                list.add(lineList.get(0));
                System.out.println("producer produced - "+lineList.remove(0));
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.notifyAll();
    }
}

