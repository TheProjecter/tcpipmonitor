/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tcpipmon;

/**
 *
 * @author Slawek
 */
public class MonitorModel {
    private long id;
    private long listenerPort;
    private long remotePort;
    private String remoteHost;
    private boolean autoStart;
    private String name;

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the listenerPort
     */
    public long getListenerPort() {
        return listenerPort;
    }

    /**
     * @param listenerPort the listenerPort to set
     */
    public void setListenerPort(long listenerPort) {
        this.listenerPort = listenerPort;
    }

    /**
     * @return the remotePort
     */
    public long getRemotePort() {
        return remotePort;
    }

    /**
     * @param remotePort the remotePort to set
     */
    public void setRemotePort(long remotePort) {
        this.remotePort = remotePort;
    }

    /**
     * @return the remoteHost
     */
    public String getRemoteHost() {
        return remoteHost;
    }

    /**
     * @param remoteHost the remoteHost to set
     */
    public void setRemoteHost(String remoteHost) {
        this.remoteHost = remoteHost;
    }

    /**
     * @return the autoStart
     */
    public boolean isAutoStart() {
        return autoStart;
    }

    /**
     * @param autoStart the autoStart to set
     */
    public void setAutoStart(boolean autoStart) {
        this.autoStart = autoStart;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
