package org.firstinspires.ftc.teamcode.support;

import com.qualcomm.robotcore.hardware.DcMotor;

public class LinearSlide
{
    protected DcMotor liftMotor = null;

    private int maximumPosition = 0;
    private int minimumPosition = 0;

    private final int LIFT_SPEED = 6;

    private String hardwareName = "";

    public LinearSlide(String name, int maximumPosition, int minimumPosition)
    {
        hardwareName = name;

        this.maximumPosition = maximumPosition;
        this.minimumPosition = minimumPosition;
    }

    public String getName()
    {
        return hardwareName;
    }

    public void setMotor(DcMotor motor)
    {
        liftMotor = motor;
    }

    public int getPosition()
    {
        return liftMotor.getCurrentPosition();
    }
}
