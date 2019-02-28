
/* The producer–consumer problem:  a multi-process synchronization problem, where producer and the consumer,
 * share a common, fixed-size data buffer/queue. The producer’s job is to generate data, put it into the 
 * queue, and start again. At the same time, the consumer is removing data from the queue, one piece at 
 * a time. Problem: Using locks and condition variable, we need to make sure that the producer won’t try
 * to add data into buffer if it’s full and that the consumer won’t try to remove data from an empty data 
 * buffer */

public class App {

	public static void main(String[] args) {
		
		//create shared data store
		SharedDataStore dataStore = new SharedDataStore(5);//max capacity. if we will increase it  - it will be more revealing
		
		//create consumer thread and producer thread
		Producer producer = new Producer(dataStore);           
		Consumer consumer = new Consumer(dataStore);
		
		//start threads
		producer.start();
		consumer.start();

	}

}
