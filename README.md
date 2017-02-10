# PainthomeProject

An android application which displays the list of paint companies dealers and paint calculator

After Splashscreen there are 4 buttons for paint paint companies in the new activity. Click on asian paints and you enter the two tabs which represents
two fragments which has used pager adapter. The fragments had media.java as parent activity.
First fragment(map_location.java) has find dealers button. Click on that and you navigate to mapsactivity where you see your current location in
blue marker and remaining in red marker.
Second fragment(CalcHint.java) has a pallete button. Click on that button you go to an activity (Start_paint.java). A color pallette 
pops up, select the color and click ok. As the color picker closes you see hex code at the top. enter wall height width,
door height width and window height and width. click on calculate. The button onclick listener should go to next page and display 
the result as cost of paint and amount of paint.

NOTE : Database has only two hexcode mapped. So color selection on the color picker is tentative. ( for eg: Take the first o picker 
to the top right corner) and the second arrow to bottom left. and you get the hex code as 0xffff0000 which is red and this is
mapped to dstabase.

Database is hosted in paint.bidheegroup.com

The map activity is not working. The app crashes as we click on find dealers.


The calculate button is not working too. 
