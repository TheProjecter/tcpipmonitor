package tcpipmon.resources;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;
import tcpipmon.MonitorPanel;


public class MonitorApp implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(MonitorApp.class.getName());

    private String remoteHost = "";
    private int remotePort;
    ServerSocket port;
    Socket listenerSocket;
    MonitorPanel monitorPanel;
    int listenPort;
    boolean run = true;

    private ArrayList<TcpIpData> tcpDataList = new ArrayList<TcpIpData>();
    ExecutorService exec;

    public boolean startMonitoring(MonitorPanel monitorPanel, int listenPort, String remoteHost, int remotePort) {
        exec = Executors.newCachedThreadPool();

        try {
            this.listenPort = listenPort;
            port = new ServerSocket(listenPort);

            setRemoteHost(remoteHost);
            setRemotePort(remotePort);
            this.monitorPanel = monitorPanel;

            run = true;
            exec.execute(this);

            
        } catch (IOException e) {
            System.out.println(String.format("Error: Could not bind to port [%s]. Another process may own it.", listenPort));
            closeSocket();
            return false;
        } catch (Exception e) {
            closeSocket();
            LOGGER.log(Level.SEVERE, "startMonitoring: error occured executing listener thread.", e);
            return false;
        }

        return true;
    }

    public void stopMonitoringNow(){
        exec.shutdownNow();
        run = false;
        closeSocket();        
    }

    public void stopMonitoring(){        
        exec.shutdown();
        run = false;
        closeSocket();
    }

    private void closeSocket(){
        try {
            port.close();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "closeSocker: error occured closing server socket.", ex);
        }
    }


    @Override
    public void run() {
        
        try {
            while (run) {
                LOGGER.log(Level.INFO, "run:WAITING FOR CONNECTION");
                listenerSocket = port.accept();

                exec.execute(new Worker(listenerSocket, this));
            }
        } catch (SocketException se){
            LOGGER.log(Level.INFO, "run: socket server was closed -> accept() interrupted.");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "run: error occured listening for connection on port: " + listenPort);
        }
     }

    public void setRemoteHost(String remoteHost) {
        this.remoteHost = remoteHost;
    }

    public void setRemotePort(int remotePort) {
        this.remotePort = remotePort;
    }

    public ArrayList<TcpIpData> getTcpDataList() {
        return this.tcpDataList;
    }

    private class Worker implements Runnable {

        private Socket listenerConnection;
        private Socket remoteConnection;
        private boolean usesCR = false;
        private MonitorApp monitor;

        public Worker(Socket connection, MonitorApp monitor) {
            this.listenerConnection = connection;
            this.monitor = monitor;
        }

        @Override
        public void run() {
            try {

                TcpIpData tcpData = new TcpIpData();

                // Get the request
                InputStream in = new BufferedInputStream(listenerConnection.getInputStream());

                // get the destination
                remoteConnection = new Socket(remoteHost, remotePort);
                OutputStream out = new BufferedOutputStream(remoteConnection.getOutputStream());

                // get the log
                ByteArrayOutputStream logStr = new ByteArrayOutputStream();
                OutputStream log = new BufferedOutputStream(logStr);

                // copy headers
                long length = copyHeaders(in, out, log);

                System.out.println("*** Request Content-Length: " + length);
                log.flush();
                tcpData.setRequestHeader(logStr.toString());
                
                 // clear log
                logStr.reset();

                // copy content
                copyContent(in, out, log, length);
                
                log.flush();

                System.out.println("**** REQUEST: " + tcpData.toString());
                //System.out.println(logStr.toString());
                tcpData.setRequest(logStr.toString());
                monitorPanel.onRequestSend(tcpData);


                out.flush();

                // Get the response
                in = new BufferedInputStream(remoteConnection.getInputStream());
                // get the source
                out = new BufferedOutputStream(listenerConnection.getOutputStream());

                // clear log
                logStr.reset();

                // copy headers
                length = copyHeaders(in, out, log);
                System.out.println("*** Response Content-Length: " + length);
                log.flush();
                tcpData.setResponseHeader(logStr.toString());
                logStr.reset();

                // copy content
                copyContent(in, out, log, length);
                out.flush();
                log.flush();

                System.out.println("**** REPONSE: " + tcpData.toString());
               // System.out.println(logStr.toString());
                tcpData.setResponse(logStr.toString());

                //sock.close();
                //connection.close();

                tcpDataList.add(tcpData);

                monitorPanel.onResponseReceived();

            } catch(UnknownHostException uhe){
                //
            } catch (IOException e) {
                e.printStackTrace();

            } finally {
                if(remoteConnection!= null){
                    try{
                        remoteConnection.close();
                    } catch(IOException e){
                        System.out.println("Worker.run: error occured closing remoteConnection!");

                    }
                }
                try{
                    listenerConnection.close();
                } catch(IOException e){
                    System.out.println("Worker.run: error occured closing listenerConnection!");

                }
            }
        }

        private void copyContent(InputStream in, OutputStream out,
                OutputStream log, long length) throws IOException {
            long counter;
            int value;
            counter = 0;
            value = 0;
            while (value != -1 && counter < length) {
                value = in.read();
                out.write(value);
                log.write(value);
                counter++;
            }
        }

        private long copyHeaders(InputStream in, OutputStream out,
                OutputStream log) throws IOException {
            boolean finished = false;
            long length = 0;
            while (!finished) {
                String line = readLine(in);
                if (line.length() == 0) {
                    finished = true;
                } else if (new String(line).startsWith("Content-Length: ")) {
                    length = Long.parseLong(StringUtils.substringAfter(line, "Content-Length: "));
                }
                writeLine(out, line);
                writeLine(log, line);
            }
            return length;
        }

        private void writeLine(OutputStream out, String line) throws IOException {
            byte[] bytes = line.getBytes();
            for (byte b : bytes) {
                out.write(b);
            }
            if (usesCR) {
                out.write('\r');
            }
            out.write('\n');
        }

        private String readLine(InputStream in) throws IOException {
            int value = 0;
            StringBuilder builder = new StringBuilder();
            while ((value = in.read()) != -1) {
                if (value == '\n') {
                    break;
                }
                if (value != '\r') {
                    builder.append((char) value);
                } else {
                    usesCR = true;
                }
            }
            return builder.toString();
        }
    }
}
