import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class ConnectFourGUI extends JFrame {

    // ===== Color Option Model =====
    private static class ColorOption {
        final String name;
        final Color color;
        
        final char code; // unique symbol char stored in Board
        
        ColorOption(String name, Color color, char code) {
            this.name = name; this.color = color; this.code = code;
        }
        @Override
        public String toString() { return name; }
    }
    
    //colour options for user
    private static final ColorOption[] PALETTE = new ColorOption[]{
            new ColorOption("Red",     new Color(220, 60, 60), 'R'),
            new ColorOption("Yellow",  new Color(240,200, 60), 'Y'),
            new ColorOption("Blue",    new Color( 60, 90,220), 'B'),
            new ColorOption("Green",   new Color( 60,170, 90), 'G'),
            new ColorOption("Purple",  new Color(140, 90,200), 'P'),
            new ColorOption("Pink",    new Color(230,100,180), 'M'),
            new ColorOption("Orange",  new Color(245,140, 30), 'O'),
            new ColorOption("Cyan",    new Color( 60,190,200), 'C'),
            new ColorOption("Black",   new Color( 30, 30, 30), 'K')
            
    };

    //function that takes in the name of the colour
    private static ColorOption byName(String n) {
    	
    	//for each ColourOption object in the palette
        for (ColorOption co: PALETTE) {
        	
        	//if name is the same as the inputted colour from user
        	if (co.name.equals(n)) {
        		
        		return co;
        	}
        }
        
        return PALETTE[0];
    }
    
    //function that takes the colour code
    private static ColorOption byCode(char code) {
        for (ColorOption co: PALETTE) { 
        	
        	
        	if (co.code == code) {
        		
        		
        		return co;
        	}
        
        }
        return PALETTE[0];
    }
    
    
    private static Color colorForCode(char code) { 
    	
    	return byCode(code).color; 
    	
    }
    private static String nameForCode(char code) { 
    	
    	return byCode(code).name; 
    	
    }

    //function that handles the colour choices
    private static char pickOpponentFor(char humanCode) {
    	
    	//if the user selects a colour the ai is given a different colour
        if (humanCode == 'R') return 'Y';
        if (humanCode == 'Y') return 'R';
        if (humanCode == 'B') return 'O';
        if (humanCode == 'O') return 'B';
        if (humanCode == 'G') return 'P';
        if (humanCode == 'P') return 'G';
        if (humanCode == 'M') return 'C';
        if (humanCode == 'C') return 'M';
        if (humanCode == 'K') return 'R';
        
        
        //for each colour in the palette
        for (ColorOption co : PALETTE) {
        	
        	//if the colours are not the same
        	if (co.code != humanCode) {
        		
        		//returns the colour code
        		return co.code;
        	}
        }
        
        //default is yellow
        return 'Y';
    }

    private JPanel currentPanel;

    //constructor that sets the title, screen size, layout
    public ConnectFourGUI() {
        super("Connect 4");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(820, 760);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        setContent(new StartMenuPanel(this));
    }

    //function that sets the content
    private void setContent(JPanel panel) {
    	
    	//container object = the content pane
        Container cp = getContentPane();
        
        //if instances are not the same
        if (!(cp.getLayout() instanceof BorderLayout)) { 
        	
        	//set the content pane layout to be border layout
        	cp.setLayout(new BorderLayout());
        
        }
        
        //if the current panel is not empty
        if (currentPanel != null) { 
        	
        	//remove the panel from the content pane
        	cp.remove(currentPanel);
        
        
        }
        
        //assign the panel to the current panel
        currentPanel = panel;
        
        //adds the current panel as border layout
        cp.add(currentPanel, BorderLayout.CENTER);
        
        //revalidates in case of error
        cp.revalidate();
        //repaint in case pixels are wrong
        cp.repaint();
    }

    //function that starts the ai game
    void startVsAI(char humanColorCode, boolean humanStarts, int aiLevel) {
    	
    	//sets the ai content
        setContent(new GamePanel(this, true, humanColorCode, humanStarts, aiLevel, '\0', '\0', false));
    }

    //function that starts the 2 player game
    void startTwoPlayer(char p1Code, char p2Code, boolean p1Starts) {
    	
    	//sets the two player content
        setContent(new GamePanel(this, false, '\0', p1Starts, 0, p1Code, p2Code, true));
    }

    // ===== Start Menu =====
    private static class StartMenuPanel extends JPanel {
    	
    	//constructor
        StartMenuPanel(ConnectFourGUI app) {
        	
        	//sets layout
            setLayout(new GridBagLayout());
            
            //sets background colour
            setBackground(new Color(245, 245, 245));
            
            GridBagConstraints gc = new GridBagConstraints();
            gc.gridx = 0; gc.gridy = 0;
            gc.fill = GridBagConstraints.HORIZONTAL;
            gc.insets = new Insets(8, 16, 8, 16);
            gc.weightx = 1.0;

            JLabel title = new JLabel("Connect 4", SwingConstants.CENTER);
            title.setFont(title.getFont().deriveFont(Font.BOLD, 32f));
            title.setBorder(new EmptyBorder(10, 10, 20, 10));

            JButton vsAI = new JButton("Play vs Computer");
            JButton twoP = new JButton("Play 2 Player");
            JButton exit = new JButton("Exit");

            //versus ai button action listener
            vsAI.addActionListener(e -> {
            	
            	//difficulty options
                Object[] lvlOpts = {"Easy", "Medium"};
                
                //ai level menu option pane
                int aiLevel = JOptionPane.showOptionDialog(app,
                        "Select AI difficulty:",
                        "AI Difficulty",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null, lvlOpts, lvlOpts[0]);
                
                //if option menu is closed
                if (aiLevel == JOptionPane.CLOSED_OPTION) return;

                //human colour choice option pane
                String humanPick = (String) JOptionPane.showInputDialog(
                        app, "Choose your color:", "Player Color",
                        JOptionPane.QUESTION_MESSAGE, null,
                        Arrays.stream(PALETTE).map(co -> co.name).toArray(), "Red");
                
                
                if (humanPick == null) return;
                char humanCode = byName(humanPick).code;
                
                
                //play first option pane
                int first = JOptionPane.showConfirmDialog(app,
                        "Do you want to play first?", "First Move",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                
                if (first == JOptionPane.CLOSED_OPTION) return;
                
                boolean humanStarts = (first == JOptionPane.YES_OPTION);

                //starts the ai game content panel
                app.startVsAI(humanCode, humanStarts, aiLevel);
            });

            //two player action listener
            twoP.addActionListener(e -> {
            	
            	//player 1 colour options
                String p1Pick = (String) JOptionPane.showInputDialog(
                        app, "Player 1: choose your color", "P1 Color",
                        JOptionPane.QUESTION_MESSAGE, null,
                        Arrays.stream(PALETTE).map(co -> co.name).toArray(), "Red");
                
                if (p1Pick == null) return;
                
                
                char p1 = byName(p1Pick).code;

                String p2Pick; 
                char p2;
                
                while (true) {
                	
                	//player 2 colour options using stream to connect the palette array and mapping the string to a new array
                    p2Pick = (String) JOptionPane.showInputDialog(
                    		
                            app, "Player 2: choose your color (different to P1)", "P2 Color",
                            JOptionPane.QUESTION_MESSAGE, null,
                            Arrays.stream(PALETTE).map(co -> co.name).toArray(), "Yellow");
                    
                    if (p2Pick == null) return;
                    
                    //maps the player 2 colour choice to byName Function
                    p2 = byName(p2Pick).code;
                    
                    // is the colours are not the same
                    if (p2 != p1) {
                    	
                    	
                    	break;
                    	
                    }
                    //players must choose a new colour
                    JOptionPane.showMessageDialog(app,
                            "Player 2 color must differ from Player 1.",
                            "Invalid Color", JOptionPane.WARNING_MESSAGE);
                }

                //stores the players
                Object[] startOpts = {"Player 1", "Player 2"};
                
                //asks user who players first
                int start = JOptionPane.showOptionDialog(app,
                        "Who starts?", "Starting Player",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, startOpts, startOpts[0]);
                
                //if the option window is closed
                if (start == JOptionPane.CLOSED_OPTION) return;
                boolean p1Starts = (start == 0);

                //calls start 2 player function that sets the content
                app.startTwoPlayer(p1, p2, p1Starts);
            });

            //if the user selects exit
            exit.addActionListener(e -> System.exit(0));
            
            //adds the panels to the screen
            JPanel box = new JPanel(new GridLayout(0, 1, 10, 10));
            box.setOpaque(false);
            box.add(title);
            box.add(vsAI);
            box.add(twoP);
            box.add(exit);

            add(box, gc);
        }
    }

    // ===== Game Panel =====
    private static class GamePanel extends JPanel {
        private final ConnectFourGUI app;
        private final boolean vsAI;
        private final int aiLevel;
        private final Board board = new Board();

        private final char humanCode, aiCode;
        private final boolean twoPlayerMode;
        private final char p1Code, p2Code;
        private char current;

        private final JButton[] dropButtons = new JButton[7];
        private final CellPanel[][] cells = new CellPanel[6][7];
        private final JLabel status = new JLabel(" ");
        private boolean inputEnabled = true;

        private AI aiEngine;

        //constructor
        GamePanel(ConnectFourGUI app,boolean vsAI,char humanCode,boolean humanStarts,
                  int aiLevel,char p1Code,char p2Code,boolean twoPlayerMode) {
        	
            this.app = app;
            this.vsAI = vsAI;
            this.aiLevel = aiLevel;
            this.twoPlayerMode = twoPlayerMode;

            //if vsAI is true set the attributes
            if (vsAI) {
                this.humanCode = humanCode;
                this.aiCode = pickOpponentFor(humanCode);
                this.p1Code = humanCode;
                this.p2Code = aiCode;
                this.current = humanStarts ? humanCode : aiCode;
                this.aiEngine = (aiLevel == 0) ? new EasyAI() : new MediumAI();
            }
            //if twoPlayer is true set the attributes
            else {
                this.p1Code = p1Code;
                this.p2Code = p2Code;
                this.humanCode = '\0';
                this.aiCode = '\0';
                this.current = humanStarts ? p1Code : p2Code;
            }

            buildUI();
            updateStatus();
            
            //if its the ai game
            if (vsAI && current == aiCode) {
            	
            	//call the delay ai function
            	aiMoveWithDelay();
            	
            }
        }
        
        //function the builds how it will look
        private void buildUI() {
        	
        	//sets layout to borderLayout
            setLayout(new BorderLayout());
            //sets background colour
            setBackground(new Color(250, 250, 250));

            JPanel top = new JPanel(new BorderLayout());
            top.setBorder(new EmptyBorder(8, 8, 8, 8));
            JButton toMenu = new JButton("<- Menu");
            
            //shows the menu content
            toMenu.addActionListener(e -> app.setContent(new StartMenuPanel(app)));
            
            //label at top
            JLabel title = new JLabel("Connect 4", SwingConstants.CENTER);
            title.setFont(title.getFont().deriveFont(Font.BOLD, 18f));
            top.add(toMenu, BorderLayout.WEST);
            top.add(title, BorderLayout.CENTER);
            add(top, BorderLayout.NORTH);

            JPanel center = new JPanel(new BorderLayout());
            center.setBorder(new EmptyBorder(8, 8, 8, 8));
            
            //controls to drop disc
            JPanel controls = new JPanel(new GridLayout(1, 7, 6, 6));
            
            for (int c = 0; c < 7; c++) {
                final int col = c;
                JButton b = new JButton(Integer.toString(c + 1));
                b.setFocusPainted(false);
                b.setFont(b.getFont().deriveFont(Font.BOLD, 14f));
                b.addActionListener(dropHandler(col));
                dropButtons[c] = b;
                controls.add(b);
            }
            //adds to screen
            center.add(controls, BorderLayout.NORTH);
            
            //the game board panel
            JPanel boardPanel = new JPanel(new GridLayout(6, 7, 6, 6));
            
            //creates space
            boardPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
            
            //sets the colour to a blue 
            boardPanel.setBackground(new Color(25, 70, 160));
            
            //loops through and adds the white empty cells
            for (int r = 0; r < 6; r++) {
                for (int c = 0; c < 7; c++) {
                    CellPanel cell = new CellPanel();
                    cells[r][c] = cell;
                    boardPanel.add(cell);
                }
            }
            //adds to screen
            center.add(boardPanel, BorderLayout.CENTER);

            add(center, BorderLayout.CENTER);
            
            //shows the status of the game in bottom left
            JPanel statusBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
            statusBar.setBorder(new EmptyBorder(8, 10, 8, 10));
            statusBar.add(status);
            add(statusBar, BorderLayout.SOUTH);
        }

        //handles the dropped discs
        private ActionListener dropHandler(int col) {
            return e -> { if (inputEnabled) doMove(col); };
        }

        //places the move
        private void doMove(int col) {
        	
        	//uses board class logic
            int row = board.placeMove(col, current);
            
            //if column is full
            if (row == -1) {
                status.setText("Column " + (col+1) + " is full. Choose another.");
                return;
            }

            //it fills the cell with the placed move
            paintCell(row, col, current);

            //if the move is a win
            if (board.checkWin(current)) {
            	
            	//it displays the color and win message in colour of current player
                announceResult(nameForCode(current) + " wins!", colorForCode(current));
                return;
            }
            
            //if the board is full
            if (board.isFull()) {
            	
            	//announces draw
                announceResult("Draw.", Color.GRAY);
                return;
            }
            //swaps the current player
            current = other(current);
            
            //updates the game status based on the type of game being played
            updateStatus();
            
            //the ai is playing
            if (vsAI && current == aiCode) {
            	
            	//add a small delay
            	aiMoveWithDelay();
            }
        }
        
        //function that adds small delay for ai
        private void aiMoveWithDelay() {
        	
            setInputEnabled(false);
            status.setText("AI thinking...");
            
   
            new javax.swing.Timer(350, ev -> {
                ((javax.swing.Timer) ev.getSource()).stop();
                doAIMove();
            }).start();
        }

        //function that does the ai move
        private void doAIMove() {
        	
        	//column = the ais move
            int col = aiEngine.getMove(board, aiCode, humanCode);
            
            //if no valid columns
            if (col < 0 || col > 6) {
                announceResult("Draw.", Color.GRAY);
                return;
            }
            
            //places the ai move
            int row = board.placeMove(col, aiCode);
            
            if (row == -1) {
                status.setText("AI attempted a full column. Your move.");
                setInputEnabled(true);
                return;
            }
            
            //places the disc in appropriate cell
            paintCell(row, col, aiCode);

            //checks if the move is a win
            if (board.checkWin(aiCode)) {
                announceResult("Computer (" + nameForCode(aiCode) + ") wins!", colorForCode(aiCode));
                return;
            }
            
            //if the board is full
            if (board.isFull()) {
                announceResult("Draw.", Color.GRAY);
                return;
            }
            
            //swaps the current player
            current = other(current);
            
            //updates the game status
            updateStatus();
            setInputEnabled(true);
        }
        
        //function that displays the result
        private void announceResult(String msg, Color accent) {
        	//sets the font and colour for the status label
            status.setFont(status.getFont().deriveFont(Font.BOLD, 18f));
            status.setForeground(accent.darker());
            status.setText(msg);
            
            setInputEnabled(false);
            
            //shows a message window
            JOptionPane.showMessageDialog(this, msg, "Game Over", JOptionPane.INFORMATION_MESSAGE);
        }
        
        //paints the cell with the colour of the player
        private void paintCell(int row, int col, char code) {
            cells[row][col].setDiscColor(colorForCode(code));
            cells[row][col].repaint();
        }

        //helper function that disables button when not needed (ie after a game is won/drawn)
        private void setInputEnabled(boolean enabled) {
            inputEnabled = enabled;
            for (JButton b : dropButtons) b.setEnabled(enabled);
        }

        //function the updates the status label
        private void updateStatus() {
            status.setFont(status.getFont().deriveFont(Font.PLAIN, 14f));
            status.setForeground(Color.DARK_GRAY);
            
            //if against the ai
            if (vsAI) {
            	//sets the status if humancode or aicode
                status.setText(current == humanCode ?
                        "Your move ("+nameForCode(humanCode)+")." :
                        "AI turn ("+nameForCode(aiCode)+").");
            } 
            //if against 2 player
            else {
            	//sets the status depending on who is playing
                status.setText((current == p1Code ? "Player 1" : "Player 2")
                        +" ("+nameForCode(current)+") to move.");
            }
        }
        
        //function the swaps who is playing
        private char other(char s) {
            if (vsAI) {
            	
            	//returns the colour of ai or human 
            	return (s == humanCode) ? aiCode : humanCode;
            }
            return (s == p1Code) ? p2Code : p1Code;
        }

        //cell panel class
        private static class CellPanel extends JPanel {
        	
        	//sets the panels to white
            private Color disc = new Color(245,245,245);
            
            //constructor
            CellPanel() { 
            	
            	//sets the size of the cell
            	setPreferredSize(new Dimension(80,80)); 
            	
            	setOpaque(false); 
            	}
            
            //sets the disc to the colour of the current player
            void setDiscColor(Color c) {
            	
            	disc = c; 
            	
            }
            //paints the cell, made using AI
            protected void paintComponent(Graphics g) {
            	
                super.paintComponent(g);
                
                Graphics2D g2 = (Graphics2D) g.create();
                
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int w=getWidth(), h=getHeight();
                
                int margin=Math.min(w,h)/8;
                
                int d=Math.min(w,h)-2*margin;
                
                g2.setColor(new Color(255,255,255,70));
                g2.fillOval(margin-2, margin-2, d+4, d+4);
                g2.setColor(disc);
                g2.fillOval(margin, margin, d, d);
                g2.setColor(new Color(0,0,0,60));
                g2.drawOval(margin, margin, d, d);
                g2.dispose();
            }
        }
    }

    public static void main(String[] args) {
    	
    	//starts the GUI 
        SwingUtilities.invokeLater(() -> new ConnectFourGUI().setVisible(true));
    }
}
