// Branislav Buna
// 29.11.2023
// Program to simulate a drink vending machine
import javax.swing.*;
import java.awt.Color;

public class Ass2B00158771 {
    // creating an instance of JFrame
    public static JFrame frame = new JFrame();

    // Declare counter for each button
    public static int coffeeCounter = 0, teaCounter = 0, soupCounter = 0, waterCounter;

    // Declare buttons and labels
    public static JButton coffee, tea, soup, water, finishAndPay, reset, newPurchase;
    public static JLabel coffeeLabel, teaLabel, soupLabel, waterLabel, acknowledgeMsg, finalMsg, coffeePriceLabel, teaPriceLabel, soupPriceLabel, waterPriceLabel;

    // main method
    public static void main(String[] args){
        // calling a generateUI method to render the GUI
        generateUI();
    }

    // method to generate Java Swing UI
    public static void generateUI(){
        // creating an instance of buttons
        coffee = new JButton("Coffee");
        tea = new JButton("Tea");
        soup = new JButton("Soup");
        water = new JButton("Water");
        finishAndPay = new JButton("Finish & Pay");
        reset = new JButton("Reset");

        // change color of finishAndPay button
        finishAndPay.setBackground(new Color(144, 238, 144));
        finishAndPay.setOpaque(true);
        finishAndPay.setVisible(false);
        reset.setBackground(Color.RED);
        reset.setOpaque(true);
        reset.setVisible(false);

        // moving and resizing the buttons
        coffee.setBounds(150,100,100, 40);
        tea.setBounds(150,150,100, 40);
        soup.setBounds(150,200,100, 40);
        water.setBounds(150,250,100, 40);
        finishAndPay.setBounds(100, 320, 100, 40);
        reset.setBounds(200, 320, 100, 40);

        // creating an instance of labels for buttons
        // to the right of the button
        coffeeLabel = new JLabel(coffeeCounter + "x");
        teaLabel = new JLabel(teaCounter + "x");
        soupLabel = new JLabel(soupCounter + "x");
        waterLabel = new JLabel(waterCounter + "x");

        // below buttons
        acknowledgeMsg = new JLabel();

        // label to the left of the button
        coffeePriceLabel = new JLabel("€2.00");
        teaPriceLabel = new JLabel("€2.00");
        soupPriceLabel = new JLabel("€2.00");
        waterPriceLabel = new JLabel("€1.50");

        // moving and resizing the labels
        coffeeLabel.setBounds(260,100,50, 40);
        teaLabel.setBounds(260,150,50, 40);
        soupLabel.setBounds(260,200,50, 40);
        waterLabel.setBounds(260,250,50, 40);
        acknowledgeMsg.setBounds(130, 370, 200, 40);
        coffeePriceLabel.setBounds(105, 100, 50, 40);
        teaPriceLabel.setBounds(105, 150, 50, 40);
        soupPriceLabel.setBounds(105, 200, 50, 40);
        waterPriceLabel.setBounds(105, 250, 50, 40);

        // add buttons to the JFrame
        frame.add(coffee);
        frame.add(tea);
        frame.add(soup);
        frame.add(water);
        frame.add(finishAndPay);
        frame.add(reset);

        // add button labels to the JFrame
        frame.add(coffeeLabel);
        frame.add(teaLabel);
        frame.add(soupLabel);
        frame.add(waterLabel);
        frame.add(acknowledgeMsg);
        frame.add(coffeePriceLabel);
        frame.add(teaPriceLabel);
        frame.add(soupPriceLabel);
        frame.add(waterPriceLabel);

        // add action listeners to buttons
        addButtonListeners();

        // JFrame options
        frame.setSize(400,500);
        frame.setTitle("Drink Wending Machine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    // method to create a logic when button is clicked
    public static void addButtonListeners(){
        // coffee button click listener
        coffee.addActionListener(e -> {
            coffeeCounter++;
            coffeeLabel.setText(coffeeCounter + "x");
            acknowledgeMsg.setText("You have chosen coffee");
            finishAndPay.setVisible(true);
            reset.setVisible(true);
        });

        // tea button click listener
        tea.addActionListener(e -> {
            teaCounter++;
            teaLabel.setText(teaCounter + "x");
            acknowledgeMsg.setText("You have chosen tea");
            finishAndPay.setVisible(true);
            reset.setVisible(true);
        });

        // soup button click listener
        soup.addActionListener(e -> {
            soupCounter++;
            soupLabel.setText(soupCounter + "x");
            acknowledgeMsg.setText("You have chosen soup");
            finishAndPay.setVisible(true);
            reset.setVisible(true);
        });

        // water button click listener
        water.addActionListener(e -> {
            waterCounter++;
            waterLabel.setText(waterCounter + "x");
            acknowledgeMsg.setText("You have chosen water");
            finishAndPay.setVisible(true);
            reset.setVisible(true);
        });

        // finishing payment for selected products
        finishAndPay.addActionListener(e -> {
            // calculating final price and calling totalMoneyDue method
            double finalPrice = totalMoneyDue(coffeeCounter, waterCounter, teaCounter, soupCounter);

            // removing everything from frame and repainting screen
            frame.getContentPane().removeAll();
            frame.repaint();

            // creating newPurchase button
            newPurchase = new JButton("New purchase");
            newPurchase.setBounds(150,250,100, 40);

            // adding acknowledge msg to new frame
            finalMsg = new JLabel("The total due is €" + finalPrice);
            finalMsg.setBounds(125, 150, 300, 40);

            // rendering new frame with button and label
            frame.add(newPurchase);
            frame.add(finalMsg);
            frame.validate();

            // new purchase button listener
            newPurchase.addActionListener(ev -> {
                // removing everything from frame
                frame.getContentPane().removeAll();
                frame.repaint();

                // resetting counters and labels to 0
                reset.doClick();

                // generate new ui into the JFrame
                generateUI();
            });
        });

        // resetting the transaction for selected products
        reset.addActionListener(e -> {
            // set counter to zero
            coffeeCounter = 0;
            teaCounter = 0;
            waterCounter = 0;
            soupCounter = 0;

            // update the text on each label
            coffeeLabel.setText(coffeeCounter + "x");
            teaLabel.setText(teaCounter + "x");
            waterLabel.setText(waterCounter + "x");
            soupLabel.setText(soupCounter + "x");
            acknowledgeMsg.setText("");

            // make finish and reset button hidden
            finishAndPay.setVisible(false);
            reset.setVisible(false);
        });
    }

    // method to calculate final price
    public static double totalMoneyDue(int coffee, int water, int tea, int soup){
        // declare variables for products
        double coffeePrice = 2.00, teaPrice = 2.00, soupPrice = 2.00, waterPrice = 1.50 ;
        // return the calculated price based on inputs
        return (coffeePrice * coffee) + (teaPrice * tea) + (soupPrice * soup) + (waterPrice * water);
    }
}
