package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robots.DriveBase;
import org.firstinspires.ftc.teamcode.robots.WisdomBot;
@TeleOp(name="WisdomTeleop", group="Test Modes")
public class WisdomTeleop extends LinearOpMode {
    private WisdomBot robot = new WisdomBot(this);

    public void runOpMode()
    {
        robot.init();

        waitForStart();

        while(opModeIsActive())
        {
            double axial = -gamepad1.left_stick_y;   // Forward on left stick yields negative val
            double lateral = gamepad1.left_stick_x;
            double yaw = gamepad1.right_stick_x;
            double wristReverse = gamepad1.left_trigger;
            double wristForward = gamepad1.right_trigger;
            boolean liftUp = gamepad1.right_bumper;
            boolean liftDown = gamepad1.left_bumper;
            boolean intakeForward = gamepad1.dpad_up;
            boolean intakeBackwards = gamepad1.dpad_down;

            robot.drive(axial,lateral,yaw);
            robot.linearSlide(liftUp, liftDown);
            robot.rotateWrist(wristForward-wristReverse);
            robot.intake(intakeForward, intakeBackwards);

            telemetry.update();
        }
    }
}
