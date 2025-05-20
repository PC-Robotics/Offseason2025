package org.firstinspires.ftc.teamcode.robots;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class TeacherRobot extends DriveBaseOdometry {

    // Init motors
    private CRServo intake = null;
    private CRServo outtake = null;
    private Servo tilt = null;
    private Servo extender = null;
    private DcMotor linearSlide = null;

    // Drive constants
    public static final double INTAKE_SPEED = 0;
    public static final double OUTAKE_SPEED = 0;

    public TeacherRobot (LinearOpMode opmode) {
        super(opmode);
    }

    public void init(){
        super.init();

        intake = myOpMode.hardwareMap.get(CRServo.class,"intake");
        outtake = myOpMode.hardwareMap.get(CRServo.class,"outtake");
        tilt = myOpMode.hardwareMap.get(Servo.class,"tilt");
        extender = myOpMode.hardwareMap.get(Servo.class,"extender");
        linearSlide = myOpMode.hardwareMap.get(DcMotor.class,"slide");


    }

}
