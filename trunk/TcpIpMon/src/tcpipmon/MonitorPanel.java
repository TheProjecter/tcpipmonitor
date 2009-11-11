/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MonitorPanel.java
 *
 * Created on 07-Nov-2009, 14:09:18
 */

package tcpipmon;
import java.awt.Dimension;
import java.util.Vector;
import org.apache.commons.lang.StringUtils;
import tcpipmon.resources.TcpIpData;
import tcpipmon.resources.MonitorApp;

/**
 *
 * @author Slawek
 */
public class MonitorPanel extends javax.swing.JPanel {
    MonitorManager manager;
    Vector<TcpIpData> requestVector;
    MonitorApp monitorApp;
    private long id;

    /** Creates new form MonitorPanel */
    public MonitorPanel(MonitorManager manager, Long id){
        initComponents();
        this.id = id;
        jLabelErrorMessage.setMaximumSize(new Dimension(200, 200));

        this.manager = manager;
        this.requestVector = new Vector<TcpIpData>();
        this.monitorApp = new MonitorApp();
        
    }

    public MonitorPanel(MonitorManager manager, MonitorModel model){
        this(manager, model.getId());
        //TODO: until we do not have valiation, assume that port 0 is empty value
        String listenPort = "";
        String remotePort = "";
        
        if(model.getListenerPort() != 0){
            listenPort = String.valueOf(model.getListenerPort());
        }
        
        if(model.getRemotePort() != 0){
            remotePort = String.valueOf(model.getRemotePort());
        }
        jTextFieldListenPort.setText(listenPort);
        jTextFieldRemoteHost.setText(model.getRemoteHost());
        jTextFieldRemotePort.setText(remotePort);

        if(model.isAutoStart()){
            jButtonStartActionPerformed(null);
        }
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelTop = new javax.swing.JPanel();
        jPanelInfo = new javax.swing.JPanel();
        jLabelMonitorInfo = new javax.swing.JLabel();
        jButtonStart = new javax.swing.JButton();
        jPanelConfig = new javax.swing.JPanel();
        jLabelRemoteHost = new javax.swing.JLabel();
        jLabelRemotePort = new javax.swing.JLabel();
        jLabelListenPort = new javax.swing.JLabel();
        jTextFieldRemoteHost = new javax.swing.JTextField();
        jTextFieldRemotePort = new javax.swing.JTextField();
        jTextFieldListenPort = new javax.swing.JTextField();
        jPanelBottom = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListRequests = new javax.swing.JList();
        jPanelError = new javax.swing.JPanel();
        jButtonCloseError = new javax.swing.JButton();
        jLabelErrorMessage = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(255, 330));
        setPreferredSize(new java.awt.Dimension(255, 365));

        jPanelTop.setName("jPanelTop"); // NOI18N

        jPanelInfo.setFocusable(false);
        jPanelInfo.setName("jPanelInfo"); // NOI18N
        jPanelInfo.setVisible(false);

        jLabelMonitorInfo.setText("Remote host:");
        jLabelMonitorInfo.setName("jLabelMonitorInfo"); // NOI18N

        javax.swing.GroupLayout jPanelInfoLayout = new javax.swing.GroupLayout(jPanelInfo);
        jPanelInfo.setLayout(jPanelInfoLayout);
        jPanelInfoLayout.setHorizontalGroup(
            jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelMonitorInfo)
                .addContainerGap(200, Short.MAX_VALUE))
        );
        jPanelInfoLayout.setVerticalGroup(
            jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelMonitorInfo)
        );

        jButtonStart.setText("Start");
        jButtonStart.setName("jButtonStart"); // NOI18N
        jButtonStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStartActionPerformed(evt);
            }
        });

        jPanelConfig.setName("jPanelConfig"); // NOI18N

        jLabelRemoteHost.setText("Remote host:");
        jLabelRemoteHost.setName("jLabelRemoteHost"); // NOI18N

        jLabelRemotePort.setText("port:");
        jLabelRemotePort.setName("jLabelRemotePort"); // NOI18N

        jLabelListenPort.setText("Listen port:");
        jLabelListenPort.setName("jLabelListenPort"); // NOI18N

        jTextFieldRemoteHost.setName("jTextFieldRemoteHost"); // NOI18N

        jTextFieldRemotePort.setName("jTextFieldRemotePort"); // NOI18N

        jTextFieldListenPort.setName("jTextFieldListenPort"); // NOI18N

        javax.swing.GroupLayout jPanelConfigLayout = new javax.swing.GroupLayout(jPanelConfig);
        jPanelConfig.setLayout(jPanelConfigLayout);
        jPanelConfigLayout.setHorizontalGroup(
            jPanelConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConfigLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelListenPort)
                    .addGroup(jPanelConfigLayout.createSequentialGroup()
                        .addComponent(jLabelRemoteHost)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldRemoteHost, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelRemotePort)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldListenPort)
                    .addComponent(jTextFieldRemotePort, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        jPanelConfigLayout.setVerticalGroup(
            jPanelConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConfigLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelRemoteHost)
                    .addComponent(jLabelRemotePort)
                    .addComponent(jTextFieldRemoteHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldRemotePort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelListenPort)
                    .addComponent(jTextFieldListenPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanelTopLayout = new javax.swing.GroupLayout(jPanelTop);
        jPanelTop.setLayout(jPanelTopLayout);
        jPanelTopLayout.setHorizontalGroup(
            jPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTopLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonStart)
                .addContainerGap(208, Short.MAX_VALUE))
            .addComponent(jPanelConfig, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelTopLayout.setVerticalGroup(
            jPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTopLayout.createSequentialGroup()
                .addComponent(jPanelConfig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonStart))
        );

        jPanelBottom.setName("jPanelBottom"); // NOI18N
        jPanelBottom.setPreferredSize(new java.awt.Dimension(255, 270));

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jListRequests.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListRequests.setName("jListRequests"); // NOI18N
        jListRequests.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListRequestsItemSelected(evt);
            }
        });
        jScrollPane1.setViewportView(jListRequests);

        javax.swing.GroupLayout jPanelBottomLayout = new javax.swing.GroupLayout(jPanelBottom);
        jPanelBottom.setLayout(jPanelBottomLayout);
        jPanelBottomLayout.setHorizontalGroup(
            jPanelBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
        );
        jPanelBottomLayout.setVerticalGroup(
            jPanelBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
        );

        jPanelError.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 102), 1, true));
        jPanelError.setName("jPanelError"); // NOI18N
        jPanelError.setVisible(false);

        jButtonCloseError.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButtonCloseError.setForeground(new java.awt.Color(255, 0, 102));
        jButtonCloseError.setText("x");
        jButtonCloseError.setToolTipText("Close Error Message");
        jButtonCloseError.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButtonCloseError.setContentAreaFilled(false);
        jButtonCloseError.setName("jButtonCloseError"); // NOI18N
        jButtonCloseError.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCloseErrorActionPerformed(evt);
            }
        });

        jLabelErrorMessage.setName("jLabelErrorMessage"); // NOI18N

        javax.swing.GroupLayout jPanelErrorLayout = new javax.swing.GroupLayout(jPanelError);
        jPanelError.setLayout(jPanelErrorLayout);
        jPanelErrorLayout.setHorizontalGroup(
            jPanelErrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelErrorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelErrorMessage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 229, Short.MAX_VALUE)
                .addComponent(jButtonCloseError, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelErrorLayout.setVerticalGroup(
            jPanelErrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelErrorLayout.createSequentialGroup()
                .addGroup(jPanelErrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonCloseError)
                    .addGroup(jPanelErrorLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelErrorMessage)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanelBottom, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                        .addComponent(jPanelError, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelBottom, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStartActionPerformed
        if(jButtonStart.getText().equals("Start")){
            closeAndClearErrorMessage();

            //TODO: add some validation
            int listenPort = Integer.parseInt(jTextFieldListenPort.getText());
            int remotePort = Integer.parseInt(jTextFieldRemotePort.getText());
            String remoteHost = jTextFieldRemoteHost.getText();

            if(monitorApp.startMonitoring(this, listenPort, remoteHost, remotePort)){
                jButtonStart.setText("Stop");
                setSettingFieldsEnabled(false);
                jPanelConfig.setVisible(false);
                showInfoMessage(true);
            } else {
                notifyMonitorError("Could not open port "+jTextFieldListenPort.getText()+". It may be used by another process.", true, true);
            }
        } else {
            stopMonitor(true);
        }
    }//GEN-LAST:event_jButtonStartActionPerformed

    private void jListRequestsItemSelected(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListRequestsItemSelected
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
    }//GEN-LAST:event_jListRequestsItemSelected

    private void jButtonCloseErrorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCloseErrorActionPerformed
        closeAndClearErrorMessage();
    }//GEN-LAST:event_jButtonCloseErrorActionPerformed

    private void showInfoMessage(boolean show){
        if(show){
            jLabelMonitorInfo.setText(jTextFieldRemoteHost.getText()+":"+jTextFieldRemotePort.getText()
                    +" @ port:"+jTextFieldListenPort.getText());
        }
        jPanelInfo.setVisible(show);
    }

    private void stopMonitor(boolean now){
        //make sure that config controlls are visible and enabled
        setSettingFieldsEnabled(true);
        jPanelConfig.setVisible(true);

        jButtonStart.setText("Start");
        showInfoMessage(false);
        jPanelConfig.setVisible(true);
        if(now){
            monitorApp.stopMonitoringNow();
        } else {
            monitorApp.stopMonitoring();
        }
    }

    private void closeAndClearErrorMessage(){
        jLabelErrorMessage.setText("");
        jPanelError.setVisible(false);
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

    public void notifyMonitorError(String errorMsg, boolean stopListening, boolean now){        
       
        String currMsg = jLabelErrorMessage.getText();
        if(StringUtils.isBlank(currMsg)){
            jLabelErrorMessage.setText(errorMsg);
        } else {
            jLabelErrorMessage.setText(currMsg + "\n" + errorMsg);
        }

        jPanelError.setVisible(true);
        
        if(stopListening){
            monitorApp.stopMonitoring();
            stopMonitor(now);
        }
    }

    public MonitorModel getModel(){
        MonitorModel model = new MonitorModel();
        model.setAutoStart(false);
        model.setName("monitor" + Long.toString(getId()));
        model.setId(getId());
        //until we do not have validation init ports with zero
        long listenerPort = 0;
        long remotePort = 0;
        if(StringUtils.isNotBlank(jTextFieldListenPort.getText())){
            listenerPort = Long.parseLong(jTextFieldListenPort.getText());
        }
        if(StringUtils.isNotBlank(jTextFieldRemotePort.getText())){
            remotePort = Long.parseLong(jTextFieldRemotePort.getText());
        }
        model.setListenerPort(listenerPort);
        model.setRemotePort(remotePort);
        model.setRemoteHost(jTextFieldRemoteHost.getText());

        model.setListenerPort(id);
        return model;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCloseError;
    private javax.swing.JButton jButtonStart;
    private javax.swing.JLabel jLabelErrorMessage;
    private javax.swing.JLabel jLabelListenPort;
    private javax.swing.JLabel jLabelMonitorInfo;
    private javax.swing.JLabel jLabelRemoteHost;
    private javax.swing.JLabel jLabelRemotePort;
    private javax.swing.JList jListRequests;
    private javax.swing.JPanel jPanelBottom;
    private javax.swing.JPanel jPanelConfig;
    private javax.swing.JPanel jPanelError;
    private javax.swing.JPanel jPanelInfo;
    private javax.swing.JPanel jPanelTop;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldListenPort;
    private javax.swing.JTextField jTextFieldRemoteHost;
    private javax.swing.JTextField jTextFieldRemotePort;
    // End of variables declaration//GEN-END:variables

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

}
