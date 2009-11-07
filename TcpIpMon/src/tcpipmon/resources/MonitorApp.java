package tcpipmon.resources;

import tcpipmon.TcpIpMonView;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.lang.StringUtils;
import tcpipmon.MonitorItem;


public class MonitorApp implements Runnable {

    private String remoteHost = "";
    private int remotePort;
    ServerSocket port;
    MonitorItem monitorItem;

    private ArrayList<TcpIpData> tcpDataList = new ArrayList<TcpIpData>();
    ExecutorService exec = Executors.newCachedThreadPool();

    public boolean startMonitoring(MonitorItem monitorItem, int listenPort, String remoteHost, int remotePort) {
        

        try {
            port = new ServerSocket(listenPort);

            setRemoteHost(remoteHost);
            setRemotePort(remotePort);
            this.monitorItem = monitorItem;
            
            exec.execute(this);

            
        } catch (IOException e) {
            System.out.println(String.format("Error: Could not bind to port [%s]. Another process may own it.", listenPort));
            return false;
        } catch (Exception e) {
            System.out.println(String.format("Error: Could not bind to port [%s], or a connection was interrupted.", listenPort));
            return false;
        }

        return true;
    }

    public void stopMonitoring(){
        exec.shutdownNow();
    }


    @Override
    public void run() {
        
        try {
                while (true) {
                    System.out.println("WAITING FOR CONNECTION");
                    Socket connection = port.accept();
                    exec.execute(new Worker(connection));
                }
            } catch (IOException e) {
            System.out.println("monitorApp.run() Error: Could not bind to port [%s]. Another process may own it.");
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

        private Socket connection;
        private boolean usesCR = false;

        public Worker(Socket connection) {
            this.connection = connection;
        }

        @Override
        public void run() {
            try {

                TcpIpData tcpData = new TcpIpData();

                // Get the request
                InputStream in = new BufferedInputStream(connection.getInputStream());

                // get the destination
                Socket sock = new Socket(remoteHost, remotePort);
                OutputStream out = new BufferedOutputStream(sock.getOutputStream());

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

                System.out.println("**** REQUEST ******");
                System.out.println(logStr.toString());
                tcpData.setRequest(logStr.toString());
                monitorItem.onRequestSend(tcpData);


                out.flush();

                // Get the response
                in = new BufferedInputStream(sock.getInputStream());
                // get the source
                out = new BufferedOutputStream(connection.getOutputStream());

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

                System.out.println("**** REPONSE ******");
                System.out.println(logStr.toString());
                tcpData.setResponse(logStr.toString());

                //sock.close();
                //connection.close();

                tcpDataList.add(tcpData);

                monitorItem.onResponseReceived();

            } catch (IOException e) {
                e.printStackTrace();
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
