# extractor

[![Build Status](https://travis-ci.org/letcheng/extractor.svg?branch=master)](https://travis-ci.org/letcheng/extractor)
[![Release](https://jitpack.io/v/letcheng/extractor.svg)](https://jitpack.io/#letcheng/extractor)

A data extraction and cleaning of the components, mainly used to extract ordinary text, HTML, JSON and other data, can be used in combination with web crawler program

![image](https://github.com/letcheng/extractor/raw/master/src/main/resources/extractor.png)


## Usage

- Html extractor
```java

Html html = Html.create("<div class=\"content\">\n" +
                    "<h3>The integration of human relation resources in all directions</h3>\n" +
                    "<p>RuyuApp...~</p>\n" +
                    "</div>");
PlainText plainText = html.selectForPlainText(new CssSelector("div.content>h3", ValueType.TEXT));
System.out.println(plainText.get());

```

- JSON extractor

```java

Json json = Json.create("{ \"store\": {\n" +
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