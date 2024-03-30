import java.lang.Thread;

public class OrphanProcess {
    public static void main(String[] args) {
        // Create the first child process (thread)
        Thread child1 = new Thread(() -> {
            try {
                System.out.println("Child 1 (PID: " + ProcessHandle.current().pid() + ") is running.");
                Thread.sleep(2000); // Simulate some work
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Child 1 is finished.");
        });

        // Create the second child process (thread)
        Thread child2 = new Thread(() -> {
            try {
                System.out.println("Child 2 (PID: " + ProcessHandle.current().pid() + ") is running.");
                Thread.sleep(5000); // Simulate some work that takes longer
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Child 2 is finished.");
            System.out.println("Child 2's parent PID is: " + ProcessHandle.current().parent().map(ProcessHandle::pid).orElse(-1));
        });

        // Start the child processes
        child1.start();
        child2.start();

        try {
            System.out.println("Parent (PID: " + ProcessHandle.current().pid() + ") is running.");
            Thread.sleep(3000); // Simulate some work
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Parent is finished.");
    }
}