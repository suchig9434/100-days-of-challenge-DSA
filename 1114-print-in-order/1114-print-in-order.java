import java.util.concurrent.CountDownLatch;

class Foo {

    private CountDownLatch firstDone = new CountDownLatch(1);
    private CountDownLatch secondDone = new CountDownLatch(1);

    public Foo() {
    }

    public void first(Runnable printFirst) throws InterruptedException {
        // Print "first"
        printFirst.run();

        // Signal that first() is completed
        firstDone.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        // Wait until first() is completed
        firstDone.await();

        // Print "second"
        printSecond.run();

        // Signal that second() is completed
        secondDone.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        // Wait until second() is completed
        secondDone.await();

        // Print "third"
        printThird.run();
    }
}