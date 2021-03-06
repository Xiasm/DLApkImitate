### DLApkImitate： 任玉刚dynamic-load-apk模仿，动态加载框架技术剖析

插件化技术如今已经非常成熟了，所以作为一个Android开发者，还是有必要去理解插件化技术思想。dynamic-load-apk是刚哥(任玉刚)2014年前后开发的一款动态加载apk框架，当时是插件化技术刚刚诞生不久，所以任主席的dynamic-load-apk对插件化技术很有指导意义。<br/>
dynamic-load-apk是完全从应用层着手，基于宿主代理来实现加载插件apk，而DLApkImitate是对dynamic-load-apk源码研究后实现的简易插件加载框架，本框架不投入生产开发，只是为了自己理解类似dynamic-load-apk的插件加载思想。为什么我又重新写一个DLApkImitate框架，是因为DL框架源码过于复杂，而我的目的是为了让大家理解插件加载思想，所以DLApkImitate对dynamic-load-apk做了核心的抽离，让大家可以很容易对插件加载技术进行理解。

#### dynamic-load-apk

* [dynamic-load-apk源码访问](https://github.com/singwhatiwanna/dynamic-load-apk) 
* [DL动态加载解析](https://blog.csdn.net/singwhatiwanna/article/details/39937639) 


#### 通过DLApkImitate你能了解到：
* 如何加载未安装apk的Activity；
* 如何加载Service；
* 如何加载BroadcastReceiver等；
* PMS解析；
