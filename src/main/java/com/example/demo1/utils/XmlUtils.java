package com.example.demo1.utils;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: XML解析工具类
 * @Author: LiuZW
 * @CreateDate: 2019/12/4/004 11:35
 * @Version: 1.0
 */
public class XmlUtils {

    private static final String PREFIX_XML = "<xml>";

    private static final String SUFFIX_XML = "</xml>";

    private static final String PREFIX_CDATA = "<![CDATA[";

    private static final String SUFFIX_CDATA = "]]>";

    /**
     * 转化成xml, 单层无嵌套
     *
     * @param parm
     * @param isAddCDATA
     * @return
     */
    public static String xmlFormat(Map<String, String> parm, boolean isAddCDATA) {

        StringBuffer strbuff = new StringBuffer(PREFIX_XML);
        if (parm != null && !parm.isEmpty()) {
            for (Map.Entry<String, String> entry : parm.entrySet()) {
                strbuff.append("<").append(entry.getKey()).append(">");
                if (isAddCDATA) {
                    strbuff.append(PREFIX_CDATA);
                    if (StringUtils.isNotNull(entry.getValue())) {
                        strbuff.append(entry.getValue());
                    }
                    strbuff.append(SUFFIX_CDATA);
                } else {
                    if (StringUtils.isNotNull(entry.getValue())) {
                        strbuff.append(entry.getValue());
                    }
                }
                strbuff.append("</").append(entry.getKey()).append(">");
            }
        }
        return strbuff.append(SUFFIX_XML).toString();
    }

    /**
     * 解析xml
     *
     * @param xml
     * @return
     * @throws XmlPullParserException
     * @throws IOException
     */
    public static Map<String, String> xmlParse(String xml) throws XmlPullParserException, IOException {
        Map<String, String> map = null;
        if (StringUtils.isNotNull(xml)) {
            InputStream inputStream = new ByteArrayInputStream(xml.getBytes());
            XmlPullParser pullParser = XmlPullParserFactory.newInstance().newPullParser();
            pullParser.setInput(inputStream, "UTF-8"); // 为xml设置要解析的xml数据
            int eventType = pullParser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        map = new HashMap<String, String>();
                        break;
                    case XmlPullParser.START_TAG:
                        String key = pullParser.getName();
                        if (key.equals("xml"))
                            break;
                        String value = pullParser.nextText().trim();
                        map.put(key, value);
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                }
                eventType = pullParser.next();
            }
        }
        return map;
    }
}
