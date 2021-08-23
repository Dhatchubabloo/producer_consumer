import java.util.ArrayList;

public class Consumer implements Runnable{
    private int size=5;
    private ArrayList<String> list;
    public Consumer(ArrayList<String> list){
        this.list =list;
    }
    public void run() {
        try {
            synchronized (list) {
                while (true) {
                    if (list.size() == 0) {
                        list.wait();
                    } else
                        consume();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void consume() {
        for(int i=0;i<size;i++){
            Query.insert(list.get(0));
            System.out.println("consumer consumed"+list.remove(0));
            try {
                Thread.sleep(1000);
            }catch (Exception e){

            }
        }
        list.notifyAll();
    }
//    public void consume() throws IOException {
//            File file = new File("/home/inc5/IdeaProjects/producer_consumer/MultiThread/src/test.txt");
//            FileWriter fw = null;
//            try {
//                fw = new FileWriter(file);
//            } catch (
//                    FileNotFoundException e) {
//                e.printStackTrace();
//            }
//            BufferedWriter bw = new BufferedWriter(fw);
//            for(int i=0;i<5;i++){
//                bw.write(list.get(0));
//                bw.newLine();
//                System.out.println("consumer consumed"+list.remove(0));
//                try {
//                    Thread.sleep(1000);
//                }catch (Exception e){
//                }
//            }
//            bw.close();
//            list.notifyAll();
//        }

    }
