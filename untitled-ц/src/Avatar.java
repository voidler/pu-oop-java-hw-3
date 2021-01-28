import java.awt.*;

public class Avatar {
    String name;
    int team ;
    int type ;
    int start_x ;
    int start_y ;
    int end_x;
    int end_y;
    Color color;
    Color border;

    public Avatar( String name_in , int team, int type,  Color color , Color border )
    {
        this.name = name_in;
        this.team = team;
        this.type = type;
        this.color = color;
        this.border  =border;
    }
}