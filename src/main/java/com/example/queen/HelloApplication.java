package com.example.queen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


/*
*   MY OLD IMPORTS
* */
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class HelloApplication extends Application {
    int x = 1;
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();

        Menu(stage);
        SetWalls(); // creates the walls from an image file
    }


    AnimationTimer timerMenu;


    // MENU
//    MediaPlayer mediaPlayer1;
    public void Menu(Stage stage){
        Group menuRoot = new Group();
        viewAmaraMenu.setTranslateX(600);
        viewAmaraMenu.setTranslateY(660);
        menuBackView1a.setTranslateY(20);
        menuBackView1b.setTranslateY(20);
        menuBackView1c.setTranslateY(20);

        String musicFile = "file:media/asdfghj.mp3";     // For example
//        Media sound = new Media(new File(musicFile).toURI().toString());
//        mediaPlayer1 = new MediaPlayer(sound);
//        mediaPlayer1.setVolume(0.7);

//        mediaPlayer1.play();

        timerMenu = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // animate first layer
                menuBackView1a.setTranslateX(menuBackView1a.getTranslateX() - 4); // translation speed
                menuBackView1b.setTranslateX(menuBackView1b.getTranslateX() - 4);
                menuBackView1c.setTranslateX(menuBackView1c.getTranslateX() - 4);
                if(menuBackView1a.getTranslateX() == -1260){
                    menuBackView1a.setTranslateX(menuBackView1a.getTranslateX() + 3720);
                }
                else if(menuBackView1b.getTranslateX() == -1260){
                    menuBackView1b.setTranslateX(menuBackView1b.getTranslateX() + 3720);
                }
                else if(menuBackView1c.getTranslateX() == -1260){
                    menuBackView1c.setTranslateX(menuBackView1c.getTranslateX() + 3720);
                }
            }
        };
        timerMenu.start();

        menuRoot.getChildren().addAll(menuBackView3, menuBackView2, menuBackView1a,menuBackView1b, menuBackView1c, viewAmaraMenu );
        menuBackView1a.setTranslateX(-1240);
        menuBackView1b.setTranslateX(0);
        menuBackView1c.setTranslateX(1240);
        Scene menuScene = new Scene(menuRoot, 1240, 740);

        stage.setScene(menuScene);
        stage.show();

        // Control
        menuScene.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ENTER){
                    timerMenu.stop();
                    Cutscene(stage);
                }
            }});
    }



    // CUTSCENE
    static int counterCutscene = 0;
    static int halfCounterCutscene = 0;
    public AnimationTimer timerCutscene;

    // variables
    String imageAmaraNameCutscene = "file:media/AmaraLight.png";
    Image imageAmaraCutscene = new Image(imageAmaraNameCutscene);
    ImageView viewAmaraCutscene = new ImageView(imageAmaraCutscene);

    public void Cutscene(Stage stage){

        Group cutsceneRoot = new Group();
        Scene cutsceneScene = new Scene(cutsceneRoot, 1240, 740);

        stage.setScene(cutsceneScene);
        stage.show();

        //cutsceneRoot.getChildren().addAll(menuBackView3, menuBackView1b, viewAmaraCutscene );

        menuBackView1b.setTranslateX(0);
        viewAmaraCutscene.setTranslateX(600);
        viewAmaraCutscene.setTranslateY(660);


        Text text = new Text(640, 590, " I've been lost for days!\n I need to get home.");
        text.setScaleX(1.5);
        text.setScaleY(1.5);

        Rectangle textBox = new Rectangle(600, 550, 240, 90);
        textBox.setFill(Color.PINK);
        textBox.setArcWidth(50.0);
        textBox.setArcHeight(50.0);

        cutsceneRoot.getChildren().addAll(menuBackView3, menuBackView1b, viewAmaraCutscene, textBox, text );

        timerCutscene = new AnimationTimer() {
            @Override
            public void handle(long now) {
                counterCutscene = counterCutscene + 1;
                halfCounterCutscene = (int) (counterCutscene);

                if(halfCounterCutscene > 0 &&  halfCounterCutscene <= 250){
                    //viewAmaraCutscene.setTranslateX(viewAmaraCutscene.getTranslateX() + 4);
                }
                else if(halfCounterCutscene > 250 &&  halfCounterCutscene <= 450){
                    text.setScaleX(1.7);
                    text.setScaleY(1.7);

                    //text.setTranslateY(660);
                    text.setText("I really miss home... \nlet's go!");
                    textBox.setTranslateX(-10);
                    //viewAmaraCutscene.setTranslateY(viewAmaraCutscene.getTranslateY() + 3);
                }
                else if(halfCounterCutscene > 450 &&  halfCounterCutscene <= 650){
                    text.setTranslateY(2000);
                    textBox.setTranslateY(2000);
                    viewAmaraCutscene.setImage(imageAmaraR1);
                    viewAmaraCutscene.setTranslateX(viewAmaraCutscene.getTranslateX() + 3.5);
                }
                if(halfCounterCutscene == 651){
                    timerCutscene.stop();
                    Scene1(stage);
                }
            }
        };
        timerCutscene.start();

        // skip cutscene by pressing any key
        cutsceneScene.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                //timerCutscene.stop();
                //Scene1(stage);
            }});
    }




    //SCENE 1
    public AnimationTimer timerScene1;
//    MediaPlayer mediaPlayer2;

    public void Scene1(Stage stage){
        scene1Root = new Group();
        scene1Scene = new Scene(scene1Root, 1240, 740);

        String musicFile = "asdfgh.mp3";     // For example
//        Media sound = new Media(new File(musicFile).toURI().toString());
//        mediaPlayer2 = new MediaPlayer(sound);



        try {
            Thread.sleep(100);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        for(int i = 0; i<68; i++){

            try {
                Thread.sleep(30);
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

//            mediaPlayer1.setVolume(mediaPlayer1.getVolume() - 0.01);
        }
//        mediaPlayer1.setVolume(0.0);
//        mediaPlayer1.stop();
        AddItems();
        MoveWalls(0);



        // running the game
        timerScene1 = new AnimationTimer() {
            @Override
            public void handle(long now) {
                UpdateGame();

                if(wallLocation > 1){

//                    mediaPlayer2.play();
                }

                // Change Amara's sprite depending on the direction she is moving
                if(right){
                    viewAmara.setImage(imageAmaraR1);
                }
                else if(left) {
                    viewAmara.setImage(imageAmaraL1);
                }
                else{
                    viewAmara.setImage(imageAmara);
                }

                if(viewAmara.getTranslateY() > 1300) { // if she falls down

                    if(wallLocation < 3900){
                        viewAmara.setTranslateY(700);
                        MoveWalls(-wallLocation);
                    }
                    else if(wallLocation >= 3900 && wallLocation < 8350){
                        viewAmara.setTranslateY(700);
                        MoveWalls(-wallLocation+3900);
                    }
                    else{
                        viewAmara.setTranslateY(700);
                        MoveWalls(-wallLocation+8100);
                    }

                }

                if(!Fiona) { // if jungle key is taken
                    viewFiona.setVisible(false);
                    jungleGrassWall.setTranslateY(20000);
                    //viewJungleWall.setTranslateY(20000);
                    JGText.setText("     You found her!\n     Thank you\n     I'll let you go now.");
                    JGText.setScaleX(1.4);
                    JGText.setScaleY(1.4);

                }
                if(!grassKey) { // if jungle key is taken
                    viewGrassKey.setVisible(false);
                    grassWall.setTranslateY(20000);
                    viewGrassWall.setTranslateY(20000);
                }
                if(!boat) { // if jungle key is taken
                    viewBoat.setVisible(false);
                    grassCityWall.setTranslateY(20000);
                    //viewGrassCityWall.setTranslateY(20000);
                    GCText.setText("Is that boat for me!?\nAnd I heard you saved Fiona!\nThank You! ");
                    GCText.setScaleX(1.05);
                    GCText.setScaleY(1.05);
                }

                if(Win) {
                    timerScene1.stop();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    FinalCutscene(stage);
                }

            }
        };
        timerScene1.start();

        scene1Root.getChildren().addAll(walls); // add rectangles
        scene1Root.getChildren().addAll( viewScene1BackBack, scene1BackView, viewFiona, viewGrassCityWall, viewJungleWall, viewAmara, finalKey, viewGrassWall, viewGrassPlatform);
        scene1Root.getChildren().addAll( viewGrassKey, viewBoat, viewCityPlatform, JGTextBox , JGText, GCTextBox, GCText);
        //scene1Root.getChildren().add( );

        stage.setScene(scene1Scene);
        stage.show();
    }


    // keep track of how many times the update game methods has been called
    static int updateGameCounter = 0;
    static int updateGameCounterMod = 0;

    // game input and events
    public void UpdateGame(){

        // move platforms
        updateGameCounter = updateGameCounter + 1;
        updateGameCounterMod = updateGameCounter%600;
        if(updateGameCounterMod < 300){ // grass
            viewGrassPlatform.setTranslateX(viewGrassPlatform.getTranslateX() - 3.5);
            grassPlatform.setTranslateX(grassPlatform.getTranslateX() - 3.5);

        }
        else{
            viewGrassPlatform.setTranslateX(viewGrassPlatform.getTranslateX() + 3.5);
            grassPlatform.setTranslateX(grassPlatform.getTranslateX() + 3.5);
        }

        if(updateGameCounterMod < 150){ // city
            viewCityPlatform.setTranslateY(viewCityPlatform.getTranslateY() - 2.5);
            cityPlatform.setTranslateY(cityPlatform.getTranslateY() - 2.5);
            if(viewAmara.getBoundsInParent().intersects(cityPlatform.getBoundsInParent()))viewAmara.setTranslateY(viewAmara.getTranslateY() - 2.5); // move amara
        }
        else if(updateGameCounterMod >= 300 && updateGameCounterMod < 450){
            viewCityPlatform.setTranslateY(viewCityPlatform.getTranslateY() + 2.5);
            cityPlatform.setTranslateY(cityPlatform.getTranslateY() + 2.5);
        }



        CheckItems();

        UserInput();				// Check user-inputs every frame
        intercept = CheckIntercept();			// Check if amara is intercepting a platform and thus making 'intercept' = true



        if(intercept && land){ // if Amara is on a platform and runs into a wall
            CheckHorizontalDirection();
            CheckVerticalDirection();
            //velocityY = 1;
            viewAmara.setTranslateY(viewAmara.getTranslateY() + 3);
            left= false;
            right = false;
        }
        else if(land){ // if she is on a platform without running into a wall
            if(left) MoveWalls(-7);	// make movements depending on what keys are pressed
            if(right) MoveWalls(7);
        }

        else if(intercept){// if intercepting a wall without landing on a platform
            CheckVerticalDirection();
            velocityY = 1;
        }

        else if(!intercept && !land){ // if no interception nor landing
            if(left) MoveWalls(-7);			// make movements depending on what keys are pressed
            if(right) MoveWalls(7);

            if(velocityY < 30){// change velocity for every frame (acceleration) and cap velocity at x pixels / frame
                velocityY = velocityY + 0.34; // 0.34
            }
            viewAmara.setTranslateY(viewAmara.getTranslateY() + velocityY); // Change her location depending on velocity

        }

        // check if above platform
        viewAmara.setTranslateY(viewAmara.getTranslateY() + 7);  // Move Amara down 2
        if(!(viewAmara.getBoundsInParent().intersects(walls.get(index).getBoundsInParent()))){
            land = false; // start accelerating her
        }
        viewAmara.setTranslateY(viewAmara.getTranslateY() - 7);  // Move Amara up 2

    }

    public void CheckItems(){

        if(viewAmara.getBoundsInParent().intersects(viewFiona.getBoundsInParent())){
            System.out.println("took fiona");
            Fiona = false;
        }
        if(viewAmara.getBoundsInParent().intersects(viewGrassKey.getBoundsInParent())){
            System.out.println("took Grass Key");
            grassKey = false;
        }
        if(viewAmara.getBoundsInParent().intersects(viewBoat.getBoundsInParent())){
            System.out.println("took boat");
            boat = false;
        }
        if(viewAmara.getBoundsInParent().intersects(finalKey.getBoundsInParent())){
            System.out.println("you win");
            Win = true;
        }

    }


    // MOVEWALLS - move walls according to the integer passed to the method
    static int wallLocation = 0;
    public void MoveWalls(int x){

        wallLocation = wallLocation + x;

        for(Rectangle wall : walls){
            wall.setTranslateX(wall.getTranslateX() - x); // move walls x pixels
        }

        viewFiona.setTranslateX(viewFiona.getTranslateX() - x);
        finalKey.setTranslateX(finalKey.getTranslateX() - x);
        scene1BackView.setTranslateX(scene1BackView.getTranslateX() - x);
        viewJungleWall.setTranslateX(viewJungleWall.getTranslateX() - x);
        viewGrassWall.setTranslateX(viewGrassWall.getTranslateX() - x);
        viewGrassCityWall.setTranslateX(viewGrassCityWall.getTranslateX() - x);
        viewGrassPlatform.setTranslateX(viewGrassPlatform.getTranslateX() - x);
        viewGrassKey.setTranslateX(viewGrassKey.getTranslateX() - x);
        viewBoat.setTranslateX(viewBoat.getTranslateX() - x);
        viewCityPlatform.setTranslateX(viewCityPlatform.getTranslateX() - x);
        JGText.setTranslateX(JGText.getTranslateX() - x);
        JGTextBox.setTranslateX(JGTextBox.getTranslateX() - x);
        GCText.setTranslateX(GCText.getTranslateX() - x);
        GCTextBox.setTranslateX(GCTextBox.getTranslateX() - x);


    }
    // MOVEAMARA
    public void MoveAmara(int y){
        viewAmara.setTranslateY(viewAmara.getTranslateY() + y);  // Move Amara y pixels in the y direction
    }
    // CHECKVERTICALDIRECTION - if interception occurs in the vertical direction, then move Amara back
    public void CheckVerticalDirection(){
        //System.out.println(velocityY);

        if(velocityY > 0){ 					// Hit wall moving down
            //System.out.println("down");
            viewAmara.setTranslateY(viewAmara.getTranslateY() - (velocityY + 1));
            land = true;
            //velocityY = 0;
        }
        else if(velocityY < 0){									// Hit wall moving up

            //System.out.println("moving up");
            viewAmara.setTranslateY(viewAmara.getTranslateY() + 12);
            //velocityY = 1;
        }
        else{

        }

    }
    // CHECKHORIZONTALDIRECTION - if interception occurs in the horizontal direction, then move Amara back
    public int CheckHorizontalDirection(){ // step through all walls and determined what direction she hit the platform
        int direction = 0;

        for(Rectangle wall : walls){

            // move box left  (she'd be running to the left)
            wall.setTranslateX(wall.getTranslateX() - 10);
            intercept = CheckIntercept();
            if(!intercept) {// if not an interception, i.e. the right direction, then move all walls in that direction
                wall.setTranslateX(wall.getTranslateX() + 10); // return the box used to check
                MoveWalls(5);
                return 1;
            }
            wall.setTranslateX(wall.getTranslateX() + 10);

            // move box right (she'd be running to the right)
            wall.setTranslateX(wall.getTranslateX() + 10);
            intercept = CheckIntercept();
            if(!intercept) { // if not an interception, i.e. the right direction, then move all walls in that direction
                wall.setTranslateX(wall.getTranslateX() - 10); // return the box used to check
                MoveWalls(-5);
                return 1;
            }
            wall.setTranslateX(wall.getTranslateX() - 10);
        }
        return direction;
    }

    public boolean CheckIntercept(){ // when method is done, the boolean variable 'intercept' is either true or false
        intercept = false;
        for(Rectangle wall : walls){
            if( viewAmara.getBoundsInParent().intersects(wall.getBoundsInParent())){
                intercept = true;
                index = walls.indexOf(wall); //Store the index of the wall she intercepts
            }
        } // END - check if she lands
        return intercept;
    }

    public void UserInput(){ // handle all the input from the user
        scene1Scene.setOnKeyPressed(new EventHandler<KeyEvent>(){ // action when keys pressed
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.LEFT) {
                    left = true;
                    //if(right) right = false;
                }
                if (event.getCode() == KeyCode.RIGHT){
                    right = true;
                    //if(left) left = false;
                }
                if (event.getCode() == KeyCode.SPACE){
                    if(land){
                        land = false;
                        velocityY = - 11;
                    }
                }
            }});
        scene1Scene.setOnKeyReleased(new EventHandler<KeyEvent>(){ // stop action when keys released
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.LEFT) left = false;
                if (event.getCode() == KeyCode.RIGHT) right = false;
            }});
    }







    // FINAL CUTSCENE (Keys)

    String finalBackName = "file:media/KrispyKreme.png";
    Image finalBackImage = new Image(finalBackName);
    ImageView finalBackView = new ImageView(finalBackImage);

    String finalAndersName = "file:media/Anders.png";
    Image finalAndersImage = new Image(finalAndersName);
    ImageView viewFinalAnders = new ImageView(finalAndersImage);

    public AnimationTimer timerFinal;

    public void FinalCutscene(Stage stage){
        Group cutsceneRoot = new Group(viewAmaraCutscene);
        Scene cutsceneScene = new Scene(cutsceneRoot, 1240, 740);
        cutsceneRoot.getChildren().addAll(finalBackView, viewFinalAnders);
        stage.setScene(cutsceneScene);
        stage.show();

        viewFinalAnders.setTranslateX(640);
        viewFinalAnders.setTranslateY(540);

        counterCutscene = 0;



        timerFinal = new AnimationTimer() {


            @Override
            public void handle(long now) {



                counterCutscene = counterCutscene + 1;
                halfCounterCutscene = (int) (counterCutscene);
                System.out.println("here");
                System.out.println(halfCounterCutscene);
                if(halfCounterCutscene > 0 &&  halfCounterCutscene <= 180){
                    viewFinalAnders.setTranslateY(viewFinalAnders.getTranslateY() + 0.8);
                    System.out.println("here as well");
                }
                else if(halfCounterCutscene > 180 &&  halfCounterCutscene <= 540){
                    viewFinalAnders.setTranslateX(viewFinalAnders.getTranslateX() - 1.65);
                }
                else if(halfCounterCutscene > 540 &&  halfCounterCutscene <= 720){
                    viewFinalAnders.setTranslateY(viewFinalAnders.getTranslateY() - 1.4);
                }
                if(halfCounterCutscene == 721){

                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    for(int i = 0; i<98; i++){
                        try {
                            Thread.sleep(30);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
//                        mediaPlayer2.setVolume(mediaPlayer2.getVolume()-0.01);
                    }
//                    mediaPlayer2.setVolume(0);


                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    timerFinal.stop();
                    System.exit(0);
                }
            }
        };
        timerFinal.start();

    }



























    // VARIABLES


    // MENU
    String menuBackName1 = "file:media/MenuBack1.png";
    Image menuBackImage1 = new Image(menuBackName1);
    ImageView menuBackView1a = new ImageView(menuBackImage1);
    ImageView menuBackView1b = new ImageView(menuBackImage1);
    ImageView menuBackView1c = new ImageView(menuBackImage1);


    String menuBackName2 = "file:media/MenuBack2.png";
    Image menuBackImage2 = new Image(menuBackName2);
    ImageView menuBackView2 = new ImageView(menuBackImage2);

    String menuBackName3 = "file:media/MenuBack3.png";
    Image menuBackImage3 = new Image(menuBackName3);
    ImageView menuBackView3 = new ImageView(menuBackImage3);

    String imageAmaraNameMenu = "file:media/RightLight.png";
    Image imageAmaraMenu = new Image(imageAmaraNameMenu);
    ImageView viewAmaraMenu = new ImageView(imageAmaraMenu);


    //CUTSCENE






    // SCENE1

    // Background
    String scene1BackName = "file:media/Scene1Rectangles copy.png";
    Image scene1BackImage = new Image(scene1BackName);
    ImageView scene1BackView = new ImageView(scene1BackImage);










    // Create Rectangles
    static List<Rectangle> walls = new ArrayList<>();
    public void SetWalls(){

        //String rectanglesName = "Krita3.png";
        String rectanglesName = "file:media/Scene1Rectangles.png";
        Image rectanglesImg = new Image(rectanglesName);
        PixelReader imReader = rectanglesImg.getPixelReader();

        double width = rectanglesImg.getWidth();
        double height = rectanglesImg.getHeight();

        for(int i = 1; i < width; i++){
            for(int j = 1; j < height; j++){
                int k;
                int l;

                Color color = imReader.getColor(i, j); // store the color of the pixel
                Color colorLeft = imReader.getColor(i-1, j); // store the color of the pixel to the left
                Color colorTop = imReader.getColor(i, j-1); // store the color of the pixel on top

                if(isBlack(color) && isWhite(colorLeft) && isWhite(colorTop)){ // identify to right corner of rectangle

                    for(k = 0; isBlack(color); k++){ // start a new loop that will check the width of the rectangle
                        color = imReader.getColor(i+k, j);
                        //System.out.println(k);
                    }
                    color = imReader.getColor(i, j); // Reset color
                    for(l = 0; isBlack(color); l++){ // start a new loop that will check the height of the rectangle
                        color = imReader.getColor(i, j+l);
                        //System.out.println(l);
                    }

                    // create rectangle and add to list
                    Rectangle rectangle = new Rectangle(i, j, k-1, l-1);
                    rectangle.setFill(Color.INDIANRED);
                    walls.add(rectangle);

                    //System.out.println("Top corner " + i + " " + j + " " + (k-1) + " " + (l-1));
                }

                //System.out.println(isBlack(color, i, j));
            }
        }

    }



    public boolean isBlack(Color color){ //returns true if color is close to black

        boolean blackCheck = false;
        double blue = color.getBlue();
        double red = color.getRed();
        double green = color.getGreen();

        if(red < 0.35 && blue < 0.35 && green < 0.35){// check if color is close to black
            blackCheck = true;
        }

        //System.out.print(i + " " + j + " " + red + " " + blue + " " + green + " " );

        return blackCheck;
    }

    public boolean isWhite(Color color){ //returns true if color is close to black

        boolean whiteCheck = false;
        double blue = color.getBlue();
        double red = color.getRed();
        double green = color.getGreen();

        if(red > 0.75 && blue > 0.75 && green > 0.75){// check if color is close to black
            whiteCheck = true;
        }

        //System.out.print(i + " " + j + " " + red + " " + blue + " " + green + " " );

        return whiteCheck;
    }


    public static void main(String[] args) {
        launch();
    }





    // Create Scene1 variables

    // Amara

    String imageAmaraName = "file:media/Amara.png";
    Image imageAmara = new Image(imageAmaraName);
    ImageView viewAmara = new ImageView(imageAmara);

    String imageAmaraNameL1 = "file:media/AmaraL1.png";
    Image imageAmaraL1 = new Image(imageAmaraNameL1);
    String imageAmaraNameR1 = "file:media/Right.png";
    Image imageAmaraR1 = new Image(imageAmaraNameR1);


    static Group scene1Root;
    static Scene scene1Scene;
    static int intersectsCounter = 0;
    int loopCount = 0; // keeps track of the number of iterations of the land-loop
    static int index;
    static boolean intercept = false;


    // will become true when the previous area is done
    Boolean keys = false;
    Boolean city = false;
    Boolean grassland = false;
    Boolean jungle = false;

    // controls
    static boolean right = false;
    static boolean left = false;
    static boolean up = false;
    static double velocityY = 1;
    static boolean land = false;
    static boolean intersects = false;

    // text

    Text JGText;
    Text GCText;

    Rectangle JGTextBox;
    Rectangle GCTextBox;



    // items
    static List<Image> items = new ArrayList<>();

    String imageFionaName = "file:media/Fiona1.png";
    Image imageFiona = new Image(imageFionaName);
    ImageView viewFiona = new ImageView(imageFiona);
    boolean Fiona = true;

    static Rectangle jungleGrassWall = new Rectangle(50, 50);
    String jungleWallName = "file:media/JungleWall.png";
    Image jungleWall = new Image(jungleWallName);
    ImageView viewJungleWall = new ImageView(jungleWall);
    boolean JGWall = true;

    static Rectangle grassPlatform = new Rectangle(200, 50);
    String grassPlatformName = "file:media/Grass Platform.png";
    Image grassPlatformImg = new Image(grassPlatformName);
    ImageView viewGrassPlatform = new ImageView(grassPlatformImg);

    String GrassKeyName = "file:media/Key.png";
    Image GrassKeyImg = new Image(GrassKeyName);
    ImageView viewGrassKey = new ImageView(GrassKeyImg);
    boolean grassKey = true;

    static Rectangle grassWall = new Rectangle(50, 200);
    String grassWallName = "file:media/Grass Wall.png";
    Image grassWallImg = new Image(grassWallName);
    ImageView viewGrassWall = new ImageView(grassWallImg);
    boolean GWall = true;

    String boatName = "file:media/Boat.png";
    Image boatImg = new Image(boatName);
    ImageView viewBoat = new ImageView(boatImg);
    boolean boat = true;

    static Rectangle grassCityWall = new Rectangle(50, 50);
    String grassCityWallName = "file:media/GrassCityWall.png";
    Image grassCityWallImg = new Image(grassCityWallName);
    ImageView viewGrassCityWall = new ImageView(grassCityWallImg);
    boolean GCWall = true;

    static Rectangle cityPlatform = new Rectangle(200, 50);
    String cityPlatformName = "file:media/City Platform.png";
    Image cityPlatformImg = new Image(cityPlatformName);
    ImageView viewCityPlatform = new ImageView(cityPlatformImg);


    static Rectangle finalKey = new Rectangle(50, 50);
    //static Rectangle finalKey1 = new Rectangle(50, 50);
    String finalKeyName = "file:media/JungleWall.png";
    Image finalKeyImg = new Image(finalKeyName);
    ImageView viewFinalKey = new ImageView(finalKeyImg);
    boolean Win = false;


    // scene 1 background

    String imageScene1BackBackName = "file:media/Scene1 back back.png";
    Image imageScene1BackBack = new Image(imageScene1BackBackName);
    ImageView viewScene1BackBack = new ImageView(imageScene1BackBack);



    public void AddItems(){

        // add background
        viewScene1BackBack.setTranslateX(100);
        viewScene1BackBack.setTranslateY(100);



        scene1Root.setTranslateY(-100);
        scene1Root.setTranslateX(-100);
        viewAmara.setTranslateX(610); // set amara's x-location
        viewAmara.setTranslateY(610); // set amara's y-location

        // Fiona
        viewFiona.setTranslateX(5700);
        viewFiona.setTranslateY(220);

        // jungle-grass wall
        jungleGrassWall.setTranslateX(4750);
        jungleGrassWall.setTranslateY(750); // make running through much easier
        viewJungleWall.setTranslateX(4750);
        viewJungleWall.setTranslateY(750);

        // grass Key
        viewGrassKey.setTranslateX(8900);
        viewGrassKey.setTranslateY(95);

        // boat
        viewBoat.setTranslateX(5950);
        viewBoat.setTranslateY(770);

        // grass-platform
        grassPlatform.setTranslateX(8500);
        grassPlatform.setTranslateY(650);
        viewGrassPlatform.setTranslateX(8500);
        viewGrassPlatform.setTranslateY(650);

        // grass wall
        grassWall.setTranslateX(5850);
        grassWall.setTranslateY(600);
        viewGrassWall.setTranslateX(5850);
        viewGrassWall.setTranslateY(600);

        // grass-city wall
        grassCityWall.setTranslateX(8800);
        grassCityWall.setTranslateY(750);
        viewGrassCityWall.setTranslateX(8800);
        viewGrassCityWall.setTranslateY(750);

        // grass-platform
        cityPlatform.setTranslateX(13600);
        cityPlatform.setTranslateY(750);
        viewCityPlatform.setTranslateX(13600);
        viewCityPlatform.setTranslateY(750);

        // final key
        finalKey.setTranslateX(14300);
        finalKey.setTranslateY(300);
        viewFinalKey.setTranslateX(14300);
        viewFinalKey.setTranslateY(300);


        // add text

        JGText = new Text(4670, 670, " Hey! I've lost my baby hippo...\n Can you help me?\n Her name Is Fiona and she\n weighs over a 100lbs.");
        JGText.setScaleX(1.2);
        JGText.setScaleY(1.2);

        JGTextBox = new Rectangle(4645, 645, 240, 90);
        JGTextBox.setFill(Color.PINK);
        JGTextBox.setArcWidth(50.0);
        JGTextBox.setArcHeight(50.0);


        GCText = new Text(8785, 670, " I Need to make my way\n across Lake Alice,\n I heard they found Fiona ");
        GCText.setScaleX(1.05);
        GCText.setScaleY(1.05);

        GCTextBox = new Rectangle(8770, 640, 200, 80);
        GCTextBox.setFill(Color.PINK);
        GCTextBox.setArcWidth(50.0);
        GCTextBox.setArcHeight(50.0);




        // add walls in the game to the list
        walls.add(jungleGrassWall);
        walls.add(grassWall);
        walls.add(grassCityWall);
        walls.add(grassPlatform);
        walls.add(cityPlatform);



    }

}