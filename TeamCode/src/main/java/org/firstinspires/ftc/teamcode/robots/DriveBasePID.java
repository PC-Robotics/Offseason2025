package org.firstinspires.ftc.teamcode.robots;

import static org.firstinspires.ftc.teamcode.support.ConstantsPID.*;

import static java.lang.Math.signum;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.support.PIDController;

public class DriveBasePID extends DriveBaseOdometry
{
    private double headingIntegral = 0;
    private double lastErrorHeading = 0;
    private double lastErrorDrive = 0;

    public DriveBasePID(LinearOpMode opMode, boolean isFC)
    {
        super(opMode, isFC);
    }

    public double pidHeading(double target, double kp, double ki, double kd, double current)
    {
        double error = target - current;
        headingIntegral += error;
        double derivative = error - lastErrorHeading;
        while (error > 180) error -= 360;
        while (error <= -180) error += 360;

        double correction = (error*kp) + (headingIntegral * ki) + (derivative * kd);
        lastErrorHeading = error;
        return correction;
    }

    public double pfdDrive(double kp, double kd, double kf, double error)
    {
        double derivative = error - lastErrorDrive;
        double correction = (error * kp) + (derivative * kd);
        correction += signum(error)*kf;
        lastErrorDrive = error;
        return correction;
    }

    /**
     * Provide the robot with a desired location and it will attempt to drive there
     * @param targetX - Target X position in Inches
     * @param targetY - Target Y position in Inches
     * @param targetH - Target Heading in Degrees
     */
    public void goToPosition(double targetX, double targetY, double targetH)
    {
        updatePosition();

        double xDistance = targetX - getXPosition(DistanceUnit.INCH);
        double yDistance = targetY - getYPosition(DistanceUnit.INCH);

        double rotatedX = xDistance * Math.cos(-getHeading(AngleUnit.RADIANS)) - yDistance * Math.sin(-getHeading(AngleUnit.RADIANS));
        double rotatedY = xDistance * Math.sin(-getHeading(AngleUnit.RADIANS)) + yDistance * Math.cos(-getHeading(AngleUnit.RADIANS));

        double driveCorrection = pfdDrive(DRIVE_KP,DRIVE_KD,0,rotatedX);
        double strafeCorrection = pfdDrive(STRAFE_KP,STRAFE_KD,0,rotatedY);
        double inputTurn = pidHeading(targetH, YAW_KP,YAW_KI,YAW_KD,getHeading(AngleUnit.DEGREES));

        drive(driveCorrection,strafeCorrection,inputTurn);
    }
}
