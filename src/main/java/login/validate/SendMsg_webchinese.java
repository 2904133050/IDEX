package login.validate;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.NameValuePair;

public class SendMsg_webchinese {
    public static String validate(String phone)throws Exception{
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://gbk.api.smschinese.cn");
        post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");//在头文件中设置转码
        //生成随机数
        long randoms = Math.round(Math.random()*10000);
        NameValuePair[] data ={ new NameValuePair("Uid", "yy17673042751"),//账号
                new NameValuePair("Key", "d41d8cd98f00b204e980"),//key
                new NameValuePair("smsMob",""+phone+""),//手机号
                new NameValuePair("smsText","验证码："+randoms+"【易买网】")};
        post.setRequestBody(data);
        client.executeMethod(post);
        Header[] headers = post.getResponseHeaders();
        int statusCode = post.getStatusCode();
        System.out.println("statusCode:"+statusCode);
        for(Header h : headers)
        {
            System.out.println(h.toString());
        }
        String result = new String(post.getResponseBodyAsString().getBytes("gbk"));
        System.out.println(result); //打印返回消息状态
        post.releaseConnection();
        return String.valueOf(randoms);
    }

}