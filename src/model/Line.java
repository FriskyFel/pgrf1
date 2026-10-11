package model;

public class Line {
    private Vertex vertex1, vertex2;

    Line (int x1, int y1, int x2, int y2) {
         vertex1 = new Vertex(x1, y1);
         vertex2 = new Vertex(x2, y2);
    }

    public Vertex getVertex1 (){
        return vertex1;
    }

    public Vertex getVertex2 (){
        return vertex2;
    }
}
