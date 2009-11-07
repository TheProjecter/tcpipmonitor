/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tcpipmon;
import java.awt.event.ActionEvent;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import tcpipmon.resources.TcpIpData;
import tcpipmon.resources.MonitorApp;
/**
 *
 * @author Slawek
 */
public class MonitorItem {
    private JPanel monitorPanel;
    private MonitorManager manager;
    private javax.swing.JPanel jPanelMain;
    private javax.swing.JPanel jPanelTop;
    private javax.swing.JPanel jPanelBottom;
    private javax.swing.JLabel jLabelRemoteHost;
    private javax.swing.JLabel jLabelRemotePort;
    private javax.swing.JLabel jLabelListenPort;
    private javax.swing.JTextField jTextFieldRemoteHost;
    private javax.swing.JTextField jTextFieldRemotePort;
    private javax.swing.JTextField jTextFieldListenPort;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList jListRequests;
    private javax.swing.JButton jButtonStart;

    //vector of test requests
    private Vector<TcpIpData> requestVector;

    MonitorApp monitorApp;

    MonitorItem(MonitorManager manager){
        this.manager = manager;
        this.monitorPanel = createMonitorPanel();
        this.requestVector = new Vector<TcpIpData>();
        this.monitorApp = new MonitorApp();
    }

    private JPanel createMonitorPanel(){
        //create panels
        jPanelMain = new javax.swing.JPanel();
        jPanelTop = new javax.swing.JPanel();
        jPanelBottom = new javax.swing.JPanel();
        jPanelMain.setName("testmainpanel");
        jPanelTop.setName("testtopnamepanel");
      

        //create labels
        jLabelRemoteHost = new javax.swing.JLabel();
        jLabelRemotePort = new javax.swing.JLabel();
        jLabelListenPort = new javax.swing.JLabel();  

        jLabelRemoteHost.setText("Remote host:");
        jLabelRemotePort.setText("port:");
        jLabelListenPort.setText("Listen port:");

        jLabelRemoteHost.setName("jLabelRemoteHost");

        //createtextFields
        jTextFieldRemoteHost = new javax.swing.JTextField();
        jTextFieldRemotePort = new javax.swing.JTextField();
        jTextFieldListenPort = new javax.swing.JTextField();
        //creating list and scroll pane for it
        jScrollPane1 = new javax.swing.JScrollPane();
        jListRequests = new javax.swing.JList();

        jListRequests.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                jListRequestsItemSelected(e);
            }
        });


        //create buttons
        jButtonStart = new javax.swing.JButton();
        jButtonStart.setText("Start");

        jButtonStart.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e) {
                jButtonStartActionPerformed(e);
            }
        });
        //top panel layout
        
        javax.swing.GroupLayout jPanelTopLayout = new javax.swing.GroupLayout(jPanelTop);
        jPanelTop.setLayout(jPanelTopLayout);
        jPanelTopLayout.setHorizontalGroup(
            jPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTopLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelTopLayout.createSequentialGroup()
                        .addComponent(jLabelRemoteHost)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldRemoteHost, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jLabelRemotePort)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldRemotePort, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelTopLayout.createSequentialGroup()
                        .addComponent(jButtonStart)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelListenPort)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldListenPort, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelTopLayout.setVerticalGroup(
            jPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTopLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelRemoteHost)
                    .addComponent(jTextFieldRemoteHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelRemotePort)
                    .addComponent(jTextFieldRemotePort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonStart)
                    .addComponent(jTextFieldListenPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelListenPort))
                .addGap(6, 6, 6))
        );

        //bottom panel layout
        jListRequests.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jScrollPane1.setViewportView(jListRequests);

        javax.swing.GroupLayout jPanelBottomLayout = new javax.swing.GroupLayout(jPanelBottom);
        jPanelBottom.setLayout(jPanelBottomLayout);
        jPanelBottomLayout.setHorizontalGroup(
            jPanelBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
        );
        jPanelBottomLayout.setVerticalGroup(
            jPanelBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBottomLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE))
        );


        //main panel
        javax.swing.GroupLayout jPanelMainLayout = new javax.swing.GroupLayout(jPanelMain);
        jPanelMain.setLayout(jPanelMainLayout);
        jPanelMainLayout.setHorizontalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelBottom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelMainLayout.setVerticalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addComponent(jPanelTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelBottom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

       
        return jPanelMain;
    }

    private void jButtonStartActionPerformed(ActionEvent event){
        if(jButtonStart.getText().equals("Start")){
            
            //TODO: add some validation
            int listenPort = Integer.parseInt(jTextFieldListenPort.getText());
            int remotePort = Integer.parseInt(jTextFieldRemotePort.getText());
            String remoteHost = jTextFieldRemoteHost.getText();

            if(monitorApp.startMonitoring(this, listenPort, remoteHost, remotePort)){
                jButtonStart.setText("Stop");
                setSettingFieldsEnabled(false);
            } else {
                monitorApp.stopMonitoring();
            }
        } else {
            jButtonStart.setText("Start");
            setSettingFieldsEnabled(true);          
            
        }

    }

    private void jListRequestsItemSelected(ListSelectionEvent event){

        int selectedIndex = jListRequests.getSelectedIndex();

        if(selectedIndex == -1) {
            return; //no selection
        } else if(selectedIndex > requestVector.size() - 1){
            System.out.println("MonitorItem.jListRequestsItemSelected Error: out of vector size selection (" + selectedIndex+ ")");

        } else {
            TcpIpData selectedItem = requestVector.get(selectedIndex);

            this.manager.getMonitorUi().notifyRequestHeaderListener(selectedItem);
            this.manager.getMonitorUi().notifyRequestListener(selectedItem);
            this.manager.getMonitorUi().notifyResponseHeaderListener(selectedItem);
            this.manager.getMonitorUi().notifyResponseListener(selectedItem);
        }
    }
    
    private void setSettingFieldsEnabled(boolean enabled){
        jTextFieldListenPort.setEnabled(enabled);
        jTextFieldRemoteHost.setEnabled(enabled);
        jTextFieldRemotePort.setEnabled(enabled);
    }

    public void onRequestSend(TcpIpData data){
        requestVector.add(data);
        jListRequests.setListData(requestVector);
    }

    public void onResponseReceived(){
        //refresh list
        jListRequests.setListData(requestVector);
    }


    /**
     * @return the monitorPanel
     */
    public JPanel getMonitorPanel() {
        return monitorPanel;
    }

}
