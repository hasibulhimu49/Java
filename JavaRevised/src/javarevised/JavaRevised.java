package javarevised;

interface Devices
{
    abstract void turnOn();
}

class WashingMachine implements Devices
{
    public void turnOn()
    {
        System.out.println("Turn on this");
    }
}

public class JavaRevised {

    public static void main(String[] args) {
    WashingMachine wm=new WashingMachine();
    wm.turnOn();
  }
}
