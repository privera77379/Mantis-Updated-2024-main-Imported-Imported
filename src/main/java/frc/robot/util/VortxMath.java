// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.util;

/** Add your docs here. */
public class VortxMath {

    /// basically exponential driving
    // small joystick movements = small robot movements, vice versa
    public static double smoothDrivingFuntion(double input)
    {
        if(input>=0)
        {
            return Math.pow(input, 1.75);
        } 
        else 
        {
            return -Math.abs(Math.pow(input, 1.75));
        }
    }
    public static double applyDeadzone(double input, double deadzoneValue)
    {
        if (Math.abs(input) < deadzoneValue)
        {
            return(0.0);
        }
        else{
            return(input);
        }

    }
}
