package OS_System;

import java.util.ArrayList;
import java.util.Scanner;

class Contact {
    String name;
    String phone;
    String email;

    Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    void displayContactDetails() {
        OSConsoleUI.printSection("CONTACT DETAILS");
        System.out.println("Name   : " + name);
        System.out.println("Phone  : " + phone);
        System.out.println("Email  : " + email);
    }

    void call() {
        System.out.println("Calling " + name + " ...");
        System.out.println("Phone Number : " + phone);
    }
}

class ContactBook {
    ArrayList<Contact> contacts = new ArrayList<>();
    Scanner sc;

    ContactBook(Scanner sc) {
        this.sc = sc;
    }

    void showMenu() {
        OSConsoleUI.printSquareMenu(
                "CONTACT MANAGEMENT",
                "PEOPLE  |  DETAILS  |  QUICK ACTIONS",
                new String[]{
                        "1. Add Contact",
                        "2. Remove Contact",
                        "3. Update Contact",
                        "4. Search Contact",
                        "5. Call Person",
                        "6. Exit"
                }
        );
        System.out.print("Enter your choice: ");
    }

    // Returns the position of the contact in the list, or -1 if not found
    int findContact(String key) {
        for (int i = 0; i < contacts.size(); i++) {
            Contact c = contacts.get(i);
            if (c.name.equalsIgnoreCase(key) || c.phone.equals(key)) {
                return i;
            }
        }
        return -1;
    }

    void addContact() {
        OSConsoleUI.printSection("ADD CONTACT");
        System.out.print("Name   : ");
        String name = sc.nextLine();
        System.out.print("Phone  : ");
        String phone = sc.nextLine();
        System.out.print("Email  : ");
        String email = sc.nextLine();

        contacts.add(new Contact(name, phone, email));
        System.out.println("Contact added successfully!");
    }

    void removeContact() {
        OSConsoleUI.printSection("REMOVE CONTACT");
        System.out.print("Enter name / phone: ");
        int index = findContact(sc.nextLine());

        if (index == -1) {
            System.out.println("Contact not found.");
            return;
        }

        System.out.print("Confirm delete? (yes/no): ");
        String confirm = sc.nextLine();

        if (confirm.equalsIgnoreCase("yes")) {
            contacts.remove(index);
            System.out.println("Contact removed successfully!");
        } else {
            System.out.println("Delete cancelled.");
        }
    }

    void updateContact() {
        OSConsoleUI.printSection("UPDATE CONTACT");
        System.out.print("Enter name / phone: ");
        int index = findContact(sc.nextLine());

        if (index == -1) {
            System.out.println("Contact not found.");
            return;
        }

        Contact c = contacts.get(index);
        System.out.print("New Name   : ");
        c.name = sc.nextLine();
        System.out.print("New Phone  : ");
        c.phone = sc.nextLine();
        System.out.print("New Email  : ");
        c.email = sc.nextLine();
        System.out.println("Contact updated successfully!");
    }

    void searchContact() {
        OSConsoleUI.printSection("SEARCH CONTACT");
        System.out.print("Enter name / phone: ");
        int index = findContact(sc.nextLine());

        if (index == -1) {
            System.out.println("Contact not found.");
        } else {
            contacts.get(index).displayContactDetails();
        }
    }

    void callPerson() {
        OSConsoleUI.printSection("CALL PERSON");
        System.out.print("Enter name / phone: ");
        int index = findContact(sc.nextLine());

        if (index == -1) {
            System.out.println("Contact not found.");
        } else {
            contacts.get(index).call();
        }
    }
}

public class ContactManagement {
    public static void contactMenu(Scanner sc) {

        ContactBook book = new ContactBook(sc);
        boolean running = true;

        while (running) {
            book.showMenu();
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    book.addContact();
                    break;
                case "2":
                    book.removeContact();
                    break;
                case "3":
                    book.updateContact();
                    break;
                case "4":
                    book.searchContact();
                    break;
                case "5":
                    book.callPerson();
                    break;
                case "6":
                    System.out.println("Exiting program. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice! Enter a number from 1 to 6.");
            }
        }
    }
}
