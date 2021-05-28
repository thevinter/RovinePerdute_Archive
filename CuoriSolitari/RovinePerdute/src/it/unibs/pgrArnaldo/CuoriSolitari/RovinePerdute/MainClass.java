package it.unibs.pgrArnaldo.CuoriSolitari.RovinePerdute;

import it.unibs.fp.mylib.InputDati;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Stack;

public class MainClass {

    public static void main(String[] args) {

        ArrayList<City> array_city;
        ArrayList<Vertex> vertex_Tonatiuh = new ArrayList<>();
        ArrayList<Vertex> vertex_Metztli = new ArrayList<>();
        Stack<Integer> stack_Tonatiuh;
        Stack<Integer> stack_Metztli;

        File file = new File("RovinePerdute/src/test_file/PgAr_Map_10000.xml");
        array_city = Xml.readCity(file);

        vertex_Tonatiuh = Operations.dijkstra(array_city);
        vertex_Metztli = Operations.dijkstraHigh(array_city);

        stack_Tonatiuh = Operations.getRoute(vertex_Tonatiuh);
        stack_Metztli = Operations.getRoute(vertex_Metztli);

        Xml.writeRoad(vertex_Tonatiuh, vertex_Metztli, stack_Tonatiuh, stack_Metztli);

    }
}

