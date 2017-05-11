# College-Management--Android-App
<h3>Introduction</h3>
This project is designed and created for my college (SLRTCE College Of Engineering) friend by me. It is compelete college management android app.
It helps Teacher to update syllabus,upload notices, take attendance easily from teacher section. Also, updated syllabus data and uploaded files could view by student from student section.Other section is for upload notices,other documents. 

# Features
- Beautiful LoginScreen.
- Multiple Login System (Student, Teacher and Other)
- In teacher Section, Teacher can save syllabus update,take attendance and upload notices, other files.
- In Student Section, Student will be able to see updated files and syllabus update.
- In Other Section, Other can upload notices, other doc files. 

# GuideLines :

<p>
# Localhost Testing.

1. Download php mail test mail : <url>http://www.toolheap.com/test-mail-server-tool/ </url>for email verification on localhost.
1. Start Wamp server.
2. In cmd run "ipconfig" command (make sure your pc/laptop is connected to network lan or wifi) and note the ip of lan/wifi network .
3. In android studio, Go to Java/package name/misc/utils/constants/ 
 change link address to ip address (which you got from steps 2) so link will be like this :- http://some ip/demo/register.php...
4. Browse to c/wamp (64 or 32) /www/create demo folder and paste all php files inside demo folder. 
5. open phpmyadmin in browser, there you should create demo database inside that import the demo.sql file 
6. Run test mail server tool. 
7. Finally Check in emulator/phone (for phone, you should config wamp to work on phone ).
8. If above test is success then you could upload files on serer.</p>

# Notes :
<p>
1. Login System is based on integer flag such that every data goes perfectly in it's database table.
2. Lots of optimisation is done so you have to check each files, still doubt mail me on mailto:developerrajneeshsingh@gmail.com.
</p>

# ScreenShots
----uploading soon----

# Library

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
 
 <p> If you used this project,Let me know,To add project in the list</p>
 
 <p> feel free to mail me mailto:developerrajneeshsingh@gmail.com.</p>

# MIT License
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

