/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package desktopapplication1.resources;

/**
 *
 * @author TimP
 */
public interface ListenerInterface {

    void notifyRequestHeaderListener(TcpIpData tcpIpData);
    
    void notifyRequestListener(TcpIpData tcpIpData);

    void notifyResponseHeaderListener(TcpIpData tcpIpData);

    void notifyResponseListener(TcpIpData tcpIpData);
}
