package org.firstinspires.ftc.teamcode.robots;

import static org.firstinspires.ftc.teamcode.support.ConstantsPID.*;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.support.PIDController;

public class DriveBasePID extends DriveBaseOdometry
{
    private ElapsedTime holdTimer = new ElapsedTime();

    private PIDController driveController = new PIDController(DRIVE_KP,DRIVE_KI,DRIVE_KD,DRIVE_MAX_AUTO,DRIVE_TOLERANCE,DRIVE_DEADBAND,false);
    private PIDController strafeController = new PIDController(STRAFE_KP,STRAFE_KI,STRAFE_KD,STRAFE_MAX_AUTO,STRAFE_TOLERANCE,STRAFE_DEADBAND, false);
    private PIDController yawController = new PIDController(YAW_KP,YAW_KI,YAW_KD,YAW_MAX_AUTO,YAW_TOLERANCE,YAW_DEADBAND,true);

    public DriveBasePID(LinearOpMode mode,boolean isFC)
    {
        super(mode,isFC);
    }

    public void init()
    {
        super.init();
    }

    public void forward(double distanceInches, double power, double holdTime)
    {
        driveController.reset(getXPosition(DistanceUnit.INCH)+distanceInches,power);
        strafeController.reset(getYPosition(DistanceUnit.INCH));
        yawController.reset();
        holdTimer.reset();

        while (myOpMode.opModeIsActive())
        {
            updatePositionAndTelemetry();

            drive(-driveController.getOutput(getXPosition(DistanceUnit.INCH)),-strafeController.getOutput(getYPosition(DistanceUnit.INCH)), yawController.getOutput(getHeading(AngleUnit.DEGREES)));

            myOpMode.telemetry.update();

            if(driveController.isInPosition() && yawController.isInPosition())
            {
                if(holdTimer.time() > holdTime)  break;
            }
            else holdTimer.reset();

            myOpMode.sleep(10);
        }

        drive(0,0,0);
    }

    public void strafe(double distanceInches, double power, double holdTime)
    {
        driveController.reset(getXPosition(DistanceUnit.INCH));
        strafeController.reset(getYPosition(DistanceUnit.INCH)+distanceInches,power);
        yawController.reset();
        holdTimer.reset();

        while (myOpMode.opModeIsActive())
        {
            updatePositionAndTelemetry();

            drive(-driveController.getOutput(getXPosition(DistanceUnit.INCH)),-strafeController.getOutput(getYPosition(DistanceUnit.INCH)), yawController.getOutput(getHeading(AngleUnit.DEGREES)));

            myOpMode.telemetry.update();

            if(strafeController.isInPosition() && yawController.isInPosition())
            {
                if(holdTimer.time() > holdTime)  break;
            }
            else holdTimer.reset();

            myOpMode.sleep(10);
        }

        drive(0,0,0);
    }

    public void turnTo(double headingDegree, double power, double holdTime)
    {
        yawController.reset(headingDegree,power);
        while(myOpMode.opModeIsActive())
        {
            updatePositionAndTelemetry();

            drive(0,0,yawController.getOutput(getHeading(AngleUnit.DEGREES)));

            myOpMode.telemetry.update();

            if(yawController.isInPosition())
            {
                if(holdTimer.time() > holdTime) break;
            }
            else holdTimer.reset();
            // Test if needed
            myOpMode.sleep(10);
        }

        // Test if needed
        drive(0,0,0);
    }

    /**
     * Need to focus telemetry on error from position and rotating based on current heading
     * Combination of code from the following sources
     *  - SimplifiedOdometry (basic setup and PIDController)
     *  - YouTube video on GoToPosition using the GoBilda PID Computer
     *
     * @param yLocation
     * @param xLocation
     * @param headingDegree
     * @param power
     * @param holdTime
     */
    public void goToPosition(double xLocation, double yLocation, double headingDegree, double power, double holdTime)
    {
        driveController.reset(xLocation, power);
        strafeController.reset(yLocation, power);
        yawController.reset(headingDegree, power);

        while(myOpMode.opModeIsActive())
        {
            updatePositionAndTelemetry();

            double xDistance = xLocation - getXPosition(DistanceUnit.INCH);
            double yDistance = yLocation - getYPosition(DistanceUnit.INCH);

            double negativeRadianHeading = -getHeading(AngleUnit.RADIANS);

            double rotatedX = xDistance * Math.cos(negativeRadianHeading) - yDistance * Math.sin(negativeRadianHeading);
            double rotatedY = xDistance * Math.sin(negativeRadianHeading) + yDistance * Math.cos(negativeRadianHeading);

            double axialPower = driveController.getOutputFromError(rotatedX);
            double lateralPower = strafeController.getOutputFromError(rotatedY);
            double yawPower = yawController.getOutput(getHeading(AngleUnit.DEGREES));

            drive(axialPower,-lateralPower, -yawPower);

            myOpMode.telemetry.update();

            if(driveController.isInPosition() && strafeController.isInPosition() && yawController.isInPosition())
            {
                if(holdTimer.time() > holdTime) break;
            }
            else holdTimer.reset();

            myOpMode.sleep(10);
        }

        drive(0,0,0);
    }
}
