package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.robots.DriveBasePID;

public class AutonPIDTuning extends LinearOpMode
{
    private DriveBasePID robot = new DriveBasePID(this,true);

    public void runOpMode()
    {
        robot.init();

        robot.resetPositionAndOdometry();

        waitForStart();

        testForwardBackX(24);
        testForwardBackY(24);

        testTurning(90);
        testTurning(180);
        testTurning(-90);
        testTurning(-180);

    }

    private void testForwardBackX(int distance)
    {
        robot.goToPosition(robot.getXPosition(DistanceUnit.INCH)+distance,robot.getYPosition(DistanceUnit.INCH)
            ,robot.getHeading(AngleUnit.DEGREES));
    }

    private void testForwardBackY(int distance)
    {
        robot.goToPosition(robot.getXPosition(DistanceUnit.INCH),robot.getYPosition(DistanceUnit.INCH)+distance
                ,robot.getHeading(AngleUnit.DEGREES));
    }

    private void testTurning(int degrees)
    {
        robot.goToPosition(robot.getXPosition(DistanceUnit.INCH),robot.getYPosition(DistanceUnit.INCH)
                ,robot.getHeading(AngleUnit.DEGREES)+degrees);
    }
}
