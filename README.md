Learned about selenium grid and appium mobile grid by accomplishing below:

-Got selenium tests to run on Mac Chrome/Firefox and PC Chrome/Firefox browsers at the same time. 2 tests on 2 machines at same time.


-Got appium tests to run in parallel at same time on a Nexus Android tablet and a Samsung Android tablet. 2 tests on 2 Android mobile devices at the same time connected to the same Mac hub.




NOTES:


You have to add binary path of Firefox browser or else you get an error saying desiredcapabilities not found firefoxOptions.setBinary("C:\Program Files\Mozilla Firefox\firefox.exe"); macfirefoxOptions.setBinary("/Applications/Firefox.app/Contents/MacOS/firefox-bin");

You have to set your nodes to have the path point to the folder of browser drivers. I store it in one central folder and copy that path as a path variable in environment variables in windows and for mac do the following

-Run sudo nano /etc/paths

-Enter your password

-Go to the bottom of the file and enter the path you wish to add

-My PATH looks like: /Users/name/Documents/WebDriver

-Control-x to quit

-Y to save

-Press enter to confirm

You have to use ThreadLocal for remotewebdriver and when you set the remotewebdriver for each browser you must set it to the hub url and not the node url even if its running on a windows node and your hub is a mac machine. Or else it will skip the first test not being able to start the browser.
