public class producer_consumer {
   static int number_items = 20;
   static int number_looped = 40;
   static int[] items = new int[number_items];
   static int producer = 0;
   static int consumer = 0;
   static int lels = 0;
    
    public static void main(String[] args) {
        new consumer().start();
        new producer().start();

    }

    static class consumer extends Thread {
        @Override
        public void run() {
            for(int i = 0; i < number_looped; ++i) {
                //Note(Sharo): wait until we have something produced
                while(((producer)%number_items) == consumer);

                System.out.println("Eating Cookie No: " + items[consumer]);

                consumer = (++consumer)%number_items;
            }
        }
    }

    static class producer extends Thread {
        @Override
        public void run() {
            for(int i = 0; i < number_looped; ++i) {
                //Note(sharo); wait for empty spot, or don't override.
                while(((producer + 1)%number_items) == consumer);

                System.out.println("Making Cookie No: " + lels++);
                items[producer] = producer;

                producer = (++producer)%number_items;
            }
        }
    }
}

