import java.awt.*;

public class Application {
    public static Scheme[][] array_model;
    public static void main(String[] args)
    {
        array_model =
                new Scheme[][]{
                        {createNullElement(), createNullElement(), createNullElement(), createNullElement(), createLeader(1)},
                        {createNullElement(), createNullElement(), createNullElement(), createNullElement(), createNullElement()},
                        {createTurtle(), createNullElement(), createNullElement(), createNullElement(), createTurtle()},
                        {createNullElement(), createNullElement(), createNullElement(), createNullElement(), createNullElement()},
                        {createLeader(0),   createNullElement(), createNullElement(), createNullElement(), createNullElement()}
                };
        Work work = new Work();
        work.Create();
    }
    private static Scheme createTurtle() {
        return new Scheme(0,0,0,0 , new Turtle("turtle", 0 , Color.WHITE , Color.RED)) ;

    }

    private static Scheme createNullElement() {
        return new Scheme(0,0,0,0, null);
    }



    private static Scheme createLeader(int i) {


        if (i == 1)
        {
            return new Scheme(0,0,0,0 , new Leader("leader",  1 , Color.yellow , Color.black));
        }
        else
        {
            return new Scheme(0,0,0,0 ,  new Leader("leader",  2 , Color.green , Color.black));
        }
    }
}