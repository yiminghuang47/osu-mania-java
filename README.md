# <span style='color:#ff8ee6;'>O</span>p<span style='color:#ff8ee6;'>S</span>e<span style='color:#ff8ee6;'>U</span>do<span style='color:#ff8ee6;'>!</span> <span style='color:#ff8ee6;'>mania</span>

This is a ripoff version of the popular (?) rhythm game  <span style='color:#ff8ee6;'>osu! mania</span> coded in Java. It serves as our AP CS Final Project.

It's open-source and you can add your own songs. The [tutorial](#add-your-own-songs) can be found later in thie document. 

There are four difficulties for each song, and the beatmaps are all generated beatmaps randomly according to the difficulty.

## Game Setup

Clone the repository by running the following command

    git clone https://github.com/yiminghuang47/osu-mania-java.git

Then run the following commands to start the game.

    cd osu-mania-java
    java Viewer


## Game Tutorial

The game is straight forward. There are four lanes of tiles. When the tile hits the white line close to the bottom of the screen, you hit the corresponding key. The keys are "D", "F", "J", "K" from left to right. As shown in the image, when the blue tile hits the line, you press "K" on the keyboard.

![alt text](image-1.png)

## Scoring

The scoring is based on how accurately you hit each tiles. There are 4 judgements: <span style="color:yellow">PERFECT</span>, <span style="color:green">GOOD</span>, <span style="color:blue">BAD</span>, and <span style="color:red">MISS</span>, which corresponds to <span style="color:yellow">300</span>, <span style="color:green">200</span>, <span style="color:blue">100</span>, and <span style="color:red">0</span> points. The accuracy is equal to **total score / (number of notes * 300)**.

<h2 id="add-your-own-songs"> Add Your Own Songs </h2>

Here are the steps to add your own songs.

1. Upload the audio to the `songs` folder.