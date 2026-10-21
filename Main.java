import java.util.Scanner;

/**
 * Customer Service Queue Management System
 * Demonstrates a Queue (FIFO) data structure implemented from scratch
 * using a linked list. Customers are served in the order they arrive.
 */
public class Main {

    // A single customer waiting in line
    static class Node {
        int ticketNumber;
        String name;
        String issue;
        Node next;

        Node(int ticketNumber, String name, String issue) {
            this.ticketNumber = ticketNumber;
            this.name = name;
            this.issue = issue;
            this.next = null;
        }
    }

    // Queue implementation (FIFO)
    static class CustomerQueue {
        private Node front; // next customer to be served
        private Node rear;  // last customer who joined
        private int size;
        private int ticketCounter = 1;

        boolean isEmpty() {
            return front == null;
        }

        int size() {
            return size;
        }

        // ENQUEUE: add a customer at the rear
        void enqueue(String name, String issue) {
            Node newNode = new Node(ticketCounter++, name, issue);
            if (isEmpty()) {
                front = rear = newNode;
            } else {
                rear.next = newNode;
                rear = newNode;
            }
            size++;
            System.out.println("\n[+] " + name + " joined the queue with ticket #"
                    + newNode.ticketNumber + ". Position: " + size);
        }

        // DEQUEUE: serve the customer at the front
        void dequeue() {
            if (isEmpty()) {
                System.out.println("\n[!] No customers waiting.");
                return;
            }
            Node served = front;
            front = front.next;
            if (front == null) {
                rear = null;
            }
            size--;
            System.out.println("\n[>] Now serving ticket #" + served.ticketNumber
                    + " - " + served.name + " (" + served.issue + ")");
        }

        // PEEK: look at the front customer without removing them
        void peek() {
            if (isEmpty()) {
                System.out.println("\n[!] Queue is empty.");
                return;
            }
            System.out.println("\nNext in line: ticket #" + front.ticketNumber
                    + " - " + front.name + " (" + front.issue + ")");
        }

        // DISPLAY: show everyone currently waiting
        void display() {
            if (isEmpty()) {
                System.out.println("\n[!] Queue is empty.");
                return;
            }
            System.out.println("\n--- Customers Waiting ---");
            Node current = front;
            int position = 1;
            while (current != null) {
                System.out.println(position + ". Ticket #" + current.ticketNumber
                        + " | " + current.name + " | " + current.issue);
                current = current.next;
                position++;
            }
            System.out.println("-------------------------");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerQueue queue = new CustomerQueue();
        int choice = 0;

        System.out.println("=================================");
        System.out.println(" CUSTOMER SERVICE QUEUE SYSTEM");
        System.out.println("=================================");

        do {
            System.out.println("\n1. Add customer to queue");
            System.out.println("2. Serve next customer");
            System.out.println("3. View next customer");
            System.out.println("4. Display all waiting customers");
            System.out.println("5. Show queue size");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            if (!scanner.hasNextLine()) {
                break;
            }
            String input = scanner.nextLine().trim();
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("\n[!] Please enter a number from 1 to 6.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Customer name: ");
                    String name = scanner.nextLine().trim();
                    System.out.print("Issue (e.g. SIM swap, billing): ");
                    String issue = scanner.nextLine().trim();
                    if (name.isEmpty()) {
                        System.out.println("\n[!] Name cannot be empty.");
                    } else {
                        queue.enqueue(name, issue.isEmpty() ? "General enquiry" : issue);
                    }
                    break;
                case 2:
                    queue.dequeue();
                    break;
                case 3:
                    queue.peek();
                    break;
                case 4:
                    queue.display();
                    break;
                case 5:
                    System.out.println("\nCustomers waiting: " + queue.size());
                    break;
                case 6:
                    System.out.println("\nGoodbye!");
                    break;
                default:
                    System.out.println("\n[!] Invalid option. Choose 1 to 6.");
            }
        } while (choice != 6);

        scanner.close();
    }
}
