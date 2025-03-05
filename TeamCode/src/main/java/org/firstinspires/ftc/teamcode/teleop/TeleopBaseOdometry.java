package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robots.DriveBaseOdometry;

@TeleOp(name="Basic Drive with Odometry Tracking",group="Test Modes")
public class TeleopBaseOdometry extends LinearOpMode
{
    private DriveBaseOdometry robot = new DriveBaseOdometry(this);

    public void runOpMode()
    {
        robot.init();

        robot.resetPositionAndOdometry();

        waitForStart();

        while(opModeIsActive())
        {
            gamepad1Controls();
            gamepad2Controls();

            // This command does NOT store the current position anywhere, it just updates display
            robot.updateOdometryTelemetry();

            telemetry.update();
        }
    }

    public void gamepad1Controls()
    {
        double axial = -gamepad1.left_stick_y;   // Forward on left stick yields negative val
        double lateral = gamepad1.left_stick_x;
        double yaw = gamepad1.right_stick_x;

        robot.drive(axial,lateral,yaw);
    }
    public void gamepad2Controls()
    {

    }


}
