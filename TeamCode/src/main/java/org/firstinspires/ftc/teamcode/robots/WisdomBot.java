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

    public void intake(double power){
        intake.setPower(power);
    }

    public void linearSlide (double power){
        if(leftLift.getCurrentPosition() == 0){
            power = 0;
        }
        if(leftLift.getCurrentPosition() == maxHeight){
            power = 0;
        }
        rightLift.setPower(power);
        leftLift.setPower(power);
        myOpMode.telemetry.addData("Left Lift Position",leftLift.getCurrentPosition());
        myOpMode.telemetry.addData("Right Lift Position",rightLift.getCurrentPosition());
    }

    public void rotateWrist(boolean forward, boolean backwards){
        if(forward){
            intakeWrist.setPower(1);
            intakeWrist.setPower(1);
        }else if(backwards){
            intakeWrist.setPower(-1);
            intakeWrist.setPower(-1);
        }else{
            intakeWrist.setPower(0);
            intakeWrist.setPower(0);
        }
    }
}
