# Extractor

[![Build Status](https://travis-ci.org/letcheng/extractor.svg?branch=master)](https://travis-ci.org/letcheng/extractor)
[![Release](https://jitpack.io/v/letcheng/Extractor.svg)](https://jitpack.io/#letcheng/Extractor)

数据抽取组件，用来从HTML、JSON、XML等原始数据中，按照自定义的规则抽取想要的数据

### 特色

* 支持 HTML、XML、JSON 和其他文本数据的抽取
* HTML数据抽取，支持纯文本节点的抽取(TEXT)，HTML子节点的抽取(INNER HTML)，子节点文本化抽取(PURE_TEXT)。
* 支持链式抽取，如从JSON数据抽取出来的数据是一段HTML代码，可以继续使用HTML抽取器进行抽取
* 支持正则表达式抽取
* 支持数据的替换操作

### 使用

```
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

```
<dependency>
    <groupId>com.github.letcheng</groupId>
    <artifactId>Extractor</artifactId>
    <version>x.x</version>
</dependency>
```


1.Html 数据抽取

```java
Html html = Html.create("<div class=\"content\">\n" +
                    "<h3>The integration of human relation resources in all directions</h3>\n" +
                    "<p>RuyuApp...~</p>\n" +
                    "</div>");
PlainText plainText = html.selectForPlainText(new CssSelector("div.content>h3", ValueType.TEXT));
System.out.println(plainText.get());
```

2.JSON数据抽取

```java
Json json = Json.create("{ \"store\": {\n" +
       "    \"book\": [ \n" +
       "      { \"category\": \"reference\",\n" +
       "        \"author\": \"Nigel Rees\",\n" +
       "        \"title\": \"Sayings of the Century\",\n" +
       "        \"price\": 8.95\n" +
       "      },...
       "      }]}}");
PlainText plainText = json.jsonpathforList("store.book[*].author");
System.out.println(plainText.get());
```
