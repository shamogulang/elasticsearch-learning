package cn.oddworld.concurent;

public class InterruptedSleepingThreadMain {

        /**
         * @param args
         * @throws InterruptedException
         */
        public static void main(String[] args) throws InterruptedException {
            InterruptedSleepingThread thread = new InterruptedSleepingThread();
            thread.start();
            //Giving 10 seconds to finish the job.
            Thread.sleep(10000);
            //Let me interrupt
            thread.interrupt();
        }

    }