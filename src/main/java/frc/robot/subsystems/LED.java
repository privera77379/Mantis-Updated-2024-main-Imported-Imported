package frc.robot.subsystems;


import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.AddressableLEDBufferView;
import edu.wpi.first.wpilibj.LEDPattern;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class LED extends SubsystemBase{
    private final AddressableLED m_led;
    private final AddressableLEDBuffer m_ledBuffer;
    private final AddressableLEDBufferView m_square;
    private final AddressableLEDBufferView m_strip;
    private int m_rainbowFirstPixelHue;
    private double startOfStreak, endOfStreak;
    private double startOfStreak2, endOfStreak2;

    public LED(int port, int length, int group1_length, int group2_length) {
        m_rainbowFirstPixelHue = 0;
        startOfStreak = 0.0;
        endOfStreak = 0.0;
        startOfStreak2 = 0.0;//rivera added
        endOfStreak2 = 0.0;//rivera added
        m_led = new AddressableLED(port);
        m_ledBuffer = new AddressableLEDBuffer(length);
        m_led.setLength(m_ledBuffer.getLength());
        m_led.setData(m_ledBuffer);
        m_led.start();
        m_square = m_ledBuffer.createView(0, group1_length-1);
        m_strip = m_ledBuffer.createView(group1_length,length-1);

    }

    // Function to handle the first 256 LEDs using m_square
    public void handleFirst256LEDs(Color color) {
        for (int i = 0; i < m_square.getLength(); i++) {
            m_square.setLED(i, color);
        }
        m_led.setData(m_ledBuffer);
    }
    public void handleFirst256LEDsWithSpecificColors() {
        // List of specific LED addresses to set to green
        int[] greenLEDs = {17, 19, 20, 41, 42, 43, 44, 46, 49, 51, 54, 55, 56, 69, 70, 71, 78, 81, 89, 
                           90, 91, 99, 100, 110, 113, 124, 125, 142, 145, 154, 155, 156, 164, 165, 166, 
                           174, 177, 178, 184, 185, 186, 199, 200, 201, 204, 205, 206, 209, 213, 214, 
                           234, 235, 236, 242, 243};
    
        // List of specific LED addresses to set to white
        int[] whiteLEDs = {84, 85, 104, 105, 106, 107, 116, 117, 118, 119, 120, 135, 136, 137, 138, 139, 
                           148, 149, 150, 151, 170, 171};
    
        // Set all LEDs in the first 256 to blue
        for (int i = 0; i < m_square.getLength(); i++) {
            m_square.setLED(i, Color.kBlue);
        }
    
        // Set the specific LEDs to green
        for (int led : greenLEDs) {
            if (led < m_square.getLength()) { // Ensure the LED index is within bounds
                m_square.setLED(led, Color.kGreen);
            }
        }
    
        // Set the specific LEDs to white
        for (int led : whiteLEDs) {
            if (led < m_square.getLength()) { // Ensure the LED index is within bounds
                m_square.setLED(led, Color.kWhite);
            }
        }
    
        // Update the LED buffer
        m_led.setData(m_ledBuffer);
    }
    // Function to handle the last 15 LEDs with VorTX streak using m_strip
    /*public void vorTXStreakLast15() {
        endOfStreak2 = startOfStreak2 + m_strip.getLength()/2;
        for (int i = (int) startOfStreak2; i < (int) endOfStreak2; i++) {
            m_strip.setLED(i % m_strip.getLength(), Color.kGreen);
            m_strip.setLED((i + (m_strip.getLength() / 2)) % m_strip.getLength(), Color.kBlue);
        }
        startOfStreak += 0.25;
        startOfStreak %= m_strip.getLength(); // Wrap around within the last 15 LEDs
        m_led.setData(m_ledBuffer);
    }*/
    public void vorTXStreakLast15() {
        int streakLength = m_strip.getLength() / 2; // Length of the streak
        endOfStreak2 = startOfStreak2 + streakLength;
    
        // Clear the strip first
        for (int i = 0; i < m_strip.getLength(); i++) {
            m_strip.setLED(i, Color.kBlack);
        }
    
        // Set the streak LEDs
        for (int i = 0; i < streakLength; i++) {
            int index = (int) ((startOfStreak2 + i) % m_strip.getLength());
            m_strip.setLED(index, Color.kGreen);
            m_strip.setLED((index + streakLength) % m_strip.getLength(), Color.kBlue);
        }
    
        // Increment the start of the streak to create the cycling effect
        startOfStreak2 += 0.25;
        startOfStreak2 %= m_strip.getLength(); // Wrap around within the last 15 LEDs
    
        // Update the LED buffer
        m_led.setData(m_ledBuffer);
    }
    public void updateLEDs() {
        handleFirst256LEDs(Color.kRed); // Set the first 256 LEDs to red
        vorTXStreakLast15(); // Apply VorTX streak to the last 15 LEDs
    }

    public void blinkColor(Color color) {
        double timer = System.currentTimeMillis();
        if(timer % 150 <= 75) {
            for (int i = 0; i < m_ledBuffer.getLength(); i++) { 
                m_ledBuffer.setLED(i, color);
            }
        } else {
            for (int i = 0; i < m_ledBuffer.getLength(); i++) { 
                m_ledBuffer.setLED(i, Color.kBlack);
            }
        }
        m_led.setData(m_ledBuffer);
    }
    public void blinkColor2(Color color) {
        double timer = System.currentTimeMillis();
        if(timer % 150 <= 75) {
            for (int i = 0; i < m_ledBuffer.getLength()-15; i++) { 
                m_ledBuffer.setLED(i, color);
            }
        } else {
            for (int i = 0; i < m_ledBuffer.getLength()-15; i++) { 
                m_ledBuffer.setLED(i, Color.kBlack);
            }
        }
        m_led.setData(m_ledBuffer);
    }
    public void rainbow() {
        // For every pixel
        for (int i = 0; i < m_ledBuffer.getLength(); i++) {
            // Calculate the hue - hue is easier for rainbows because the color
            // shape is a circle so only one value needs to precess
            final int hue = (m_rainbowFirstPixelHue + (i * 180 / m_ledBuffer.getLength())) % 180;
            // Set the value
            m_ledBuffer.setHSV(i, hue, 255, 128);
        }
        // Increase by to make the rainbow "move"
        m_rainbowFirstPixelHue += 3;
        // Check bounds
        m_rainbowFirstPixelHue %= 180;
        m_led.setData(m_ledBuffer);
    }

    /*public void noteCheck() {
        if(RobotContainer.intake.getNoteBeamBoolean() && !RobotContainer.intake.getRing()) {
            for(int i = 0; i < m_ledBuffer.getLength(); i++) {
                m_ledBuffer.setLED(i, Color.kAntiqueWhite);
            }
        } else if(RobotContainer.intake.getRing() && RobotContainer.intake.getNoteBeamBoolean()) {
            for(int i = 0; i < m_ledBuffer.getLength(); i++) {
                m_ledBuffer.setLED(i, Color.kGreen);
            }
        }
        else {
            for(int i = 0; i < m_ledBuffer.getLength(); i++) {
                m_ledBuffer.setLED(i, Color.kBlue);
            }
        }
        m_led.setData(m_ledBuffer);
    }*/

    public void vorTXStreak() {
        endOfStreak = startOfStreak + m_ledBuffer.getLength()/2;
        for(int i = (int) startOfStreak; i < (int) endOfStreak; i++) {
            m_ledBuffer.setLED(i % m_ledBuffer.getLength(), Color.kGreen);
            m_ledBuffer.setLED((i+(m_ledBuffer.getLength()/2)) % m_ledBuffer.getLength(), Color.kBlue);
        }
        startOfStreak += 0.15;
        startOfStreak %= m_ledBuffer.getLength();
        m_led.setData(m_ledBuffer);      
    }
    //rivera added for testing just last 15 led's
    /* 
    public void vorTXStreak2() {
        startOfStreak2 = 256.0;
        endOfStreak2 = startOfStreak2 + 15;
        for(int i = (int) startOfStreak2; i < (int) endOfStreak2; i++) {
            m_ledBuffer.setLED(i % 15, Color.kGreen);
            m_ledBuffer.setLED((i + (15/2)) % 15, Color.kBlue);
        }
        startOfStreak2 += 0.25;
        startOfStreak2 = 256;
        m_led.setData(m_ledBuffer);      
    }
    */
    public void setColor(Color color) {
        for (int i = 0; i < m_ledBuffer.getLength(); i++) { 
            m_ledBuffer.setLED(i, color);
        }
        m_led.setData(m_ledBuffer);
    }

    /* public void funny() {
        int r = (int) Math.abs(MathUtil.applyDeadband(RobotContainer.con2.getLeftX(), 0.1)*255);
        int g = (int) Math.abs(MathUtil.applyDeadband(RobotContainer.con2.getLeftY(), 0.1)*255);
        int b = (int) Math.abs(MathUtil.applyDeadband(RobotContainer.con2.getRightX(), 0.1)*255);
        for (int i = 0; i < m_ledBuffer.getLength(); i++) { 
            m_ledBuffer.setRGB(i, r, g, b);
        }
        m_led.setData(m_ledBuffer);
    } */

}