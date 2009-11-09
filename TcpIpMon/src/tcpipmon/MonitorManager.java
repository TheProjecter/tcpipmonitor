/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tcpipmon;
import javax.swing.JTabbedPane;
import java.util.ArrayList;
/**
 *
 * @author Slawek
 */
public class MonitorManager {
    private TcpIpMonView monitorUi;
    private ArrayList<MonitorPanel> monitors;

    public MonitorManager(TcpIpMonView monitorUi){
        this.monitorUi = monitorUi;
        this.monitors = new ArrayList<MonitorPanel>();
    }

    public void addMonitor(){
        MonitorPanel monitor = new MonitorPanel(this);
        monitors.add(monitor);
        getMonitorUi().getMonitorsTabbedPane().addTab("newTab", monitor);

    }

    /**
     * @return the monitorUi
     */
    public TcpIpMonView getMonitorUi() {
        return monitorUi;
    }

  
}
