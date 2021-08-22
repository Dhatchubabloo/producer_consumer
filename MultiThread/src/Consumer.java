import java.util.ArrayList;

public class Consumer implements Runnable{
    ArrayList<String> list;
    public Consumer(ArrayList<String> list){
        this.list =list;
    }
    public void run(){
        try{
            synchronized (list){
                while (true){
                    if(list.size()==0){
                        list.wait();
                    }
                    else
                        consume();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void consume() {
        for(int i=0;i<5;i++){
            Query.insert(list.get(0));
            System.out.println("consumer consumed"+list.remove(0));
            try {
                Thread.sleep(1000);
            }catch (Exception e){

            }
        }
        list.notifyAll();
    }
}
