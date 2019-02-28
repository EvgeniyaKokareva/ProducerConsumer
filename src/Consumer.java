//thread what will consume the data from shared data space

public class Consumer  extends Thread{
	
	private SharedDataStore dataStore;
	
	//constructor
	public Consumer(SharedDataStore dataStore) {
		this.dataStore = dataStore;
	}
	
	@Override
	public void run() {
		while(true){
			try {
				dataStore.consume();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
