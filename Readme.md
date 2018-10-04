**HOW TO USE**

1. **What does it do?**  
oTreeBrowser is a very small java based application. It delivers
a webview and because it is a desktop application, the user is allowed 
to open a firefox instance during the otree session.  
Additionally a wrote a few lines of code to test my javascript code
in otree.
2. **What does it not do?**  
Unfortunally there is a bug in the JavaFX-Webengine. 
Therefore for now it is not possible to start the application
vio CLI or batch with arguments like your otree session link.
I started with implementing routines which can handle and
process arguments in the Start and Gui classes. In a better future you can
use it to start the app via batch or CLI.
3. Is it working on all Systems?  
I do not know. i developed and tested it on Windows 10. You can try to 
modify it and run it on unix systems. 
4. **Dependencies**  
You need a Java 10 JRE
5. Okay, got it! How can i run it?
You can start the jar-file local or from a network device.  
Because our clients in our lab have very limited rights to read and write, there 
is not such a things like a config file, which allows you to change 
your path dynamically without new compiling the app.  
For now, you have to change the path to your firefox installation in 
the class `InternetTask`. There are two variables `devFFPath` 
and `labFFPath`. You can use it to switch from your development environment
to your lab environment.  
6. **Configuration**  
6.1. **Otree template files**  
In order to give oTreeBrowser the ability to know that the user is on
a site in your experiment, which allows him to use the internet, you have to set
two javascript variables in your template:
`{% block scripts %}`  
`<script>`  
   ` var timeout_seconds = {{timeout_seconds|json}}`  
    `var internet_window_open = true;`  
`</script>`  
`{% endblock %}`  
In `timeout_seconds` you can tell oTreeBrowser how for what time
the firefox browser has to be open. Because oTreeBrowser will count
in seconds you can use your otree timeout.  
`internet_window_open` will tell oTreeBrowser, it has to prepare to open a 
firefox instance.  
6.2. **Otree session links**  
Once oTreeBrowser has started, you can place your session link in
the startscreens input field and click ok.


