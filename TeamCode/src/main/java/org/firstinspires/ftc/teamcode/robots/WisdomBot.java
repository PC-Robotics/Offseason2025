package org.firstinspires.ftc.teamcode.robots;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class WisdomBot extends DriveBase {
    private DcMotor leftLift;
    private DcMotor rightLift;
    private DcMotor intakeWrist;
    private CRServo intake;
    public WisdomBot(LinearOpMode opMode) {
        super(opMode);
    }

    public void init(){
        leftLift = myOpMode.hardwareMap.get(DcMotor.class, "leftLift");
        rightLift = myOpMode.hardwareMap.get(DcMotor.class, "rightLift");
        intakeWrist = myOpMode.hardwareMap.get(DcMotor.class,"intakeWrist");
        intake = myOpMode.hardwareMap.get(CRServo.class,"intake");

        leftLift.setDirection(DcMotor.Direction.FORWARD);
        rightLift.setDirection(DcMotor.Direction.REVERSE);
        intakeWrist.setDirection(DcMotor.Direction.REVERSE);
        intake.setDirection(CRServo.Direction.FORWARD);

        super.init();
    }

    public void rotateWrist(double power){
        intakeWrist.setPower(power*power*power);
        myOpMode.telemetry.addData("Intake Wrist", intakeWrist.getCurrentPosition());
    }

    public void linearSlide (boolean up, boolean down){
        if(up){
            rightLift.setPower(1);
            leftLift.setPower(1);
        }else if(down){
            rightLift.setPower(-1);
            leftLift.setPower(-1);
        }else {
            rightLift.setPower(0.08);
            leftLift.setPower(0.08);
        }
        myOpMode.telemetry.addData("Left Lift Position",leftLift.getCurrentPosition());
        myOpMode.telemetry.addData("Right Lift Position",rightLift.getCurrentPosition());
    }

    public void intake(boolean forward, boolean backwards){
        if(forward){
            intake.setPower(1);
            intake.setPower(1);
        }else if(backwards){
            intake.setPower(-1);
            intake.setPower(-1);
        }else{
            intake.setPower(0);
            intake.setPower(0);
        }
    }
}
