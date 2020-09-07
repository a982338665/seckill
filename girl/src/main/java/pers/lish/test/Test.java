package pers.lish.test;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.alidns.model.v20150109.AddDomainRecordRequest;
import com.aliyuncs.alidns.model.v20150109.AddDomainRecordResponse;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainsRequest;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.google.gson.Gson;

import java.util.List;

public class Test {


    private static IAcsClient client = null;

    static {
        String regionId = "cn-hangzhou"; //必填固定值，必须为“cn-hanghou”
        String accessKeyId = "LTAI4FtEA83iZ9ZRHYkzywcR"; // your accessKey
        String accessKeySecret = "jcpNUtTN299bEb21lchspGSxCkMx6Q";// your accessSecret
        IClientProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        // 若报Can not find endpoint to access异常，请添加以下此行代码
//         DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Alidns", "alidns.aliyuncs.com");
        client = new DefaultAcsClient(profile);
    }

    public static void main(String[] args) {
        AddDomainRecordRequest request = new AddDomainRecordRequest();
        request.setRegionId("cn-hangzhou");
        request.setDomainName("pingpingpang.cn");
        request.setRR("dedd");
        request.setType("A");
        request.setValue("120.79.81.98");
        request.setLang("zn");
        request.setTTL(600L);
        request.setPriority(4L);

        try {
            AddDomainRecordResponse response = client.getAcsResponse(request);
            System.out.println(new Gson().toJson(response));
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }
    }

    private static void defaultExample() {
        DescribeDomainsRequest request = new DescribeDomainsRequest();
        DescribeDomainsResponse response;
//        request.setProtocol(ProtocolType.HTTPS); //指定访问协议
//        request.setAcceptFormat(FormatType.JSON); //指定api返回格式
//        request.setMethod(MethodType.POST); //指定请求方法
//        request.setRegionId("cn-hangzhou");//指定要访问的Region,仅对当前请求生效，不改变client的默认设置。
        try {
            response = client.getAcsResponse(request);
            List<DescribeDomainsResponse.Domain> list = response.getDomains();
            for (DescribeDomainsResponse.Domain domain : list) {
                System.out.println(domain.getDomainName());
//                System.out.println(new Gson().toJson(domain));
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }


}
