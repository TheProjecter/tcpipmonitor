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
    private ArrayList<MonitorItem> monitors;

    public MonitorManager(TcpIpMonView monitorUi){
        this.monitorUi = monitorUi;
        this.monitors = new ArrayList<MonitorItem>();
    }

    public void addMonitor(){
        MonitorItem monitor = new MonitorItem(this);
        monitors.add(monitor);
        getMonitorUi().getMonitorsTabbedPane().addTab("newTab", monitor.getMonitorPanel());

    }

    /**
     * @return the monitorUi
     */
    public TcpIpMonView getMonitorUi() {
        return monitorUi;
    }

  
}
