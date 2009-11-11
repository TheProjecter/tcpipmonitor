/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tcpipmon;
import javax.swing.JTabbedPane;
import java.util.ArrayList;
import java.util.List;
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
        MonitorPanel monitor = new MonitorPanel(this, getNextId());
        monitors.add(monitor);
        getMonitorUi().getMonitorsTabbedPane().addTab("newMonitor", monitor);

    }

    public void addMonitor(MonitorModel model){
        MonitorPanel monitor = new MonitorPanel(this, model);
        monitors.add(monitor);
        getMonitorUi().getMonitorsTabbedPane().addTab(model.getName(), monitor);
    }

    public void saveMonitors(){
        ConfigXMLFile.getConfigXML().saveMonitors(getMonitorModels());
    }

    public void loadStoredMonitors(){
        for(MonitorModel monitor: ConfigXMLFile.getConfigXML().getMonitors()){
            addMonitor(monitor);
        }
    }

    private List<MonitorModel> getMonitorModels(){
        List<MonitorModel> monitorModels = new ArrayList<MonitorModel>();
        for(MonitorPanel panel: monitors){
            monitorModels.add(panel.getModel());
        }
        return monitorModels;
    }
    /** Generate next available ID of the monitor.
     *
     * @return the monitor ID
     */
    private long getNextId(){
        long id = 0;
        for(MonitorPanel monitor: monitors){
            if(monitor.getId() > id){
                id = monitor.getId();
            }
        }
        return ++id;
    }

    /**
     * @return the monitorUi
     */
    public TcpIpMonView getMonitorUi() {
        return monitorUi;
    }

  
}
