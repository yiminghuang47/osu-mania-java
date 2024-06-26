# <span style='color:#ff8ee6;'>O</span>p<span style='color:#ff8ee6;'>S</span>e<span style='color:#ff8ee6;'>U</span>do<span style='color:#ff8ee6;'>!</span> <span style='color:#ff8ee6;'>mania</span>

*Authors: Yiming Huang, Genji Tsuchihashi, Elliott Tanalski*

This is a ripoff version of the popular (?) rhythm game  <span style='color:#ff8ee6;'>osu! mania</span>. It's coded in Java and it serves as our AP CS Final Project.

It's open-source and you can add your own songs. The [tutorial](#add-your-own-songs) can be found later in this document. 

There are four difficulties for each song, and the beatmaps are all generated randomly according to the difficulty.

## Demo Gameplay

https://youtu.be/oRYTBaxpkBM

![alt text](opseudo_mania_game_demo.gif)

## Game Setup

Clone the repository by running the following command

    git clone https://github.com/yiminghuang47/osu-mania-java.git

Then go to the osu-mania-java folder. 

    cd osu-mania-java


Run the following commands to start the game.
    
    javac *.java
    java Viewer


## How To Play

The game is pretty straightforward. It's similar to games like osu! mania or Piano Tiles. There are four lanes of tiles. When a tile hits the white line close to the bottom of the screen, you hit the corresponding key. The keys are "D", "F", "J", "K" from left to right. For example, as shown in the image, when the blue tile hits the line, you press "K" on the keyboard.

![alt text](image-1.png)

## Scoring

The scoring is based on how accurately you hit each tiles. There are 4 degrees of accuracy: <span style="color:yellow">PERFECT</span>, <span style="color:green">GOOD</span>, <span style="color:blue">BAD</span>, and <span style="color:red">MISS</span>, which corresponds to <span style="color:yellow">300</span>, <span style="color:green">200</span>, <span style="color:blue">100</span>, and <span style="color:red">0</span> points. The accuracy is equal to **total points / (number of notes * 300)**.

<h2 id="add-your-own-songs"> Add Your Own Songs </h2>

Here are the steps to add your own songs.

1. Upload the audio to the `songs` folder. If you want to add a background image, upload that to the `images` folder.
2. Go to `Songs.java` and create a new static `SongInfo` object. The description of the parameters of the object can be found in `SongInfo.java`. If you don't have a background image, put `null` for the `imagePath` parameter
3. Go to `SongSelectionScreen.java` and scroll to the end of the constructor method.There you will see the existing buttons. Follow the pattern to create a button for the new song.
4. Adjust `offset`, `distance`, and `length` parameters (preferrably in this order). This part takes some time because you have to repeatedly play through the song to adjust the parameters.