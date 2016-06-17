# extractor

[![Build Status](https://travis-ci.org/letcheng/extractor.svg?branch=master)](https://travis-ci.org/letcheng/extractor)
[![Release](https://jitpack.io/v/letcheng/extractor.svg)](https://jitpack.io/#letcheng/extractor)

A data extraction and cleaning of the components, mainly used to extract ordinary text, HTML, JSON and other data, can be used in combination with web crawler program

![image](https://github.com/letcheng/extractor/raw/master/src/main/resources/extractor.JPG)


## Usage

- CSS extrator
```Html html = Html.create("<div class=\"content\">\n" +
    "                <h3>全方位的人际资源整合</h3>\n" +
    "                <p>如鱼正在帮助您把您的朋友，同事，校友，老乡，同行以及朋友的朋友等资源整合起来，在需要的时候您可以随时找到他们~</p>\n" +
    "            </div>");
PlainText plainText = html.selectForPlainText(new CssSelector("div.content>h3", ValueType.TEXT));

System.out.println(plainText.get());
```

- JSON extrator

```Json json = Json.create("{ \"store\": {\n" +
       "    \"book\": [ \n" +
       "      { \"category\": \"reference\",\n" +
       "        \"author\": \"Nigel Rees\",\n" +
       "        \"title\": \"Sayings of the Century\",\n" +
       "        \"price\": 8.95\n" +
       "      },\n" +
       "      { \"category\": \"fiction\",\n" +
       "        \"author\": \"Evelyn Waugh\",\n" +
       "        \"title\": \"Sword of Honour\",\n" +
       "        \"price\": 12.99\n" +
       "      }]}}");
PlainText plainText = json.jsonpathforList("store.book[*].author");
System.out.println(plainText.get());
```