package com.yantao2hao.regimen.Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.yantao2hao.regimen.model.Article;
import com.yantao2hao.regimen.model.ArticleClass;
import com.yantao2hao.regimen.model.ArticleList;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * author：yanyantao
 * Created on 2015/11/23.
 * 描述：
 */
public class ApiUtils {

    public static JSONArray getClasses(){
        String url = "http://www.tngou.net/api/lore/classify";
        JSONArray classList = (JSONArray) sendRequest(url ,ArticleClass.class);
        return classList ;
    }
    public static ArticleList getNewest(){
        String url = "http://www.tngou.net/api/lore/news";
        ArticleList articleList = (ArticleList) sendRequest(url, ArticleList.class);
        return articleList ;
    }
    public static Article getDetail(long id){
        String url = "http://www.tngou.net/api/lore/show?id="+id;
        return (Article)sendRequest(url, Article.class) ;
    }
    public static ArticleList getAll(int page){
        String url = "http://www.tngou.net/api/lore/list?page="+page;
        ArticleList articleList = (ArticleList) sendRequest(url, ArticleList.class);
        return articleList;
    }
    public static ArticleList search(String keyword , int page){
        String url = "http://www.tngou.net/api/search?keyword="+keyword+"&page="+page+"&name=lore";
        ArticleList articleList = (ArticleList) sendRequest(url, ArticleList.class);
        return articleList ;
    }

    public static Serializable sendRequest(String url , Class<? extends Serializable> cl)  {

        HttpURLConnection conn = null;
        try {
            URL mURL = new URL(url);    // 创建一个url对象

            // 得到http的连接对象
            conn = (HttpURLConnection) mURL.openConnection();
            conn.setRequestMethod("GET");        // 设置请求方法为requestMethod
            conn.setConnectTimeout(10000);        // 设置连接服务器的超时时间, 如果超过10秒钟, 没有连接成功, 会抛异常
            conn.setReadTimeout(5000);        // 设置读取数据时超时时间, 如果超过5秒, 抛异常
            conn.connect();        // 开始链接
            int responseCode = conn.getResponseCode(); // 得到服务器的响应码
            if (responseCode == 200) {
                //String result = ConvertStream2String(conn.getInputStream());
                if (cl == ArticleClass.class)
                    return ConvertStream2Array(conn.getInputStream());
                return ConvertStream2Json(conn.getInputStream(),cl);
            } else {
                //throw new HttpTaskException(HttpTaskException.DefaultExceptionInfo.SERVER_RESPONSE_ERROR);
                return null;
            }
        } catch (MalformedURLException e) {
            return null;
            //throw new HttpTaskException(HttpTaskException.DefaultExceptionInfo.URL_ERROR);
        } catch (ProtocolException e) {
            //throw new HttpTaskException(HttpTaskException.DefaultExceptionInfo.CONNECTION_ERROR);
            return null;
        } catch (UnsupportedEncodingException e) {
            //throw new HttpTaskException(HttpTaskException.DefaultExceptionInfo.ENCODE_ERROR);
            return null;
        } catch (IOException e) {
            //throw new HttpTaskException(HttpTaskException.DefaultExceptionInfo.IOERROR);
            return null;
        } catch (Throwable e) {
            // throw new HttpTaskException(HttpTaskException.DefaultExceptionInfo.UNKNOWN);
            return null;
        } finally {
            if (conn != null) {
                conn.disconnect();        // 断开连接
            }
        }
    }
    public static Serializable ConvertStream2Array(InputStream inputStream) throws IOException {
        String jsonStr = ConvertStream2String(inputStream);
        return parseClasses(jsonStr);
    }
    public static Serializable ConvertStream2Json(InputStream inputStream, Class<? extends Serializable> cl) throws IOException {
        String jsonStr = ConvertStream2String(inputStream);
        return parseJson(jsonStr, cl);
    }

    private static String  ConvertStream2String(InputStream inputStream) throws IOException{
        String jsonStr = "";
        // ByteArrayOutputStream相当于内存输出流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        // 将输入流转移到内存输出流中

        while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
            out.write(buffer, 0, len);
        }
        // 将内存流转换为字符串
        jsonStr = new String(out.toByteArray());

        //jsonStr = convert(jsonStr);
        //byte[] bs = jsonStr.getBytes();
        //用新的字符编码生成字符串

        //jsonStr = URLDecoder.decode(jsonStr, "UTF-8");
        return jsonStr;
    }

    /**
     * 将json字符串解析成对象
     *
     * @param jsonStr
     * @return
     */
    public static Serializable parseJson(String jsonStr, Class<? extends Serializable> cl) {
        Serializable json = JSON.parseObject(jsonStr, cl);
        return json;
    }

    public static Serializable parseClasses(String jsonStr) {
        //Serializable json = JSON.parseObject(jsonStr, cl);
        JSONArray list = JSON.parseArray(jsonStr);
        return list;
    }
}
