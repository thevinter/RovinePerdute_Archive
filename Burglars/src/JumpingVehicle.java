public class JumpingVehicle extends Vehicle {

    public JumpingVehicle(MyMap map) {
        super(map);
    }

    @Override
    public double getFuel(Settlement from, Settlement to){
        return ( Math.abs( from.getHeight() - to.getHeight() ) );
    }
}
