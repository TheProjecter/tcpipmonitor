package tcpipmon.resources;

import java.net.URL;
import org.apache.commons.lang.StringUtils;

public class TcpIpData {

	private String requestHeader;
        private String requestBody;
	private String request;
	private String responseHeader;
        private String responseBody;
	private String response;
	private URL toAddress;

        public String toString(){
            String resp = "waiting for response";
            if(StringUtils.isNotBlank(responseHeader)){
                //trying to extract response status
                resp = StringUtils.substringBefore(responseHeader, "\n");
                resp = StringUtils.substringAfter(resp, " ");
            }
            return StringUtils.substringBefore(requestHeader, "HTTP") + " -> " + resp;
        }

	public void setToAddress(URL toAddress){
		this.toAddress = toAddress;
	}
	
	public URL getToAddress(){
		return this.toAddress;
	}
	
	public void setRequestHeader(String requestHeader){
		this.requestHeader = requestHeader;
	}

        public void setRequestBody(String requestBody){
		this.requestBody = requestBody;
	}
	
	public String getRequestHeader(){
		return this.requestHeader;
	}
	
	public void setRequest(String request){
		this.request = request;
	}
	
	public String getRequest(){
		return this.request;
	}
	
	public void setResponseHeader(String responseHeader){
		this.responseHeader = responseHeader;
	}

        public void setResponseBody(String responseBody){
		this.responseBody = responseBody;
	}
	
	public String getResponseHeader(){
		return this.responseHeader;
	}
	
	public void setResponse(String response){
		this.response = response;
	}
	
	public String getResponse(){
		return this.response;
	}
	
	
	
}
