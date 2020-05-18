#         java虚拟机



<img src="/Users/yanghaohua/Library/Containers/com.tencent.xinWeChat/Data/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/a1b67e5b860271c6748f272be27b3d11/Message/MessageTemp/9e20f478899dc29eb19741386f9343c8/Image/141588659502_.pic_hd.jpg" alt="141588659502_.pic_hd" style="zoom:50%;" />![151588659530_.pic_hd](/Users/yanghaohua/Library/Containers/com.tencent.xinWeChat/Data/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/a1b67e5b860271c6748f272be27b3d11/Message/MessageTemp/9e20f478899dc29eb19741386f9343c8/Image/151588659530_.pic_hd.jpg)

![image-20200505150946691](/Users/yanghaohua/Library/Application Support/typora-user-images/image-20200505150946691.png)



![image-20200516160533582](/Users/yanghaohua/Library/Application Support/typora-user-images/image-20200516160533582.png)



![image-20200516160845171](/Users/yanghaohua/Library/Application Support/typora-user-images/image-20200516160845171.png)

![image-20200516161911408](/Users/yanghaohua/Library/Application Support/typora-user-images/image-20200516161911408.png)



new 对象分为两步。

1. 申请对象的内存，这个过程中成员变量赋值是赋初始值 ，成员对象是null 

2. 申请完内存后再调用构造方法，此时会给成员变量或成员对象赋默认值



cpu 指令乱序执行



1. 读执行乱序执行

​     cpu 从内存中读取了5条执行执行，第一条执行是去内存读取数据，第二条指令是本地计算，两条指令之前是没有任何关联的，第一条指令执行时间明显慢于第二条，cpu为了提高执行效率，有可能会优先执行第二条指令



2. 写指令乱序 

   ​	合并写：
     Cpu 写数据会同步到l1 缓存，如果l1缓存没有会同步到l2,其次l3 ,当到同步至l2,l3时效率就比较低，cpu会将多个写操作执行完再一起同步至多级缓存， 

    wc缓存。效率极高，只有4个字节，当写入的数据小于4个缓存时。会直接把数据写入wc缓存,wc 缓存填满4个字节后会把数据同步至多级缓存







volatile实现细节



字节码层面：

 Acc_volatile



jvm层面



  volatile内存读写都加内存屏障

![image-20200517153422512](/Users/yanghaohua/Library/Application Support/typora-user-images/image-20200517153422512.png)

![image-20200517162758167](/Users/yanghaohua/Library/Application Support/typora-user-images/image-20200517162758167.png)

![image-20200517171617381](/Users/yanghaohua/Library/Application Support/typora-user-images/image-20200517171617381.png)





![image-20200518212338967](/Users/yanghaohua/Library/Application Support/typora-user-images/image-20200518212338967.png)