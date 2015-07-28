# base-diskcache


[DiskLruCache](https://github.com/JakeWharton/DiskLruCache)属于目前最好的Disk Cache库了，但是由于其的存取API，并不是特别好用。

[ASimpleCache](https://github.com/yangfuhai/ASimpleCache) 提供的API属于比较好用的了。

于是萌生想法，对于其公开的API进行扩展，对外除了原有的存取方式以外，提供类似ASimpleCache那样比较简单的API用于存储，而内部的核心实现，依然是DiskLruCache原本的。

## 方法

```java
put(String key, Bitmap bitmap)

put(String key, byte[] value)

put(String key, String value)

put(String key, JSONObject jsonObject)

put(String key, JSONArray jsonArray)

put(String key, Serializable value)

put(String key, Drawable value)

editor(String key).newOutputStream(0);//原有的方式
```

##TODO

* 待测试
* 文档待完善