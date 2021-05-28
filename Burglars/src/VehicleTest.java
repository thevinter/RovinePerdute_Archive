import java.util.ArrayList;

public class VehicleTest {

    public static void main(String[] args) {

        int[] coords = new int[3];
        coords[0] = 4;
        coords[1] = 3;
        coords[2] = 5;
        Settlement s1 = new Settlement("A", 0, coords);
        s1.addConnection(2);
        s1.addConnection(3);
        s1.addConnection(6);

        coords = new int[3];
        coords[0] = 45;
        coords[1] = 5;
        coords[2] = 5;
        Settlement s2 = new Settlement("B", 1, coords);
        s2.addConnection(2);
        s2.addConnection(4);

        coords = new int[3];
        coords[0] = 6;
        coords[1] = 3;
        coords[2] = 10;

        Settlement s3 = new Settlement("C", 2, coords);
        s3.addConnection(3);
        s3.addConnection(8);
        s3.addConnection(7);

        coords = new int[3];
        coords[0] = 8;
        coords[1] = 2;
        coords[2] = 34;

        Settlement s4 = new Settlement("D", 3, coords);
        s4.addConnection(4);
        s4.addConnection(0);
        s4.addConnection(1);
        s4.addConnection(2);
        s4.addConnection(6);

        coords = new int[3];
        coords[0] = 43;
        coords[1] = 34;
        coords[2] = 17;

        Settlement s5 = new Settlement("E", 4, coords);
        s5.addConnection(7);

        coords = new int[3];
        coords[0] = 8;
        coords[1] = 4;
        coords[2] = 25;

        Settlement s6 = new Settlement("F", 5, coords);
        s6.addConnection(1);
        s6.addConnection(2);
        s6.addConnection(4);

        coords = new int[3];
        coords[0] = 8;
        coords[1] = 1;
        coords[2] = 21;

        Settlement s7 = new Settlement("G", 6, coords);
        s7.addConnection(2);
        s7.addConnection(0);
        s7.addConnection(7);

        coords = new int[3];
        coords[0] = 3;
        coords[1] = 6;
        coords[2] = 19;

        Settlement s8 = new Settlement("H", 7, coords);
        s8.addConnection(2);
        s8.addConnection(0);
        s8.addConnection(8);

        coords = new int[3];
        coords[0] = 7;
        coords[1] = 4;
        coords[2] = 52;

        Settlement s9 = new Settlement("I", 8, coords);
        s9.addConnection(6);
        s9.addConnection(5);

        MyMap map = new MyMap();

        map.addNode(s1);
        map.addNode(s2);
        map.addNode(s3);
        map.addNode(s4);
        map.addNode(s5);
        map.addNode(s6);
        map.addNode(s7);
        map.addNode(s8);
        map.addNode(s9);

        JumpingVehicle v = new JumpingVehicle(map);

        MyMap min = v.getMinimumPaths();

        for (Settlement s : min.getNodes()) {
            System.out.printf("%s -> %s (%.2f)\n", s.getName(), min.getNode(s.getClosestId()).getName(), s.getFuelFromStart());
        }

        ArrayList<Settlement> route =  v.findQuickestPath(s4.getId());

        for (Settlement s : route) {
            System.out.printf("%s -> ", s.getName());
        }
        System.out.println();
        route =  v.findQuickestPath(s5.getId());

        for (Settlement s : route) {
            System.out.printf("%s -> ", s.getName());
        }
        System.out.println();
        route =  v.findQuickestPath(s3.getId());

        for (Settlement s : route) {
            System.out.printf("%s -> ", s.getName());
        }

    }

}