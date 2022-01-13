package virtualPet;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.border.EmptyBorder;

/**
 * Front end GUI for the virtual pet game group project.
 * All methods defined inside of PetGUIFrameCardLayout.Java are
 * @author Rianna M unless otherwise stated.
 * @author Nathan J if stated.
 * <p>Card layout help from Lazic Branis on github (<a href="https://github.com/BranislavLazic/SwingTutorials">here</a>) and on his youtube: (<a href="https://www.youtube.com/channel/UCD9USJK11rTGIOHCVWSZNhQ">here</a>).</p>
 * <p>Radio button actionEvent help from Jeff Bauer: (<a href="https://www.cs.fsu.edu/~jtbauer/cis3931/tutorial/ui/swing/radiobutton.html">here</a>).</p>
 * 
 */
public class PetGUIFrameCardLayout {

//TOP LEVEL AND OTHER VARIABLES	
	Pet myPet;
	String petName;
	String petSpecies;
	int radioSpeciesChoice;
	int radioColorChoice;
	
	/*Rianna (12/4/2021):
	* made the font a resource on the last day so the user doesn't need to download it.
	*/
	Font cool25 = createCool(25);
	Font cool30 = createCool(30);
	Font cool40 = createCool(40);
	Font cool45 = createCool(45);
	Font cool60 = createCool(60);
	
	JFrame topLevelFrame = new JFrame("My Virtual Pet");
	JPanel petGameContainerPane = new JPanel();
	CardLayout petGameContainerDeck = new CardLayout();

//WINDOW ONE VARIABLES:
	private final JPanel windowOne = new JPanel();
	private final JButton windowOneButton = new JButton("Confirm Pet Species!");
	private final JPanel windowOneCenterPetContainer = new JPanel();
	private final JLabel windowOneLabelCatLeft = new JLabel("");
	private final JLabel windowOneLabelSnakeRight = new JLabel("");
	private final JLabel windowOneLabelDogCenter = new JLabel("");
	private final JPanel windowOneRadioButtonContainer = new JPanel();
	private final JRadioButton windowOneRadioButton_ONE = new JRadioButton("");
	private final JRadioButton windowOneRadioButton_TWO = new JRadioButton("");
	private final JRadioButton windowOneRadioButton_THREE = new JRadioButton("");
	private final JLabel windowOneChooseAPet = new JLabel("Choose a pet:");
	private final ButtonGroup windowOneButtonGroup = new ButtonGroup();
	
	//Added By Nathan
	//private final JLabel windowOneLoadAPet = new JLabel("Load Pet");
	private final JButton windowOneLoadPetBttn = new JButton("Load Pet");

//WINDOW TWO VARIABLES:	
	private final JPanel windowTwo = new JPanel();
	private final JButton windowTwoButton = new JButton("Confirm My Pet!");
	private final JLabel windowTwoLabelPetSpecies = new JLabel("");
	private final JPanel windowTwoFormContainer = new JPanel();
	private final JLabel windowTwoLabelTitleCustomize = new JLabel("Customize your Pet:");
	private final JPanel windowTwoForm_NameContainer = new JPanel();
	private final JPanel windowTwoForm_ColorContainer = new JPanel();
	private final JLabel windowTwoLabelTitleName = new JLabel("Name:");
	private final JLabel windowTwoLabelTitleColor = new JLabel("Color:");
	private final JPanel windowTwoRadioButtonContainer = new JPanel();
	private final JRadioButton windowTwoRadioButton_ONE = new JRadioButton("");
	private final JRadioButton windowTwoRadioButton_TWO = new JRadioButton("");
	private final JRadioButton windowTwoRadioButton_THREE = new JRadioButton("");
	private final JTextField windowTwoTextNameSetter = new JTextField();
	private final JLabel windowTwoSpacer1 = new JLabel("                                        ");
	private final JLabel windowTwoSpacer2 = new JLabel("                                        ");
	private final ButtonGroup windowTwoButtonGroup = new ButtonGroup();
	
//WINDOW THREE VARIABLES:
	private final JPanel windowThree = new JPanel();
	private final JButton windowThreeButton = new JButton("Start Over!");
	private final JPanel windowThreeContolsAndOutputContainer = new JPanel();
	private final JLabel windowThreePetOutput = new JLabel("Fluffy looks at you expectantly...");
	private final JPanel windowThreePetImagesContainer = new JPanel();
	private final JLabel windowThreePetImage = new JLabel("");
	private final JPanel windowThreePetBarsContainer = new JPanel();
	private final JLabel windowThreePetBars_HAPPY = new JLabel("Happiness: 90/90");
	private final JLabel windowThreePetBars_HEALTH = new JLabel("Health: 90/90");
	private final JPanel windowThreePetInteractionsContainer = new JPanel();
	private final JPanel windowThreePetInterationsRowOne = new JPanel();
	private final JPanel windowThreePetInterationsRowTwo = new JPanel();
	private final JButton windowThreeButton_PET_ACTION_1 = new JButton("Pet Your Pet [Happiness+]");
	private final JButton windowThreeButton_PET_ACTION_2 = new JButton("Play [Happiness + & Health+]");
	private final JButton windowThreeButton_PET_ACTION_3 = new JButton("Speak [Happiness+]");
	private final JButton windowThreeButton_PET_ACTION_6 = new JButton("Trick [Happiness+]");
	private final JButton windowThreeButton_PET_ACTION_5 = new JButton("Special Action[Happiness+ & Health+]");
	private final JButton windowThreeButton_PET_ACTION_4 = new JButton("Feed Your Pet [Health+]");
	
	//Added by Nathan J on 11/29/21.
		private Timer j = new Timer(10000, new ActionListener() {
	        @Override 
	        public void actionPerformed(ActionEvent e)
	        {
	            myPet.healthDrop();
	            myPet.happyDrop();
	            updateBars();
	        }
	    });
		
	/**
	 * @author Nathan J 
	 * 12/4/21 
	 */
    public void savePet2() {

    	File outputFile = new File("./src/virtualPet/pet.txt");
    	try(PrintWriter writeMe = new PrintWriter(outputFile))
		{	
    		writeMe.format("%s", petName);
    		writeMe.println();
    		writeMe.format("%d", radioSpeciesChoice);
    		writeMe.println();
    		writeMe.format("%d", radioColorChoice);
    		writeMe.println();
				
			//outputFile.createNewFile();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
    	windowOneLoadPetBttn.setEnabled(true);
    }
    
    /**
	 * Added by Nathan J 
	 * 12/4/21 
	 */
    public void loadPet2()
    {    	
    	File file = new File("./src/virtualPet/pet.txt");
    	
    	
    	try(Scanner input = new Scanner(file)) {
    		petName = input.nextLine();
            radioSpeciesChoice = input.nextInt();
            radioColorChoice = input.nextInt();
        	//Rianna 12/4/21 Updates the bars and starts the timer in case of a new game/ a fresh game with a reload of a saved pet.
            setUpPetSpecies();
        	customizedPetSetup();
            updateBars();
			j.start();
			input.close();
        	petGameContainerDeck.show(petGameContainerPane, "3");

    	}catch(FileNotFoundException f)
		{
    		windowOneLoadPetBttn.setText("No saved pet found.");
		}

    	
    }
	
    /**
	 * Added by Nathan J 
	 * 12/4/21 
	 */
	private void setUpPetSpecies()
	{
		if(radioSpeciesChoice == 1)
		{
			petSpecies = "CAT";
		}
		else if(radioSpeciesChoice == 2)
		{
			petSpecies = "DOG";
		}
		else if(radioSpeciesChoice == 3)
		{
			petSpecies = "SNAKE";	
		}
	}

	/**
	 * Outer game container's logic. 
	 */
	public PetGUIFrameCardLayout() {
		
		topLevelFrame.getContentPane().setPreferredSize(new Dimension(1425, 1000));
		topLevelFrame.setResizable(true); //true so Nathan can see.
		topLevelFrame.setBounds(100, 100, 644, 328);
		
		petGameContainerPane.setBackground(Color.GRAY);
		petGameContainerPane.setLayout(petGameContainerDeck);
		petGameContainerPane.add(windowOne, "1");
		petGameContainerPane.add(windowTwo, "2");
		petGameContainerPane.add(windowThree, "3");
		
		windowOneContentsSetup();	
		windowOneContentsAdder();
		
		windowTwoContentsSetup();
		windowTwoContentsAdder();
		
		
		windowThreeContentsSetup();
		windowThreeContentsAdder();
		
		petGameContainerDeck.show(petGameContainerPane, "1");
		
		//Added by Nathan
		//Pressing the button loads the saved pet, if any.
		windowOneLoadPetBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			loadPet2();
			}
		});
		//
	
	//button action that transitions to and preps for windowTwo.
		windowOneButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(windowOneRadioButton_ONE.isSelected()) {
					radioSpeciesChoice = 1;
					petSpecies = "CAT";
				}
				
				else if(windowOneRadioButton_TWO.isSelected()) {
					radioSpeciesChoice = 2;
					petSpecies = "DOG";
				}
				
				else if(windowOneRadioButton_THREE.isSelected()) {
					radioSpeciesChoice = 3;
					petSpecies = "SNAKE";
				}
				
				windowTwoLabelPetSpecies.setIcon(new ImageIcon(PetGUIFrameCardLayout.class.getResource("/virtualPet/"+ petSpecies +"_2.png")));
				petGameContainerDeck.show(petGameContainerPane, "2");

				//Nathan:
				//I added this so if the user restarts, the text indicating the option to load a pet will re-appear.
				windowOneLoadPetBttn.setText("Load Pet");
			}
		});
		
		
		/**
		 * From https://www.cs.fsu.edu/~jtbauer/cis3931/tutorial/ui/swing/example-swing/RadioButtonDemo.java
		 * <p>- updates image in windowTwo Pet Panel when radio button is selected.</p>
		 */
	    class RadioListener implements ActionListener { 
	        public void actionPerformed(ActionEvent numberSelected) {
	        	windowTwoLabelPetSpecies.setIcon(new ImageIcon(PetGUIFrameCardLayout.class.getResource("/virtualPet/"+ petSpecies + "_" + numberSelected.getActionCommand() +".png")));
	        }
	    }
	    
	    // Register a listener for the radio buttons.
        RadioListener myListener = new RadioListener();
        windowTwoRadioButton_ONE.addActionListener(myListener);
        windowTwoRadioButton_TWO.addActionListener(myListener);
        windowTwoRadioButton_THREE.addActionListener(myListener);
        windowTwoRadioButton_ONE.setActionCommand("1");
        windowTwoRadioButton_TWO.setActionCommand("2");
        windowTwoRadioButton_THREE.setActionCommand("3");
       

      //button action that transitions to and preps for windowThree.
		windowTwoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				petName = windowTwoTextNameSetter.getText();
				
				if(windowTwoRadioButton_ONE.isSelected()) {
					 radioColorChoice = 1;
				}
				
				else if(windowTwoRadioButton_TWO.isSelected()) {
					 radioColorChoice = 2;
				}
				
				else if(windowTwoRadioButton_THREE.isSelected()) {
					 radioColorChoice = 3;
				}
				
				customizedPetSetup();
			
				updateBars();
				j.start();
				petGameContainerDeck.show(petGameContainerPane, "3");
				
				savePet2();
			}
		});
		
		//button action that transitions to and preps for returning to windowOne.
		windowThreeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {			
				j.stop(); //Rianna (12/4/21): stops the timer on restarts.
				
				//Rianna (12/4/21): window one text in cases of a previous game over.
				windowOneChooseAPet.setForeground(Color.WHITE);
				windowOneChooseAPet.setText("Choose a pet:");
				
				petGameContainerDeck.show(petGameContainerPane, "1");
			}
		});
		
		//Pet the Pet action listener. Updates Bars to reflect action when clicked.
		windowThreeButton_PET_ACTION_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				windowThreePetOutput.setText(myPet.petThePet());
				updateBars();
				
			}
		});
		
		//Play action listener. Updates Bars to reflect action when clicked.
		windowThreeButton_PET_ACTION_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				windowThreePetOutput.setText(myPet.play());
				updateBars();
			}
		});
		
		//Speak action listener. Updates Bars to reflect action when clicked.
		windowThreeButton_PET_ACTION_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				windowThreePetOutput.setText(myPet.speak());	
				updateBars();
			}
		});
		
		//Meal action listener. Updates Bars to reflect action when clicked.
		windowThreeButton_PET_ACTION_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				windowThreePetOutput.setText(myPet.meal());
				updateBars();
			}
		});
		
		//Special action listener. Updates Bars to reflect action when clicked.
		windowThreeButton_PET_ACTION_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				windowThreePetOutput.setText(myPet.specialAction());
				updateBars();
			}
		});
		
		//Trick action listener. Updates Bars to reflect action when clicked.
		windowThreeButton_PET_ACTION_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				windowThreePetOutput.setText(myPet.trick());
				updateBars();
			}
		});
		
		
		//Outermost container is set.		
		topLevelFrame.getContentPane().add(petGameContainerPane);
		topLevelFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		topLevelFrame.pack();
		topLevelFrame.setVisible(true);
		
	}
	
	/**
	 * Rianna, 11/29/21: <p>- Updates the health and happy bars.</p>
	 * 
	 * Nathan, 12/03/21: Adding gameover if stats drop to zero.
	 */
	private void updateBars() {
	//updates happy
		windowThreePetBars_HAPPY.setIcon((new ImageIcon(PetGUIFrameCardLayout.class.getResource("/virtualPet/HAPPY_"+myPet.happiness()+".png"))));
		windowThreePetBars_HAPPY.setText("Happiness: "+myPet.happiness()+"/90");
	//updates health
		windowThreePetBars_HEALTH.setIcon((new ImageIcon(PetGUIFrameCardLayout.class.getResource("/virtualPet/HEALTH_"+myPet.health()+".png"))));
		windowThreePetBars_HEALTH.setText("Health: "+myPet.health()+"/90");
		
	//gameover
		if(myPet.health() <= 0 || myPet.happiness() <= 0)
		{
			//Rianna (12/4/21): Changing window one text for game over scenario.
			windowOneChooseAPet.setForeground(Color.RED);
			windowOneChooseAPet.setText("GAME OVER! Reload a pet, or make a new one.");
			petGameContainerDeck.show(petGameContainerPane, "1");
			//Rianna (12/4/21): stops the timer on restarts of the game.
			j.stop();
		}
	}

	/**
	 * Rianna, 11/29/21: <p>- Initializes my pet to be the chosen pet type, and
	 * also changes the button text on window 3 to that chosen pet type's button text.</p>
	 */
	private void customizedPetSetup() {
		
		windowThreePetOutput.setText(petName + " looks at you expectantly...");
		windowThreePetImage.setIcon(new ImageIcon(PetGUIFrameCardLayout.class.getResource("/virtualPet/"+ petSpecies +"_"+radioColorChoice+".png")));
		
		switch(radioSpeciesChoice){
		case 1:
			myPet = new Cat(radioColorChoice, petName);
			break;
		case 2:
			myPet = new Dog(radioColorChoice, petName);
			break;
		case 3:
			myPet = new Snake(radioColorChoice, petName);
			break;
		}
		
		if (myPet instanceof Cat) {
		//play
			windowThreeButton_PET_ACTION_2.setText("Play with Yarn. [Happiness + & Health+]");
		//special action
			windowThreeButton_PET_ACTION_5.setText("Chase a Mouse. [Happiness + & Health+]");
		//trick
			windowThreeButton_PET_ACTION_6.setText("Shine a Laser Pointer. [Happiness +]");		
		}
		
		
		if (myPet instanceof Dog) {
		//play
			windowThreeButton_PET_ACTION_2.setText("Give Dog a Kong. [Happiness + & Health+]");
		//special action
			windowThreeButton_PET_ACTION_5.setText("Roll Over! [Happiness + & Health+]");
		//trick
			windowThreeButton_PET_ACTION_6.setText("Play ball! [Happiness +]");		
		}
		
		if (myPet instanceof Snake) {
		//play
			windowThreeButton_PET_ACTION_2.setText("Give Snake Some Live Prey. [Happiness + & Health+]");
		//special action
			windowThreeButton_PET_ACTION_5.setText("Coil The Snake. [Happiness +]");
		//trick
			windowThreeButton_PET_ACTION_6.setText("Handle Your Snake [Happiness +]");	
		}
		
		//Rianna: If there is more than one play through, the output bars need to be refreshed before the user can see them. This is why updateBars(); is here now.
		updateBars(); 
		
	}
	
	/**
	 * Adds content to parent containers for anything seen in windowThree 
	 */
	private void windowThreeContentsAdder() {
		windowThree.add(windowThreeButton, BorderLayout.SOUTH);
		windowThree.add(windowThreeContolsAndOutputContainer, BorderLayout.CENTER);		
		windowThreeContolsAndOutputContainer.add(windowThreePetOutput, BorderLayout.NORTH);	
		windowThreeContolsAndOutputContainer.add(windowThreePetInteractionsContainer, BorderLayout.CENTER);
		windowThreePetInteractionsContainer.add(windowThreePetInterationsRowOne, BorderLayout.NORTH);
		windowThreePetInterationsRowOne.add(windowThreeButton_PET_ACTION_1);
		windowThreePetInterationsRowOne.add(windowThreeButton_PET_ACTION_2);
		windowThreePetInterationsRowOne.add(windowThreeButton_PET_ACTION_3);
		windowThreePetInteractionsContainer.add(windowThreePetInterationsRowTwo, BorderLayout.SOUTH);
		windowThreePetInterationsRowTwo.add(windowThreeButton_PET_ACTION_4);
		windowThreePetInterationsRowTwo.add(windowThreeButton_PET_ACTION_5);
		windowThreePetInterationsRowTwo.add(windowThreeButton_PET_ACTION_6);
		windowThree.add(windowThreePetImagesContainer, BorderLayout.NORTH);
		windowThreePetImagesContainer.add(windowThreePetBarsContainer, BorderLayout.WEST);
		windowThreePetBars_HAPPY.setIcon(new ImageIcon(PetGUIFrameCardLayout.class.getResource("/virtualPet/HAPPY_90.png")));
		windowThreePetBarsContainer.add(windowThreePetBars_HAPPY, BorderLayout.NORTH);
		windowThreePetBars_HEALTH.setIcon(new ImageIcon(PetGUIFrameCardLayout.class.getResource("/virtualPet/HEALTH_90.png")));
		windowThreePetBarsContainer.add(windowThreePetBars_HEALTH, BorderLayout.SOUTH);
		windowThreePetImagesContainer.add(windowThreePetImage);
	}
	
	/**
	 * Adds DEFAULT customization for anything seen in windowThree.
	 */
	private void windowThreeContentsSetup() {
		windowThree.setOpaque(false);
		windowThree.setLayout(new BorderLayout(0, 0));
		windowThreeButton.setFocusTraversalKeysEnabled(false);
		windowThreeButton.setFocusPainted(false);
		windowThreeButton.setBackground(Color.DARK_GRAY);
		windowThreeButton.setFocusable(false);
		windowThreeButton.setBorder(null);
		windowThreeButton.setForeground(Color.WHITE);
		windowThreeButton.setFont(cool25);
		windowThreeContolsAndOutputContainer.setBackground(Color.DARK_GRAY);
		windowThreePetOutput.setBorder(new EmptyBorder(0, 0, 25, 0));
		windowThreePetOutput.setBackground(Color.LIGHT_GRAY);
		windowThreePetOutput.setForeground(Color.WHITE);
		windowThreePetOutput.setFont(cool40);
		windowThreePetOutput.setHorizontalAlignment(SwingConstants.CENTER);
		windowThreeContolsAndOutputContainer.setLayout(new BorderLayout(0, 0));
		windowThreePetInteractionsContainer.setBackground(SystemColor.controlShadow);
		windowThreePetInteractionsContainer.setBorder(new EmptyBorder(100, 0, 100, 0));
		windowThreePetInteractionsContainer.setLayout(new BorderLayout(0, 0));
		windowThreePetInterationsRowOne.setOpaque(false);
		windowThreePetInterationsRowOne.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		windowThreeButton_PET_ACTION_1.setForeground(Color.DARK_GRAY);
		windowThreeButton_PET_ACTION_1.setFont(cool30);
		windowThreeButton_PET_ACTION_1.setFocusable(false);
		windowThreeButton_PET_ACTION_1.setFocusPainted(false);
		windowThreeButton_PET_ACTION_1.setBorder(new EmptyBorder(10, 10, 10, 10));
		windowThreeButton_PET_ACTION_1.setBackground(Color.LIGHT_GRAY);
		windowThreeButton_PET_ACTION_2.setForeground(Color.DARK_GRAY);
		windowThreeButton_PET_ACTION_2.setFont(cool30);
		windowThreeButton_PET_ACTION_2.setFocusable(false);
		windowThreeButton_PET_ACTION_2.setFocusPainted(false);
		windowThreeButton_PET_ACTION_2.setBorder(new EmptyBorder(10, 10, 10, 10));
		windowThreeButton_PET_ACTION_2.setBackground(Color.LIGHT_GRAY);
		windowThreeButton_PET_ACTION_3.setForeground(Color.DARK_GRAY);
		windowThreeButton_PET_ACTION_3.setFont(cool30);
		windowThreeButton_PET_ACTION_3.setFocusable(false);
		windowThreeButton_PET_ACTION_3.setFocusPainted(false);
		windowThreeButton_PET_ACTION_3.setBorder(new EmptyBorder(10, 10, 10, 10));
		windowThreeButton_PET_ACTION_3.setBackground(Color.LIGHT_GRAY);
		windowThreePetInterationsRowTwo.setOpaque(false);
		windowThreePetInterationsRowTwo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		windowThreeButton_PET_ACTION_4.setForeground(Color.DARK_GRAY);
		windowThreeButton_PET_ACTION_4.setFont(cool30);
		windowThreeButton_PET_ACTION_4.setFocusable(false);
		windowThreeButton_PET_ACTION_4.setFocusPainted(false);
		windowThreeButton_PET_ACTION_4.setBorder(new EmptyBorder(10, 10, 10, 10));
		windowThreeButton_PET_ACTION_4.setBackground(Color.LIGHT_GRAY);
		windowThreeButton_PET_ACTION_5.setForeground(Color.DARK_GRAY);
		windowThreeButton_PET_ACTION_5.setFont(cool30);
		windowThreeButton_PET_ACTION_5.setFocusable(false);
		windowThreeButton_PET_ACTION_5.setFocusPainted(false);
		windowThreeButton_PET_ACTION_5.setBorder(new EmptyBorder(10, 10, 10, 10));
		windowThreeButton_PET_ACTION_5.setBackground(Color.LIGHT_GRAY);
		windowThreeButton_PET_ACTION_6.setForeground(Color.DARK_GRAY);
		windowThreeButton_PET_ACTION_6.setFont(cool30);
		windowThreeButton_PET_ACTION_6.setFocusable(false);
		windowThreeButton_PET_ACTION_6.setFocusPainted(false);
		windowThreeButton_PET_ACTION_6.setBorder(new EmptyBorder(10, 10, 10, 10));
		windowThreeButton_PET_ACTION_6.setBackground(Color.LIGHT_GRAY);
		windowThreePetImagesContainer.setOpaque(false);
		windowThreePetImagesContainer.setLayout(new BorderLayout(0, 0));
		windowThreePetBarsContainer.setBorder(new EmptyBorder(100, 100, 100, 100));
		windowThreePetBarsContainer.setOpaque(false);
		windowThreePetBarsContainer.setLayout(new BorderLayout(0, 0));
		windowThreePetBars_HAPPY.setHorizontalAlignment(SwingConstants.LEFT);
		windowThreePetBars_HAPPY.setForeground(Color.WHITE);
		windowThreePetBars_HAPPY.setFont(cool40);
		windowThreePetBars_HAPPY.setBackground(Color.BLUE);
		windowThreePetBars_HEALTH.setHorizontalAlignment(SwingConstants.LEFT);
		windowThreePetBars_HEALTH.setForeground(Color.WHITE);
		windowThreePetBars_HEALTH.setFont(cool40);
		windowThreePetBars_HEALTH.setBackground(Color.RED);
		windowThreePetImage.setIcon(new ImageIcon(PetGUIFrameCardLayout.class.getResource("/virtualPet/CAT_1.png")));
		windowThreePetImage.setHorizontalAlignment(SwingConstants.CENTER);
		windowThreePetImage.setForeground(Color.WHITE);
		windowThreePetImage.setFont(cool40);
		windowThreePetImage.setBackground(Color.LIGHT_GRAY);
	}

	/**
	 * Adds content to parent containers for anything seen in windowTwo 
	 */
	private void windowTwoContentsAdder() {
		windowTwo.add(windowTwoButton, BorderLayout.SOUTH);
		windowTwo.add(windowTwoLabelPetSpecies, BorderLayout.WEST);
		windowTwo.add(windowTwoFormContainer, BorderLayout.CENTER);
		windowTwoFormContainer.add(windowTwoLabelTitleCustomize, BorderLayout.NORTH);
		windowTwoFormContainer.add(windowTwoForm_NameContainer, BorderLayout.CENTER);
		windowTwoForm_NameContainer.add(windowTwoLabelTitleName, BorderLayout.NORTH);
		windowTwoForm_NameContainer.add(windowTwoTextNameSetter, BorderLayout.CENTER);
		windowTwoForm_NameContainer.add(windowTwoSpacer1, BorderLayout.WEST);
		windowTwoFormContainer.add(windowTwoForm_ColorContainer, BorderLayout.SOUTH);
		windowTwoForm_ColorContainer.add(windowTwoLabelTitleColor, BorderLayout.NORTH);
		windowTwoForm_ColorContainer.add(windowTwoRadioButtonContainer, BorderLayout.SOUTH);
		windowTwoButtonGroup.add(windowTwoRadioButton_ONE);
		windowTwoRadioButtonContainer.add(windowTwoRadioButton_ONE, BorderLayout.WEST);
		windowTwoButtonGroup.add(windowTwoRadioButton_TWO);
		windowTwoRadioButtonContainer.add(windowTwoRadioButton_TWO, BorderLayout.CENTER);
		windowTwoButtonGroup.add(windowTwoRadioButton_THREE);
		windowTwoRadioButtonContainer.add(windowTwoRadioButton_THREE, BorderLayout.EAST);
	}
	
	/**
	 * Adds DEFAULT customization for anything seen in windowTwo.
	 */
	private void windowTwoContentsSetup() {
		windowTwo.setOpaque(false);
		windowTwo.setLayout(new BorderLayout(0, 0));
		windowTwoTextNameSetter.setText("Fluffy");
		windowTwoTextNameSetter.setFont(cool25);
		windowTwoTextNameSetter.setHorizontalAlignment(SwingConstants.CENTER);
		windowTwoTextNameSetter.setColumns(10);
		windowTwoButton.setFocusTraversalKeysEnabled(false);
		windowTwoButton.setFocusPainted(false);
		windowTwoButton.setForeground(Color.WHITE);
		windowTwoButton.setFont(cool25);
		windowTwoButton.setBorder(null);
		windowTwoButton.setFocusable(false);
		windowTwoButton.setBackground(Color.DARK_GRAY);
		windowTwoFormContainer.setOpaque(false);
		windowTwoLabelPetSpecies.setIcon(new ImageIcon(PetGUIFrameCardLayout.class.getResource("/virtualPet/CAT_2.png")));
		windowTwoFormContainer.setLayout(new BorderLayout(0, 0));
		windowTwoLabelTitleCustomize.setForeground(Color.WHITE);
		windowTwoLabelTitleCustomize.setFont(cool45);
		windowTwoLabelTitleCustomize.setHorizontalAlignment(SwingConstants.CENTER);
		windowTwoForm_NameContainer.setBorder(new EmptyBorder(100, 0, 100, 0));
		windowTwoForm_NameContainer.setOpaque(false);
		windowTwoForm_NameContainer.setLayout(new BorderLayout(0, 0));
		windowTwoLabelTitleName.setBorder(new EmptyBorder(0, 0, 25, 0));
		windowTwoLabelTitleName.setHorizontalAlignment(SwingConstants.CENTER);
		windowTwoLabelTitleName.setForeground(Color.WHITE);
		windowTwoLabelTitleName.setFont(cool30);
		windowTwoSpacer1.setHorizontalAlignment(SwingConstants.CENTER);
		windowTwoSpacer1.setForeground(Color.WHITE);
		windowTwoSpacer1.setFont(cool60);
		windowTwoSpacer2.setHorizontalAlignment(SwingConstants.CENTER);
		windowTwoSpacer2.setForeground(Color.WHITE);
		windowTwoSpacer2.setFont(cool60);
		windowTwoForm_NameContainer.add(windowTwoSpacer2, BorderLayout.EAST);
		windowTwoForm_ColorContainer.setBorder(new EmptyBorder(0, 100, 0, 100));
		windowTwoForm_ColorContainer.setOpaque(false);
		windowTwoForm_ColorContainer.setLayout(new BorderLayout(0, 0));
		windowTwoLabelTitleColor.setBorder(new EmptyBorder(0, 0, 25, 0));
		windowTwoLabelTitleColor.setHorizontalAlignment(SwingConstants.CENTER);
		windowTwoLabelTitleColor.setForeground(Color.WHITE);
		windowTwoLabelTitleColor.setFont(cool30);
		windowTwoRadioButtonContainer.setOpaque(false);
		windowTwoRadioButtonContainer.setBorder(new EmptyBorder(0, 100, 25, 100));
		windowTwoRadioButtonContainer.setLayout(new BorderLayout(0, 0));
		windowTwoRadioButton_ONE.setOpaque(false);
		windowTwoRadioButton_ONE.setHorizontalTextPosition(SwingConstants.CENTER);
		windowTwoRadioButton_ONE.setHorizontalAlignment(SwingConstants.CENTER);
		windowTwoRadioButton_TWO.setSelected(true);
		windowTwoRadioButton_TWO.setOpaque(false);
		windowTwoRadioButton_TWO.setHorizontalTextPosition(SwingConstants.CENTER);
		windowTwoRadioButton_TWO.setHorizontalAlignment(SwingConstants.CENTER);
		windowTwoRadioButton_THREE.setOpaque(false);
		windowTwoRadioButton_THREE.setHorizontalTextPosition(SwingConstants.CENTER);
		windowTwoRadioButton_THREE.setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	/**
	 * Adds content to parent containers for anything seen in windowOne 
	 */
	private void windowOneContentsAdder() {
		windowOne.add(windowOneButton, BorderLayout.SOUTH);
		windowOne.add(windowOneLoadPetBttn, BorderLayout.NORTH);
		windowOneRadioButtonContainer.setBorder(new EmptyBorder(0, 100, 25, 100));
		windowOneRadioButtonContainer.setOpaque(false);
		windowOne.add(windowOneCenterPetContainer, BorderLayout.CENTER);
		windowOneCenterPetContainer.add(windowOneLabelSnakeRight, BorderLayout.EAST);
		windowOneCenterPetContainer.add(windowOneLabelCatLeft, BorderLayout.WEST);
		windowOneCenterPetContainer.add(windowOneLabelDogCenter, BorderLayout.CENTER);
		windowOneCenterPetContainer.add(windowOneRadioButtonContainer, BorderLayout.SOUTH);
		windowOneButtonGroup.add(windowOneRadioButton_ONE);
		windowOneRadioButtonContainer.add(windowOneRadioButton_ONE, BorderLayout.WEST);
		windowOneButtonGroup.add(windowOneRadioButton_TWO);
		windowOneRadioButtonContainer.add(windowOneRadioButton_TWO, BorderLayout.CENTER);
		windowOneButtonGroup.add(windowOneRadioButton_THREE);
		windowOneRadioButtonContainer.add(windowOneRadioButton_THREE, BorderLayout.EAST);
		windowOneCenterPetContainer.add(windowOneChooseAPet, BorderLayout.NORTH);
	}

	/**
	 * Adds DEFAULT customization for anything seen in windowOne.
	 */
	private void windowOneContentsSetup() {
		windowOne.setOpaque(false);
		windowOne.setLayout(new BorderLayout(0, 0));
		windowOneButton.setFocusTraversalKeysEnabled(false);
		windowOneButton.setFocusPainted(false);
		windowOneButton.setForeground(Color.WHITE);
		windowOneButton.setFont(cool25);
		windowOneButton.setBackground(Color.DARK_GRAY);
		windowOneButton.setFocusable(false);
		windowOneButton.setBorder(null);
		windowOneLabelCatLeft.setIcon(new ImageIcon(PetGUIFrameCardLayout.class.getResource("/virtualPet/CAT_1.png")));
		windowOneLabelCatLeft.setHorizontalAlignment(SwingConstants.CENTER);
		windowOneCenterPetContainer.setOpaque(false);
		windowOneCenterPetContainer.setLayout(new BorderLayout(0, 0));
		windowOneLabelSnakeRight.setIcon(new ImageIcon(PetGUIFrameCardLayout.class.getResource("/virtualPet/SNAKE_2.png")));
		windowOneLabelSnakeRight.setHorizontalAlignment(SwingConstants.CENTER);
		windowOneLabelSnakeRight.setForeground(Color.WHITE);
		windowOneLabelSnakeRight.setFont(cool25);
		windowOneLabelDogCenter.setIcon(new ImageIcon(PetGUIFrameCardLayout.class.getResource("/virtualPet/DOG_2.png")));
		windowOneLabelDogCenter.setHorizontalAlignment(SwingConstants.CENTER);
		windowOneRadioButtonContainer.setLayout(new BorderLayout(0, 0));
		windowOneRadioButton_ONE.setHorizontalTextPosition(SwingConstants.CENTER);
		windowOneRadioButton_ONE.setOpaque(false);
		windowOneRadioButton_ONE.setHorizontalAlignment(SwingConstants.CENTER);
		windowOneRadioButton_TWO.setSelected(true);
		windowOneRadioButton_TWO.setOpaque(false);
		windowOneRadioButton_TWO.setHorizontalTextPosition(SwingConstants.CENTER);
		windowOneRadioButton_TWO.setHorizontalAlignment(SwingConstants.CENTER);
		windowOneRadioButton_THREE.setOpaque(false);
		windowOneRadioButton_THREE.setHorizontalTextPosition(SwingConstants.CENTER);
		windowOneRadioButton_THREE.setHorizontalAlignment(SwingConstants.CENTER);
		windowOneChooseAPet.setForeground(Color.WHITE);
		windowOneChooseAPet.setFont(cool45);
		windowOneChooseAPet.setHorizontalAlignment(SwingConstants.CENTER);
		//Rianna: 12/4/21 customized the load button to match the other buttons 
		windowOneLoadPetBttn.setHorizontalAlignment(SwingConstants.CENTER);
		windowOneLoadPetBttn.setFocusTraversalKeysEnabled(false);
		windowOneLoadPetBttn.setFocusPainted(false);
		windowOneLoadPetBttn.setForeground(Color.WHITE);
		windowOneLoadPetBttn.setFont(cool25);
		windowOneLoadPetBttn.setBackground(Color.DARK_GRAY);
		windowOneLoadPetBttn.setFocusable(false);
		windowOneLoadPetBttn.setBorder(null);
	}
	
	
	/**
	 * @author Rianna M (12/4/21)
	 * <p>From <a href= "https://stackoverflow.com/questions/7155010/how-to-set-a-custom-fonts-size-and-other-attributes-bold-italic-etc-in-java">StackOverflow</a>
	 * creates fonts from a ttf in the source file.
	 * </p>
	 * @return font from file with proper size.
	 */
	public Font createCool(int mySize) {
	    try {
	        InputStream myFontLocation = PetGUIFrameCardLayout.class.getResourceAsStream("COOLVETICA CONDENSED RG.TTF");
	        Font cool = Font.createFont(Font.TRUETYPE_FONT, myFontLocation).deriveFont(Font.PLAIN, mySize);
	        myFontLocation.close();
	        return cool;
	    } catch (FontFormatException | IOException ex) {
	    	System.err.println("Font not found");
	        return null;
	    }
	}
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new PetGUIFrameCardLayout();
			}
		});
	}

}
