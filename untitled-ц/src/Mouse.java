import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

    public class Mouse extends JComponent implements MouseListener {
        int mode = 1;
        int x = 0;
        int y = 0;

        @Override
        public void mouseClicked(MouseEvent e) {
            if (mode == 1) {
                if (checkPositionAvatar(e.getX(), e.getY()) == false) {
                    mode = 1;
                    return;
                }
                x = e.getX();
                y = e.getY();
                mode = 2;
            } else {

                int first_i = 0;
                int first_j = 0;
                int second_j = 0;
                int second_i = 0;
                int x_s = e.getX();
                int y_s = e.getY();
                int isOK = 0;
                for (int i = 0; i < 5; i++) {
                    if (isOK > 1) {
                        break;
                    }
                    for (int j = 0; j < 5; j++) {
                        if ((Application.array_model[i][j].start_x <= x && Application.array_model[i][j].end_x >= x) &&
                                (Application.array_model[i][j].start_y <= y && Application.array_model[i][j].end_y >= y)) {
                            first_i = i;
                            first_j = j;
                            isOK++;
                        } else if ((Application.array_model[i][j].start_x <= x_s && Application.array_model[i][j].end_x >= x_s) &&
                                (Application.array_model[i][j].start_y <= y_s && Application.array_model[i][j].end_y >= y_s)) {
                            second_i = i;
                            second_j = j;
                            isOK++;
                        }
                    }
                }
                if (checkClick(second_i, second_j) == false) {
                    isOK--;
                    mode = 1;
                    return;
                }
                Work.nullColor();
                if (isOK > 1) {
                    moveAvatar(first_i, first_j, second_i, second_j);
                }
                mode = 1;
            }
        }

        /**
         * Proverqva dali e kliknato
         * @param second_i
         * @param second_j
         * @return
         */
        private boolean checkClick(int second_i, int second_j) {
            if (Work.array[second_i][second_j] == "M") {
                return true;
            } else {
                return false;
            }
        }

        /**
         * Mesti elementa
         * @param first_i
         * @param first_j
         * @param second_i
         * @param second_j
         */
        private void moveAvatar(int first_i, int first_j, int second_i, int second_j) {
            if(Application.array_model[second_i][second_j].avatar != null) {
                if (Application.array_model[second_i][second_j].avatar.getClass().getName() == "Turtle") {
                    Application.array_model[second_i][second_j].avatar = null;
                    Application.array_model[first_i][first_j].avatar = null;
                    Work.addElements(Work.gs, Application.array_model, true);
                    return;
                }
            }
            Avatar model = Application.array_model[first_i][first_j].avatar;
            Application.array_model[first_i][first_j].avatar = Application.array_model[second_i][second_j].avatar;
            Application.array_model[second_i][second_j].avatar = model;
            Work.addElements(Work.gs, Application.array_model ,true);
        }

        /**
         * Proverqva poziciqta na elementa
         * @param x
         * @param y
         * @return
         */
        private boolean checkPositionAvatar(int x, int y) {

            boolean isok = false;
            int i_s = 0;
            int j_s = 0;
            for (int i = 0 ; i < 5 ; i++)
            {
                if (isok  == true)
                {break;}
                for (int j = 0 ; j < 5 ; j++ )
                {
                    if ((Application.array_model[i][j].start_x <= x && Application.array_model[i][j].end_x >= x) &&
                            (Application.array_model[i][j].start_y <= y && Application.array_model[i][j].end_y >= y))
                    {
                        if(Application.array_model[i][j].avatar != null)
                        {
                            i_s = i;
                            j_s = j;
                            isok = true;
                            break;
                        }
                    }
                }
            }

            if (isok== true)
            {
                if(Application.array_model[i_s][j_s].avatar.getClass().getName() == "Guard")
                {
                    guardColorMode(i_s, j_s);
                }
                else
                {
                    leaderMove(i_s , j_s);
                }
            }
            Work.addElements(Work.gs, Application.array_model ,true);
            return isok;
        }

        /**
         * Dvijenie na lidera
         * @param i_s
         * @param j_s
         */
        private void leaderMove(int i_s, int j_s) {
            int last_i = i_s ;
            int last_j  = j_s ;
            for (int i = i_s-1 ; i > -1 ; i--)
            {
                if (Application.array_model[i][j_s].avatar != null)
                {
                    break;
                }
                last_i = i;
                if ( i== 2 && j_s == 2)
                {
                    break;
                }
            }
            changeColorGuard(last_i, j_s, 0);
            last_i = i_s;
            for (int i = i_s+1 ; i < 5 ; i++)
            {
                if (Application.array_model[i][j_s].avatar != null)
                {
                    break;
                }
                last_i = i;
                if (i == 2 && j_s == 2)
                {
                    break;
                }
            }
            changeColorGuard(last_i, j_s, 0);
            last_i = i_s;
            for (int j = j_s -1; j > -1 ; j--)
            {
                if (Application.array_model[i_s][j].avatar != null)
                {
                    break;
                }
                last_j = j;
                if (i_s == 2 && j == 2)
                {
                    break;
                }
            }
            changeColorGuard(i_s, last_j, 0);
            last_j = j_s;
            for (int j = j_s+1 ; j < 5 ; j++)
            {
                if (Application.array_model[i_s][j].avatar != null)
                {
                    break;
                }
                last_j = j;
                if (i_s == 2 && j == 2)
                {
                    break;
                }
            }
            changeColorGuard(i_s, last_j, 0);
        }

        /**
         * Proverq cveta na garda
         * @param i_s
         * @param j_s
         */
        private void guardColorMode(int i_s, int j_s) {
            if (i_s + 1 < 5 )
            {
                changeColorGuard(i_s + 1, j_s, 1);
            }
            if (i_s - 1 > -1 )
            {
                changeColorGuard(i_s -1, j_s, 1);
            }
            if (j_s + 1 < 5)
            {
                changeColorGuard(i_s , j_s+1 , 1);
            }
            if (j_s - 1 > -1)
            {
                changeColorGuard(i_s , j_s-1, 1);
            }
        }

        /**
         * Promenq cveta na garda
         * @param i_s
         * @param j_s
         */
        private void changeColorGuard(int i_s, int j_s , int mode_color) {
            if(Application.array_model[i_s][j_s].avatar == null)
            {
                if (mode_color == 1)
                {
                    if(i_s == 2 && j_s == 2)
                    {
                        return;
                    }
                }
                Work.array[i_s][j_s] ="M";
            }
            else
            {
                if(Application.array_model[i_s][j_s].avatar.type == -2)
                {
                    Work.array[i_s][j_s] = "M";
                    return;
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

    }

