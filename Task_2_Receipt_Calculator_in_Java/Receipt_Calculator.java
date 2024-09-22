import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Class to represent an Item
class Item {
    private String name;
    private double price;
    private int quantity;

    public Item(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return price * quantity;
    }
}

// Class to handle receipt calculations
class Receipt {
    private List<Item> items;
    private double taxRate;
    private double discountRate;

    public Receipt(double taxRate, double discountRate) {
        this.items = new ArrayList<>();
        this.taxRate = taxRate;
        this.discountRate = discountRate;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public double calculateSubtotal() {
        double subtotal = 0.0;
        for (Item item : items) {
            subtotal += item.getTotalPrice();
        }
        return subtotal;
    }

    public double calculateTax() {
        return calculateSubtotal() * taxRate / 100;
    }

    public double calculateDiscount() {
        return calculateSubtotal() * discountRate / 100;
    }

    public double calculateTotal() {
        return calculateSubtotal() + calculateTax() - calculateDiscount();
    }

    public String generateReceipt() {
        StringBuilder receipt = new StringBuilder();
        receipt.append("Receipt:\n");
        receipt.append("------------------------\n");
        for (Item item : items) {
            receipt.append(item.getName())
                   .append(" (Rs.")
                   .append(item.getPrice())
                   .append(" x ")
                   .append(item.getQuantity())
                   .append(") = Rs.")
                   .append(item.getTotalPrice())
                   .append("\n");
        }
        receipt.append("------------------------\n");
        receipt.append("Subtotal: Rs.").append(calculateSubtotal()).append("\n");
        receipt.append("Tax: Rs.").append(calculateTax()).append("\n");
        receipt.append("Discount: Rs.").append(calculateDiscount()).append("\n");
        receipt.append("Total: Rs.").append(calculateTotal()).append("\n");
        return receipt.toString();
    }

    public void saveReceiptToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(generateReceipt());
            System.out.println("Receipt saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving receipt: " + e.getMessage());
        }
    }
}

// Main class to interact with the user
public class ReceiptCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Receipt receipt = new Receipt(18.0, 10.0); // 18% tax and 10% discount

        System.out.println("Welcome to the Receipt Calculator!");

        while (true) {
            System.out.print("Enter item name (or 'done' to finish): ");
            String name = scanner.nextLine();
            if (name.equalsIgnoreCase("done")) {
                break;
            }

            System.out.print("Enter item price: Rs.");
            double price = scanner.nextDouble();

            System.out.print("Enter item quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            receipt.addItem(new Item(name, price, quantity));
        }

        // Display the receipt
        System.out.println("\n" + receipt.generateReceipt());

        // Save the receipt to a file
        System.out.print("Do you want to save the receipt to a file? (yes/no): ");
        String saveOption = scanner.nextLine();
        if (saveOption.equalsIgnoreCase("yes")) {
            System.out.print("Enter filename (e.g., receipt.txt): ");
            String filename = scanner.nextLine();
            receipt.saveReceiptToFile(filename);
        }

        scanner.close();
    }
}
