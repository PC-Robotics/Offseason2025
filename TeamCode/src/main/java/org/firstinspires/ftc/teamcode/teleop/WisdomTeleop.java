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
            boolean wristReverse = gamepad2.left_bumper;
            boolean wristForward = gamepad2.right_bumper;
            double liftUp = -gamepad2.right_stick_y;
            double intakeForward = gamepad2.right_trigger;
            double intakeBackwards = gamepad2.left_trigger;

            robot.drive(axial,lateral,yaw);
            robot.linearSlide(liftUp);
            robot.rotateWrist(wristForward, wristReverse);
            robot.intake(intakeForward - intakeBackwards);

            telemetry.update();
        }
    }
}
