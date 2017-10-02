# College-Management-Android-App

# Introduction :
<p>This project is compelete college management android app.</p>

# Features : 
- Beautiful LoginScreen.
- Multiple Login System (Student, Teacher and Other)
- In Teacher Section, Teacher will be able to save syllabus update,take attendance and upload notices.
- In Student Section, Student will be able to see updated files and syllabus update.
- In Other Section, Other will be able to upload notices, other doc files. 

# GuideLines :

 <B><h3> Localhost Testing :</h3></B>

<p>
<br>1. Download php mail test mail : <url>http://www.toolheap.com/test-mail-server-tool/ </url>for email verification on localhost.
<br>2. Start Wamp server.
<br>3. In cmd run "ipconfig" command (make sure your pc/laptop is connected to network lan or wifi) and note the ip of lan/wifi network .
<br>4. Run project in android studio, Go to Java/package name/misc/utils/constants/ 
 change link address to ip address (which you got from steps 2) so link will be like this :- http://some ip/demo/register.php...
<br>5. Browse to c/wamp (64 or 32) /www/create demo folder and paste all php files inside demo folder. 
<br>6. open phpmyadmin in browser, there you should create demo database inside that import the demo.sql file 
<br>7. Run test mail server tool. 
<br>8. Finally Check in emulator/phone (for phone, you should config wamp to work on phone ).
<br>9. If above test is success then you could upload files on server.</p>

 <B><h3> Server settings :</h3></B>
 
<br> 1. For Email verify from server :
			   	change this line " $headers = 'From:noreply@Slrtce pocket app' . "\r\n"; " to   " $headers = 'From: your server email name' . "\r\n"; in DB_Functions file.
<br> 2. Change database connection in these listed files :
        - Config.php
        - attendance/Config.php
        - syllabus/Config.php
        
# Notes :
<p>
<br>1. In student section, only fourth year cmpn depart. syllabus is added (for other year data you could add in the database).
<br>2. Teachers could update only fourth year cmpn syllabus data (for other year data you could add in the database).
<br>3. Login System is based on integer flag such that every data goes perfectly in it's database table.
<br>4. Lots of optimization is done so you have to check each files and code too :)
</p>

# ScreenShots :
<a href="https://github.com/RajneeshSingh007/College-Management-Android-App/tree/master/Screenshots">Screenshots folder </a>

# Library :

<p>
 <ul>
 <li>
       <a href="https://github.com/amitshekhariitbhu/Fast-Android-Networking"><b>Fast-Android-Networking</b></a>
 </li>
     <li>
        <a href="https://github.com/vikramkakkar/SublimePicker"><b>SublimePicker</b></a>    
    </li>
 </ul>
 </p>

# Contact :

 <p>mail me : developerrajneeshsingh@gmail.com.</p>

# MIT License :
<pre>Copyright (c) 2017 Rajneesh Singh
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.</pre>

