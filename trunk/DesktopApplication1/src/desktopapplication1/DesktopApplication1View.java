/*
 * DesktopApplication1View.java
 */

package desktopapplication1;

import desktopapplication1.resources.ListenerInterface;
import desktopapplication1.resources.NewMonitorApp;
import desktopapplication1.resources.TcpIpData;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 * The application's main frame.
 */
public class DesktopApplication1View extends FrameView {

    public void notifyRequestHeaderListener(TcpIpData tcpIpData){
        if(tcpIpData.getRequestHeader() != null){
            requestHeader.setText(tcpIpData.getRequestHeader());
        } else {
            requestHeader.setText("");
        }
    }

    public void notifyRequestListener(TcpIpData tcpIpData){
        if(tcpIpData.getRequest() != null){
            requestContents.setText(tcpIpData.getRequest());
        } else {
            requestContents.setText("");
        }
    }

    public void notifyResponseHeaderListener(TcpIpData tcpIpData){
        if(tcpIpData.getResponseHeader() != null){
            responseHeader.setText(tcpIpData.getResponseHeader());
        } else {
            responseHeader.setText("");
        }
    }

    public void notifyResponseListener(TcpIpData tcpIpData){
        if(tcpIpData.getResponse() != null){
            responseContents.setText(tcpIpData.getResponse());
        } else {
            responseContents.setText("");
        }
    }

    public DesktopApplication1View(SingleFrameApplication app) {
        super(app);

        initComponents();

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = DesktopApplication1.getApplication().getMainFrame();
            aboutBox = new DesktopApplication1AboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        DesktopApplication1.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        connectionPanel = new javax.swing.JScrollPane();
        connectionTree = new javax.swing.JTree();
        infoPanel = new javax.swing.JPanel();
        reqTimeLabel = new javax.swing.JLabel();
        resTimeLabel = new javax.swing.JLabel();
        typeLabel = new javax.swing.JLabel();
        reqTime = new javax.swing.JTextField();
        resTime = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        reqResPanel = new javax.swing.JSplitPane();
        requestPanel = new javax.swing.JSplitPane();
        requestHeaderPane = new javax.swing.JScrollPane();
        requestHeader = new javax.swing.JTextArea();
        requestContentsPane = new javax.swing.JScrollPane();
        requestContents = new javax.swing.JTextArea();
        responsePanel = new javax.swing.JSplitPane();
        responseHeaderPane = new javax.swing.JScrollPane();
        responseHeader = new javax.swing.JTextArea();
        responseContentsPane = new javax.swing.JScrollPane();
        responseContents = new javax.swing.JTextArea();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        testButton = new javax.swing.JButton();

        mainPanel.setName("mainPanel"); // NOI18N

        connectionPanel.setName("connectionPanel"); // NOI18N

        connectionTree.setName("connectionTree"); // NOI18N
        connectionPanel.setViewportView(connectionTree);

        infoPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        infoPanel.setName("infoPanel"); // NOI18N

        reqTimeLabel.setName("reqTimeLabel"); // NOI18N

        resTimeLabel.setName("resTimeLabel"); // NOI18N

        typeLabel.setName("typeLabel"); // NOI18N

        reqTime.setEditable(false);
        reqTime.setName("reqTime"); // NOI18N

        resTime.setEditable(false);
        resTime.setName("resTime"); // NOI18N

        jTextField1.setEditable(false);
        jTextField1.setName("jTextField1"); // NOI18N

        javax.swing.GroupLayout infoPanelLayout = new javax.swing.GroupLayout(infoPanel);
        infoPanel.setLayout(infoPanelLayout);
        infoPanelLayout.setHorizontalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addComponent(typeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(resTimeLabel)
                            .addComponent(reqTimeLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(reqTime, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(resTime, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))))
                .addGap(68, 68, 68))
        );
        infoPanelLayout.setVerticalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reqTimeLabel)
                    .addComponent(reqTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(resTimeLabel)
                    .addComponent(resTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(typeLabel)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        reqResPanel.setName("reqResPanel"); // NOI18N

        requestPanel.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        requestPanel.setName("requestPanel"); // NOI18N

        requestHeaderPane.setName("requestHeaderPane"); // NOI18N

        requestHeader.setColumns(20);
        requestHeader.setEditable(false);
        requestHeader.setRows(5);
        requestHeader.setName("requestHeader"); // NOI18N
        requestHeaderPane.setViewportView(requestHeader);

        requestPanel.setTopComponent(requestHeaderPane);

        requestContentsPane.setName("requestContentsPane"); // NOI18N

        requestContents.setColumns(20);
        requestContents.setEditable(false);
        requestContents.setRows(5);
        requestContents.setName("requestContents"); // NOI18N
        requestContentsPane.setViewportView(requestContents);

        requestPanel.setRightComponent(requestContentsPane);

        reqResPanel.setLeftComponent(requestPanel);

        responsePanel.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        responsePanel.setName("responsePanel"); // NOI18N

        responseHeaderPane.setName("responseHeaderPane"); // NOI18N

        responseHeader.setColumns(20);
        responseHeader.setEditable(false);
        responseHeader.setRows(5);
        responseHeader.setName("responseHeader"); // NOI18N
        responseHeaderPane.setViewportView(responseHeader);

        responsePanel.setTopComponent(responseHeaderPane);

        responseContentsPane.setName("responseContentsPane"); // NOI18N

        responseContents.setColumns(20);
        responseContents.setEditable(false);
        responseContents.setRows(5);
        responseContents.setName("responseContents"); // NOI18N
        responseContentsPane.setViewportView(responseContents);

        responsePanel.setRightComponent(responseContentsPane);

        reqResPanel.setRightComponent(responsePanel);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(infoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(connectionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reqResPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(reqResPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(infoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(connectionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)))
                .addContainerGap())
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setName("fileMenu"); // NOI18N

        jMenuItem1.setName("jMenuItem1"); // NOI18N
        fileMenu.add(jMenuItem1);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(desktopapplication1.DesktopApplication1.class).getContext().getActionMap(DesktopApplication1View.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        testButton.setName("testButton"); // NOI18N
        testButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 722, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addGap(190, 190, 190)
                .addComponent(testButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 332, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(statusPanelLayout.createSequentialGroup()
                        .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(statusMessageLabel)
                            .addComponent(statusAnimationLabel)
                            .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3))
                    .addComponent(testButton)))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void testButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testButtonActionPerformed

        int listenPort = 1248;
	String remoteHost = "localhost"; //"10.26.1.80";
	int remotePort = 1234; //22874;
        
        DesktopApplication1View visApp = this;


        NewMonitorApp app = new NewMonitorApp();
        app.startMonitoring(visApp, listenPort,remoteHost,remotePort);

    }//GEN-LAST:event_testButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane connectionPanel;
    private javax.swing.JTree connectionTree;
    private javax.swing.JPanel infoPanel;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JSplitPane reqResPanel;
    private javax.swing.JTextField reqTime;
    private javax.swing.JLabel reqTimeLabel;
    private javax.swing.JTextArea requestContents;
    private javax.swing.JScrollPane requestContentsPane;
    private javax.swing.JTextArea requestHeader;
    private javax.swing.JScrollPane requestHeaderPane;
    private javax.swing.JSplitPane requestPanel;
    private javax.swing.JTextField resTime;
    private javax.swing.JLabel resTimeLabel;
    private javax.swing.JTextArea responseContents;
    private javax.swing.JScrollPane responseContentsPane;
    private javax.swing.JTextArea responseHeader;
    private javax.swing.JScrollPane responseHeaderPane;
    private javax.swing.JSplitPane responsePanel;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JButton testButton;
    private javax.swing.JLabel typeLabel;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;
}
