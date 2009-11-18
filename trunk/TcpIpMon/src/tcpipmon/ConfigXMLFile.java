/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tcpipmon;
import java.util.*;
import javax.xml.stream.*;
import java.io.*;

/**
 *
 * @author Slawek
 */
public class ConfigXMLFile {
    private static final String CONFIG_FILE_PATH = "config.xml";
    private static final String ELEMENT_ROOT = "TcpIpMonitorConfig";
    private static final String ELEMENT_STORED_MONITORS = "StoredMonitors";
    private static final String ELEMENT_MONITOR = "Monitor";
    private static final String MONITOR_ATTRIBUTE_ID = "id";
    private static final String MONITOR_ATTRIBUTE_LISTENER_PORT = "listenerPort";
    private static final String MONITOR_ATTRIBUTE_REMOTE_PORT = "remotePort";
    private static final String MONITOR_ATTRIBUTE_REMOTE_HOST = "remoteHost";
    private static final String ELEMENT_AUTO_START = "AutoStart";
    private static final String ELEMENT_MONITOR_NAME = "Name";

    //singleton instance
    private static ConfigXMLFile CONFIG_XML = null;

    public static ConfigXMLFile getConfigXML(){
        if(CONFIG_XML == null){
            CONFIG_XML = new ConfigXMLFile();
        }
        return CONFIG_XML;
    }

    private File configFile = null;

    private ConfigXMLFile(){
        configFile = new File(CONFIG_FILE_PATH);
    }
   
    private void checkFile() throws IOException{
        if(!configFile.exists()){
            configFile.createNewFile();
        }
    }


    public boolean saveMonitors(List<MonitorModel> monitors){
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        FileWriter fileWriter = null;

        try{
            this.checkFile();

            fileWriter = new FileWriter(CONFIG_FILE_PATH);
            XMLStreamWriter writer = outputFactory.createXMLStreamWriter(fileWriter);

            writer.writeStartDocument();

            writer.writeStartElement(ELEMENT_ROOT);
            writer.writeStartElement(ELEMENT_STORED_MONITORS);

            for(MonitorModel monitor: monitors){
                writer.writeStartElement(ELEMENT_MONITOR);
                writer.writeAttribute(MONITOR_ATTRIBUTE_ID, String.valueOf(monitor.getId()));
                writer.writeAttribute(MONITOR_ATTRIBUTE_REMOTE_HOST, monitor.getRemoteHost());
                writer.writeAttribute(MONITOR_ATTRIBUTE_REMOTE_PORT, String.valueOf(monitor.getRemotePort()));
                writer.writeAttribute(MONITOR_ATTRIBUTE_LISTENER_PORT, String.valueOf(monitor.getListenerPort()));

                writer.writeStartElement(ELEMENT_AUTO_START);
                writer.writeCharacters(String.valueOf(monitor.isAutoStart()));
                writer.writeEndElement();

                writer.writeStartElement(ELEMENT_MONITOR_NAME);
                writer.writeCharacters(monitor.getName());
                writer.writeEndElement();

                writer.writeEndElement();//end monitor
            }

            writer.writeEndElement();//end stored monitors
            writer.writeEndElement();//end root element

            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
        } catch(IOException e){
            e.printStackTrace();
            return false;
        } catch(XMLStreamException se){
            se.printStackTrace();
            return false;
        } finally {
            if(fileWriter != null){
                try {
                    fileWriter.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return true;
    }

    public List<MonitorModel> getMonitors(){
        ArrayList<MonitorModel> monitors = new ArrayList<MonitorModel>();
        MonitorModel monitor = null;

        XMLInputFactory inputFactor = XMLInputFactory.newInstance();
        FileReader fileReader = null;
        try{
            this.checkFile();

            fileReader = new FileReader(CONFIG_FILE_PATH);
            XMLStreamReader reader = inputFactor.createXMLStreamReader(fileReader);

            while(reader.hasNext()){
                int eventType = reader.getEventType();
                switch(eventType){
                    case XMLStreamConstants.START_ELEMENT:
                        String elementName = reader.getLocalName();
                        if(elementName.equals(ELEMENT_MONITOR)){
                            monitor = new MonitorModel();
                            for(int i = 0; i < reader.getAttributeCount(); i++){
                                String attributeName = reader.getAttributeName(i).getLocalPart();
                                if(attributeName.equals(MONITOR_ATTRIBUTE_ID)){
                                    monitor.setId(Long.parseLong(reader.getAttributeValue(i)));
                                } else if(attributeName.equals(MONITOR_ATTRIBUTE_LISTENER_PORT)){
                                    monitor.setListenerPort(Long.parseLong(reader.getAttributeValue(i)));
                                } else if(attributeName.equals(MONITOR_ATTRIBUTE_REMOTE_HOST)){
                                    monitor.setRemoteHost(reader.getAttributeValue(i));
                                } else if(attributeName.equals(MONITOR_ATTRIBUTE_REMOTE_PORT)){
                                    monitor.setRemotePort(Long.parseLong(reader.getAttributeValue(i)));
                                }
                            }
                        } else if(elementName.equals(ELEMENT_AUTO_START)){
                            monitor.setAutoStart(Boolean.parseBoolean(reader.getElementText()));
                        } else if(elementName.equals(ELEMENT_MONITOR_NAME)){
                            monitor.setName(reader.getElementText());
                        }

                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        if(reader.getLocalName().equals(ELEMENT_MONITOR)){
                            monitors.add(monitor);
                        }
                        break;
                    default:
                        break;
                }

                reader.next();

            }
        } catch(IOException e){
            e.printStackTrace();
            return null;
        } catch(XMLStreamException se){
            se.printStackTrace();
            return null;
        } finally {
            if(fileReader != null){
                try {
                    fileReader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return monitors;
    }

    public MonitorModel getMonitor(long id){
        for(MonitorModel monitor: getMonitors()){
            if(monitor.getId() == id){
                return monitor;
            }
        }
        return null;
    }

    public boolean deleteMonitor(long id){
        List<MonitorModel> monitors = getMonitors();
        for(MonitorModel monitor: monitors){
            if(monitor.getId() == id){
                monitors.remove(monitor);
                break;
            }
        }
        return saveMonitors(monitors);
    }

    public boolean addMonitor(MonitorModel monitor){
        List<MonitorModel> monitors = getMonitors();
        monitors.add(monitor);
        return saveMonitors(monitors);
    }

    public boolean updateMonitor(MonitorModel monitor){
        long id = monitor.getId();
        List<MonitorModel> monitors = getMonitors();
        int index = 0;
        for(MonitorModel m: monitors){
            if(m.getId() == id){
                index = monitors.indexOf(m);
                monitors.remove(m);
                break;
            }
        }
        monitors.add(index, monitor);
        return saveMonitors(monitors);
    }
}
