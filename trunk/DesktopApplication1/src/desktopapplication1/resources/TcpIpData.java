package desktopapplication1.resources;

import java.net.URL;

public class TcpIpData {

	private String requestHeader;
	private String request;
	private String responseHeader;
	private String response;
	private URL toAddress;

	public void setToAddress(URL toAddress){
		this.toAddress = toAddress;
	}
	
	public URL getToAddress(){
		return this.toAddress;
	}
	
	public void setRequestHeader(String requestHeader){
		this.requestHeader = requestHeader;
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
