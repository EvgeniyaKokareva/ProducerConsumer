//thread what will create and add the data to shared data space
import java.util.Random;

public class Producer  extends Thread{
	
	private SharedDataStore dataStore;
	
	//constructor
	public Producer(SharedDataStore dataStore) {
		this.dataStore = dataStore;
	}
	
	@Override
	public void run() {
		while(true){
			//generate random int to fill out the shared data space
			Random r = new Random();
			int number = r.nextInt(10);
			try {
				dataStore.produce(number);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
