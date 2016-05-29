package com.ruyuapp.selector;

import com.jayway.jsonpath.JsonPath;
import com.ruyuapp.extractor.controller.Html;
import com.ruyuapp.extractor.controller.HtmlNode;
import com.ruyuapp.extractor.controller.Json;
import com.ruyuapp.extractor.controller.PlainText;
import com.ruyuapp.extractor.selector.CssSelector;
import com.ruyuapp.extractor.utils.ValueType;
import org.junit.Test;

/**
 * @author Letcheng on 2016/5/10.
 */
public class SelectorTest {

    @Test
    public void testCssSelect1(){

        Html html = Html.create("<div class=\"content\">\n" +
                "                <h3>全方位的人际资源整合</h3>\n" +
                "                <p>如鱼正在帮助您把您的朋友，同事，校友，老乡，同行以及朋友的朋友等资源整合起来，在需要的时候您可以随时找到他们~</p>\n" +
                "            </div>");
        PlainText plainText = html.selectForPlainText(new CssSelector("div.content>h3", ValueType.TEXT));

        System.out.println(plainText.get());
    }

    @Test
    public void testCssSelectChain(){
        Html html = Html.create("<div class=\"content\">\n" +
                "                <h3>全方位的人际资源整合</h3>\n" +
                "                <p>如鱼正在帮助您把您的朋友，同事，校友，老乡，同行以及朋友的朋友等资源整合起来，在需要的时候您可以随时找到他们~</p>\n" +
                "            </div>");

        HtmlNode htmlNode = html.selectForHtmlNode(new CssSelector("div.content", ValueType.INNER_HTML));
        PlainText plainText = htmlNode.selectForPlainText(new CssSelector("h3", ValueType.TEXT));
        System.out.println(plainText.get());
    }

    @Test
    public void testJsonPath(){
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
        plainText.all().forEach(System.out::println);
    }

}
