import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SharedDataStore {
	
	//create shared data store
	int[] dataStore;  // array can be more effective by using memory
	
	//create lock and condition variables
	private Lock lock = new ReentrantLock();
	private Condition notEmpty = lock.newCondition();
	private Condition notFull = lock.newCondition();
	private int counter = 0;
	
	
	//constructor
	public SharedDataStore(int capacity) {
		 this.dataStore = new int[capacity];
	}
	
	
	//method will fill out the shared data store
	public void produce(int number) throws InterruptedException {
		//take a lock
		lock.lock();
		
		try {
			//check if lock is already has its owner. If so, thread need wait
			while(counter == dataStore.length) {
				System.out.println("Producer wants to produce, but storage is full.");
				notFull.await();
			}
			
			//critical region
			dataStore[counter] = number;
			System.out.println("Producer: a new object is added.");
			counter++;
			
			//notify contrary lock
			notEmpty.signal();
			System.out.println("Producer: signalling that storage is not empty.");
		}finally{
			//release lock
			lock.unlock();
		}		
	}

	//method will remove data from shared data store
	public void consume() throws InterruptedException {
		//create lock
		lock.lock();
		try {
			//check if data store is empty. if so, thread need to wait
			while(counter == 0) {
				System.out.println("Consumer wants to consume, but storage is empty");
				notEmpty.await();
			}
			
			//critical region
			counter--;
			System.out.println("Consumer: an object is removed: " + dataStore[counter]);
			
			//notify contrary lock
			notFull.signal();
			System.out.println("Consumer: signalling that storage is not full.");
		}finally{
			
			//release lock
			lock.unlock();
		}		
	}
}
