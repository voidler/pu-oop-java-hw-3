import java.awt.*;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.*;

public class Work extends JPanel {
    public static Graphics gs;
    public  static String[][] array = {
            {"A","B","C","B","A"},
            {"B","B","C","B","B"},
            {"C","C","C","C","C"},
            {"B","B","C","B","B"},
            {"A","B","C","B","A"}
    };
    public  static String[][] base = {
            {"A","B","C","B","A"},
            {"B","B","C","B","B"},
            {"C","C","C","C","C"},
            {"B","B","C","B","B"},
            {"A","B","C","B","A"}
    };
    static int padding = 25;
    static JFrame frame;
    static boolean first = true;

    /**
     * Metod koito risuva
     * @param g
     */
    public  void paint(Graphics g){
        gs = g;
        addElements(g, Application.array_model, false);
    }

    /**
     * Dobavq kvadratchetata
     * @param g
     * @param array_mode
     * @param modes
     */
    public  static void addElements(Graphics g, Scheme[][] array_mode, boolean modes) {
        if (modes)
        {
            frame.repaint();
        }
        else
        {
            if (first) {
                generateAvatarPosition();
            }
        }
        int x = 0 ;
        int y = 0;
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){

                if (array[i][j] == "A")
                {
                    g.setColor(Color.red);
                }
                else if (array[i][j] == "B") {
                    if (Application.array_model[i][j].avatar != null) {
                        g.setColor(Color.gray);
                    } else {
                        g.setColor(Color.lightGray);
                    }
                }
                else if (array[i][j] == "C")
                {
                    g.setColor(Color.white);
                }
                else
                {
                    g.setColor(Color.orange);
                }
                g.fillRect(x, y, 100, 100);

                g.setColor(Color.black);
                g.drawRect(x, y, 100, 100);
                Scheme model = Application.array_model[i][j];
                model.start_y =y;
                model.start_x =x;
                model.end_x =x + 100;
                model.end_y = y+100;
                Application.array_model[i][j] = model;
                addAvatars(g , Application.array_model , x, y , i , j) ;
                addCenterElement(g,x,y, i , j );
                x+=100;

            }
            y+= 100;
            x = 0 ;
        }
        CHeckWInner();
    }
    private static void CHeckWInner() {
        String textWinner = "";
        boolean isOk = false;
        if (Application.array_model[2][2].avatar != null) {
            if (Application.array_model[2][2].avatar.getClass().getName() == "Leader") {
                if (Application.array_model[2][2].avatar.color == Color.yellow) {
                    textWinner = "Leader yellow ";
                } else {
                    textWinner = "Leader green ";
                }
                isOk = true;
            }
        }
        if (isOk) {
            JOptionPane.showMessageDialog(null, textWinner, "WINNER: ", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Postavq random elementite po dyskata
     */
    private static void generateAvatarPosition() {
        int max = 8 ;
        int added = 0;
        Random rand = new Random();
        for (int i = 0 ; i < max ; i++)
        {
            int x = rand.nextInt(5);
            int y = rand.nextInt(5);
            if (x == y && x == 2)
            {
                max++;
            }
            else
            {
                if (Application.array_model[x][y].avatar == null)
                {
                    added++;
                    if (added > 4)
                    {
                        Application.array_model[x][y]= createGuard(1);
                    }
                    else
                    {
                        Application.array_model[x][y]= createGuard(0);
                    }
                    if (added == 8)
                    {
                        first = false;
                        return;
                    }
                }
                else
                {
                    max++;
                }
            }
        }
    }
    private static Scheme createGuard(int i) {
        if (i == 1)
        {
            return new Scheme(0,0,0,0 , new Guard("guard", 1 , Color.yellow , Color.green)) ;
        }
        else
        {
            return new Scheme(0,0,0,0 , new Guard("guard",  2 , Color.green , Color.yellow));
        }
    }
    public static void nullColor() {
        for (int m = 0 ; m < 5 ; m++)
        {
            for (int  mk = 0 ; mk < 5 ; mk++)
            {
                array[m][mk] = base[m][mk];
            }
        }
    }

    /**
     * Dobavq krygcheto v sredata na dyskata
     * @param g
     * @param x
     * @param y
     * @param i
     * @param j
     */
    private  static void addCenterElement(Graphics g , int x , int y , int i , int j) {
        if (i == j && i == 2) {
            g.setColor(Color.BLACK);
            g.fillOval(x + padding, y + padding, 50, 50);
            g.setColor(Color.gray);
            g.drawOval(x + padding, y + padding, 50, 50);
        }
    }

    /**
     * Pribavq figurite vyrhu dyskata
     * @param g
     * @param array_model
     * @param x
     * @param y
     * @param i
     * @param j
     */
    private static void addAvatars(Graphics g, Scheme[][] array_model, int x , int  y , int i , int j) {
        if (array_model[i][j].avatar != null)
        {
            if (array_model[i][j].avatar.type == 2|| array_model[i][j].avatar.type == -2)
            {
                g.setColor(array_model[i][j].avatar.color);
                g.fillOval(x+padding, y+padding, 50, 50);
                g.setColor(array_model[i][j].avatar.border);
                g.drawOval(x+padding, y+padding , 50, 50);
            }
            else
            {
                g.setColor(array_model[i][j].avatar.color);
                g.fillRect(x+padding, y+padding, 50, 50);
                g.setColor(array_model[i][j].avatar.border);
                g.drawRect(x+padding, y+padding , 50, 50);
            }
        }
    }

    private static void changeCoordinates(int x, int y, int i, int j) {

    }

    /**
     * Syzdava panela za risuvane
     */
    public static void Create(){
        frame = new JFrame();
        frame.setSize(600,600);
        frame.getContentPane().add(new Work());
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.black);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        Component mouseClick = new Mouse()  ;
        frame.addMouseListener((MouseListener) mouseClick);
    }


}

