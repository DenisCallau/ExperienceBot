# Experience Bot

This is an application to track the experience gained in a game and calculate the time untill you level up.
The intention behind developing this application is to provide a way to track the experience gain, once in some games it can be very laborious task, so efficieny is always aimed.
Also, as this application don't access memory address, so it can't be considered hacking or violating game rules. Just in case, <strong>USE THIS SOFTWARE AT YOUR OWN RISK.</strong>
I do not take any responsibilities for penalties in your game account.

<h3>The first version is released:</h3>
https://github.com/DenisCallau/ExperienceBot/releases/download/v0.1/ExperienceBot.jar
<br><br>
For now, you <strong>must</strong> install Google Tesseract and it <strong>must</strong> be in "C:/Program Files/" folder with default folder name. Note that can't be "Program Files (x86)".
<br><br>

It works with image recognition and processing, periodically taking screenshots from the region containing the experience quantity and calculating how much experience is needed to next level.

It uses [Tess4J](http://tess4j.sourceforge.net/), which is a JNA Wrapper for [Google Tesseract](https://en.wikipedia.org/wiki/Tesseract_(software)), therefore [JNA](https://github.com/java-native-access/jna) is also a dependency.

After taking a screenshot, the picture is binarized in order to ease the OCR procedure, which recognize the numbers in the image.

Unprocessed screenshot:
![image](https://user-images.githubusercontent.com/13944617/123715097-fcc85180-d84d-11eb-8cb3-4e7db316ab01.png)

After binarization:
![image](https://user-images.githubusercontent.com/13944617/123715074-f508ad00-d84d-11eb-8e41-cf4dd1d5a638.png)

OCR with Tess4J returns a string of the text data, in this case numbers (a filter is used to indicate that only numbers will be in the screenshot, so the output will be optimized for this purpose). Then the rest is math manipulation.
Using Swing library, a small box (always on top) is displayed on screen with the relevant calculated data:
<br>![image](https://user-images.githubusercontent.com/13944617/123714940-b246d500-d84d-11eb-9acc-3d8c8d3a03b3.png)


Some games presents your character experience in a Current/Total integer number form, and others only with the percentage of your current experience.

At first, the application was designed to work with a online game called Maple Story, which present the current/total experience as integers.
With some refactors I extended the funcionallity to another game called Black Desert (where I got the example pictures above), which presents the current percentage of experience in the current level as floating-point number.
A lot of refactor still need to be done to simplify the process of extending the functionallity to other games.

<h3>Full screenshot with the application window on left side</h3>

![image](https://user-images.githubusercontent.com/13944617/123716099-297d6880-d850-11eb-9ec3-511250bd1226.png)
